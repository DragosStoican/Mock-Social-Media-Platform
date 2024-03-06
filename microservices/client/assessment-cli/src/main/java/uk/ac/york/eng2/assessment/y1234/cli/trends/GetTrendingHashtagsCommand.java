package uk.ac.york.eng2.assessment.y1234.cli.trends;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import uk.ac.york.eng2.assessment.y1234.cli.domain.Trend;
import uk.ac.york.eng2.assessment.y1234.cli.domain.User;
import uk.ac.york.eng2.assessment.y1234.cli.dto.TrendDTO;

@Command(name="get-trending-hashtags", description="Gets the current top10 trending hashtags", mixinStandardHelpOptions = true)
public class GetTrendingHashtagsCommand implements Runnable{

	@Inject
	TrendClient client;
	
	@Override
	public void run() {
		HttpResponse<TrendDTO> response = client.latestTrend();
		if (response.getBody().isEmpty()) {
			System.out.println("Server responded with status " + response.getStatus() + ": " + response.reason());
			return;
		}
		
		TrendDTO trend = response.getBody().get();
		System.out.print(trend);
	}

}
