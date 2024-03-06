package uk.ac.york.eng2.assessment.y1234.video.events;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import uk.ac.york.eng2.assessment.y1234.video.dto.*;
import uk.ac.york.eng2.assessment.y1234.video.domain.*;

@KafkaClient
public interface VideoProducer {
	
	@Topic("video_posted")
	public void videoPosted(@KafkaKey Long userId, VideoDTO dto);
	
	@Topic("video_watched")
	public void videoWatched(@KafkaKey Long userId, Long videoId);
	
	@Topic("video_liked")
	public void videoLiked(@KafkaKey Long videoId, VideoDTO dto);
	
	@Topic("video_disliked")
	public void videoDisliked(@KafkaKey Long videoId, VideoDTO dto);
	
	@Topic("user_created")
	public void userCreated(@KafkaKey Long userId, UserDTO dto);
	
}
