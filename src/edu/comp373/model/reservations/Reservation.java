package edu.comp373.model.reservations;

import java.time.LocalDateTime;
import java.util.ArrayList;

import edu.comp373.dal.reservations.ReservationDAO;

public class Reservation implements ReservationInterface {

	private LocalDateTime start;
	private LocalDateTime end;
	private String faciliyID;
	private String id;
	
	static private ReservationDAO reservationDAO = new ReservationDAO(); 
	
	public Reservation(final LocalDateTime start, final LocalDateTime end, final String facID) {
		this.start = start;
		this.end = end;
		this.faciliyID = facID;
		this.id = "";
	}
	
	public Reservation(final LocalDateTime start, final LocalDateTime end, final String facID, final String ID) {
		this.start = start;
		this.end = end;
		this.faciliyID = facID;
		this.id = ID;
	}
	
	public LocalDateTime getStart() {
		return this.start;
	}
	
	public LocalDateTime getEnd() {
		return this.end;
	}
	
	public String getFacilityID() {
		return this.faciliyID;
	}
	
	public String getID() {
		return this.id;
	}
	
	public void setID(String id) {
		this.id = id;
	}
	
	public static ArrayList<Reservation> getAllReservation(String id) {
		return reservationDAO.getAllReservations(id);
	}
	
}