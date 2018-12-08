import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class TestFile {
    public void writing() {
        try {
        	
        	Random rand = new Random();
        	int n ;
        	SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
        	Date date = new Date(117,0,1);
        	Calendar cal = Calendar.getInstance();
        	cal.setTime(date);
       
        	
            File motionTest = new File("C:\\Users\\thodoris\\Desktop\\thesis\\src\\thesismotionTest.txt");
            if(motionTest.exists()) { 
            	FileOutputStream is = new FileOutputStream(motionTest);
                OutputStreamWriter osw = new OutputStreamWriter(is);    
                Writer w = new BufferedWriter(osw);
                for(int i=1;i<300000;i++){
                	if((cal.get(Calendar.HOUR_OF_DAY)>=0 && cal.get(Calendar.HOUR_OF_DAY)<8)){
                	
                		n = rand.nextInt(20) + 1;
                	}else if((cal.get(Calendar.HOUR_OF_DAY)>=17 && cal.get(Calendar.HOUR_OF_DAY)<22) ){
                		n = rand.nextInt(1) + 1;
                	}else if(cal.get(Calendar.DAY_OF_WEEK)==1){
                		n = rand.nextInt(1) + 1;
                	}else{
                		n = rand.nextInt(3)+1;
                	}
                	cal.add(Calendar.MINUTE, 5);
                	
                	if(n==1){
                		
                		w.write(i+",true,"+  dateFormatter.format(cal.getTime()) +"\n");
                	}else{
                		w.write(i+",false,"+  dateFormatter.format(cal.getTime())+ "\n");
                	}
                }
                w.close();
            }
            
        } catch (IOException e) {
            System.err.println("Problem writing to the file statsTest.txt");
        }
    }

   public static void main(String[] args){
	   TestFile test = new TestFile();
	   test.writing();
   }
}