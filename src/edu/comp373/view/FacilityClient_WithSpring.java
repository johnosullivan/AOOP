package edu.comp373.view;

import java.time.Duration;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;

import edu.comp373.model.facility.*;
import edu.comp373.model.facility.Facility.DetailType;
import edu.comp373.model.inspections.Inspection;
import edu.comp373.model.maintenance.MaintenanceRequest;
import edu.comp373.model.maintenance.MaintenanceRequest.MaintenanceStatus;
import edu.comp373.model.manager.FacilityManager;
import edu.comp373.model.manager.MaintenanceManager;
import edu.comp373.model.reservations.Reservation;
import edu.comp373.model.users.FacilityUser;
import edu.comp373.model.users.Inspector;
import edu.comp373.dal.Configs;
import com.mongodb.client.MongoDatabase;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.logging.Logger;
import java.util.logging.Level;

public class FacilityClient_WithSpring {
	
	static boolean DEBUGGING = false;

	public static void main(String[] args) {
		Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");
		System.out.println("***************** Application Context instantiated! ******************");
		
		
		Address address = (Address)context.getBean("address");
		
		Location location = (Location)context.getBean("location", "", "", address);
		
		Location location_basic = (Location)context.getBean("location_basic");
		
		
		Inspection inspection = (Inspection)context.getBean("inspection");
		
		FacilityUser facilityuser = (FacilityUser)context.getBean("facilityuser");
		
		Inspector inspector = (Inspector)context.getBean("inspector");
		
		Reservation reservation = (Reservation)context.getBean("reservation");
		
		LocalDateTime start = LocalDateTime.now().minusHours(2); 
        LocalDateTime end = LocalDateTime.now();
        
        LocalTimeRange range = (LocalTimeRange)context.getBean("localdatetimerange",start,end);

		Facility facility = (Facility) context.getBean("facility");
		
		MaintenanceRequest maintenancerequest = (MaintenanceRequest)context.getBean("maintenancerequest");
		
		FacilityManager facilitymanager = (FacilityManager)context.getBean("facilitymanager");
		
		MaintenanceManager maintenancemanager = (MaintenanceManager)context.getBean("maintenancemanager");
	
	}
	
}
