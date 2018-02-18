package edu.comp373.model.manager;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
//import java.util.Map;
import java.util.TreeMap;

import edu.comp373.dal.facility.FacilityDAO;
import edu.comp373.dal.reservations.ReservationDAO;
import edu.comp373.model.facility.Facility;
import edu.comp373.model.facility.Facility.DetailType;
import edu.comp373.model.reservations.Reservation;
import edu.comp373.model.users.FacilityUser;
 

public class FacilityManager implements FacilityManagerInterface {

	/* Reservation Data Access Object */
	private ReservationDAO reservationDAO = new ReservationDAO();
	private FacilityDAO facilityDAO = new FacilityDAO();
	
	public FacilityManager() {
		
	}
	
	public ArrayList<Facility> listFacilities() {
		return facilityDAO.listFacilities();
	}
	
	public ArrayList<Facility> requestAvailableCapacity(Integer capslimit) {
		ArrayList<Facility> respond = new ArrayList<Facility>();
		Iterator<Facility> iters = facilityDAO.listFacilities().iterator();
		
		while(iters.hasNext()) {
			Facility facilityObj = iters.next();
			if (capslimit <= facilityObj.getCapacity()) {
				respond.add(facilityObj);
			}
		}
		return respond;
	} 
	
	public ArrayList<Facility> vacateFacility(final LocalDateTime start, final LocalDateTime end) {
		ArrayList<Facility> respond = new ArrayList<Facility>();
		Iterator<Facility> iters = facilityDAO.listFacilities().iterator();
		while(iters.hasNext()) {
			Facility facilityObj = iters.next();
			if (facilityObj.isInUseDuringInterval(start, end)) {
				respond.add(facilityObj);
			}
		}
		return respond;
	}
	
	public Facility addNewFacility(Facility facility) {
		String id = facility.save();
		facility.setID(id);
		return facility;
	}
	
	public FacilityUser addFacilityUser(FacilityUser facilityUser) {
		String id = facilityUser.save();
		facilityUser.setID(id);
		return facilityUser;
	}
	
	
	public void addFacilityDetail(Facility facility, DetailType type,Object obj) {
		facility.addDetail(type, obj);
	}
	
	public boolean removeFacility(Facility facility) {
		return facility.remove();
	}
	
	public ArrayList<Reservation> getAllReservation(Facility facility) {
		return reservationDAO.getAllReservations(facility.getID());
	}
	
	public TreeMap<String,Long> listActualUsage() {
		
		TreeMap<String,Long> listActualUsage = new TreeMap<String,Long>();
		
		Iterator<Facility> list = this.listFacilities().iterator();
		
		while(list.hasNext()) {
			Facility facility = list.next();
			
			Iterator<Reservation> reservations = facility.getAllReservation().iterator();
			long seconds = 0;
			while(reservations.hasNext()) {
				Reservation item = reservations.next();
				Duration dur = Duration.between(item.getStart(), item.getEnd());
				seconds = seconds + dur.getSeconds();
			}
			listActualUsage.put(facility.getLocation().getBuildingName() + " - " + facility.getLocation().getRoom(), seconds);
		}
	
		return listActualUsage;
	}
	
	public Double calcUsageRate(Facility facility, LocalDateTime datetime) {
		
		Iterator<Reservation> reservations = facility.getAllReservation().iterator();
		long seconds = 0;
		long total = Duration.between(facility.getCreated(), datetime).getSeconds();
		while(reservations.hasNext()) {
			Reservation item = reservations.next();
			Duration dur = Duration.between(item.getStart(), item.getEnd());
			seconds = seconds + dur.getSeconds();
		}
				
		return ((double)seconds/(double)total) * 100;
	}
	
}
