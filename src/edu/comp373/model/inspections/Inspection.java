package edu.comp373.model.inspections;

import java.time.LocalDateTime;

import edu.comp373.dal.inspections.InspectionDAO;

public class Inspection implements InspectionInterface {
	/*
	 * Private vars for the Inspection class
	 */
	private Inspector inspector;
	private String report;
	private String facility;
	private String id;
	private LocalDateTime datetime;
	/* 
	 * InspectionDAO for access to MongoDB access
	 */
	private InspectionDAO inspectionDAO = new InspectionDAO();
	
	public Inspection() { }
	
	/*
	 * Constructor for Inspection in the MongoDB via ID
	 */
	public Inspection(String id) {
		
	}
	/* Constructor for the building before saving to MongoDB */
	public Inspection(final String facility,final Inspector inspector,final String report,final LocalDateTime dateTime) {
		this.facility = facility;
		this.inspector = inspector;
		this.report = report;
		this.datetime = dateTime;
	}
	/* Constructor for the building before after to MongoDB */
	public Inspection(final String facility,final Inspector inspector,final String report,final LocalDateTime dateTime, final String id) {
		this.facility = facility;
		this.inspector = inspector;
		this.report = report;
		this.datetime = dateTime;
		this.id = id;
	}
	/*
	 * (non-Javadoc) Gets the inspector who made the inspection
	 * @see edu.comp373.model.inspections.InspectionInterface#getInspector()
	 */
	public Inspector getInspector() {
		return this.inspector;
	}
	/*
	 * (non-Javadoc) Sets the inspector for the inspection given
	 * @see edu.comp373.model.inspections.InspectionInterface#setInspector(edu.comp373.model.inspections.Inspector)
	 */
	public void setInspector(final Inspector inspector) {
		this.inspector = inspector;
	}
	/*
	 * (non-Javadoc) Gets the report from the inspection object
	 * @see edu.comp373.model.inspections.InspectionInterface#getReport()
	 */
	public String getReport() {
		return this.report;
	}
	/*
	 * (non-Javadoc) Sets the report
	 * @see edu.comp373.model.inspections.InspectionInterface#setReport(java.lang.String)
	 */
	public void setReport(final String report) {
		this.report = report;
	}
	/*
	 * (non-Javadoc) Gets the inspection ID which is from MongoDB 
	 * @see edu.comp373.model.inspections.InspectionInterface#getID()
	 */
	public String getID() {
		return this.id;
	}
	/* 
	 * (non-Javadoc) Gets the inspection ID which is from MongoDB
	 * @see edu.comp373.model.inspections.InspectionInterface#setID(java.lang.String)
	 */
	public void setID(final String id) {
		this.id = id;
	}
	/*
	 * (non-Javadoc) Gets the facility that the inspection is about.
	 * @see edu.comp373.model.inspections.InspectionInterface#getFacility()
	 */
	public String getFacility() {
		return this.facility;
	}
	/*
	 * (non-Javadoc) Sets the facility for the inspection
	 * @see edu.comp373.model.inspections.InspectionInterface#setFacility(java.lang.String)
	 */
	public void setFacility(final String facility) {
		this.facility = facility;
	}
	/*
	 * (non-Javadoc) Saves the inspection to MongoDB
	 * @see edu.comp373.model.inspections.InspectionInterface#saveInspection()
	 */
	public String saveInspection() {
		return inspectionDAO.addInspection(this);
	}
	/*
	 * (non-Javadoc) Sets the LocalDateTime for the inspection
	 * @see edu.comp373.model.inspections.InspectionInterface#setDateTime(java.time.LocalDateTime)
	 */
	public void setDateTime(final LocalDateTime datetime) {
		this.datetime = datetime;
	}
	/*
	 * (non-Javadoc) Gets the LocalDateTime
	 * @see edu.comp373.model.inspections.InspectionInterface#getDateTime()
	 */
	public LocalDateTime getDateTime() {
		return this.datetime;
	}
	
}
