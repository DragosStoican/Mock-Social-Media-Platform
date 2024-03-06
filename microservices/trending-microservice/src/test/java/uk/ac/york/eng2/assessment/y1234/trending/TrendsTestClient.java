package uk.ac.york.eng2.assessment.y1234.trending;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.assessment.y1234.trending.domain.Trend;
import uk.ac.york.eng2.assessment.y1234.trending.dto.TrendDTO;

@Client("/trending")
public interface TrendsTestClient {

	@Get("/")
	public HttpResponse<Iterable<Trend>> list();
	
	@Get("/latest")
	public HttpResponse<TrendDTO> latestTrend();
}
