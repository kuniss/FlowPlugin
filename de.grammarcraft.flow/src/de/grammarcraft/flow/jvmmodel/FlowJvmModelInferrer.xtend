package de.grammarcraft.flow.jvmmodel

import com.google.inject.Inject
import de.grammarcraft.flow.flow.Model
import org.eclipse.xtext.common.types.JvmDeclaredType
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor.IPostIndexingInitializing
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder

/**
 * <p>Infers a JVM model from the source model.</p> 
 *
 * <p>The JVM model should contain all elements that would appear in the Java code 
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>     
 */
class FlowJvmModelInferrer extends AbstractModelInferrer {

    /**
     * convenience API to build and initialize JVM types and their members.
     */
	@Inject extension JvmTypesBuilder

    @Inject extension IQualifiedNameProvider

	/**
	 * The dispatch method {@code infer} is called for each instance of the
	 * given element's type that is contained in a resource.
	 * 
	 * @param element
	 *            the model to create one or more
	 *            {@link JvmDeclaredType declared
	 *            types} from.
	 * @param acceptor
	 *            each created
	 *            {@link JvmDeclaredType type}
	 *            without a container should be passed to the acceptor in order
	 *            get attached to the current resource. The acceptor's
	 *            {@link IJvmDeclaredTypeAcceptor#accept(org.eclipse.xtext.common.types.JvmDeclaredType)
	 *            accept(..)} method takes the constructed empty type for the
	 *            pre-indexing phase. This one is further initialized in the
	 *            indexing phase using the closure you pass to the returned
	 *            {@link IPostIndexingInitializing#initializeLater(org.eclipse.xtext.xbase.lib.Procedures.Procedure1)
	 *            initializeLater(..)}.
	 * @param isPreIndexingPhase
	 *            whether the method is called in a pre-indexing phase, i.e.
	 *            when the global index is not yet fully updated. You must not
	 *            rely on linking using the index if isPreIndexingPhase is
	 *            <code>true</code>.
	 */
   	def dispatch void infer(Model element, IJvmDeclaredTypeAcceptor acceptor, boolean isPreIndexingPhase) {
   		// Here you explain how your model is mapped to Java elements, by writing the actual translation code.
   		
   		// An implementation for the initial hello world example could look like this:
//   		acceptor.accept(element.toClass("my.company.greeting.MyGreetings"))
//   			.initializeLater([
//   				for (greeting : element.greetings) {
//   					members += greeting.toMethod("hello" + greeting.name, greeting.newTypeRef(typeof(String))) [
//   						body = [
//   							append('''return "Hello «greeting.name»";''')
//   						]
//   					]
//   				}
//   			])
   	}
   	
//   	def dispatch void infer(IntegrationUnit integrationUnit, IJvmDeclaredTypeAcceptor acceptor, boolean isPreIndexingPhase) 
//   	{
//        acceptor.accept(integrationUnit.toClass(integrationUnit.fullyQualifiedName))
//            .initializeLater([
//                val thisMembers = members
//                integrationUnit.referencedUnits.forEach[
//                    thisMembers += integrationUnit.toField(it, )
//                ]
//            ])
//   	}
//    
//    def referencedUnits(IntegrationUnit unit) {
//        unit.streams.fold(new ArrayList<String>)[unitList, s| 
//            if (s.leftPort instanceof ForeignPort) {
//                val foreignPort = s.leftPort as ForeignPort
//                unitList.add(foreignPort.functionUnit.fullyQualifiedName.toString)
//            }
//            if (s.rightPort instanceof ForeignPort) {
//                val foreignPort = s.rightPort as ForeignPort
//                unitList.add(foreignPort.functionUnit.fullyQualifiedName.toString)
//            }
//            unitList
//        ]
//    }
   	
   	
}

