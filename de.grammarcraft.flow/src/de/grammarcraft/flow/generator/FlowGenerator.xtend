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
import org.eclipse.xtext.common.types.JvmCustomAnnotationValue
import org.eclipse.xtext.common.types.JvmDeclaredType
import org.eclipse.xtext.common.types.JvmGenericType
import org.eclipse.xtext.common.types.JvmType
import org.eclipse.xtext.common.types.JvmTypeReference
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.xtext.xbase.XFeatureCall
import org.eclipse.xtext.xbase.XListLiteral
import org.eclipse.xtext.xbase.XStringLiteral
import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotation
import org.eclipse.xtext.xbase.lib.Functions.Function1

enum FlowDirection {
    IN, OUT
}

class FlowGenerator implements IGenerator {
    @Inject extension IQualifiedNameProvider
    
    var Map<String, String> inferredPortTypes 

    override doGenerate(Resource resource, IFileSystemAccess fsa) {
        val directConnections = initTransitiveClosure(resource);
        val transitiveClosure = computeTransitiveClosure(directConnections)
        
        val streams = resource.allContents.filter(typeof(Stream)).toList
        val aliasRefs = resource.allContents.filter(typeof(FunctionUnit)).filter[aliasType!=null].toList

        val portTypesFromStreams = computePortTypesFromStreams(streams)
        val portTypesFromExternalRefs = computePortTypesFromExternalRefs(aliasRefs, streams)

        inferredPortTypes = inferPortTypes(transitiveClosure, portTypesFromStreams, portTypesFromExternalRefs, resource.allContents.toIterable.filter(typeof(OwnPort)))
        
        resource.allContents.toIterable.filter(typeof(FunctionUnit)).filter[aliasType==null].forEach[
            fsa.generateFile('''«fullyQualifiedName.skipLast(1).segments.join('/')»/«name».xtend''', generate(inferredPortTypes))
        ]
        
        fsa.generateFile("log.txt", '''
            // own ports
            «resource.allContents.toIterable.filter(typeof(OwnPort)).distinct[fullQualifiedName]
                .map['''own port "«fullQualifiedName»"'''].join('\n')»
            // foreign ports
            «resource.allContents.toIterable.filter(typeof(ForeignPort)).distinct[fullQualifiedName]
                .map['''foreign port "«fullQualifiedName»"'''].join('\n')»
            // external ports
            «resource.allContents.toIterable.filter(typeof(ExternalReferencePort)).distinct[fullQualifiedName]
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
            val inputPortAnnotation = portSpec.inputPortAnnotation
            if (inputPortAnnotation != null) {
                val typeExpression = inputPortAnnotation?.elementValuePairs.findFirst[it.element.identifier == '''«InputPort.name».type()'''.toString]?.value as XFeatureCall
                val type = typeExpression.feature as JvmGenericType
                val typeParams = type.typeParameters.join(',')
                val typeRepr = type.identifier + if (typeParams.length > 0) '''<«typeParams»>''' else ''
                return typeRepr
            }
        }
        return 'port_type_could_not_be_determined'
    }
    
    def inputPortAnnotation(ExternalReferencePort portSpec) {
            val portDeclaringType = portSpec.type.type as JvmDeclaredType
            val fuAnnotation = portDeclaringType?.annotations.findFirst[annotation.qualifiedName == de.grammarcraft.xtend.flow.annotations.FunctionUnit.name] // should be only one
            val inputPortsAnnotationValue = fuAnnotation?.values.findFirst[valueName == 'inputPorts'] as JvmCustomAnnotationValue
            val inputPortAnnotationsList = inputPortsAnnotationValue?.values.findFirst[it instanceof XListLiteral] as XListLiteral
            if (portSpec.port == null) { // implicit on and only port of the particular function unit has to be used
                val xAnnotationList = inputPortAnnotationsList.elements.filter(typeof(XAnnotation)).map[it as XAnnotation]
                if (xAnnotationList.length > 1) {
                    // TODO log warning "port specification is ambiguous as no port is specified but function unit has more than one port"
                }
                xAnnotationList.head
            }
            else {
                inputPortAnnotationsList.elements.filter(typeof(XAnnotation)).map[it as XAnnotation].
                findFirst[(elementValuePairs.findFirst[it.element.identifier == InputPort.name + '.name()']?.value as XStringLiteral).value == portSpec.port.name]
            }
    }
    

    def String outputPortType(ExternalReferencePort portSpec) {
        if(portSpec.type.type instanceof JvmDeclaredType) {
            val outputPortAnnotation = portSpec.outputPortAnnotation
            if (outputPortAnnotation != null) {
                val typeExpression = outputPortAnnotation.elementValuePairs.findFirst[it.element.identifier == '''«OutputPort.name».type()'''.toString]?.value as XFeatureCall
                val type = typeExpression.feature as JvmGenericType
                val typeParams = type.typeParameters.join(',')
                val typeRepr = type.identifier + if (typeParams.length > 0) '''<«typeParams»>''' else ''
                return typeRepr
            }
        }
        return 'port_type_could_not_be_determined'
    }
    
    def outputPortAnnotation(ExternalReferencePort portSpec) {
        val portDeclaringType = portSpec.type.type as JvmDeclaredType
        val fuAnnotation = portDeclaringType?.annotations.findFirst[annotation.qualifiedName == de.grammarcraft.xtend.flow.annotations.FunctionUnit.name] // should be only one
        val inputPortsAnnotationValue = fuAnnotation?.values.findFirst[valueName == 'outputPorts'] as JvmCustomAnnotationValue
        val inputPortAnnotationsList = inputPortsAnnotationValue?.values.findFirst[it instanceof XListLiteral] as XListLiteral
        if (portSpec.port == null) { // implicit on and only port of the particular function unit has to be used
            val xAnnotationList = inputPortAnnotationsList.elements.filter(typeof(XAnnotation)).map[it as XAnnotation]
            if (xAnnotationList.length > 1) {
                // TODO log warning "port specification is ambiguous as no port is specified but function unit has more than one port"
            }
            xAnnotationList.head
        }
        else {
            inputPortAnnotationsList.elements.filter(typeof(XAnnotation)).map[it as XAnnotation].
            findFirst[(elementValuePairs.findFirst[it.element.identifier == OutputPort.name + '.name()']?.value as XStringLiteral).value == portSpec.port.name]
        }
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
        
    def CharSequence generate(FunctionUnit unit, Map<String, String> inferredPortTypes) '''
        package «unit.fullyQualifiedName.skipLast(1)»
        
        import de.grammarcraft.xtend.flow.annotations.FunctionUnit
        import de.grammarcraft.xtend.flow.annotations.InputPort
        import de.grammarcraft.xtend.flow.annotations.OutputPort
                
        @FunctionUnit(
            inputPorts = #[
                «unit.streams.map[leftPort].filter(typeof(OwnPort)).distinct[port.name].join(',\n')[generateInputPort]»
            ],
            outputPorts = #[
                «unit.streams.map[rightPort].filter(typeof(OwnPort)).distinct[port.name].join(',\n')[generateOutputPort]»
            ]
        )
        class «unit.name» {
            «val streamsWithOwnRightPort = unit.streams.filter[rightPort instanceof OwnPort].toList»
            «streamsWithOwnRightPort.distinct[(rightPort as OwnPort)?.port.name].
                map[leftPort.generateFunctionUnitInstantiation].join»
            
