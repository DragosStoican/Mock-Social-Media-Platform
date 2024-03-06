/**
 */
package Y1234.tests;

import Y1234.KafkaSubscriber;
import Y1234.Y1234Factory;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Kafka Subscriber</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class KafkaSubscriberTest extends SubscriberTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(KafkaSubscriberTest.class);
	}

	/**
	 * Constructs a new Kafka Subscriber test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KafkaSubscriberTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Kafka Subscriber test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected KafkaSubscriber getFixture() {
		return (KafkaSubscriber)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(Y1234Factory.eINSTANCE.createKafkaSubscriber());
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

} //KafkaSubscriberTest
