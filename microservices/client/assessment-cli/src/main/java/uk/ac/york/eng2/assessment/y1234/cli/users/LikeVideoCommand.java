package uk.ac.york.eng2.assessment.y1234.cli.users;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name="like-video", description="User likes a video", mixinStandardHelpOptions = true)
public class LikeVideoCommand implements Runnable{

	@Inject 
	UsersClient client;
	
	@Parameters(index = "0")
	private Long userId;
	
	@Parameters(index = "1")
	private Long videoId;

	@Override
	public void run() {
		HttpResponse<String> response = client.likeVideo(userId, videoId);
		System.out.printf("Server responded with status %s: %s%n",
				response.getStatus(), response.getBody().orElse(response.reason()));
	}
	
	
}
