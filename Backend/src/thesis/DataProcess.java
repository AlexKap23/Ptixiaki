

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class DataProcess implements Runnable {
	private String topic; //this is used to help us use more threads for more jobs
	private String value;
	private Date date;
	protected static Queue<SensorData> tempData = new LinkedList<SensorData>();
	protected static Queue<SensorData> motionData = new LinkedList<SensorData>();

	
	//formaters for the date object 
	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
	SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
	
	
	public DataProcess() {}
	
	public DataProcess(String topic,String value,Date date) {
		this.topic = topic;
		this.value = value;
		this.date = date;
		//this.tempChain = tempChain;
		//this.motionChain = motionChain;
	}
	
	public DataProcess(String topic) {
		this.topic = topic;
	}
	
	public void setTopic(String topic){
		this.topic = topic;
	}
	
	public String getTopic(){
		return topic;
	}
	
	public void setMessage(String value){
		this.value = value;
	}
	
	public String getMessage(){
		return value;
	}
	
	public void setDate(Date date){
		this.date = date;
	}
	
	public Date getDate(){
		return date;
	}
	
	
	/*updating the queue that holds temperature data. Every time a new temperature item comes
	 the queue has to be updated. We remove the peek element and add the new one at the tail*/
	protected Queue<SensorData> tempUpdate(Queue<SensorData> tempData,String newTempValue){
		//date = new Date();
		double temp = Double.valueOf(newTempValue); //casting string to double
		
		
		SensorData sd = new SensorData(temp,date);
		tempData.add(sd);
		if(tempData.size()>=2400){
			tempData.remove();//removes the head of the queue	
		}
		return tempData;
	}
	
	protected void insert2DataBase(){
		
		String dateAsString = dateFormatter.format(date);
		String timeAsString = timeFormatter.format(date);
		DataBaseImpl databaseUser = new DataBaseImpl();
			
		if(this.topic.equals("temp")){
			databaseUser.insertTemp(Double.parseDouble(value),dateAsString,timeAsString);
		}else if(this.topic.equals("motion")){
			databaseUser.insertMotion(value,dateAsString,timeAsString);
		}
	}
	
	/*Updating the queue that holds motion sensor values. Motion is either yes or no*/
	protected Queue<SensorData> motionUpdate(Queue<SensorData> motionData,String newMotionValue){
		//date = new Date();
		SensorData sd = new SensorData(newMotionValue,date);
		motionData.add(sd);
		if(motionData.size()>=2400){
			motionData.remove();//removes the head of the queue	
		}
		return motionData;
	}
	
	
	public void run() {
		String queryForProbability ="";
		String queryForAll="";
		if(topic.equals("temp")) {
			//call tempUpdate with the right arguments
			//update database
			//compute probabilities
			tempUpdate(tempData,this.value);
			insert2DataBase();
			//tempChain.populateTransitionTable(queryForProbability,queryForAll);  //
			System.out.println(tempData.element().getT()+" Size= "+ tempData.size()); //debug message
		}else if(topic.equals("motion")){
			//call motionUpdate with right arguments
			//update database
			//compute probabilities
			motionUpdate(motionData,this.value);
			insert2DataBase();
			//motionChain.populateTransitionTable(queryForProbability,queryForAll);  //
			System.out.println(motionData.element().getT()+" Size= "+ motionData.size()); //debug message
		}else {
		
			System.out.println("An error occured. Terminating....");
		}
	}
	
}
