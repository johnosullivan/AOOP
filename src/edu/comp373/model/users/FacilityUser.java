package edu.comp373.model.users;

import com.mongodb.BasicDBObject;

import edu.comp373.dal.users.UsersDAO;
import edu.comp373.model.maintenance.MaintenanceRequest;
import edu.comp373.model.patterns.Observer;
import edu.comp373.model.patterns.Request;
import edu.comp373.model.reservations.Reservation;

public class FacilityUser extends Observer {
	/* Private vars */
	private String firstName;
	private String middleName;
	private String lastName;
	private String title;
	private String id;
	
	private UsersDAO usersDAO = new UsersDAO();
	/* Basic Constructor */
	public FacilityUser() { }
	/* Constructor for the building before saving to MongoDB */
	public FacilityUser(final String firstName, final String middleName, final String lastName, final String title) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.title = title;
		this.id = "";
	}
	/* Constructor for the building after saving to MongoDB */
	public FacilityUser(final String firstName, final String middleName, final String lastName, final String title, final String id) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.title = title;
		this.id = id;
	}
	/* Constructor for Inspector with MongoDB ID */
	public FacilityUser(String id) { 
		BasicDBObject userObj = this.usersDAO.getUserByID(id);
		this.id = " " + userObj.get("_id");
		this.firstName = (String)userObj.get("firstName");
		this.middleName = (String)userObj.get("middleName");
		this.lastName = (String)userObj.get("lastName");
		this.title = (String)userObj.get("title");
	}
	
	/*
	 * (non-Javadoc) Gets the FirstName
	 * @see edu.comp373.model.users.UserInterface#getFirstName()
	 */
	@Override
	public String getFirstName() {
		return this.firstName;
	}
	/*
	 * (non-Javadoc) Sets the FirstName
	 * @see edu.comp373.model.users.UserInterface#setFirstName(java.lang.String)
	 */
	@Override
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
	 * @see edu.comp373.model.users.UserInterface#getID()
	 */
	public String getID() {
		return this.id;
	}
	/*
	 * (non-Javadoc) Sets the ID
	 * @see edu.comp373.model.users.UserInterface#setID(java.lang.String)
	 */
	public void setID(final String id) {
		this.id = id;
	}
	/*
	 * (non-Javadoc) Add Inspector
	 * @see edu.comp373.model.users.UserInterface#addInspector()
	 */
	public String save() {
		this.id = this.usersDAO.addUser(this);
		return this.id;
	}
	/*
	 * (non-Javadoc)
	 * @see edu.comp373.model.observer.Observer#update()
	 */
	@Override
	public void update() {
		if (this.subject instanceof MaintenanceRequest) {
			System.out.println("Observer MaintenanceRequest -> Update the request FacilityUser");
		}
		
		if (this.subject instanceof Reservation) {
			System.out.println("Observer Reservation -> Update the request FacilityUser");
		}
	}
	/*
	 * (non-Javadoc)
	 * @see edu.comp373.model.users.UserInterface#attachToRequest(edu.comp373.model.observer.Request)
	 */
	public void attachToRequest(Request subject) {
		this.subject = subject;
	    this.subject.attach(this);
	}
	/*
	 * (non-Javadoc)
	 * @see edu.comp373.model.users.UserInterface#receive(java.lang.String)
	 */
	@Override
	public void receive(String message) {
		System.out.println(this.getFirstName() + " " + this.getLastName() + " Received -> " + message);
	}
}
