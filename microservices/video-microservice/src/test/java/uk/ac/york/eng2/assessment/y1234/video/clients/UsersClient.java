package uk.ac.york.eng2.assessment.y1234.video.clients;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.assessment.y1234.video.domain.User;
import uk.ac.york.eng2.assessment.y1234.video.dto.UserDTO;
import uk.ac.york.eng2.assessment.y1234.video.dto.VideoDTO;


@Client("/users")
public interface UsersClient {
	
	@Get("/")
	public Iterable<User> list();

	@Post("/")
	public HttpResponse<Void> add(@Body UserDTO userDetails);
	
	@Get("/{userId}")
	public HttpResponse<UserDTO> getUser(Long userId);
	
	@Post("/{userId}/videos")
	public HttpResponse<String> addVideo(long userId, @Body VideoDTO videoDetails);
	
	@Get("/{userId}/videos")
	public Iterable<VideoDTO> getUserVideos(long userId);
	
	@Get("/{userId}/videos/viewed")
	public Iterable<VideoDTO> getViewedVideos(Long userId);
	
	@Get("/{userId}/videos/liked")
	public Iterable<VideoDTO> getLikedVideos(Long userId);
	
	@Get("/{userId}/videos/disliked")
	public Iterable<VideoDTO> getDislikedVideos(Long userId);
	
	@Put("/{userId}/videos/viewed/{videoId}")
	public HttpResponse<String> viewVideo(Long userId, Long videoId);
	
	@Put("/{userId}/videos/liked/{videoId}")
	public HttpResponse<String> likeVideo(Long userId, Long videoId);
	
	@Put("/{userId}/videos/disliked/{videoId}")
	public HttpResponse<String> dislikeVideo(Long userId, Long videoId);

}
