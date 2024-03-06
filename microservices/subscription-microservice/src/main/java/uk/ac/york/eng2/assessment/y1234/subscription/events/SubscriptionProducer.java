package uk.ac.york.eng2.assessment.y1234.subscription.events;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import uk.ac.york.eng2.assessment.y1234.subscription.dto.*;
import uk.ac.york.eng2.assessment.y1234.subscription.domain.*;

@KafkaClient
public interface SubscriptionProducer {
	
	@Topic("user_subscribed")
	public void userSubscribed(@KafkaKey Long userId, Hashtag hashtag);
	
	@Topic("user_unsubscribed")
	public void userUnsubscribed(@KafkaKey Long userId, Hashtag hashtag);
	
}
