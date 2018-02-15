package edu.comp373.dal.facility;

import java.util.ArrayList;
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
	
	//private ArrayList<Facility> allFacility = new ArrayList<Facility>();
	//private Map<String, Facility> keyToFacility = new TreeMap<String, Facility>();
		
	MongoClient mongoClient;
	MongoCollection<BasicDBObject> collection;
	MongoDatabase database;
	
	public FacilityDAO() {  
	}
	
	static public ArrayList<Facility> listFacilities() { 
		ArrayList<Facility> all = new ArrayList<Facility>();
		
		MongoClient mongoClient = new MongoClient();
		MongoDatabase database = mongoClient.getDatabase(Configs.DB_NAME);
		MongoCollection<BasicDBObject> collection = database.getCollection(Configs.Facility_Collection_Name, BasicDBObject.class);
		
		FindIterable<BasicDBObject> all_documents = collection.find();	
		
		Block<BasicDBObject> build_array = new Block<BasicDBObject>() {
		   @Override
		   public void apply(final BasicDBObject document) {		      
		      String id = "" + document.get("_id");
		      all.add(new Facility(id));
		   }
		};
		all_documents.forEach(build_array);
		
		mongoClient.close();
		
		return all; 
	}
	
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

		mongoClient = new MongoClient();
		database = mongoClient.getDatabase(Configs.DB_NAME);
		collection = database.getCollection(Configs.Facility_Collection_Name, BasicDBObject.class);
		
		FindIterable<BasicDBObject> iters = collection.find(eq("_id", new ObjectId(facility.getID())));
		
		BasicDBObject facilityObj = iters.first();
		collection.findOneAndDelete(facilityObj);
		
		mongoClient.close();	
		
		return true;
	}
	
	public void addFacilityDetail() {
		
	}
	
}
