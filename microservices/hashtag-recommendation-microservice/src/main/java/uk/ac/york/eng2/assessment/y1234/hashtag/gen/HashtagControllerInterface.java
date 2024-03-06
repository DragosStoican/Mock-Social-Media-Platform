package uk.ac.york.eng2.assessment.y1234.hashtag.gen;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import uk.ac.york.eng2.assessment.y1234.hashtag.dto.*;
import uk.ac.york.eng2.assessment.y1234.hashtag.domain.*;

public interface HashtagControllerInterface {

	@Get("/")
	public HttpResponse<?> getHashtags(
		
	);
	
	@Get("/{hashtagId}")
	public HttpResponse<?> getRecommendedHashtags(
		Long hashtagId		
	);
	
}