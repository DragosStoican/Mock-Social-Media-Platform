package uk.ac.york.eng2.assessment.y1234.cli.hashtags.recommandation;

import java.util.LinkedHashMap;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.assessment.y1234.cli.domain.Hashtag;

@Client("${users.url:`http://localhost:8083/hashtags`}")
public interface HastagRecommandationClient {
	
	@Get("/{hashtagId}")
	public HttpResponse<LinkedHashMap<String, Integer>> getRecommendedHashtags(Long hashtagId);
}
