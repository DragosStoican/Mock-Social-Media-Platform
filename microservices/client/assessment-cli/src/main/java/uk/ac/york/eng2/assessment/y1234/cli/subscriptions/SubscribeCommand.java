package uk.ac.york.eng2.assessment.y1234.cli.subscriptions;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="subscribe", description="Subscribe a user to a hashtag", mixinStandardHelpOptions = true)
public class SubscribeCommand implements Runnable {

	@Inject
	SubscriptionsClient client;
	
	@Parameters(index = "0")
	private Long userId;
	
	@Parameters(index = "1")
	private Long hashtagId;

	@Override
	public void run() {
		HttpResponse<String> response = client.subscribe(userId, hashtagId);
		System.out.printf("Server responded with status %s: %s%n",
				response.getStatus(), response.getBody().orElse(response.reason()));		
	}
}
