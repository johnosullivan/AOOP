package edu.comp373.model.facility;

import java.time.LocalDateTime;
import java.util.ArrayList;

import edu.comp373.model.facility.Facility.DetailType;
import edu.comp373.model.facility.Facility.FeatureType;
import edu.comp373.model.inspections.Inspection;
import edu.comp373.model.reservations.Reservation;

public interface FacilityInterface {

	public void addDetail(DetailType type,Object obj);
	
	public void addFeatures(FeatureType feature);
	
	public Location getLocation();
	
	public void setLocation(Location location);
	
	public String getFacilityInformation();
	
	public void setCapacity(Integer size);
	
	public Integer getCapacity();
	
	public String getID();
	
	public boolean assignFacilityToUse(final LocalDateTime start,final LocalDateTime end);
	
	public ArrayList<Reservation> getReservations();

	public boolean isInUseDuringInterval(final LocalDateTime start, final LocalDateTime end);
	
	public String save();
	
	public boolean remove();
	
	public void update();
	
	public ArrayList<Inspection> listInspections();
	
	public String addInspection(Inspection inspection);
	
	public void setID(final String id);
	
}
