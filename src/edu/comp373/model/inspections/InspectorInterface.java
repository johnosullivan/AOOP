package edu.comp373.model.inspections;

public interface InspectorInterface {

	public String getFirstName();
	
	public void setFirstName(final String firstName);
	
	public String getMiddleName();
	
	public void setMiddleName(final String middleName);
	
	public String getLastName();
	
	public void setLastName(final String lastName);
	
	public String getTitle();
	
	public void setTitle(final String title);
	
	public String getID();
	
	public void setID(final String id);
	
	public String addInspector();
	
}
