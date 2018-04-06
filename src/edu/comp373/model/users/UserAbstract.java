package edu.comp373.model.users;

import edu.comp373.model.observer.Request;

public abstract class UserAbstract {

	private UserInterface user;
 
	public UserAbstract(UserInterface user){
		this.user = user;
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
 
	
}