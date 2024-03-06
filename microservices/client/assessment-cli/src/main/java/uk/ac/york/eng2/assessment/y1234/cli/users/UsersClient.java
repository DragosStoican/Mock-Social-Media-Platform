package uk.ac.york.eng2.assessment.y1234.cli.users;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.assessment.y1234.cli.domain.User;
import uk.ac.york.eng2.assessment.y1234.cli.dto.UserDTO;
import uk.ac.york.eng2.assessment.y1234.cli.dto.VideoDTO;


@Client("${users.url:`http://localhost:8080/users`}")
public interface UsersClient {
	
	@Get("/")
	public HttpResponse<Iterable<User>> list(
		
	);
	
	@Post("/")
	public HttpResponse<Void> add(
		@Body UserDTO body		
	);
	
	@Post("/{userId}/videos")
	public HttpResponse<String> addVideo(
		Long userId,
		@Body VideoDTO body		
	);
	
	@Get("/{userId}/videos")
	public HttpResponse<Iterable<VideoDTO>> getUserVideos(
		Long userId		
	);
	
	@Put("/{userId}/videos/viewed/{videoId}")
	public HttpResponse<String> viewVideo(
		Long userId,
		Long videoId		
	);
	
	@Put("/{userId}/videos/liked/{videoId}")
	public HttpResponse<String> likeVideo(
		Long userId,
		Long videoId		
	);
	
	@Put("/{userId}/videos/disliked/{videoId}")
	public HttpResponse<String> dislikeVideo(
		Long userId,
		Long videoId		
	);

}
