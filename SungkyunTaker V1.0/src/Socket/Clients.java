package Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


class Test extends Thread{
	private long record;
	private String str;
	
	public Test(long record) {
		this.record = record;
		str = new String();
		
	}
	
	public String returnRecord() {
		return this.str;
	}
	
   public void run() {
      try {
    	  Socket soc = new Socket("localhost", 5000);
    	  DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
    	  dos.writeLong(record);
    	  
    	  DataInputStream dis = new DataInputStream(soc.getInputStream());
    	  this.str = dis.readUTF();
//    	  System.out.println("readUTF : " + str);
    	  

    	  dos.close();
    	  dis.close();
    	  soc.close();

         
      } catch (IOException e) {
         // TODO: handle exception
         e.printStackTrace();
      }
   }
}



public class Clients {
	
	private long record;
	private String str;
	
	public Clients(long record) {
		this.record = record;
		str = new String();
	}
	
	public String returnRecord() {
		return this.str;
	}

   public void start() {
	   Test test = new Test(record);
	   test.start();
	   
	   while(true) {
		   if(test.returnRecord().length() != 0) {
			   break;
		   }
		   System.out.print("");
	   }
	   
	   
	   
	   this.str = test.returnRecord();
//	   System.out.println("client : " + str);
	   
   }
}