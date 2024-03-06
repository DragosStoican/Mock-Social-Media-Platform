package uk.ac.york.eng2.assessment.y1234.cli.hashtags.recommandation;

import java.util.LinkedHashMap;
import java.util.Map;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="get-recommended-hashtags", description="For a given hashtag, lists the top 10 hashtags its subscribers subscribe to as well", mixinStandardHelpOptions = true)
public class GetRecommendedHashtagsCommand implements Runnable {

	@Inject
	private HastagRecommandationClient client;
	
	@Parameters(index = "0")
	private Long hashtagId;
	
	@Override
	public void run() {
		HttpResponse<LinkedHashMap<String, Integer>> response = client.getRecommendedHashtags(hashtagId);
		if (response.getBody().isEmpty()) {
			System.out.println("Server responded with status " + response.getStatus() + ":" + response.reason());
			return;
		}
		
		LinkedHashMap<String, Integer> hashtags = response.getBody().get();
		if (hashtags.keySet().size() == 0) {
			System.err.println("No hashtags found!");
		} else {
			for (Map.Entry<String, Integer> e : hashtags.entrySet()) {
				System.out.println("Hashtag " + e.getKey() + " has " + e.getValue() + " common subscriber(s)");
			}
		}
		
	}
}
