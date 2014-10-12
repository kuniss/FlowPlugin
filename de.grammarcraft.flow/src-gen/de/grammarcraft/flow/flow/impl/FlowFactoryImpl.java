/**
 */
package de.grammarcraft.flow.flow.impl;

import de.grammarcraft.flow.flow.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FlowFactoryImpl extends EFactoryImpl implements FlowFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static FlowFactory init()
  {
    try
    {
      FlowFactory theFlowFactory = (FlowFactory)EPackage.Registry.INSTANCE.getEFactory(FlowPackage.eNS_URI);
      if (theFlowFactory != null)
      {
        return theFlowFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new FlowFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FlowFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case FlowPackage.MODEL: return createModel();
      case FlowPackage.FUNCTION_UNIT: return createFunctionUnit();
      case FlowPackage.STREAM: return createStream();
      case FlowPackage.PORT: return createPort();
      case FlowPackage.OWN_PORT: return createOwnPort();
      case FlowPackage.FOREIGN_PORT: return createForeignPort();
      case FlowPackage.EXTERNAL_REFERENCE_PORT: return createExternalReferencePort();
      case FlowPackage.NAMED_PORT: return createNamedPort();
      case FlowPackage.CONNECTION: return createConnection();
      case FlowPackage.TYPE_LESS_CONNECTION: return createTypeLessConnection();
      case FlowPackage.TYPE_ANNOTATED_CONNECTION: return createTypeAnnotatedConnection();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Model createModel()
  {
    ModelImpl model = new ModelImpl();
    return model;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FunctionUnit createFunctionUnit()
  {
    FunctionUnitImpl functionUnit = new FunctionUnitImpl();
    return functionUnit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Stream createStream()
  {
    StreamImpl stream = new StreamImpl();
    return stream;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Port createPort()
  {
    PortImpl port = new PortImpl();
    return port;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OwnPort createOwnPort()
  {
    OwnPortImpl ownPort = new OwnPortImpl();
    return ownPort;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ForeignPort createForeignPort()
  {
    ForeignPortImpl foreignPort = new ForeignPortImpl();
    return foreignPort;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExternalReferencePort createExternalReferencePort()
  {
    ExternalReferencePortImpl externalReferencePort = new ExternalReferencePortImpl();
    return externalReferencePort;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamedPort createNamedPort()
  {
    NamedPortImpl namedPort = new NamedPortImpl();
    return namedPort;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Connection createConnection()
  {
    ConnectionImpl connection = new ConnectionImpl();
    return connection;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TypeLessConnection createTypeLessConnection()
  {
    TypeLessConnectionImpl typeLessConnection = new TypeLessConnectionImpl();
    return typeLessConnection;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TypeAnnotatedConnection createTypeAnnotatedConnection()
  {
    TypeAnnotatedConnectionImpl typeAnnotatedConnection = new TypeAnnotatedConnectionImpl();
    return typeAnnotatedConnection;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FlowPackage getFlowPackage()
  {
    return (FlowPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static FlowPackage getPackage()
  {
    return FlowPackage.eINSTANCE;
  }

} //FlowFactoryImpl
