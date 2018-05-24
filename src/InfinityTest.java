
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class InfinityTest {

	public LinkedList<Platform> platforms = new LinkedList<Platform>();
	public Player player = new Player(50, 50, 50, 50);//Spawn Location
	public ScoreSystem scoreboard = new ScoreSystem();
//	public Potion potion = new Potion();
	public char keyCode;
	public int mouseCoordX;
	public int mouseCoordY;
	public boolean debug;
	public int rectCoord;
	public static int platformSpeed = 2;
	public int fixedSeparation = platformSpeed * 50;
	public PlatformGenerator pg = new PlatformGenerator();

	/**
	 * Commands that are to be called before anything is drawn or updated.
	 */
	public void onCreate() {
		//Create an initial Platform to avoid null pointers
		platforms.add(pg.generatePlatform(0));
		while(platforms.peekLast().x + platforms.peekLast().width + fixedSeparation < GraphicalEngine.WIDTH) {
			platforms.add(pg.generatePlatform(platforms.peekLast().x + platforms.peekLast().width + fixedSeparation));
		}
		scoreboard.createFile();
		scoreboard.readScoreFile();
	}

	public void update() {
		managePlatforms();
		checkCollisions();
		if(!player.isPlayerDead()) {
			scoreboard.score++;
			player.update();
		}
		else if(scoreboard.score > scoreboard.highScore) {
			scoreboard.writeHighScore();
		}

//		checkPotionCollision();
//		potion.update();
		//Checks whether the anything is colliding with the ride side of the platform.
		//Stops movement of platforms if anything is colliding.
		for(int i = 0; i < platforms.size(); i++) {
			if(!player.collidingRight) {
				platforms.get(i).x -= platformSpeed;
//				potion.movement = 2;
				if(debug) {
					rectCoord = platforms.get(i).x;
				}
			}
//			else {
//				potion.movement = 0;
//			}
		}
	}

	public void draw(Graphics2D g) {
		if(debug) {
			isDebugEnabled(g);
		}
		player.draw(g);
//		potion.draw(g);
		for(int i = 0; i < platforms.size(); i++) {
			platforms.get(i).draw(g);
		}
	}

	public void managePlatforms() {
		if(platforms.peekFirst().x + platforms.peekFirst().width < 0) {
			platforms.pollFirst();
		}
		if(platforms.peekLast().x + platforms.peekLast().width + fixedSeparation < GraphicalEngine.WIDTH) {
			platforms.add(pg.generatePlatform(platforms.peekLast().x + platforms.peekLast().width + fixedSeparation));
		}
	}

	/**
	 * Debug information. Press 0 to display in game information
	 * @param g
	 */
	public void isDebugEnabled(Graphics2D g) {
		g.drawString("High Score: " + Integer.toString(scoreboard.highScore), 1150, 25);
		g.drawString("Current Score: " + Integer.toString(scoreboard.score), 1150, 50);
		g.drawString("Key Pressed: " + Character.toString(keyCode), 1150, 75);
		g.drawString("MouseX: " + Integer.toString(mouseCoordX), 1150, 100);
		g.drawString("MouseY: " + Integer.toString(mouseCoordY), 1150, 125);
		g.drawString("X: " + Integer.toString(player.x), 1150, 150);
		g.drawString("Y: " + Integer.toString(player.y), 1150, 175);
		g.drawString("isPlayerDead: " + Boolean.toString(player.isPlayerDead()), 1150, 200);
		g.drawString("rectangle Coord: " + Integer.toString(rectCoord), 1150, 225);
//		g.drawString("PotionX: " + Integer.toString(potion.x), 1150, 250);
//		g.drawString("PotionY: " + Integer.toString(potion.y), 1150, 275);
//		g.drawString("itemsCollided: " + Boolean.toString(potion.itemCollided), 1150, 300);

	}

	/**
	 * An event which indicates that a mouse action occurred.
	 * @param event
	 */
	public void mousePressed(MouseEvent event) {//Player jumps
		if(!player.jumping && !player.falling) {
			player.jumping = true;
		}
		mouseCoordX = event.getX();//For Debug
		mouseCoordY = event.getY();

		//Displays information of where the mouse was clicked.
//		System.out.println(event);
	}

	/**
	 * An event which indicates that a key action occurred
	 * @param event
	 */
	public void keyTyped(KeyEvent event) {
		char letterPressed = event.getKeyChar();

		keyCode = letterPressed;//For Debug

		if('r' == letterPressed || 'R' == letterPressed) {//Reset Spawn Location
			player.fallSpd = 0;
			player.setLocation(640, 180);
		}
		if('0' == letterPressed) {//Enable or Disable Debug Info
			if(debug) {
				debug = false;
			}
			else {
				debug = true;
			}
		}
		//Displays the about the key being pressed.
//		System.out.println(event);
	}

	/**
	 * Checks the items collisions. TODO: Fix indentation
	 */
//	public void checkPotionCollision() {
//		if((player.x - (player.size / 2)//Todo - Improve Logic
//			== potion.x || player.x + (player.size / 2)
//			== potion.x) && player.y == potion.y) {
//			potion.itemCollided = true;
//		}
//		potion.collidingTop = false;
//		potion.collidingBot = false;
//		for(int i = 0; i < platforms.size(); i++) {
//			int platformX = platforms.get(i).x;
//			int platformY = platforms.get(i).y;
//			int platformW = platforms.get(i).width;
//			int platformH = platforms.get(i).height;
//			if(potion.y <= platformY + platformH
//				&& potion.y + potion.size >= platformY
//				&& potion.x <= platformX + platformW
//				&& potion.x + potion.size >= platformX) {
//				if(potion.x <= platformX + platformW - 2
//					&& potion.x + potion.size - 2 >= platformX
//					&& potion.y > platformY) {
//					potion.collidingTop = false;
//					potion.jumping = false;
//					potion.falling = true;
//					potion.jumpSpd = potion.jumpOriginalVal;
//				}
//				if(potion.x <= platformX + platformW - 2
//					&& potion.x + potion.size - 2 >= platformX
//					&& potion.y + potion.size < platformY + platformH) {
//					potion.y = platformY - potion.size;
//					potion.collidingBot = true;
//					potion.falling = false;
//					potion.fallSpd = 0;
//				}
//				if(potion.x + potion.size == platformX && potion.y + potion.size != platformY) {
//					potion.collidingRight = true;
//				}
//				if(potion.x == platformX + platformW) {
//					potion.collidingLeft = true;
//				}
//			}
//			if(!potion.collidingBot) {
//				potion.falling = true;
//			}
//		}
//	}
	/**
	 * Checks the player's collisions. TODO: Fix indentation
	 */
	public void checkCollisions() {
		player.collidingTop = false;
		player.collidingBot = false;
		player.collidingRight = false;
		player.collidingLeft = false;
		for(int i = 0; i < platforms.size(); i++) {
			int platformX = platforms.get(i).x;
			int platformY = platforms.get(i).y;
			int platformW = platforms.get(i).width;
			int platformH = platforms.get(i).height;
			if(player.y <= platformY + platformH
				&& player.y + player.size >= platformY
				&& player.x <= platformX + platformW
				&& player.x + player.size >= platformX) {
				if(player.x <= platformX + platformW - 2
					&& player.x + player.size - 2 >= platformX
					&& player.y > platformY) {
					player.collidingTop = false;
					player.jumping = false;
					player.falling = true;
					player.jumpSpd = player.jumpOriginalVal;
				}
				if(player.x <= platformX + platformW - 2
					&& player.x + player.size - 2 >= platformX
					&& player.y + player.size < platformY + platformH) {
					player.y = platformY - player.size;
					player.collidingBot = true;
					player.falling = false;
					player.fallSpd = 0;
				}
				if(player.x + player.size == platformX && player.y + player.size != platformY) {
					player.collidingRight = true;
				}
				if(player.x == platformX + platformW) {
					player.collidingLeft = true;
				}
			}
			if(!player.collidingBot) {
				player.falling = true;
			}
		}
	}
}
