package GameObject;

import Handler.SpriteSheet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Guard extends GameObject{

	private ArrayList<ArrayList<BufferedImage>> guardImages;
	
	private BufferedImage currentImage;
	
	private int cnt = 0;
	
	private int imageIdx = 0;
	
	private int leftDirection;
	
	public Guard(int x, int y, ID id, BufferedImage image) {
		super(x, y, id, image);
		
		leftDirection = 0;
		
		priority = 2;
		
		SpriteSheet ss = new SpriteSheet(image);
		
		guardImages = new ArrayList<ArrayList<BufferedImage>>();
		guardImages.add(new ArrayList<BufferedImage>());
		guardImages.add(new ArrayList<BufferedImage>());
		
		for(int i = 0; i < 6; i++) {
			guardImages.get(0).add(ss.grabImage(i+1, 1, 100, 100));
		}
		for(int i = 0; i < 6; i++) {
			guardImages.get(1).add(ss.grabImage(i+1, 2, 100, 100));
		}
		
		
		currentImage = guardImages.get(0).get(0);
	}

	public void turnRight() {
		leftDirection = 0;
	}
	
	public void turnLeft() {
		leftDirection = 1;
	}
	
	public void tick() {
		
		if(cnt == 5) {
			imageIdx = 0;
			cnt = 0;
		}
		cnt++;
		imageIdx++;
		currentImage = guardImages.get(leftDirection).get(imageIdx);
		
	}
	
	public void render(Graphics g) {
		g.drawImage(currentImage, (int)x, (int)y, null);
	}

}
