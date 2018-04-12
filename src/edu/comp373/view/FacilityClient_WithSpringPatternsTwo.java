package edu.comp373.view;

import java.time.LocalDateTime;

import edu.comp373.model.announcement.Announcement;
import edu.comp373.model.patterns.AppAnnouncementMediator;
import edu.comp373.model.patterns.GenerateReport;
import edu.comp373.model.patterns.Report;
import edu.comp373.model.patterns.Request;
import edu.comp373.model.users.FacilityUser;
import edu.comp373.model.users.Inspector;
import edu.comp373.model.users.User;

public class FacilityClient_WithSpringPatternsTwo {

	public static void main(String[] args) {
		
		Request report = new Report();
		LocalDateTime start = LocalDateTime.now().minusHours(2); 
        LocalDateTime end = LocalDateTime.now();
		GenerateReport genreport = new GenerateReport(start,end);
		report.accept(genreport);
		
		AppAnnouncementMediator mediator = new AppAnnouncementMediator();
		
		User user_bridge_one = new User(new FacilityUser(), mediator);
		user_bridge_one.setUserFirstName("John");
		user_bridge_one.setUserMiddleName("Nikolas");
		user_bridge_one.setUserLastName("O'Sullivan");
		
		User user_bridge_two = new User(new Inspector(), mediator);
		user_bridge_two.setUserFirstName("Rachel");
		user_bridge_two.setUserMiddleName("Louise");
		user_bridge_two.setUserLastName("O'Sullivan"); 
		
		mediator.addUser(user_bridge_one);
		mediator.addUser(user_bridge_two);
		
		
		Announcement announcement = new Announcement();
		
		announcement.setAnnouncement("The second floor power has gone out can someone please fix it!!!");
		announcement.setAnnouncer(user_bridge_one);
		
		user_bridge_one.post(announcement);
		
		
	}
	
}
