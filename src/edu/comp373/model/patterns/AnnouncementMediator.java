package edu.comp373.model.patterns;

import edu.comp373.model.announcement.Announcement;
import edu.comp373.model.users.UserAbstract;

public interface AnnouncementMediator {
	public void post(Announcement announcement, UserAbstract userAbstract);
}
