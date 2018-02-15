package edu.comp373.model.facility;

public class Location implements LocationInterface {
	/* Location object extends the facility with the building name/location and room name */
	private String building_name;
	private String room_name;
	private Address address;
	
	/* Basic constructor */
	public Location() { }
	
	/* Custom constructor for passing object values */ 
	public Location(final String buildingname,final String roomname, Address address) {
		this.building_name = buildingname;
		this.room_name = roomname;
		this.address = address;
	}
	
	/* Getter/Setters for the location object */
	public String getBuildingName() { return this.building_name; }
	public void setBuidlingName(final String buildingname) { this.building_name = buildingname; }
	public String getRoom() { return this.room_name; }
	public void setRoomName(final String roomname) { this.room_name = roomname; }
	
	public Address getAddress() { return this.address; }
	public void setAddress(final Address address) { this.address = address; }
}