package uk.ac.york.eng2.assessment.y1234.trending.events;
/* protected region Imports on begin */
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import io.micronaut.configuration.kafka.serde.SerdeRegistry;
import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import uk.ac.york.eng2.assessment.y1234.trending.dto.VideoDTO;
/* protected region Imports end */

@Factory
public class HashtagLikedStream {

	public static final String FROM_TOPIC = "video_liked";
	public static final String TO_TOPIC = "hashtag_liked";
	
	@Inject
	SerdeRegistry serdeRegistry;
	
	@Singleton
	public KStream<String, Long> hashtagLikedStream(ConfiguredStreamBuilder builder) {
		/* protected region KafkaStream logic on begin */
		Properties props = builder.getConfiguration();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "trending-microservice");
		props.put(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, StreamsConfig.EXACTLY_ONCE);
		
		KStream<Long, VideoDTO> likedVideosStream = builder
				.stream(FROM_TOPIC, Consumed.with(Serdes.Long(), serdeRegistry.getSerde(VideoDTO.class)));
		
		KStream<String, Long> likedHashtagStream = likedVideosStream
				.flatMapValues((k, v) -> Arrays.asList(v.getHashtags().split(",")))
				.map((k, v) -> new KeyValue<String, Long>(v, k));
		
		likedHashtagStream.to(TO_TOPIC,
				Produced.with(Serdes.String(), Serdes.Long()));
		
		return likedHashtagStream;
		/* protected region KafkaStream logic end */
	}
	
	/* protected region Additional KafkaStream components on begin */
	// Aggregators, transformers, etc	
	/* protected region Additional KafkaStream components end */
}
