package edu.comp373.model.maintenance;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import edu.comp373.model.facility.Address;
import edu.comp373.model.facility.Facility;
import edu.comp373.model.facility.Location;
import edu.comp373.model.maintenance.MaintenanceRequest.MaintenanceStatus;

public class MaintenanceTests {

	@Test
	public void maintenance_basic() {
		Address address = new Address();
        address.setAddress("1032 W Sheridan Rd");
        address.setCity("Chicago");
        address.setState("IL");
        address.setZip("60660");
        Location location = new Location();
        location.setAddress(address);
        location.setBuidlingName("Damen Student Center");
        location.setRoomName("RM 345");
        Facility facility = new Facility();
        facility.setCapacity(125);
        facility.setLocation(location);
		LocalDateTime start = LocalDateTime.now().minusHours(2); 
        LocalDateTime end = LocalDateTime.now(); 
		MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setFacility(facility);
        maintenanceRequest.setProblem("Fix the broken pipe in the den");
        maintenanceRequest.setStatus(MaintenanceStatus.PENDING);
        maintenanceRequest.setStartDateTime(start);
        maintenanceRequest.setEndDateTime(end);
        maintenanceRequest.setCost(123.3);
        assertEquals(facility,maintenanceRequest.getFacility());
        assertEquals("Fix the broken pipe in the den",maintenanceRequest.getProblem());
        assertEquals(MaintenanceStatus.PENDING,maintenanceRequest.getStatus());
        assertEquals(start,maintenanceRequest.getStartDateTime());
        assertEquals(end,maintenanceRequest.getEndDateTime());
        assertEquals(Double.valueOf(123.3),maintenanceRequest.getCost());
	}

}
