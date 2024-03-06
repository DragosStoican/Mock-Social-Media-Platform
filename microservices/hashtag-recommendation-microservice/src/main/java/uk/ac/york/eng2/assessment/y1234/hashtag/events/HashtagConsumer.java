package uk.ac.york.eng2.assessment.y1234.hashtag.events;
/* protected region Imports on begin */
import java.util.HashSet;
import java.util.Optional;

import javax.transaction.Transactional;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;
import uk.ac.york.eng2.assessment.y1234.hashtag.domain.*;
import uk.ac.york.eng2.assessment.y1234.hashtag.repositories.HashtagRepository;
import uk.ac.york.eng2.assessment.y1234.hashtag.repositories.UserRepository;

/* protected region Imports end */

@KafkaListener(groupId = "hashtag-consumers")
public class HashtagConsumer {
	/* protected region Extra fields/methods on begin */
	@Inject
	UserRepository userRepository;
	
	@Inject
	HashtagRepository hashtagRepository;
	/* protected region Extra fields/methods end */
	
	/* protected region Consumer userSubscribed logic on begin */
	@Transactional
	@Topic("user_subscribed")
	public void userSubscribed(@KafkaKey Long userId, Hashtag hashtag) {		
		// If there is no user we must also create it in this DB
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) {
			user = new User();
			user.setId(userId);
			userRepository.save(user);
			
		}
		
		Optional<Hashtag> oHashtag = hashtagRepository.findById(hashtag.getId());
		if (oHashtag.isEmpty()) {
			Hashtag newHashtag = new Hashtag();
			newHashtag.setId(hashtag.getId());
			newHashtag.setName(hashtag.getName());
			newHashtag.getSubscribers().add(user);
			hashtagRepository.save(newHashtag);
		}
		else {
			Hashtag newHashtag = oHashtag.get();
			newHashtag.getSubscribers().add(user);
			hashtagRepository.update(newHashtag);
		}
	}
	/* protected region Consumer userSubscribed logic end */
	
	/* protected region Consumer userUnsubscribed logic on begin */
	@Transactional
	@Topic("user_unsubscribed")
	public void userUnsubscribed(@KafkaKey Long userId, Hashtag hashtag) {
		// The hashtag and user must already exist at this point or SM would have raised an error
		User user = userRepository.findById(userId).get();
		Hashtag h = hashtagRepository.findById(hashtag.getId()).get();
		h.getSubscribers().remove(user);
		hashtagRepository.update(h);
	}
	/* protected region Consumer userUnsubscribed logic end */
	
}
