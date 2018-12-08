import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;


public class ProbCalculation {
	
	private DataBaseImpl test = new DataBaseImpl();
	
	public ProbCalculation(){
		
	}
	
	public float getProbMotionTime(String fromTime, String toTime , boolean value){	
		String results = test.fetchFromBd("Select count(value) from motion where time > "+ fromTime + " && time < "+ toTime + " && value = '"+ value +"';");
		String results1 = test.fetchFromBd("Select count(value) from motion where time > "+ fromTime + " && time < "+ toTime +";");
		return Float.parseFloat(results)/Float.parseFloat(results1);
	}
	
	public static void main(String[] args){
		
		ProbCalculation test = new ProbCalculation();
		float re = test.getProbMotionTime("'08:00:00'","'10:25:00'" , false);
		System.out.println(re);
	}
	
}
