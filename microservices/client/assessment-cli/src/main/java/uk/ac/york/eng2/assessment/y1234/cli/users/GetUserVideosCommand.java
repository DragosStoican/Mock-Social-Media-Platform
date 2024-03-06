package uk.ac.york.eng2.assessment.y1234.cli.users;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import uk.ac.york.eng2.assessment.y1234.cli.domain.User;
import uk.ac.york.eng2.assessment.y1234.cli.domain.Video;
import uk.ac.york.eng2.assessment.y1234.cli.dto.VideoDTO;

@Command(name="get-user-videos", description="Gets a user's videos", mixinStandardHelpOptions = true)
public class GetUserVideosCommand implements Runnable{
	
	@Inject
	private UsersClient client;
	
	@Parameters(index = "0")
	private Long userId;
	
	@Override
	public void run() {
		HttpResponse<Iterable<VideoDTO>> response = client.getUserVideos(userId);
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
