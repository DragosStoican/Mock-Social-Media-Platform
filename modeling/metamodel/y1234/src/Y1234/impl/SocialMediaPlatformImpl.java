/**
 */
package Y1234.impl;

import Y1234.Event;
import Y1234.EventStream;
import Y1234.Microservice;
import Y1234.SocialMediaPlatform;
import Y1234.Y1234Package;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Social Media Platform</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link Y1234.impl.SocialMediaPlatformImpl#getName <em>Name</em>}</li>
 *   <li>{@link Y1234.impl.SocialMediaPlatformImpl#getMicroservices <em>Microservices</em>}</li>
 *   <li>{@link Y1234.impl.SocialMediaPlatformImpl#getTopics <em>Topics</em>}</li>
 *   <li>{@link Y1234.impl.SocialMediaPlatformImpl#getEvents <em>Events</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SocialMediaPlatformImpl extends MinimalEObjectImpl.Container implements SocialMediaPlatform {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMicroservices() <em>Microservices</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMicroservices()
	 * @generated
	 * @ordered
	 */
	protected EList<Microservice> microservices;

	/**
	 * The cached value of the '{@link #getTopics() <em>Topics</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTopics()
	 * @generated
	 * @ordered
	 */
	protected EList<EventStream> topics;

	/**
	 * The cached value of the '{@link #getEvents() <em>Events</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEvents()
	 * @generated
	 * @ordered
	 */
	protected EList<Event> events;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SocialMediaPlatformImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Y1234Package.Literals.SOCIAL_MEDIA_PLATFORM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Y1234Package.SOCIAL_MEDIA_PLATFORM__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Microservice> getMicroservices() {
		if (microservices == null) {
			microservices = new EObjectContainmentEList<Microservice>(Microservice.class, this, Y1234Package.SOCIAL_MEDIA_PLATFORM__MICROSERVICES);
		}
		return microservices;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EventStream> getTopics() {
		if (topics == null) {
			topics = new EObjectContainmentEList<EventStream>(EventStream.class, this, Y1234Package.SOCIAL_MEDIA_PLATFORM__TOPICS);
		}
		return topics;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Event> getEvents() {
		if (events == null) {
			events = new EObjectContainmentEList<Event>(Event.class, this, Y1234Package.SOCIAL_MEDIA_PLATFORM__EVENTS);
		}
		return events;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Y1234Package.SOCIAL_MEDIA_PLATFORM__MICROSERVICES:
				return ((InternalEList<?>)getMicroservices()).basicRemove(otherEnd, msgs);
			case Y1234Package.SOCIAL_MEDIA_PLATFORM__TOPICS:
				return ((InternalEList<?>)getTopics()).basicRemove(otherEnd, msgs);
			case Y1234Package.SOCIAL_MEDIA_PLATFORM__EVENTS:
				return ((InternalEList<?>)getEvents()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Y1234Package.SOCIAL_MEDIA_PLATFORM__NAME:
				return getName();
			case Y1234Package.SOCIAL_MEDIA_PLATFORM__MICROSERVICES:
				return getMicroservices();
			case Y1234Package.SOCIAL_MEDIA_PLATFORM__TOPICS:
				return getTopics();
			case Y1234Package.SOCIAL_MEDIA_PLATFORM__EVENTS:
				return getEvents();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Y1234Package.SOCIAL_MEDIA_PLATFORM__NAME:
				setName((String)newValue);
				return;
			case Y1234Package.SOCIAL_MEDIA_PLATFORM__MICROSERVICES:
				getMicroservices().clear();
				getMicroservices().addAll((Collection<? extends Microservice>)newValue);
				return;
			case Y1234Package.SOCIAL_MEDIA_PLATFORM__TOPICS:
				getTopics().clear();
				getTopics().addAll((Collection<? extends EventStream>)newValue);
				return;
			case Y1234Package.SOCIAL_MEDIA_PLATFORM__EVENTS:
				getEvents().clear();
				getEvents().addAll((Collection<? extends Event>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case Y1234Package.SOCIAL_MEDIA_PLATFORM__NAME:
				setName(NAME_EDEFAULT);
				return;
			case Y1234Package.SOCIAL_MEDIA_PLATFORM__MICROSERVICES:
				getMicroservices().clear();
				return;
			case Y1234Package.SOCIAL_MEDIA_PLATFORM__TOPICS:
				getTopics().clear();
				return;
			case Y1234Package.SOCIAL_MEDIA_PLATFORM__EVENTS:
				getEvents().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case Y1234Package.SOCIAL_MEDIA_PLATFORM__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case Y1234Package.SOCIAL_MEDIA_PLATFORM__MICROSERVICES:
				return microservices != null && !microservices.isEmpty();
			case Y1234Package.SOCIAL_MEDIA_PLATFORM__TOPICS:
				return topics != null && !topics.isEmpty();
			case Y1234Package.SOCIAL_MEDIA_PLATFORM__EVENTS:
				return events != null && !events.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //SocialMediaPlatformImpl
