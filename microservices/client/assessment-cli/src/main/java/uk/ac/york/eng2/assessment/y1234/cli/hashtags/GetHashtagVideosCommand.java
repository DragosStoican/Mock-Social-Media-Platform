package uk.ac.york.eng2.assessment.y1234.cli.hashtags;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import uk.ac.york.eng2.assessment.y1234.cli.dto.HashtagDTO;
import uk.ac.york.eng2.assessment.y1234.cli.dto.VideoDTO;

@Command(name="get-hashtag-videos", description="Gets a hashtag's videos", mixinStandardHelpOptions = true)
public class GetHashtagVideosCommand implements Runnable{
	
	@Inject
	private HashtagsClient client;
	
	@Parameters(index = "0")
	private Long hashtagId;
	
	@Override
	public void run() {
		HttpResponse<Iterable<VideoDTO>> response = client.getHashtagVideos(hashtagId);
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