            new() {
                bind();
            }
            
            private def bind() {
                «streamsWithOwnRightPort.map[generateForwardingFromLeftFUs].join»
            }
            
            «val streamsWithOwnLeftPort = unit.streams.filter[leftPort instanceof OwnPort].toList»
            «streamsWithOwnLeftPort.map[leftPort as OwnPort].distinct[port.name].
                map[generateInputPortImplementations(streamsWithOwnLeftPort)].join»
        }
    '''
    
    def generateInputPortImplementations(OwnPort ownPort, Iterable<Stream> allStreamsWithOwnPortOnLeftSide) '''
        override process«ownPort.port.name.toFirstUpper»(«inferredPortTypes.get(ownPort.fullQualifiedName)» msg) {
            «allStreamsWithOwnPortOnLeftSide.
                filter[(leftPort as OwnPort)?.port.name == ownPort.port.name].
                map[rightPort.generateInputForwarding].join»
        }
    '''
    
    def Object generateInputForwarding(Port port) '''
        «port.varName».«port.inputPortName»(msg)
    '''

    def inputPortName(Port port) {
        if (port.port != null) // explicit port specification
            return port.port.name 
        else // implicit port specification
            switch port {
                OwnPort: '!recursive_weaving_from_own_port_back_to_own_port_not_supported_yet!'
                ForeignPort: (port.functionUnit.streams.filter[leftPort instanceof OwnPort].head as OwnPort).port.name // there must be only one take the first one // TODO optimize by making a map of own output ports per FU
                ExternalReferencePort: port.externalInputPortName
            }
    }

    /** Determines the external output port name from the OutputPort active annotation */
    def externalInputPortName(ExternalReferencePort port) {
        (port.inputPortAnnotation.elementValuePairs.findFirst[it.element.identifier == InputPort.name + '.name()']?.value as XStringLiteral).value
    }

    
    def generateFunctionUnitInstantiation(Port port) '''
        val «port.varName» = new «port.containingFunctionUnitFQN»
    '''
    
    def varName(Port port) {
        switch port {
            OwnPort: '!recursive_weaving_from_ownport_back_to_own_port_not_supported_yet!' 
            ExternalReferencePort: port.type.identifier.replace('.', '_')
            ForeignPort: port.functionUnit.fullyQualifiedName.toString('_')
        }
    } 

    def containingFunctionUnitFQN(Port port) {
        switch port {
            OwnPort: '!recursive_weaving_from_ownport_back_to_own_port_not_supported_yet!' 
            ExternalReferencePort: port.type.identifier
            ForeignPort: port.functionUnit.fullyQualifiedName
        }
    }
    
    /** generates code for stream specifications flowing from a foreign or external port to an own port, like tupper:out -> :output */
    def generateForwardingFromLeftFUs(Stream stream) '''
        «stream.leftPort.varName».«stream.leftPort.outputPortName» -> [msg | «(stream.rightPort as OwnPort)?.port?.name».forward(msg)]
    '''
    
    def outputPortName(Port port) {
        if (port.port != null) // explicit port specification
            return port.port.name 
        else // implicit port specification
            switch port {
                OwnPort: '!recursive_weaving_from_own_port_back_to_own_port_not_supported_yet!'
                ForeignPort: (port.functionUnit.streams.filter[rightPort instanceof OwnPort].head as OwnPort).port.name // there must be only one take the first one // TODO optimize by making a map of own output ports per FU
                ExternalReferencePort: port.externalOutputPortName
            }
    }
    
    /** Determines the external output port name from the OutputPort active annotation */
    def externalOutputPortName(ExternalReferencePort port) {
        (port.outputPortAnnotation.elementValuePairs.findFirst[it.element.identifier == OutputPort.name + '.name()']?.value as XStringLiteral).value
    }
    
    /** Remove all duplicates in a list according to given list element selector, turning it into a list of unique values */
    def static <T> distinct(Iterable<? extends T> values, Function1<? super T, ? extends Object> selector) {
        values.groupBy(selector).toPairs.map[value.head]
    }

    /** transforms a map into a list of pairs */
    def static <K, V> toPairs(Map<K, V> map) {
        map.entrySet.map[it.key -> it.value].toList
    }
    
    def generateInputPort(OwnPort input) {
        var portTypeName = inferredPortTypes.get(input.fullQualifiedName)
        if (portTypeName == null || portTypeName.length == 0)
            portTypeName = '''<unknow type for "«input.fullQualifiedName»"'''
        '''
            @InputPort(name="«input.port.name»", type=«portTypeName»)
        '''
    }
    
    def generateOutputPort(OwnPort output) '''
        @OutputPort(name="«output.port.name»", type=«inferredPortTypes.get(output.fullQualifiedName)»)
    '''
    
}