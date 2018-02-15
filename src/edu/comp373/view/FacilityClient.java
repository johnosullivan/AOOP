package edu.comp373.view;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.mongodb.MongoClient;
import edu.comp373.model.facility.*;
import edu.comp373.dal.Configs;
import com.mongodb.client.MongoDatabase;
import java.util.Iterator;


public class FacilityClient {
	
	static boolean DEBUGGING = true;

	public static void main(String[] args) {
		System.out.println("Advanced Object Oriented Programming (OOP)");
                
        Address address1 = new Address("40 E Oak Street","Chicago","IL","60091");
        Location location1 = new Location("Damen Student Center","RM 345",address1);
        Facility facility1 = new Facility(location1,125);
        
        String ID = facility1.saveFacility();
        System.out.println("Facility ID: " + ID);
                
        ArrayList<Facility> list = Facility.listFacilities();
        Iterator<Facility> iters_list = list.iterator();
        System.out.println("listFacilities_count: " + list.size());
        
        while(iters_list.hasNext()) {
			Facility item = iters_list.next();
			System.out.println("Capacity: " + item.getCapacity() + " Location: " + item.getLocation().getBuildingName() + " " + item.getLocation().getRoom() + " Address: " + item.getLocation().getAddress().getFullAddress());
		}
        
        ArrayList<Facility> respond = Facility.requestAvailableCapacity(100);
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
        
        if(!facility1.removeFacility()) { }
       
        if (!DEBUGGING) {
        		MongoClient mongoClient = new MongoClient();
        		MongoDatabase database = mongoClient.getDatabase(Configs.DB_NAME);
        		database.drop();
        		mongoClient.close();
        }
        
	}
	
}
