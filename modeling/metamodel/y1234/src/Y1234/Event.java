/**
 */
package Y1234;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Y1234.Event#getName <em>Name</em>}</li>
 *   <li>{@link Y1234.Event#getKey <em>Key</em>}</li>
 *   <li>{@link Y1234.Event#getValue <em>Value</em>}</li>
 *   <li>{@link Y1234.Event#getTopics <em>Topics</em>}</li>
 * </ul>
 *
 * @see Y1234.Y1234Package#getEvent()
 * @model
 * @generated
 */
public interface Event extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see Y1234.Y1234Package#getEvent_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link Y1234.Event#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key</em>' containment reference.
	 * @see #setKey(JavaVar)
	 * @see Y1234.Y1234Package#getEvent_Key()
	 * @model containment="true"
	 * @generated
	 */
	JavaVar getKey();

	/**
	 * Sets the value of the '{@link Y1234.Event#getKey <em>Key</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key</em>' containment reference.
	 * @see #getKey()
	 * @generated
	 */
	void setKey(JavaVar value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' containment reference.
	 * @see #setValue(JavaVar)
	 * @see Y1234.Y1234Package#getEvent_Value()
	 * @model containment="true"
	 * @generated
	 */
	JavaVar getValue();

	/**
	 * Sets the value of the '{@link Y1234.Event#getValue <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' containment reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(JavaVar value);

	/**
	 * Returns the value of the '<em><b>Topics</b></em>' reference list.
	 * The list contents are of type {@link Y1234.EventStream}.
	 * It is bidirectional and its opposite is '{@link Y1234.EventStream#getEventType <em>Event Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Topics</em>' reference list.
	 * @see Y1234.Y1234Package#getEvent_Topics()
	 * @see Y1234.EventStream#getEventType
	 * @model opposite="eventType"
	 * @generated
	 */
	EList<EventStream> getTopics();

} // Event
