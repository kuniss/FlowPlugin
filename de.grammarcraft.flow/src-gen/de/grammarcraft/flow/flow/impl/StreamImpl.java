/**
 */
package de.grammarcraft.flow.flow.impl;

import de.grammarcraft.flow.flow.Connection;
import de.grammarcraft.flow.flow.FlowPackage;
import de.grammarcraft.flow.flow.Port;
import de.grammarcraft.flow.flow.Stream;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stream</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.grammarcraft.flow.flow.impl.StreamImpl#getLeftPort <em>Left Port</em>}</li>
 *   <li>{@link de.grammarcraft.flow.flow.impl.StreamImpl#getConnection <em>Connection</em>}</li>
 *   <li>{@link de.grammarcraft.flow.flow.impl.StreamImpl#getRightPort <em>Right Port</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StreamImpl extends MinimalEObjectImpl.Container implements Stream
{
  /**
   * The cached value of the '{@link #getLeftPort() <em>Left Port</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLeftPort()
   * @generated
   * @ordered
   */
  protected Port leftPort;

  /**
   * The cached value of the '{@link #getConnection() <em>Connection</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConnection()
   * @generated
   * @ordered
   */
  protected Connection connection;

  /**
   * The cached value of the '{@link #getRightPort() <em>Right Port</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRightPort()
   * @generated
   * @ordered
   */
  protected Port rightPort;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected StreamImpl()
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
    return FlowPackage.Literals.STREAM;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Port getLeftPort()
  {
    return leftPort;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLeftPort(Port newLeftPort, NotificationChain msgs)
  {
    Port oldLeftPort = leftPort;
    leftPort = newLeftPort;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FlowPackage.STREAM__LEFT_PORT, oldLeftPort, newLeftPort);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLeftPort(Port newLeftPort)
  {
    if (newLeftPort != leftPort)
    {
      NotificationChain msgs = null;
      if (leftPort != null)
        msgs = ((InternalEObject)leftPort).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FlowPackage.STREAM__LEFT_PORT, null, msgs);
      if (newLeftPort != null)
        msgs = ((InternalEObject)newLeftPort).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FlowPackage.STREAM__LEFT_PORT, null, msgs);
      msgs = basicSetLeftPort(newLeftPort, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FlowPackage.STREAM__LEFT_PORT, newLeftPort, newLeftPort));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Connection getConnection()
  {
    return connection;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetConnection(Connection newConnection, NotificationChain msgs)
  {
    Connection oldConnection = connection;
    connection = newConnection;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FlowPackage.STREAM__CONNECTION, oldConnection, newConnection);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConnection(Connection newConnection)
  {
    if (newConnection != connection)
    {
      NotificationChain msgs = null;
      if (connection != null)
        msgs = ((InternalEObject)connection).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FlowPackage.STREAM__CONNECTION, null, msgs);
      if (newConnection != null)
        msgs = ((InternalEObject)newConnection).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FlowPackage.STREAM__CONNECTION, null, msgs);
      msgs = basicSetConnection(newConnection, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FlowPackage.STREAM__CONNECTION, newConnection, newConnection));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Port getRightPort()
  {
    return rightPort;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRightPort(Port newRightPort, NotificationChain msgs)
  {
    Port oldRightPort = rightPort;
    rightPort = newRightPort;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FlowPackage.STREAM__RIGHT_PORT, oldRightPort, newRightPort);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRightPort(Port newRightPort)
  {
    if (newRightPort != rightPort)
    {
      NotificationChain msgs = null;
      if (rightPort != null)
        msgs = ((InternalEObject)rightPort).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FlowPackage.STREAM__RIGHT_PORT, null, msgs);
      if (newRightPort != null)
        msgs = ((InternalEObject)newRightPort).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FlowPackage.STREAM__RIGHT_PORT, null, msgs);
      msgs = basicSetRightPort(newRightPort, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FlowPackage.STREAM__RIGHT_PORT, newRightPort, newRightPort));
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
      case FlowPackage.STREAM__LEFT_PORT:
        return basicSetLeftPort(null, msgs);
      case FlowPackage.STREAM__CONNECTION:
        return basicSetConnection(null, msgs);
      case FlowPackage.STREAM__RIGHT_PORT:
        return basicSetRightPort(null, msgs);
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
      case FlowPackage.STREAM__LEFT_PORT:
        return getLeftPort();
      case FlowPackage.STREAM__CONNECTION:
        return getConnection();
      case FlowPackage.STREAM__RIGHT_PORT:
        return getRightPort();
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
      case FlowPackage.STREAM__LEFT_PORT:
        setLeftPort((Port)newValue);
        return;
      case FlowPackage.STREAM__CONNECTION:
        setConnection((Connection)newValue);
        return;
      case FlowPackage.STREAM__RIGHT_PORT:
        setRightPort((Port)newValue);
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
      case FlowPackage.STREAM__LEFT_PORT:
        setLeftPort((Port)null);
        return;
      case FlowPackage.STREAM__CONNECTION:
        setConnection((Connection)null);
        return;
      case FlowPackage.STREAM__RIGHT_PORT:
        setRightPort((Port)null);
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
      case FlowPackage.STREAM__LEFT_PORT:
        return leftPort != null;
      case FlowPackage.STREAM__CONNECTION:
        return connection != null;
      case FlowPackage.STREAM__RIGHT_PORT:
        return rightPort != null;
    }
    return super.eIsSet(featureID);
  }

} //StreamImpl
