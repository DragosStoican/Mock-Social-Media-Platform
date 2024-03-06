package uk.ac.york.eng2.assessment.y1234.cli.subscriptions;

import java.util.List;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import uk.ac.york.eng2.assessment.y1234.cli.domain.Hashtag;
import uk.ac.york.eng2.assessment.y1234.cli.domain.Video;
import uk.ac.york.eng2.assessment.y1234.cli.dto.VideoDTO;

@Command(name="get-all-recommended-videos", description="Lists the top10 recommanded videos for a given user and all its subscriptions", mixinStandardHelpOptions = true)
public class GetAllRecommandedVideosCommand implements Runnable {
	
	@Inject
	SubscriptionsClient client;
	
	@Parameters(index = "0")
	private Long userId;

	@Override
	public void run() {
		HttpResponse<Iterable<Hashtag>> subscriptionsResponse = client.getSubscriptions(userId);
		if (subscriptionsResponse.getBody().isEmpty()) {
			System.out.println("Server responded with status " + subscriptionsResponse.getStatus() + ": " + subscriptionsResponse.reason());
			return;
		}
		Iterable<Hashtag> subscriptions = subscriptionsResponse.getBody().get();
		
		if (!subscriptions.iterator().hasNext()) {
			System.out.println("User not subscribed to any hashtag");
			return;
		}
		for (Hashtag h : subscriptions) {
			System.out.println("Top 10 videos for hashtag " + h.getName() + ":");
			HttpResponse<List<VideoDTO>> response = client.getRecomandationsForHashtag(userId, h.getId());
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
}
