package uk.ac.york.eng2.assessment.y1234.cli.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class TrendDTO {
	
	private String top;
	
	private String hour;
	
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
	
	@Override
	public String toString() {
		return "### Top hashtags at " + hour + "###" 
				+ System.lineSeparator() 
				+ top.replace(" ", "")
					 .replace(",", "\n")
					 .replace("{", "")
					 .replace("}", "")
					 .replace("=", " with likes = ");
	}
}
