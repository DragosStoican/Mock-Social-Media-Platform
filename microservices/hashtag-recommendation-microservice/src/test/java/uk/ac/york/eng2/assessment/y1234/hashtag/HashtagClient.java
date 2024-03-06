package uk.ac.york.eng2.assessment.y1234.hashtag;

import java.util.LinkedHashMap;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.assessment.y1234.hashtag.domain.Hashtag;

@Client("/hashtags")
public interface HashtagClient {
	
	@Get("/")
	public HttpResponse<Iterable<Hashtag>> getHashtags();

	@Get("/{hashtagId}")
	public HttpResponse<LinkedHashMap<String, Integer>> getRecommendedHashtags(Long hashtagId);
}
