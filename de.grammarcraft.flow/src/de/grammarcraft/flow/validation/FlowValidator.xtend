/*
 * generated by Xtext
 */
package de.grammarcraft.flow.validation

import de.grammarcraft.flow.flow.FlowPackage
import de.grammarcraft.flow.flow.Model
import org.eclipse.xtext.validation.Check
import de.grammarcraft.flow.jvmmodel.PortTypeInferrer
import java.util.HashMap
import de.grammarcraft.flow.flow.Stream
import de.grammarcraft.flow.flow.Port
import de.grammarcraft.flow.flow.FunctionUnit
import com.google.inject.Inject
import de.grammarcraft.flow.generator.QualifiedPortNameProvider
import de.grammarcraft.flow.flow.OwnPort

//import org.eclipse.xtext.validation.Check

/**
 * Custom validation rules. 
 *
 * see http://www.eclipse.org/Xtext/documentation.html#validation
 */
class FlowValidator extends AbstractFlowValidator {
    

//  public static val INVALID_NAME = 'invalidName'
//
//	@Check
//	def checkGreetingStartsWithCapital(Greeting greeting) {
//		if (!Character.isUpperCase(greeting.name.charAt(0))) {
//			warning('Name should start with a capital', 
//					MyDslPackage.Literals.GREETING__NAME,
//					INVALID_NAME)
//		}
//	}

    @Inject extension QualifiedPortNameProvider qpnp
    
    HashMap<String, String> inferredPortTypes
    
    @Check
    def inferTypesOnModelRoot(Model model) {
        val streams = model.eAllContents.filter(typeof(Stream)).toList
        val ports = model.eAllContents.filter(typeof(Port)).toList
        val aliasRefs = model.eAllContents.filter(typeof(FunctionUnit)).filter[aliasType!=null].toList
        val portTypeInferrer = new PortTypeInferrer(streams, ports, aliasRefs, qpnp)
        
        inferredPortTypes = portTypeInferrer.inferredPortTyps()
    
        info('''mode stats: streams: «streams.size», ports: «ports.size», aliasRefs: «aliasRefs.size» inferred port types: «inferredPortTypes.size»''', 
            FlowPackage.Literals.MODEL__NAME
        )
    }
    
    @Check
    def reportUnknownPortType(OwnPort port) {
        val portName = port.fullyQualifiedPortName
        val portTypeName = inferredPortTypes.get(portName)
        if (portTypeName == null || portTypeName.length == 0)
            warning('''type for port «portName» could not be inferred''', port.port, FlowPackage.Literals.NAMED_PORT__NAME)
    }

}
