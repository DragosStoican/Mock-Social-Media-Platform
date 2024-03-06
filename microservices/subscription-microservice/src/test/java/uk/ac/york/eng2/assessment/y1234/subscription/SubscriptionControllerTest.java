package uk.ac.york.eng2.assessment.y1234.subscription;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import uk.ac.york.eng2.assessment.y1234.subscription.domain.Hashtag;
import uk.ac.york.eng2.assessment.y1234.subscription.domain.User;
import uk.ac.york.eng2.assessment.y1234.subscription.domain.Video;
import uk.ac.york.eng2.assessment.y1234.subscription.dto.VideoDTO;
import uk.ac.york.eng2.assessment.y1234.subscription.events.SubscriptionProducer;
import uk.ac.york.eng2.assessment.y1234.subscription.repositories.HashtagRepository;
import uk.ac.york.eng2.assessment.y1234.subscription.repositories.UserRepository;
import uk.ac.york.eng2.assessment.y1234.subscription.repositories.VideoRepository;

@MicronautTest(transactional = false, environments = "no_streams")
public class SubscriptionControllerTest {

	@Inject
	SubscriptionClient client;
	
	@Inject
	HashtagRepository hashtagRepository;
	
	@Inject
	VideoRepository videoRepository;
	
	@Inject
	UserRepository userRepository;
	
	private final Map<Long, Hashtag> userSubscribed = new HashMap<Long, Hashtag>();
	private final Map<Long, Hashtag> userUnsubscribed = new HashMap<Long, Hashtag>();
	
	@MockBean(SubscriptionProducer.class)
	SubscriptionProducer testProducer() {
		return new SubscriptionProducer() {
			
			@Override
			public void userSubscribed(Long userId, Hashtag hashtag) {
				userSubscribed.put(userId, hashtag);
			}
			
			@Override
			public void userUnsubscribed(Long userId, Hashtag hashtag) {
				userUnsubscribed.put(userId, hashtag);
			}
		};
	}
	
	@BeforeEach
	void clean() {
		hashtagRepository.deleteAll();
		videoRepository.deleteAll();
		userRepository.deleteAll();
		userSubscribed.clear();
		userUnsubscribed.clear();
	}
	
	@Test
	public void healthCheck() {
		HttpResponse<Iterable<User>> response = client.getUsers();
		assertEquals(HttpStatus.OK, response.status());
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
		
		// Check that subscribing twice doesn't do anything
		response = client.subscribe(userId, hashtagId);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatus(), "Subscribing to a hashtag a second time should return 203 code");
		
		// Check the producer was called by the addition
		assertTrue(userSubscribed.containsKey(userId));
		
		// Check that the user is subscribed to the hashtag
		List<Hashtag> subscriptions = iterableToList(client.getSubscriptions(userId).getBody().get());
		assertEquals(1, subscriptions.size(), "User should have one subscription now");
		assertEquals(hashtagName, subscriptions.get(0).getName(), "Hashtag should be titled " + hashtagName);
	
		// Check that unsubscribing removes the hashtag from the user's subscriptions
		response = client.unsubscribe(userId, hashtagId);
		assertEquals(HttpStatus.OK, response.getStatus(), "Unsubscribing from hashtag should be succsesfull");
		
		// Check the producer was called by the addition
		assertTrue(userSubscribed.containsKey(userId));
		
		// Check that unsubscribing twice doesn't do anything
		response = client.unsubscribe(userId, hashtagId);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatus(), "Unsubscribing from a hashtag a second time should return 203 code");
		
		subscriptions = iterableToList(client.getSubscriptions(userId).getBody().get());
		assertEquals(0, subscriptions.size(), "User should have no subscriptions now");
	}
	
	@Test
	public void getRecomandationsForHashtag() {
		final String username = "York CS Student";
		final String hashtagName = "CS";
		final String hashtag2Name = "York";
		final String videoTitle = "York CS is geat!";

		// User 1 posts 3 videos
		User user = new User();
		user.setUsername(username);
		userRepository.save(user);
		final Long userId = user.getId();
		
		Hashtag hashtag = new Hashtag();
		hashtag.setName(hashtagName);
		hashtagRepository.save(hashtag);
		final Long hashtagId = hashtag.getId();
		
		Hashtag hashtag2 = new Hashtag();
		hashtag2.setName(hashtag2Name);
		hashtagRepository.save(hashtag2);
		final Long hashtag2Id = hashtag2.getId();
		
		Video video = new Video();
		video.setTitle(videoTitle);
		video.setUser(user);
		video.setHashtags(new HashSet<Hashtag>(Arrays.asList(hashtag)));
		video.setViews(50L);
		videoRepository.save(video);
		
		Video video2 = new Video();
		video2.setTitle(videoTitle);
		video2.setUser(user);
		video2.setHashtags(new HashSet<Hashtag>(Arrays.asList(hashtag)));
		video2.setViews(100L);
		videoRepository.save(video2);
		
		Video video3 = new Video();
		video3.setTitle(videoTitle);
		video3.setUser(user);
		video3.setHashtags(new HashSet<Hashtag>(Arrays.asList(hashtag)));
		video3.setViews(50L);
		videoRepository.save(video3);
		
		// User2 views one of the videos
		User user2 = new User();
		user2.setUsername(username);
		user2.setViewedVideos(new HashSet<Video>(Arrays.asList(video3)));
		userRepository.save(user2);
		final Long user2Id = user2.getId();
		
		// If user2 subscribes to the hashtag he should get recommended 2 videos because one of them has already been seen
		HttpResponse<String> r = client.subscribe(user2Id, hashtagId);
		List<VideoDTO> videos = iterableToList(client.getRecomandationsForHashtag(user2Id, hashtagId).getBody().get());
		assertEquals(2, videos.size());
		// The list should be ordered
		assertTrue(videos.get(0).getViews() > videos.get(1).getViews());
		
		// User1 shouldn't have any recommended videos because he is the owner of all videos
		r = client.subscribe(userId, hashtagId);
		videos = iterableToList(client.getRecomandationsForHashtag(userId, hashtagId).getBody().get());
		assertEquals(0, videos.size());
		
		// If we request a hashtag we haven't subscribed to, return NO_CONTENT
		assertEquals(HttpStatus.NO_CONTENT, client.getRecomandationsForHashtag(user2Id, hashtag2Id).getStatus());
	}
	
	@Test
	public void testNotFound() {
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
		
		// Test the methods that should return 404 when objectId is not found
		assertEquals(HttpStatus.NOT_FOUND, client.getSubscriptions(99L).getStatus());
		
		assertEquals(HttpStatus.NOT_FOUND, client.subscribe(userId, 99L).getStatus());
		assertEquals(HttpStatus.NOT_FOUND, client.subscribe(99L, hashtagId).getStatus());
		assertEquals(HttpStatus.NOT_FOUND, client.unsubscribe(userId, 99L).getStatus());
		assertEquals(HttpStatus.NOT_FOUND, client.unsubscribe(99L, hashtagId).getStatus());
		
		
		assertEquals(HttpStatus.NOT_FOUND, client.getRecomandationsForHashtag(99L, hashtagId).getStatus());
		assertEquals(HttpStatus.NOT_FOUND, client.getRecomandationsForHashtag(userId, 99L).getStatus());
	}
	
	private <T> List<T> iterableToList(Iterable<T> iterable) {
		List<T> l = new ArrayList<>();
		iterable.forEach(l::add);
		return l;
	}
}
