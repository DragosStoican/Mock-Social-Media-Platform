package uk.ac.york.eng2.assessment.y1234.trending.events;
/* protected region Imports on begin */
import java.util.LinkedHashMap;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;
import uk.ac.york.eng2.assessment.y1234.trending.domain.*;
import uk.ac.york.eng2.assessment.y1234.trending.dto.*;

import uk.ac.york.eng2.assessment.y1234.trending.repositories.TrendRepository;
/* protected region Imports end */

@KafkaListener(groupId = "trending-consumers")
public class TrendingConsumer {
	/* protected region Extra fields/methods on begin */
	@Inject
	TrendRepository trendRepository;
	/* protected region Extra fields/methods end */
	
	/* protected region Consumer updateTrends logic on begin */
	@Topic("trending_hashtags_by_hour")
	public void updateTrends(@KafkaKey WindowedIdentifier window, LinkedHashMap<String, Integer> top10Trends) {
		String start = window.getStart();
		String end = window.getEnd();

		// Only add the latest sliding windows 
		// (this if statement ignores messages produced by "dropped" sliding windows 
		if (!trendRepository.existsByHour(start) && !trendRepository.existsByHour(end)) {
			Trend topEntity = new Trend();
			topEntity.setTop(top10Trends.toString());
			topEntity.setHour(end);
			trendRepository.save(topEntity);
		}
	}
	/* protected region Consumer updateTrends logic end */
	
}
