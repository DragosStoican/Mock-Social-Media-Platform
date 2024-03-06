package uk.ac.york.eng2.assessment.y1234.cli.trends;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.assessment.y1234.cli.dto.TrendDTO;

@Client("${users.url:`http://localhost:8081/trending`}")
public interface TrendClient {
	
	@Get("/latest")
	public HttpResponse<TrendDTO> latestTrend();
}