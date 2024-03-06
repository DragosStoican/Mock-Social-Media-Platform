package uk.ac.york.eng2.assessment.y1234.trending;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.TestInputTopic;
import org.apache.kafka.streams.TestOutputTopic;
import org.apache.kafka.streams.TopologyTestDriver;
import org.junit.jupiter.api.Test;

import io.micronaut.configuration.kafka.serde.SerdeRegistry;
import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import uk.ac.york.eng2.assessment.y1234.trending.dto.WindowedIdentifier;
import uk.ac.york.eng2.assessment.y1234.trending.events.TrendingHashtagsStream;

@MicronautTest(environments = "no_streams")
public class TrendingHashtagsStreamTest {

	@Inject
	SerdeRegistry serdeRegistry;
	
	@Inject
	TrendingHashtagsStream stream;
	
	@Test
	public void topologyCheck() {
		final ConfiguredStreamBuilder builder = new ConfiguredStreamBuilder(new Properties());
		stream.trendingHashtagsStream(builder);

		try (TopologyTestDriver testDriver = new TopologyTestDriver(builder.build())) {
			TestInputTopic<String, Long> inputTopic = testDriver.createInputTopic(
				TrendingHashtagsStream.FROM_TOPIC,
				new StringSerializer(), new LongSerializer());

			final List<String> hashtags = Arrays.asList("York", "UK", "CS", "York");
			for (String s : hashtags) {
				inputTopic.pipeInput(s, 1L);
			}

			TestOutputTopic<WindowedIdentifier, LinkedHashMap> outputTopic = testDriver.createOutputTopic(
				TrendingHashtagsStream.TO_TOPIC,
				serdeRegistry.getDeserializer(WindowedIdentifier.class),
				serdeRegistry.getDeserializer(LinkedHashMap.class));

			List<KeyValue<WindowedIdentifier, LinkedHashMap>> keyValues = outputTopic.readKeyValuesToList();
			assertFalse(keyValues.isEmpty());

			// Test if the values get grouped correctly
			LinkedHashMap<String, Integer> values = keyValues.get(keyValues.size() - 1).value;
			assertEquals(3, values.keySet().size());
			assertEquals(2, values.get("York"));
			assertEquals(1, values.get("UK"));
			assertEquals(1, values.get("CS"));
		}
	}
}
