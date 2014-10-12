/**
 */
package de.grammarcraft.flow.flow;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.grammarcraft.flow.flow.FlowFactory
 * @model kind="package"
 * @generated
 */
public interface FlowPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "flow";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.grammarcraft.de/flow/Flow";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "flow";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  FlowPackage eINSTANCE = de.grammarcraft.flow.flow.impl.FlowPackageImpl.init();

  /**
   * The meta object id for the '{@link de.grammarcraft.flow.flow.impl.ModelImpl <em>Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.grammarcraft.flow.flow.impl.ModelImpl
   * @see de.grammarcraft.flow.flow.impl.FlowPackageImpl#getModel()
   * @generated
   */
  int MODEL = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__NAME = 0;

  /**
   * The feature id for the '<em><b>Imports</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__IMPORTS = 1;

  /**
   * The feature id for the '<em><b>Function Units</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__FUNCTION_UNITS = 2;

  /**
   * The number of structural features of the '<em>Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link de.grammarcraft.flow.flow.impl.FunctionUnitImpl <em>Function Unit</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.grammarcraft.flow.flow.impl.FunctionUnitImpl
   * @see de.grammarcraft.flow.flow.impl.FlowPackageImpl#getFunctionUnit()
   * @generated
   */
  int FUNCTION_UNIT = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_UNIT__NAME = 0;

  /**
   * The feature id for the '<em><b>Streams</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_UNIT__STREAMS = 1;

  /**
   * The feature id for the '<em><b>Alias Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_UNIT__ALIAS_TYPE = 2;

  /**
   * The number of structural features of the '<em>Function Unit</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_UNIT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link de.grammarcraft.flow.flow.impl.StreamImpl <em>Stream</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.grammarcraft.flow.flow.impl.StreamImpl
   * @see de.grammarcraft.flow.flow.impl.FlowPackageImpl#getStream()
   * @generated
   */
  int STREAM = 2;

  /**
   * The feature id for the '<em><b>Left Port</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STREAM__LEFT_PORT = 0;

  /**
   * The feature id for the '<em><b>Connection</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STREAM__CONNECTION = 1;

  /**
   * The feature id for the '<em><b>Right Port</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STREAM__RIGHT_PORT = 2;

  /**
   * The number of structural features of the '<em>Stream</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STREAM_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link de.grammarcraft.flow.flow.impl.PortImpl <em>Port</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.grammarcraft.flow.flow.impl.PortImpl
   * @see de.grammarcraft.flow.flow.impl.FlowPackageImpl#getPort()
   * @generated
   */
  int PORT = 3;

  /**
   * The feature id for the '<em><b>Port</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT__PORT = 0;

  /**
   * The number of structural features of the '<em>Port</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link de.grammarcraft.flow.flow.impl.OwnPortImpl <em>Own Port</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.grammarcraft.flow.flow.impl.OwnPortImpl
   * @see de.grammarcraft.flow.flow.impl.FlowPackageImpl#getOwnPort()
   * @generated
   */
  int OWN_PORT = 4;

  /**
   * The feature id for the '<em><b>Port</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OWN_PORT__PORT = PORT__PORT;

  /**
   * The number of structural features of the '<em>Own Port</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OWN_PORT_FEATURE_COUNT = PORT_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link de.grammarcraft.flow.flow.impl.ForeignPortImpl <em>Foreign Port</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.grammarcraft.flow.flow.impl.ForeignPortImpl
   * @see de.grammarcraft.flow.flow.impl.FlowPackageImpl#getForeignPort()
   * @generated
   */
  int FOREIGN_PORT = 5;

  /**
   * The feature id for the '<em><b>Port</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOREIGN_PORT__PORT = PORT__PORT;

  /**
   * The feature id for the '<em><b>Function Unit</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOREIGN_PORT__FUNCTION_UNIT = PORT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Foreign Port</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOREIGN_PORT_FEATURE_COUNT = PORT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link de.grammarcraft.flow.flow.impl.ExternalReferencePortImpl <em>External Reference Port</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.grammarcraft.flow.flow.impl.ExternalReferencePortImpl
   * @see de.grammarcraft.flow.flow.impl.FlowPackageImpl#getExternalReferencePort()
   * @generated
   */
  int EXTERNAL_REFERENCE_PORT = 6;

  /**
   * The feature id for the '<em><b>Port</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTERNAL_REFERENCE_PORT__PORT = PORT__PORT;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTERNAL_REFERENCE_PORT__TYPE = PORT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>External Reference Port</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTERNAL_REFERENCE_PORT_FEATURE_COUNT = PORT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link de.grammarcraft.flow.flow.impl.NamedPortImpl <em>Named Port</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.grammarcraft.flow.flow.impl.NamedPortImpl
   * @see de.grammarcraft.flow.flow.impl.FlowPackageImpl#getNamedPort()
   * @generated
   */
  int NAMED_PORT = 7;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_PORT__NAME = 0;

  /**
   * The number of structural features of the '<em>Named Port</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_PORT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link de.grammarcraft.flow.flow.impl.ConnectionImpl <em>Connection</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.grammarcraft.flow.flow.impl.ConnectionImpl
   * @see de.grammarcraft.flow.flow.impl.FlowPackageImpl#getConnection()
   * @generated
   */
  int CONNECTION = 8;

  /**
   * The number of structural features of the '<em>Connection</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONNECTION_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link de.grammarcraft.flow.flow.impl.TypeLessConnectionImpl <em>Type Less Connection</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.grammarcraft.flow.flow.impl.TypeLessConnectionImpl
   * @see de.grammarcraft.flow.flow.impl.FlowPackageImpl#getTypeLessConnection()
   * @generated
   */
  int TYPE_LESS_CONNECTION = 9;

  /**
   * The number of structural features of the '<em>Type Less Connection</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_LESS_CONNECTION_FEATURE_COUNT = CONNECTION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link de.grammarcraft.flow.flow.impl.TypeAnnotatedConnectionImpl <em>Type Annotated Connection</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.grammarcraft.flow.flow.impl.TypeAnnotatedConnectionImpl
   * @see de.grammarcraft.flow.flow.impl.FlowPackageImpl#getTypeAnnotatedConnection()
   * @generated
   */
  int TYPE_ANNOTATED_CONNECTION = 10;

  /**
   * The feature id for the '<em><b>Msg Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_ANNOTATED_CONNECTION__MSG_TYPE = CONNECTION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Type Annotated Connection</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_ANNOTATED_CONNECTION_FEATURE_COUNT = CONNECTION_FEATURE_COUNT + 1;


  /**
   * Returns the meta object for class '{@link de.grammarcraft.flow.flow.Model <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model</em>'.
   * @see de.grammarcraft.flow.flow.Model
   * @generated
   */
  EClass getModel();

  /**
   * Returns the meta object for the attribute '{@link de.grammarcraft.flow.flow.Model#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see de.grammarcraft.flow.flow.Model#getName()
   * @see #getModel()
   * @generated
   */
  EAttribute getModel_Name();

  /**
   * Returns the meta object for the containment reference '{@link de.grammarcraft.flow.flow.Model#getImports <em>Imports</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Imports</em>'.
   * @see de.grammarcraft.flow.flow.Model#getImports()
   * @see #getModel()
   * @generated
   */
  EReference getModel_Imports();

  /**
   * Returns the meta object for the containment reference list '{@link de.grammarcraft.flow.flow.Model#getFunctionUnits <em>Function Units</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Function Units</em>'.
   * @see de.grammarcraft.flow.flow.Model#getFunctionUnits()
   * @see #getModel()
   * @generated
   */
  EReference getModel_FunctionUnits();

  /**
   * Returns the meta object for class '{@link de.grammarcraft.flow.flow.FunctionUnit <em>Function Unit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Function Unit</em>'.
   * @see de.grammarcraft.flow.flow.FunctionUnit
   * @generated
   */
  EClass getFunctionUnit();

  /**
   * Returns the meta object for the attribute '{@link de.grammarcraft.flow.flow.FunctionUnit#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see de.grammarcraft.flow.flow.FunctionUnit#getName()
   * @see #getFunctionUnit()
   * @generated
   */
  EAttribute getFunctionUnit_Name();

  /**
   * Returns the meta object for the containment reference list '{@link de.grammarcraft.flow.flow.FunctionUnit#getStreams <em>Streams</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Streams</em>'.
   * @see de.grammarcraft.flow.flow.FunctionUnit#getStreams()
   * @see #getFunctionUnit()
   * @generated
   */
  EReference getFunctionUnit_Streams();

  /**
   * Returns the meta object for the containment reference '{@link de.grammarcraft.flow.flow.FunctionUnit#getAliasType <em>Alias Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Alias Type</em>'.
   * @see de.grammarcraft.flow.flow.FunctionUnit#getAliasType()
   * @see #getFunctionUnit()
   * @generated
   */
  EReference getFunctionUnit_AliasType();

  /**
   * Returns the meta object for class '{@link de.grammarcraft.flow.flow.Stream <em>Stream</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Stream</em>'.
   * @see de.grammarcraft.flow.flow.Stream
   * @generated
   */
  EClass getStream();

  /**
   * Returns the meta object for the containment reference '{@link de.grammarcraft.flow.flow.Stream#getLeftPort <em>Left Port</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left Port</em>'.
   * @see de.grammarcraft.flow.flow.Stream#getLeftPort()
   * @see #getStream()
   * @generated
   */
  EReference getStream_LeftPort();

  /**
   * Returns the meta object for the containment reference '{@link de.grammarcraft.flow.flow.Stream#getConnection <em>Connection</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Connection</em>'.
   * @see de.grammarcraft.flow.flow.Stream#getConnection()
   * @see #getStream()
   * @generated
   */
  EReference getStream_Connection();

  /**
   * Returns the meta object for the containment reference '{@link de.grammarcraft.flow.flow.Stream#getRightPort <em>Right Port</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right Port</em>'.
   * @see de.grammarcraft.flow.flow.Stream#getRightPort()
   * @see #getStream()
   * @generated
   */
  EReference getStream_RightPort();

  /**
   * Returns the meta object for class '{@link de.grammarcraft.flow.flow.Port <em>Port</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Port</em>'.
   * @see de.grammarcraft.flow.flow.Port
   * @generated
   */
  EClass getPort();

  /**
   * Returns the meta object for the containment reference '{@link de.grammarcraft.flow.flow.Port#getPort <em>Port</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Port</em>'.
   * @see de.grammarcraft.flow.flow.Port#getPort()
   * @see #getPort()
   * @generated
   */
  EReference getPort_Port();

  /**
   * Returns the meta object for class '{@link de.grammarcraft.flow.flow.OwnPort <em>Own Port</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Own Port</em>'.
   * @see de.grammarcraft.flow.flow.OwnPort
   * @generated
   */
  EClass getOwnPort();

  /**
   * Returns the meta object for class '{@link de.grammarcraft.flow.flow.ForeignPort <em>Foreign Port</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Foreign Port</em>'.
   * @see de.grammarcraft.flow.flow.ForeignPort
   * @generated
   */
  EClass getForeignPort();

  /**
   * Returns the meta object for the reference '{@link de.grammarcraft.flow.flow.ForeignPort#getFunctionUnit <em>Function Unit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Function Unit</em>'.
   * @see de.grammarcraft.flow.flow.ForeignPort#getFunctionUnit()
   * @see #getForeignPort()
   * @generated
   */
  EReference getForeignPort_FunctionUnit();

  /**
   * Returns the meta object for class '{@link de.grammarcraft.flow.flow.ExternalReferencePort <em>External Reference Port</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>External Reference Port</em>'.
   * @see de.grammarcraft.flow.flow.ExternalReferencePort
   * @generated
   */
  EClass getExternalReferencePort();

  /**
   * Returns the meta object for the containment reference '{@link de.grammarcraft.flow.flow.ExternalReferencePort#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see de.grammarcraft.flow.flow.ExternalReferencePort#getType()
   * @see #getExternalReferencePort()
   * @generated
   */
  EReference getExternalReferencePort_Type();

  /**
   * Returns the meta object for class '{@link de.grammarcraft.flow.flow.NamedPort <em>Named Port</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Named Port</em>'.
   * @see de.grammarcraft.flow.flow.NamedPort
   * @generated
   */
  EClass getNamedPort();

  /**
   * Returns the meta object for the attribute '{@link de.grammarcraft.flow.flow.NamedPort#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see de.grammarcraft.flow.flow.NamedPort#getName()
   * @see #getNamedPort()
   * @generated
   */
  EAttribute getNamedPort_Name();

  /**
   * Returns the meta object for class '{@link de.grammarcraft.flow.flow.Connection <em>Connection</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Connection</em>'.
   * @see de.grammarcraft.flow.flow.Connection
   * @generated
   */
  EClass getConnection();

  /**
   * Returns the meta object for class '{@link de.grammarcraft.flow.flow.TypeLessConnection <em>Type Less Connection</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Type Less Connection</em>'.
   * @see de.grammarcraft.flow.flow.TypeLessConnection
   * @generated
   */
  EClass getTypeLessConnection();

  /**
   * Returns the meta object for class '{@link de.grammarcraft.flow.flow.TypeAnnotatedConnection <em>Type Annotated Connection</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Type Annotated Connection</em>'.
   * @see de.grammarcraft.flow.flow.TypeAnnotatedConnection
   * @generated
   */
  EClass getTypeAnnotatedConnection();

  /**
   * Returns the meta object for the containment reference '{@link de.grammarcraft.flow.flow.TypeAnnotatedConnection#getMsgType <em>Msg Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Msg Type</em>'.
   * @see de.grammarcraft.flow.flow.TypeAnnotatedConnection#getMsgType()
   * @see #getTypeAnnotatedConnection()
   * @generated
   */
  EReference getTypeAnnotatedConnection_MsgType();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  FlowFactory getFlowFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link de.grammarcraft.flow.flow.impl.ModelImpl <em>Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.grammarcraft.flow.flow.impl.ModelImpl
     * @see de.grammarcraft.flow.flow.impl.FlowPackageImpl#getModel()
     * @generated
     */
    EClass MODEL = eINSTANCE.getModel();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MODEL__NAME = eINSTANCE.getModel_Name();

    /**
     * The meta object literal for the '<em><b>Imports</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL__IMPORTS = eINSTANCE.getModel_Imports();

    /**
     * The meta object literal for the '<em><b>Function Units</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL__FUNCTION_UNITS = eINSTANCE.getModel_FunctionUnits();

    /**
     * The meta object literal for the '{@link de.grammarcraft.flow.flow.impl.FunctionUnitImpl <em>Function Unit</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.grammarcraft.flow.flow.impl.FunctionUnitImpl
     * @see de.grammarcraft.flow.flow.impl.FlowPackageImpl#getFunctionUnit()
     * @generated
     */
    EClass FUNCTION_UNIT = eINSTANCE.getFunctionUnit();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FUNCTION_UNIT__NAME = eINSTANCE.getFunctionUnit_Name();

    /**
     * The meta object literal for the '<em><b>Streams</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUNCTION_UNIT__STREAMS = eINSTANCE.getFunctionUnit_Streams();

    /**
     * The meta object literal for the '<em><b>Alias Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUNCTION_UNIT__ALIAS_TYPE = eINSTANCE.getFunctionUnit_AliasType();

    /**
     * The meta object literal for the '{@link de.grammarcraft.flow.flow.impl.StreamImpl <em>Stream</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.grammarcraft.flow.flow.impl.StreamImpl
     * @see de.grammarcraft.flow.flow.impl.FlowPackageImpl#getStream()
     * @generated
     */
    EClass STREAM = eINSTANCE.getStream();

    /**
     * The meta object literal for the '<em><b>Left Port</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STREAM__LEFT_PORT = eINSTANCE.getStream_LeftPort();

    /**
     * The meta object literal for the '<em><b>Connection</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STREAM__CONNECTION = eINSTANCE.getStream_Connection();

    /**
     * The meta object literal for the '<em><b>Right Port</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STREAM__RIGHT_PORT = eINSTANCE.getStream_RightPort();

    /**
     * The meta object literal for the '{@link de.grammarcraft.flow.flow.impl.PortImpl <em>Port</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.grammarcraft.flow.flow.impl.PortImpl
     * @see de.grammarcraft.flow.flow.impl.FlowPackageImpl#getPort()
     * @generated
     */
    EClass PORT = eINSTANCE.getPort();

    /**
     * The meta object literal for the '<em><b>Port</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PORT__PORT = eINSTANCE.getPort_Port();

    /**
     * The meta object literal for the '{@link de.grammarcraft.flow.flow.impl.OwnPortImpl <em>Own Port</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.grammarcraft.flow.flow.impl.OwnPortImpl
     * @see de.grammarcraft.flow.flow.impl.FlowPackageImpl#getOwnPort()
     * @generated
     */
    EClass OWN_PORT = eINSTANCE.getOwnPort();

    /**
     * The meta object literal for the '{@link de.grammarcraft.flow.flow.impl.ForeignPortImpl <em>Foreign Port</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.grammarcraft.flow.flow.impl.ForeignPortImpl
     * @see de.grammarcraft.flow.flow.impl.FlowPackageImpl#getForeignPort()
     * @generated
     */
    EClass FOREIGN_PORT = eINSTANCE.getForeignPort();

    /**
     * The meta object literal for the '<em><b>Function Unit</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOREIGN_PORT__FUNCTION_UNIT = eINSTANCE.getForeignPort_FunctionUnit();

    /**
     * The meta object literal for the '{@link de.grammarcraft.flow.flow.impl.ExternalReferencePortImpl <em>External Reference Port</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.grammarcraft.flow.flow.impl.ExternalReferencePortImpl
     * @see de.grammarcraft.flow.flow.impl.FlowPackageImpl#getExternalReferencePort()
     * @generated
     */
    EClass EXTERNAL_REFERENCE_PORT = eINSTANCE.getExternalReferencePort();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXTERNAL_REFERENCE_PORT__TYPE = eINSTANCE.getExternalReferencePort_Type();

    /**
     * The meta object literal for the '{@link de.grammarcraft.flow.flow.impl.NamedPortImpl <em>Named Port</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.grammarcraft.flow.flow.impl.NamedPortImpl
     * @see de.grammarcraft.flow.flow.impl.FlowPackageImpl#getNamedPort()
     * @generated
     */
    EClass NAMED_PORT = eINSTANCE.getNamedPort();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NAMED_PORT__NAME = eINSTANCE.getNamedPort_Name();

    /**
     * The meta object literal for the '{@link de.grammarcraft.flow.flow.impl.ConnectionImpl <em>Connection</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.grammarcraft.flow.flow.impl.ConnectionImpl
     * @see de.grammarcraft.flow.flow.impl.FlowPackageImpl#getConnection()
     * @generated
     */
    EClass CONNECTION = eINSTANCE.getConnection();

    /**
     * The meta object literal for the '{@link de.grammarcraft.flow.flow.impl.TypeLessConnectionImpl <em>Type Less Connection</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.grammarcraft.flow.flow.impl.TypeLessConnectionImpl
     * @see de.grammarcraft.flow.flow.impl.FlowPackageImpl#getTypeLessConnection()
     * @generated
     */
    EClass TYPE_LESS_CONNECTION = eINSTANCE.getTypeLessConnection();

    /**
     * The meta object literal for the '{@link de.grammarcraft.flow.flow.impl.TypeAnnotatedConnectionImpl <em>Type Annotated Connection</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.grammarcraft.flow.flow.impl.TypeAnnotatedConnectionImpl
     * @see de.grammarcraft.flow.flow.impl.FlowPackageImpl#getTypeAnnotatedConnection()
     * @generated
     */
    EClass TYPE_ANNOTATED_CONNECTION = eINSTANCE.getTypeAnnotatedConnection();

    /**
     * The meta object literal for the '<em><b>Msg Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TYPE_ANNOTATED_CONNECTION__MSG_TYPE = eINSTANCE.getTypeAnnotatedConnection_MsgType();

  }

} //FlowPackage
