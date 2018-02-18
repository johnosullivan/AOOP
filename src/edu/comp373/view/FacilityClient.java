package edu.comp373.view;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.mongodb.MongoClient;
import edu.comp373.model.facility.*;
import edu.comp373.model.facility.Facility.DetailType;
import edu.comp373.model.inspections.Inspection;
import edu.comp373.model.inspections.Inspector;
import edu.comp373.model.maintenance.MaintenanceRequest;
import edu.comp373.model.maintenance.MaintenanceRequest.MaintenanceStatus;
import edu.comp373.model.manager.FacilityManager;
import edu.comp373.model.manager.MaintenanceManager;
import edu.comp373.model.reservations.Reservation;
import edu.comp373.dal.Configs;
import com.mongodb.client.MongoDatabase;
import java.util.Iterator;
import java.util.logging.Logger;
import java.util.logging.Level;

public class FacilityClient {
	
	static boolean DEBUGGING = true;

	public static void main(String[] args) {
		Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
		
		FacilityManager facilityManager = new FacilityManager();
		MaintenanceManager maintenanceManager = new MaintenanceManager();
		
		System.out.println("Advanced Object Oriented Programming (OOP)");
		
		LocalDateTime start = null, end = null, start_test = null, end_test = null;        
        
		Address address1 = new Address("40 E Oak Street","Chicago","IL","60091");
        Location location1 = new Location("Damen Student Center","RM 345",address1);
        Facility facility1 = new Facility(location1,125, start.now());
        
        // Adds new facility
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
        ArrayList<Facility> respond = facilityManager.requestAvailableCapacity(100);
        Iterator<Facility> iters = respond.iterator();
        System.out.println("Facility_RequestAvailableCapacity: " + respond.size());
        while(iters.hasNext()) {
        		Facility item = iters.next();
			System.out.println("Capacity: " + item.getCapacity() + " Location: " + item.getLocation().getBuildingName() + " " + item.getLocation().getRoom() + " Address: " + item.getLocation().getAddress().getFullAddress());
        }
      
        try{ 
	        start = LocalDateTime.now(); 
	        end = start.plusHours(2);
	        if (facility1.assignFacilityToUse(start,end)) { System.out.println("Assigned - In Use"); }
	        	start_test = LocalDateTime.now().minusHours(4); 
	            end_test = start_test.minusHours(2);
	        if (facility1.assignFacilityToUse(start_test,end_test)) { System.out.println("Assigned - In Use"); }
	        if (facility1.isInUseDuringInterval(start_test, end_test)) { System.out.println("Facility Is Free"); }
	        
        }catch(DateTimeException dtE){
        	dtE.getMessage();
        }
        
        Facility facility2 = new Facility(facility1.getID());
        System.out.println("Building: " + facility2.getLocation().getBuildingName());
        System.out.println("Room: " + facility2.getLocation().getRoom());
        System.out.println("Capacity: " + facility2.getCapacity());
        System.out.println("Address: " + facility2.getLocation().getAddress().getAddress());
        System.out.println("City: " + facility2.getLocation().getAddress().getCity());
        System.out.println("State: " + facility2.getLocation().getAddress().getState());
        System.out.println("Zip: " + facility2.getLocation().getAddress().getZip());
        
        Iterator<Reservation> res = facility2.getReservations().iterator();
        while(res.hasNext()) {
        		Reservation item = res.next();
        		System.out.println("Reservation_ID: " + item.getID() + "Facility_ID: " + item.getFacilityID() + " Start: " + item.getStart().toString() + " End: " + item.getEnd().toString());
        }
        
        System.out.println("Facility Information: " + facility1.getFacilityInformation());
        
        Inspector inspector = new Inspector("John","Nikolas","O'Sullivan","Mr.");
        String in_id = inspector.addInspector();
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
        maintenanceRequest2.setStatus(MaintenanceStatus.PENDING);
        maintenanceRequest2.setStartDateTime(start);
        maintenanceRequest2.setEndDateTime(end);
        maintenanceRequest2.setCost(231.1);
        System.out.println("MaintenanceRequest2_ID: " + maintenanceRequest2.saveMaintenanceRequest());
        
        System.out.println("calcMaintenanceCostForFacility: $" + maintenanceManager.calcMaintenanceCostForFacility(facility2));
        
        
        //if(!facility1.removeFacility()) { }
       
        if (!DEBUGGING) {
        		MongoClient mongoClient = new MongoClient();
        		MongoDatabase database = mongoClient.getDatabase(Configs.DB_NAME);
        		database.drop();
        		mongoClient.close();
        }
        
	}
	
}
