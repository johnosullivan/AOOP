package edu.comp373.dal.inspections;

import static com.mongodb.client.model.Filters.eq;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import edu.comp373.dal.Configs;
import edu.comp373.model.inspections.Inspection;
import edu.comp373.model.inspections.Inspector;

public class InspectionDAO {
	
	MongoClient mongoClient;
	MongoCollection<BasicDBObject> collection;
	MongoDatabase database;
	
	public InspectionDAO() {
		
	}
	
	public String addInspection(Inspection inspection) {
		
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase(Configs.DB_NAME);
		collection = database.getCollection(Configs.Inspection_Collection_Name, BasicDBObject.class);

		BasicDBObject inspectionObj = new BasicDBObject();
		inspectionObj.append("report", inspection.getReport());
		inspectionObj.append("facility", inspection.getFacility());
		inspectionObj.append("inspector", inspection.getInspector().getID());
		inspectionObj.append("datetime", inspection.getDateTime().toString());

		collection.insertOne(inspectionObj);
		
		mongoClient.close();	
		
		return "" + inspectionObj.get("_id");
		
	}
	
	public BasicDBObject getInspectionByID(String id) {
		
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase(Configs.DB_NAME);
		collection = database.getCollection(Configs.Inspection_Collection_Name, BasicDBObject.class);
		
		FindIterable<BasicDBObject> iterable = collection.find(eq("_id", new ObjectId(id)));
		
		BasicDBObject inspection = iterable.first();
		
		mongoClient.close();	
		    
		return inspection;
	}
	
	public ArrayList<Inspection> getAllInspections(String id) {
		
		ArrayList<Inspection> temp_inspection = new ArrayList<Inspection>();	
		
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase(Configs.DB_NAME);
		collection = database.getCollection(Configs.Inspection_Collection_Name, BasicDBObject.class);
		
		FindIterable<BasicDBObject> iterable = collection.find(eq("facility", id));		
		Block<BasicDBObject> build_array = new Block<BasicDBObject>() {
		   @Override
		   public void apply(final BasicDBObject document) {		      
			   temp_inspection.add(new Inspection(
		    		  (String)document.get("facility"),
		    		  new Inspector((String)document.get("inspector")),
		    		  (String)document.get("report"),
		    		  LocalDateTime.parse((CharSequence) document.get("datetime")),
		    		  "" + document.get("_id")
		    	  ));  
		   }
		};
		iterable.forEach(build_array);
		
		mongoClient.close();	

		return temp_inspection;
	}
}
