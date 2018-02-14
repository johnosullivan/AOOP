package edu.comp373.dal.facility;

import java.util.ArrayList;
//import java.util.Map;
//import java.util.TreeMap;

import java.util.Optional;

import org.bson.types.ObjectId;

import edu.comp373.model.facility.Address;
import edu.comp373.model.facility.Facility;
import edu.comp373.model.facility.Location;
import edu.comp373.dal.Configs;

import static com.mongodb.client.model.Filters.*;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class FacilityDAO {
	
	private ArrayList<Facility> allFacility = new ArrayList<Facility>();
	//private Map<String, Facility> keyToFacility = new TreeMap<String, Facility>();
		
	MongoClient mongoClient;
	MongoCollection<BasicDBObject> collection;
	MongoDatabase database;
	
	public FacilityDAO() { 

	}
	
	public ArrayList<Facility> listFacilities() { return this.allFacility; }
	
	public boolean requestAvailableCapacity(Integer size) {
		return true;
	}
	
	public String addNewFacility(Facility facility) {
		
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase(Configs.DB_NAME);
		collection = database.getCollection(Configs.Facility_Collection_Name, BasicDBObject.class);

		Location location = facility.getLocation();
		Address address = facility.getLocation().getAddress();
		
		BasicDBObject addressObj = new BasicDBObject();
		addressObj.append("address", address.getAddress());
		addressObj.append("city", address.getCity());
		addressObj.append("state", address.getState());
		addressObj.append("zip", address.getZip());
		
		BasicDBObject locationObj = new BasicDBObject();
		locationObj.append("buildingname", location.getBuildingName());
		locationObj.append("room", location.getRoom());

		BasicDBObject facilitObj = new BasicDBObject();
		facilitObj.append("capacity", facility.getCapacity());
		facilitObj.append("location", locationObj);
		facilitObj.append("address", addressObj);

		collection.insertOne(facilitObj);
		
		mongoClient.close();	
		
		return "" + facilitObj.get("_id");
	}
	
	public BasicDBObject getFacilityByID(String id) {
		
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase(Configs.DB_NAME);
		collection = database.getCollection(Configs.Facility_Collection_Name, BasicDBObject.class);
		
		FindIterable<BasicDBObject> iterable = collection.find(eq("_id", new ObjectId(id)));
		
		BasicDBObject facility = iterable.first();
		
		mongoClient.close();	
		    
		return facility;
	}
	
	public boolean removeFacility(Facility facility) {
		this.allFacility.remove(facility);
		return true;
	}
	
	public void addFacilityDetail() {
		
	}
	
}
