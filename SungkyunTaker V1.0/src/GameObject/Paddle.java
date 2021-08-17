package GameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Paddle extends GameObject{	
		
	private BufferedImage currentImage;
	
	public Paddle(int x, int y, ID id, BufferedImage image) {
		super(x, y, id, image);
		priority = 1;
		currentImage = image;
		
	}

	public void tick() {
		return;
	}

	public void render(Graphics g) {
		g.drawImage(currentImage, (int)x, (int)y, null);
	}

}
