package uk.ac.york.eng2.assessment.y1234.cli.subscriptions;

import java.util.List;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.assessment.y1234.cli.domain.Hashtag;
import uk.ac.york.eng2.assessment.y1234.cli.domain.User;
import uk.ac.york.eng2.assessment.y1234.cli.domain.Video;
import uk.ac.york.eng2.assessment.y1234.cli.dto.VideoDTO;

@Client("${users.url:`http://localhost:8082/user-subscriptions`}")
public interface SubscriptionsClient {
	
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
	public HttpResponse<List<VideoDTO>> getRecomandationsForHashtag(
		Long userId,
		Long hashtagId		
	);
}
