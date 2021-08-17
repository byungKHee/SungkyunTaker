package Handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MapLoader {

	private int row;
	private int column;
	private int count;
	private int[][] mapArr;
	
	public int[][] mapLoad(String mapPath){
		try{
	         File file = new File(mapPath);
	            Scanner scan = new Scanner(file);
	            row = scan.nextInt();
	            column = scan.nextInt();
	            count = scan.nextInt();
	            
	            mapArr=new int[row][column];
	            
	            for(int i = 0; i < row; i++) {
	               for(int j = 0; j < column; j++) {
	                  mapArr[i][j]=scan.nextInt();
	               }
	            }
	     	}
		 catch (FileNotFoundException e) {
	        	e.printStackTrace();
	    }
		return mapArr;
	}
	
	public int returnRow() {
		return row;
	}
	public int returnColumn() {
		return column;
	}
	public int returnCount() {
		return count;
	}
	
}
