package edu.comp373.model.patterns;

import edu.comp373.model.users.UserAbstract;

public interface AnnouncementMediator {
	public void post(String message, UserAbstract userAbstract);
}
