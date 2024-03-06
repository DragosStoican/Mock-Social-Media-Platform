package uk.ac.york.eng2.assessment.y1234.hashtag.domain;
/* protected region Imports on begin */
import java.util.*;
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
	/* protected region id annotations end */
	private Long id;
	
	/* protected region name annotations on begin */
	@Column(nullable = false, unique = true)
	/* protected region name annotations end */
	private String name;
	
	// Generated relations
	@JsonIgnore
	@JoinTable(name="subscribers_subscriptions")
	@ManyToMany	
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
	
	public Set<User> getSubscribers() {
		return subscribers;
	}
	
	public void setSubscribers(Set<User> subscribers) {
		this.subscribers = subscribers;
	}
	
	/* protected region Additional methods on begin */
	// Any other methods required go here
	public Hashtag() {
		this.subscribers = new HashSet<User>();
	}

	
	// Implementing hashcode and equals for set operations
	@Override
	public boolean equals(Object o) {
		if (o == this)
	        return true;
		
	    if (!(o instanceof Hashtag))
	        return false;
	    
	    Hashtag video = (Hashtag) o;
	    return this.id == video.id;
	}
	
	@Override
	public int hashCode() {
		if (id == null)
			return 0;
		return id.intValue();
	}
	
	/* protected region Additional methods end */
}