package GameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Rock extends GameObject{

	private BufferedImage currentImage;
	
	public Rock(int x, int y, ID id, BufferedImage image) {
		super(x, y, id, image);
		currentImage = image;
		priority = 2;
	}

	public void tick() {
		return;
	}

	public void render(Graphics g) {
		g.drawImage(currentImage, (int)x, (int)y, null);
	}

}