package edu.comp373.model.users;

import edu.comp373.model.patterns.AnnouncementMediator;
import edu.comp373.model.patterns.Request;

public abstract class UserAbstract {

	private UserInterface user;
	private AnnouncementMediator mediator;
 
	public UserAbstract(UserInterface user){
		this.user = user;
	}
	
	public UserAbstract(UserInterface user, AnnouncementMediator mediator){
		this.user = user;
		this.mediator = mediator;
	}
	
	public AnnouncementMediator getMediator() {
		return mediator;
	}
	
	public void send(String message) {
	    mediator.post(message, this);
	}
	
	public void setFirstName(String firstName) {
		user.setFirstName(firstName);
	}
	
	public String getFirstName() {
		return user.getFirstName();
	}
	
	public String getMiddleName() {
		return user.getMiddleName();
	}
	
	public void setMiddleName(final String middleName) {
		user.setMiddleName(middleName);
	}
	
	public String getLastName() {
		return user.getLastName();
	}
	
	public void setLastName(final String lastName) {
		user.setLastName(lastName);
	}
	
	public String getTitle() {
		return user.getTitle();
	}
	
	public void setTitle(final String title) {
		user.setTitle(title);
	}
	
	public String getID() {
		return user.getID();
	}
	
	public void setID(final String id) {
		user.setID(id);
	}
	
	public String save() {
		return user.save();
	}
	
	public void attachToRequest(Request subject) {
		user.attachToRequest(subject);
	}
 
	public void receive(String message) {
		user.receive(message);
	}
	
}