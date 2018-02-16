package edu.comp373.model.manager;

import java.util.ArrayList;
import java.util.Iterator;

import edu.comp373.dal.facility.FacilityDAO;
import edu.comp373.dal.reservations.ReservationDAO;
import edu.comp373.model.facility.Facility;
import edu.comp373.model.facility.Facility.DetailType;
import edu.comp373.model.reservations.Reservation;

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
	
	public ArrayList<Facility> vacateFacility() {
		ArrayList<Facility> respond = new ArrayList<Facility>();
		
		return respond;
	}
	
	public Facility addNewFacility(Facility facility) {
		String id = facility.save();
		facility.setID(id);
		return facility;
	}
	
	public void addFacilityDetail(Facility facility, DetailType type,Object obj) {
		facility.addDetail(type, obj);
	}
	
	public boolean removeFacility(Facility facility) {
		return facility.remove();
	}
	
	public ArrayList<Reservation> getAllReservation(String id) {
		return reservationDAO.getAllReservations(id);
	}
	
}
