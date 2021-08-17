package Game;

import GameObject.*;
import Handler.*;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -5584526247608405871L;

	public static final int WIDTH = 1200, HEIGHT = 800;
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	
	private MapLoader mapLoader;
	
	private MoveHandler moveHandler;
	
	private MoveCount moveCount;
	
//	public static BufferedImage backgroundImage;

	public static BufferedImageLoader loader;

	
//	public static BufferedImage spriteSheet;
	
	public Game() {
		
		BGMplay bgm = new BGMplay();
		bgm.playLoop("audio/bgm2.wav");
		
		moveCount = new MoveCount();
		
		handler = new Handler(moveCount);
		
		moveHandler = new MoveHandler(handler, moveCount);
		this.addKeyListener(new KeyInput(handler, moveHandler));
		
		handler.mapSet("maps/intro.txt");

		new Window(WIDTH, HEIGHT, "sungkyunkwan taker", this);
		
		loader = new BufferedImageLoader();
		

		
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 15.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;

		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
//			if(!handler.isRun())
//				break;
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		
//		g.drawImage(backgroundImage, 0, 0, null);
		
		handler.render(g);
		
		g.dispose();
		bs.show();
		
		
	}
	
	public static void main(String args[]) {
		Game game = new Game();
		
	}

}
