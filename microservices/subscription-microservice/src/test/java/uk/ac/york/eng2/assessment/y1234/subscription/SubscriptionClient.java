package uk.ac.york.eng2.assessment.y1234.subscription;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.assessment.y1234.subscription.domain.Hashtag;
import uk.ac.york.eng2.assessment.y1234.subscription.domain.User;
import uk.ac.york.eng2.assessment.y1234.subscription.dto.VideoDTO;

@Client("/user-subscriptions")
public interface SubscriptionClient {

	@Get("/")
	public HttpResponse<Iterable<User>> getUsers(
		
	);
	
	@Get("/{userId}")
	public HttpResponse<Iterable<Hashtag>> getSubscriptions(
		Long userId		
	);
	
	@Put("/{userId}/subscribe")
	public HttpResponse<String> subscribe(
		Long userId,
		Long hashtagId		
	);
	
	@Put("/{userId}/unsubscribe")
	public HttpResponse<String> unsubscribe(
		Long userId,
		Long hashtagId		
	);
	
	@Get("/{userId}/recomandations?hashtagId={hashtagId}")
	public HttpResponse<Iterable<VideoDTO>> getRecomandationsForHashtag(
		Long userId,
		Long hashtagId		
	);
}
