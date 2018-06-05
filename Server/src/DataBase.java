import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public interface DataBase {
	
	//creates the data base if it has not been created earlier
	public void createDb(); 
	
	//inserts values that came from the temperature sensor into the table that holds temp values
	abstract void insertTemp(double value,String date,String time); 
	
	//inserts  values that came from the motion sensor into the table that holds motion values
	abstract void insertMotion(String motion,String date,String time);
	
	//method that runs a query into the database
	abstract ResultSet fetchFromBd(String query);
	
}
