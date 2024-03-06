package uk.ac.york.eng2.assessment.y1234.trending;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Properties;

import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.TestInputTopic;
import org.apache.kafka.streams.TestOutputTopic;
import org.apache.kafka.streams.TopologyTestDriver;
import org.junit.jupiter.api.Test;

import io.micronaut.configuration.kafka.serde.SerdeRegistry;
import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import uk.ac.york.eng2.assessment.y1234.trending.dto.VideoDTO;
import uk.ac.york.eng2.assessment.y1234.trending.events.HashtagLikedStream;

@MicronautTest(environments = "no_streams")
public class HashtagLikedStreamTest {

	@Inject
	SerdeRegistry serdeRegistry;
	
	@Inject
	HashtagLikedStream stream;
	
	@Test
	public void topologyCheck() {
		
		final ConfiguredStreamBuilder builder = new ConfiguredStreamBuilder(new Properties());
		stream.hashtagLikedStream(builder);

		try (TopologyTestDriver testDriver = new TopologyTestDriver(builder.build())) {
			TestInputTopic<Long, VideoDTO> inputTopic = testDriver.createInputTopic(
				HashtagLikedStream.FROM_TOPIC,
				new LongSerializer(), serdeRegistry.getSerializer(VideoDTO.class));

			final String hashtagNames = "York,UK,CS";
			VideoDTO dto = new VideoDTO();
			dto.setHashtags(hashtagNames);
			final Long videoId = 1L;
			inputTopic.pipeInput(videoId, dto);

			TestOutputTopic<String, Long> outputTopic = testDriver.createOutputTopic(
				HashtagLikedStream.TO_TOPIC,
				new StringDeserializer(),
				new LongDeserializer());

			List<KeyValue<String, Long>> keyValues = outputTopic.readKeyValuesToList();
			assertFalse(keyValues.isEmpty());

			for (KeyValue<String, Long> kv : keyValues) {
				assertTrue(hashtagNames.contains(kv.key));
			}
		}
	}
}
