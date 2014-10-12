/**
 */
package de.grammarcraft.flow.flow;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stream</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.grammarcraft.flow.flow.Stream#getLeftPort <em>Left Port</em>}</li>
 *   <li>{@link de.grammarcraft.flow.flow.Stream#getConnection <em>Connection</em>}</li>
 *   <li>{@link de.grammarcraft.flow.flow.Stream#getRightPort <em>Right Port</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.grammarcraft.flow.flow.FlowPackage#getStream()
 * @model
 * @generated
 */
public interface Stream extends EObject
{
  /**
   * Returns the value of the '<em><b>Left Port</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Left Port</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Left Port</em>' containment reference.
   * @see #setLeftPort(Port)
   * @see de.grammarcraft.flow.flow.FlowPackage#getStream_LeftPort()
   * @model containment="true"
   * @generated
   */
  Port getLeftPort();

  /**
   * Sets the value of the '{@link de.grammarcraft.flow.flow.Stream#getLeftPort <em>Left Port</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Left Port</em>' containment reference.
   * @see #getLeftPort()
   * @generated
   */
  void setLeftPort(Port value);

  /**
   * Returns the value of the '<em><b>Connection</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Connection</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Connection</em>' containment reference.
   * @see #setConnection(Connection)
   * @see de.grammarcraft.flow.flow.FlowPackage#getStream_Connection()
   * @model containment="true"
   * @generated
   */
  Connection getConnection();

  /**
   * Sets the value of the '{@link de.grammarcraft.flow.flow.Stream#getConnection <em>Connection</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Connection</em>' containment reference.
   * @see #getConnection()
   * @generated
   */
  void setConnection(Connection value);

  /**
   * Returns the value of the '<em><b>Right Port</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Right Port</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Right Port</em>' containment reference.
   * @see #setRightPort(Port)
   * @see de.grammarcraft.flow.flow.FlowPackage#getStream_RightPort()
   * @model containment="true"
   * @generated
   */
  Port getRightPort();

  /**
   * Sets the value of the '{@link de.grammarcraft.flow.flow.Stream#getRightPort <em>Right Port</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Right Port</em>' containment reference.
   * @see #getRightPort()
   * @generated
   */
  void setRightPort(Port value);

} // Stream
