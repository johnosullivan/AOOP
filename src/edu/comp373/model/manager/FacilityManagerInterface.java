package edu.comp373.model.manager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeMap;

import edu.comp373.dal.facility.FacilityDAO;
import edu.comp373.model.facility.Facility;
import edu.comp373.model.facility.Facility.DetailType;

public interface FacilityManagerInterface {

	public ArrayList<Facility> listFacilities();
	
	public ArrayList<Facility> requestAvailableCapacity(Integer capslimit);
	
	public ArrayList<Facility> vacateFacility(final LocalDateTime start, final LocalDateTime end);
	
	public Facility addNewFacility(Facility facility);
	
	public void addFacilityDetail(Facility facility, DetailType type,Object obj);
	
	public boolean removeFacility(Facility facility);
	
	public TreeMap<String, Long> listActualUsage();
	
	public Double calcUsageRate(Facility facility, LocalDateTime datetime);
	
	public void setFacilityDAO(FacilityDAO facilityDAO);
	
}
