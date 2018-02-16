package edu.comp373.model.manager;

import java.util.ArrayList;

import edu.comp373.model.facility.Facility;
import edu.comp373.model.maintenance.MaintenanceRequest;

public interface MaintenanceManagerInterface {
	
	public boolean makeFacilityMaintRequest(MaintenanceRequest maintenance);
	
	public boolean scheduleMaintenance(MaintenanceRequest maintenance);
	
	public Double calcMaintenanceCostForFacility(Facility facility);
	
	public Double calcProblemRateForFacility(Facility facility);
	
	public Double calcDownTimeForFacility(Facility facility);
	
	public ArrayList<MaintenanceRequest> listMaintRequests();
	
	public ArrayList<MaintenanceRequest> listMaintenance();
	
	public ArrayList<MaintenanceRequest> listFacilityProblems();

}
