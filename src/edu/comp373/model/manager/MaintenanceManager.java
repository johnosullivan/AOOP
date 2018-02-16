package edu.comp373.model.manager;

import java.util.ArrayList;
import java.util.Iterator;

import edu.comp373.dal.maintenance.MaintenanceDAO;
import edu.comp373.model.facility.Facility;
import edu.comp373.model.maintenance.MaintenanceRequest;
import edu.comp373.model.maintenance.MaintenanceRequest.MaintenanceStatus;

public class MaintenanceManager implements MaintenanceManagerInterface {
	
	private MaintenanceDAO maintenanceDAO = new MaintenanceDAO();
	
	public MaintenanceManager() {
		
	}

	public boolean makeFacilityMaintRequest(MaintenanceRequest maintenance) {
		if (maintenance.saveMaintenanceRequest().equals("")) {
			return false;
		}
		return true;
	}
	
	public boolean scheduleMaintenance(MaintenanceRequest maintenance) {
		return true;
	}
	
	public Double calcMaintenanceCostForFacility(Facility facility) {
		Double totalcost = 0.0;
		
		Iterator<MaintenanceRequest> list = maintenanceDAO.getMaintenanceRequestsForFacility(facility).iterator();
		
		while(list.hasNext()) {
			MaintenanceRequest item = list.next();
			totalcost = totalcost + item.getCost();
		}
		
		
		return totalcost;
	}
	
	public Double calcProblemRateForFacility(Facility facility) {
		return 0.0;
	}
	
	public Double calcDownTimeForFacility(Facility facility) {
		return 0.0; 
	}
	
	public ArrayList<MaintenanceRequest> listMaintRequests() {
		return maintenanceDAO.getMaintenanceRequest(MaintenanceStatus.PROCESSED);
	}
	
	public ArrayList<MaintenanceRequest> listMaintenance() {
		return maintenanceDAO.getMaintenanceRequest(MaintenanceStatus.COMPLETED);
	}
	
	public ArrayList<MaintenanceRequest> listFacilityProblems() {
		return maintenanceDAO.getMaintenanceRequest(MaintenanceStatus.PROBLEM);
	}
	
}
