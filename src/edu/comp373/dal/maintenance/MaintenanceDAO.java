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
	
}
