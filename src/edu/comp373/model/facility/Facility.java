package edu.comp373.model.facility;

import edu.comp373.dal.facility.*;
import edu.comp373.dal.inspections.InspectionDAO;
import edu.comp373.dal.reservations.ReservationDAO;
import edu.comp373.model.inspections.Inspection;
import edu.comp373.model.reservations.Reservation;
import edu.comp373.model.users.FacilityUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import com.mongodb.BasicDBObject;

public class Facility implements FacilityInterface {

	private Location location;
	private Integer capacity;
	private String id;
	private LocalDateTime created;
	
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
	
	/*
	 * Basic constructor
	 */
	public Facility() { }
	/*
	 * Constructor to build object from a MongoDB ID
	 */
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
		this.created = LocalDateTime.parse((CharSequence) facilityDAO.get("created"));
	}
	/*
	 * Constructor to build facility before saving
	 */
	public Facility(final Location location, final Integer capacity, final LocalDateTime created) {
		this.location = location;
		this.capacity = capacity;
		this.id = UUID.randomUUID().toString();
		this.created = created;
	}
	/*
	 * (non-Javadoc) Saves details of the object basic on the enum type
	 * @see edu.comp373.model.facility.FacilityInterface#addDetail(edu.comp373.model.facility.Facility.DetailType, java.lang.Object)
	 */
	public void addDetail(DetailType type,Object obj) {
		switch (type) {
        		case CAPACITY: 
        			this.setCapacity((Integer)obj);
        			break;
        		default:
        			break;
		}
	}
	/*
	 * (non-Javadoc) Adds the feature the facility has
	 * @see edu.comp373.model.facility.FacilityInterface#addFeatures(edu.comp373.model.facility.Facility.FeatureType)
	 */
	public void addFeatures(FeatureType feature) {
		features.add(feature);
	}
	/*
	 * (non-Javadoc) Gets the location of the facility
	 * @see edu.comp373.model.facility.FacilityInterface#getLocation()
	 */
	public Location getLocation() { return this.location; }
	/*
	 * (non-Javadoc) Sets the location of the facility
	 * @see edu.comp373.model.facility.FacilityInterface#setLocation(edu.comp373.model.facility.Location)
	 */
	public void setLocation(Location location) { this.location = location; }
	/*
	 * (non-Javadoc) Gets all the information of the facility
	 * @see edu.comp373.model.facility.FacilityInterface#getFacilityInformation()
	 */
	public String getFacilityInformation() { 
		Address address = this.getLocation().getAddress();
		return "Facility: " + location.getBuildingName() + " - " + location.getRoom() + " Capacity: " + this.capacity + "\nAddress: " + address.getAddress() + " " + address.getCity() + ", " + address.getState() + " " + address.getZip(); 
	}
	/*
	 * (non-Javadoc) Sets the total capacity of the facility
	 * @see edu.comp373.model.facility.FacilityInterface#setCapacity(java.lang.Integer)
	 */
	public void setCapacity(Integer size) { this.capacity = size; }
	/*
	 * (non-Javadoc) Gets the facility's max capacity 
	 * @see edu.comp373.model.facility.FacilityInterface#getCapacity()
	 */
	public Integer getCapacity() { return this.capacity; }
	/*
	 * (non-Javadoc) Gets the ID of the facility - MongoDB _id
	 * @see edu.comp373.model.facility.FacilityInterface#getID()
	 */
	public String getID() { return this.id; }
	/*
	 * (non-Javadoc) Assigns the facility to use during a start and end datetime
	 * @see edu.comp373.model.facility.FacilityInterface#assignFacilityToUse(java.time.LocalDateTime, java.time.LocalDateTime)
	 */
	public boolean assignFacilityToUse(final FacilityUser facilityUser, final LocalDateTime start,final LocalDateTime end) {
		Reservation res = new Reservation(facilityUser,start,end,this.id);
		System.out.println("getFacilityUser: " + res.getFacilityUser().getID());
		String id = reservationDAO.addReservation(res);
		res.setID(id);
		reservations.add(res);
		return true;
	}
	/*
	 * (non-Javadoc) Gets all the reservation for the facility
	 * @see edu.comp373.model.facility.FacilityInterface#getReservations()
	 */
	public ArrayList<Reservation> getReservations() {
		return this.reservations;
	}
	/*
	 * (non-Javadoc) Gets all the inspections of the facility
	 * @see edu.comp373.model.facility.FacilityInterface#listInspections()
	 */
	public ArrayList<Inspection> listInspections() {		
		return this.inspections;
	}
	/*
	 * (non-Javadoc) Add a inspection to the facility
	 * @see edu.comp373.model.facility.FacilityInterface#addInspection(edu.comp373.model.inspections.Inspection)
	 */
	public String addInspection(Inspection inspection) {
		inspection.setFacility(this.id);
		String idInspection = inspection.saveInspection();
		this.inspections.add(inspection);
;		return idInspection;
	}
	/*
	 * (non-Javadoc) Logic to check if the facility is use during a time range 
	 * @see edu.comp373.model.facility.FacilityInterface#isInUseDuringInterval(java.time.LocalDateTime, java.time.LocalDateTime)
	 */
	public boolean isInUseDuringInterval(final LocalDateTime start, final LocalDateTime end) {
		boolean available = true; 
		for (int x = 0; x > this.reservations.size(); x++) {
			Reservation current_reservation = this.reservations.get(x);
			if(end.isBefore(current_reservation.getStart())) { available = false; }
			if(start.isBefore(current_reservation.getStart())) { available = false; }
		}
		return available;
	}
	/*
	 * (non-Javadoc) Saves the facility to MongoDB via DAO
	 * @see edu.comp373.model.facility.FacilityInterface#save()
	 */
	public String save() {
		this.id = facilityDAO.addNewFacility(this);
		return this.id;
	}
	/*
	 * (non-Javadoc) Deletes the facility from MongoDb via DAO
	 * @see edu.comp373.model.facility.FacilityInterface#remove()
	 */
	public boolean remove() {
		facilityDAO.removeFacility(this);
		reservationDAO.removeReservation(this);
		return true;
	}
	/*
	 * (non-Javadoc) Sets the facility ID from MongoDB
	 * @see edu.comp373.model.facility.FacilityInterface#setID(java.lang.String)
	 */
	public void setID(final String id) {
		this.id = id;
	}
	/*
	 * (non-Javadoc) Updates the facility information
	 * @see edu.comp373.model.facility.FacilityInterface#update()
	 */
	public void update() {
		
	}
	
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	
	public LocalDateTime getCreated() {
		return this.created;
	}
	
	public ArrayList<Reservation> getAllReservation() {
		return this.reservations;
	}
	
}