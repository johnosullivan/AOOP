package edu.comp373.model.facility;

import java.time.LocalDateTime;
import java.util.ArrayList;

import edu.comp373.model.facility.Facility.DetailType;
import edu.comp373.model.facility.Facility.FeatureType;

interface FacilityInterface {

	public void addFacilityDetail(DetailType type,Object obj);
	
	public void addFeatures(FeatureType feature);
	
	public Location getLocation();
	
	public void setLocation(Location location);
	
	public String getFacilityInformation();
	
	public void setCapacity(Integer size);
	
	public Integer getCapacity();
	
	public String getID();
	
	public boolean assignFacilityToUse(final LocalDateTime start,final LocalDateTime end);
	
	static public ArrayList<Facility> listFacilities() {
		return null;
	}
	
	public ArrayList<Reservation> getReservations();

	public boolean isInUseDuringInterval(final LocalDateTime start, final LocalDateTime end);
	
	public String saveFacility();
	
	public boolean removeFacility();
	
	static public ArrayList<Facility> requestAvailableCapacity(Integer capslimit) {
		return null;
	}
	
	static public ArrayList<Facility> vacateFacility() {
		return null;
	}
	
	public void update();
	
}
