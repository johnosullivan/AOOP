package edu.comp373.model.maintenance;

import edu.comp373.model.facility.Facility;
import edu.comp373.model.inspections.Inspector;

public class MaintenanceRequest {

	private Facility facility;
	private Inspector inspector;
	private String problem;
	
	enum MaintenanceStatus {
		
	}
	
	public MaintenanceRequest(final Facility facility, final Inspector inspector, final String problem) {
	
	}
	
	public Facility getFacility() {
		return this.facility;
	}
	public void setFacility(Facility facility) {
		this.facility = facility;
	}
	
	public Inspector getInspector() {
		return this.inspector;
	}
	public void setInspector(Inspector inspector) {
		this.inspector = inspector;
	}
	
	public String getProblem() {
		return this.problem;
	}
	public void setInspector(String problem) {
		this.problem = problem;
	}
	
}
