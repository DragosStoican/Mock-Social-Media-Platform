/**
 */
package Y1234;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Subscriber</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Y1234.Subscriber#getFrom <em>From</em>}</li>
 * </ul>
 *
 * @see Y1234.Y1234Package#getSubscriber()
 * @model abstract="true"
 * @generated
 */
public interface Subscriber extends EObject {
	/**
	 * Returns the value of the '<em><b>From</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link Y1234.EventStream#getSubscribers <em>Subscribers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' reference.
	 * @see #setFrom(EventStream)
	 * @see Y1234.Y1234Package#getSubscriber_From()
	 * @see Y1234.EventStream#getSubscribers
	 * @model opposite="subscribers"
	 * @generated
	 */
	EventStream getFrom();

	/**
	 * Sets the value of the '{@link Y1234.Subscriber#getFrom <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' reference.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(EventStream value);

} // Subscriber
