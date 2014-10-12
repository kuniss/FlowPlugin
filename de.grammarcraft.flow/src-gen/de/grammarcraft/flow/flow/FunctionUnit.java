/**
 */
package de.grammarcraft.flow.flow;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.common.types.JvmTypeReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.grammarcraft.flow.flow.FunctionUnit#getName <em>Name</em>}</li>
 *   <li>{@link de.grammarcraft.flow.flow.FunctionUnit#getStreams <em>Streams</em>}</li>
 *   <li>{@link de.grammarcraft.flow.flow.FunctionUnit#getAliasType <em>Alias Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.grammarcraft.flow.flow.FlowPackage#getFunctionUnit()
 * @model
 * @generated
 */
public interface FunctionUnit extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see de.grammarcraft.flow.flow.FlowPackage#getFunctionUnit_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link de.grammarcraft.flow.flow.FunctionUnit#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Streams</b></em>' containment reference list.
   * The list contents are of type {@link de.grammarcraft.flow.flow.Stream}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Streams</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Streams</em>' containment reference list.
   * @see de.grammarcraft.flow.flow.FlowPackage#getFunctionUnit_Streams()
   * @model containment="true"
   * @generated
   */
  EList<Stream> getStreams();

  /**
   * Returns the value of the '<em><b>Alias Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Alias Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Alias Type</em>' containment reference.
   * @see #setAliasType(JvmTypeReference)
   * @see de.grammarcraft.flow.flow.FlowPackage#getFunctionUnit_AliasType()
   * @model containment="true"
   * @generated
   */
  JvmTypeReference getAliasType();

  /**
   * Sets the value of the '{@link de.grammarcraft.flow.flow.FunctionUnit#getAliasType <em>Alias Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Alias Type</em>' containment reference.
   * @see #getAliasType()
   * @generated
   */
  void setAliasType(JvmTypeReference value);

} // FunctionUnit
