package uk.ac.york.eng2.assessment.y1234.subscription.gen;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import uk.ac.york.eng2.assessment.y1234.subscription.dto.*;
import uk.ac.york.eng2.assessment.y1234.subscription.domain.*;

public interface SubscriptionControllerInterface {

	@Get("/")
	public HttpResponse<?> getUsers(
		
	);
	
	@Get("/{userId}")
	public HttpResponse<?> getSubscriptions(
		Long userId		
	);
	
	@Put("/{userId}/subscribe")
	public HttpResponse<?> subscribe(
		Long userId,
		Long hashtagId		
	);
	
	@Put("/{userId}/unsubscribe")
	public HttpResponse<?> unsubscribe(
		Long userId,
		Long hashtagId		
	);
	
	@Get("/{userId}/recomandations")
	public HttpResponse<?> getRecomandationsForHashtag(
		Long userId,
		Long hashtagId		
	);
	
}