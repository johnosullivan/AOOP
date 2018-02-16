package edu.comp373.dal.maintenance;

import static com.mongodb.client.model.Filters.eq;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import edu.comp373.dal.Configs;
import edu.comp373.model.facility.Facility;
import edu.comp373.model.maintenance.MaintenanceRequest;
import edu.comp373.model.maintenance.MaintenanceRequest.MaintenanceStatus;

public class MaintenanceDAO {

	MongoClient mongoClient;
	MongoCollection<BasicDBObject> collection;
	MongoDatabase database;
	
	public MaintenanceDAO() {
		
	}
	
	private Integer maintenanceStatusToInt(MaintenanceStatus status) {
		switch (status) {
		case PENDING: return 1;
		case PROCESSED: return 2;
		case INPROGRESS: return 3;
		case COMPLETED: return 4;
		case PROBLEM: return 5;
		default: return 1;
		}
	}
	
	public String addMaintenanceRequest(MaintenanceRequest maintenanceRequest) {
		
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase(Configs.DB_NAME);
		collection = database.getCollection(Configs.MaintenanceRequest_Collection_Name, BasicDBObject.class);

		BasicDBObject maintenancerequestObj = new BasicDBObject();
		maintenancerequestObj.append("facility", maintenanceRequest.getFacility().getID());
		maintenancerequestObj.append("problem", maintenanceRequest.getProblem());
		maintenancerequestObj.append("status", maintenanceRequest.maintenanceStatusToInt());
		maintenancerequestObj.append("datetime", maintenanceRequest.getDateTime().toString());

		collection.insertOne(maintenancerequestObj);
		mongoClient.close();	
		
		return "" + maintenancerequestObj.get("_id");
	}
	
	public ArrayList<MaintenanceRequest> getMaintenanceRequest(MaintenanceStatus status) {
		
		ArrayList<MaintenanceRequest> res = new ArrayList<MaintenanceRequest>();
		
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase(Configs.DB_NAME);
		collection = database.getCollection(Configs.MaintenanceRequest_Collection_Name, BasicDBObject.class);
		
		FindIterable<BasicDBObject> iterable = collection.find(eq("status", maintenanceStatusToInt(status)));		
		
		Block<BasicDBObject> insert_block = new Block<BasicDBObject>() {
		   @Override
		   public void apply(final BasicDBObject document) {		      
			   res.add(new MaintenanceRequest(
					 new Facility((String)document.get("facility")),
					 (String)document.get("problem"),
					 LocalDateTime.parse((CharSequence) document.get("datetime")),
					 "" + document.getInt("_id")
			   ));
		   }
		};
		
		iterable.forEach(insert_block);
		
		mongoClient.close();	
		
		return res;
	}
	
	
}
