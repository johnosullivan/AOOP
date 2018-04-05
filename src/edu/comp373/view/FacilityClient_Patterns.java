package edu.comp373.view;

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
	}
	
}
