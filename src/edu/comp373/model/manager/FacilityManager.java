package edu.comp373.model.manager;

import java.util.ArrayList;
import java.util.Iterator;

import edu.comp373.dal.facility.FacilityDAO;
import edu.comp373.model.facility.Facility;
import edu.comp373.model.facility.Facility.DetailType;

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
	
	public Facility addNewFacility(Facility facility) {
		String id = facility.save();
		facility.setID(id);
		return facility;
	}
	
	public void addFacilityDetail(Facility facility, DetailType type,Object obj) {
		facility.addDetail(type, obj);
	}
	
	public boolean removeFacility(Facility facility) {
		return facility.remove();
	}
	
}
