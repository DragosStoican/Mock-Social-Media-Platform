package uk.ac.york.eng2.assessment.y1234.cli;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.env.Environment;

public class TestUserCommands {
	
    @Test
    public void testWithCommandLineOption() throws Exception {
        try (ApplicationContext ctx = ApplicationContext.run(Environment.CLI, Environment.TEST)) {
        	ByteArrayOutputStream baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            
            PicocliRunner.run(AssessmentCliCommand.class, ctx, new String[] { "add-user", "CS Student"});
            assertTrue(baos.toString().contains("CREATED"), "The new user was created");

            baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            PicocliRunner.run(AssessmentCliCommand.class, ctx, new String[] { "get-users" });
            String newOutput = baos.toString().trim();
			String[] newLines = newOutput.split(System.lineSeparator());
			
			assertTrue(newLines.length > 0, "There is at least one line of output");
			
			baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            PicocliRunner.run(AssessmentCliCommand.class, ctx, new String[] { "add-video", "1", "CS video", "-h CS"});
            newOutput = baos.toString().trim();
			newLines = newOutput.split(System.lineSeparator());
			
			assertTrue(baos.toString().contains("CREATED"), "The new video was created");
			
			baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            PicocliRunner.run(AssessmentCliCommand.class, ctx, new String[] { "get-videos" });
            newOutput = baos.toString().trim();
			newLines = newOutput.split(System.lineSeparator());
			
			assertTrue(newLines.length > 0, "There is at least one line of output");
			
			baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            PicocliRunner.run(AssessmentCliCommand.class, ctx, new String[] { "get-hashtags" });
            newOutput = baos.toString().trim();
			newLines = newOutput.split(System.lineSeparator());
			
			assertTrue(newLines.length > 0, "There is at least one line of output");
			
			baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            PicocliRunner.run(AssessmentCliCommand.class, ctx, new String[] { "get-user-videos", "1"});
            newOutput = baos.toString().trim();
			newLines = newOutput.split(System.lineSeparator());
			
			assertTrue(newLines.length > 0, "There is at least one line of output");
			
			baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            PicocliRunner.run(AssessmentCliCommand.class, ctx, new String[] { "get-hashtag-videos", "2"});
            newOutput = baos.toString().trim();
			newLines = newOutput.split(System.lineSeparator());
			
			assertTrue(newLines.length > 0, "There is at least one line of output");
			
			baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            PicocliRunner.run(AssessmentCliCommand.class, ctx, new String[] { "view-video", "1", "3"});
            newOutput = baos.toString().trim();
			newLines = newOutput.split(System.lineSeparator());
			
			assertTrue(baos.toString().contains("OK"), "The video was viewed");
			
			baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            PicocliRunner.run(AssessmentCliCommand.class, ctx, new String[] { "like-video", "1", "3"});
            newOutput = baos.toString().trim();
			newLines = newOutput.split(System.lineSeparator());
			
			assertTrue(baos.toString().contains("OK"), "The video was liked");
			
			baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            PicocliRunner.run(AssessmentCliCommand.class, ctx, new String[] { "get-trending-hashtags"});
            newOutput = baos.toString().trim();
			newLines = newOutput.split(System.lineSeparator());
			
			assertTrue(newLines.length > 0, "There is at least one line of output");
			
			baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            PicocliRunner.run(AssessmentCliCommand.class, ctx, new String[] { "get-trending-hashtags"});
            newOutput = baos.toString().trim();
			newLines = newOutput.split(System.lineSeparator());
			
			assertTrue(newLines.length > 0, "There is at least one line of output");
			
			baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            PicocliRunner.run(AssessmentCliCommand.class, ctx, new String[] { "subscribe", "1", "2"});
            newOutput = baos.toString().trim();
			newLines = newOutput.split(System.lineSeparator());
			
			assertTrue(baos.toString().contains("OK"), "User subscribed");
			
			baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            PicocliRunner.run(AssessmentCliCommand.class, ctx, new String[] { "unsubscribe", "1", "2"});
            newOutput = baos.toString().trim();
			newLines = newOutput.split(System.lineSeparator());
			
			assertTrue(baos.toString().contains("OK"), "User unsubscribed");
			
			PicocliRunner.run(AssessmentCliCommand.class, ctx, new String[] { "subscribe", "1", "2"});
			PicocliRunner.run(AssessmentCliCommand.class, ctx, new String[] { "add-video", "1", "CS video", "-h CS"});
			
			baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            PicocliRunner.run(AssessmentCliCommand.class, ctx, new String[] { "get-recommended-videos", "1", "2"});
            newOutput = baos.toString().trim();
			newLines = newOutput.split(System.lineSeparator());
			
			assertTrue(newLines.length > 0, "There is at least one line of output");
			
			baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            PicocliRunner.run(AssessmentCliCommand.class, ctx, new String[] { "get-all-recommended-videos", "1", "2"});
            newOutput = baos.toString().trim();
			newLines = newOutput.split(System.lineSeparator());
			
			assertTrue(newLines.length > 0, "There is at least one line of output");

			baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            PicocliRunner.run(AssessmentCliCommand.class, ctx, new String[] { "get-recommended-hashtags", "2"});
            newOutput = baos.toString().trim();
			newLines = newOutput.split(System.lineSeparator());
			
			assertTrue(newLines.length > 0, "There is at least one line of output");
        }
    }
    
}
