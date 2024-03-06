package uk.ac.york.eng2.assessment.y1234.video.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import jakarta.inject.Inject;
import uk.ac.york.eng2.assessment.y1234.video.domain.Hashtag;
import uk.ac.york.eng2.assessment.y1234.video.domain.User;
import uk.ac.york.eng2.assessment.y1234.video.domain.Video;
import uk.ac.york.eng2.assessment.y1234.video.dto.UserDTO;
import uk.ac.york.eng2.assessment.y1234.video.dto.VideoDTO;
import uk.ac.york.eng2.assessment.y1234.video.events.VideoProducer;
import uk.ac.york.eng2.assessment.y1234.video.gen.UsersControllerInterface;
import uk.ac.york.eng2.assessment.y1234.video.repositories.HashtagRepository;
import uk.ac.york.eng2.assessment.y1234.video.repositories.UserRepository;
import uk.ac.york.eng2.assessment.y1234.video.repositories.VideoRepository;

@Controller("/users")
public class UsersController implements UsersControllerInterface {
	
	@Inject
	private UserRepository usersRepository;
	
	@Inject
	private VideoRepository videosRepository;
	
	@Inject 
	private HashtagRepository hashtagsRepository;
	
	@Inject
	private VideoProducer producer;
	
	@Override
	public HttpResponse<Iterable<User>> list() {
		return HttpResponse.ok(usersRepository.findAll());
	}
	
	@Transactional
	@Override
	public HttpResponse<Void> add(@Body UserDTO userDetails) {
		User user = new User();
		user.setUsername(userDetails.getUsername());
		usersRepository.save(user);
		producer.userCreated(user.getId(), userDetails);
		return HttpResponse.created(URI.create("/users/" + user.getId()));
	}
	
	@Override
	public HttpResponse<UserDTO> getUser(Long userId) {
		// Get the user, returning 404 if the user id doesn't exist
		Optional<UserDTO> oUser = usersRepository.findOne(userId);
		if (oUser.isEmpty()) {
			return HttpResponse.status(HttpStatus.NOT_FOUND, String.format("User %d not found", userId));
		}
		return HttpResponse.ok(oUser.get());
	}
	
	@Transactional
	@Override
	public HttpResponse<String> addVideo(Long userId, @Body VideoDTO videoDetails) {
		// Get the user, returning 404 if the user id doesn't exist
		Optional<User> oUser = usersRepository.findById(userId);
		if (oUser.isEmpty()) {
			return HttpResponse.notFound(String.format("User %d not found", userId));
		}
		User user = oUser.get();
		
		// Create the set of hashtags from the given hashtag names
		Set<Hashtag> hashtags = new HashSet<Hashtag>();
		if (videoDetails.getHashtags() != null) {
			Set<String> hashtagsSet = new HashSet<String>(
					Arrays.asList(videoDetails.getHashtags().split(",")));
			
			for (String hashtagName : hashtagsSet) {
				Optional<Hashtag> oHashtag = hashtagsRepository.findByName(hashtagName);
				// If the hashtag doesn't exist, we must create it
				if (oHashtag.isEmpty()) {
					Hashtag newHashtag = new Hashtag();
					newHashtag.setName(hashtagName);
					hashtagsRepository.save(newHashtag);
					hashtags.add(newHashtag);
				}
				// If it does exist, we add it to the current video
				else
					hashtags.add(oHashtag.get());
			}
		}
		
		// Create the video object and add it to the DB
		Video video = new Video();
		video.setTitle(videoDetails.getTitle());
		video.setUser(user);
		video.setHashtags(hashtags);

		videosRepository.save(video);
		producer.videoPosted(user.getId(), video.toDto());

		return HttpResponse.created(URI.create(String.format("/videos/%d/", userId) + video.getId()));
	}
	
	@Transactional
	@Override
	public HttpResponse<Iterable<VideoDTO>> getUserVideos(Long userId) {
		// Get the user, returning 404 if the user id doesn't exist
		Optional<User> oUser = usersRepository.findById(userId);
		if (oUser.isEmpty()) {
			return HttpResponse.status(HttpStatus.NOT_FOUND, String.format("User %d not found", userId));
		}
		User user = oUser.get();
		
		// Get the user's videos
		Set<Video> videos = user.getVideos();
		List<VideoDTO> videoDTOs = new ArrayList<VideoDTO>();
		for (Video video : videos) {
			videoDTOs.add(video.toDto());
		}
		return HttpResponse.ok(videoDTOs);
	}

	@Transactional
	@Override
	public HttpResponse<Iterable<VideoDTO>> getViewedVideos(Long userId) {
		// Get the user, returning 404 if the user id doesn't exist
		Optional<User> oUser = usersRepository.findById(userId);
		if (oUser.isEmpty()) {
			return HttpResponse.status(HttpStatus.NOT_FOUND, String.format("User %d not found", userId));
		}
		User user = oUser.get();
		
		// Get the user's viewed videos
		Set<Video> videos = user.getViewedVideos();
		Set<VideoDTO> videoDTOs = new HashSet<VideoDTO>();
		for (Video video : videos) {
			videoDTOs.add(video.toDto());
		}
		return HttpResponse.ok(videoDTOs);
	}
	
