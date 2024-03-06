/**
 */
package Y1234;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Topic</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Y1234.Topic#getName <em>Name</em>}</li>
 *   <li>{@link Y1234.Topic#getEvent <em>Event</em>}</li>
 *   <li>{@link Y1234.Topic#getPublishers <em>Publishers</em>}</li>
 *   <li>{@link Y1234.Topic#getSubscribers <em>Subscribers</em>}</li>
 * </ul>
 *
 * @see Y1234.Y1234Package#getTopic()
 * @model
 * @generated
 */
public interface Topic extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see Y1234.Y1234Package#getTopic_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link Y1234.Topic#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event</em>' containment reference.
	 * @see #setEvent(Event)
	 * @see Y1234.Y1234Package#getTopic_Event()
	 * @model containment="true"
	 * @generated
	 */
	Event getEvent();

	/**
	 * Sets the value of the '{@link Y1234.Topic#getEvent <em>Event</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event</em>' containment reference.
	 * @see #getEvent()
	 * @generated
	 */
	void setEvent(Event value);

	/**
	 * Returns the value of the '<em><b>Publishers</b></em>' reference list.
	 * The list contents are of type {@link Y1234.Publisher}.
	 * It is bidirectional and its opposite is '{@link Y1234.Publisher#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Publishers</em>' reference list.
	 * @see Y1234.Y1234Package#getTopic_Publishers()
	 * @see Y1234.Publisher#getTo
	 * @model opposite="to"
	 * @generated
	 */
	EList<Publisher> getPublishers();

	/**
	 * Returns the value of the '<em><b>Subscribers</b></em>' reference list.
	 * The list contents are of type {@link Y1234.Subscriber}.
	 * It is bidirectional and its opposite is '{@link Y1234.Subscriber#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subscribers</em>' reference list.
	 * @see Y1234.Y1234Package#getTopic_Subscribers()
	 * @see Y1234.Subscriber#getFrom
	 * @model opposite="from"
	 * @generated
	 */
	EList<Subscriber> getSubscribers();

} // Topic
