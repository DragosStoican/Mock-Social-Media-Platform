package uk.ac.york.eng2.assessment.y1234.trending.domain;
/* protected region Imports on begin */
import javax.persistence.*;
import io.micronaut.serde.annotation.Serdeable;
/* protected region Imports end */


@Entity
@Serdeable
public class Trend {
	
	// Generated fields
	/* protected region id annotations on begin */
	@Id
	@GeneratedValue
	/* protected region id annotations end */
	private Long id;
	
	/* protected region top annotations on begin */
	@Column(nullable = false)
	/* protected region top annotations end */
	private String top;
	
	/* protected region hour annotations on begin */
	@Column(nullable = false)
	/* protected region hour annotations end */
	private String hour;
	
	// Generated relations
	// Generated getters/setters
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTop() {
		return top;
	}
	
	public void setTop(String top) {
		this.top = top;
	}
	
	public String getHour() {
		return hour;
	}
	
	public void setHour(String hour) {
		this.hour = hour;
	}
	
	
	/* protected region Additional methods on begin */
	// Any other methods required go here
	
	/* protected region Additional methods end */
}