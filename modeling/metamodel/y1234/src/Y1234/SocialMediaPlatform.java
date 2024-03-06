/**
 */
package Y1234;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Social Media Platform</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Y1234.SocialMediaPlatform#getName <em>Name</em>}</li>
 *   <li>{@link Y1234.SocialMediaPlatform#getMicroservices <em>Microservices</em>}</li>
 *   <li>{@link Y1234.SocialMediaPlatform#getTopics <em>Topics</em>}</li>
 *   <li>{@link Y1234.SocialMediaPlatform#getEvents <em>Events</em>}</li>
 * </ul>
 *
 * @see Y1234.Y1234Package#getSocialMediaPlatform()
 * @model
 * @generated
 */
public interface SocialMediaPlatform extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see Y1234.Y1234Package#getSocialMediaPlatform_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link Y1234.SocialMediaPlatform#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Microservices</b></em>' containment reference list.
	 * The list contents are of type {@link Y1234.Microservice}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Microservices</em>' containment reference list.
	 * @see Y1234.Y1234Package#getSocialMediaPlatform_Microservices()
	 * @model containment="true"
	 * @generated
	 */
	EList<Microservice> getMicroservices();

	/**
	 * Returns the value of the '<em><b>Topics</b></em>' containment reference list.
	 * The list contents are of type {@link Y1234.EventStream}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Topics</em>' containment reference list.
	 * @see Y1234.Y1234Package#getSocialMediaPlatform_Topics()
	 * @model containment="true"
	 * @generated
	 */
	EList<EventStream> getTopics();

	/**
	 * Returns the value of the '<em><b>Events</b></em>' containment reference list.
	 * The list contents are of type {@link Y1234.Event}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Events</em>' containment reference list.
	 * @see Y1234.Y1234Package#getSocialMediaPlatform_Events()
	 * @model containment="true"
	 * @generated
	 */
	EList<Event> getEvents();

} // SocialMediaPlatform
