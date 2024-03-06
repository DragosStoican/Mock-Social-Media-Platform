/**
 */
package Y1234.tests;

import Y1234.KafkaPublisher;
import Y1234.Y1234Factory;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Kafka Publisher</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class KafkaPublisherTest extends PublisherTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(KafkaPublisherTest.class);
	}

	/**
	 * Constructs a new Kafka Publisher test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KafkaPublisherTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Kafka Publisher test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected KafkaPublisher getFixture() {
		return (KafkaPublisher)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(Y1234Factory.eINSTANCE.createKafkaPublisher());
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

} //KafkaPublisherTest
