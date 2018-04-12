package edu.comp373.model.patterns;

import java.util.*;

import edu.comp373.model.users.User;
import edu.comp373.model.users.UserAbstract;

public class AppAnnouncementMediator implements AnnouncementMediator {

	private ArrayList<User> users;
	
	public AppAnnouncementMediator() {
		users = new ArrayList<User>();
	}
	 
	public void addUser(User user) {
		users.add(user);
	}

	public void post(String message, UserAbstract suser) {
	    for(User user: users) {
	      if(user != suser) {
	    	  	user.receive(message);
	      }
	    }
	}

}
