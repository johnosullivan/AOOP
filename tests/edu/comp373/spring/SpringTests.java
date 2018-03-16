package edu.comp373.spring;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.comp373.model.facility.Address;
import edu.comp373.model.facility.Facility;
import edu.comp373.model.facility.Location;
import edu.comp373.model.inspections.Inspection;
import edu.comp373.model.maintenance.MaintenanceRequest;
import edu.comp373.model.maintenance.MaintenanceRequest.MaintenanceStatus;
import edu.comp373.model.reservations.Reservation;
import edu.comp373.model.users.FacilityUser;
import edu.comp373.model.users.Inspector;

public class SpringTests {

	ApplicationContext context;
	
	@Before
	public void setup() {
		context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");
	}
	
	@Test
	public void address_test() {
		Address address = (Address)context.getBean("address");
		address.setAddress("1032 W Sheridan Rd");
        address.setCity("Chicago");
        address.setState("IL");
        address.setZip("60660");
        assertEquals("1032 W Sheridan Rd",address.getAddress());
        assertEquals("Chicago",address.getCity());
        assertEquals("IL",address.getState());
        assertEquals("60660",address.getZip());
	}
	
	@Test
	public void address_location_test() {
		Address address = (Address)context.getBean("address");
		address.setAddress("1032 W Sheridan Rd");
        address.setCity("Chicago");
        address.setState("IL");
        address.setZip("60660");
        Location location = (Location)context.getBean("location_basic");
        location.setAddress(address);
        location.setBuidlingName("Damen Student Center");
        location.setRoomName("RM 345");
        assertEquals("Damen Student Center",location.getBuildingName());
        assertEquals("RM 345",location.getRoom());
        assertEquals("1032 W Sheridan Rd",location.getAddress().getAddress());
        assertEquals("Chicago",location.getAddress().getCity());
        assertEquals("IL",location.getAddress().getState());
        assertEquals("60660",location.getAddress().getZip());
        assertEquals("1032 W Sheridan Rd Chicago IL 60660",location.getAddress().getFullAddress());
	}
	
	@Test
	public void facility_test() {
		Address address = (Address)context.getBean("address");
		address.setAddress("1032 W Sheridan Rd");
        address.setCity("Chicago");
        address.setState("IL");
        address.setZip("60660");
        Location location = (Location)context.getBean("location_basic");
        location.setAddress(address);
        location.setBuidlingName("Damen Student Center");
        location.setRoomName("RM 345");
        Facility facility = (Facility)context.getBean("facility");
        facility.setCapacity(125);
        facility.setLocation(location);
        assertEquals(Integer.valueOf(125),facility.getCapacity());
        assertEquals("Damen Student Center",location.getBuildingName());
        assertEquals("RM 345",location.getRoom());
        assertEquals("1032 W Sheridan Rd",location.getAddress().getAddress());
        assertEquals("Chicago",location.getAddress().getCity());
        assertEquals("IL",location.getAddress().getState());
        assertEquals("60660",location.getAddress().getZip());
        assertEquals("1032 W Sheridan Rd Chicago IL 60660",location.getAddress().getFullAddress());
	}
	
	@Test
	public void inspector() {
		Inspector inspector = (Inspector)context.getBean("inspector");
		inspector.setFirstName("John");
		inspector.setMiddleName("Nikolas");
		inspector.setLastName("O'Sullivan");
		inspector.setTitle("Mr.");
		assertEquals("John",inspector.getFirstName());
		assertEquals("Nikolas",inspector.getMiddleName());
		assertEquals("O'Sullivan",inspector.getLastName());
		assertEquals("Mr.",inspector.getTitle());
	}
		
	@Test 
	public void facilityuser_test() {
		FacilityUser user = (FacilityUser)context.getBean("facilityuser");
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
		Inspector user = (Inspector)context.getBean("inspector");
		user.setFirstName("John");
		user.setMiddleName("Nikolas");
		user.setLastName("O'Sullivan");
		user.setTitle("Mr.");
		assertEquals("John",user.getFirstName());
		assertEquals("Nikolas",user.getMiddleName());
		assertEquals("O'Sullivan",user.getLastName());
		assertEquals("Mr.",user.getTitle());
		Inspection inspection = (Inspection)context.getBean("inspection");
		inspection.setInspector(user);
		LocalDateTime datetime = LocalDateTime.now().minusHours(2); 
		inspection.setDateTime(datetime);
		inspection.setReport("some report");
		inspection.setID("theid");
		inspection.setFacility("facility");
		assertEquals(datetime,inspection.getDateTime());
		assertEquals("facility",inspection.getFacility());
		assertEquals("some report",inspection.getReport());
		assertEquals("theid",inspection.getID());
	}
	
	@Test 
	public void reservation_test() {
		FacilityUser user = (FacilityUser)context.getBean("facilityuser");
		user.setFirstName("John");
		user.setMiddleName("Nikolas");
		user.setLastName("O'Sullivan");
		user.setTitle("Mr.");
		LocalDateTime start = LocalDateTime.now().minusHours(2); 
        LocalDateTime end = LocalDateTime.now(); 
		Reservation reservation = (Reservation)context.getBean("reservation");
		reservation.setFacilityUser(user);
		reservation.setStart(start);
		reservation.setEnd(end);
		assertEquals(start,reservation.getStart());
		assertEquals(end,reservation.getEnd());
		assertEquals("John",reservation.getFacilityUser().getFirstName());
		assertEquals("Nikolas",reservation.getFacilityUser().getMiddleName());
		assertEquals("O'Sullivan",reservation.getFacilityUser().getLastName());
		assertEquals("Mr.",reservation.getFacilityUser().getTitle());
	}
	
	@Test 
	public void maintenancerequest_test() {
		Address address = (Address)context.getBean("address");
		address.setAddress("1032 W Sheridan Rd");
        address.setCity("Chicago");
        address.setState("IL");
        address.setZip("60660");
        Location location = (Location)context.getBean("location_basic");
        location.setAddress(address);
        location.setBuidlingName("Damen Student Center");
        location.setRoomName("RM 345");
        Facility facility = (Facility)context.getBean("facility");
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
        assertEquals(Double.valueOf(123.3),maintenanceRequest.getCost());
        assertEquals(start,maintenanceRequest.getStartDateTime());
        assertEquals(end,maintenanceRequest.getEndDateTime());
        assertEquals("Fix the broken pipe in the den",maintenanceRequest.getProblem());
        assertEquals(MaintenanceStatus.PENDING,maintenanceRequest.getStatus());
        assertEquals(Integer.valueOf(125),maintenanceRequest.getFacility().getCapacity());
        assertEquals("Damen Student Center",maintenanceRequest.getFacility().getLocation().getBuildingName());
        assertEquals("RM 345",maintenanceRequest.getFacility().getLocation().getRoom());
        assertEquals("1032 W Sheridan Rd",maintenanceRequest.getFacility().getLocation().getAddress().getAddress());
        assertEquals("Chicago",maintenanceRequest.getFacility().getLocation().getAddress().getCity());
        assertEquals("IL",maintenanceRequest.getFacility().getLocation().getAddress().getState());
        assertEquals("60660",maintenanceRequest.getFacility().getLocation().getAddress().getZip());
        assertEquals("1032 W Sheridan Rd Chicago IL 60660",maintenanceRequest.getFacility().getLocation().getAddress().getFullAddress());
	}

}
