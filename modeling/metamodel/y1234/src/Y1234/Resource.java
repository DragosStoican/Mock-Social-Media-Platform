/**
 */
package Y1234;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Y1234.Resource#getName <em>Name</em>}</li>
 *   <li>{@link Y1234.Resource#getPath <em>Path</em>}</li>
 *   <li>{@link Y1234.Resource#getType <em>Type</em>}</li>
 *   <li>{@link Y1234.Resource#getQueryParameters <em>Query Parameters</em>}</li>
 *   <li>{@link Y1234.Resource#getRequestType <em>Request Type</em>}</li>
 * </ul>
 *
 * @see Y1234.Y1234Package#getResource()
 * @model
 * @generated
 */
public interface Resource extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see Y1234.Y1234Package#getResource_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link Y1234.Resource#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path</em>' attribute.
	 * @see #setPath(String)
	 * @see Y1234.Y1234Package#getResource_Path()
	 * @model
	 * @generated
	 */
	String getPath();

	/**
	 * Sets the value of the '{@link Y1234.Resource#getPath <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see Y1234.Y1234Package#getResource_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link Y1234.Resource#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Query Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link Y1234.JavaVar}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Query Parameters</em>' containment reference list.
	 * @see Y1234.Y1234Package#getResource_QueryParameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<JavaVar> getQueryParameters();

	/**
	 * Returns the value of the '<em><b>Request Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Request Type</em>' attribute.
	 * @see #setRequestType(String)
	 * @see Y1234.Y1234Package#getResource_RequestType()
	 * @model
	 * @generated
	 */
	String getRequestType();

	/**
	 * Sets the value of the '{@link Y1234.Resource#getRequestType <em>Request Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Request Type</em>' attribute.
	 * @see #getRequestType()
	 * @generated
	 */
	void setRequestType(String value);

} // Resource
