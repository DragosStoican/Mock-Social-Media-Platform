package uk.ac.york.eng2.assessment.y1234.hashtag.domain;
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
	/* protected region id annotations end */
	private Long id;
	
	// Generated relations
	@JsonIgnore
	@ManyToMany(mappedBy = "subscribers")	
	private Set<Hashtag> subscriptions;
	
	// Generated getters/setters
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Set<Hashtag> getSubscriptions() {
		return subscriptions;
	}
	
	public void setSubscriptions(Set<Hashtag> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	/* protected region Additional methods on begin */
	// Any other methods required go here
	
	/* protected region Additional methods end */
}