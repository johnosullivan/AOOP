package edu.comp373.model.reservations;

import java.time.LocalDateTime;

public interface ReservationInterface {

	public LocalDateTime getStart();
	
	public LocalDateTime getEnd();
	
	public String getFacilityID() ;
	
	public String getID();
	
	public void setID(String id);
	
}
