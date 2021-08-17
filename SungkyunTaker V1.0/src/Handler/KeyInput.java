package Handler;

import GameObject.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private MoveHandler moveHandler;
	
	public KeyInput(Handler handler, MoveHandler moveHandler) {
		this.handler = handler;
		this.moveHandler = moveHandler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
			if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
				handler.getPlayer().turnRight();
				GameObject tmp = handler.returnObject(handler.getPlayer().getX() + 100, handler.getPlayer().getY());
				
				if(tmp.getId() == ID.Empty) {
					moveHandler.moveToEmpty(handler.getPlayer(), tmp);
				}
				else if(tmp.getId() == ID.Guard) {
					moveHandler.moveToGuard(handler.getPlayer(), tmp);
				}
				else if(tmp.getId() == ID.Rock) {
					moveHandler.moveToRock(handler.getPlayer(), tmp);
				}
				else if(tmp.getId() == ID.Paddle) {
					moveHandler.moveToGinko(handler.getPlayer(), tmp);
				}
				else if(tmp.getId() == ID.Friend) {
					moveHandler.moveToFriend(handler.getPlayer(), tmp);
				}
				
			}			
			if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
				handler.getPlayer().turnLeft();
				GameObject tmp = handler.returnObject(handler.getPlayer().getX() - 100, handler.getPlayer().getY());
				
				if(tmp.getId() == ID.Empty) {
					moveHandler.moveToEmpty(handler.getPlayer(), tmp);
				}
				else if(tmp.getId() == ID.Guard){
					moveHandler.moveToGuard(handler.getPlayer(), tmp);
				}
				else if(tmp.getId() == ID.Rock) {
					moveHandler.moveToRock(handler.getPlayer(), tmp);
				}
				else if(tmp.getId() == ID.Paddle) {
					moveHandler.moveToGinko(handler.getPlayer(), tmp);
				}
				else if(tmp.getId() == ID.Friend) {
					moveHandler.moveToFriend(handler.getPlayer(), tmp);
				}
			}
				
//				
			if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
				GameObject tmp = handler.returnObject(handler.getPlayer().getX(), handler.getPlayer().getY() - 100);

				if(tmp.getId() == ID.Empty) {
					moveHandler.moveToEmpty(handler.getPlayer(), tmp);
				}
				else if(tmp.getId() == ID.Guard){
					moveHandler.moveToGuard(handler.getPlayer(), tmp);
				}
				else if(tmp.getId() == ID.Rock) {
					moveHandler.moveToRock(handler.getPlayer(), tmp);
				}
				else if(tmp.getId() == ID.Paddle) {
					moveHandler.moveToGinko(handler.getPlayer(), tmp);
				}
				else if(tmp.getId() == ID.Friend) {
					moveHandler.moveToFriend(handler.getPlayer(), tmp);
				}
			}
//				
			if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
				GameObject tmp = handler.returnObject(handler.getPlayer().getX(), handler.getPlayer().getY() + 100);
				
				if(tmp.getId() == ID.Empty) {
					moveHandler.moveToEmpty(handler.getPlayer(), tmp);
				}
				else if(tmp.getId() == ID.Guard){
					moveHandler.moveToGuard(handler.getPlayer(), tmp);
				}
				else if(tmp.getId() == ID.Rock) {
					moveHandler.moveToRock(handler.getPlayer(), tmp);
				}
				else if(tmp.getId() == ID.Paddle) {
					moveHandler.moveToGinko(handler.getPlayer(), tmp);
				}
				else if(tmp.getId() == ID.Friend) {
					moveHandler.moveToFriend(handler.getPlayer(), tmp);
				}
			}
	}

}
