package uk.ac.york.eng2.assessment.y1234.hashtag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import uk.ac.york.eng2.assessment.y1234.hashtag.domain.Hashtag;
import uk.ac.york.eng2.assessment.y1234.hashtag.domain.User;
import uk.ac.york.eng2.assessment.y1234.hashtag.events.HashtagConsumer;
import uk.ac.york.eng2.assessment.y1234.hashtag.repositories.HashtagRepository;
import uk.ac.york.eng2.assessment.y1234.hashtag.repositories.UserRepository;

@MicronautTest(transactional = false, environments = "no_streams")
public class HashtagConsumerTest {

	@Inject
	HashtagConsumer consumer;
	
	@Inject
	UserRepository userRepository;
	
	@Inject
	HashtagRepository hashtagRepository;
	
	@BeforeEach
	void clean() {
		hashtagRepository.deleteAll();
		userRepository.deleteAll();
	}
	
	@Test
	public void testUserSubscribed() {
		final Long userId = 1L;
		final Long hashtagId = 2L;
		final String hashtagName = "CS";
		
		Hashtag hashtag = new Hashtag();
		hashtag.setId(hashtagId);
		hashtag.setName(hashtagName);
		
		consumer.userSubscribed(userId, hashtag);
		
		assertTrue(userRepository.findById(userId).isPresent(), "The user should be created with the id equal to the record key");
		assertTrue(hashtagRepository.findById(hashtagId).isPresent(), "The hashtag should be created with the same id");
		
		
		Hashtag h = hashtagRepository.findById(hashtagId).get();
		assertEquals(1, h.getSubscribers().size(), "The hashtag should have a subscriber");
		assertEquals(userId, h.getSubscribers().iterator().next().getId(), "The subscriber's id should match the record key");
		
		// 
		final Long user2Id = 2L;
		consumer.userSubscribed(user2Id, hashtag);
		h = hashtagRepository.findById(hashtagId).get();
		assertEquals(2, h.getSubscribers().size(), "Adding another user should update the existing hashtag");
		
	}
	
	@Test
	public void testUserUnsubscribed() {
		final Long userId = 1L;
		final Long hashtagId = 2L;
		final String hashtagName = "CS";
		
		User user = new User();
		user.setId(userId);
		userRepository.save(user);
		
		Hashtag hashtag = new Hashtag();
		hashtag.setId(hashtagId);
		hashtag.setName(hashtagName);
		hashtag.setSubscribers(new HashSet<User>(Arrays.asList(user)));
		hashtagRepository.save(hashtag);
		
		consumer.userUnsubscribed(userId, hashtag);
		
		Hashtag h = hashtagRepository.findById(hashtagId).get();
		assertEquals(0, h.getSubscribers().size());
	}
}
