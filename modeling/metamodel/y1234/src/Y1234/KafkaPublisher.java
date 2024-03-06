/**
 */
package Y1234;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Kafka Publisher</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Y1234.KafkaPublisher#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see Y1234.Y1234Package#getKafkaPublisher()
 * @model
 * @generated
 */
public interface KafkaPublisher extends Publisher {

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see Y1234.Y1234Package#getKafkaPublisher_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link Y1234.KafkaPublisher#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);
} // KafkaPublisher
