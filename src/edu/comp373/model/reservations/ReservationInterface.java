package edu.comp373.model.reservations;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface ReservationInterface {

	public LocalDateTime getStart();
	
	public LocalDateTime getEnd();
	
	public String getFacilityID() ;
	
	public String getID();
	
	public void setID(String id);
	
	public static ArrayList<Reservation> getAllReservation(String id) {
		return null;
	}
	
}
