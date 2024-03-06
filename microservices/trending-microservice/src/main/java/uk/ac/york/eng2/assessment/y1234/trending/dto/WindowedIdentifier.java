package uk.ac.york.eng2.assessment.y1234.trending.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class WindowedIdentifier {
	
	private String start;
	private String end;

	public WindowedIdentifier() {
		// empty constructor for reflection
	}

	public WindowedIdentifier(String start, String end) {
		this.setStart(start);
		this.setEnd(end);
	}
	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "WindowedIdentifier [start=" + start + ", end=" + end + "]";
	}

}
