package uk.ac.york.eng2.assessment.y1234.cli.domain;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class Trend {

	private Long id;

	private String top;
	
	private String hour;

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
}
