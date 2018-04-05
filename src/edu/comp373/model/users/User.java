package edu.comp373.model.users;

public class User extends UserAbstract {

	public User(UserInterface user) {
		super(user);
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
	
}
