/**
 */
package Y1234.impl;

import Y1234.Event;
import Y1234.EventStream;
import Y1234.Publisher;
import Y1234.Subscriber;
import Y1234.Y1234Package;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event Stream</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link Y1234.impl.EventStreamImpl#getName <em>Name</em>}</li>
 *   <li>{@link Y1234.impl.EventStreamImpl#getEventType <em>Event Type</em>}</li>
 *   <li>{@link Y1234.impl.EventStreamImpl#getPublishers <em>Publishers</em>}</li>
 *   <li>{@link Y1234.impl.EventStreamImpl#getSubscribers <em>Subscribers</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EventStreamImpl extends MinimalEObjectImpl.Container implements EventStream {
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
	 * The cached value of the '{@link #getEventType() <em>Event Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventType()
	 * @generated
	 * @ordered
	 */
	protected Event eventType;

	/**
	 * The cached value of the '{@link #getPublishers() <em>Publishers</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPublishers()
	 * @generated
	 * @ordered
	 */
	protected EList<Publisher> publishers;

	/**
	 * The cached value of the '{@link #getSubscribers() <em>Subscribers</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubscribers()
	 * @generated
	 * @ordered
	 */
	protected EList<Subscriber> subscribers;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EventStreamImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Y1234Package.Literals.EVENT_STREAM;
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
			eNotify(new ENotificationImpl(this, Notification.SET, Y1234Package.EVENT_STREAM__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Event getEventType() {
		if (eventType != null && eventType.eIsProxy()) {
			InternalEObject oldEventType = (InternalEObject)eventType;
			eventType = (Event)eResolveProxy(oldEventType);
			if (eventType != oldEventType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Y1234Package.EVENT_STREAM__EVENT_TYPE, oldEventType, eventType));
			}
		}
		return eventType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Event basicGetEventType() {
		return eventType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEventType(Event newEventType, NotificationChain msgs) {
		Event oldEventType = eventType;
		eventType = newEventType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Y1234Package.EVENT_STREAM__EVENT_TYPE, oldEventType, newEventType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEventType(Event newEventType) {
		if (newEventType != eventType) {
			NotificationChain msgs = null;
			if (eventType != null)
				msgs = ((InternalEObject)eventType).eInverseRemove(this, Y1234Package.EVENT__TOPICS, Event.class, msgs);
			if (newEventType != null)
				msgs = ((InternalEObject)newEventType).eInverseAdd(this, Y1234Package.EVENT__TOPICS, Event.class, msgs);
			msgs = basicSetEventType(newEventType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Y1234Package.EVENT_STREAM__EVENT_TYPE, newEventType, newEventType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Publisher> getPublishers() {
		if (publishers == null) {
			publishers = new EObjectWithInverseResolvingEList<Publisher>(Publisher.class, this, Y1234Package.EVENT_STREAM__PUBLISHERS, Y1234Package.PUBLISHER__TO);
		}
		return publishers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Subscriber> getSubscribers() {
		if (subscribers == null) {
			subscribers = new EObjectWithInverseResolvingEList<Subscriber>(Subscriber.class, this, Y1234Package.EVENT_STREAM__SUBSCRIBERS, Y1234Package.SUBSCRIBER__FROM);
		}
		return subscribers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Y1234Package.EVENT_STREAM__EVENT_TYPE:
				if (eventType != null)
					msgs = ((InternalEObject)eventType).eInverseRemove(this, Y1234Package.EVENT__TOPICS, Event.class, msgs);
				return basicSetEventType((Event)otherEnd, msgs);
			case Y1234Package.EVENT_STREAM__PUBLISHERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPublishers()).basicAdd(otherEnd, msgs);
			case Y1234Package.EVENT_STREAM__SUBSCRIBERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSubscribers()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Y1234Package.EVENT_STREAM__EVENT_TYPE:
				return basicSetEventType(null, msgs);
			case Y1234Package.EVENT_STREAM__PUBLISHERS:
				return ((InternalEList<?>)getPublishers()).basicRemove(otherEnd, msgs);
			case Y1234Package.EVENT_STREAM__SUBSCRIBERS:
				return ((InternalEList<?>)getSubscribers()).basicRemove(otherEnd, msgs);
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
			case Y1234Package.EVENT_STREAM__NAME:
				return getName();
			case Y1234Package.EVENT_STREAM__EVENT_TYPE:
				if (resolve) return getEventType();
				return basicGetEventType();
			case Y1234Package.EVENT_STREAM__PUBLISHERS:
				return getPublishers();
			case Y1234Package.EVENT_STREAM__SUBSCRIBERS:
				return getSubscribers();
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
			case Y1234Package.EVENT_STREAM__NAME:
				setName((String)newValue);
				return;
			case Y1234Package.EVENT_STREAM__EVENT_TYPE:
				setEventType((Event)newValue);
				return;
			case Y1234Package.EVENT_STREAM__PUBLISHERS:
				getPublishers().clear();
				getPublishers().addAll((Collection<? extends Publisher>)newValue);
				return;
			case Y1234Package.EVENT_STREAM__SUBSCRIBERS:
				getSubscribers().clear();
				getSubscribers().addAll((Collection<? extends Subscriber>)newValue);
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
			case Y1234Package.EVENT_STREAM__NAME:
				setName(NAME_EDEFAULT);
				return;
			case Y1234Package.EVENT_STREAM__EVENT_TYPE:
				setEventType((Event)null);
				return;
			case Y1234Package.EVENT_STREAM__PUBLISHERS:
				getPublishers().clear();
				return;
			case Y1234Package.EVENT_STREAM__SUBSCRIBERS:
				getSubscribers().clear();
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
			case Y1234Package.EVENT_STREAM__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case Y1234Package.EVENT_STREAM__EVENT_TYPE:
				return eventType != null;
			case Y1234Package.EVENT_STREAM__PUBLISHERS:
				return publishers != null && !publishers.isEmpty();
			case Y1234Package.EVENT_STREAM__SUBSCRIBERS:
				return subscribers != null && !subscribers.isEmpty();
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

} //EventStreamImpl
