package uk.ac.york.eng2.assessment.y1234.video.clients;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.assessment.y1234.video.domain.Hashtag;
import uk.ac.york.eng2.assessment.y1234.video.dto.HashtagDTO;
import uk.ac.york.eng2.assessment.y1234.video.dto.VideoDTO;

@Client("/hashtags")
public interface HashtagsClient {

	@Get("/")
	Iterable<Hashtag> list();
	
	@Get("/{hashtagId}/videos")
	public Iterable<VideoDTO> getHashtagVideos(long hashtagId);
	
	@Get("/{hashtagId}")
	public HttpResponse<HashtagDTO> getHashtag(Long hashtagId);
}
