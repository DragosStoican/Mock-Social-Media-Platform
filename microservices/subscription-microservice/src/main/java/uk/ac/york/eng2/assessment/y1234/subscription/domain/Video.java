package uk.ac.york.eng2.assessment.y1234.subscription.domain;
/* protected region Imports on begin */
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micronaut.serde.annotation.Serdeable;
import uk.ac.york.eng2.assessment.y1234.subscription.dto.VideoDTO;

/* protected region Imports end */


@Entity
@Serdeable
public class Video {
	
	// Generated fields
	/* protected region id annotations on begin */
	@Id
	@GeneratedValue
	/* protected region id annotations end */
	private Long id;
	
	/* protected region title annotations on begin */
	@Column(nullable = false)
	/* protected region title annotations end */
	private String title;
	
	/* protected region views annotations on begin */
	@Column(nullable = false)
	/* protected region views annotations end */
	private Long views;
	
	// Generated relations
	@JsonIgnore
	@JoinTable(name="user_videos")
	@ManyToOne	
	private User user;
	
	@JsonIgnore
	@JoinTable(name="hashtags_videos")
	@ManyToMany	
	private Set<Hashtag> hashtags;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "viewedVideos")	
	private Set<User> viewedBy;
	
	// Generated getters/setters
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Long getViews() {
		return views;
	}
	
	public void setViews(Long views) {
		this.views = views;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	public Set<Hashtag> getHashtags() {
		return hashtags;
	}
	
	public void setHashtags(Set<Hashtag> hashtags) {
		this.hashtags = hashtags;
	}
	public Set<User> getViewedBy() {
		return viewedBy;
	}
	
	public void setViewedBy(Set<User> viewedBy) {
		this.viewedBy = viewedBy;
	}
	
	/* protected region Additional methods on begin */
	// Constructor should set viewes to 0
	public Video() {
		this.views = 0L;
	}
	
	// Implementing hashcode and equals for set operations
	@Override
	public boolean equals(Object o) {
		if (o == this)
	        return true;
		
	    if (!(o instanceof Video))
	        return false;
	    
	    Video video = (Video) o;
	    return this.id == video.id;
	}
	
	@Override
	public int hashCode() {
		if (id == null)
			return 0;
		return id.intValue();
	}
	
	public VideoDTO toDto() {
		VideoDTO dto = new VideoDTO();
		dto.setTitle(title);
		dto.setUser(user.getUsername());
		if (hashtags != null) {
			List<String> hashtagString = hashtags.stream().map(Hashtag::getName).collect(Collectors.toList());
			dto.setHashtags(hashtagString.toString()
					.replace(" ", "")
					.replace("[", "")
					.replace("]", ""));
		}
		dto.setViews(views);
			
		return dto;
	}
	/* protected region Additional methods end */
}