package Handler;

import GameObject.*;
import Socket.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Handler {
	
	private BGMplay bgm;
	private MoveCount moveCount;
	private MapLoader mapLoader;
	private BackGround backGround;
	private ArrayList<String> mapPathList;
	private long startTime;
	private long endTime;
	Clients clients;
	private long score;
	private boolean running;
	
	// image sprites
	public static BufferedImage myeongRyunSprite;
	public static BufferedImage yulJeonSprite;
	public static BufferedImage guardSprite;
	public static BufferedImage rockSprite;
	public static BufferedImage ginkoSprite;
	public static BufferedImage wallSprite;
	public static BufferedImage emptySprite;
	public static BufferedImageLoader loader;

	Player player;
	
	private ArrayList<ArrayList<GameObject>> stages;
	private ArrayList<GameObject> currentStage;
	private int level;
	
	private boolean clear;
	
	private String recordString;
	
	public Handler(MoveCount moveCount) {
		score = 0;
		bgm = new BGMplay();
		
		mapPathList = new ArrayList<String>();
		mapPathList.add("maps/intro.txt");
		mapPathList.add("maps/story1.txt");
		mapPathList.add("maps/map1.txt");
		mapPathList.add("maps/story2.txt");
		mapPathList.add("maps/map2.txt");
		mapPathList.add("maps/outro.txt");
		mapPathList.add("maps/score.txt");

		level = 0;
		running = true;
		this.moveCount = moveCount;	
		
		//set currentImage
		backGround = new BackGround();
		backGround.addImage("resources/introbg.png");
		backGround.addImage("resources/story1.png");
		backGround.addImage("resources/background1.png");
		backGround.addImage("resources/story2.png");
		backGround.addImage("resources/background2.png");
		backGround.addImage("resources/ending.png");
		backGround.addImage("resources/record.png");
		
		backGround.setStage(0);
		
		stages = new ArrayList<ArrayList<GameObject>>();
		
		for(int i = 0; i < mapPathList.size(); i++) {
			stages.add(new ArrayList<GameObject>());
		}
		
		currentStage = stages.get(0);
		
		clear = false;
		
		mapLoader = new MapLoader();
		
		loader = new BufferedImageLoader();
		
		myeongRyunSprite = loader.loadImage("resources/myeongryun.png");
		yulJeonSprite = loader.loadImage("resources/yuljeon.png");
		guardSprite = loader.loadImage("resources/guard.png");
		rockSprite = loader.loadImage("resources/Rock.png");
		ginkoSprite = loader.loadImage("resources/ginko.png");
		wallSprite = loader.loadImage("resources/Wall.png");
		emptySprite = loader.loadImage("resources/Empty.png");
	}
	
	public boolean isRun() {
		return running;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
		
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public void tick() {
		
		if(this.player.gameClear()) {
			level++;
			if(level == 1) {
				startTime = System.currentTimeMillis();
			}
			if(level == mapPathList.size() - 1) {
				endTime = System.currentTimeMillis();
				score = (endTime - startTime);
				
				// socket part
				clients = new Clients(score);
				clients.start();
				recordString = clients.returnRecord();
			
			}
			else if(level == mapPathList.size()) {
				level = 0;
			}
			currentStage = stages.get(level);
			this.mapSet(mapPathList.get(level));
			backGround.setStage(level);
			player.setNotClear();
		}
		else {
			if(moveCount.gameOver()) {
				bgm.playTemp("audio/gameover.wav");
				this.mapSet(mapPathList.get(level));

				
			}
			for(int i = 0; i < currentStage.size(); i++) {
				GameObject tmp = currentStage.get(i);
				tmp.tick();
			}
		}
	}
	
	public void render(Graphics g) {
		
		if(level == 0 || level == 1 || level == 3 || level == 5) {
			for(int i = 0; i < currentStage.size(); i++) {
				GameObject tmp = currentStage.get(i);
				tmp.render(g);
			}
			backGround.render(g);

		}
		else if(level == 6) {
			for(int i = 0; i < currentStage.size(); i++) {
				GameObject tmp = currentStage.get(i);
				tmp.render(g);
			}
			backGround.render(g);
			ScoreBoard board = new ScoreBoard(recordString, score);
			
			board.render(g);
			
		}
		else {
		
			backGround.render(g);

			for(int i = 0; i < currentStage.size(); i++) {
				GameObject tmp = currentStage.get(i);
				tmp.render(g);
			}
			moveCount.render(g);
		}
	}
	
	
	public void addObject(GameObject object) {
		this.currentStage.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.currentStage.remove(object);
	}
	
	
	public GameObject returnObject(int x, int y) {
		ArrayList<GameObject> check = new ArrayList<GameObject>();
		
		for(int i = 0; i < currentStage.size(); i++) {
			GameObject tmp = currentStage.get(i);
			if(tmp.getX() == x && tmp.getY() == y) {
				check.add(tmp);
			}
		}
		if(check.size() > 1) {
			GameObject tmp = check.get(0);
			for(int i = 1; i < check.size(); i++) {
				if(tmp.getPrior() < check.get(i).getPrior()) {
					tmp = check.get(i);
				}
			}
			return tmp;
			
		}
		else{
			return check.get(0);
		}

	}
	
	public void mapSet(String mapPath) {
		currentStage.clear();
		int[][] mapArr = mapLoader.mapLoad(mapPath);
		int row = mapLoader.returnRow();
		int column = mapLoader.returnColumn();
		int count = mapLoader.returnCount();
		
		moveCount.setCount(count);
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				if(mapArr[i][j] == 0) {	//empty
					this.addObject(new Empty(j * 100, i * 100, ID.Empty, emptySprite));
				}
				else if(mapArr[i][j] == 1) {	//player
					Player tmp = new Player(j * 100, i * 100, ID.Player, yulJeonSprite);
					this.addObject(tmp);
					this.setPlayer(tmp);
					this.addObject(new Empty(j * 100, i * 100, ID.Empty, emptySprite));
				}
				else if(mapArr[i][j] == 2) {	//guard
					this.addObject(new Guard(j * 100, i * 100, ID.Guard, guardSprite));
					this.addObject(new Empty(j * 100, i * 100, ID.Empty, emptySprite));
				}
				else if(mapArr[i][j] == 3) {	//rock
					this.addObject(new Rock(j * 100, i * 100, ID.Rock, rockSprite));
					this.addObject(new Empty(j * 100, i * 100, ID.Empty, emptySprite));
				}
				else if(mapArr[i][j] == 4) {	//friend
					this.addObject(new Friend(j * 100, i * 100, ID.Friend, myeongRyunSprite));
				}
				else if(mapArr[i][j] == 5) {	//paddle
					this.addObject(new Paddle(j * 100, i * 100, ID.Paddle, ginkoSprite));
					this.addObject(new Empty(j * 100, i * 100, ID.Empty, emptySprite));
				}
				else if(mapArr[i][j] == 6) {	//wall
					this.addObject(new Wall(j * 100, i * 100, ID.Wall, wallSprite));
				}
			}
		}		
	}

}