package edu.comp373.model.patterns;

import edu.comp373.model.inspections.Inspection;
import edu.comp373.model.maintenance.MaintenanceRequest;
import edu.comp373.model.reservations.Reservation;

public interface ReportPartVisitor {
	public void visit(MaintenanceRequest maintenanceRequest);
	public void visit(Inspection inspection);
	public void visit(Reservation reservation);
	public void visit(Report report);
}
