package edu.comp373.view;

import java.time.Duration;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.mongodb.MongoClient;
import edu.comp373.model.facility.*;
import edu.comp373.model.facility.Facility.DetailType;
import edu.comp373.model.inspections.Inspection;
import edu.comp373.model.maintenance.MaintenanceRequest;
import edu.comp373.model.maintenance.MaintenanceRequest.MaintenanceStatus;
import edu.comp373.model.manager.FacilityManager;
import edu.comp373.model.manager.MaintenanceManager;
import edu.comp373.model.reservations.Reservation;
import edu.comp373.model.users.FacilityUser;
import edu.comp373.model.users.Inspector;
import edu.comp373.dal.Configs;
import com.mongodb.client.MongoDatabase;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.logging.Logger;
import java.util.logging.Level;

public class FacilityClient {
	
	static boolean DEBUGGING = false;

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");
		
		
		/*
		Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
		
		FacilityManager facilityManager = new FacilityManager();
		MaintenanceManager maintenanceManager = new MaintenanceManager();
		
		System.out.println("Advanced Object Oriented Programming (OOP)");
                
        Address address1 = new Address("40 E Oak Street","Chicago","IL","60091");
        Location location1 = new Location("Damen Student Center","RM 345",address1);
        Facility facility1 = new Facility(location1,125, LocalDateTime.now().minusHours(12));
        
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
         
        FacilityUser facilityUser = new FacilityUser("Rachel","Louise","Cundiff","Ms.");
        System.out.println("FacilityUser: " + facilityUser.save());
        
        //facilityUser = facilityManager.addFacilityUser(facilityUser);
        
        LocalDateTime start = LocalDateTime.now().minusHours(2); 
        LocalDateTime end = LocalDateTime.now(); 
        if (facility1.assignFacilityToUse(facilityUser,start, end)) { }
        
        
        //if (facility1.assignFacilityToUse(start,end)) { System.out.println("Assigned - In Use"); }
        //LocalDateTime start_test = LocalDateTime.now().minusHours(4); 
        //LocalDateTime end_test = start_test.minusHours(2);
        //if (facility1.assignFacilityToUse(start_test,end_test)) { System.out.println("Assigned - In Use"); }
        //if (facility1.isInUseDuringInterval(start_test, end_test)) { System.out.println("Facility Is Free"); }
        
        Facility facility2 = new Facility(facility1.getID());
        System.out.println("Building: " + facility2.getLocation().getBuildingName());
        System.out.println("Room: " + facility2.getLocation().getRoom());
        System.out.println("Capacity: " + facility2.getCapacity());
        System.out.println("Address: " + facility2.getLocation().getAddress().getAddress());
        System.out.println("City: " + facility2.getLocation().getAddress().getCity());
        System.out.println("State: " + facility2.getLocation().getAddress().getState());
        System.out.println("Zip: " + facility2.getLocation().getAddress().getZip());
        
        System.out.println("calcUsageRate: " + facilityManager.calcUsageRate(facility2, LocalDateTime.now()) + "%");
        
        TreeMap<String,Long> tree = facilityManager.listActualUsage();
        for (Entry<String, Long> entry: tree.entrySet()) {
        		System.out.println("listActualUsage_Facility: " + entry.getKey() + " ActualUsage: " + Duration.ofSeconds(entry.getValue()).toHours() + " hrs");
        }
        
        
        Iterator<Reservation> res = facility2.getReservations().iterator();
        while(res.hasNext()) {
        		Reservation item = res.next();
        		System.out.println("Reservation_ID: " + item.getID() + "Facility_ID: " + item.getFacilityID() + " Start: " + item.getStart().toString() + " End: " + item.getEnd().toString());
        }
        
        System.out.println("Facility Information: " + facility1.getFacilityInformation());
        
        Inspector inspector = new Inspector("John","Nikolas","O'Sullivan","Mr.");
        String in_id = inspector.save();
        Inspector inspector2 = new Inspector(in_id);
        System.out.println("FirstName: " + inspector2.getFirstName());
        System.out.println("MiddleName: " + inspector2.getMiddleName());
        System.out.println("LastName: " + inspector2.getLastName());
        System.out.println("Title: " + inspector2.getTitle());
        System.out.println("ID: " + inspector2.getID());
        
        Inspection inspection = new Inspection();
        inspection.setReport("There is a water pipe broken :/");
        inspection.setInspector(inspector);
        inspection.setDateTime(LocalDateTime.now());
        System.out.println("Inspection_ID: " + facility1.addInspection(inspection));
        
        Iterator<Inspection> inspecs = facility1.listInspections().iterator();
        while(inspecs.hasNext()) {
        		Inspection item = inspecs.next();
        		System.out.println("Inspection_ID: " + item.getID() + " Facility_ID: " + item.getFacility() + " DateTime: " + item.getDateTime().toString() + " Report: " + item.getReport());
        }
       
        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setFacility(facility2);
        maintenanceRequest.setProblem("Fix the broken pipe in the den");
        maintenanceRequest.setStatus(MaintenanceStatus.PENDING);
        maintenanceRequest.setStartDateTime(start);
        maintenanceRequest.setEndDateTime(end);
        maintenanceRequest.setCost(123.3);
        System.out.println("MaintenanceRequest_ID: " + maintenanceRequest.saveMaintenanceRequest());
        
        MaintenanceRequest maintenanceRequest2 = new MaintenanceRequest();
        maintenanceRequest2.setFacility(facility2);
        maintenanceRequest2.setProblem("It is really broken lol");
        maintenanceRequest2.setStatus(MaintenanceStatus.PROBLEM);
        maintenanceRequest2.setStartDateTime(start);
        maintenanceRequest2.setEndDateTime(end.minusHours(1));
        maintenanceRequest2.setCost(231.1);
        System.out.println("MaintenanceRequest2_ID: " + maintenanceRequest2.saveMaintenanceRequest());
        
        System.out.println("calcMaintenanceCostForFacility: $" + maintenanceManager.calcMaintenanceCostForFacility(facility2));
        System.out.println("calcProblemRateForFacility: " + maintenanceManager.calcProblemRateForFacility(facility2) + "%");
        System.out.println("calcDownTimeForFacility: " + maintenanceManager.calcDownTimeForFacility(facility2) + " hrs");
               
        if (!DEBUGGING) {
        		MongoClient mongoClient = new MongoClient();
        		MongoDatabase database = mongoClient.getDatabase(Configs.DB_NAME);
        		database.drop();
        		mongoClient.close();
        }
        */
        
	}
	
}
