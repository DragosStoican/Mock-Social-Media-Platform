package uk.ac.york.eng2.assessment.y1234.video.gen;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import uk.ac.york.eng2.assessment.y1234.video.dto.*;
import uk.ac.york.eng2.assessment.y1234.video.domain.*;

public interface HashtagControllerInterface {

	@Get("/")
	public HttpResponse<?> list(
		
	);
	
	@Get("/{hashtagId}")
	public HttpResponse<?> getHashtag(
		Long hashtagId		
	);
	
	@Get("/{hashtagId}/videos")
	public HttpResponse<?> getHashtagVideos(
		Long hashtagId		
	);
	
}