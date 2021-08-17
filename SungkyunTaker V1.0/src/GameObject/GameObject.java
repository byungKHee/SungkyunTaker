package GameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;



public abstract class GameObject {

	private static final long serialVersionUID = -8165663057818847971L;

	protected int x, y;
	
	protected ID id;
	
	protected int priority;
	
	protected BufferedImage image;
	
	
	public GameObject(int x, int y, ID id, BufferedImage image) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.image = image;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setId(ID id) {
		this.id = id;
	}
	
	public ID getId() {
		return id;
	}
	
	public int getPrior() {
		return priority;
	}
	
	
	
}