/**
 */
package Y1234;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event Stream</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Y1234.EventStream#getName <em>Name</em>}</li>
 *   <li>{@link Y1234.EventStream#getEventType <em>Event Type</em>}</li>
 *   <li>{@link Y1234.EventStream#getPublishers <em>Publishers</em>}</li>
 *   <li>{@link Y1234.EventStream#getSubscribers <em>Subscribers</em>}</li>
 * </ul>
 *
 * @see Y1234.Y1234Package#getEventStream()
 * @model
 * @generated
 */
public interface EventStream extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see Y1234.Y1234Package#getEventStream_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link Y1234.EventStream#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Event Type</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link Y1234.Event#getTopics <em>Topics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event Type</em>' reference.
	 * @see #setEventType(Event)
	 * @see Y1234.Y1234Package#getEventStream_EventType()
	 * @see Y1234.Event#getTopics
	 * @model opposite="topics"
	 * @generated
	 */
	Event getEventType();

	/**
	 * Sets the value of the '{@link Y1234.EventStream#getEventType <em>Event Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event Type</em>' reference.
	 * @see #getEventType()
	 * @generated
	 */
	void setEventType(Event value);

	/**
	 * Returns the value of the '<em><b>Publishers</b></em>' reference list.
	 * The list contents are of type {@link Y1234.Publisher}.
	 * It is bidirectional and its opposite is '{@link Y1234.Publisher#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Publishers</em>' reference list.
	 * @see Y1234.Y1234Package#getEventStream_Publishers()
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
	 * @see Y1234.Y1234Package#getEventStream_Subscribers()
	 * @see Y1234.Subscriber#getFrom
	 * @model opposite="from"
	 * @generated
	 */
	EList<Subscriber> getSubscribers();

} // EventStream
