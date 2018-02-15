package edu.comp373.model.facility;

interface LocationInterface {

	public String getBuildingName();
	
	public void setBuidlingName(final String buildingname);
	
	public String getRoom();
	
	public void setRoomName(final String roomname);
	
	public Address getAddress();
	
	public void setAddress(final Address address);
	
}
