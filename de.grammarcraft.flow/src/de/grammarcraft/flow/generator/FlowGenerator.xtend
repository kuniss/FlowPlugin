package de.grammarcraft.flow.generator

import com.google.inject.Inject
import de.grammarcraft.flow.flow.ExternalReferencePort
import de.grammarcraft.flow.flow.ForeignPort
import de.grammarcraft.flow.flow.FunctionUnit
import de.grammarcraft.flow.flow.OwnPort
import de.grammarcraft.flow.flow.Port
import de.grammarcraft.flow.flow.Stream
import de.grammarcraft.flow.flow.TypeAnnotatedConnection
import de.grammarcraft.xtend.flow.annotations.InputPort
import de.grammarcraft.xtend.flow.annotations.OutputPort
import java.util.HashMap
import java.util.HashSet
import java.util.Map
import java.util.Set
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.common.types.JvmAnnotationAnnotationValue
import org.eclipse.xtext.common.types.JvmAnnotationReference
import org.eclipse.xtext.common.types.JvmCustomAnnotationValue
import org.eclipse.xtext.common.types.JvmDeclaredType
import org.eclipse.xtext.common.types.JvmGenericType
import org.eclipse.xtext.common.types.JvmType
import org.eclipse.xtext.common.types.JvmTypeReference
import org.eclipse.xtext.common.types.access.binary.asm.JvmAnnotationReferenceBuilder
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.xtext.xbase.XFeatureCall
import org.eclipse.xtext.xbase.XListLiteral
import org.eclipse.xtext.xbase.XStringLiteral
import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotation
import org.eclipse.xtext.xbase.services.XbaseGrammarAccess.XLiteralElements

enum FlowDirection {
    IN, OUT
}

class FlowGenerator implements IGenerator {
    @Inject extension IQualifiedNameProvider

    override doGenerate(Resource resource, IFileSystemAccess fsa) {
        val directConnections = initTransitiveClosure(resource);
        val transitiveClosure = computeTransitiveClosure(directConnections)
        
        val streams = resource.allContents.filter(typeof(Stream)).toList
        val aliasRefs = resource.allContents.filter(typeof(FunctionUnit)).filter[aliasType!=null].toList

        val portTypesFromStreams = computePortTypesFromStreams(streams)
        val portTypesFromExternalRefs = computePortTypesFromExternalRefs(aliasRefs, streams)

        val inferredPortTypes = inferPortTypes(transitiveClosure, portTypesFromStreams, portTypesFromExternalRefs, resource.allContents.toIterable.filter(typeof(OwnPort)))
        
        resource.allContents.toIterable.filter(typeof(FunctionUnit)).filter[aliasType==null].forEach[
            fsa.generateFile('''«fullyQualifiedName.skipLast(1).segments.join('/')»/«name».xtend''', generate(inferredPortTypes))
        ]
        
        fsa.generateFile("log.txt", '''
            // own ports
            «resource.allContents.toIterable.filter(typeof(OwnPort))
                .map['''own port "«fullQualifiedName»"'''].join('\n')»
            // foreign ports
            «resource.allContents.toIterable.filter(typeof(ForeignPort))
                .map['''foreign port "«fullQualifiedName»"'''].join('\n')»
            // external ports
            «resource.allContents.toIterable.filter(typeof(ExternalReferencePort))
                .map['''external port "«fullQualifiedName»"'''].join('\n')»
            // port types directly inferred from stream connection types
            «portTypesFromStreams.entrySet.map['''port «key»: «value?.qualifiedName»'''].join('\n')»
            // port types directly inferred from external references
            «portTypesFromExternalRefs.entrySet.map['''port «key»: «value»'''].join('\n')»
            // inferred port types
            «inferredPortTypes.entrySet.map['''inferred «key»: «value»'''].join('\n')»
        ''')
        
    }
    
