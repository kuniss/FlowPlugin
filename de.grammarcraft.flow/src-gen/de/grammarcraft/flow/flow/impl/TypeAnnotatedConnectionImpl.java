/**
 */
package de.grammarcraft.flow.flow.impl;

import de.grammarcraft.flow.flow.FlowPackage;
import de.grammarcraft.flow.flow.TypeAnnotatedConnection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.xtext.common.types.JvmTypeReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Annotated Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.grammarcraft.flow.flow.impl.TypeAnnotatedConnectionImpl#getMsgType <em>Msg Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeAnnotatedConnectionImpl extends ConnectionImpl implements TypeAnnotatedConnection
{
  /**
   * The cached value of the '{@link #getMsgType() <em>Msg Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMsgType()
   * @generated
   * @ordered
   */
  protected JvmTypeReference msgType;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TypeAnnotatedConnectionImpl()
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
    return FlowPackage.Literals.TYPE_ANNOTATED_CONNECTION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JvmTypeReference getMsgType()
  {
    return msgType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMsgType(JvmTypeReference newMsgType, NotificationChain msgs)
  {
    JvmTypeReference oldMsgType = msgType;
    msgType = newMsgType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FlowPackage.TYPE_ANNOTATED_CONNECTION__MSG_TYPE, oldMsgType, newMsgType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMsgType(JvmTypeReference newMsgType)
  {
    if (newMsgType != msgType)
    {
      NotificationChain msgs = null;
      if (msgType != null)
        msgs = ((InternalEObject)msgType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FlowPackage.TYPE_ANNOTATED_CONNECTION__MSG_TYPE, null, msgs);
      if (newMsgType != null)
        msgs = ((InternalEObject)newMsgType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FlowPackage.TYPE_ANNOTATED_CONNECTION__MSG_TYPE, null, msgs);
      msgs = basicSetMsgType(newMsgType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FlowPackage.TYPE_ANNOTATED_CONNECTION__MSG_TYPE, newMsgType, newMsgType));
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
      case FlowPackage.TYPE_ANNOTATED_CONNECTION__MSG_TYPE:
        return basicSetMsgType(null, msgs);
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
      case FlowPackage.TYPE_ANNOTATED_CONNECTION__MSG_TYPE:
        return getMsgType();
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
      case FlowPackage.TYPE_ANNOTATED_CONNECTION__MSG_TYPE:
        setMsgType((JvmTypeReference)newValue);
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
      case FlowPackage.TYPE_ANNOTATED_CONNECTION__MSG_TYPE:
        setMsgType((JvmTypeReference)null);
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
      case FlowPackage.TYPE_ANNOTATED_CONNECTION__MSG_TYPE:
        return msgType != null;
    }
    return super.eIsSet(featureID);
  }

} //TypeAnnotatedConnectionImpl
