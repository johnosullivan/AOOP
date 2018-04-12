package edu.comp373.model.announcement;

import edu.comp373.model.users.User;

public class Announcement implements AnnouncementInterface  {

	private User user;
	private String announcement;
	
	public Announcement() {
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see edu.comp373.model.announcement.AnnouncementInterface#getInspector()
	 */
	@Override
	public User getAnnouncer() {
		return this.user;
	}

	/*
	 * (non-Javadoc)
	 * @see edu.comp373.model.announcement.AnnouncementInterface#setInspector(edu.comp373.model.users.UserInterface)
	 */
	@Override
	public void setAnnouncer(User user) {
		this.user = user;
	}

	/*
	 * (non-Javadoc)
	 * @see edu.comp373.model.announcement.AnnouncementInterface#getAnnouncement()
	 */
	@Override
	public String getAnnouncement() {
		return announcement;
	}

	/*
	 * (non-Javadoc)
	 * @see edu.comp373.model.announcement.AnnouncementInterface#setAnnouncement(java.lang.String)
	 */
	@Override
	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}

}
