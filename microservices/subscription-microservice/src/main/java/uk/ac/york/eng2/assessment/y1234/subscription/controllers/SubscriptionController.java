package uk.ac.york.eng2.assessment.y1234.subscription.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import uk.ac.york.eng2.assessment.y1234.subscription.domain.Hashtag;
import uk.ac.york.eng2.assessment.y1234.subscription.domain.User;
import uk.ac.york.eng2.assessment.y1234.subscription.domain.Video;
import uk.ac.york.eng2.assessment.y1234.subscription.dto.VideoDTO;
import uk.ac.york.eng2.assessment.y1234.subscription.events.SubscriptionProducer;
import uk.ac.york.eng2.assessment.y1234.subscription.gen.SubscriptionControllerInterface;
import uk.ac.york.eng2.assessment.y1234.subscription.repositories.HashtagRepository;
import uk.ac.york.eng2.assessment.y1234.subscription.repositories.UserRepository;
import uk.ac.york.eng2.assessment.y1234.subscription.repositories.VideoRepository;

@Controller("/user-subscriptions")
public class SubscriptionController implements SubscriptionControllerInterface {

	@Inject
	UserRepository userRepository;
	
	@Inject
	HashtagRepository hashtagRepository;
	
	@Inject
	VideoRepository videoRepository;
	
	@Inject
	SubscriptionProducer producer;
	
	@Override
	public HttpResponse<Iterable<User>> getUsers() {
		return HttpResponse.ok(userRepository.findAll());
	}

	@Override
	public HttpResponse<Iterable<Hashtag>> getSubscriptions(Long userId) {
		// Get the user, returning null if the user id doesn't exist
		Optional<User> oUser = userRepository.findById(userId);
		if (oUser.isEmpty()) {
			return HttpResponse.status(HttpStatus.NOT_FOUND, String.format("User %d not found", userId));
		}
		User user = oUser.get();
		
		return HttpResponse.ok(user.getSubscriptions());
	}

	@Transactional
	@Override
	public HttpResponse<String> subscribe(Long userId, Long hashtagId) {
		// Get the user, returning 404 if the user id doesn't exist
		Optional<User> oUser = userRepository.findById(userId);
		if (oUser.isEmpty()) {
			return HttpResponse.notFound(String.format("User %d not found", userId));
		}
		User user = oUser.get();
		
		// Get the hashtag, returning 404 if the user id doesn't exist
		Optional<Hashtag> oHashtag = hashtagRepository.findById(hashtagId);
		if (oHashtag.isEmpty()) {
			return HttpResponse.notFound(String.format("Hashtag %d not found", hashtagId));
		}
		Hashtag hashtag = oHashtag.get();
		
		// Add the hashtag to the set of subscribed hashtags
		if (user.getSubscriptions().add(hashtag)) {
			userRepository.update(user);
			producer.userSubscribed(userId, hashtag);
			return HttpResponse.ok(String.format("User %d subscribed to hashtag %d", userId, hashtagId));
		}
		
		return HttpResponse.status(HttpStatus.NO_CONTENT, String.format("User %d already subscribed to hashtag %d", userId, hashtagId));
	}

	@Transactional
	@Override
	public HttpResponse<String> unsubscribe(Long userId, Long hashtagId) {
		// Get the user, returning 404 if the user id doesn't exist
		Optional<User> oUser = userRepository.findById(userId);
		if (oUser.isEmpty()) {
			return HttpResponse.notFound(String.format("User %d not found", userId));
		}
		User user = oUser.get();
		
		// Get the hashtag, returning 404 if the user id doesn't exist
		Optional<Hashtag> oHashtag = hashtagRepository.findById(hashtagId);
		if (oHashtag.isEmpty()) {
			return HttpResponse.notFound(String.format("Hashtag %d not found", hashtagId));
		}
		Hashtag hashtag = oHashtag.get();
		
		// Remove the hashtag from the set of subscribed hashtags
		if (user.getSubscriptions().remove(hashtag)) {
			userRepository.update(user);
			producer.userUnsubscribed(userId, hashtag);
			return HttpResponse.ok(String.format("User %d unsubscribed from hashtag %d", userId, hashtagId));
		}
		
		return HttpResponse.status(HttpStatus.NO_CONTENT, String.format("User %d is not subscribed to hashtag %d", userId, hashtagId));
	}

	@Transactional
	@Override
	public HttpResponse<Iterable<VideoDTO>> getRecomandationsForHashtag(Long userId, Long hashtagId) {
		// Get the user, returning null if the user id doesn't exist
		Optional<User> oUser = userRepository.findById(userId);
		if (oUser.isEmpty()) {
			return HttpResponse.status(HttpStatus.NOT_FOUND, String.format("User %d not found", userId));
		}
		User user = oUser.get();
		
		// Get the hashtag, returning null if the user id doesn't exist
		Optional<Hashtag> oHashtag = hashtagRepository.findById(hashtagId);
		if (oHashtag.isEmpty()) {
			return HttpResponse.status(HttpStatus.NOT_FOUND, String.format("Hashtag %d not found", hashtagId));
		}
		Hashtag hashtag = oHashtag.get();
		
		if (!user.getSubscriptions().contains(hashtag))
			return HttpResponse.status(HttpStatus.NO_CONTENT, String.format("User %d is not subscribed to hashtag %d", userId, hashtagId));
				
		// Sort the hastags's videos by views
		List<Video> videos = new ArrayList<Video>(hashtag.getVideos());
		videos.sort(new Comparator<Video>() {

			@Override
			public int compare(Video v1, Video v2) {
				return (v1.getViews() > v2.getViews()) ? -1 : 
					   (v1.getViews() == v2.getViews())? 0 : 
					   1;
			}
		});
		
		
		
		// Get the user's viewed videos
		Set<Video> userViewedVideos = user.getViewedVideos();
		
		System.out.println(userViewedVideos);
		System.out.println(videos);
		
		// Now remove the videos which the users has already seen or is the owner of
		videos.removeIf((v) -> userViewedVideos.contains(v) || v.getUser().equals(user));
		
		// Get the top10 elements from the list
		return HttpResponse.ok(videos.stream().map(v -> v.toDto()).collect(Collectors.toList()).subList(0, Math.min(videos.size(), 10)));
	}

}
