package uk.ac.york.eng2.assessment.y1234.video.clients;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

@Client("/videos")
public interface VideosClient {
	
	@Get("/")
	Iterable<uk.ac.york.eng2.assessment.y1234.video.domain.Video> list();
}
