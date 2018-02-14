package edu.comp373.model.facility;

import edu.comp373.dal.facility.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import com.mongodb.BasicDBObject;

public class Facility {

	private Location location;
	private Integer capacity;
	private String id;
	private FacilityDAO facilityDAO = new FacilityDAO();
	
	private ArrayList<Reservation> reservations = new ArrayList<Reservation>();
	
	public Facility() { }
	
	public Facility(String _id) { 
		
		BasicDBObject facilityDAO = this.facilityDAO.getFacilityByID(_id);
		BasicDBObject address = (BasicDBObject)facilityDAO.get("address");
		BasicDBObject location = (BasicDBObject)facilityDAO.get("location");
		
		this.id = "" + facilityDAO.get("_id");
		this.capacity = facilityDAO.getInt("capacity");
		
		Address addressObj = new Address(address.getString("address"),address.getString("city"),address.getString("state"),address.getString("zip"));
		this.location = new Location(location.getString("buildingname"),location.getString("room"),addressObj);
	}
	
	public Facility(final Location location, final Integer capacity) {
		this.location = location;
		this.capacity = capacity;
		this.id = UUID.randomUUID().toString();
	}
	
	public Location getLocation() { return this.location; }
	public void setLocation(Location location) { this.location = location; }
	public String getFacilityInformation() { return ""; }
	public void setCapacity(Integer size) { this.capacity = size; }
	public Integer getCapacity() { return this.capacity; }
	public String getID() { return this.id; }
	
	public boolean assignFacilityToUse(final LocalDateTime start,final LocalDateTime end) {
		reservations.add(new Reservation(start,end,this.id));
		return true;
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
	
	public String save() {
		this.id = facilityDAO.addNewFacility(this);
		return this.id;
	}
	
	public void update() {
		
	}
	
}