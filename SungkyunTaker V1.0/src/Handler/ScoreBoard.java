package Handler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class ScoreBoard {
	
	private ArrayList<String> records;
	
	private long currentScore;
	
	public ScoreBoard(String s, long currentScore) {
		records = new ArrayList<String>();
		
		this.currentScore = currentScore;
		
		String[] tmp = s.split(",");
		
		
		if(tmp.length >= 10) {
			for(int i = 0; i < 10; i++) {
				records.add(tmp[i]);
			}
		}
		else {
			for(int i = 0; i < tmp.length; i++) {
				records.add(tmp[i]);
			}
			for(int i = tmp.length; i < 10; i++) {
				records.add("0");
			}
		}
		
	}
	
	
	public void render(Graphics g) {
		for(int i = 0; i < 5; i++) {
			g.setFont(new Font("Ariel", Font.BOLD, 50));
			if(Long.parseLong(records.get(i)) == currentScore) {
				g.setColor(Color.RED);
			}
			else {
				g.setColor(Color.WHITE);
			}
			g.drawString(Integer.toString(i+1) + "  " + records.get(i), 200, i * 100 + 250 );
			}
		for(int i = 5; i < 9; i++) {
			g.setFont(new Font("Ariel", Font.BOLD, 50));
			if(Long.parseLong(records.get(i)) == currentScore) {
				g.setColor(Color.RED);
			}
			else {
				g.setColor(Color.WHITE);
			}
			g.drawString(Integer.toString(i+1) + "  " + records.get(i), 700, (i - 5)* 100 + 250 );
		}
		g.setFont(new Font("Ariel", Font.BOLD, 50));
		if(Long.parseLong(records.get(9)) == currentScore) {
			g.setColor(Color.RED);
		}
		else {
			g.setColor(Color.WHITE);
		}
		g.drawString(Integer.toString(10) + "  " + records.get(9), 670, (4)* 100 + 250 );
		
	}

}
