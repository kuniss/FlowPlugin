/**
 */
package de.grammarcraft.flow.flow.impl;

import de.grammarcraft.flow.flow.FlowPackage;
import de.grammarcraft.flow.flow.ForeignPort;
import de.grammarcraft.flow.flow.FunctionUnit;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Foreign Port</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.grammarcraft.flow.flow.impl.ForeignPortImpl#getFunctionUnit <em>Function Unit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ForeignPortImpl extends PortImpl implements ForeignPort
{
  /**
   * The cached value of the '{@link #getFunctionUnit() <em>Function Unit</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFunctionUnit()
   * @generated
   * @ordered
   */
  protected FunctionUnit functionUnit;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ForeignPortImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return FlowPackage.Literals.FOREIGN_PORT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FunctionUnit getFunctionUnit()
  {
    if (functionUnit != null && functionUnit.eIsProxy())
    {
      InternalEObject oldFunctionUnit = (InternalEObject)functionUnit;
      functionUnit = (FunctionUnit)eResolveProxy(oldFunctionUnit);
      if (functionUnit != oldFunctionUnit)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FlowPackage.FOREIGN_PORT__FUNCTION_UNIT, oldFunctionUnit, functionUnit));
      }
    }
    return functionUnit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FunctionUnit basicGetFunctionUnit()
  {
    return functionUnit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFunctionUnit(FunctionUnit newFunctionUnit)
  {
    FunctionUnit oldFunctionUnit = functionUnit;
    functionUnit = newFunctionUnit;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FlowPackage.FOREIGN_PORT__FUNCTION_UNIT, oldFunctionUnit, functionUnit));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case FlowPackage.FOREIGN_PORT__FUNCTION_UNIT:
        if (resolve) return getFunctionUnit();
        return basicGetFunctionUnit();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case FlowPackage.FOREIGN_PORT__FUNCTION_UNIT:
        setFunctionUnit((FunctionUnit)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case FlowPackage.FOREIGN_PORT__FUNCTION_UNIT:
        setFunctionUnit((FunctionUnit)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case FlowPackage.FOREIGN_PORT__FUNCTION_UNIT:
        return functionUnit != null;
    }
    return super.eIsSet(featureID);
  }

} //ForeignPortImpl
