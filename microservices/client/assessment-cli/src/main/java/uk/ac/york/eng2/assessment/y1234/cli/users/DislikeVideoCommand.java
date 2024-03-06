package uk.ac.york.eng2.assessment.y1234.cli.users;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="dislike-video", description="User dislikes a video", mixinStandardHelpOptions = true)
public class DislikeVideoCommand implements Runnable{

	@Inject 
	UsersClient client;
	
	@Parameters(index = "0")
	private Long userId;
	
	@Parameters(index = "1")
	private Long videoId;

	@Override
	public void run() {
		HttpResponse<String> response = client.dislikeVideo(userId, videoId);
		System.out.printf("Server responded with status %s: %s%n",
				response.getStatus(), response.getBody().orElse(response.reason()));
	}
	
	
}
