package edu.comp373.model.reservations;

import java.time.LocalDateTime;

import edu.comp373.model.users.FacilityUser;

public interface ReservationInterface {

	public LocalDateTime getStart();
	
	public LocalDateTime getEnd();
	
	public String getFacilityID() ;
	
	public String getID();
	
	public void setID(String id);
	
	public FacilityUser getFacilityUser();

	public void setFacilityUser(FacilityUser facilityuser);
	
}
