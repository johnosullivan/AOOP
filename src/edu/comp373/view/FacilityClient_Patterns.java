package edu.comp373.view;

import java.time.LocalDateTime;

import edu.comp373.model.maintenance.MaintenanceRequest;
import edu.comp373.model.maintenance.MaintenanceRequest.MaintenanceStatus;
import edu.comp373.model.reservations.Reservation;
import edu.comp373.model.users.FacilityUser;
import edu.comp373.model.users.Inspector;
import edu.comp373.model.users.User;
import edu.comp373.model.users.UserInterface;

public class FacilityClient_Patterns {

	public static void main(String[] args) {
		System.out.println("Advanced Object Oriented Programming (OOP) Patterns");
		
		UserInterface user_one = new FacilityUser();
		User user_bridge_one = new User(user_one);
		user_bridge_one.setUserFirstName("John");
		user_bridge_one.setUserMiddleName("Nikolas");
		user_bridge_one.setUserLastName("O'Sullivan");
		
		UserInterface user_two = new Inspector();
		User user_bridge_two = new User(user_two);
		user_bridge_two.setUserFirstName("Rachel");
		user_bridge_two.setUserMiddleName("Louise");
		user_bridge_two.setUserLastName("O'Sullivan");
		
		
		System.out.println(user_bridge_one.getUserFirstName());
		System.out.println(user_bridge_one.getUserMiddleName());
		System.out.println(user_bridge_one.getUserLastName());
		
		System.out.println(user_bridge_two.getUserFirstName());
		System.out.println(user_bridge_two.getUserMiddleName());
		System.out.println(user_bridge_two.getUserLastName());
		
		
		
		
		MaintenanceRequest request = new MaintenanceRequest();
		request.setCost(200.0);
		request.setProblem("The pipe inside the room in the back broke!!!");

		user_bridge_two.attachToRequest(request);
		
		request.setStatus(MaintenanceStatus.COMPLETED);
		
		LocalDateTime start = LocalDateTime.now().minusHours(2); 
        LocalDateTime end = LocalDateTime.now(); 
        Reservation reversation = new Reservation();
        reversation.setStart(start);
        reversation.setEnd(end);
        
        user_bridge_one.attachToRequest(reversation);
        
        reversation.cancel();
		
	}
	
}
