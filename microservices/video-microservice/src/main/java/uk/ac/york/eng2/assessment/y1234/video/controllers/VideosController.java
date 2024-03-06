package uk.ac.york.eng2.assessment.y1234.video.controllers;

import javax.transaction.Transactional;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import jakarta.inject.Inject;
import uk.ac.york.eng2.assessment.y1234.video.domain.Video;
import uk.ac.york.eng2.assessment.y1234.video.gen.VideosControllerInterface;
import uk.ac.york.eng2.assessment.y1234.video.repositories.VideoRepository;

@Controller("/videos")
public class VideosController implements VideosControllerInterface{

	@Inject
	private VideoRepository videosRepository;
	
	@Transactional
	public HttpResponse<Iterable<Video>> list() {
		return HttpResponse.ok(videosRepository.findAll());
	}
}
