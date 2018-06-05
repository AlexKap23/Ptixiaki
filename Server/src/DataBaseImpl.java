/*This is the actual database implementation class. It contains methods to start from scratch the database
 * and insert values into tables. It also has a method for running queries 
 * to the database. This is being used in order for us to
 * calculate probability values and populate those into our transition tables.
 * */

import java.sql.*;
import java.util.ArrayList;
public class DataBaseImpl implements DataBase{

	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/";

	private static final String USER = "root";
	private static final String PASS = "password";

	static Connection conn = null;
	static Statement stmt = null;
	
	public void createDb(){
		try{
			Class.forName("com.mysql.jdbc.Driver");

			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			// Create connection and statement
			String query = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'SENSORS_DATA' AND table_name = 'Temperature'";
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			boolean dbExists = rs.getInt("COUNT(*)") > 0;
			if (!dbExists) {
				System.out.println("Creating database...");

				String cdb = "CREATE DATABASE SENSORS_DATA";
				stmt.execute(cdb);
				System.out.println("Database SENSORS_DATA created successfully...");

				System.out.println("Connecting to SENSORS_DATA database...");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				System.out.println("Connected database successfully...");

				System.out.println("Creating table in SENSORS_DATA database...");
				stmt = conn.createStatement();
				
				// Creating table Temprerature to hold values from temperature sensors
				String tempTable = "CREATE TABLE SENSORS_DATA.Temperature "  
						+"(" + "id INT NOT NULL AUTO_INCREMENT ,"
						+ "sensorValue DOUBLE not NULL, " 
						+ " date DATE, " 
						+ " time TIME, "
						+ " PRIMARY KEY ( id ))";
				System.out.println(tempTable); //just debug message
				stmt.executeUpdate(tempTable);
				System.out.println("Table created");

				// Creating table Motion to hold values from motion sensors
				String motionTable = "CREATE TABLE SESNORS_DATA.Motion " 
						+ "(" + "id INT NOT NULL AUTO_INCREMENT ,"
						+ "value VARCHAR(5) not NULL, " 
						+ " date DATE , " 
						+ " time TIME, "
						+ " PRIMARY KEY ( id ))";
				System.out.println(motionTable); //just debug message
				stmt.executeUpdate(motionTable);
				System.out.println("Table Created");
		}
		
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
	}
	
	public void insertTemp(double value,String date,String time){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			
			String insert = "INSERT INTO SENSORS_DATA.Temperature" + "(value,date,time) " + "VALUES ( " + value + " ," + date+" ," + time+" );";
			stmt.executeQuery(insert);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
	}

	
	public void insertMotion(String motion, String date, String time) {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			
			String insert = "INSERT INTO SESNORS_DATA.Motion" + "(motion,date,time) " + "VALUES ( " + motion + " ," + date+" ," + time+" );";
			stmt.executeQuery(insert);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
	}
	
	
	public ResultSet fetchFromBd(String query){
		
		ResultSet rs = null;
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("An exception happened while running a query");
			e.printStackTrace();
		}
		return rs;
	}
	

	

}
