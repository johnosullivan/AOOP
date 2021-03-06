package edu.comp373.model.users;

import edu.comp373.model.announcement.Announcement;
import edu.comp373.model.patterns.AnnouncementMediator;
import edu.comp373.model.patterns.Request;

public class User extends UserAbstract {
	/*
	 * User
	 */
	public User(UserInterface user) {
		super(user);
	}
	
	public User(UserInterface user, AnnouncementMediator mediator) {
		super(user,mediator);
	}
	
	public void setUserFirstName(String firstName) {
		setFirstName(firstName);
	}
	
	public String getUserFirstName() {
		return getFirstName();
	}
	
	public String getUserMiddleName() {
		return getMiddleName();
	}
	
	public void setUserMiddleName(final String middleName) {
		setMiddleName(middleName);
	}
	
	public String getUserLastName() {
		return getLastName();
	}
	
	public void setUserLastName(final String lastName) {
		setLastName(lastName);
	}
	
	public String getUserTitle() {
		return getTitle();
	}
	
	public void setUserTitle(final String title) {
		setTitle(title);
	}
	
	public String getUserID() {
		return getID();
	}
	
	public void setUserID(final String id) {
		setID(id);
	}
	
	public String saveUser() {
		return save();
	}  
	
	public void attachUserToRequest(Request subject) {
		attachToRequest(subject);
	}
	
	public void receiveUser(Announcement announcement) {
		receive(announcement);
	}
	
	public void postUser(Announcement announcement) {
		post(announcement);
	}
}
