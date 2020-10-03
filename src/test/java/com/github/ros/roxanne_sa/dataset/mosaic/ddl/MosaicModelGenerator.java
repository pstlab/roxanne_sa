package com.github.ros.roxanne_sa.dataset.mosaic.ddl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class MosaicModelGenerator 
{
	private static final String OUTPUT_FOLDER = "gen/sharework";
	private static int HORIZON = 7000;
	private static int UNCERTAINTY = 10;
	
	private static String[] ROWS = new String[] { 
		"DoRow1", "DoRow2", "DoRow3", "DoRow4", "DoRow5"
	};
	
	private static String[][] CELLS = {
			
		// row 1
		{"DoCellA1", "DoCellB1", "DoCellC1", "DoCellD1", "DoCellE1", "DoCellF1", "DoCellG1", "DoCellH1", "DoCellI1", "DoCellJ1"},
		
		// row 2
		{"DoCellA2", "DoCellB2", "DoCellC2", "DoCellD2", "DoCellE2", "DoCellF2", "DoCellG2", "DoCellH2", "DoCellI2", "DoCellJ2"},
		
		// row 3
		{ "DoCellA3", "DoCellB3", "DoCellC3", "DoCellD3", "DoCellE3", "DoCellF3", "DoCellG3", "DoCellH3", "DoCellI3", "DoCellJ3" },
		
		// row 4
		{ "DoCellA4", "DoCellB4", "DoCellC4", "DoCellD4", "DoCellE4", "DoCellF4", "DoCellG4", "DoCellH4", "DoCellI4", "DoCellJ4" },
		
		// row 5
		{ "DoCellA5", "DoCellB5", "DoCellC5", "DoCellD5", "DoCellE5", "DoCellF5", "DoCellG5", "DoCellH5", "DoCellI5", "DoCellJ5" }
			
	};
	
	
	/**
	 * 
	 * @param args
	 */
	public static final void main(String[] args)
	{
		// connect to the DB
		MongoClient client = new MongoClient();
		// get data-base
		MongoDatabase db = client.getDatabase("roxanne_mosaic"); 
		// select data-set
		MongoCollection<Document> collection = db.getCollection("hrc_tasks_properties");
		
		
		
		// generate DDL 
		String ddl = generateDDL(collection);
		// generat PDL
		String pdl = generatePDL();
		
		try 
		{
			// write DDL 
			File ddlFile = new File(OUTPUT_FOLDER + "/hrc_mosaic_gen.ddl");
			try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ddlFile), "UTF-8"))) {
				// write file content
				writer.write(ddl);
			}
			
			// writ PDL
			File pdlFile = new File(OUTPUT_FOLDER + "/hrc_mosaic_gen.pdl");
			try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pdlFile), "UTF-8"))) {
				// write file content
				writer.write(pdl);
			}
		}
		catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
		finally {
			
			// close db connection
			client.close();
		}
	}
	
	
	/**
	 * 
	 * @param collection
	 * @return
	 */
	private static String generateDDL(MongoCollection<Document> collection) {
		
		// generate DDL file content
		String ddl = "DOMAIN SHAREWORK_HRC_MOSAIC_GEN {\n\n";
		
		// add temporal module declaration
		ddl += "\tTEMPORAL_MODULE temporal_module = [0, " + HORIZON + "], 100;\n\n";
		
		// print process state variable
		ddl += "\tCOMP_TYPE SingletonStateVariable GoalType ( DoMosaic(), Idle() ) { \n\n"
				+ "\t\tVALUE Idle() [1, +INF]\n"
				+ "\t\tMEETS {\n"
				+ "\t\t\tDoMosaic();\n"
				+ "\t\t}\n\n"
				+ ""
				+ "\t\tVALUE DoMosaic() [1, +INF]\n"
				+ "\t\tMEETS {\n"
				+ "\t\t\tIdle();\n"
				+ "\t\t}\n\n";
		// close state variable
		ddl += "\t}\n\n";
		
		// print process row-level description
		ddl += "\tCOMP_TYPE SingletonStateVariable MosaicProcessType (";
		// add row description
		for (String row : ROWS) {
			ddl += " " + row + "(), ";
		}
		ddl += " Idle() ) {\n\n";
		
		// add state transition
		for (String row : ROWS) {
			ddl += "\t\tVALUE " + row + "() [1, +INF]\n"
					+ "\t\tMEETS {\n"
					+ "\t\t\tIdle();\n"
					+ "\t\t}\n\n";
		}
		
		// last transition
		ddl += "\t\tVALUE Idle() [1, +INF]\n"
				+ "\t\tMEETS {\n";
		for (String row : ROWS) {
			ddl += "\t\t\t" + row + "();\n";
		}
		ddl += "\t\t}\n\n";
		
		// close state variable
		ddl += "\t}\n\n";
		
		
		
		// print process cell-level description
		ddl += "\tCOMP_TYPE SingletonStateVariable AssemblyProcessType (";
		// add cell description
		for (int i = 0; i < ROWS.length; i++) {
			// get cells fo the current row
			String[] cells = CELLS[i];
			for (String cell : cells) {
				ddl += " " + cell + "(), ";
			}
		}
		ddl += " Idle() ) {\n\n";
		
		// add state transition
		for (int i = 0; i < ROWS.length; i++) {
			// get cells fo the current row
			String[] cells = CELLS[i];
			for (String cell : cells) {
				ddl += "\t\tVALUE " + cell + "() [1, +INF]\n"
						+ "\t\tMEETS {\n"
						+ "\t\t\tIdle();\n"
						+ "\t\t}\n\n";
			}
		}
		
		// last transition
		ddl += "\t\tVALUE Idle() [1, +INF]\n"
				+ "\t\tMEETS {\n";
		
		for (int i = 0; i < ROWS.length; i++) {
			// get cells fo the current row
			String[] cells = CELLS[i];
			for (String cell : cells) {
				ddl += "\t\t\t" + cell + "();\n";
			}
		}
		ddl += "\t\t}\n\n";
		
		// close state variable
		ddl += "\t}\n\n";
		
		
		
		// print human agent task description
		ddl += "\tCOMP_TYPE SingletonStateVariable HumanAgentType (";
		
		// retrieve all document from the collection
		MongoCursor<Document> cursor = collection.find().iterator();
		while (cursor.hasNext()) {
			// get document
			Document doc = cursor.next();
			// add task description
			ddl += " _" + doc.get("task") + "(), "; 
		}
		
		ddl += " Idle() ) {\n\n";
		// add value transition
		cursor.close();
		
		cursor = collection.find().iterator();
		while (cursor.hasNext()) {
			// get next document
			Document doc = cursor.next();
			// get mean human duration
			int hMean = doc.getInteger("human-duration");
			// compute min duration
			int min = Math.max(1, hMean - UNCERTAINTY);
			// compute max duration
			int max = Math.min(hMean + UNCERTAINTY, HORIZON);
			
			// add add transition
			ddl += "\t\tVALUE _" + doc.get("task") + "() [" + min + ", " + max + "]\n"
					+ "\t\tMEETS {\n"
					+ "\t\t\tIdle();\n\n"
					+ "\t\t}\n\n";
		}
		// close cursor
		cursor.close();
		
		// add last transitions
		ddl += "\t\tVALUE Idle() [1, +INF]\n"
				+ "\t\tMEETS {\n\n";
		
		cursor = collection.find().iterator();
		while (cursor.hasNext()) {
			// get next document
			Document doc = cursor.next();
			
			// add transition
			ddl += "\t\t\t_" + doc.get("task") + "();\n";
		}
		// close cursor
		cursor.close();
		
		// close last transition
		ddl += "\t\t}\n\n";
		
		// close state variable 
		ddl += "\t}\n\n";
		
		
		
		
		// print robot agent task description
		ddl += "\tCOMP_TYPE SingletonStateVariable RobotAgentType (";
		
		// retrieve all document from the collection
		cursor = collection.find().iterator();
		while (cursor.hasNext()) {
			// get document
			Document doc = cursor.next();
			// add task description
			ddl += " _" + doc.get("task") + "(), "; 
		}
		
		ddl += " Idle() ) {\n\n";
		// add value transition
		cursor.close();
		
		cursor = collection.find().iterator();
		while (cursor.hasNext()) {
			// get next document
			Document doc = cursor.next();
			// get mean human duration
			int rMean = doc.getInteger("robot-duration");
			// compute min duration
			int min = Math.max(1, rMean - UNCERTAINTY);
			// compute max duration
			int max = Math.min(rMean + UNCERTAINTY, HORIZON);
			
			// add add transition
			ddl += "\t\tVALUE _" + doc.get("task") + "() [" + min + ", " + max + "]\n"
					+ "\t\tMEETS {\n"
					+ "\t\t\tIdle();\n\n"
					+ "\t\t}\n\n";
		}
		// close cursor
		cursor.close();
		
		// add last transitions
		ddl += "\t\tVALUE Idle() [1, +INF]\n"
				+ "\t\tMEETS {\n\n";
		
		cursor = collection.find().iterator();
		while (cursor.hasNext()) {
			// get next document
			Document doc = cursor.next();
			
			// add transition
			ddl += "\t\t\t_" + doc.get("task") + "();\n";
		}
		
		// close last transition
		ddl += "\t\t}\n\n";
		
		// close state variable 
		ddl += "\t}\n\n";
		
		
		
		
		
		// add component declarations
		ddl += "\tCOMPONENT Goal { FLEXIBLE hrc(functional) } : GoalType;\n";
		ddl += "\tCOMPONENT Mosaic { FLEXIBLE process(functional) } : MosaicProcessType;\n";
		ddl += "\tCOMPONENT Assembly { FLEXIBLE coordination(functional) } : AssemblyProcessType;\n";
		ddl += "\tCOMPONENT Robot { FLEXIBLE task(primitive) } : RobotAgentType;\n";
		ddl += "\tCOMPONENT Human { FLEXIBLE operation(primitive) } : HumanAgentType;\n";
		
		ddl += "\n\n";
		
		// add synchronization for goal decomposition
		ddl += "\tSYNCHRONIZE Goal.hrc {\n\n"
				+ "\t\tVALUE DoMosaic() {\n\n";
		
		int dCounter = 0;
		for (String row : ROWS) {
			
			// add decision
			ddl += "\t\t\tr" + dCounter + " Mosaic.process." + row + "();\n"
					+ "\t\t\tCONTAINS [0, +INF] [0, +INF] r"+ dCounter + ";\n\n";
			// increment counter
			dCounter++;
		}
		
		// close value synch
		ddl += "\t\t}\n";
		// close synch
		ddl += "\t}\n\n";
				
				
		
		// add synchronization for process decomposition
		ddl += "\tSYNCHRONIZE Mosaic.process {\n\n";
				
		for (int i = 0; i < ROWS.length; i++) {
			
			// get current row
			String row = ROWS[i];
			// add synch for row decomposition
			ddl += "\t\tVALUE " + row + "() {\n\n";
			
			// dec counter
			int counter = 0;
			// get cells
			String[] cells = CELLS[i];
			for (String cell : cells) {
				
				// add decision
				ddl += "\t\t\td" + counter + " Assembly.coordination." + cell + "();\n"
						+ "\t\t\tCONTAINS [0, +INF] [0, +INF] d" + counter + ";\n\n";
				// increment counter
				counter++;
				
				
			}
			
			
			// close synch
			ddl += "\t\t}\n\n";
			
		}
		
		// close synch
		ddl += "\t}\n\n";
		
		
		
		
		
		// add synchronization for agent coordination
		ddl += "\tSYNCHRONIZE Assembly.coordination {\n\n";
		for (int i = 0; i < ROWS.length; i++) {
			
			// dec counter
			int counter = 0;
			// get cells
			String[] cells = CELLS[i];
			for (String cell : cells) {
				
				
				// extract cell index
				String cIndex = cell.replace("DoCell", "").trim();
				
				// check cell 
				if (cIndex.equals("A1") || cIndex.equals("B1") || cIndex.equals("C1") || 
						cIndex.equals("A2") || cIndex.equals("A3") || cIndex.equals("B3") || 
						cIndex.equals("C3") || cIndex.equals("C4") || cIndex.equals("A5") || 
						cIndex.equals("B5") || cIndex.equals("C5")) 
				{
					
					// only the robot
					
					// add synch costraint toward the robot
					ddl += "\t\tVALUE " + cell + "() {\n\n";
					// add decision
					ddl += "\t\t\td" + counter + " Robot.task._PickPlace-" + cIndex + "();\n"
							+ "\t\t\tCONTAINS [0, +INF] [0, +INF] d" + counter + ";\n\n";
					
					// close constraints block
					ddl += "\t\t}\n\n";
				}
				else if (cIndex.equals("E1") || cIndex.equals("I1") || cIndex.equals("E2") || 
						cIndex.equals("G2") || cIndex.equals("I2") || cIndex.equals("E3") || 
						cIndex.equals("G3") || cIndex.equals("I3") || cIndex.equals("F4") || 
						cIndex.equals("H4") || cIndex.equals("F5") || cIndex.equals("H5")) 
				{
					// only human
					
					// add synch costraint toward the human
					ddl += "\t\tVALUE " + cell + "() {\n\n";
					// add decision
					ddl += "\t\t\td" + counter + " Human.operation._PickPlace-" + cIndex + "();\n"
							+ "\t\t\tCONTAINS [0, +INF] [0, +INF] d" + counter + ";\n\n";
					
					
					// close constraints block
					ddl += "\t\t}\n\n";
				}
				else 
				{
					// both human and robot 
					
					// add synch costraint toward the human
					ddl += "\t\tVALUE " + cell + "() {\n\n";
					// add decision
					ddl += "\t\t\td" + counter + " Human.operation._PickPlace-" + cIndex + "();\n"
							+ "\t\t\tCONTAINS [0, +INF] [0, +INF] d" + counter + ";\n\n";
					
					
					// close constraints block
					ddl += "\t\t}\n\n";
					
					
					// add synch costraint toward the robot
					ddl += "\t\tVALUE " + cell + "() {\n\n";
					// add decision
					ddl += "\t\t\td" + counter + " Robot.task._PickPlace-" + cIndex + "();\n"
							+ "\t\t\tCONTAINS [0, +INF] [0, +INF] d" + counter + ";\n\n";
					
					// close constraints block
					ddl += "\t\t}\n\n";
					
				}
				
				
				
				// increment counter
				counter++;
				
				
			}
		}
		
		// close synch
		ddl += "\t}\n\n";
		
		
		
		// close DDL  
		ddl += "\n}\n\n";
		// get ddl 
		return ddl;
	}


	/**
	 * 
	 * @return
	 */
	private static String generatePDL() {
		
		// set model
		String pdl = "PROBLEM SHAREWORK_HRC_MOSAIC_PRO (DOMAIN SHAREWORK_HRC_MOSAIC_GEN) {\n\n";
		
		// goal counter
		int gCounter = 0;
		// add a goal 
		pdl += "\tg" + gCounter + " <goal> Goal.hrc.DoMosaic() AT [0, " + HORIZON + "] [0, " + HORIZON + "] [0, " + HORIZON + "];\n";
		
		// close problem description
		pdl += "\n}\n\n";
		
		// get PDL
		return pdl;
		
	}

}
