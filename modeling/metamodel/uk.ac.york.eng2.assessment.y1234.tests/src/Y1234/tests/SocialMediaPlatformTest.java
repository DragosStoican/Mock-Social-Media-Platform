/**
 */
package Y1234.tests;

import Y1234.SocialMediaPlatform;
import Y1234.Y1234Factory;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Social Media Platform</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class SocialMediaPlatformTest extends TestCase {

	/**
	 * The fixture for this Social Media Platform test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SocialMediaPlatform fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(SocialMediaPlatformTest.class);
	}

	/**
	 * Constructs a new Social Media Platform test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SocialMediaPlatformTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Social Media Platform test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(SocialMediaPlatform fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Social Media Platform test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SocialMediaPlatform getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(Y1234Factory.eINSTANCE.createSocialMediaPlatform());
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

} //SocialMediaPlatformTest
