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
	
	/*
	 * (non-Javadoc) Gets the buidling name the facility is located in 
	 * @see edu.comp373.model.facility.LocationInterface#getBuildingName()
	 */
	public String getBuildingName() { return this.building_name; }
	/*
	 * (non-Javadoc) Sets the buidling name the facility located in
	 * @see edu.comp373.model.facility.LocationInterface#setBuidlingName(java.lang.String)
	 */
	public void setBuidlingName(final String buildingname) { this.building_name = buildingname; }
	/*
	 * (non-Javadoc) Gets the room location in building
	 * @see edu.comp373.model.facility.LocationInterface#getRoom()
	 */
	public String getRoom() { return this.room_name; }
	/*
	 * (non-Javadoc) Sets the room name for the facility in building 
	 * @see edu.comp373.model.facility.LocationInterface#setRoomName(java.lang.String)
	 */
	public void setRoomName(final String roomname) { this.room_name = roomname; }
	/*
	 * (non-Javadoc) Gets the Address Object
	 * @see edu.comp373.model.facility.LocationInterface#getAddress()
	 */
	public Address getAddress() { return this.address; }
	/*
	 * (non-Javadoc) Sets the Address Object
	 * @see edu.comp373.model.facility.LocationInterface#setAddress(edu.comp373.model.facility.Address)
	 */
	public void setAddress(final Address address) { this.address = address; }
}