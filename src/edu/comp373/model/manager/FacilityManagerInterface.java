package edu.comp373.model.manager;

import java.util.ArrayList;

import edu.comp373.model.facility.Facility;
import edu.comp373.model.facility.Facility.DetailType;

public interface FacilityManagerInterface {

	public ArrayList<Facility> listFacilities();
	
	public ArrayList<Facility> requestAvailableCapacity(Integer capslimit);
	
	public ArrayList<Facility> vacateFacility();
	
	public Facility addNewFacility(Facility facility);
	
	public void addFacilityDetail(Facility facility, DetailType type,Object obj);
	
	public boolean removeFacility(Facility facility);
	
}
