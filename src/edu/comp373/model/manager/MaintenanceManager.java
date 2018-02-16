package edu.comp373.model.manager;

import java.time.Duration;
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
		
		Iterator<MaintenanceRequest> requests = maintenanceDAO.getMaintenanceRequestsForFacility(facility).iterator();
		
		while(requests.hasNext()) {
			MaintenanceRequest item = requests.next();
			totalcost = totalcost + item.getCost();
		}
		
		
		return totalcost;
	}
	
	public Double calcProblemRateForFacility(Facility facility) {
		
		ArrayList<MaintenanceRequest> requests = maintenanceDAO.getMaintenanceRequestsForFacility(facility);
		Integer total = requests.size();
		Integer proms = 0;
		
		Iterator<MaintenanceRequest> its = requests.iterator();
		while(its.hasNext()) {
			MaintenanceRequest item = its.next();
			if (item.getStatus() == MaintenanceStatus.PROBLEM) {
				proms++;
			}
			
		}
				
		return ((double)proms/(double)total) * 100;
	}
	
	public Double calcDownTimeForFacility(Facility facility) {
		
		Iterator<MaintenanceRequest> requests = maintenanceDAO.getMaintenanceRequestsForFacility(facility).iterator();
		long seconds = 0;
		while(requests.hasNext()) {
			MaintenanceRequest item = requests.next();
			Duration dur = Duration.between(item.getStartDateTime(), item.getEndDateTime());
			seconds = seconds + dur.getSeconds();
		}
		
		return (double)Duration.ofSeconds(seconds).toHours(); 
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
