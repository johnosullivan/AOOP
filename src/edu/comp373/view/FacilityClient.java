package edu.comp373.view;

import java.time.LocalDateTime;
import java.util.ArrayList;

import edu.comp373.model.facility.*;
import edu.comp373.dal.facility.*;

public class FacilityClient {

	public static void main(String[] args) {
		System.out.println("Advanced Object Oriented Programming (OOP)");
        
        FacilityDAO facilityDAO = new FacilityDAO();
        
        Address address1 = new Address("40 E Oak Street","Chicago","IL","60091");
        Location location1 = new Location("Damen Student Center","RM 345",address1);
        Facility facility1 = new Facility(location1,125);
        
        System.out.println("Facility ID: " + facility1.getID());
        if (!facilityDAO.addNewFacility(facility1)) { System.out.println("Error Adding Facility"); }
        
        
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
        
        if (facility1.isInUseDuringInterval(start_test, end_test)) { System.out.println("Facility Is Free"); }
	}
	
}
