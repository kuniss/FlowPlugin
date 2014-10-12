/**
 */
package de.grammarcraft.flow.flow;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.grammarcraft.flow.flow.FlowPackage
 * @generated
 */
public interface FlowFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  FlowFactory eINSTANCE = de.grammarcraft.flow.flow.impl.FlowFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Model</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Model</em>'.
   * @generated
   */
  Model createModel();

  /**
   * Returns a new object of class '<em>Function Unit</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Function Unit</em>'.
   * @generated
   */
  FunctionUnit createFunctionUnit();

  /**
   * Returns a new object of class '<em>Stream</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Stream</em>'.
   * @generated
   */
  Stream createStream();

  /**
   * Returns a new object of class '<em>Port</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Port</em>'.
   * @generated
   */
  Port createPort();

  /**
   * Returns a new object of class '<em>Own Port</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Own Port</em>'.
   * @generated
   */
  OwnPort createOwnPort();

  /**
   * Returns a new object of class '<em>Foreign Port</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Foreign Port</em>'.
   * @generated
   */
  ForeignPort createForeignPort();

  /**
   * Returns a new object of class '<em>External Reference Port</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>External Reference Port</em>'.
   * @generated
   */
  ExternalReferencePort createExternalReferencePort();

  /**
   * Returns a new object of class '<em>Named Port</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Named Port</em>'.
   * @generated
   */
  NamedPort createNamedPort();

  /**
   * Returns a new object of class '<em>Connection</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Connection</em>'.
   * @generated
   */
  Connection createConnection();

  /**
   * Returns a new object of class '<em>Type Less Connection</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Type Less Connection</em>'.
   * @generated
   */
  TypeLessConnection createTypeLessConnection();

  /**
   * Returns a new object of class '<em>Type Annotated Connection</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Type Annotated Connection</em>'.
   * @generated
   */
  TypeAnnotatedConnection createTypeAnnotatedConnection();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  FlowPackage getFlowPackage();

} //FlowFactory
