
/*We created this generic class in order to represent our sensor data as objects of a class. Doing it this way
 * we can have both sensor value and the time stamp that we received this specific value 
 */
public class SensorData <T,U>{
	public final T t;
	public final U u;
	
	public SensorData(T t, U u) {
		this.t = t;
		this.u = u;
	}
	
	public T getT(){
		return t;
	}
	
	public U getU(){
		return u;
	}
		
}