    def computePortTypesFromExternalRefs(Iterable<FunctionUnit> units, Iterable<Stream> streams) {
        val portTypesFromExternalRefs = computePortTypesFromAliases(units)
        val portTypesFromStreams = computePortTypesFromExternalsRefsInStreams(streams)
        portTypesFromExternalRefs.putAll(portTypesFromStreams)
        return portTypesFromExternalRefs
    }
    

    def inferPortTypes(Map<String, Set<String>> transitiveClosure, Map<String, JvmType> portTypesFromStreams, Map<String, String> portTypesFromExternalRefs, Iterable<OwnPort> ownPorts) {
        val inferredPortTypes = new HashMap<String, String>
        ownPorts.forEach[
            val portName = it.fullQualifiedName
            for(transitivePortName : transitiveClosure.get(portName))
            {
                if (portTypesFromStreams.containsKey(transitivePortName)) {
                    inferredPortTypes.put(portName, portTypesFromStreams.get(transitivePortName).identifier)
                }
                else if (portTypesFromExternalRefs.containsKey(transitivePortName)) {
                    inferredPortTypes.put(portName, portTypesFromExternalRefs.get(transitivePortName))
                }
            }
        ]
        return inferredPortTypes
    }
    
    def String fullQualifiedName(Port port) {
        switch port {
            OwnPort: {
                val fu = port.eContainer.eContainer as FunctionUnit
                '''«fu.fullyQualifiedName».«port.port?.name»'''
            } 
            ExternalReferencePort: '''«port.type.identifier».«port.port?.name»'''
            ForeignPort: '''«port.functionUnit.fullyQualifiedName».«port.port?.name»'''
        }
    }
    
    
    def initTransitiveClosure(Resource resource) {
        val Map<String, Set<String>> directConnections = new HashMap;
        for (stream: resource.allContents.toIterable.filter(typeof(Stream))) {
            val leftPortName =  stream.leftPort.fullQualifiedName
            val rightPortName = stream.rightPort.fullQualifiedName
            directConnections.addConnection(leftPortName, rightPortName)
        }
        // connections to itself
        for(port: resource.allContents.toIterable.filter(typeof(Port))) {
            val portName = port.fullQualifiedName
            directConnections.addConnection(portName, portName)
        }
        return directConnections
    }
    
    private def addConnection(Map<String, Set<String>> directConnections, String leftPortName, String rightPortName) {
        if (directConnections.containsKey(leftPortName)) {
            val targetPorts = directConnections.get(leftPortName)
            targetPorts.add(rightPortName)
        }
        else {
            val Set<String> targetPorts = new HashSet
            targetPorts.add(rightPortName)
            directConnections.put(leftPortName, targetPorts)
        }
    }
    
    def computeTransitiveClosure(Map<String, Set<String>> directConnections) {
        val transitiveClosure = directConnections
        val ports = directConnections.keySet 
        for(portK: ports) {
            for(portI: ports) {
                if (transitiveClosure.hasConnection(portI, portK)) {
                    for(portJ: ports) {
                        if (transitiveClosure.hasConnection(portK, portJ))
                            transitiveClosure.addConnection(portI, portJ)                            
                    }
                }
            }
        }
        return transitiveClosure
    }
    
    def static boolean hasConnection(Map<String, Set<String>> transitiveClosure, String leftPortName, String rightPortName) {
        transitiveClosure.get(leftPortName).contains(rightPortName)
    }
    
    def Map<String, JvmType> computePortTypesFromStreams(Iterable<Stream> streams) {
        val portTypes = new HashMap<String, JvmType>
        streams.filter[connection instanceof TypeAnnotatedConnection].forEach[ 
            val portTypeRef = (connection as TypeAnnotatedConnection).msgType
            if (!(leftPort instanceof ExternalReferencePort))
                portTypes.put(leftPort.fullQualifiedName, portTypeRef.type)
            if (!(rightPort instanceof ExternalReferencePort))
                portTypes.put(rightPort.fullQualifiedName, portTypeRef.type)
        ]
        return portTypes
    }
           
