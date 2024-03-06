/**
 */
package Y1234;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Kafka Subscriber</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Y1234.KafkaSubscriber#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see Y1234.Y1234Package#getKafkaSubscriber()
 * @model
 * @generated
 */
public interface KafkaSubscriber extends Subscriber {

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see Y1234.Y1234Package#getKafkaSubscriber_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link Y1234.KafkaSubscriber#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);
} // KafkaSubscriber
