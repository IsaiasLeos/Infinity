
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
	public static int platformSpeed = 4;
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
		//Checks whether the anything is colliding with the ride side of the platform.
		//Stops movement of platforms if anything is colliding.
		for(int i = 0; i < platforms.size(); i++) {
			if(!player.collidingRight) {
				platforms.get(i).x -= platformSpeed;
				if(debug) {
					rectCoord = platforms.get(i).x;
				}
			}
		}
	}

	public void draw(Graphics2D g) {
		if(debug) {
			isDebugEnabled(g);
		}
		player.draw(g);
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
	 * Checks the player's collisions. TODO: Fix indentation
	 */
	public void checkCollisions(){
		player.collidingTop = false;
		player.collidingBot = false;
		player.collidingLeft = false;
		player.collidingRight = false;
		for(int i = 0; i < platforms.size(); i++){
			Platform platform = platforms.get(i);
			if(platform.getBounds().contains(player.getTopLeft()) || platform.getBounds().contains(player.getTopRight())){
				player.collidingTop = true;
				player.jumping = false;
				player.falling = true;
				player.jumpSpd = player.jumpOriginalVal;
			}
			if(platform.getBounds().contains(player.getBotLeft()) || platform.getBounds().contains(player.getBotRight())){
				player.collidingBot = true;
				player.y = platform.y - player.size;
				player.falling = false;
				player.fallSpd = 0;
			}
			if(platform.getBounds().contains(player.getLeftTop()) || platform.getBounds().contains(player.getLeftBot())){
				player.collidingLeft = true;
			}
			if(platform.getBounds().contains(player.getRightTop()) || platform.getBounds().contains(player.getRightBot())){
				player.collidingRight = true;
			}
		}
		if(!player.collidingBot){
			player.falling = true;
		}
	}
}
