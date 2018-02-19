package edu.comp373.model.facility;

import org.junit.*;

import edu.comp373.model.users.FacilityUser;
import edu.comp373.model.users.Inspector;

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
	
	@Test 
	public void facility_test() {
		FacilityUser user = new FacilityUser();
		user.setFirstName("John");
		user.setMiddleName("Nikolas");
		user.setLastName("O'Sullivan");
		user.setTitle("Mr.");
		assertEquals("John",user.getFirstName());
		assertEquals("Nikolas",user.getMiddleName());
		assertEquals("O'Sullivan",user.getLastName());
		assertEquals("Mr.",user.getTitle());
	}
	
	@Test 
	public void inspection_test() {
		Inspector user = new Inspector();
		user.setFirstName("John");
		user.setMiddleName("Nikolas");
		user.setLastName("O'Sullivan");
		user.setTitle("Mr.");
		assertEquals("John",user.getFirstName());
		assertEquals("Nikolas",user.getMiddleName());
		assertEquals("O'Sullivan",user.getLastName());
		assertEquals("Mr.",user.getTitle());
	}
	
	
	@Test 
	public void time_overlay() {
		LocalDateTime start = LocalDateTime.now().minusHours(2); 
        LocalDateTime end = LocalDateTime.now(); 
		LocalTimeRange range = new LocalTimeRange(start,end);
		LocalDateTime start2 = LocalDateTime.now().minusHours(4); 
        LocalDateTime end2 = LocalDateTime.now(); 
		LocalTimeRange range2 = new LocalTimeRange(start2,end2);
		assertEquals(true,range.overlaps(range2));
	}
	
	@Test 
	public void reservation_test() {
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
        FacilityUser user = new FacilityUser();
		user.setFirstName("John");
		user.setMiddleName("Nikolas");
		user.setLastName("O'Sullivan");
		user.setTitle("Mr.");
        //LocalDateTime start = LocalDateTime.now().minusHours(2); 
        //LocalDateTime end = LocalDateTime.now(); 
        //if (facility.assignFacilityToUse(user,start, end)) { }
	}
	


}
