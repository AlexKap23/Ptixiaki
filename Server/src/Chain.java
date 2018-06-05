import java.sql.SQLException;

/*This class represents a chain. The chain is going to have #number of nodes 
 * given by the program every time the constructor of the Chain class is called
 * We create a different chain for each sensor and this is going to be determined 
 * by the label. Label is a private value of the chain class */

public class Chain {
	private int numOfNodes;
	String label;
	Node node;
	DataBaseImpl db = new DataBaseImpl();
	
	public Chain(int numOfNodes,String label){
		this.numOfNodes = numOfNodes;
		this.label = label;
		float[] probabilities = new float[numOfNodes];
		if(label.equals("temp")){	
			for(int i = 0;i<numOfNodes;i++){
				node = new Node(probabilities,i+1);
			}
		}else if(label.equals("motion")){
			for(int i=0;i<2;i++){
				node = new Node(probabilities,i+1);
			}
		}
		
	}
	
	public void setNumOfNodes(int numOfNodes){
		this.numOfNodes = numOfNodes;
	}
	public int getNumOfNodes(){
		return numOfNodes;
	}
	
	public void setLabel(String label){
		this.label = label;
	}
	public String getLabel(){
		return label;
	}
	
	public Node getNode(){
		return node;
	}
	
	private float computeProb(String queryForProbability,String queryForAll){
		//TODO
		//computing probabilities for each node
		//fetching data from the db
		int allValues = 1;
		int possibilityValues = 0;
		float probability = 0.0f;
		

		   //Query for counting number of values in the possibility set N(a)
		  //Query to find all the values
		
		try {
			possibilityValues = db.fetchFromBd(queryForProbability).getRow();
			allValues = db.fetchFromBd(queryForAll).getRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL Exception occured. Something went wrong when fetching values from DataBase");
			e.printStackTrace();
			
		}
		probability = possibilityValues/allValues;
		return probability;
	}
	
	protected void populateTransitionTable(String queryForProbability,String queryForAll){
		//TODO
		//adding the probabilities that previously computed into 
		//the table.
		
		for(int i=0;i<numOfNodes;i++){
			node.getProbability()[i] = computeProb(queryForProbability,queryForAll);
		}
		
		
	}
	
	
}
