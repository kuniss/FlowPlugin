/**
 */
package de.grammarcraft.flow.flow.util;

import de.grammarcraft.flow.flow.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.grammarcraft.flow.flow.FlowPackage
 * @generated
 */
public class FlowAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static FlowPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FlowAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = FlowPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FlowSwitch<Adapter> modelSwitch =
    new FlowSwitch<Adapter>()
    {
      @Override
      public Adapter caseModel(Model object)
      {
        return createModelAdapter();
      }
      @Override
      public Adapter caseFunctionUnit(FunctionUnit object)
      {
        return createFunctionUnitAdapter();
      }
      @Override
      public Adapter caseStream(Stream object)
      {
        return createStreamAdapter();
      }
      @Override
      public Adapter casePort(Port object)
      {
        return createPortAdapter();
      }
      @Override
      public Adapter caseOwnPort(OwnPort object)
      {
        return createOwnPortAdapter();
      }
      @Override
      public Adapter caseForeignPort(ForeignPort object)
      {
        return createForeignPortAdapter();
      }
      @Override
      public Adapter caseExternalReferencePort(ExternalReferencePort object)
      {
        return createExternalReferencePortAdapter();
      }
      @Override
      public Adapter caseNamedPort(NamedPort object)
      {
        return createNamedPortAdapter();
      }
      @Override
      public Adapter caseConnection(Connection object)
      {
        return createConnectionAdapter();
      }
      @Override
      public Adapter caseTypeLessConnection(TypeLessConnection object)
      {
        return createTypeLessConnectionAdapter();
      }
      @Override
      public Adapter caseTypeAnnotatedConnection(TypeAnnotatedConnection object)
      {
        return createTypeAnnotatedConnectionAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link de.grammarcraft.flow.flow.Model <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.grammarcraft.flow.flow.Model
   * @generated
   */
  public Adapter createModelAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.grammarcraft.flow.flow.FunctionUnit <em>Function Unit</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.grammarcraft.flow.flow.FunctionUnit
   * @generated
   */
  public Adapter createFunctionUnitAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.grammarcraft.flow.flow.Stream <em>Stream</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.grammarcraft.flow.flow.Stream
   * @generated
   */
  public Adapter createStreamAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.grammarcraft.flow.flow.Port <em>Port</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.grammarcraft.flow.flow.Port
   * @generated
   */
  public Adapter createPortAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.grammarcraft.flow.flow.OwnPort <em>Own Port</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.grammarcraft.flow.flow.OwnPort
   * @generated
   */
  public Adapter createOwnPortAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.grammarcraft.flow.flow.ForeignPort <em>Foreign Port</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.grammarcraft.flow.flow.ForeignPort
   * @generated
   */
  public Adapter createForeignPortAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.grammarcraft.flow.flow.ExternalReferencePort <em>External Reference Port</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.grammarcraft.flow.flow.ExternalReferencePort
   * @generated
   */
  public Adapter createExternalReferencePortAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.grammarcraft.flow.flow.NamedPort <em>Named Port</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.grammarcraft.flow.flow.NamedPort
   * @generated
   */
  public Adapter createNamedPortAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.grammarcraft.flow.flow.Connection <em>Connection</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.grammarcraft.flow.flow.Connection
   * @generated
   */
  public Adapter createConnectionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.grammarcraft.flow.flow.TypeLessConnection <em>Type Less Connection</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.grammarcraft.flow.flow.TypeLessConnection
   * @generated
   */
  public Adapter createTypeLessConnectionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.grammarcraft.flow.flow.TypeAnnotatedConnection <em>Type Annotated Connection</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.grammarcraft.flow.flow.TypeAnnotatedConnection
   * @generated
   */
  public Adapter createTypeAnnotatedConnectionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //FlowAdapterFactory
