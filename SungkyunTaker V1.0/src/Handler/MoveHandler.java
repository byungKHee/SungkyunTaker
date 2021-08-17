package Handler;

import GameObject.*;

public class MoveHandler {
	
	private Handler handler;
	private MoveCount moveCount;
	private BGMplay bgm;
	public MoveHandler(Handler handler, MoveCount moveCount) {
		this.handler = handler;
		this.moveCount = moveCount;
		bgm = new BGMplay();
	}
	
	public void moveToEmpty(Player player, GameObject object) {
		bgm.playTemp("audio/move.wav");
		moveCount.countDown();
		int dx = object.getX() - player.getX();
		int dy = object.getY() - player.getY();
		handler.player.setX(handler.player.getX() + dx);
		handler.player.setY(handler.player.getY() + dy);
	}
	
	public void moveToGuard(Player player, GameObject object) {
		bgm.playTemp("audio/kickGuard.wav");

		moveCount.countDown();
		
		int dx = object.getX() - player.getX();
		int dy = object.getY() - player.getY();
		
		if(dx > 0) {
			((Guard) object).turnRight();
		}
		if(dx < 0) {
			((Guard) object).turnLeft();
		}
		
		GameObject tmp = handler.returnObject(object.getX() + dx, object.getY() + dy);
		
		if(tmp.getId() == ID.Wall) {
			handler.removeObject(object);
			bgm.playTemp("audio/die.wav");

		}
		else if(tmp.getId() == ID.Empty){
			object.setX(object.getX() + dx);
			object.setY(object.getY() + dy);
		}
		else if(tmp.getId() == ID.Guard) {
			handler.removeObject(object);
			bgm.playTemp("audio/die.wav");

		}
		else if(tmp.getId() == ID.Paddle) {
			object.setX(object.getX() + dx);
			object.setY(object.getY() + dy);
		}
		else if(tmp.getId() == ID.Rock) {
			handler.removeObject(object);
			bgm.playTemp("audio/die.wav");

		}
		else if(tmp.getId() == ID.Friend) {
			handler.removeObject(object);
			bgm.playTemp("audio/die.wav");

		}
		
	}
	
	public void moveToRock(Player player, GameObject object) {
		bgm.playTemp("audio/kickStone.wav");
		
		moveCount.countDown();

		int dx = object.getX() - player.getX();
		int dy = object.getY() - player.getY();
		
		GameObject tmp = handler.returnObject(object.getX() + dx, object.getY() + dy);
		
		if(tmp.getId() == ID.Wall) {
			return;
		}
		else if(tmp.getId() == ID.Empty){
			object.setX(object.getX() + dx);
			object.setY(object.getY() + dy);
		}
		else if(tmp.getId() == ID.Guard) {
			return;
		}
		else if(tmp.getId() == ID.Paddle) {
			object.setX(object.getX() + dx);
			object.setY(object.getY() + dy);
		}
		else if(tmp.getId() == ID.Rock) {
			return;
		}
		else if(tmp.getId() == ID.Friend) {
			return;
		}
	}
	
	public void moveToGinko(Player player, GameObject object) {
		bgm.playTemp("audio/diving.wav");

		moveCount.countDown();
		moveCount.countDown();

		int dx = object.getX() - player.getX();
		int dy = object.getY() - player.getY();
		handler.player.setX(handler.player.getX() + dx);
		handler.player.setY(handler.player.getY() + dy);
		
	}
	
	public void moveToFriend(Player player, GameObject object) {
		bgm.playTemp("audio/clear.wav");
		moveCount.countDown();
		int dx = object.getX() - player.getX();
		int dy = object.getY() - player.getY();
		handler.player.setX(handler.player.getX() + dx);
		handler.player.setY(handler.player.getY() + dy);
		handler.player.setClear();
		
	}

	
}
