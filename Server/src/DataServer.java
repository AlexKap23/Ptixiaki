

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A TCP server that runs on port 9090.  When a client connects, it
 * sends the client the current date and time, then closes the
 * connection with that client.  Arguably just about the simplest
 * server you can write.
 */
public class DataServer {

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(2323);
      	String sensorValue;
      	String[] parts ;
      	ExecutorService executor = Executors.newFixedThreadPool(5);
      	
        try {

            while (true) {
                Socket socket = listener.accept();
                try {
                    PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                    DataInputStream inFromClient = new DataInputStream((socket.getInputStream()));

                    try{
                    	sensorValue = inFromClient.readUTF();
                    	parts = splitter(sensorValue);
                    	System.out.println(parts[0]+","+parts[1]);
                    	Chain tempChain = new Chain(7,"temp");
                    	Chain motionChain = new Chain(2,"motion");
                    	executor.execute(new DataProcess(parts[0],parts[1],new Date(),tempChain,motionChain));
                    	
                    }catch(Exception e){
                    	e.printStackTrace();
                    	
                    }
                    out.println("Hello this is a test Server");
                } finally {
                    socket.close();
                }
            }
        }
        finally {
            listener.close();
           // executor.shutdown();
        }
    }
      
    //helping method to form the message as we want
     protected static String[] splitter(String msg) {
    	 String[] parts = msg.split("/");
    	 return parts;
     }
}

