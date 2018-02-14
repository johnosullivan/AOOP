package edu.comp373.model.facility;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Facility {

	private Location location;
	private Integer capacity;
	private String id;
	
	private ArrayList<Reservation> reservations = new ArrayList<Reservation>();
	
	public Facility() { }
	
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
	
}