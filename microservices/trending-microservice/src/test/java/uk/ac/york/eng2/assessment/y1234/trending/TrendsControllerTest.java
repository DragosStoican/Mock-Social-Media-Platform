package uk.ac.york.eng2.assessment.y1234.trending;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import uk.ac.york.eng2.assessment.y1234.trending.domain.Trend;
import uk.ac.york.eng2.assessment.y1234.trending.dto.TrendDTO;
import uk.ac.york.eng2.assessment.y1234.trending.repositories.TrendRepository;

@MicronautTest(transactional = false, environments = "no_streams")
public class TrendsControllerTest {
	
	@Inject
	TrendsTestClient client;
	
	@Inject
	TrendRepository repo;
	
	@BeforeEach
	void clean() {
		repo.deleteAll();
	}
	
	@Test
	public void testHealthCheck() {
		HttpResponse<Iterable<Trend>> response = client.list();
		assertEquals(HttpStatus.OK, response.status());
	}
	
	@Test
	public void testGetLatestTrend() {
		// Check if NO_CONTENT is returned when no trend exists
		HttpResponse<TrendDTO> r = client.latestTrend();
		assertEquals(HttpStatus.NO_CONTENT, r.getStatus(), "Controller should return NO_CONTENT before the first trend is created");
		
		Trend trend = new Trend();
		trend.setHour("HOUR");
		trend.setTop("TOP");
		repo.save(trend);
		Long trendId = trend.getId();
		
		Trend trend2 = new Trend();
		trend2.setHour("HOUR2");
		trend2.setTop("TOP2");
		repo.save(trend2);
		Long trend2Id = trend.getId();
		
		TrendDTO dto = client.latestTrend().getBody().get();
		assertEquals("HOUR2", dto.getHour(), "Method should return latest trend");
		assertEquals("TOP2", dto.getTop(), "Method should return latest trend");
	}
}
