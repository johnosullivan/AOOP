package edu.comp373.model.inspections;

import java.time.LocalDateTime;

import edu.comp373.dal.inspections.InspectionDAO;

public class Inspection implements InspectionInterface {
	
	private Inspector inspector;
	private String report;
	private String facility;
	private String id;
	private LocalDateTime datetime;
	
	private InspectionDAO inspectionDAO = new InspectionDAO();
	
	public Inspection() {
		
	}
	
	public Inspection(String id) {
		
	}
	
	public Inspection(final String facility,final Inspector inspector,final String report,final LocalDateTime dateTime) {
		this.facility = facility;
		this.inspector = inspector;
		this.report = report;
		this.datetime = dateTime;
	}
	
	public Inspection(final String facility,final Inspector inspector,final String report,final LocalDateTime dateTime, final String id) {
		this.facility = facility;
		this.inspector = inspector;
		this.report = report;
		this.datetime = dateTime;
		this.id = id;
	}
	
	public Inspector getInspector() {
		return this.inspector;
	}
	
	public void setInspector(final Inspector inspector) {
		this.inspector = inspector;
	}
	
	public String getReport() {
		return this.report;
	}
	
	public void setReport(final String report) {
		this.report = report;
	}
	
	public String getID() {
		return this.id;
	}
	
	public void setID(final String id) {
		this.id = id;
	}
	
	public String getFacility() {
		return this.facility;
	}
	
	public void setFacility(final String facility) {
		this.facility = facility;
	}

	public String saveInspection() {
		return inspectionDAO.addInspection(this);
	}
	
	public void setDateTime(final LocalDateTime datetime) {
		this.datetime = datetime;
	}

	public LocalDateTime getDateTime() {
		return this.datetime;
	}
	
}
