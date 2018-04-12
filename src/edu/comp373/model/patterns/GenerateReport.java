package edu.comp373.model.patterns;

import java.time.LocalDateTime;

import edu.comp373.model.inspections.Inspection;
import edu.comp373.model.maintenance.MaintenanceRequest;
import edu.comp373.model.reservations.Reservation;

public class GenerateReport implements ReportPartVisitor {

	private LocalDateTime start;
	private LocalDateTime end;
	
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
		System.out.println("MaintenanceRequest");
	}

	@Override
	public void visit(Inspection inspection) {
		System.out.println("Inspection");
	}

	@Override
	public void visit(Reservation reservation) {
		System.out.println("Reservation");
	}

	@Override
	public void visit(Report report) {
		System.out.println("Report");
	}

}
