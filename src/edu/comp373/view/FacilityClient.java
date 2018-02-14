package edu.comp373.view;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import edu.comp373.model.facility.*;
import edu.comp373.dal.Configs;
import edu.comp373.dal.facility.*;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import java.util.Iterator;

import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

public class FacilityClient {
	
	static boolean DEBUGGING = true;

	public static void main(String[] args) {
		System.out.println("Advanced Object Oriented Programming (OOP)");
        
        FacilityDAO facilityDAO = new FacilityDAO();
        
        Address address1 = new Address("40 E Oak Street","Chicago","IL","60091");
        Location location1 = new Location("Damen Student Center","RM 345",address1);
        Facility facility1 = new Facility(location1,125);
        
        String ID = facility1.save();
        System.out.println("Facility ID: " + ID);
                
        ArrayList<Facility> list = facilityDAO.listFacilities();
        System.out.println("listFacilities_count: " + list.size());
        
        for (int x = 0; x < list.size(); x++) {
			Facility item = list.get(x);
			System.out.println("Capacity: " + item.getCapacity() + " Location: " + item.getLocation().getBuildingName() + " " + item.getLocation().getRoom() + " Address: " + item.getLocation().getAddress().getFullAddress());
		}
         
        LocalDateTime start = LocalDateTime.now(); 
        LocalDateTime end = start.plusHours(2);
                
        if (facility1.assignFacilityToUse(start,end)) { System.out.println("Assigned - In Use"); }
        
        LocalDateTime start_test = LocalDateTime.now().minusHours(4); 
        LocalDateTime end_test = start_test.minusHours(2);
        
        if (facility1.assignFacilityToUse(start_test,end_test)) { System.out.println("Assigned - In Use"); }
        
        if (facility1.isInUseDuringInterval(start_test, end_test)) { System.out.println("Facility Is Free"); }
        
        FacilityDAO dao = new FacilityDAO();
        
        Facility facility = new Facility(ID);
        
        Iterator<Reservation> res = facility.getReservations().iterator();
        
        while(res.hasNext()) {
        		Reservation item = res.next();
        		System.out.println("Reservation_ID: " + item.getID() + "Facility_ID: " + item.getFacilityID() + " Start: " + item.getStart().toString() + " End: " + item.getEnd().toString());
        }
       
        if (!DEBUGGING) {
        		MongoClient mongoClient = new MongoClient();
        		MongoDatabase database = mongoClient.getDatabase(Configs.DB_NAME);
        		database.drop();
        		mongoClient.close();
        }
        
	}
	
}
