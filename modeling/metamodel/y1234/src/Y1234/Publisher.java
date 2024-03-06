/**
 */
package Y1234;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Publisher</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Y1234.Publisher#getTo <em>To</em>}</li>
 * </ul>
 *
 * @see Y1234.Y1234Package#getPublisher()
 * @model abstract="true"
 * @generated
 */
public interface Publisher extends EObject {
	/**
	 * Returns the value of the '<em><b>To</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link Y1234.EventStream#getPublishers <em>Publishers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' reference.
	 * @see #setTo(EventStream)
	 * @see Y1234.Y1234Package#getPublisher_To()
	 * @see Y1234.EventStream#getPublishers
	 * @model opposite="publishers"
	 * @generated
	 */
	EventStream getTo();

	/**
	 * Sets the value of the '{@link Y1234.Publisher#getTo <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' reference.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(EventStream value);

} // Publisher
