package uk.ac.york.eng2.assessment.y1234.cli.users;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="view-video", description="User views a video", mixinStandardHelpOptions = true)
public class ViewVideoCommand implements Runnable{

	@Inject 
	UsersClient client;
	
	@Parameters(index = "0")
	private Long userId;
	
	@Parameters(index = "1")
	private Long videoId;

	@Override
	public void run() {
		HttpResponse<String> response = client.viewVideo(userId, videoId);
		System.out.printf("Server responded with status %s: %s%n",
				response.getStatus(), response.getBody().orElse(response.reason()));
	}
	
	
}
