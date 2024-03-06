package uk.ac.york.eng2.assessment.y1234.subscription.domain;
/* protected region Imports on begin */
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micronaut.serde.annotation.Serdeable;
/* protected region Imports end */


@Entity
@Serdeable
public class Hashtag {
	
	// Generated fields
	/* protected region id annotations on begin */
	@Id
	@GeneratedValue
	/* protected region id annotations end */
	private Long id;
	
	/* protected region name annotations on begin */
	@Column(nullable = false, unique = true)
	/* protected region name annotations end */
	private String name;
	
	// Generated relations
	@JsonIgnore
	@ManyToMany(mappedBy = "hashtags")	
	private Set<Video> videos;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "subscriptions")	
	private Set<User> subscribers;
	
	// Generated getters/setters
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Video> getVideos() {
		return videos;
	}
	
	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}
	public Set<User> getSubscribers() {
		return subscribers;
	}
	
	public void setSubscribers(Set<User> subscribers) {
		this.subscribers = subscribers;
	}
	
	/* protected region Additional methods on begin */
	// Any other methods required go here
	
	/* protected region Additional methods end */
}