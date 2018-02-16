package edu.comp373.view;

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
import edu.comp373.model.reservations.Reservation;
import edu.comp373.dal.Configs;
import com.mongodb.client.MongoDatabase;
import java.util.Iterator;
import java.util.logging.Logger;
import java.util.logging.Level;

public class FacilityClient {
	
	static boolean DEBUGGING = false;

	public static void main(String[] args) {
		Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE); 
		
		System.out.println("Advanced Object Oriented Programming (OOP)");
                
        Address address1 = new Address("40 E Oak Street","Chicago","IL","60091");
        Location location1 = new Location("Damen Student Center","RM 345",address1);
        Facility facility1 = new Facility(location1,125);
        facility1.addDetail(DetailType.CAPACITY, 120);
        
        String ID = facility1.save();
        System.out.println("Facility ID: " + ID);
        
        FacilityManager facilityManager = new FacilityManager();
                
        ArrayList<Facility> list = facilityManager.listFacilities();
        Iterator<Facility> iters_list = list.iterator();
        System.out.println("listFacilities_count: " + list.size());
        
        while(iters_list.hasNext()) {
			Facility item = iters_list.next();
			System.out.println("Capacity: " + item.getCapacity() + " Location: " + item.getLocation().getBuildingName() + " " + item.getLocation().getRoom() + " Address: " + item.getLocation().getAddress().getFullAddress());
		}
        
        ArrayList<Facility> respond = facilityManager.requestAvailableCapacity(100);
        Iterator<Facility> iters = respond.iterator();
        System.out.println("Facility_RequestAvailableCapacity: " + respond.size());
        while(iters.hasNext()) {
        		Facility item = iters.next();
			System.out.println("Capacity: " + item.getCapacity() + " Location: " + item.getLocation().getBuildingName() + " " + item.getLocation().getRoom() + " Address: " + item.getLocation().getAddress().getFullAddress());
        }
         
        LocalDateTime start = LocalDateTime.now(); 
        LocalDateTime end = start.plusHours(2);
                
        if (facility1.assignFacilityToUse(start,end)) { System.out.println("Assigned - In Use"); }
        
        LocalDateTime start_test = LocalDateTime.now().minusHours(4); 
        LocalDateTime end_test = start_test.minusHours(2);
        
        if (facility1.assignFacilityToUse(start_test,end_test)) { System.out.println("Assigned - In Use"); }
        if (facility1.isInUseDuringInterval(start_test, end_test)) { System.out.println("Facility Is Free"); }
          
        Facility facility = new Facility(ID);
        System.out.println("Building: " + facility.getLocation().getBuildingName());
        System.out.println("Room: " + facility.getLocation().getRoom());
        System.out.println("Capacity: " + facility.getCapacity());
        System.out.println("Address: " + facility.getLocation().getAddress().getAddress());
        System.out.println("City: " + facility.getLocation().getAddress().getCity());
        System.out.println("State: " + facility.getLocation().getAddress().getState());
        System.out.println("Zip: " + facility.getLocation().getAddress().getZip());
        
        Iterator<Reservation> res = facility.getReservations().iterator();
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
        
        Facility facility2 = new Facility(facility1.getID());
        Iterator<Inspection> inspecs = facility2.listInspections().iterator();
        while(inspecs.hasNext()) {
        		Inspection item = inspecs.next();
        		System.out.println("Inspection_ID: " + item.getID() + "Facility_ID: " + item.getFacility() + " DateTime: " + item.getDateTime().toString() + " Report: " + item.getReport());
        }
       
        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setFacility(facility2);
        maintenanceRequest.setProblem("Fix the broken pipe in the den");
        maintenanceRequest.setStatus(MaintenanceStatus.PENDING);
        maintenanceRequest.setDateTime(LocalDateTime.now());
        System.out.println("MaintenanceRequest_ID: " + maintenanceRequest.saveMaintenanceRequest());
        
        
        //if(!facility1.removeFacility()) { }
       
        if (!DEBUGGING) {
        		MongoClient mongoClient = new MongoClient();
        		MongoDatabase database = mongoClient.getDatabase(Configs.DB_NAME);
        		database.drop();
        		mongoClient.close();
        }
        
	}
	
}
