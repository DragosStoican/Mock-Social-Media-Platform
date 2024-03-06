package uk.ac.york.eng2.assessment.y1234.video;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
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
import uk.ac.york.eng2.assessment.y1234.video.clients.UsersClient;
import uk.ac.york.eng2.assessment.y1234.video.domain.User;
import uk.ac.york.eng2.assessment.y1234.video.domain.Video;
import uk.ac.york.eng2.assessment.y1234.video.dto.UserDTO;
import uk.ac.york.eng2.assessment.y1234.video.dto.VideoDTO;
import uk.ac.york.eng2.assessment.y1234.video.repositories.HashtagRepository;
import uk.ac.york.eng2.assessment.y1234.video.repositories.UserRepository;
import uk.ac.york.eng2.assessment.y1234.video.repositories.VideoRepository;

@Property(name = "spec.name", value = "KafkaProductionTest")
@MicronautTest(transactional = false)
public class KafkaProductionTest {

	@Inject
	UsersClient usersClient;
	
	@Inject
	UserRepository usersRepository;
	
	@Inject
	HashtagRepository hashtagsRepository;
	
	@Inject
	VideoRepository videosRepository;
	
	private static final Map<Long, VideoDTO> videosPosted = new HashMap<>();
	private static final Map<Long, Long> videosWatched = new HashMap<>();
	private static final Map<Long, VideoDTO> videosLiked = new HashMap<>();
	private static final Map<Long, VideoDTO> videosDisliked = new HashMap<>();
	private static final Map<Long, UserDTO> usersCreated = new HashMap<>();
	
	@BeforeEach
	void clean() {
		usersRepository.deleteAll();
		videosRepository.deleteAll();
		hashtagsRepository.deleteAll();
		videosPosted.clear();
		videosWatched.clear();
		videosLiked.clear();
		videosDisliked.clear();
	}
	
	@Test
	public void addUser() {
		final String username = "Test User";
		
		UserDTO user = new UserDTO();
		user.setUsername(username);
		HttpResponse<Void> response = usersClient.add(user);
		assertEquals(HttpStatus.CREATED, response.getStatus(), "Update should be successful");

		Awaitility.await()
			.atMost(Duration.ofSeconds(30))
			.until(() -> usersCreated.size() > 0);
	}
	
	@Test
	public void addVideoNoHashtags() {
		final String videoTitle = "Top10 reasons ENG2 is great!";
		final String username = "York CS Student";
		
		User user = new User();
		user.setUsername(username);
		usersRepository.save(user);
		final Long userId = user.getId();
		
		VideoDTO dto = new VideoDTO();
		dto.setTitle(videoTitle);
		
		// Check that the video was created
		HttpResponse<String> response = usersClient.addVideo(userId, dto);
		assertEquals(HttpStatus.CREATED, response.getStatus(), "Adding a video should be successful");
		
		// Check the producer was called by the addition
		Awaitility.await()
		.atMost(Duration.ofSeconds(30))
		.until(() -> videosPosted.containsKey(userId));
	}
	
	@Test
	public void getViewedVideos() {
		final String videoTitle = "Top10 reasons ENG2 is great!";
		final String username = "York CS Student";
		
		User user = new User();
		user.setUsername(username);
		usersRepository.save(user);
		final Long userId = user.getId();
		
		Video video = new Video();
		video.setTitle(videoTitle);
		video.setUser(user);
		videosRepository.save(video);
		final Long videoId = video.getId();
		
		// Add the video to watched
		HttpResponse<String> response = usersClient.viewVideo(userId, videoId);
		assertEquals(HttpStatus.OK, response.getStatus(), "Viewing a video should be successful");

		// Check the producer was called by the addition
		Awaitility.await()
		.atMost(Duration.ofSeconds(30))
		.until(() -> videosWatched.containsKey(userId));
	}
	
	@Test
	public void getLikedVideos() {
		final String videoTitle = "Top10 reasons ENG2 is great!";
		final String username = "York CS Student";
		
		User user = new User();
		user.setUsername(username);
		usersRepository.save(user);
		final Long userId = user.getId();
		
		Video video = new Video();
		video.setTitle(videoTitle);
		video.setUser(user);
		videosRepository.save(video);
		final Long videoId = video.getId();
		
		// Add the video to watched
		HttpResponse<String> response = usersClient.likeVideo(userId, videoId);
		assertEquals(HttpStatus.OK, response.getStatus(), "Liking a video should be successful");
		
		// Check the producer was called by the addition
		Awaitility.await()
		.atMost(Duration.ofSeconds(30))
		.until(() -> videosLiked.containsKey(videoId));
	}
	
	@Test
	public void getDislikedVideos() {
		final String videoTitle = "Top10 reasons ENG2 is great!";
		final String username = "York CS Student";
		
		User user = new User();
		user.setUsername(username);
		usersRepository.save(user);
		final Long userId = user.getId();
		
		Video video = new Video();
		video.setTitle(videoTitle);
		video.setUser(user);
		videosRepository.save(video);
		final Long videoId = video.getId();
		
		// Add the video to watched
		HttpResponse<String> response = usersClient.dislikeVideo(userId, videoId);
		assertEquals(HttpStatus.OK, response.getStatus(), "Disliking a video should be successful");
		
		// Check the producer was called by the addition
		Awaitility.await()
		.atMost(Duration.ofSeconds(30))
		.until(() -> videosDisliked.containsKey(videoId));
	}
	
	@Requires(property = "spec.name", value = "KafkaProductionTest")
	@KafkaListener(groupId = "kafka-production-test")
	static class TestConsumer {
		@Topic("video_posted")
		void videoPosted(@KafkaKey Long userId, VideoDTO dto) {
			videosPosted.put(userId, dto);
		}
		@Topic("video_watched")
		void videoWatched(@KafkaKey Long userId, Long videoId) {
			videosWatched.put(userId, videoId);
		}
		@Topic("video_liked")
		void videoLiked(@KafkaKey Long videoId, VideoDTO dto) {
			videosLiked.put(videoId, dto);
		}
		@Topic("video_disliked")
		void videoDisliked(@KafkaKey Long videoId, VideoDTO dto) {
			videosDisliked.put(videoId, dto);
		}
		@Topic("user_created")
		void userCreated(@KafkaKey Long userId, UserDTO dto) {
			usersCreated.put(userId, dto);
		}
	}
}
