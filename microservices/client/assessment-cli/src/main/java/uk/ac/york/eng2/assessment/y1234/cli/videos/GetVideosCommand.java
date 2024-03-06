package uk.ac.york.eng2.assessment.y1234.cli.videos;


import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import uk.ac.york.eng2.assessment.y1234.cli.domain.User;
import uk.ac.york.eng2.assessment.y1234.cli.domain.Video;

@Command(name="get-videos", description="Gets all the videos", mixinStandardHelpOptions = true)
public class GetVideosCommand implements Runnable{
	
	@Inject
	private VideosClient client;
	
	@Override
	public void run() {
		HttpResponse<Iterable<Video>> response = client.list();
		if (response.getBody().isEmpty()) {
			System.out.println("Server responded with status " + response.getStatus() + ": " + response.reason());
			return;
		}
		Iterable<Video> videos = response.getBody().get();
		if (!videos.iterator().hasNext()) {
			System.err.println("No videos found!");
		} else {
			for (Video v : videos) {
				System.out.println(v);
			}
		}		
	}
}
