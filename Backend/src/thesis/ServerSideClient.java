


import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.DataOutputStream;
import java.net.Socket;

//

public class ServerSideClient implements MqttCallback {
/*This Class represents a serverSideClient. We use this class to get the data from our sensors via the broker.
 *  This being done we can now use JavaRMI to get all sensor values to our server.
 * 
 * */
 
    public static void main(String[] args) {
    	new ServerSideClient().sub();
    }
    
    public void sub(){
        String tempTopic = "temp";
        String motionTopic = "motion";
        String broker = "tcp://localhost:1883";
        String tempClientId = "tempUser";
        String motionClientId = "motionUser";
        MemoryPersistence persistence = new MemoryPersistence();
        // TODO reSub if broker disconnect
        try {
            MqttClient subClientTemp = new MqttClient(broker, tempClientId, persistence); 
            MqttClient subClientMotion = new MqttClient(broker, motionClientId, persistence); 
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            subClientTemp.connect(connOpts);
            subClientTemp.setCallback(this);
            subClientTemp.subscribe(tempTopic);
            subClientMotion.connect(connOpts);
            subClientMotion.setCallback(this);
            subClientMotion.subscribe(motionTopic);
            
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }

	@Override
	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/*This method is being used from subscriber to get sensor values from broker. 
	 * In order for the server to get this values we establish a connection using sockets 
	 * and send the values as a string through an output stream.
	 */
	@Override
	public void messageArrived(String arg0, MqttMessage message) throws Exception {
		String sensorValue = arg0 + "/"+ message;
		String serverAddress = "127.0.0.1";
		Socket socket2server = null;
			
		try{			
	        socket2server  = new Socket(serverAddress, 2323);
	        DataOutputStream out =
	                new DataOutputStream(socket2server.getOutputStream());
	        out.writeUTF(sensorValue);
	        out.close();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			socket2server.close();
		}
		System.out.println(sensorValue);   
	}
		
	
}
