package edu.comp373.model.facility;

import org.junit.*;
import static org.junit.Assert.*;

import java.time.LocalDateTime;

public class FacilityTests {

	@Test
	public void basic_facility_constructor_test_1() {        
        Address address = new Address("1032 W Sheridan Rd", "Chicago","IL","60660");
        Location location = new Location("Damen Student Center","RM 345",address);   
        Facility facility = new Facility(location,125, LocalDateTime.now());
        
        assertEquals(Integer.valueOf(125),facility.getCapacity());
        assertEquals("Damen Student Center",facility.getLocation().getBuildingName());
        assertEquals("RM 345",facility.getLocation().getRoom());
        assertEquals("1032 W Sheridan Rd",facility.getLocation().getAddress().getAddress());
        assertEquals("Chicago",facility.getLocation().getAddress().getCity());
        assertEquals("IL",facility.getLocation().getAddress().getState());
        assertEquals("60660",facility.getLocation().getAddress().getZip());
        assertEquals("1032 W Sheridan Rd Chicago IL 60660",facility.getLocation().getAddress().getFullAddress());
	}
	
	@Test
	public void basic_facility_constructor_test_2() {        
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
        assertEquals(Integer.valueOf(125),facility.getCapacity());
        assertEquals("Damen Student Center",facility.getLocation().getBuildingName());
        assertEquals("RM 345",facility.getLocation().getRoom());
        assertEquals("1032 W Sheridan Rd",facility.getLocation().getAddress().getAddress());
        assertEquals("Chicago",facility.getLocation().getAddress().getCity());
        assertEquals("IL",facility.getLocation().getAddress().getState());
        assertEquals("60660",facility.getLocation().getAddress().getZip());
        assertEquals("1032 W Sheridan Rd Chicago IL 60660",facility.getLocation().getAddress().getFullAddress());
	}

}
