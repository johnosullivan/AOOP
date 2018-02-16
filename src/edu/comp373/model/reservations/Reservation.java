package edu.comp373.model.reservations;

import java.time.LocalDateTime;

public class Reservation implements ReservationInterface {
	/* Private vars */
	private LocalDateTime start;
	private LocalDateTime end;
	private String faciliyID;
	private String id; 
	/* Constructor for the building before saving to MongoDB */
	public Reservation(final LocalDateTime start, final LocalDateTime end, final String facID) {
		this.start = start;
		this.end = end;
		this.faciliyID = facID;
		this.id = "";
	}
	/* Constructor for the building after saving to MongoDB */
	public Reservation(final LocalDateTime start, final LocalDateTime end, final String facID, final String ID) {
		this.start = start;
		this.end = end;
		this.faciliyID = facID;
		this.id = ID;
	}
	/*
	 * (non-Javadoc) Gets the start datetime
	 * @see edu.comp373.model.reservations.ReservationInterface#getStart()
	 */
	public LocalDateTime getStart() {
		return this.start;
	}
	/*
	 * (non-Javadoc) Gets the end datetime
	 * @see edu.comp373.model.reservations.ReservationInterface#getEnd()
	 */
	public LocalDateTime getEnd() {
		return this.end;
	}
	/*
	 * (non-Javadoc) Gets the facility ID
	 * @see edu.comp373.model.reservations.ReservationInterface#getFacilityID()
	 */
	public String getFacilityID() {
		return this.faciliyID;
	}
	/*
	 * (non-Javadoc) Gets the MongoID
	 * @see edu.comp373.model.reservations.ReservationInterface#getID()
	 */
	public String getID() {
		return this.id;
	}
	/*
	 * (non-Javadoc) Sets the MongoID
	 * @see edu.comp373.model.reservations.ReservationInterface#setID(java.lang.String)
	 */
	public void setID(String id) {
		this.id = id;
	}
	
}