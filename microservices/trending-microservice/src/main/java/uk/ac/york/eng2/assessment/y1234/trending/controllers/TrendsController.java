package uk.ac.york.eng2.assessment.y1234.trending.controllers;

import java.util.Iterator;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import jakarta.inject.Inject;
import uk.ac.york.eng2.assessment.y1234.trending.domain.Trend;
import uk.ac.york.eng2.assessment.y1234.trending.dto.TrendDTO;
import uk.ac.york.eng2.assessment.y1234.trending.gen.TrendsControllerInterface;
import uk.ac.york.eng2.assessment.y1234.trending.repositories.TrendRepository;

@Controller("/trending")
public class TrendsController implements TrendsControllerInterface{

	@Inject
	private TrendRepository topRepository;
	
	@Override
	public HttpResponse<Iterable<Trend>> list() {
		return HttpResponse.ok(topRepository.findAll());
	}
	
	@Override
	public HttpResponse<TrendDTO> latestTrend() {
		Iterator<Trend> iter = topRepository.findAll().iterator();
		
		while (iter.hasNext()) {
			Trend trend = (Trend)iter.next();
			if (!iter.hasNext()) {
				TrendDTO dto = new TrendDTO();
				dto.setTop(trend.getTop());
				dto.setHour(trend.getHour());
				return HttpResponse.ok(dto);
			}
		}
		return HttpResponse.status(HttpStatus.NO_CONTENT, String.format("No videos liked yet"));
	}
}
