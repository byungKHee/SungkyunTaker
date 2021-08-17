package Handler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class MoveCount {
	
	BufferedImageLoader loader;
	BufferedImage countImage;
	
	private int count;
	private int cnt;
	
	public static int x;
	public static int y;
	
	
	
	public MoveCount() {
		loader = new BufferedImageLoader();
		
		x = 850;
		y = 250;
		countImage = loader.loadImage("resources/countBoard.png");
		
	}
	
	public void setCount(int n) {
		count = n;
		cnt = count;
	}
	
	public void countReset() {
		cnt = count;
	}
	
	public boolean gameOver() {
		if(cnt <= 0)
			return true;
		return false;
	}
	
//	public void tick() {
//		
//	}
	
	public void countDown() {
		cnt--;
	}
	
	public void render(Graphics g) {
		g.drawImage(countImage, (int)x, (int)y, null);
		g.setFont(new Font("Ariel", Font.BOLD, 100));
		g.setColor(Color.RED);
		g.drawString(Integer.toString(cnt), x+110, y+180);
	}

}