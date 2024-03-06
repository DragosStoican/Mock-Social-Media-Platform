package uk.ac.york.eng2.assessment.y1234.video.domain;
/* protected region Imports on begin */
import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.serde.annotation.Serdeable;
/* protected region Imports end */


@Entity
@Serdeable
public class User {
	
	// Generated fields
	/* protected region id annotations on begin */
	@Id
	@GeneratedValue
	/* protected region id annotations end */
	private Long id;
	
	/* protected region username annotations on begin */
	@Column(nullable = false)
	/* protected region username annotations end */
	private String username;
	
	// Generated relations
	@JsonIgnore
	@OneToMany(mappedBy = "user")	
	private Set<Video> videos;
	
	@JsonIgnore
	@JoinTable(name="viewedVideos_viewedBy")
	@ManyToMany	
	private Set<Video> viewedVideos;
	
	@JsonIgnore
	@JoinTable(name="likedVideos_likedBy")
	@ManyToMany	
	private Set<Video> likedVideos;
	
	@JsonIgnore
	@JoinTable(name="dislikedVideos_dislikedBy")
	@ManyToMany	
	private Set<Video> dislikedVideos;
	
	// Generated getters/setters
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Set<Video> getVideos() {
		return videos;
	}
	
	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}
	public Set<Video> getViewedVideos() {
		return viewedVideos;
	}
	
	public void setViewedVideos(Set<Video> viewedVideos) {
		this.viewedVideos = viewedVideos;
	}
	public Set<Video> getLikedVideos() {
		return likedVideos;
	}
	
	public void setLikedVideos(Set<Video> likedVideos) {
		this.likedVideos = likedVideos;
	}
	public Set<Video> getDislikedVideos() {
		return dislikedVideos;
	}
	
	public void setDislikedVideos(Set<Video> dislikedVideos) {
		this.dislikedVideos = dislikedVideos;
	}
	
	/* protected region Additional methods on begin */
	// Any other methods required go here
	/* protected region Additional methods end */
}