package uk.ac.york.eng2.assessment.y1234.cli.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class HashtagDTO {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "[name=" + name + "]";
	}

}
