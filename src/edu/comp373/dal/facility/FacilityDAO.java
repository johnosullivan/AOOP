package edu.comp373.dal.facility;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import edu.comp373.model.facility.Facility;

public class FacilityDAO {
	
	private ArrayList<Facility> allFacility = new ArrayList<Facility>();
	private Map<String, Facility> keyToFacility = new TreeMap<String, Facility>();
	
	public FacilityDAO() { }
	
	public ArrayList<Facility> listFacilities() { return this.allFacility; }
	
	public boolean requestAvailableCapacity(Integer size) {
		return true;
	}
	
	public boolean addNewFacility(Facility facility) {
		this.keyToFacility.put(facility.getID(), facility);
		this.allFacility.add(facility);
		return true;
	}
	
	public boolean removeFacility(Facility facility) {
		this.allFacility.remove(facility);
		return true;
	}
	
	public void addFacilityDetail() {
		
	}
	
}
