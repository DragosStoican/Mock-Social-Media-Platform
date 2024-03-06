package uk.ac.york.eng2.assessment.y1234.video.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import jakarta.inject.Inject;
import uk.ac.york.eng2.assessment.y1234.video.domain.Hashtag;
import uk.ac.york.eng2.assessment.y1234.video.domain.Video;
import uk.ac.york.eng2.assessment.y1234.video.dto.HashtagDTO;
import uk.ac.york.eng2.assessment.y1234.video.dto.VideoDTO;
import uk.ac.york.eng2.assessment.y1234.video.gen.HashtagControllerInterface;
import uk.ac.york.eng2.assessment.y1234.video.repositories.HashtagRepository;

@Controller("/hashtags")
public class HashtagController implements HashtagControllerInterface{

	@Inject 
	private HashtagRepository hashtagsRepository;
	
	public HttpResponse<Iterable<Hashtag>> list() {
		return HttpResponse.ok(hashtagsRepository.findAll());
	}
	
	public HttpResponse<HashtagDTO> getHashtag(Long hashtagId) {
		// Get the user, returning 404 if the user id doesn't exist
		Optional<HashtagDTO> oHashtag = hashtagsRepository.findOne(hashtagId);
		if (oHashtag.isEmpty()) {
			return HttpResponse.status(HttpStatus.NOT_FOUND, String.format("Hashtag %d not found", hashtagId));
		}
		return HttpResponse.ok(oHashtag.get());
	}
	
	@Transactional
	public HttpResponse<Iterable<VideoDTO>> getHashtagVideos(Long hashtagId) {
		// Get the user, returning 404 if the user id doesn't exist
		Optional<Hashtag> oHashtag = hashtagsRepository.findById(hashtagId);
		if (oHashtag.isEmpty()) {
			return HttpResponse.status(HttpStatus.NOT_FOUND, String.format("Hashtag %d not found", hashtagId));
		}
		Hashtag hashtag = oHashtag.get();
		
		// Get the user's videos
		Set<Video> videos = hashtag.getVideos();
		List<VideoDTO> videoDTOs = new ArrayList<VideoDTO>();
		for (Video video : videos) {
			videoDTOs.add(video.toDto());
		}
		return HttpResponse.ok(videoDTOs);
	}
}
