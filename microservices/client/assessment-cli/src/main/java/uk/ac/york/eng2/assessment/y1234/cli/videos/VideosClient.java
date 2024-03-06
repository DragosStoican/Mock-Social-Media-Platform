package uk.ac.york.eng2.assessment.y1234.cli.videos;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.assessment.y1234.cli.domain.Video;
import uk.ac.york.eng2.assessment.y1234.cli.dto.VideoDTO;

@Client("${users.url:`http://localhost:8080/videos`}")
public interface VideosClient {
	
	@Get("/")
	public HttpResponse<Iterable<Video>> list();
}
