package edu.comp373.model.users;

import com.mongodb.BasicDBObject;

import edu.comp373.dal.users.UsersDAO;

public class Inspector implements UserInterface {
	/* Private vars */
	private String firstName;
	private String middleName;
	private String lastName;
	private String title;
	private String id;
	
	private UsersDAO usersDAO = new UsersDAO();
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
		BasicDBObject inspectorObj = this.usersDAO.getInspectorByID(id);
		this.id = " " + inspectorObj.get("_id");
		this.firstName = (String)inspectorObj.get("firstName");
		this.middleName = (String)inspectorObj.get("middleName");
		this.lastName = (String)inspectorObj.get("lastName");
		this.title = (String)inspectorObj.get("title");
	}
	/*
	 * (non-Javadoc) Gets the FirstName
	 * @see edu.comp373.model.users.UserInterface#getFirstName()
	 */
	public String getFirstName() {
		return this.firstName;
	}
	/*
	 * (non-Javadoc) Sets the FirstName
	 * @see edu.comp373.model.users.UserInterface#setFirstName(java.lang.String)
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}
	/*
	 * (non-Javadoc) Gets the MiddleName
	 * @see edu.comp373.model.users.UserInterface#getMiddleName()
	 */
	public String getMiddleName() {
		return this.middleName;
	}
	/*
	 * (non-Javadoc) Sets the MiddleName
	 * @see edu.comp373.model.users.UserInterface#setMiddleName(java.lang.String)
	 */
	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}
	/*
	 * (non-Javadoc) Gets the LastName
	 * @see edu.comp373.model.users.UserInterface#getLastName()
	 */
	public String getLastName() {
		return this.lastName;
	}
	/*
	 * (non-Javadoc) Sets the LastName
	 * @see edu.comp373.model.users.UserInterface#setLastName(java.lang.String)
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}
	/*
	 * (non-Javadoc) Gets the Title
	 * @see edu.comp373.model.users.UserInterface#getTitle()
	 */
	public String getTitle() {
		return this.title;
	}
	/* 
	 * (non-Javadoc) Sets the Title
	 * @see edu.comp373.model.users.UserInterface#setTitle(java.lang.String)
	 */
	public void setTitle(final String title) {
		this.title = title;
	}
	/*
	 * (non-Javadoc) Gets the ID
	 * @see edu.comp373.model.inspections.UserInterface#getID()
	 */
	public String getID() {
		return this.id;
	}
	/*
	 * (non-Javadoc) Sets the ID
	 * @see edu.comp373.model.inspections.UserInterface#setID(java.lang.String)
	 */
	public void setID(final String id) {
		this.id = id;
	}
	/*
	 * (non-Javadoc) Add Inspector
	 * @see edu.comp373.model.inspections.UserInterface#addInspector()
	 */
	public String save() {
		this.id = this.usersDAO.addInspector(this);
		return this.id;
	}
	
}