package uk.ac.york.eng2.assessment.y1234.cli.subscriptions;

import java.util.List;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import uk.ac.york.eng2.assessment.y1234.cli.domain.Video;
import uk.ac.york.eng2.assessment.y1234.cli.dto.VideoDTO;

@Command(name="get-recommended-videos", description="Lists the top10 recommanded videos for a given user and hashtag", mixinStandardHelpOptions = true)
public class GetRecommandedVideosCommand implements Runnable {
	
	@Inject
	SubscriptionsClient client;
	
	@Parameters(index = "0")
	private Long userId;
	
	@Parameters(index = "1")
	private Long hashtagId;

	@Override
	public void run() {
		HttpResponse<List<VideoDTO>> response = client.getRecomandationsForHashtag(userId, hashtagId);
		if (response.getBody().isEmpty()) {
			System.out.println("Server responded with status " + response.getStatus() + ": " + response.reason());
			return;
		}
		Iterable<VideoDTO> videos = response.getBody().get();
		if (!videos.iterator().hasNext()) {
			System.err.println("No videos found!");
		} else {
			for (VideoDTO v : videos) {
				System.out.println(v);
			}
		}
	}
}
