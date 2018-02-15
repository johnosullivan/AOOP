package edu.comp373.model.manager;

import java.util.ArrayList;
import java.util.Iterator;

import edu.comp373.dal.facility.FacilityDAO;
import edu.comp373.model.facility.Facility;

public class FacilityManager implements FacilityManagerInterface {

	public FacilityManager() {
		
	}
	
	public ArrayList<Facility> listFacilities() {
		return FacilityDAO.listFacilities();
	}
	
	public ArrayList<Facility> requestAvailableCapacity(Integer capslimit) {
		ArrayList<Facility> respond = new ArrayList<Facility>();
		Iterator<Facility> iters = FacilityDAO.listFacilities().iterator();
		
		while(iters.hasNext()) {
			Facility facilityObj = iters.next();
			if (capslimit <= facilityObj.getCapacity()) {
				respond.add(facilityObj);
			}
		}
		return respond;
	}
	
	public ArrayList<Facility> vacateFacility() {
		ArrayList<Facility> respond = new ArrayList<Facility>();
		
		return respond;
	}
	
}
