package uk.ac.york.eng2.assessment.y1234.subscription;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.context.annotation.Property;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import uk.ac.york.eng2.assessment.y1234.subscription.domain.Hashtag;
import uk.ac.york.eng2.assessment.y1234.subscription.domain.User;
import uk.ac.york.eng2.assessment.y1234.subscription.repositories.HashtagRepository;
import uk.ac.york.eng2.assessment.y1234.subscription.repositories.UserRepository;
import uk.ac.york.eng2.assessment.y1234.subscription.repositories.VideoRepository;

@Property(name = "spec.name", value = "KafkaProductionTest")
@MicronautTest(transactional = false)
public class KafkaProductionTest {
	
	@Inject
	SubscriptionClient client;
	
	@Inject
	HashtagRepository hashtagRepository;
	
	@Inject
	VideoRepository videoRepository;
	
	@Inject
	UserRepository userRepository;
	
	private static final Map<Long, Hashtag> userSubscribed = new HashMap<Long, Hashtag>();
	private static final Map<Long, Hashtag> userUnsubscribed = new HashMap<Long, Hashtag>();
	
	@BeforeEach
	void clean() {
		hashtagRepository.deleteAll();
		videoRepository.deleteAll();
		userRepository.deleteAll();
		userSubscribed.clear();
		userUnsubscribed.clear();
	}
	
	@Test
	public void subscribe() {
		final String username = "York CS Student";
		final String hashtagName = "CS";

		User user = new User();
		user.setUsername(username);
		userRepository.save(user);
		final Long userId = user.getId();
		
		Hashtag hashtag = new Hashtag();
		hashtag.setName(hashtagName);
		hashtagRepository.save(hashtag);
		final Long hashtagId = hashtag.getId();
		
		//Subscribe the user to the hashtag
		HttpResponse<String> response = client.subscribe(userId, hashtagId);
		assertEquals(HttpStatus.OK, response.getStatus(), "Subscribing to hashtag should be succsesfull");
		
		// Check the producer was called by the addition
		Awaitility.await()
				.atMost(Duration.ofSeconds(30))
				.until(() -> userSubscribed.size() > 0);
	
		// Check that unsubscribing removes the hashtag from the user's subscriptions
		response = client.unsubscribe(userId, hashtagId);
		assertEquals(HttpStatus.OK, response.getStatus(), "Unsubscribing from hashtag should be succsesfull");
		
		// Check the producer was called by the addition
		Awaitility.await()
				.atMost(Duration.ofSeconds(30))
				.until(() -> userUnsubscribed.size() > 0);
	}

	@Requires(property = "spec.name", value = "KafkaProductionTest")
	@KafkaListener(groupId = "kafka-production-test-subscribe")
	static class TestConsumer {
		@Topic("user_subscribed")
		public void userSubscribedTopic(@KafkaKey Long userId, Hashtag hashtag) {
			userSubscribed.put(userId, hashtag);
		}
		
		@Topic("user_unsubscribed")
		public void userUnsubscribedTopic(@KafkaKey Long userId, Hashtag hashtag) {
			userUnsubscribed.put(userId, hashtag);
		}
	}
}
