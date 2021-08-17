package Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class Server {
	private static ArrayList<Long> ranking = new ArrayList<Long>();
	
	public static void main(String[] args) {
		ServerSocket ss = null;
		Socket socket = null;
		try {
	         ss = new ServerSocket(5000);
	      } catch (IOException e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      }
	      System.out.println("Record server is ready");
		
		
		while(true) {
			try {
				socket = ss.accept();
				System.out.println("new connection");
				
				// input
		         DataInputStream dis = new DataInputStream(socket.getInputStream());
		         ranking.add(dis.readLong());
		         Collections.sort(ranking);

		         
		         DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		         String str = new String();
		         for(int i=0; i < ranking.size(); i++) {
		        	 str = str.concat(Long.toString(ranking.get(i)));
		        	 str = str.concat(",");
		         }
		         System.out.println(str);
		         dos.writeUTF(str);
		         
		         dis.close();
		         dos.close();
		         socket.close();
				
	         } catch (IOException e) {
	            // TODO: handle exception
	            e.printStackTrace();
	         }
			
		}
		
	}

}