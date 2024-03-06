/**
 */
package Y1234.tests;

import Y1234.KafkaStream;
import Y1234.Y1234Factory;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Kafka Stream</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class KafkaStreamTest extends SubscriberTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(KafkaStreamTest.class);
	}

	/**
	 * Constructs a new Kafka Stream test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KafkaStreamTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Kafka Stream test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected KafkaStream getFixture() {
		return (KafkaStream)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(Y1234Factory.eINSTANCE.createKafkaStream());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //KafkaStreamTest
