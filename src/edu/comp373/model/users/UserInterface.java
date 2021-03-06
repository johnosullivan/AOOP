package edu.comp373.model.users;

import edu.comp373.model.announcement.Announcement;
import edu.comp373.model.patterns.Request;

public interface UserInterface {

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
	
	public String save();
	
	public void attachToRequest(Request subject);
	
	public void receive(Announcement announcement);
	
}
