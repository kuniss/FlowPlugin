/**
 */
package de.grammarcraft.flow.flow;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Foreign Port</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.grammarcraft.flow.flow.ForeignPort#getFunctionUnit <em>Function Unit</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.grammarcraft.flow.flow.FlowPackage#getForeignPort()
 * @model
 * @generated
 */
public interface ForeignPort extends Port
{
  /**
   * Returns the value of the '<em><b>Function Unit</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Function Unit</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Function Unit</em>' reference.
   * @see #setFunctionUnit(FunctionUnit)
   * @see de.grammarcraft.flow.flow.FlowPackage#getForeignPort_FunctionUnit()
   * @model
   * @generated
   */
  FunctionUnit getFunctionUnit();

  /**
   * Sets the value of the '{@link de.grammarcraft.flow.flow.ForeignPort#getFunctionUnit <em>Function Unit</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Function Unit</em>' reference.
   * @see #getFunctionUnit()
   * @generated
   */
  void setFunctionUnit(FunctionUnit value);

} // ForeignPort
