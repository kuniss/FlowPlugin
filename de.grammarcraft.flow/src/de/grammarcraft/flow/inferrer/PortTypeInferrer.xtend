package de.grammarcraft.flow.inferrer

import de.grammarcraft.flow.flow.ExternalReferencePort
import de.grammarcraft.flow.flow.FunctionUnit
import de.grammarcraft.flow.flow.OwnPort
import de.grammarcraft.flow.flow.Port
import de.grammarcraft.flow.flow.Stream
import de.grammarcraft.flow.flow.TypeAnnotatedConnection
import de.grammarcraft.flow.generator.QualifiedPortNameProvider
import de.grammarcraft.xtend.flow.annotations.InputPort
import de.grammarcraft.xtend.flow.annotations.OutputPort
import java.util.HashMap
import java.util.HashSet
import java.util.List
import java.util.Map
import java.util.Set
import org.eclipse.xtext.common.types.JvmCustomAnnotationValue
import org.eclipse.xtext.common.types.JvmDeclaredType
import org.eclipse.xtext.common.types.JvmGenericType
import org.eclipse.xtext.common.types.JvmType
import org.eclipse.xtext.common.types.JvmTypeReference
import org.eclipse.xtext.xbase.XFeatureCall
import org.eclipse.xtext.xbase.XListLiteral
import org.eclipse.xtext.xbase.XStringLiteral
import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotation

class PortTypeInferrer {
    
    val Object singleComputation = new Object

    val List<Stream> streams
    val List<Port> ports
    val List<FunctionUnit> aliasRefs
    val extension QualifiedPortNameProvider qpnp
    HashMap<String, String> inferredPortTypes
    
    new (List<Stream> streams, List<Port> ports, List<FunctionUnit> aliasRefs, QualifiedPortNameProvider qpnp) {
        this.streams = streams
        this.ports = ports
        this.aliasRefs = aliasRefs
        this.qpnp = qpnp
    }
    
    def inferredPortTyps() {
        if (this.inferredPortTypes == null) {
            synchronized(singleComputation) {
                if (this.inferredPortTypes == null) {
                    val directConnections = initTransitiveClosure(streams, ports)
                    val transitiveClosure = computeTransitiveClosure(directConnections)
                    val portTypesFromStreams = computePortTypesFromStreams(streams)
                    val portTypesFromExternalRefs = computePortTypesFromExternalRefs(aliasRefs, streams)
                    this.inferredPortTypes = inferPortTypes(transitiveClosure, portTypesFromStreams, portTypesFromExternalRefs, ports.filter(typeof(OwnPort)))
                }
            }
        }
        return this.inferredPortTypes
    }
    
    private def inferPortTypes(Map<String, Set<String>> transitiveClosure, Map<String, JvmType> portTypesFromStreams, Map<String, String> portTypesFromExternalRefs, Iterable<OwnPort> ownPorts) {
        val inferredPortTypes = new HashMap<String, String>
        ownPorts.forEach[
            val portName = it.fullyQualifiedPortName
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
    
    
    private def initTransitiveClosure(List<Stream> streams, List<Port> ports) {
        val Map<String, Set<String>> directConnections = new HashMap;
        for (stream: streams) {
            val leftPortName =  stream.leftPort.fullyQualifiedPortName
            val rightPortName = stream.rightPort.fullyQualifiedPortName
            directConnections.addConnection(leftPortName, rightPortName)
        }
        // connections to itself
        for(port: ports) {
            val portName = port.fullyQualifiedPortName
            directConnections.addConnection(portName, portName)
        }
        return directConnections
    }
    
    private static def addConnection(Map<String, Set<String>> directConnections, String leftPortName, String rightPortName) {
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
    
    private static def computeTransitiveClosure(Map<String, Set<String>> directConnections) {
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
    
    private static def boolean hasConnection(Map<String, Set<String>> transitiveClosure, String leftPortName, String rightPortName) {
        transitiveClosure.get(leftPortName).contains(rightPortName)
    }
    
    private def Map<String, JvmType> computePortTypesFromStreams(Iterable<Stream> streams) {
        val portTypes = new HashMap<String, JvmType>
        streams.filter[connection instanceof TypeAnnotatedConnection].forEach[ 
            val portTypeRef = (connection as TypeAnnotatedConnection).msgType
            if (!(leftPort instanceof ExternalReferencePort))
                portTypes.put(leftPort.fullyQualifiedPortName, portTypeRef.type)
            if (!(rightPort instanceof ExternalReferencePort))
                portTypes.put(rightPort.fullyQualifiedPortName, portTypeRef.type)
        ]
        return portTypes
    }
           
    private def computePortTypesFromExternalsRefsInStreams(Iterable<Stream> streams) {
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
                portTypes.put(rightPort.fullyQualifiedPortName, portType)
        ]
            
        streams.filter[rightPort instanceof ExternalReferencePort].forEach[
            val portType = (rightPort as ExternalReferencePort).inputPortType
            if (!(leftPort instanceof ExternalReferencePort)) {
                portTypes.put(leftPort.fullyQualifiedPortName, portType)
            }
        ]
        return portTypes
    }
    
    private static def String inputPortType(ExternalReferencePort portSpec) {
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
    
    private static def inputPortAnnotation(ExternalReferencePort portSpec) {
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
    
    private static def String outputPortType(ExternalReferencePort portSpec) {
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
    
    private static def outputPortAnnotation(ExternalReferencePort portSpec) {
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
    
    private def computePortTypesFromExternalRefs(Iterable<FunctionUnit> units, Iterable<Stream> streams) {
        val portTypesFromExternalRefs = computePortTypesFromAliases(units)
        val portTypesFromStreams = computePortTypesFromExternalsRefsInStreams(streams)
        portTypesFromExternalRefs.putAll(portTypesFromStreams)
        return portTypesFromExternalRefs
    }
    
    private static def computePortTypesFromAliases(Iterable<FunctionUnit> aliasedFunctionUnits) {
        val Map<String, String> portTypes = new HashMap
        aliasedFunctionUnits.forEach[
            computePortTypesFromAlias(portTypes, name, aliasType)
        ]
        return portTypes
    }
    
    private static def computePortTypesFromAlias(Map<String, String> portTypes, String functionUnitName, JvmTypeReference functionUnitType) {
        if (functionUnitType.type instanceof JvmDeclaredType) {
            val declaredType = functionUnitType as JvmDeclaredType
            val inputPortAnnotations = declaredType.annotations.filter(typeof(InputPort))
            inputPortAnnotations.forEach[
                portTypes.put('''«functionUnitName».«name»''', type.name)
            ]
        }
    }
        
}
