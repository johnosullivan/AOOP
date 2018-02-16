package edu.comp373.model.inspections;

import com.mongodb.BasicDBObject;

import edu.comp373.dal.inspections.InspectorDAO;

public class Inspector implements InspectorInterface {
	/* Private vars */
	private String firstName;
	private String middleName;
	private String lastName;
	private String title;
	private String id;
	
	private InspectorDAO inspectorDAO = new InspectorDAO();
	/* Basic Constructor */
	public Inspector() { }
	/* Constructor for the building before saving to MongoDB */
	public Inspector(final String firstName, final String middleName, final String lastName, final String title) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.title = title;
		this.id = "";
	}
	/* Constructor for the building after saving to MongoDB */
	public Inspector(final String firstName, final String middleName, final String lastName, final String title, final String id) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.title = title;
		this.id = id;
	}
	/* Constructor for Inspector with MongoDB ID */
	public Inspector(String id) { 
		BasicDBObject inspectorObj = this.inspectorDAO.getInspectorByID(id);
		this.id = " " + inspectorObj.get("_id");
		this.firstName = (String)inspectorObj.get("firstName");
		this.middleName = (String)inspectorObj.get("middleName");
		this.lastName = (String)inspectorObj.get("lastName");
		this.title = (String)inspectorObj.get("title");
	}
	/*
	 * (non-Javadoc) Gets the FirstName
	 * @see edu.comp373.model.inspections.InspectorInterface#getFirstName()
	 */
	public String getFirstName() {
		return this.firstName;
	}
	/*
	 * (non-Javadoc) Sets the FirstName
	 * @see edu.comp373.model.inspections.InspectorInterface#setFirstName(java.lang.String)
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}
	/*
	 * (non-Javadoc) Gets the MiddleName
	 * @see edu.comp373.model.inspections.InspectorInterface#getMiddleName()
	 */
	public String getMiddleName() {
		return this.middleName;
	}
	/*
	 * (non-Javadoc) Sets the MiddleName
	 * @see edu.comp373.model.inspections.InspectorInterface#setMiddleName(java.lang.String)
	 */
	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}
	/*
	 * (non-Javadoc) Gets the LastName
	 * @see edu.comp373.model.inspections.InspectorInterface#getLastName()
	 */
	public String getLastName() {
		return this.lastName;
	}
	/*
	 * (non-Javadoc) Sets the LastName
	 * @see edu.comp373.model.inspections.InspectorInterface#setLastName(java.lang.String)
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}
	/*
	 * (non-Javadoc) Gets the Title
	 * @see edu.comp373.model.inspections.InspectorInterface#getTitle()
	 */
	public String getTitle() {
		return this.title;
	}
	/* 
	 * (non-Javadoc) Sets the Title
	 * @see edu.comp373.model.inspections.InspectorInterface#setTitle(java.lang.String)
	 */
	public void setTitle(final String title) {
		this.title = title;
	}
	/*
	 * (non-Javadoc) Gets the ID
	 * @see edu.comp373.model.inspections.InspectorInterface#getID()
	 */
	public String getID() {
		return this.id;
	}
	/*
	 * (non-Javadoc) Sets the ID
	 * @see edu.comp373.model.inspections.InspectorInterface#setID(java.lang.String)
	 */
	public void setID(final String id) {
		this.id = id;
	}
	/*
	 * (non-Javadoc) Add Inspector
	 * @see edu.comp373.model.inspections.InspectorInterface#addInspector()
	 */
	public String addInspector() {
		this.id = this.inspectorDAO.addInspector(this);
		return this.id;
	}
	
}