    def computePortTypesFromExternalsRefsInStreams(Iterable<Stream> streams) {
        val portTypes = new HashMap<String, String> 
        
        streams.filter[leftPort instanceof ExternalReferencePort].forEach[
            val portType = (leftPort as ExternalReferencePort).outputPortType
            if (rightPort instanceof ExternalReferencePort) {
                val otherPortType = (rightPort as ExternalReferencePort).inputPortType
                if (portType != otherPortType)
                    // TODO log error
                    println('''left port «leftPort» of type «portType» mismatch right port «rightPort» of type «otherPortType»''')               
            }
            else
                portTypes.put(rightPort.fullQualifiedName, portType)
        ]
            
        streams.filter[rightPort instanceof ExternalReferencePort].forEach[
            val portType = (rightPort as ExternalReferencePort).inputPortType
            if (!(leftPort instanceof ExternalReferencePort)) {
                portTypes.put(leftPort.fullQualifiedName, portType)
            }
        ]
        return portTypes
    }
    
    def String inputPortType(ExternalReferencePort portSpec) {
        if(portSpec.type.type instanceof JvmDeclaredType) {
            val portDeclaringType = portSpec.type.type as JvmDeclaredType
            val fuAnnotation = portDeclaringType?.annotations.findFirst[annotation.qualifiedName == de.grammarcraft.xtend.flow.annotations.FunctionUnit.name] // should be only one
            val inputPortsAnnotationValue = fuAnnotation?.values.findFirst[valueName == 'inputPorts'] as JvmCustomAnnotationValue
            val inputPortAnnotationsList = inputPortsAnnotationValue?.values.findFirst[it instanceof XListLiteral] as XListLiteral
            var XAnnotation inputPortAnnotation 
            if (portSpec.port == null) { // implicit on and only port of the particular function unit has to be used
                val xAnnotationList = inputPortAnnotationsList.elements.filter(typeof(XAnnotation)).map[it as XAnnotation]
                if (xAnnotationList.length > 1) {
                    // TODO log warning "port specification is ambiguous as no port is specified but function unit has more than one port"
                }
                inputPortAnnotation = xAnnotationList.head
            }
            else {
                inputPortAnnotation = inputPortAnnotationsList.elements.filter(typeof(XAnnotation)).map[it as XAnnotation].
                findFirst[(elementValuePairs.findFirst[it.element.identifier == de.grammarcraft.xtend.flow.annotations.InputPort.name + '.name()']?.value as XStringLiteral).value == portSpec.port.name]
            }
            
            if (inputPortAnnotation != null) {
                val typeExpression = inputPortAnnotation?.elementValuePairs.findFirst[it.element.identifier == '''«de.grammarcraft.xtend.flow.annotations.InputPort.name».type()'''.toString]?.value as XFeatureCall
                val type = typeExpression.feature as JvmGenericType
                val typeParams = type.typeParameters.join(',')
                val typeRepr = type.identifier + if (typeParams.length > 0) '''<«typeParams»>''' else ''
                return typeRepr
            }
        }
        return 'port_type_could_not_be_determined'
    }

