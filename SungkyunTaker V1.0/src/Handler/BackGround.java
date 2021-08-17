package Handler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BackGround {
	
	private BufferedImageLoader imageLoader;
	
	private ArrayList<BufferedImage> backgroundImages;
	
	private BufferedImage currentImage;
	
	
	public BackGround() {
		imageLoader = new BufferedImageLoader();
		backgroundImages = new ArrayList<BufferedImage>();
	}
	
	public void addImage(String path) {
		backgroundImages.add(imageLoader.loadImage(path));
	}
	
	public void setStage(int n) {
		currentImage = backgroundImages.get(n);
	}
	
	public void render(Graphics g) {
		g.drawImage(currentImage, 0, 0, null);
	}
}
