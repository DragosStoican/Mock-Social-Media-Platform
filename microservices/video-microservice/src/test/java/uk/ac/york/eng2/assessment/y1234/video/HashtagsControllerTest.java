package uk.ac.york.eng2.assessment.y1234.video;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import uk.ac.york.eng2.assessment.y1234.video.clients.HashtagsClient;
import uk.ac.york.eng2.assessment.y1234.video.domain.Hashtag;
import uk.ac.york.eng2.assessment.y1234.video.domain.User;
import uk.ac.york.eng2.assessment.y1234.video.domain.Video;
import uk.ac.york.eng2.assessment.y1234.video.dto.HashtagDTO;
import uk.ac.york.eng2.assessment.y1234.video.dto.VideoDTO;
import uk.ac.york.eng2.assessment.y1234.video.repositories.HashtagRepository;
import uk.ac.york.eng2.assessment.y1234.video.repositories.UserRepository;
import uk.ac.york.eng2.assessment.y1234.video.repositories.VideoRepository;

@MicronautTest(transactional = false, environments = "no_streams")
public class HashtagsControllerTest {

	@Inject
	HashtagsClient hashtagsClient;
	
	@Inject
	HashtagRepository hashtagsRepository;
	
	@Inject
	UserRepository usersRepository;
	
	@Inject
	VideoRepository videosRepository;
	
	@BeforeEach
	void clean() {
		hashtagsRepository.deleteAll();
		usersRepository.deleteAll();
		videosRepository.deleteAll();
	}
	
	@Test
	public void noHashtags() {
		Iterable<Hashtag> iterHashtags = hashtagsClient.list();
		assertFalse(iterHashtags.iterator().hasNext(),
				 "Service should not list any hashtags initially");
	}
	
	@Test
	public void getHashtags() {
		Hashtag hashtag = new Hashtag();
		hashtag.setName("Test hashtag");
		hashtagsRepository.save(hashtag);
		
		Iterable<Hashtag> iterHashtags = hashtagsClient.list();
		assertTrue(iterHashtags.iterator().hasNext(),
				 "Service should list created hashtags");
	}
	
	@Test
	public void getHashtag() {
		final String name = "CS";
		
		Hashtag hashtag = new Hashtag();
		hashtag.setName(name);
		hashtagsRepository.save(hashtag);
		final Long hashtagId = hashtag.getId();
		
		HttpResponse<HashtagDTO> response = hashtagsClient.getHashtag(hashtagId);
		assertTrue(response.getBody().isPresent(), "Hashtag should be present");
		
		HashtagDTO hashtagDTO = response.getBody().get();
		assertEquals(hashtag.getName(), hashtagDTO.getName(), "Name should be fetched correctly");
		
		response = hashtagsClient.getHashtag(99L);
		assertEquals(HttpStatus.NOT_FOUND, response.status(), "Controller should return NOT_FOUND if hashtag doesn't exist");
	}
	
	@Test
	public void getHashtagVideos() {
		final String videoTitle = "Top10 reasons ENG2 is great!";
		final String hashtagName = "CS";
		final String username = "York CS Student";
		
		User user = new User();
		user.setUsername(username);
		usersRepository.save(user);
		
		Hashtag hashtag = new Hashtag();
		hashtag.setName(hashtagName);
		hashtagsRepository.save(hashtag);
		final Long hashtagId = hashtag.getId();
		Set<Hashtag> hashtags = new HashSet<Hashtag>();
		hashtags.add(hashtag);
		
		Video video = new Video();
		video.setTitle(videoTitle);
		video.setUser(user);
		video.setHashtags(hashtags);
		videosRepository.save(video);
		
		//Calling with an invalid id should return null
		assertEquals(null, hashtagsClient.getHashtagVideos(99L));
		
		// Check that the hashtags are listed
		List<VideoDTO> v = iterableToList(hashtagsClient.getHashtagVideos(hashtagId));
		assertEquals(1, v.size(), "Hashtag should have 1 video");
		assertEquals(videoTitle, v.get(0).getTitle(), "Video should be titled " + videoTitle);
	}
	
	private <T> List<T> iterableToList(Iterable<T> iterable) {
		List<T> l = new ArrayList<>();
		iterable.forEach(l::add);
		return l;
	}
}
