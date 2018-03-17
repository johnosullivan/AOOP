package edu.comp373.view;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import edu.comp373.dal.Configs;
import edu.comp373.model.facility.Address;
import edu.comp373.model.facility.Facility;
import edu.comp373.model.facility.Location;
import edu.comp373.model.facility.Facility.DetailType;
import edu.comp373.model.inspections.Inspection;
import edu.comp373.model.maintenance.MaintenanceRequest;
import edu.comp373.model.maintenance.MaintenanceRequest.MaintenanceStatus;
import edu.comp373.model.manager.FacilityManager;
import edu.comp373.model.manager.MaintenanceManager;
//import edu.comp373.model.manager.MaintenanceManager;
import edu.comp373.model.reservations.Reservation;
import edu.comp373.model.users.FacilityUser;
import edu.comp373.model.users.Inspector;

public class FacilityClient_WithSpringAndDAO {

	static boolean DEBUGGING = false;

	public static void main(String[] args) {
		Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");
		System.out.println("***************** Application Context Instantiated! ******************");
		// Creating the manager objects from spring bean
		FacilityManager facilityManager = (FacilityManager)context.getBean("facilitymanager");
		MaintenanceManager maintenanceManager= (MaintenanceManager)context.getBean("maintenancemanager");
		//MaintenanceManager maintenanceManager = (MaintenanceManager)context.getBean("maintenancemanager");
		System.out.println("Advanced Object Oriented Programming (OOP)");        
		// Creating the address object from spring bean
		Address address1 = (Address)context.getBean("address");
		address1.setAddress("1032 W Sheridan Rd");
		address1.setCity("Chicago");
		address1.setState("IL");
		address1.setZip("60660");
		// Creating the location object from spring bean
		Location location1 = (Location)context.getBean("location_basic");
		location1.setAddress(address1);
		location1.setBuidlingName("Damen Student Center");
		location1.setRoomName("RM 345");
		// Creating the facility object from spring bean
		Facility facility1 = (Facility)context.getBean("facility");
		facility1.setCapacity(125);
		facility1.setLocation(location1);
		facility1.setCreated(LocalDateTime.now());
        facility1.addDetail(DetailType.CAPACITY, 120);
        facility1 = facilityManager.addNewFacility(facility1);
        System.out.println("Facility ID: " + facility1.getID());
        // listFacilities
        ArrayList<Facility> list = facilityManager.listFacilities();
        Iterator<Facility> iters_list = list.iterator();
        System.out.println("listFacilities_count: " + list.size());
        while(iters_list.hasNext()) { 
			Facility item = iters_list.next();
			System.out.println("Capacity: " + item.getCapacity() + " Location: " + item.getLocation().getBuildingName() + " " + item.getLocation().getRoom() + " Address: " + item.getLocation().getAddress().getFullAddress());
		}
        // requestAvailableCapacity
        ArrayList<Facility> respond = facilityManager.requestAvailableCapacity(150);
        Iterator<Facility> iters = respond.iterator();
        System.out.println("Facility_RequestAvailableCapacity: " + respond.size());
        while(iters.hasNext()) {
        		Facility item = iters.next();
			System.out.println("Capacity: " + item.getCapacity() + " Location: " + item.getLocation().getBuildingName() + " " + item.getLocation().getRoom() + " Address: " + item.getLocation().getAddress().getFullAddress());
        }
        // creating a facility user from the bean
        FacilityUser facilityUser = (FacilityUser)context.getBean("facilityuser");
		facilityUser.setFirstName("Rachel");
		facilityUser.setMiddleName("Louise");
		facilityUser.setLastName("Cundiff");
		facilityUser.setTitle("Ms.");
        System.out.println("FacilityUser: " + facilityUser.save());
        // Making a reservation        
        LocalDateTime start = LocalDateTime.now().minusHours(2); 
        LocalDateTime end = LocalDateTime.now(); 
        if (facility1.assignFacilityToUse(facilityUser,start, end)) { }
        // Gets facility with a constructor injection
        Facility facility2 = (Facility)context.getBean("facility_id",facility1.getID());
        // Prints the results
        System.out.println("Building: " + facility2.getLocation().getBuildingName());
        System.out.println("Room: " + facility2.getLocation().getRoom());
        System.out.println("Capacity: " + facility2.getCapacity());
        System.out.println("Address: " + facility2.getLocation().getAddress().getAddress());
        System.out.println("City: " + facility2.getLocation().getAddress().getCity());
        System.out.println("State: " + facility2.getLocation().getAddress().getState());
        System.out.println("Zip: " + facility2.getLocation().getAddress().getZip());
        // Prints the usage rate
        System.out.println("calcUsageRate: " + facilityManager.calcUsageRate(facility2, LocalDateTime.now()) + "%");
        TreeMap<String,Long> tree = facilityManager.listActualUsage();
        for (Entry<String, Long> entry: tree.entrySet()) {
        		System.out.println("listActualUsage_Facility: " + entry.getKey() + " ActualUsage: " + Duration.ofSeconds(entry.getValue()).toHours() + " hrs");
        }
        // Gets all the reservations
        Iterator<Reservation> res = facility2.getReservations().iterator();
        while(res.hasNext()) {
        		Reservation item = res.next();
        		System.out.println("Reservation_ID: " + item.getID() + "Facility_ID: " + item.getFacilityID() + " Start: " + item.getStart().toString() + " End: " + item.getEnd().toString());
        }
        // Prints the facility information
        System.out.println("Facility Information: " + facility1.getFacilityInformation());
        // Creates a inspector
        Inspector inspector = (Inspector)context.getBean("inspector");
		inspector.setFirstName("John");
		inspector.setMiddleName("Nikolas");
		inspector.setLastName("O'Sullivan");
		inspector.setTitle("Mr.");
        String in_id = inspector.save();
        System.out.println("<-- Inspector -->");
        // Gets the inspector just created from the constructor injections
        Inspector inspector2 = (Inspector)context.getBean("inspector_id",in_id);
        System.out.println("FirstName: " + inspector2.getFirstName());
        System.out.println("MiddleName: " + inspector2.getMiddleName());
        System.out.println("LastName: " + inspector2.getLastName());
        System.out.println("Title: " + inspector2.getTitle());
        System.out.println("ID: " + inspector2.getID());
        // Creates a inspetions
        Inspection inspection = (Inspection)context.getBean("inspection");
        inspection.setReport("There is a water pipe broken :/");
        inspection.setInspector(inspector);
        inspection.setDateTime(LocalDateTime.now());
        System.out.println("Inspection_ID: " + facility1.addInspection(inspection));
        // Lists all the inspections
        Iterator<Inspection> inspecs = facility1.listInspections().iterator();
        while(inspecs.hasNext()) {
        		Inspection item = inspecs.next();
        		System.out.println("Inspection_ID: " + item.getID() + " Facility_ID: " + item.getFacility() + " DateTime: " + item.getDateTime().toString() + " Report: " + item.getReport());
        }
        // Creates a maintenance request
        MaintenanceRequest maintenanceRequest = (MaintenanceRequest)context.getBean("maintenancerequest");
        maintenanceRequest.setFacility(facility2);
        maintenanceRequest.setProblem("Fix the broken pipe in the den");
        maintenanceRequest.setStatus(MaintenanceStatus.PENDING);
        maintenanceRequest.setStartDateTime(start);
        maintenanceRequest.setEndDateTime(end);
        maintenanceRequest.setCost(123.3);
        System.out.println("MaintenanceRequest_ID: " + maintenanceRequest.saveMaintenanceRequest());
        // Creates another maintenance request
        MaintenanceRequest maintenanceRequest2 = (MaintenanceRequest)context.getBean("maintenancerequest");
        maintenanceRequest2.setFacility(facility2);
        maintenanceRequest2.setProblem("It is really broken lol");
        maintenanceRequest2.setStatus(MaintenanceStatus.PROBLEM);
        maintenanceRequest2.setStartDateTime(start);
        maintenanceRequest2.setEndDateTime(end.minusHours(1));
        maintenanceRequest2.setCost(231.1);
        System.out.println("MaintenanceRequest2_ID: " + maintenanceRequest2.saveMaintenanceRequest());
        // Prints some maintenance stats
        System.out.println("calcMaintenanceCostForFacility: $" + maintenanceManager.calcMaintenanceCostForFacility(facility2));
        System.out.println("calcProblemRateForFacility: " + maintenanceManager.calcProblemRateForFacility(facility2) + "%");
        System.out.println("calcDownTimeForFacility: " + maintenanceManager.calcDownTimeForFacility(facility2) + " hrs");
        // Cleaning up the session
        if (!DEBUGGING) {
        		MongoClient mongoClient = new MongoClient();
        		MongoDatabase database = mongoClient.getDatabase(Configs.DB_NAME);
        		database.drop();
        		mongoClient.close();
        }
        // Closes the context
     	((ClassPathXmlApplicationContext) context).close();
	}
	
}
