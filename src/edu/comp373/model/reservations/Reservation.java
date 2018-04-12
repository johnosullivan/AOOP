package edu.comp373.model.reservations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.comp373.model.patterns.Observer;
import edu.comp373.model.patterns.ReportPartVisitor;
import edu.comp373.model.users.FacilityUser;

public class Reservation implements ReservationInterface {
	/* Private vars */
	private LocalDateTime start;
	private LocalDateTime end;
	private String faciliyID;
	private String id; 
	private FacilityUser facilityuser;
	
	private List<Observer> observers = new ArrayList<Observer>();
	
	public void notifyAllObservers(){
	   for (Observer observer : observers) {
	      observer.update();
	   }
	} 
	
	/* Constructor for the building before saving to MongoDB */
	public Reservation(final FacilityUser facilityUser, final LocalDateTime start, final LocalDateTime end, final String facID) {
		this.start = start;
		this.end = end;
		this.faciliyID = facID;
		this.id = "";
		this.facilityuser = facilityUser;
	}
	/* Constructor for the building after saving to MongoDB */
	public Reservation(final FacilityUser facilityUser, final LocalDateTime start, final LocalDateTime end, final String facID, final String ID) {
		this.start = start;
		this.end = end;
		this.faciliyID = facID;
		this.id = ID;
		this.facilityuser = facilityUser;
	}
	
	public Reservation() {
		
	}
	
	public void cancel() {
		this.notifyAllObservers();
	}
	
	public void setFacility(String id) {
		this.faciliyID = id;
	}

	public FacilityUser getFacilityUser() {
		return this.facilityuser;
	}

	public void setFacilityUser(FacilityUser facilityuser) {
		this.facilityuser = facilityuser;
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
	/*
	 * (non-Javadoc)
	 * @see edu.comp373.model.reservations.ReservationInterface#setStart(java.time.LocalDateTime)
	 */
	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	/*
	 * (non-Javadoc)
	 * @see edu.comp373.model.reservations.ReservationInterface#setEnd(java.time.LocalDateTime)
	 */
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	
	@Override
	public void attach(Observer user) {
		observers.add(user);		
	}

	@Override
	public void accept(ReportPartVisitor reportPartVisitor) {
		reportPartVisitor.visit(this);
	}
	
}