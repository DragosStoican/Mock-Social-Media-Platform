package uk.ac.york.eng2.assessment.y1234.video;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import uk.ac.york.eng2.assessment.y1234.video.clients.VideosClient;
import uk.ac.york.eng2.assessment.y1234.video.domain.Video;
import uk.ac.york.eng2.assessment.y1234.video.repositories.VideoRepository;

@MicronautTest(transactional = false, environments = "no_streams")
public class VideosControllerTest {

	@Inject
	VideosClient videosClient;
	
	@Inject
	VideoRepository videosRepository;
	
	@BeforeEach
	void clean() {
		videosRepository.deleteAll();
	}
	
	@Test
	void noVideos() {
		Iterable<Video> iterVideos = videosClient.list();
		assertFalse(iterVideos.iterator().hasNext(),
				 "Service should not list any videos initially");
	}
	
	@Test
	void getVideos( ) {
		Video video = new Video();
		video.setTitle("Test video");
		videosRepository.save(video);
		
		Iterable<Video> iterVideos = videosClient.list();
		assertTrue(iterVideos.iterator().hasNext(),
				 "Service should list created videos");
	}
}
