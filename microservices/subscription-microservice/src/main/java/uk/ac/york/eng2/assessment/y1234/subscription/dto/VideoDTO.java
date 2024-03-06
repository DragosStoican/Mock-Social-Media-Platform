package uk.ac.york.eng2.assessment.y1234.subscription.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class VideoDTO {
	
	private String title;
	
	private String user;
	
	private String hashtags;
	
	private Long views;


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHashtags() {
		return hashtags;
	}

	public void setHashtags(String hashtags) {
		this.hashtags = hashtags;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Long getViews() {
		return views;
	}

	public void setViews(Long views) {
		this.views = views;
	}
}
