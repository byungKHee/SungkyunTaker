package GameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Wall extends GameObject {
	
	private BufferedImage currentImage;
	
	public Wall(int x, int y, ID id, BufferedImage image) {
		super(x, y, id, image);
		currentImage = image;
	}

	@Override
	public void tick() {
		return;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(currentImage, (int)x, (int)y, null);
	}

}
