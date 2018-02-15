package edu.comp373.model.facility;

public class Address implements AddressInterface {

	private String address;
	private String city;
	private String state;
	private String zipcode;
	
	public Address() { }
	
	public Address(final String address,final String city,final String state, final String zip) {
		this.address = address;
		this.state= state;
		this.zipcode = zip;
		this.city = city;
	}
	
	public String getFullAddress() { return this.address + " " + this.city + " " + this.state + " " + this.zipcode; }
	
	public String getAddress() { return this.address; }
	public void setAddress(final String address) { this.address = address; }
	
	public String getState() { return this.state; }
	public void setState(final String state) { this.state = state; }
	
	public String getZip() { return this.zipcode; }
	public void setZip(final String zip) { this.zipcode = zip; }
	
	public String getCity() { return this.city; }
	public void setCity(final String city) { this.city = city; }
}
