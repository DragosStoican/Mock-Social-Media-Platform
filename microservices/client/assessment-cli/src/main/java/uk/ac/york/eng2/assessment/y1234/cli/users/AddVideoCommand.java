package uk.ac.york.eng2.assessment.y1234.cli.users;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import uk.ac.york.eng2.assessment.y1234.cli.dto.VideoDTO;

@Command(name="add-video", description="Adds a video for a user", mixinStandardHelpOptions = true)
public class AddVideoCommand implements Runnable{

	@Inject UsersClient client;
	
	@Parameters(index = "0")
	private Long userId;
	
	@Parameters(index = "1")
	private String title;
	
	@Option(names = {"-h", "--hashtag"}, description = "Comma separated video hashtags")
	private String hashtags;
	
	@Override
	public void run() {
		VideoDTO dto = new VideoDTO();
		dto.setTitle(title);
		if (hashtags != null) {
			dto.setHashtags(hashtags);
		}
		
		HttpResponse<String> response = client.addVideo(userId, dto);
		System.out.println("Server responded with: " + response.getStatus());
	}
}
