package com.github.ros.roxanne_sa.dataset.mosaic;

import java.util.Random;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class MosaicTaskDurationDataSetup 
{
	// robot task max duration
	private static int ROBOT_TASK_MEAN_DURATION = 63;
	// human task max duration
	private static int HUMAN_TASK_MEAN_DURATION = 145;
	// static risk max value
	private static int STATIC_RISK_MAX = 100;
	
	// tiles of the mosaic
	private static String[] TILES = new String[] {
		"A1", "A2", "A3", "A4", "A5",
		"B1", "B2", "B3", "B4", "B5",
		"C1", "C2", "C3", "C4", "C5",
		"D1", "D2", "D3", "D4", "D5",
		"E1", "E2", "E3", "E4", "E5",
		"F1", "F2", "F3", "F4", "F5",
		"G1", "G2", "G3", "G4", "G5",
		"H1", "H2", "H3", "H4", "H5",
		"I1", "I2", "I3", "I4", "I5",
		"J1", "J2", "J3", "J4", "J5" 
	};

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		// create a data-set
		MongoClient client = new MongoClient();
		try
		{
			// randomly compute a dynamic risk factor
			Random rand = new Random(System.currentTimeMillis());
					
			// get data-base
			MongoDatabase db = client.getDatabase("roxanne_mosaic"); 
			// select data-set
			MongoCollection<Document> collection = db.getCollection("hrc_tasks_properties");
			// clear collection
			collection.drop();
			
			
			// record id counter
			int recordId = 0;
			// create task property records
			for (String tile : TILES)
			{
				// set task name of the current task
				String taskName = "PickPlace-" + tile;
				
				// create basic object
				Document obj = new Document("_id", recordId);
				// append fields
				obj.append("task", taskName);
				// set static a random value assigned for the static risk of a task
				obj.append("risk-static", rand.nextInt(STATIC_RISK_MAX + 1));
					
				// randomly set both human and robot mean duration
				int rDuration = rand.nextInt(ROBOT_TASK_MEAN_DURATION) + 1;
				int hDuration = rand.nextInt(HUMAN_TASK_MEAN_DURATION) + 1;
				
				obj.append("robot-duration", rDuration);
				obj.append("human-duration", hDuration);
				
				// insert document into the collection
				collection.insertOne(obj);
				// increment record id
				recordId++;
			}
		}
		catch (Exception ex) {
			// error
			System.err.println(ex.getMessage());
		}
		finally {
			
			// close connection
			client.close();
		}
	}
}
