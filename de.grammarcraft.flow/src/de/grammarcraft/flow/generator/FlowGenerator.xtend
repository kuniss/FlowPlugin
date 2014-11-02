package de.grammarcraft.flow.generator

import com.google.inject.Inject
import de.grammarcraft.flow.flow.ExternalReferencePort
import de.grammarcraft.flow.flow.ForeignPort
import de.grammarcraft.flow.flow.FunctionUnit
import de.grammarcraft.flow.flow.OwnPort
import de.grammarcraft.flow.flow.Port
import de.grammarcraft.flow.flow.Stream
import de.grammarcraft.flow.inferrer.PortTypeInferrer
import de.grammarcraft.xtend.flow.annotations.InputPort
import de.grammarcraft.xtend.flow.annotations.OutputPort
import java.util.ArrayList
import java.util.Map
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.common.types.JvmCustomAnnotationValue
import org.eclipse.xtext.common.types.JvmDeclaredType
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.xtext.xbase.XListLiteral
import org.eclipse.xtext.xbase.XStringLiteral
import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotation
import org.eclipse.xtext.xbase.lib.Functions.Function1

import static extension org.eclipse.xtext.EcoreUtil2.*

class QualifiedPortNameProvider {
    @Inject extension IQualifiedNameProvider

    def String fullyQualifiedPortName(Port port) {
        switch port {
            OwnPort: {
                val fu = port.getContainerOfType(FunctionUnit)
                '''«fu.fullyQualifiedName».«port.port?.name»'''
            } 
            ExternalReferencePort: '''«port.type.identifier».«port.port?.name»'''
            ForeignPort: '''«port.functionUnit.fullyQualifiedName».«port.port?.name»'''
        }
    }
    
    
    
}


class FlowGenerator implements IGenerator {
    @Inject extension IQualifiedNameProvider
    @Inject extension QualifiedPortNameProvider qpnp
    
    var Map<String, String> inferredPortTypes 