	@Transactional
	@Override
	public HttpResponse<Iterable<VideoDTO>> getLikedVideos(Long userId) {
		// Get the user, returning 404 if the user id doesn't exist
		Optional<User> oUser = usersRepository.findById(userId);
		if (oUser.isEmpty()) {
			return HttpResponse.status(HttpStatus.NOT_FOUND, String.format("User %d not found", userId));
		}
		User user = oUser.get();
		
		// Get the user's liked videos
		Set<Video> videos = user.getLikedVideos();
		Set<VideoDTO> videoDTOs = new HashSet<VideoDTO>();
		for (Video video : videos) {
			videoDTOs.add(video.toDto());
		}
		return HttpResponse.ok(videoDTOs);
	}
	
	@Transactional
	@Override
	public HttpResponse<Iterable<VideoDTO>> getDislikedVideos(Long userId) {
		// Get the user, returning 404 if the user id doesn't exist
		Optional<User> oUser = usersRepository.findById(userId);
		if (oUser.isEmpty()) {
			return HttpResponse.status(HttpStatus.NOT_FOUND, String.format("User %d not found", userId));
		}
		User user = oUser.get();
		
		// Get the user's disliked videos
		Set<Video> videos = user.getDislikedVideos();
		Set<VideoDTO> videoDTOs = new HashSet<VideoDTO>();
		for (Video video : videos) {
			videoDTOs.add(video.toDto());
		}
		return HttpResponse.ok(videoDTOs);
	}
	
	@Transactional
	@Override
	public HttpResponse<String> viewVideo(Long userId, Long videoId) {
		// Get the user, returning 404 if the user id doesn't exist
		Optional<User> oUser = usersRepository.findById(userId);
		if (oUser.isEmpty()) {
			return HttpResponse.notFound(String.format("User %d not found", userId));
		}
		User user = oUser.get();
		
		// Get the video, returning 404 if the video id doesn't exist
		Optional<Video> oVideo = videosRepository.findById(videoId);
		if (oVideo.isEmpty()) {
			return HttpResponse.notFound(String.format("Video %d not found", videoId));
		}
		Video video = oVideo.get();
		
		// Add the video to the list of viewed videos and update the user
		if (user.getViewedVideos().add(video)) {
			usersRepository.update(user);
			producer.videoWatched(user.getId(), video.getId());
			return HttpResponse.ok(String.format("User %d watched video %d", userId, videoId));
		}
		
		return HttpResponse.status(HttpStatus.NO_CONTENT, String.format("Video already watched", userId));
	}
	
	@Transactional
	@Override
	public HttpResponse<String> likeVideo(Long userId, Long videoId) {
		// Get the user, returning 404 if the user id doesn't exist
		Optional<User> oUser = usersRepository.findById(userId);
		if (oUser.isEmpty()) {
			return HttpResponse.notFound(String.format("User %d not found", userId));
		}
		User user = oUser.get();
		
		// Get the video, returning 404 if the video id doesn't exist
		Optional<Video> oVideo = videosRepository.findById(videoId);
		if (oVideo.isEmpty()) {
			return HttpResponse.notFound(String.format("Video %d not found", videoId));
		}
		Video video = oVideo.get();
		
		// Add the video to the list of liked videos and update the user
		if (user.getLikedVideos().add(video)) {
			usersRepository.update(user);
			producer.videoLiked(video.getId(), video.toDto());
			return HttpResponse.ok(String.format("User %d liked video %d", userId, videoId));
		}
		
		return HttpResponse.status(HttpStatus.NO_CONTENT, String.format("Video already liked", userId));
	}
	
	@Transactional
	@Override
	public HttpResponse<String> dislikeVideo(Long userId, Long videoId) {
		// Get the user, returning 404 if the user id doesn't exist
		Optional<User> oUser = usersRepository.findById(userId);
		if (oUser.isEmpty()) {
			return HttpResponse.notFound(String.format("User %d not found", userId));
		}
		User user = oUser.get();
		
		// Get the video, returning 404 if the video id doesn't exist
		Optional<Video> oVideo = videosRepository.findById(videoId);
		if (oVideo.isEmpty()) {
			return HttpResponse.notFound(String.format("Video %d not found", videoId));
		}
		Video video = oVideo.get();
		
		// Add the video to the list of disliked videos and update the user
		if (user.getDislikedVideos().add(video)) {
			usersRepository.update(user);
			producer.videoDisliked(video.getId(), video.toDto());
			return HttpResponse.ok(String.format("User %d disliked video %d", userId, videoId));
		}
		
		return HttpResponse.status(HttpStatus.NO_CONTENT, String.format("Video already disliked", userId));
	}
}
