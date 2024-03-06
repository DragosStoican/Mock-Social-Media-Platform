package uk.ac.york.eng2.assessment.y1234.trending.gen;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import uk.ac.york.eng2.assessment.y1234.trending.dto.*;
import uk.ac.york.eng2.assessment.y1234.trending.domain.*;

public interface TrendsControllerInterface {

	@Get("/")
	public HttpResponse<?> list(
		
	);
	
	@Get("/latest")
	public HttpResponse<?> latestTrend(
		
	);
	
}