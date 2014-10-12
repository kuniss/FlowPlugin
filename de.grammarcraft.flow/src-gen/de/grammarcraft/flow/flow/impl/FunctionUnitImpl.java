/**
 */
package de.grammarcraft.flow.flow.impl;

import de.grammarcraft.flow.flow.FlowPackage;
import de.grammarcraft.flow.flow.FunctionUnit;
import de.grammarcraft.flow.flow.Stream;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xtext.common.types.JvmTypeReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Function Unit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.grammarcraft.flow.flow.impl.FunctionUnitImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.grammarcraft.flow.flow.impl.FunctionUnitImpl#getStreams <em>Streams</em>}</li>
 *   <li>{@link de.grammarcraft.flow.flow.impl.FunctionUnitImpl#getAliasType <em>Alias Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FunctionUnitImpl extends MinimalEObjectImpl.Container implements FunctionUnit
{
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getStreams() <em>Streams</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStreams()
   * @generated
   * @ordered
   */
  protected EList<Stream> streams;

  /**
   * The cached value of the '{@link #getAliasType() <em>Alias Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAliasType()
   * @generated
   * @ordered
   */
  protected JvmTypeReference aliasType;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FunctionUnitImpl()
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
    return FlowPackage.Literals.FUNCTION_UNIT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FlowPackage.FUNCTION_UNIT__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Stream> getStreams()
  {
    if (streams == null)
    {
      streams = new EObjectContainmentEList<Stream>(Stream.class, this, FlowPackage.FUNCTION_UNIT__STREAMS);
    }
    return streams;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JvmTypeReference getAliasType()
  {
    return aliasType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAliasType(JvmTypeReference newAliasType, NotificationChain msgs)
  {
    JvmTypeReference oldAliasType = aliasType;
    aliasType = newAliasType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FlowPackage.FUNCTION_UNIT__ALIAS_TYPE, oldAliasType, newAliasType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAliasType(JvmTypeReference newAliasType)
  {
    if (newAliasType != aliasType)
    {
      NotificationChain msgs = null;
      if (aliasType != null)
        msgs = ((InternalEObject)aliasType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FlowPackage.FUNCTION_UNIT__ALIAS_TYPE, null, msgs);
      if (newAliasType != null)
        msgs = ((InternalEObject)newAliasType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FlowPackage.FUNCTION_UNIT__ALIAS_TYPE, null, msgs);
      msgs = basicSetAliasType(newAliasType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FlowPackage.FUNCTION_UNIT__ALIAS_TYPE, newAliasType, newAliasType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case FlowPackage.FUNCTION_UNIT__STREAMS:
        return ((InternalEList<?>)getStreams()).basicRemove(otherEnd, msgs);
      case FlowPackage.FUNCTION_UNIT__ALIAS_TYPE:
        return basicSetAliasType(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case FlowPackage.FUNCTION_UNIT__NAME:
        return getName();
      case FlowPackage.FUNCTION_UNIT__STREAMS:
        return getStreams();
      case FlowPackage.FUNCTION_UNIT__ALIAS_TYPE:
        return getAliasType();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case FlowPackage.FUNCTION_UNIT__NAME:
        setName((String)newValue);
        return;
      case FlowPackage.FUNCTION_UNIT__STREAMS:
        getStreams().clear();
        getStreams().addAll((Collection<? extends Stream>)newValue);
        return;
      case FlowPackage.FUNCTION_UNIT__ALIAS_TYPE:
        setAliasType((JvmTypeReference)newValue);
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
      case FlowPackage.FUNCTION_UNIT__NAME:
        setName(NAME_EDEFAULT);
        return;
      case FlowPackage.FUNCTION_UNIT__STREAMS:
        getStreams().clear();
        return;
      case FlowPackage.FUNCTION_UNIT__ALIAS_TYPE:
        setAliasType((JvmTypeReference)null);
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
      case FlowPackage.FUNCTION_UNIT__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case FlowPackage.FUNCTION_UNIT__STREAMS:
        return streams != null && !streams.isEmpty();
      case FlowPackage.FUNCTION_UNIT__ALIAS_TYPE:
        return aliasType != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //FunctionUnitImpl
