package edu.comp373.model.reservations;

import java.time.LocalDateTime;

import edu.comp373.model.patterns.Request;
import edu.comp373.model.users.FacilityUser;

public interface ReservationInterface extends Request {

	public LocalDateTime getStart();
	
	public LocalDateTime getEnd();
	
	public String getFacilityID() ;
	
	public String getID();
	
	public void setID(String id);
	
	public FacilityUser getFacilityUser();

	public void setFacilityUser(FacilityUser facilityuser);
	
	public void setStart(LocalDateTime start);
	
	public void setEnd(LocalDateTime end);
	
	public void setFacility(String id);
	
	public void cancel();
	
}
