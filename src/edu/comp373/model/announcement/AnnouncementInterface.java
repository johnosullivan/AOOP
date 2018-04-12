package edu.comp373.model.announcement;

import edu.comp373.model.users.User;

public interface AnnouncementInterface {

	public User getAnnouncer();
	
	public void setAnnouncer(final User user);
	
	public String getAnnouncement();
	
	public void setAnnouncement(final String announcement);
	
}
