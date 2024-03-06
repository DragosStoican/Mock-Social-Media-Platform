package uk.ac.york.eng2.assessment.y1234.video;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import uk.ac.york.eng2.assessment.y1234.video.clients.UsersClient;
import uk.ac.york.eng2.assessment.y1234.video.domain.Hashtag;
import uk.ac.york.eng2.assessment.y1234.video.domain.User;
import uk.ac.york.eng2.assessment.y1234.video.domain.Video;
import uk.ac.york.eng2.assessment.y1234.video.dto.UserDTO;
import uk.ac.york.eng2.assessment.y1234.video.dto.VideoDTO;
import uk.ac.york.eng2.assessment.y1234.video.events.VideoProducer;
import uk.ac.york.eng2.assessment.y1234.video.repositories.HashtagRepository;
import uk.ac.york.eng2.assessment.y1234.video.repositories.UserRepository;
import uk.ac.york.eng2.assessment.y1234.video.repositories.VideoRepository;

@MicronautTest(transactional = false, environments = "no_streams")
public class UsersControllerTest {

	@Inject
	UsersClient usersClient;
	
	@Inject
	UserRepository usersRepository;
	
	@Inject
	HashtagRepository hashtagsRepository;
	
	@Inject
	VideoRepository videosRepository;
	
	private final Map<Long, VideoDTO> videosPosted = new HashMap<>();
	private final Map<Long, Long> videosWatched = new HashMap<>();
	private final Map<Long, VideoDTO> videosLiked = new HashMap<>();
	private final Map<Long, VideoDTO> videosDisliked = new HashMap<>();
	private final Map<Long, UserDTO> usersCreated = new HashMap<>();
	
	@MockBean(VideoProducer.class)
	VideoProducer testProducer() {
		return new VideoProducer() {

			@Override
			public void videoPosted(Long userId, VideoDTO dto) {
				videosPosted.put(userId, dto);				
			}

			@Override
			public void videoWatched(Long userId, Long videoId) {
				videosWatched.put(userId, videoId);
			}

			@Override
			public void videoLiked(Long videoId, VideoDTO dto) {
				videosLiked.put(videoId, dto);					
			}

			@Override
			public void videoDisliked(Long videoId, VideoDTO dto) {
				videosDisliked.put(videoId, dto);					
			}

			@Override
			public void userCreated(Long userId, UserDTO dto) {
				usersCreated.put(userId, dto);
			}

		};
	}
	
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
	public void noUsers() {
		Iterable<User> iterUsers = usersClient.list();
		assertFalse(iterUsers.iterator().hasNext(),
				 "Service should not list any videos initially");
	}
	
	@Test
	public void addUser() {
		final String username = "Test User";
		
		UserDTO user = new UserDTO();
		user.setUsername(username);
		HttpResponse<Void> response = usersClient.add(user);
		assertEquals(HttpStatus.CREATED, response.getStatus(), "Update should be successful");

		List<User> users = iterableToList(usersClient.list());
		
		assertEquals(1, users.size());
		assertEquals(username, users.get(0).getUsername());
		
		// Check the producer was called by the addition
		assertTrue(usersCreated.containsKey(users.get(0).getId()));
	}
	
	@Test
	public void getUser() {
		final String username = "York CS Student";
		
		User user = new User();
		user.setUsername(username);
		usersRepository.save(user);
		final Long userId = user.getId();
		
		HttpResponse<UserDTO> response = usersClient.getUser(userId);
		assertTrue(response.getBody().isPresent(), "The user should be present");
		
		UserDTO userDTO =response.getBody().get();
		assertEquals(user.getUsername(), userDTO.getUsername(), "Username should be fetched correctly");
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
		assertTrue(videosPosted.containsKey(userId));
		
		List<VideoDTO> v = iterableToList(usersClient.getUserVideos(userId));
		assertEquals(1, v.size(), "User should have 1 video posted now");
		assertEquals(videoTitle, v.get(0).getTitle(), "Video should be titled " + videoTitle);
	}
	
