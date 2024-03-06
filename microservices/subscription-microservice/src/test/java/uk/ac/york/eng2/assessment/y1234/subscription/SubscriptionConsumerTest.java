package uk.ac.york.eng2.assessment.y1234.subscription;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import uk.ac.york.eng2.assessment.y1234.subscription.domain.Hashtag;
import uk.ac.york.eng2.assessment.y1234.subscription.domain.User;
import uk.ac.york.eng2.assessment.y1234.subscription.domain.Video;
import uk.ac.york.eng2.assessment.y1234.subscription.dto.UserDTO;
import uk.ac.york.eng2.assessment.y1234.subscription.dto.VideoDTO;
import uk.ac.york.eng2.assessment.y1234.subscription.events.SubscriptionConsumer;
import uk.ac.york.eng2.assessment.y1234.subscription.events.SubscriptionProducer;
import uk.ac.york.eng2.assessment.y1234.subscription.repositories.HashtagRepository;
import uk.ac.york.eng2.assessment.y1234.subscription.repositories.UserRepository;
import uk.ac.york.eng2.assessment.y1234.subscription.repositories.VideoRepository;

@MicronautTest(transactional = false, environments = "no_streams")
public class SubscriptionConsumerTest {

	@Inject
	SubscriptionConsumer consumer;
	
	@Inject
	HashtagRepository hashtagRepository;
	
	@Inject
	VideoRepository videoRepository;
	
	@Inject
	UserRepository userRepository;
	
	
	@BeforeEach
	void clean() {
		hashtagRepository.deleteAll();
		videoRepository.deleteAll();
		userRepository.deleteAll();
	}
	
	@Test
	public void testUserCreated() {
		final Long userId = 1L;
		final String username = "York CS Student";
		
		UserDTO dto = new UserDTO();
		dto.setUsername(username);
		consumer.userCreated(userId, dto);
		Iterable<User> users = userRepository.findAll();
		
		// Check that the user was created with that id
		assertTrue(userRepository.findById(userId).isPresent());
	}
	
	@Test
	public void testVideoPosted() {
		final String username = "York CS Student";
		final String hashtagName = "CS";
		final String videoTitle = "CS is great!";

		User user = new User();
		user.setUsername(username);
		userRepository.save(user);
		final Long userId = user.getId();
		
		VideoDTO dto = new VideoDTO();
		dto.setTitle(videoTitle);
		dto.setHashtags(hashtagName);
		
		consumer.videoPosted(userId, dto);
		Iterable<Video> videos = videoRepository.findAll();
		Iterable<Hashtag> hashtags = hashtagRepository.findAll();
		
		// CHeck that the new video and new hashtags were created
		assertTrue(videos.iterator().hasNext());
		assertTrue(hashtags.iterator().hasNext());
	}
	
	@Test
	public void testVideoViewed() {
		final String username = "York CS Student";
		final String videoTitle = "York CS is geat!";
		
		// User 1 posts 3 videos
		User user = new User();
		user.setUsername(username);
		userRepository.save(user);
		final Long userId = user.getId();
		
		Video video = new Video();
		video.setTitle(videoTitle);
		video.setUser(user);
		videoRepository.save(video);
		final Long videoId = video.getId();
		
		consumer.videoViewed(userId, videoId);
		
		// Check that the videow as added to the user's videos
		assertTrue(userRepository.findById(userId).get().getVideos().size() > 0);
		
		// Check that the video has 1 view now
		assertTrue(videoRepository.findById(videoId).get().getViews() > 0);
	}
}
