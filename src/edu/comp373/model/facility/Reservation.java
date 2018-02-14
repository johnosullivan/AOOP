package edu.comp373.model.facility;

import java.time.LocalDateTime;

public class Reservation {

	private LocalDateTime start;
	private LocalDateTime end;
	private String faciliyID;
	
	public Reservation(final LocalDateTime start, final LocalDateTime end, final String ID) {
		this.start = start;
		this.end = end;
		this.faciliyID = ID;
	}
	
	public LocalDateTime getStart() {
		return this.start;
	}
	
	public LocalDateTime getEnd() {
		return this.end;
	}
	
	public String getID() {
		return this.faciliyID;
	}
}