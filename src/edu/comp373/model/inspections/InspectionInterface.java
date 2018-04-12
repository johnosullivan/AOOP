package edu.comp373.model.inspections;

import java.time.LocalDateTime;

import edu.comp373.model.patterns.Request;
import edu.comp373.model.users.Inspector;

public interface InspectionInterface extends Request {

	public Inspector getInspector();
	
	public void setInspector(final Inspector inspector);
	
	public String getReport();
	
	public void setReport(final String report);
	
	public String getID();
	
	public void setID(final String id);
	
	public String getFacility();
	
	public void setFacility(final String facility);

	public String saveInspection();
	
	public void setDateTime(final LocalDateTime datetime);

	public LocalDateTime getDateTime();
	
}
