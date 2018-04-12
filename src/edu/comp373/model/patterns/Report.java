package edu.comp373.model.patterns;

import edu.comp373.model.inspections.Inspection;
import edu.comp373.model.maintenance.MaintenanceRequest;
import edu.comp373.model.reservations.Reservation;

public class Report implements Request {
	
	Request[] reporting_parts;

	public Report(){
		reporting_parts = new Request[] {
				new MaintenanceRequest(), 
				new Inspection(), 
				new Reservation()
		};		
	} 

	
	@Override
	public void accept(ReportPartVisitor reportPartVisitor) {
		
	   for (int i = 0; i < reporting_parts.length; i++) {
		   reporting_parts[i].accept(reportPartVisitor);
	   }
	   reportPartVisitor.visit(this);
	}


	@Override
	public void attach(Observer user) {
		
	}
}
