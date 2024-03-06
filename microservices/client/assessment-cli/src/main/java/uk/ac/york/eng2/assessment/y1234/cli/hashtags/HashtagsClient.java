package uk.ac.york.eng2.assessment.y1234.cli.hashtags;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.assessment.y1234.cli.domain.Hashtag;
import uk.ac.york.eng2.assessment.y1234.cli.dto.HashtagDTO;
import uk.ac.york.eng2.assessment.y1234.cli.dto.VideoDTO;

@Client("${users.url:`http://localhost:8080/hashtags`}")
public interface HashtagsClient {

	@Get("/")
	public HttpResponse<Iterable<Hashtag>> list();
	
	@Get("/{hashtagId}/videos")
	public HttpResponse<Iterable<VideoDTO>> getHashtagVideos(Long hashtagId);
}
