package edu.comp373.dal.inspections;

import static com.mongodb.client.model.Filters.eq;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import edu.comp373.dal.Configs;
import edu.comp373.model.inspections.*;

public class InspectorDAO {

	MongoClient mongoClient;
	MongoCollection<BasicDBObject> collection;
	MongoDatabase database;
	
	public InspectorDAO() {
		
	}
	
	public String addInspector(Inspector inspector) {
		
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase(Configs.DB_NAME);
		collection = database.getCollection(Configs.Inspector_Collection_Name, BasicDBObject.class);

		BasicDBObject inspectorObj = new BasicDBObject();
		inspectorObj.append("firstName", inspector.getFirstName());
		inspectorObj.append("middleName", inspector.getMiddleName());
		inspectorObj.append("lastName", inspector.getLastName());
		inspectorObj.append("title", inspector.getTitle());

		collection.insertOne(inspectorObj);
		mongoClient.close();	
		
		return "" + inspectorObj.get("_id");
		
	}
	
	public BasicDBObject getInspectorByID(String id) {
		
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase(Configs.DB_NAME);
		collection = database.getCollection(Configs.Inspector_Collection_Name, BasicDBObject.class);
		
		FindIterable<BasicDBObject> iterable = collection.find(eq("_id", new ObjectId(id)));
		
		BasicDBObject inspector = iterable.first();
		
		mongoClient.close();	
		    
		return inspector;
	}
	
	
	
}
