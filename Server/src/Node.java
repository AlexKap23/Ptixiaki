/*This class represents a node of the chain we are going to use
 * in order for our system to use probabilities and learn paterns 
 * from users.There are two different constructors each of them for
 * a different sensor, as a different chains are used for a different sensor.*/

public class Node {
	private float [] probability;
	private double start;
	private double end;
	private String motion;
	private int id;
	
	
	public Node(){}
	
	public Node(float[]probability,int id){
		this.probability = probability;
		this.id = id;
	}
	
	public Node(float[] probability,double start,double end,int id){
		this.probability = probability;
		this.start = start;
		this.end = end;
		this.id = id;
	}
	
	public Node(float[] probability,String motion,int id){
		this.probability = probability;
		this.motion = motion;
		this.id = id;
	}
	
	public void setProbability(float[] propability){
		this.probability = probability;
	}
	
	public float[] getProbability(){
		return probability;
	}
	
	public void setStart(double start){
		this.start = start;
	}
	
	public double getStart(){
		return start;
	}
	
	public void setEnd(double end){
		this.end = end;
	}
	
	public double getEnd(){
		return end;
	}
	
	public void setMotion(String motion){
		this.motion = motion;
	}
	
	public String getMotion(){
		return motion;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
}
