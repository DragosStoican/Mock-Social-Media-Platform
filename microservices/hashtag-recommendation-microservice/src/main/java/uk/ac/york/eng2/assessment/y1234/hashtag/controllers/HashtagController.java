package uk.ac.york.eng2.assessment.y1234.hashtag.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import jakarta.inject.Inject;
import uk.ac.york.eng2.assessment.y1234.hashtag.domain.Hashtag;
import uk.ac.york.eng2.assessment.y1234.hashtag.domain.User;
import uk.ac.york.eng2.assessment.y1234.hashtag.gen.HashtagControllerInterface;
import uk.ac.york.eng2.assessment.y1234.hashtag.repositories.HashtagRepository;
import uk.ac.york.eng2.assessment.y1234.hashtag.repositories.UserRepository;

@Controller("/hashtags")
public class HashtagController implements HashtagControllerInterface{

	@Inject
	HashtagRepository hashtagRepository;
	
	@Inject
	UserRepository userRepository;
	
	@Override
	public HttpResponse<Iterable<Hashtag>> getHashtags() {
		return HttpResponse.ok(hashtagRepository.findAll());
	}

	@Transactional
	@Override
	public HttpResponse<LinkedHashMap<String, Integer>> getRecommendedHashtags(Long hashtagId) {
		// Get the hashtag, returning 404 if the user id doesn't exist
		Optional<Hashtag> oHashtag = hashtagRepository.findById(hashtagId);
		if (oHashtag.isEmpty()) {
			return HttpResponse.status(HttpStatus.NOT_FOUND, String.format("Hashtag %d not found", hashtagId));
		}
		Hashtag hashtag = oHashtag.get();
		
		
		// Construct the map of common subscribers
		HashMap<String, Integer> commonSubscribersCount = new HashMap<String, Integer>();
		
		for (User u : hashtag.getSubscribers()) {
			System.out.println("User: " + u.getId());
			for (Hashtag h : u.getSubscriptions()) {
				System.out.println("Comparing " + hashtag.getName() + " with " + h.getName());
				if (h.getId() == hashtag.getId())
					continue;
				Integer currValue = commonSubscribersCount.get(h.getName());
				if (currValue == null) 
					currValue = 0;
				System.out.println("Putting " + hashtag.getName() + " with value" + currValue);
				commonSubscribersCount.put(h.getName(), currValue + 1);
			}
		}
		
		return HttpResponse.ok(getTopNSorted(commonSubscribersCount, 10));
	}
	
	private LinkedHashMap<String, Integer> getTopNSorted(HashMap<String, Integer> top, int n) {
		List<Entry<String, Integer>> lst = new ArrayList<>(top.entrySet());
		lst.sort(Entry.comparingByValue());
		Collections.reverse(lst);
		
		List<Entry<String, Integer>> lstN = lst.subList(0, Math.min(lst.size(), n));
	
		LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        for (Entry<String, Integer> entry : lstN) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
	}

}
