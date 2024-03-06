/**
 */
package Y1234.impl;

import Y1234.JavaVar;
import Y1234.Resource;
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
 * An implementation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link Y1234.impl.ResourceImpl#getName <em>Name</em>}</li>
 *   <li>{@link Y1234.impl.ResourceImpl#getPath <em>Path</em>}</li>
 *   <li>{@link Y1234.impl.ResourceImpl#getType <em>Type</em>}</li>
 *   <li>{@link Y1234.impl.ResourceImpl#getQueryParameters <em>Query Parameters</em>}</li>
 *   <li>{@link Y1234.impl.ResourceImpl#getRequestType <em>Request Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ResourceImpl extends MinimalEObjectImpl.Container implements Resource {
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
	 * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected String path = PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getQueryParameters() <em>Query Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQueryParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<JavaVar> queryParameters;

	/**
	 * The default value of the '{@link #getRequestType() <em>Request Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequestType()
	 * @generated
	 * @ordered
	 */
	protected static final String REQUEST_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRequestType() <em>Request Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequestType()
	 * @generated
	 * @ordered
	 */
	protected String requestType = REQUEST_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Y1234Package.Literals.RESOURCE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, Y1234Package.RESOURCE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPath() {
		return path;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPath(String newPath) {
		String oldPath = path;
		path = newPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Y1234Package.RESOURCE__PATH, oldPath, path));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Y1234Package.RESOURCE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<JavaVar> getQueryParameters() {
		if (queryParameters == null) {
			queryParameters = new EObjectContainmentEList<JavaVar>(JavaVar.class, this, Y1234Package.RESOURCE__QUERY_PARAMETERS);
		}
		return queryParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRequestType() {
		return requestType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRequestType(String newRequestType) {
		String oldRequestType = requestType;
		requestType = newRequestType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Y1234Package.RESOURCE__REQUEST_TYPE, oldRequestType, requestType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Y1234Package.RESOURCE__QUERY_PARAMETERS:
				return ((InternalEList<?>)getQueryParameters()).basicRemove(otherEnd, msgs);
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
			case Y1234Package.RESOURCE__NAME:
				return getName();
			case Y1234Package.RESOURCE__PATH:
				return getPath();
			case Y1234Package.RESOURCE__TYPE:
				return getType();
			case Y1234Package.RESOURCE__QUERY_PARAMETERS:
				return getQueryParameters();
			case Y1234Package.RESOURCE__REQUEST_TYPE:
				return getRequestType();
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
			case Y1234Package.RESOURCE__NAME:
				setName((String)newValue);
				return;
			case Y1234Package.RESOURCE__PATH:
				setPath((String)newValue);
				return;
			case Y1234Package.RESOURCE__TYPE:
				setType((String)newValue);
				return;
			case Y1234Package.RESOURCE__QUERY_PARAMETERS:
				getQueryParameters().clear();
				getQueryParameters().addAll((Collection<? extends JavaVar>)newValue);
				return;
			case Y1234Package.RESOURCE__REQUEST_TYPE:
				setRequestType((String)newValue);
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
			case Y1234Package.RESOURCE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case Y1234Package.RESOURCE__PATH:
				setPath(PATH_EDEFAULT);
				return;
			case Y1234Package.RESOURCE__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case Y1234Package.RESOURCE__QUERY_PARAMETERS:
				getQueryParameters().clear();
				return;
			case Y1234Package.RESOURCE__REQUEST_TYPE:
				setRequestType(REQUEST_TYPE_EDEFAULT);
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
			case Y1234Package.RESOURCE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case Y1234Package.RESOURCE__PATH:
				return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
			case Y1234Package.RESOURCE__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case Y1234Package.RESOURCE__QUERY_PARAMETERS:
				return queryParameters != null && !queryParameters.isEmpty();
			case Y1234Package.RESOURCE__REQUEST_TYPE:
				return REQUEST_TYPE_EDEFAULT == null ? requestType != null : !REQUEST_TYPE_EDEFAULT.equals(requestType);
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
		result.append(", path: ");
		result.append(path);
		result.append(", type: ");
		result.append(type);
		result.append(", requestType: ");
		result.append(requestType);
		result.append(')');
		return result.toString();
	}

} //ResourceImpl
