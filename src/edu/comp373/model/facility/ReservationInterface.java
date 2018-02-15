package edu.comp373.model.facility;

import java.time.LocalDateTime;
import java.util.ArrayList;

interface ReservationInterface {

	public LocalDateTime getStart();
	
	public LocalDateTime getEnd();
	
	public String getFacilityID() ;
	
	public String getID();
	
	public void setID(String id);
	
	public static ArrayList<Reservation> getAllReservation(String id) {
		return null;
	}
	
}
