package edu.comp373.view;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.comp373.model.maintenance.MaintenanceRequest;
import edu.comp373.model.maintenance.MaintenanceRequest.MaintenanceStatus;
import edu.comp373.model.reservations.Reservation;
import edu.comp373.model.users.FacilityUser;
import edu.comp373.model.users.Inspector;
import edu.comp373.model.users.User;

public class FacilityClient_WithSpringPatternsOne {

	public static void main(String[] args) {
		System.out.println("Advanced Object Oriented Programming (OOP) Patterns One");
		Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");
		/*
		 * Bridge Pattern
		 */
		// Creates a instance of the user with the implementation facility user
		final User user_bridge_one = (User)context.getBean("user", (FacilityUser)context.getBean("facilityuser"));
		user_bridge_one.setUserFirstName("John");
		user_bridge_one.setUserMiddleName("Nikolas");
		user_bridge_one.setUserLastName("O'Sullivan");
		// Creates a instance of the user with the implementation inspector
		final User user_bridge_two = (User)context.getBean("user", (Inspector)context.getBean("inspector"));
		user_bridge_two.setUserFirstName("Rachel");
		user_bridge_two.setUserMiddleName("Louise");
		user_bridge_two.setUserLastName("O'Sullivan"); 
		// Prints details to confirm it is working
		System.out.println(user_bridge_one.getUserFirstName());
		System.out.println(user_bridge_one.getUserMiddleName());
		System.out.println(user_bridge_one.getUserLastName());
		System.out.println(user_bridge_two.getUserFirstName());
		System.out.println(user_bridge_two.getUserMiddleName());
		System.out.println(user_bridge_two.getUserLastName());
		/*
		 * Observer Pattern 
		 */
		MaintenanceRequest request = (MaintenanceRequest)context.getBean("maintenancerequest");
		request.setCost(200.0);
		request.setProblem("The pipe inside the room in the back broke!!!");
		// Attaches the users to the request
		user_bridge_two.attachToRequest(request);
		// Changes the status of the request
		request.setStatus(MaintenanceStatus.COMPLETED);
		// Creates a reservation 
		LocalDateTime start = LocalDateTime.now().minusHours(2); 
        LocalDateTime end = LocalDateTime.now(); 
        Reservation reversation = (Reservation)context.getBean("reservation");
        reversation.setStart(start);
        reversation.setEnd(end);
        // Attaches the users to the request
        user_bridge_one.attachToRequest(reversation);
        // Changes the status of the reservation
        reversation.cancel();
        // Closes the spring context
        ((ClassPathXmlApplicationContext) context).close();
	}
	
}
