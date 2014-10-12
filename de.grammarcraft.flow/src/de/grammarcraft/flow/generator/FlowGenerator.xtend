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
import org.eclipse.xtext.common.types.JvmDeclaredType
import org.eclipse.xtext.common.types.JvmType
import org.eclipse.xtext.common.types.JvmTypeReference
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.xtext.naming.IQualifiedNameProvider

enum FlowDirection {
    IN, OUT
}

@Data class InferencedPort {
    val String name
    val JvmTypeReference type
}

class FlowGenerator implements IGenerator {
    @Inject extension IQualifiedNameProvider

    override doGenerate(Resource resource, IFileSystemAccess fsa) {
        val directConnections = initTransitiveClosure(resource);
        val transitiveClosure = computeTransitiveClosure(directConnections)
        
        val streams = resource.allContents.toIterable.filter(typeof(Stream))
        val  aliasRefs = resource.allContents.toIterable.filter(typeof(FunctionUnit)).filter[aliasType!=null]

        val portTypesFromStreams = computePortTypesFromStreams(streams)
        val portTypesFromExternalRefs = computePortTypesFromExternalRefs(aliasRefs, streams)

        val inferredPortTypes = inferPortTypes(transitiveClosure, portTypesFromStreams, portTypesFromExternalRefs, resource.allContents.toIterable.filter(typeof(OwnPort)))
        
        resource.allContents.toIterable.filter(typeof(FunctionUnit)).filter[aliasType==null].forEach[
            fsa.generateFile(name+".xtend", it.generate(inferredPortTypes))
        ]
        
    }
    
    def computePortTypesFromExternalRefs(Iterable<FunctionUnit> units, Iterable<Stream> streams) {
        val portTypesFromExternalRefs = computePortTypesFromAliases(units)
        val portTypesFromStreams = computePortTypesFromExternalsRefsInStreams(streams)
        portTypesFromExternalRefs.putAll(portTypesFromStreams)
        return portTypesFromExternalRefs
    }
    

    def inferPortTypes(Map<String, Set<String>> transitiveClosure, Map<String, JvmType> portTypesFromStreams, Map<String, Class<?>> portTypesFromExternalRefs, Iterable<OwnPort> ownPorts) {
        val inferredPortTypes = new HashMap<String, String>
        ownPorts.forEach[
            val portName = it.fullQualifiedName
            for(transitivePortName : transitiveClosure.get(portName))
            {
                if (portTypesFromStreams.containsKey(transitivePortName)) {
                    inferredPortTypes.put(portName, portTypesFromStreams.get(transitivePortName).identifier)
                }
                else if (portTypesFromExternalRefs.containsKey(transitivePortName)) {
                    inferredPortTypes.put(portName, portTypesFromExternalRefs.get(transitivePortName).name)
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
            ExternalReferencePort: '''«port.type».«port.port?.name»'''
            ForeignPort: '''«port.fullyQualifiedName»'''
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
            portTypes.put(leftPort.fullQualifiedName, portTypeRef.type)
            portTypes.put(rightPort.fullQualifiedName, portTypeRef.type)
        ]
        return portTypes
    }
           
    def computePortTypesFromExternalsRefsInStreams(Iterable<Stream> streams) {
        val portTypes = new HashMap<String, Class<?>> 
        streams.filter[leftPort instanceof ExternalReferencePort].forEach[
            val portType = (leftPort as ExternalReferencePort).inputPortType
            if (rightPort instanceof ExternalReferencePort) {
                val otherPortType = (rightPort as ExternalReferencePort).outputPortType
                if (portType != otherPortType)
                    // TODO log error
                    println('''left port «leftPort» of type «portType.name» mismatch right port «rightPort» of type «otherPortType.name»''')               
            }
            else
                portTypes.put(rightPort.fullQualifiedName, portType)
        ]
            
        streams.filter[rightPort instanceof ExternalReferencePort].forEach[
            val portType = (rightPort as ExternalReferencePort).outputPortType
            if (!(leftPort instanceof ExternalReferencePort)) {
                portTypes.put(leftPort.fullQualifiedName, portType)
            }
        ]
        return portTypes
    }
    
    def Class<?> inputPortType(ExternalReferencePort port) {
        if(port.type instanceof JvmDeclaredType) {
            val declaredType = port.type as JvmDeclaredType
            val portAnnotation = declaredType?.annotations.filter(typeof(InputPort)).head
            return portAnnotation?.type
        }
        return null
    }

    def Class<?> outputPortType(ExternalReferencePort port) {
        if(port.type instanceof JvmDeclaredType) {
            val declaredType = port.type as JvmDeclaredType
            val portAnnotation = declaredType?.annotations.filter(typeof(OutputPort)).head
            return portAnnotation?.type
        }
        return null
    }
    
    def computePortTypesFromAliases(Iterable<FunctionUnit> aliasedFunctionUnits) {
        val Map<String, Class<?>> portTypes = new HashMap
        aliasedFunctionUnits.forEach[
            computePortTypesFromAlias(portTypes, name, aliasType)
        ]
        return portTypes
    }
    
    def computePortTypesFromAlias(Map<String, Class<?>> portTypes, String functionUnitName, JvmTypeReference functionUnitType) {
        if (functionUnitType.type instanceof JvmDeclaredType) {
            val declaredType = functionUnitType as JvmDeclaredType
            val inputPortAnnotations = declaredType.annotations.filter(typeof(InputPort))
            inputPortAnnotations.forEach[
                portTypes.put('''«functionUnitName».«name»''', type)
            ]
        }
    }
        
    def CharSequence generate(FunctionUnit unit, HashMap<String, String> inferredPortTypes) '''
        package «unit.fullyQualifiedName.skipLast(1)»
        
        @FunctionUnit(
            inputPorts = #[
                «unit.streams.map[leftPort].filter(typeof(OwnPort)).join(", ")[generateInputPort(inferredPortTypes)]»
            ],
            outputPorts = #[
                «unit.streams.map[rightPort].filter(typeof(OwnPort)).join(", ")[generateOutputPort(inferredPortTypes)]»
            ]
        )
        class «unit.name» {
        }            
    '''
    
    def generateInputPort(OwnPort input, HashMap<String, String> inferredPortTypes) '''
        @InputPort(name="«input.port.name»", type=«inferredPortTypes.get(input.fullQualifiedName)»)
    '''
    
    def generateOutputPort(OwnPort output, HashMap<String, String> inferredPortTypes) '''
        @OutputPort(name="«output.port.name»", type=«inferredPortTypes.get(output.fullQualifiedName)»)
    '''
    
}