    override doGenerate(Resource resource, IFileSystemAccess fsa) {
        val streams = resource.allContents.filter(typeof(Stream)).toList
        val ports = resource.allContents.filter(typeof(Port)).toList
        val aliasRefs = resource.allContents.filter(typeof(FunctionUnit)).filter[aliasType!=null].toList

        val portTypeInferrer = new PortTypeInferrer(streams, ports, aliasRefs, qpnp)
        inferredPortTypes = portTypeInferrer.inferredPortTyps()
        
        resource.allContents.toIterable.filter(typeof(FunctionUnit)).filter[aliasType==null].forEach[
            fsa.generateFile('''«fullyQualifiedName.skipLast(1).segments.join('/')»/«name».xtend''', generate(inferredPortTypes))
        ]
        
        fsa.generateFile(resource.normalizedURI.lastSegment + ".log", '''
            // own ports
            «resource.allContents.toIterable.filter(typeof(OwnPort)).distinct[fullyQualifiedPortName]
                .map['''own port "«fullyQualifiedPortName»"'''].join('\n')»
            // foreign ports
            «resource.allContents.toIterable.filter(typeof(ForeignPort)).distinct[fullyQualifiedPortName]
                .map['''foreign port "«fullyQualifiedPortName»"'''].join('\n')»
            // external ports
            «resource.allContents.toIterable.filter(typeof(ExternalReferencePort)).distinct[fullyQualifiedPortName]
                .map['''external port "«fullyQualifiedPortName»"'''].join('\n')»
«««            // port types directly inferred from stream connection types
«««            «portTypesFromStreams.entrySet.map['''port «key»: «value?.qualifiedName»'''].join('\n')»
«««            // port types directly inferred from external references
«««            «portTypesFromExternalRefs.entrySet.map['''port «key»: «value»'''].join('\n')»
            // inferred port types
            «inferredPortTypes.entrySet.map['''inferred «key»: «value»'''].join('\n')»
        ''')

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
    
    private def CharSequence generate(FunctionUnit unit, Map<String, String> inferredPortTypes) '''
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
            «val externaLAndForeignPorts = unit.streams.fold(new ArrayList<Port>)[list, stream | list.addBothPortsOf(stream)].filter[!(it instanceof OwnPort)].distinct[varName].toList»
            «externaLAndForeignPorts.map[generateFunctionUnitVarInstantiation].join»
            new() {
                bind();
            }
            
            private def bind() {
                «val streamsWithOwnRightPort = unit.streams.filter[rightPort instanceof OwnPort].toList»
                «streamsWithOwnRightPort.map[generateForwardingFromLeftFUs].join»
            }
            
            «val streamsWithOwnLeftPort = unit.streams.filter[leftPort instanceof OwnPort].toList»
            «streamsWithOwnLeftPort.map[leftPort as OwnPort].distinct[port.name].
                map[generateInputPortImplementations(streamsWithOwnLeftPort)].join»
        }
    '''
    
    private static def addBothPortsOf(ArrayList<Port> ports, Stream stream) {
        ports.add(stream.leftPort)
        ports.add(stream.rightPort)
        return ports
    }
    
    private def generateInputPortImplementations(OwnPort ownPort, Iterable<Stream> allStreamsWithOwnPortOnLeftSide) '''
        override process«ownPort.port.name.toFirstUpper»(«inferredPortTypes.get(ownPort.fullyQualifiedPortName)» msg) {
            «allStreamsWithOwnPortOnLeftSide.
                filter[(leftPort as OwnPort)?.port.name == ownPort.port.name].
                map[rightPort.generateInputForwarding].join»
        }
    '''
    
    private def Object generateInputForwarding(Port port) '''
        «port.varName».«port.inputPortName»(msg)
    '''

    private def inputPortName(Port port) {
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
    private def externalInputPortName(ExternalReferencePort port) {
        (port.inputPortAnnotation.elementValuePairs.findFirst[it.element.identifier == InputPort.name + '.name()']?.value as XStringLiteral).value
    }
    
    private def generateFunctionUnitVarInstantiation(Port port) '''
        val «port.varName» = new «port.containingFunctionUnitFQN»
    '''
    
    private def varName(Port port) {
        switch port {
            OwnPort: '!recursive_weaving_from_ownport_back_to_own_port_not_supported_yet!' 
            ExternalReferencePort: port.type.identifier.replace('.', '_')
            ForeignPort: port.functionUnit.fullyQualifiedName.toString('_')
        }
    } 

    private def containingFunctionUnitFQN(Port port) {
        switch port {
            OwnPort: '!recursive_weaving_from_ownport_back_to_own_port_not_supported_yet!' 
            ExternalReferencePort: port.type.identifier
            ForeignPort: port.functionUnit.fullyQualifiedName
        }
    }
    
    /** generates code for stream specifications flowing from a foreign or external port to an own port, like tupper:out -> :output */
    private def generateForwardingFromLeftFUs(Stream stream) '''
        «stream.leftPort.varName».«stream.leftPort.outputPortName» -> [msg | «(stream.rightPort as OwnPort)?.port?.name».forward(msg)]
    '''
    
    private def outputPortName(Port port) {
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
    private def externalOutputPortName(ExternalReferencePort port) {
        (port.outputPortAnnotation.elementValuePairs.findFirst[it.element.identifier == OutputPort.name + '.name()']?.value as XStringLiteral).value
    }
    
    /** Remove all duplicates in a list according to given list element selector, turning it into a list of unique values */
    private def static <T> distinct(Iterable<? extends T> values, Function1<? super T, ? extends Object> selector) {
        values.groupBy(selector).toPairs.map[value.head]
    }

    /** transforms a map into a list of pairs */
    private def static <K, V> toPairs(Map<K, V> map) {
        map.entrySet.map[it.key -> it.value].toList
    }
    
    private def generateInputPort(OwnPort input) {
        var portTypeName = inferredPortTypes.get(input.fullyQualifiedPortName)
        if (portTypeName == null || portTypeName.length == 0)
            portTypeName = '''<unknow type for "«input.fullyQualifiedPortName»"'''
        '''
            @InputPort(name="«input.port.name»", type=«portTypeName»)
        '''
    }
    
    private def generateOutputPort(OwnPort output) '''
        @OutputPort(name="«output.port.name»", type=«inferredPortTypes.get(output.fullyQualifiedPortName)»)
    '''
    
}