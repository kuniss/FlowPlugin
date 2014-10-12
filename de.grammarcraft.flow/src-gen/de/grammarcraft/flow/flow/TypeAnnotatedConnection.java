/**
 */
package de.grammarcraft.flow.flow;

import org.eclipse.xtext.common.types.JvmTypeReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Annotated Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.grammarcraft.flow.flow.TypeAnnotatedConnection#getMsgType <em>Msg Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.grammarcraft.flow.flow.FlowPackage#getTypeAnnotatedConnection()
 * @model
 * @generated
 */
public interface TypeAnnotatedConnection extends Connection
{
  /**
   * Returns the value of the '<em><b>Msg Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Msg Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Msg Type</em>' containment reference.
   * @see #setMsgType(JvmTypeReference)
   * @see de.grammarcraft.flow.flow.FlowPackage#getTypeAnnotatedConnection_MsgType()
   * @model containment="true"
   * @generated
   */
  JvmTypeReference getMsgType();

  /**
   * Sets the value of the '{@link de.grammarcraft.flow.flow.TypeAnnotatedConnection#getMsgType <em>Msg Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Msg Type</em>' containment reference.
   * @see #getMsgType()
   * @generated
   */
  void setMsgType(JvmTypeReference value);

} // TypeAnnotatedConnection
