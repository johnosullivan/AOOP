package edu.comp373.view;

import static org.junit.Assert.assertEquals;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;

import edu.comp373.model.facility.*;
import edu.comp373.model.inspections.Inspection;
import edu.comp373.model.maintenance.MaintenanceRequest;
import edu.comp373.model.maintenance.MaintenanceRequest.MaintenanceStatus;
import edu.comp373.model.reservations.Reservation;
import edu.comp373.model.users.FacilityUser;
import edu.comp373.model.users.Inspector;

import java.util.logging.Logger;
import java.util.logging.Level;

public class FacilityClient_WithSpring {
	
	static boolean DEBUGGING = false;

	public static void main(String[] args) {
		Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");
		System.out.println("***************** Application Context Instantiated! ******************");
		// Creating the address object from spring bean
		Address address = (Address)context.getBean("address");
		address.setAddress("1032 W Sheridan Rd");
        address.setCity("Chicago");
        address.setState("IL");
        address.setZip("60660");
        // Creating the location object from spring bean
        Location location = (Location)context.getBean("location_basic");
        location.setAddress(address);
        location.setBuidlingName("Damen Student Center");
        location.setRoomName("RM 345");
        // Creating the facility object from spring bean
        Facility facility = (Facility)context.getBean("facility");
        facility.setCapacity(125);
        facility.setLocation(location);
        LocalDateTime start = LocalDateTime.now().minusHours(2); 
        LocalDateTime end = LocalDateTime.now(); 
        // Creating the maintenance request object from spring bean
		MaintenanceRequest maintenanceRequest = (MaintenanceRequest)context.getBean("maintenancerequest");
        maintenanceRequest.setFacility(facility);
        maintenanceRequest.setProblem("Fix the broken pipe in the den");
        maintenanceRequest.setStatus(MaintenanceStatus.PENDING);
        maintenanceRequest.setStartDateTime(start);
        maintenanceRequest.setEndDateTime(end);
        maintenanceRequest.setCost(123.3);
        // Creating the inspector object from spring bean
        Inspector user = (Inspector)context.getBean("inspector");
		user.setFirstName("John");
		user.setMiddleName("Nikolas");
		user.setLastName("O'Sullivan");
		user.setTitle("Mr.");
		assertEquals("John",user.getFirstName());
		assertEquals("Nikolas",user.getMiddleName());
		assertEquals("O'Sullivan",user.getLastName());
		assertEquals("Mr.",user.getTitle());
		// Creating the inspection object from spring bean
		Inspection inspection = (Inspection)context.getBean("inspection");
		inspection.setInspector(user);
		LocalDateTime datetime = LocalDateTime.now().minusHours(2); 
		inspection.setDateTime(datetime);
		inspection.setReport("some report");
		inspection.setID("theid");
		inspection.setFacility("facility");
		assertEquals(datetime,inspection.getDateTime());
		assertEquals("facility",inspection.getFacility());
		assertEquals("some report",inspection.getReport());
		assertEquals("theid",inspection.getID());
		// Creating the facility user object from spring bean
		FacilityUser facility_user = (FacilityUser)context.getBean("facilityuser");
		facility_user.setFirstName("Rachel");
		facility_user.setMiddleName("Louise");
		facility_user.setLastName("Cundiff");
		facility_user.setTitle("Ms.");
		// Creating the reservation object from spring bean
		Reservation reservation = (Reservation)context.getBean("reservation");
		reservation.setFacilityUser(facility_user);
		reservation.setStart(start);
		reservation.setEnd(end);
		// Prints out the output of the object to ensure the object dependencies work
		System.out.println("<-- MaintenanceRequest in Facility -->");
		System.out.println("Problem: " + maintenanceRequest.getProblem());
		System.out.println("MaintenanceStatus: " + maintenanceRequest.getStatus());
		System.out.println("StartDateTime: " + maintenanceRequest.getStartDateTime().toString());
		System.out.println("EndDateTime: " + maintenanceRequest.getEndDateTime().toString());
		System.out.println("Cost: " + maintenanceRequest.getCost());
		System.out.println("Building: " + maintenanceRequest.getFacility().getLocation().getBuildingName());
        System.out.println("Room: " + maintenanceRequest.getFacility().getLocation().getRoom());
        System.out.println("Capacity: " + maintenanceRequest.getFacility().getCapacity());
        System.out.println("Address: " + maintenanceRequest.getFacility().getLocation().getAddress().getAddress());
        System.out.println("City: " + maintenanceRequest.getFacility().getLocation().getAddress().getCity());
        System.out.println("State: " + maintenanceRequest.getFacility().getLocation().getAddress().getState());
        System.out.println("Zip: " + maintenanceRequest.getFacility().getLocation().getAddress().getZip());
        System.out.println("<-- Inspection by Inspector -->");
        System.out.println("Report: " + inspection.getReport());
        System.out.println("Inspector: " + inspection.getInspector().getTitle() + " " + inspection.getInspector().getFirstName() + " " + inspection.getInspector().getMiddleName() + " " + inspection.getInspector().getLastName());
        System.out.println("<-- Reservation by FacilityUser -->");
        System.out.println("Start: " + reservation.getStart().toString());
        System.out.println("End: " + reservation.getEnd().toString());
        System.out.println("FacilityUser: " + reservation.getFacilityUser().getTitle() + " " + reservation.getFacilityUser().getFirstName() + " " + reservation.getFacilityUser().getMiddleName() + " " + reservation.getFacilityUser().getLastName());
		System.out.println("***************** Application Context Done! **************************");
		// Closes the context
		((ClassPathXmlApplicationContext) context).close();
	}
	
}
