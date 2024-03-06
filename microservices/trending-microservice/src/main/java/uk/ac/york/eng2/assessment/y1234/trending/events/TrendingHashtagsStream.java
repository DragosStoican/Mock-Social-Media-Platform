package uk.ac.york.eng2.assessment.y1234.trending.events;
/* protected region Imports on begin */
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Aggregator;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.SlidingWindows;

import io.micronaut.configuration.kafka.serde.SerdeRegistry;
import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import uk.ac.york.eng2.assessment.y1234.trending.dto.WindowedIdentifier;
/* protected region Imports end */

@Factory
public class TrendingHashtagsStream {

	public static final String FROM_TOPIC = "hashtag_liked";
	public static final String TO_TOPIC = "trending_hashtags_by_hour";
	
	@Inject
	SerdeRegistry serdeRegistry;
	
	@Singleton
	public KStream<WindowedIdentifier, LinkedHashMap> trendingHashtagsStream(ConfiguredStreamBuilder builder) {
		/* protected region KafkaStream logic on begin */
		Properties props = builder.getConfiguration();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "trending-microservice");
		props.put(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, StreamsConfig.EXACTLY_ONCE);
		
		KStream<String, Long> likedHashtagStream = builder
				.stream(FROM_TOPIC, Consumed.with(Serdes.String(), serdeRegistry.getSerde(Long.class)));
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		KStream<WindowedIdentifier, LinkedHashMap> likedHashtagsByHour = likedHashtagStream
				.map((k, v) -> new KeyValue<String, String>("_", k))
				.groupByKey(Grouped.with(Serdes.String(), Serdes.String()))
				.windowedBy(SlidingWindows.withTimeDifferenceAndGrace(Duration.ofMinutes(60), Duration.ofSeconds(60)))
				.aggregate(
						() -> new HashMap<String, Integer>(),
						(key, value, aggregate) -> new TopAggregator().apply(key, value, aggregate),
						Materialized.with(Serdes.String(), serdeRegistry.getSerde(HashMap.class))
						)
				.toStream()
				.selectKey((k, v) -> new WindowedIdentifier(milisToString(k.window().start()), milisToString(k.window().end())))
				.mapValues((k, v) -> getTopNSorted(v, 10));
		
		likedHashtagsByHour.to(TO_TOPIC,
				Produced.with(serdeRegistry.getSerde(WindowedIdentifier.class), serdeRegistry.getSerde(LinkedHashMap.class)));
		
		return likedHashtagsByHour;
		/* protected region KafkaStream logic end */
	}
	
	/* protected region Additional KafkaStream components on begin */
	private class TopAggregator implements Aggregator<String, String, HashMap<String, Integer>> {
	    @Override
	    public HashMap<String, Integer> apply(String key, String value, HashMap<String, Integer> aggregate) {
	        // Ignore dummy hashtags
			if (value.equals(HashtagLikedScheduler.DUMMY_HASHTAG))
				return aggregate;
			
			Integer likes = 1;
			Integer curLikes = aggregate.get(value);
			if (curLikes == null)
				aggregate.put(value, likes);
			else
				aggregate.put(value, likes + curLikes);
			
			return aggregate;
	    }
	}
	
	private String milisToString(long millis) {		
		return Instant
				.ofEpochMilli(millis)
				.atZone(ZoneId.systemDefault())
				.format(DateTimeFormatter.ofPattern("MM/dd/yyyy, HH:mm:ss"))
				.toString();
	}
	
	private LinkedHashMap<String, Integer> getTopNSorted(HashMap<String, Integer> top, int n) {
		List<Entry<String, Integer>> lst = new ArrayList<>(top.entrySet());
		lst.sort(Entry.comparingByValue());
		Collections.reverse(lst);
		
		List<Entry<String, Integer>> lstN = lst.subList(0, Math.min(lst.size(), n));
	
		LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        for (Entry<String, Integer> entry : lstN) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
	}
	/* protected region Additional KafkaStream components end */
}
