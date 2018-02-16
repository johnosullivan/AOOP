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
	
	/*
	 * (non-Javadoc)  Gets the full address for a facility
	 * @see edu.comp373.model.facility.AddressInterface#getFullAddress()
	 */
	public String getFullAddress() { return this.address + " " + this.city + " " + this.state + " " + this.zipcode; }
	/*
	 * (non-Javadoc) Gets the address
	 * @see edu.comp373.model.facility.AddressInterface#getAddress()
	 */
	public String getAddress() { return this.address; }
	/*
	 * (non-Javadoc) Sets the address for the current facility 
	 * @see edu.comp373.model.facility.AddressInterface#setAddress(java.lang.String)
	 */
	public void setAddress(final String address) { this.address = address; }
	/*
	 * (non-Javadoc) Gets the state of facility
	 * @see edu.comp373.model.facility.AddressInterface#getState()
	 */
	public String getState() { return this.state; }
	/*
	 * (non-Javadoc) Sets the state for the facility
	 * @see edu.comp373.model.facility.AddressInterface#setState(java.lang.String)
	 */
	public void setState(final String state) { this.state = state; }
	/*
	 * (non-Javadoc) Gets the Zip of the facility
	 * @see edu.comp373.model.facility.AddressInterface#getZip()
	 */
	public String getZip() { return this.zipcode; }
	/*
	 * (non-Javadoc) Sets the zip of the facility
	 * @see edu.comp373.model.facility.AddressInterface#setZip(java.lang.String)
	 */
	public void setZip(final String zip) { this.zipcode = zip; }
	/*
	 * (non-Javadoc) Gets the city the facility is located in 
	 * @see edu.comp373.model.facility.AddressInterface#getCity()
	 */
	public String getCity() { return this.city; }
	/*
	 * (non-Javadoc) Sets the city the facility located in
	 * @see edu.comp373.model.facility.AddressInterface#setCity(java.lang.String)
	 */
	public void setCity(final String city) { this.city = city; }
}
