package edu.comp373.model.facility;

import edu.comp373.dal.facility.*;
import edu.comp373.dal.inspections.InspectionDAO;
import edu.comp373.dal.reservations.ReservationDAO;
import edu.comp373.model.inspections.Inspection;
import edu.comp373.model.reservations.Reservation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import com.mongodb.BasicDBObject;

public class Facility implements FacilityInterface {

	private Location location;
	private Integer capacity;
	private String id;
	
	private FacilityDAO facilityDAO = new FacilityDAO();
	private ReservationDAO reservationDAO = new ReservationDAO();
	private InspectionDAO inspectionDAO = new InspectionDAO();
	
	public enum FeatureType {
		HAS_STAGE,
		HAS_PROJECTOR,
		HAS_HDMI_SUPPORT,
		HAS_INTERNET,
		HAS_TV,
		HAS_WHITEBOARD,
		HAS_TABLES,
		HAS_SOUND_SYSTEM
	}
	
	public enum DetailType {
		CAPACITY,
		LOCATION_BUILDING,
		LOCATION_ROOM,
		ADDRESS_ADDRESS,
		ADDRESS_CITY,
		ADDRESS_STATE,
		ADSRESS_ZIP
	}
	
	private ArrayList<FeatureType> features = new ArrayList<FeatureType>();
	private ArrayList<Reservation> reservations = new ArrayList<Reservation>();
	private ArrayList<Inspection> inspections = new ArrayList<Inspection>();
	
	public Facility() { }
	
	public Facility(String _id) { 
		
		BasicDBObject facilityDAO = this.facilityDAO.getFacilityByID(_id);
		BasicDBObject address = (BasicDBObject)facilityDAO.get("address");
		BasicDBObject location = (BasicDBObject)facilityDAO.get("location");
		
		this.id = "" + facilityDAO.get("_id");
		this.capacity = facilityDAO.getInt("capacity");
		
		Address addressObj = new Address(address.getString("address"),address.getString("city"),address.getString("state"),address.getString("zip"));
		this.location = new Location(location.getString("buildingname"),location.getString("room"),addressObj);
		
		this.reservations = reservationDAO.getAllReservations(this.id);
		this.inspections = inspectionDAO.getAllInspections(this.id);
	}
	
	public Facility(final Location location, final Integer capacity) {
		this.location = location;
		this.capacity = capacity;
		this.id = UUID.randomUUID().toString();
	}
	
	public void addFacilityDetail(DetailType type,Object obj) {
		switch (type) {
        		case CAPACITY: 
        			this.setCapacity((Integer)obj);
        			break;
        		default:
        			break;
		}
	}
	
	public void addFeatures(FeatureType feature) {
		features.add(feature);
	}
	
	public Location getLocation() { return this.location; }
	public void setLocation(Location location) { this.location = location; }
	
	
	public String getFacilityInformation() { 
		Address address = this.getLocation().getAddress();
		return "Facility: " + location.getBuildingName() + " - " + location.getRoom() + " Capacity: " + this.capacity + "\nAddress: " + address.getAddress() + " " + address.getCity() + ", " + address.getState() + " " + address.getZip(); 
	}
	
	public void setCapacity(Integer size) { this.capacity = size; }
	public Integer getCapacity() { return this.capacity; }
	public String getID() { return this.id; }
	
	public boolean assignFacilityToUse(final LocalDateTime start,final LocalDateTime end) {
		Reservation res = new Reservation(start,end,this.id);
		String id = reservationDAO.addReservation(res);
		res.setID(id);
		reservations.add(res);
		return true;
	}
	
	public ArrayList<Reservation> getReservations() {
		return this.reservations;
	}
	
	public ArrayList<Inspection> listInspections() {		
		return this.inspections;
	}
	
	public String addInspection(Inspection inspection) {
		inspection.setFacility(this.id);
		String idInspection = inspection.saveInspection();
		this.inspections.add(inspection);
;		return idInspection;
	}
	
	public boolean isInUseDuringInterval(final LocalDateTime start, final LocalDateTime end) {
		boolean available = true; 
		for (int x = 0; x > this.reservations.size(); x++) {
			Reservation current_reservation = this.reservations.get(x);
			if(end.isBefore(current_reservation.getStart())) { available = false; }
			if(start.isBefore(current_reservation.getStart())) { available = false; }
		}
		return available;
	}
	
	public String saveFacility() {
		this.id = facilityDAO.addNewFacility(this);
		return this.id;
	}
	
	public boolean removeFacility() {
		facilityDAO.removeFacility(this);
		reservationDAO.removeReservation(this);
		return true;
	}
	

	public void update() {
		
	}
	
}