	@Test
	public void addVideoWithHashtags() {
		String videoTitle = "Top10 reasons ENG2 is great!";
		String hashtags = "CS,York,England";
		final String username = "York CS Student";
		
		User user = new User();
		user.setUsername(username);
		usersRepository.save(user);
		final Long userId = user.getId();
		
		VideoDTO dto = new VideoDTO();
		dto.setTitle(videoTitle);
		dto.setHashtags(hashtags);
		
		// Check that the video was created
		HttpResponse<String> response = usersClient.addVideo(userId, dto);
		assertEquals(HttpStatus.CREATED, response.getStatus(), "Adding a video should be successful");
		
		// Check the producer was called by the addition
		assertTrue(videosPosted.containsKey(userId));
		
		// Check that the hashtags were added to the DB
		List<Hashtag> h = iterableToList(hashtagsRepository.findAll());
		assertEquals(3, h.size());
		
		// Adding another video with the same hashtags shouldn't change the nr of hashtags in the DB
		videoTitle = "Not the same video!";
		hashtags = "CS,York";
		dto.setTitle(videoTitle);
		dto.setHashtags(hashtags);
		
		response = usersClient.addVideo(userId, dto);
		assertEquals(HttpStatus.CREATED, response.getStatus(), "Adding a second video should be successful");
		
		h = iterableToList(hashtagsRepository.findAll());
		assertEquals(3, h.size());
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
		assertTrue(videosWatched.containsKey(userId));
		
		// Check that adding the video twice doesn't do anything
		response = usersClient.viewVideo(userId, videoId);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatus(), "Viewing a video a second time should return 203 code");
		
		// Check that there is a single video viewed for the user
		List<VideoDTO> v = iterableToList(usersClient.getViewedVideos(userId));
		assertEquals(1, v.size(), "User should have 1 video viewed now");
		assertEquals(videoTitle, v.get(0).getTitle(), "Video should be titled " + videoTitle);
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
		assertTrue(videosLiked.containsKey(videoId));
		
		// Check that adding the video twice doesn't do anything
		response = usersClient.likeVideo(userId, videoId);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatus(), "Liking a video a second time should return 203 code");
		
		// Check that there is a single video liked for the user
		List<VideoDTO> v = iterableToList(usersClient.getLikedVideos(userId));
		assertEquals(1, v.size(), "User should have 1 video liked now");
		assertEquals(videoTitle, v.get(0).getTitle(), "Video should be titled " + videoTitle);
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
		assertTrue(videosDisliked.containsKey(videoId));
		
		// Check that adding the video twice doesn't do anything
		response = usersClient.dislikeVideo(userId, videoId);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatus(), "Disliking a video a second time should return 203 code");
		
		// Check that there is a single video disliked for the user
		List<VideoDTO> v = iterableToList(usersClient.getDislikedVideos(userId));
		assertEquals(1, v.size(), "User should have 1 video disliked now");
		assertEquals(videoTitle, v.get(0).getTitle(), "Video should be titled " + videoTitle);
	}
	
	@Test
	public void testNotFound() {
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
		
		// Test the methods that should return 404 when objectId is not found
		assertEquals(HttpStatus.NOT_FOUND, usersClient.getUser(99L).getStatus());
		assertEquals(HttpStatus.NOT_FOUND, usersClient.addVideo(99L, new VideoDTO()).getStatus());
		
		assertEquals(HttpStatus.NOT_FOUND, usersClient.viewVideo(99L, 99L).getStatus());
		assertEquals(HttpStatus.NOT_FOUND, usersClient.viewVideo(userId, 99L).getStatus());
		assertEquals(HttpStatus.NOT_FOUND, usersClient.viewVideo(99L, videoId).getStatus());
		
		assertEquals(HttpStatus.NOT_FOUND, usersClient.likeVideo(99L, 99L).getStatus());
		assertEquals(HttpStatus.NOT_FOUND, usersClient.likeVideo(userId, 99L).getStatus());
		assertEquals(HttpStatus.NOT_FOUND, usersClient.likeVideo(99L, videoId).getStatus());
		
		assertEquals(HttpStatus.NOT_FOUND, usersClient.dislikeVideo(99L, 99L).getStatus());
		assertEquals(HttpStatus.NOT_FOUND, usersClient.dislikeVideo(userId, 99L).getStatus());
		assertEquals(HttpStatus.NOT_FOUND, usersClient.dislikeVideo(99L, videoId).getStatus());
		
		assertEquals(null, usersClient.getUserVideos(99L));
		assertEquals(null, usersClient.getViewedVideos(99L));
		assertEquals(null, usersClient.getLikedVideos(99L));
		assertEquals(null, usersClient.getDislikedVideos(99L));
	}
	
	private <T> List<T> iterableToList(Iterable<T> iterable) {
		List<T> l = new ArrayList<>();
		iterable.forEach(l::add);
		return l;
	}
}
