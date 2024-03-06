package uk.ac.york.eng2.assessment.y1234.cli.users;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import uk.ac.york.eng2.assessment.y1234.cli.domain.User;

@Command(name="get-users", description="Gets all the users", mixinStandardHelpOptions = true)
public class GetUsersCommand implements Runnable{

	@Inject
	private UsersClient client;

	@Override
	public void run() {
		HttpResponse<Iterable<User>> response = client.list();
		if (response.getBody().isEmpty()) {
			System.out.println("Server responded with status " + response.getStatus() + ": " + response.reason());
			return;
		}
		Iterable<User> users = response.getBody().get();
		if (!users.iterator().hasNext()) {
			System.err.println("No users found!");
		} else {
			for (User u : users) {
				System.out.println(u);
			}
		}	
	}
}
