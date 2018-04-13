package edu.comp373.model.patterns;

import java.time.LocalDateTime;

import edu.comp373.model.inspections.Inspection;
import edu.comp373.model.maintenance.MaintenanceRequest;
import edu.comp373.model.reservations.Reservation;

public class GenerateReport implements ReportPartVisitor {

	private LocalDateTime start;
	private LocalDateTime end;
	
	private MaintenanceRequest maintenanceRequest;
	private Inspection inspection;
	private Reservation reservation;
	private Report report;
	
	public GenerateReport(final LocalDateTime _start, final LocalDateTime _end) {
		this.start = _start;
		this.end = _end;
	}
	
	public LocalDateTime getStartDate() {
		return this.start;
	}
	
	public LocalDateTime getEndDate() {
		return this.end;
	}
	
	@Override
	public void visit(MaintenanceRequest maintenanceRequest) {
		this.maintenanceRequest = maintenanceRequest;
	}

	@Override
	public void visit(Inspection inspection) {
		this.inspection = inspection;
	}

	@Override
	public void visit(Reservation reservation) {
		this.reservation = reservation;
	}

	@Override
	public void visit(Report report) {
		this.report = report;
	}
	
	public void printReport() {
		System.out.println(maintenanceRequest);
		System.out.println(inspection);
		System.out.println(reservation);
		System.out.println(report);
	}

}
