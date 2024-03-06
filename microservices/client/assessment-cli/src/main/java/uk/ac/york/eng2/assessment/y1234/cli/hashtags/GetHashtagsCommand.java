package uk.ac.york.eng2.assessment.y1234.cli.hashtags;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import uk.ac.york.eng2.assessment.y1234.cli.domain.Hashtag;

@Command(name="get-hashtags", description="Gets all hashtags", mixinStandardHelpOptions = true)
public class GetHashtagsCommand implements Runnable{

	@Inject
	private HashtagsClient client;
	
	@Override
	public void run() {
		HttpResponse<Iterable<Hashtag>> response = client.list();
		if (response.getBody().isEmpty()) {
			System.out.println("Server responded with status " + response.getStatus() + ": " + response.reason());
			return;
		}
		Iterable<Hashtag> hashtags = response.getBody().get();
		if (!hashtags.iterator().hasNext()) {
			System.err.println("No hashtags found!");
		} else {
			for (Hashtag h : hashtags) {
				System.out.println(h);
			}
		}	
	}
}
