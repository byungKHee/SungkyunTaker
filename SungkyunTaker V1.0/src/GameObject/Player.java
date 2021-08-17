package GameObject;

import Handler.SpriteSheet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Player extends GameObject {
	
	private ArrayList<ArrayList<BufferedImage>> normalImages;
	private BufferedImage currentImage;
	private int cnt = 0;
	private int imageIdx = 0;
	private int leftDirection;
	private boolean clear;
	
	public Player(int x, int y, ID id, BufferedImage image) {
		
		super(x, y, id, image);
		
		leftDirection = 0;
				
		clear = false;
				
		SpriteSheet ss = new SpriteSheet(image);
		
		normalImages = new ArrayList<ArrayList<BufferedImage>>();
		normalImages.add(new ArrayList<BufferedImage>());
		normalImages.add(new ArrayList<BufferedImage>());
		
		for(int i = 0; i < 12; i++) {
			normalImages.get(0).add(ss.grabImage(i+1, 1, 100, 100));
		}
		
		
		for(int i = 0; i < 12; i++) {
			normalImages.get(1).add(ss.grabImage(i+1, 2, 100, 100));
		}
		
		
		currentImage = normalImages.get(0).get(0);
		
	}
	
	public void tick() {
		
		if(cnt == 11) {
			imageIdx = 0;
			cnt = 0;
		}
		cnt++;
		imageIdx++;
		currentImage = normalImages.get(leftDirection).get(imageIdx);
		
	}
	
	public void turnRight() {
		leftDirection = 0;
	}
	
	public void turnLeft() {
		leftDirection= 1;
	}
	
	public void render(Graphics g) {
		g.drawImage(currentImage, (int)x, (int)y, null);
	}
	
	public boolean gameClear() {
		return clear;
					
	}
	
	public void setClear() {
		clear = true;
	}
	
	public void setNotClear() {
		clear = false;
	}
}
