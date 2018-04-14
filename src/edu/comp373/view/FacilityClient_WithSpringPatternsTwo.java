package edu.comp373.view;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
		System.out.println("Advanced Object Oriented Programming (OOP) Patterns Two");
		Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");
		// Gets a instance of the report of from the bean
		Request report = (Report)context.getBean("report");
		// Creates a time frame
		LocalDateTime start = LocalDateTime.now().minusHours(2); 
        LocalDateTime end = LocalDateTime.now();
        // Generate a report by visiting the different request objects 
		GenerateReport genreport = (GenerateReport)context.getBean("generatereport", start, end);
		report.accept(genreport);
		// Prints the report that has been generated 
		genreport.printReport();
		// Gets a instance of the mediator for the user's announcement board. 
		AppAnnouncementMediator mediator = (AppAnnouncementMediator)context.getBean("appannouncementmediator");
		// Creates the first user to the mediator
		final User user_bridge_one = (User)context.getBean("usermediator", (FacilityUser)context.getBean("facilityuser"), mediator);
		user_bridge_one.setUserFirstName("John");
		user_bridge_one.setUserMiddleName("Nikolas");
		user_bridge_one.setUserLastName("O'Sullivan");
		// Creates the second user to the mediator
		final User user_bridge_two = (User)context.getBean("usermediator", (Inspector)context.getBean("inspector"), mediator);
		user_bridge_two.setUserFirstName("Rachel");
		user_bridge_two.setUserMiddleName("Louise");
		user_bridge_two.setUserLastName("O'Sullivan"); 
		// Adds the users to mediator
		mediator.addUser(user_bridge_one);
		mediator.addUser(user_bridge_two);
		// Creates an announcement from spring bean
		Announcement announcement = (Announcement)context.getBean("announcement");
		// Adds the content of the announcement
		announcement.setAnnouncement("The second floor power has gone out can someone please fix it!!!");
		announcement.setAnnouncer(user_bridge_one);
		// Posts the announcement
		user_bridge_one.post(announcement);
		// Closes the spring context
        ((ClassPathXmlApplicationContext) context).close();
	}
	
}
