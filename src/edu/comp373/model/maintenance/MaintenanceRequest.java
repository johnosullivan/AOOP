package edu.comp373.model.maintenance;

import java.time.LocalDateTime;

import edu.comp373.dal.maintenance.MaintenanceDAO;
import edu.comp373.model.facility.Facility;

public class MaintenanceRequest implements MaintenanceRequestInterface {

	private Facility facility;
	private String problem;
	private String id;
	private MaintenanceStatus status;
	private LocalDateTime datetime;
	
	private MaintenanceDAO maintenanceDAO = new MaintenanceDAO();
	
	public enum MaintenanceStatus {
		PENDING,
		PROCESSED,
		INPROGRESS,
		COMPLETED,
		PROBLEM
	}
	
	public MaintenanceRequest() {
	
	}
	
	public MaintenanceRequest(final Facility facility, final String problem, final LocalDateTime datetime) {
		this.facility = facility;
		this.problem = problem;
		this.id = "";
		this.status = MaintenanceStatus.PENDING;
		this.datetime = datetime;
	}
	
	public MaintenanceRequest(final Facility facility, final String problem, final LocalDateTime datetime, final String id) {
		this.facility = facility;
		this.problem = problem;
		this.id = id;
		this.status = MaintenanceStatus.PENDING;
		this.datetime = datetime;
	}
	
	public Integer maintenanceStatusToInt() {
		switch (this.status) {
		case PENDING: return 1;
		case PROCESSED: return 2;
		case INPROGRESS: return 3;
		case COMPLETED: return 4;
		case PROBLEM: return 5;
		default: return 1;
		}
	}
	
	public MaintenanceStatus intToMaintenanceStatus(Integer x) {
		switch (x) {
		case 1: return MaintenanceStatus.PENDING;
		case 2: return MaintenanceStatus.PROCESSED;
		case 3: return MaintenanceStatus.INPROGRESS;
		case 4: return MaintenanceStatus.COMPLETED;
		case 5: return MaintenanceStatus.PROBLEM;
		default: return MaintenanceStatus.PENDING;
		}
	}
	
	public Facility getFacility() {
		return this.facility;
	}
	public void setFacility(Facility facility) {
		this.facility = facility;
	}
	
	public String getProblem() {
		return this.problem;
	}
	
	public void setProblem(final String problem) {
		this.problem = problem;
	}
	
	public String getID() {
		return this.id;
	}
	
	public void setID(final String id) {
		this.id = id;
	}
	
	public MaintenanceStatus getStatus() {
		return this.status;
	}
	
	public void setStatus(final MaintenanceStatus status) {
		this.status = status;
	}
	
	public String saveMaintenanceRequest() {
		return maintenanceDAO.addMaintenanceRequest(this);
	}
	
	public void setDateTime(final LocalDateTime datetime) {
		this.datetime = datetime;
	}

	public LocalDateTime getDateTime() {
		return this.datetime;
	}

}
