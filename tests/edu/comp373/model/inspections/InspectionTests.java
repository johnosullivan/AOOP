package edu.comp373.model.inspections;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import edu.comp373.model.inspections.*;

public class InspectionTests {

	@Test
	public void test() {
		
		   Inspection inspection = new Inspection();
	       
	       inspection.setFacility("facility1");
	       inspection.setID("5a8a613343b3c34b027e2dce");
	       inspection.setReport("Someone left the sink on :/");
	       inspection.setInspector(inspection.getInspector());
	        
	       assertEquals("facility1", inspection.getFacility());
	       assertEquals("5a8a613343b3c34b027e2dce", inspection.getID());
	       assertEquals("Someone left the sink on :/", inspection.getReport());
	       assertEquals(null, inspection.getInspector());
	       assertEquals(LocalDateTime.now(), inspection.getDateTime());
	}

}
