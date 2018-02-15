package edu.comp373.model.manager;

import java.util.ArrayList;

import edu.comp373.model.facility.Facility;

public interface FacilityManagerInterface {

	public ArrayList<Facility> listFacilities();
	
	public ArrayList<Facility> requestAvailableCapacity(Integer capslimit);
	
	public ArrayList<Facility> vacateFacility();
	
}
