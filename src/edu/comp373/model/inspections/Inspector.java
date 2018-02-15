package edu.comp373.model.inspections;

import com.mongodb.BasicDBObject;

import edu.comp373.dal.inspections.InspectorDAO;

public class Inspector {

	private String firstName;
	private String middleName;
	private String lastName;
	private String title;
	private String id;
	
	private InspectorDAO inspectorDAO = new InspectorDAO();
	
	public Inspector() { }
	
	public Inspector(final String firstName, final String middleName, final String lastName, final String title) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.title = title;
		this.id = "";
	}
	
	public Inspector(final String firstName, final String middleName, final String lastName, final String title, final String id) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.title = title;
		this.id = id;
	}
	
	public Inspector(String id) { 
		BasicDBObject inspectorObj = this.inspectorDAO.getInspectorByID(id);
		this.id = " " + inspectorObj.get("_id");
		this.firstName = (String)inspectorObj.get("firstName");
		this.middleName = (String)inspectorObj.get("middleName");
		this.lastName = (String)inspectorObj.get("lastName");
		this.title = (String)inspectorObj.get("title");
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleName() {
		return this.middleName;
	}
	
	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(final String title) {
		this.title = title;
	}
	
	public String getID() {
		return this.id;
	}
	
	public void setID(final String id) {
		this.id = id;
	}
	
	public String addInspector() {
		this.id = this.inspectorDAO.addInspector(this);
		return this.id;
	}
	
}
