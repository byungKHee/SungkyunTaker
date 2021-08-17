package GameObject;

import Handler.SpriteSheet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Friend extends GameObject{

	private ArrayList<BufferedImage> friendImages;
	private BufferedImage currentImage;
	private int cnt = 0;
	
	public Friend(int x, int y, ID id, BufferedImage image) {
		super(x, y, id, image);
		
		SpriteSheet ss = new SpriteSheet(image);
		
		friendImages = new ArrayList<BufferedImage>();
		
		for(int i = 0; i < 12; i++) {
			friendImages.add(ss.grabImage(i+1, 1, 100, 100));
		}
		
		currentImage = friendImages.get(0);
		
	}
	
	public void tick() {
		
		if(cnt == 11) {
			cnt = 0;
		}
		cnt++;
		currentImage = friendImages.get(cnt);
		
	}
	
	public void render(Graphics g) {
		g.drawImage(currentImage, (int)x, (int)y, null);
	}

}