    def String outputPortType(ExternalReferencePort portSpec) {
        if(portSpec.type.type instanceof JvmDeclaredType) {
            val portDeclaringType = portSpec.type.type as JvmDeclaredType
            val fuAnnotation = portDeclaringType?.annotations.findFirst[annotation.qualifiedName == de.grammarcraft.xtend.flow.annotations.FunctionUnit.name] // should be only one
            val inputPortsAnnotationValue = fuAnnotation?.values.findFirst[valueName == 'outputPorts'] as JvmCustomAnnotationValue
            val inputPortAnnotationsList = inputPortsAnnotationValue?.values.findFirst[it instanceof XListLiteral] as XListLiteral
            var XAnnotation inputPortAnnotation 
            if (portSpec.port == null) { // implicit on and only port of the particular function unit has to be used
                val xAnnotationList = inputPortAnnotationsList.elements.filter(typeof(XAnnotation)).map[it as XAnnotation]
                if (xAnnotationList.length > 1) {
                    // TODO log warning "port specification is ambiguous as no port is specified but function unit has more than one port"
                }
                inputPortAnnotation = xAnnotationList.head
            }
            else {
                inputPortAnnotation = inputPortAnnotationsList.elements.filter(typeof(XAnnotation)).map[it as XAnnotation].
                findFirst[(elementValuePairs.findFirst[it.element.identifier == de.grammarcraft.xtend.flow.annotations.OutputPort.name + '.name()']?.value as XStringLiteral).value == portSpec.port.name]
            }
            if (inputPortAnnotation != null) {
                val typeExpression = inputPortAnnotation?.elementValuePairs.findFirst[it.element.identifier == '''«de.grammarcraft.xtend.flow.annotations.OutputPort.name».type()'''.toString]?.value as XFeatureCall
                val type = typeExpression.feature as JvmGenericType
                val typeParams = type.typeParameters.join(',')
                val typeRepr = type.identifier + if (typeParams.length > 0) '''<«typeParams»>''' else ''
                return typeRepr
            }
        }
        return 'port_type_could_not_be_determined'
    }
    
    def computePortTypesFromAliases(Iterable<FunctionUnit> aliasedFunctionUnits) {
        val Map<String, String> portTypes = new HashMap
        aliasedFunctionUnits.forEach[
            computePortTypesFromAlias(portTypes, name, aliasType)
        ]
        return portTypes
    }
    
    def computePortTypesFromAlias(Map<String, String> portTypes, String functionUnitName, JvmTypeReference functionUnitType) {
        if (functionUnitType.type instanceof JvmDeclaredType) {
            val declaredType = functionUnitType as JvmDeclaredType
            val inputPortAnnotations = declaredType.annotations.filter(typeof(InputPort))
            inputPortAnnotations.forEach[
                portTypes.put('''«functionUnitName».«name»''', type.name)
            ]
        }
    }
        
    def CharSequence generate(FunctionUnit unit, HashMap<String, String> inferredPortTypes) '''
        package «unit.fullyQualifiedName.skipLast(1)»
        
        import de.grammarcraft.xtend.flow.annotations.FunctionUnit
        import de.grammarcraft.xtend.flow.annotations.InputPort
        import de.grammarcraft.xtend.flow.annotations.OutputPort
                
        @FunctionUnit(
            inputPorts = #[
                «unit.streams.map[leftPort].distinct.filter(typeof(OwnPort)).join(", ")[generateInputPort(inferredPortTypes)]»
            ],
            outputPorts = #[
                «unit.streams.map[rightPort].distinct.filter(typeof(OwnPort)).join(", ")[generateOutputPort(inferredPortTypes)]»
            ]
        )
        class «unit.name» {
        }
    '''
    
    /** Remove all double values in a list, turning it into a list of unique values */
    def static <T> distinct(Iterable<? extends T> values) {
        values.groupBy[it].toPairs.map[value.head]
    }

    /** transforms a map into a list of pairs */
    def static <K, V> toPairs(Map<K, V> map) {
        map.entrySet.map[it.key -> it.value].toList
    }
    
    def generateInputPort(OwnPort input, HashMap<String, String> inferredPortTypes) {
        var portTypeName = inferredPortTypes.get(input.fullQualifiedName)
        if (portTypeName == null || portTypeName.length == 0)
            portTypeName = '''<unknow type for "«input.fullQualifiedName»"'''
        '''
            @InputPort(name="«input.port.name»", type=«portTypeName»)
        '''
    }
    
    def generateOutputPort(OwnPort output, HashMap<String, String> inferredPortTypes) '''
        @OutputPort(name="«output.port.name»", type=«inferredPortTypes.get(output.fullQualifiedName)»)
    '''
    
}