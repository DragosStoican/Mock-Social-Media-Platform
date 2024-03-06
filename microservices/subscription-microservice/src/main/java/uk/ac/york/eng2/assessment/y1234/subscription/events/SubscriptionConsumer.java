package uk.ac.york.eng2.assessment.y1234.subscription.events;
/* protected region Imports on begin */
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.Set;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import uk.ac.york.eng2.assessment.y1234.subscription.domain.*;
import uk.ac.york.eng2.assessment.y1234.subscription.dto.*;
import uk.ac.york.eng2.assessment.y1234.subscription.repositories.HashtagRepository;
import uk.ac.york.eng2.assessment.y1234.subscription.repositories.UserRepository;
import uk.ac.york.eng2.assessment.y1234.subscription.repositories.VideoRepository;
/* protected region Imports end */

@KafkaListener(groupId = "subscription-consumers")
public class SubscriptionConsumer {
	/* protected region Extra fields/methods on begin */
	@Inject
	UserRepository userRepository;
	
	@Inject
	HashtagRepository hashtagRepository;
	
	@Inject
	VideoRepository videoRepository;
	/* protected region Extra fields/methods end */
	
	/* protected region Consumer userCreated logic on begin */
	@Topic("user_created")
	public void userCreated(@KafkaKey Long userId, UserDTO dto) {
		User user = new User();
		user.setUsername(dto.getUsername());
		userRepository.save(user);		
	}
	/* protected region Consumer userCreated logic end */
	
	/* protected region Consumer videoPosted logic on begin */
	@Topic("video_posted")
	public void videoPosted(@KafkaKey Long userId, VideoDTO dto) {
		Video video = new Video();
		video.setTitle(dto.getTitle());
		
		// Find the user that created the video
		User u = userRepository.findById(userId).get();
		video.setUser(u);
		
		// Create the set of hashtags from the given hashtag names
		Set<Hashtag> hashtags = new HashSet<Hashtag>();
		if (dto.getHashtags() != null) {
			Set<String> hashtagsSet = new HashSet<String>(
					Arrays.asList(dto.getHashtags().split(",")));
			
			for (String hashtagName : hashtagsSet) {
				Optional<Hashtag> oHashtag = hashtagRepository.findByName(hashtagName);
				// If the hashtag doesn't exist, we must create it
				if (oHashtag.isEmpty()) {
					Hashtag newHashtag = new Hashtag();
					newHashtag.setName(hashtagName);
					hashtagRepository.save(newHashtag);
					hashtags.add(newHashtag);
				}
				// If it does exist, we add it to the current video
				else
					hashtags.add(oHashtag.get());
			}
		}
		video.setHashtags(hashtags);
		videoRepository.save(video);
	}
	/* protected region Consumer videoPosted logic end */
	
	/* protected region Consumer videoViewed logic on begin */
	@Topic("video_watched")
	public void videoViewed(@KafkaKey Long userId, Long videoId) {
		// These must exist or VM would have raised an error
		User user = userRepository.findById(userId).get();
		Video video = videoRepository.findById(videoId).get();
		
		// Add the video to the user's viewed videos
		if (user.getViewedVideos().add(video)) {
			userRepository.update(user);
		}
		
		Long currentViews = video.getViews();
		video.setViews(currentViews + 1);
		videoRepository.update(video);
	}
	/* protected region Consumer videoViewed logic end */
	
}
