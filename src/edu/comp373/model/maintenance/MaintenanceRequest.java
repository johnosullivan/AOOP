package edu.comp373.model.maintenance;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.comp373.dal.maintenance.MaintenanceDAO;
import edu.comp373.model.facility.Facility;
import edu.comp373.model.patterns.Observer;
import edu.comp373.model.patterns.ReportPartVisitor;

public class MaintenanceRequest implements MaintenanceRequestInterface {

	private Facility facility;
	private String problem;
	private String id;
	private MaintenanceStatus status;
	private LocalDateTime start_datetime;
	private LocalDateTime end_datetime;
	private Double cost;
	
	private MaintenanceDAO maintenanceDAO = new MaintenanceDAO();
	
	private List<Observer> observers = new ArrayList<Observer>();
	
	public void notifyAllObservers(){
	   for (Observer observer : observers) {
	      observer.update();
	   }
	} 
	
	public enum MaintenanceStatus {
		PENDING,
		PROCESSED,
		INPROGRESS,
		COMPLETED,
		PROBLEM
	}
	
	public MaintenanceRequest() {
	
	}
	
	public MaintenanceRequest(final Facility facility, final String problem, final LocalDateTime start_datetime, final LocalDateTime end_datetime,final Double cost, final MaintenanceStatus status) {
		this.facility = facility;
		this.problem = problem;
		this.id = "";
		this.status = status;
		this.start_datetime = start_datetime;
		this.end_datetime = end_datetime;
		this.cost = cost;
	}
	
	public MaintenanceRequest(final Facility facility, final String problem, final LocalDateTime start_datetime, final LocalDateTime end_datetime, final Double cost, final MaintenanceStatus status, final String id) {
		this.facility = facility;
		this.problem = problem;
		this.id = id;
		this.status = status;
		this.start_datetime = start_datetime;
		this.end_datetime = end_datetime;
		this.cost = cost;
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
	
	public Double getCost() {
		return this.cost;
	}
	
	public void setCost(final Double cost) {
		this.cost = cost;
	}
	
	public MaintenanceStatus getStatus() {
		return this.status;
	}
	
	public void setStatus(final MaintenanceStatus status) {
		this.status = status;
		this.notifyAllObservers();
	}
	
	public String saveMaintenanceRequest() {
		this.id = maintenanceDAO.addMaintenanceRequest(this);
		return this.id;
	}
	
	public void setEndDateTime(final LocalDateTime end_datetime) {
		this.end_datetime = end_datetime;
	}

	public LocalDateTime getEndDateTime() {
		return this.end_datetime;
	}
	
	public void setStartDateTime(final LocalDateTime start_datetime) {
		this.start_datetime = start_datetime;
	}

	public LocalDateTime getStartDateTime() {
		return this.start_datetime;
	}

	@Override
	public void attach(Observer user) {
		observers.add(user);		
	}

	@Override
	public void accept(ReportPartVisitor reportPartVisitor) {
		reportPartVisitor.visit(this);
	}

}
