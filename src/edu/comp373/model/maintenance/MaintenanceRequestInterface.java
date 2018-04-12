package edu.comp373.model.maintenance;

import edu.comp373.model.facility.Facility;
import edu.comp373.model.maintenance.MaintenanceRequest.MaintenanceStatus;
import edu.comp373.model.patterns.Request;

public interface MaintenanceRequestInterface extends Request  {

	public Facility getFacility();
	
	public void setFacility(Facility facility);
	
	public String getProblem();
	
	public void setProblem(final String problem);
	
	public String getID();
	
	public void setID(final String id);
	
	public MaintenanceStatus getStatus();
	
	public void setStatus(final MaintenanceStatus status);
	
	public String saveMaintenanceRequest();
	
}
