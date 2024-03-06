package uk.ac.york.eng2.assessment.y1234.video.gen;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import uk.ac.york.eng2.assessment.y1234.video.dto.*;
import uk.ac.york.eng2.assessment.y1234.video.domain.*;

public interface UsersControllerInterface {

	@Get("/")
	public HttpResponse<?> list(
		
	);
	
	@Post("/")
	public HttpResponse<?> add(
		@Body UserDTO body		
	);
	
	@Get("/{userId}")
	public HttpResponse<?> getUser(
		Long userId		
	);
	
	@Post("/{userId}/videos")
	public HttpResponse<?> addVideo(
		Long userId,
		@Body VideoDTO body		
	);
	
	@Get("/{userId}/videos")
	public HttpResponse<?> getUserVideos(
		Long userId		
	);
	
	@Get("/{userId}/videos/viewed")
	public HttpResponse<?> getViewedVideos(
		Long userId		
	);
	
	@Get("/{userId}/videos/liked")
	public HttpResponse<?> getLikedVideos(
		Long userId		
	);
	
	@Get("/{userId}/videos/disliked")
	public HttpResponse<?> getDislikedVideos(
		Long userId		
	);
	
	@Put("/{userId}/videos/viewed/{videoId}")
	public HttpResponse<?> viewVideo(
		Long userId,
		Long videoId		
	);
	
	@Put("/{userId}/videos/liked/{videoId}")
	public HttpResponse<?> likeVideo(
		Long userId,
		Long videoId		
	);
	
	@Put("/{userId}/videos/disliked/{videoId}")
	public HttpResponse<?> dislikeVideo(
		Long userId,
		Long videoId		
	);
	
}