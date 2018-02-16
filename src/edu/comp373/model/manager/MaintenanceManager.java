package edu.comp373.model.manager;

import java.util.ArrayList;

import edu.comp373.model.facility.Facility;
import edu.comp373.model.maintenance.MaintenanceRequest;

public class MaintenanceManager implements MaintenanceManagerInterface {
	
	public MaintenanceManager() {
		
	}

	public boolean makeFacilityMaintRequest(MaintenanceRequest maintenance) {
		return true;
	}
	
	public boolean scheduleMaintenance() {
		return true;
	}
	
	public Double calcMaintenanceCostForFacility(Facility facility) {
		return 0.0;
	}
	
	public Double calcProblemRateForFacility(Facility facility) {
		return 0.0;
	}
	
	public Double calcDownTimeForFacility(Facility facility) {
		return 0.0;
	}
	
	public ArrayList<MaintenanceRequest> listMaintRequests() {
		return new ArrayList<MaintenanceRequest>();
	}
	
	public ArrayList<MaintenanceRequest> listMaintenance() {
		return new ArrayList<MaintenanceRequest>();
	}
	
	public ArrayList<MaintenanceRequest> listFacilityProblems() {
		return new ArrayList<MaintenanceRequest>();
	}
	
}
