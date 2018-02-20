package edu.comp373.model.manager;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

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
import edu.comp373.model.reservations.Reservation;
import edu.comp373.model.users.FacilityUser;
import edu.comp373.model.users.Inspector;

public class TestCase {

	@Test
	public void test_Scenario() {	
		Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
		
		FacilityManager facilityManager = new FacilityManager();
		MaintenanceManager maintenanceManager = new MaintenanceManager();
		                
        Address address1 = new Address("40 E Oak Street","Chicago","IL","60091");
        Location location1 = new Location("Damen Student Center","RM 345",address1);
        Facility facility1 = new Facility(location1,125, LocalDateTime.now().minusHours(12));
        
        assertEquals("Damen Student Center",facility1.getLocation().getBuildingName());
        assertEquals("RM 345",facility1.getLocation().getRoom());
        assertEquals("40 E Oak Street",facility1.getLocation().getAddress().getAddress());
        assertEquals("Chicago",facility1.getLocation().getAddress().getCity());
        assertEquals("IL",facility1.getLocation().getAddress().getState());
        assertEquals("60091",facility1.getLocation().getAddress().getZip());
        assertEquals("40 E Oak Street Chicago IL 60091",facility1.getLocation().getAddress().getFullAddress());
        
        facility1.addDetail(DetailType.CAPACITY, 120);
        facility1 = facilityManager.addNewFacility(facility1);
        System.out.println("Facility ID: " + facility1.getID());
        
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
         
        FacilityUser facilityUser = new FacilityUser("Rachel","Louise","Cundiff","Ms.");
        System.out.println("FacilityUser: " + facilityUser.save());
        
                LocalDateTime start = LocalDateTime.now().minusHours(2); 
        LocalDateTime end = LocalDateTime.now(); 
        if (facility1.assignFacilityToUse(facilityUser,start, end)) { }

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
        
        ArrayList<Inspection> inspecs = facility1.listInspections();
        assertEquals(1,inspecs.size());
       
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
           
        assertEquals(Double.valueOf(354.4),maintenanceManager.calcMaintenanceCostForFacility(facility2));
        assertEquals(Double.valueOf(50.0),maintenanceManager.calcProblemRateForFacility(facility2));
        assertEquals(Double.valueOf(3.0),maintenanceManager.calcDownTimeForFacility(facility2));
        
        MongoClient mongoClient = new MongoClient();
        	MongoDatabase database = mongoClient.getDatabase(Configs.DB_NAME);
        	database.drop();
        	mongoClient.close();
        
	}

}
