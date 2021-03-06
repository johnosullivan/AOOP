package edu.comp373.dal.reservations;

import static com.mongodb.client.model.Filters.eq;

import java.time.LocalDateTime;
import java.util.ArrayList;

//import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import edu.comp373.dal.Configs;
import edu.comp373.model.facility.Facility;
import edu.comp373.model.reservations.Reservation;
import edu.comp373.model.users.FacilityUser;

public class ReservationDAO {

	MongoClient mongoClient;
	MongoCollection<BasicDBObject> collection;
	MongoDatabase database;
	
	public ReservationDAO() {
		
	}
	
	public String addReservation(Reservation reservation) {
		
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase(Configs.DB_NAME);
		collection = database.getCollection(Configs.Reservation_Collection_Name, BasicDBObject.class);
				
		BasicDBObject reservationObj = new BasicDBObject();
		reservationObj.append("facility", reservation.getFacilityID());
		reservationObj.append("start", reservation.getStart().toString());
		reservationObj.append("end", reservation.getEnd().toString());
		reservationObj.append("user", reservation.getFacilityUser().getID());

		collection.insertOne(reservationObj);
		mongoClient.close();	
		
		return "" + reservationObj.get("_id");
	}
	
	public ArrayList<Reservation> getAllReservations(String id) {
		
		ArrayList<Reservation> temp_reservation = new ArrayList<Reservation>();	
		
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase(Configs.DB_NAME);
		collection = database.getCollection(Configs.Reservation_Collection_Name, BasicDBObject.class);
		
		FindIterable<BasicDBObject> iterable = collection.find(eq("facility", id));		
		Block<BasicDBObject> build_array = new Block<BasicDBObject>() {
		   @Override
		   public void apply(final BasicDBObject document) {	
		      temp_reservation.add(new Reservation(
		    		  new FacilityUser("" + document.get("user")),
		    		  LocalDateTime.parse((CharSequence) document.get("start")),
		    		  LocalDateTime.parse((CharSequence) document.get("end")),
		    		  (String)document.get("facility"),
		    		  "" + document.get("_id")
		    	  ));  
		   }
		};
		iterable.forEach(build_array);
		
		mongoClient.close();	

		return temp_reservation;
	}
	
	public boolean removeReservation(Facility facility) {
		
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase(Configs.DB_NAME);
		collection = database.getCollection(Configs.Reservation_Collection_Name, BasicDBObject.class);
		
		FindIterable<BasicDBObject> iterable = collection.find(eq("facility", facility.getID()));		
		
		Block<BasicDBObject> delete_block = new Block<BasicDBObject>() {
		   @Override
		   public void apply(final BasicDBObject document) {		      
			   collection.deleteOne(document);
		   }
		};
		
		iterable.forEach(delete_block);
		
		mongoClient.close();	
		
		return true;
	}
	
}
