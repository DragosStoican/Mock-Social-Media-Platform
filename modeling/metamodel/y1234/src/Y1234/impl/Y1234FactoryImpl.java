/**
 */
package Y1234.impl;

import Y1234.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Y1234FactoryImpl extends EFactoryImpl implements Y1234Factory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Y1234Factory init() {
		try {
			Y1234Factory theY1234Factory = (Y1234Factory)EPackage.Registry.INSTANCE.getEFactory(Y1234Package.eNS_URI);
			if (theY1234Factory != null) {
				return theY1234Factory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Y1234FactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Y1234FactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case Y1234Package.SOCIAL_MEDIA_PLATFORM: return createSocialMediaPlatform();
			case Y1234Package.MICROSERVICE: return createMicroservice();
			case Y1234Package.KAFKA_PUBLISHER: return createKafkaPublisher();
			case Y1234Package.KAFKA_SUBSCRIBER: return createKafkaSubscriber();
			case Y1234Package.KAFKA_STREAM: return createKafkaStream();
			case Y1234Package.EVENT_STREAM: return createEventStream();
			case Y1234Package.EVENT: return createEvent();
			case Y1234Package.CONTROLLER: return createController();
			case Y1234Package.RESOURCE: return createResource();
			case Y1234Package.JAVA_VAR: return createJavaVar();
			case Y1234Package.ENTITY: return createEntity();
			case Y1234Package.RELATION: return createRelation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SocialMediaPlatform createSocialMediaPlatform() {
		SocialMediaPlatformImpl socialMediaPlatform = new SocialMediaPlatformImpl();
		return socialMediaPlatform;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Microservice createMicroservice() {
		MicroserviceImpl microservice = new MicroserviceImpl();
		return microservice;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KafkaPublisher createKafkaPublisher() {
		KafkaPublisherImpl kafkaPublisher = new KafkaPublisherImpl();
		return kafkaPublisher;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KafkaSubscriber createKafkaSubscriber() {
		KafkaSubscriberImpl kafkaSubscriber = new KafkaSubscriberImpl();
		return kafkaSubscriber;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KafkaStream createKafkaStream() {
		KafkaStreamImpl kafkaStream = new KafkaStreamImpl();
		return kafkaStream;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventStream createEventStream() {
		EventStreamImpl eventStream = new EventStreamImpl();
		return eventStream;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Event createEvent() {
		EventImpl event = new EventImpl();
		return event;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Controller createController() {
		ControllerImpl controller = new ControllerImpl();
		return controller;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Resource createResource() {
		ResourceImpl resource = new ResourceImpl();
		return resource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaVar createJavaVar() {
		JavaVarImpl javaVar = new JavaVarImpl();
		return javaVar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Entity createEntity() {
		EntityImpl entity = new EntityImpl();
		return entity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Relation createRelation() {
		RelationImpl relation = new RelationImpl();
		return relation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Y1234Package getY1234Package() {
		return (Y1234Package)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Y1234Package getPackage() {
		return Y1234Package.eINSTANCE;
	}

} //Y1234FactoryImpl
