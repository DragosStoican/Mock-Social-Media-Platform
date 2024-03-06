package uk.ac.york.eng2.assessment.y1234.hashtag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import antlr.collections.List;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import uk.ac.york.eng2.assessment.y1234.hashtag.domain.Hashtag;
import uk.ac.york.eng2.assessment.y1234.hashtag.domain.User;
import uk.ac.york.eng2.assessment.y1234.hashtag.repositories.HashtagRepository;
import uk.ac.york.eng2.assessment.y1234.hashtag.repositories.UserRepository;

@MicronautTest(transactional = false, environments = "no_streams")
public class HashtagControllerTest {

	@Inject
	HashtagClient client;
	
	@Inject
	HashtagRepository hashtagRepository;
	
	@Inject
	UserRepository userRepository;
	
	@BeforeEach
	void clean() {
		hashtagRepository.deleteAll();
		userRepository.deleteAll();
	}
	
	@Test
	public void testHealthCheck() {
		HttpResponse<Iterable<Hashtag>> response = client.getHashtags();
		assertEquals(HttpStatus.OK, response.status());
	}
	
	@Test
	public void testGetRecommendedHashtags( ) {
		final Long firstId = 1L;
		final String hashtagName = "CS";
		final String hashtag2Name = "is";
		final String hashtag3Name = "great!";
		final String hashtag4Name = "yee";
		
		// Check if NOT_FOUND is returned for invalid hashtag id
		assertEquals(HttpStatus.NOT_FOUND, client.getRecommendedHashtags(99L).getStatus());

		
		User user = new User();
		user.setId(firstId);
		userRepository.save(user);
		final Long userId = user.getId();
		
		User user2 = new User();
		user2.setId(userId + 1L);
		userRepository.save(user2);
		final Long user2Id = user2.getId();
		
		// Both users are subscribed to hashtag 1
		Hashtag hashtag = new Hashtag();
		hashtag.setId(user2Id + 1L);
		hashtag.setName(hashtagName);
		hashtag.getSubscribers().add(user);
		hashtag.getSubscribers().add(user2);
		hashtagRepository.save(hashtag);
		final Long hashtagId = hashtag.getId();
		
		// Both users are subscribed to hashtag 2
		Hashtag hashtag2 = new Hashtag();
		hashtag2.setId(hashtagId + 1L);
		hashtag2.setName(hashtag2Name);
		hashtag2.getSubscribers().add(user);
		hashtag2.getSubscribers().add(user2);
		hashtagRepository.save(hashtag2);
		final Long hashtag2Id = hashtag2.getId();
		
		// Only user 1 is subscribed to hashtag 3
		Hashtag hashtag3 = new Hashtag();
		hashtag3.setId(hashtag2Id + 1L);
		hashtag3.setName(hashtag3Name);
		hashtag3.getSubscribers().add(user);
		hashtagRepository.save(hashtag3);
		final Long hashtag3Id = hashtag3.getId();
		
		// Hashtag 4 has no subscribers
		Hashtag hashtag4 = new Hashtag();
		hashtag4.setId(hashtag3Id + 1L);
		hashtag4.setName(hashtag4Name);
		hashtagRepository.save(hashtag4);
		final Long hashtag4Id = hashtag4.getId();
		
		// Hashtag1 should recommend hashtag 2 and 3
		LinkedHashMap<String, Integer> m = client.getRecommendedHashtags(hashtagId).getBody().get();
		assertEquals(2, m.keySet().size());
		// The first element of the map should have 2 common subscribers and the map should be sorted by values
		ArrayList<Integer> commonSubs = new ArrayList<Integer>(m.values());
		assertEquals(2, commonSubs.get(0));
		assertTrue(commonSubs.get(0) > commonSubs.get(1));
		
		// Hashtag 4 shouldn't recommand any hashtags
		m = client.getRecommendedHashtags(hashtag4Id).getBody().get();
		assertEquals(0, m.keySet().size());
	}
}
