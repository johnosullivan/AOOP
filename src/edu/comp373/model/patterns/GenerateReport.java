package edu.comp373.model.patterns;

import edu.comp373.model.inspections.Inspection;
import edu.comp373.model.maintenance.MaintenanceRequest;
import edu.comp373.model.reservations.Reservation;

public class GenerateReport implements ReportPartVisitor {

	public GenerateReport() {

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
