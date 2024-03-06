package uk.ac.york.eng2.assessment.y1234.cli;

import io.micronaut.configuration.picocli.PicocliRunner;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import uk.ac.york.eng2.assessment.y1234.cli.hashtags.GetHashtagVideosCommand;
import uk.ac.york.eng2.assessment.y1234.cli.hashtags.GetHashtagsCommand;
import uk.ac.york.eng2.assessment.y1234.cli.hashtags.recommandation.GetRecommendedHashtagsCommand;
import uk.ac.york.eng2.assessment.y1234.cli.subscriptions.GetAllRecommandedVideosCommand;
import uk.ac.york.eng2.assessment.y1234.cli.subscriptions.GetRecommandedVideosCommand;
import uk.ac.york.eng2.assessment.y1234.cli.subscriptions.SubscribeCommand;
import uk.ac.york.eng2.assessment.y1234.cli.subscriptions.UnsubscribeCommand;
import uk.ac.york.eng2.assessment.y1234.cli.trends.GetTrendingHashtagsCommand;
import uk.ac.york.eng2.assessment.y1234.cli.users.AddUserCommand;
import uk.ac.york.eng2.assessment.y1234.cli.users.AddVideoCommand;
import uk.ac.york.eng2.assessment.y1234.cli.users.DislikeVideoCommand;
import uk.ac.york.eng2.assessment.y1234.cli.users.GetUserVideosCommand;
import uk.ac.york.eng2.assessment.y1234.cli.users.GetUsersCommand;
import uk.ac.york.eng2.assessment.y1234.cli.users.LikeVideoCommand;
import uk.ac.york.eng2.assessment.y1234.cli.users.ViewVideoCommand;
import uk.ac.york.eng2.assessment.y1234.cli.videos.GetVideosCommand;

@Command(name = "assessment-cli", description = "...",
mixinStandardHelpOptions = true,
subcommands = {
		AddUserCommand.class, GetUsersCommand.class, // User commands
		AddVideoCommand.class, // Add video command
		GetVideosCommand.class, GetHashtagsCommand.class, // Helper commands
		GetUserVideosCommand.class,  GetHashtagVideosCommand.class, // Get videos by hashtag/user
		ViewVideoCommand.class, LikeVideoCommand.class, DislikeVideoCommand.class, // View, like, dislike video 
		GetTrendingHashtagsCommand.class, // Get trending hashtags
		SubscribeCommand.class, UnsubscribeCommand.class,  // Subscribe/unsubscribe to hashtag
		GetRecommandedVideosCommand.class, GetAllRecommandedVideosCommand.class, // Get recommended videos for subscription
		GetRecommendedHashtagsCommand.class // Get recommended hashtags for a given hashtag
})
public class AssessmentCliCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(AssessmentCliCommand.class, args);
    }

    public void run() {
        // business logic here
        if (verbose) {
            System.out.println("Hi!");
        }
    }
}
