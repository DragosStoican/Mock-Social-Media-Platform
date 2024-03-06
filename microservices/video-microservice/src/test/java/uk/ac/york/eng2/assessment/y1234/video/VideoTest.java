package uk.ac.york.eng2.assessment.y1234.video;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import uk.ac.york.eng2.assessment.y1234.video.domain.Hashtag;
import uk.ac.york.eng2.assessment.y1234.video.domain.User;
import uk.ac.york.eng2.assessment.y1234.video.domain.Video;
import uk.ac.york.eng2.assessment.y1234.video.dto.VideoDTO;
import uk.ac.york.eng2.assessment.y1234.video.repositories.UserRepository;
import uk.ac.york.eng2.assessment.y1234.video.repositories.VideoRepository;

@MicronautTest(environments = "no_streams")
public class VideoTest {

	@Inject
	VideoRepository videosRepository;
	
	@Inject
	UserRepository usersRepository;
	
	@BeforeEach
	void clean() {
		videosRepository.deleteAll();
	}
	
	@Test
	void testToDto() {
		final String videoTitle = "Top10 reasons ENG2 is great!";
		final String username = "York CS Student";
		final HashSet<String> hashtagStrings = new HashSet<String>(Arrays.asList("CS","York","England"));
		
		User user = new User();
		user.setUsername(username);
		usersRepository.save(user);
		
		Set<Hashtag> hashtags = new HashSet<Hashtag>();
		for (String hashtagName : hashtagStrings) {
			Hashtag newHashtag = new Hashtag();
			newHashtag.setName(hashtagName);
			hashtags.add(newHashtag);
		}
		
		Video video = new Video();
		video.setTitle(videoTitle);
		video.setUser(user);
		video.setHashtags(hashtags);
		videosRepository.save(video);
		final VideoDTO videoDTO = video.toDto();
		
		assertEquals(video.getTitle(), videoDTO.getTitle(), "Title should be fetched correctly");
		assertEquals(video.getUser().getUsername(), videoDTO.getUser(), "User should be fetched correctly");
		for (String s : hashtagStrings) {
			assertTrue(videoDTO.getHashtags().contains(s), "Hashtags should be fetched correctly");
		}
		
	}
	
	@Test
	void testToDtoNoHashtags() {
		final String videoTitle = "Top10 reasons ENG2 is great!";
		final String username = "York CS Student";
		
		User user = new User();
		user.setUsername(username);
		usersRepository.save(user);
		
		Video video = new Video();
		video.setTitle(videoTitle);
		video.setUser(user);
		videosRepository.save(video);
		final VideoDTO videoDTO = video.toDto();
		
		assertEquals(video.getTitle(), videoDTO.getTitle(), "Title should be fetched correctly");
		assertEquals(video.getUser().getUsername(), videoDTO.getUser(), "User should be fetched correctly");
	}
}
