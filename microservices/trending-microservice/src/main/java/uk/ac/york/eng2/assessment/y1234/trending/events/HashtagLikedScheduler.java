package uk.ac.york.eng2.assessment.y1234.trending.events;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import uk.ac.york.eng2.assessment.y1234.trending.dto.VideoDTO;

@Singleton
public class HashtagLikedScheduler {
	
	public static final String DUMMY_HASHTAG = "_";
	public static final Long DUMMY_VIDEOID = 0L;

	@Inject
    private VideoProducer producer;


    @Scheduled(fixedDelay = "1m")
    void sendMessageEveryMinute() {
    	VideoDTO dto = new VideoDTO();
    	dto.setHashtags(DUMMY_HASHTAG);
		producer.videoLiked(DUMMY_VIDEOID, dto);
    }

    @KafkaClient
    public interface VideoProducer {
    	
    	@Topic("video_liked")
    	void videoLiked(@KafkaKey Long videoId, VideoDTO v);
    }
}

