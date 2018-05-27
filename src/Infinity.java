
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class Infinity {

	public LinkedList<Platform> platforms = new LinkedList<Platform>();
	public Player player = new Player(180, 180, 48);//Spawn Location
	public ScoreSystem scoreboard = new ScoreSystem();
//	public Potion potion = new Potion();
	public int keyCode;
	public char keyChar;
	public int mouseCoordX;
	public int mouseCoordY;
	public boolean debug = true;
	public int rectCoord;
	public int[] speedIncrease = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	public int index;
	public final int HEIGHT = 720;
	public final int WIDTH = 1280;
	public static int platformSpeed = 5;
	public int fixedSeparation = platformSpeed * 50;
	public PlatformGenerator pg = new PlatformGenerator();
	public BufferedImage backGround;

	/**
	 * Commands that are to be called before anything is drawn or updated.
	 */
	public void onCreate() {
		//Create an initial Platform to avoid null pointers
		backGround = ImageLoader.loadImage("/background.png");
		platforms.add(pg.generatePlatform(0));
		while(platforms.peekLast().x + platforms.peekLast().width + fixedSeparation < GraphicalEngine.WIDTH) {
			platforms.add(pg.generatePlatform(platforms.peekLast().x + platforms.peekLast().width + fixedSeparation));
		}
		scoreboard.createFile();
		scoreboard.readScoreFile();
	}

	public void playerFallingReset() {
		if(!player.jumping && player.fallAcceler != 0.15) {
			player.fallAcceler = 0.15;
		}
	}
	
	public void speedIncrease()
	{

	}

	public void update() {
		//Update the jumping speed to its original state
		playerFallingReset();
		//If the player is dead stop movement
		if(!player.isPlayerDead()) {
			managePlatforms();
			checkCollisions();
			scoreboard.score++;
			player.update();
		}
		//If the player is dead, update score
		else if(scoreboard.score > scoreboard.highScore) {
			scoreboard.writeHighScore();
		}
		//Checks whether the anything is colliding with the right side of the platform.
		//Stops movement of platforms if anything is colliding.
		if(!player.isPlayerDead()) {
			for(int i = 0; i < platforms.size(); i++) {
				if(!player.collidingRight) {
					platforms.get(i).x -= platformSpeed;
					if(debug) {
						rectCoord = platforms.get(i).x;
					}
				}
			}
		}
	}

	public void draw(Graphics2D g) {
		g.drawImage(backGround, 0, 0, null);
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
		int debugSeperationHeight = 15;
		g.setColor(Color.WHITE);
		g.drawString("highScore: " + Integer.toString(scoreboard.highScore), 1150, debugSeperationHeight * 1);
		g.drawString("currentScore: " + Integer.toString(scoreboard.score), 1150, debugSeperationHeight * 2);
		g.drawString("keyCode: " + Integer.toString(keyCode), 1150, debugSeperationHeight * 3);
		g.drawString("keyChar: " + Character.toString(keyChar), 1150, debugSeperationHeight * 4);
		g.drawString("mouseX: " + Integer.toString(mouseCoordX), 1150, debugSeperationHeight * 5);
		g.drawString("mouseY: " + Integer.toString(mouseCoordY), 1150, debugSeperationHeight * 6);
		g.drawString("playerX: " + Integer.toString(player.x), 1150, debugSeperationHeight * 7);
		g.drawString("playerY: " + Integer.toString(player.y), 1150, debugSeperationHeight * 8);
		g.drawString("isPlayerDead: " + Boolean.toString(player.isPlayerDead()), 1150, debugSeperationHeight * 9);
		g.drawString("rectCoord: " + Integer.toString(rectCoord), 1150, debugSeperationHeight * 10);
		g.drawString("fallAccelr: " + Double.toString(player.fallAcceler), 1150, debugSeperationHeight * 11);
		g.drawString("fallSpd: " + Double.toString(player.fallSpd), 1150, debugSeperationHeight * 12);
		g.drawString("jumpAccelr: " + Double.toString(player.jumpAcceler), 1150, debugSeperationHeight * 13);
		g.drawString("jumpSped: " + Double.toString(player.jumpSpd), 1150, debugSeperationHeight * 14);
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
	 * An event which indicates what key was pressed.
	 * @param event
	 */
	void keyPressed(KeyEvent event) {
		keyCode = event.getKeyCode();//For Debug
		if(event.getKeyCode() == KeyEvent.VK_UP) {
			if(!player.jumping && !player.falling) {
				player.jumping = true;
			}
		}
		if(event.getKeyCode() == KeyEvent.VK_DOWN) {
			if(player.jumping) {
				player.fallAcceler += 1.0;
			}
		}
		if(event.getKeyCode() == KeyEvent.VK_LEFT) {
			if(!(player.x < WIDTH - (WIDTH - 50))) {
				player.x -= 3.0;
			}
		}
		if(event.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(!(player.x > WIDTH - 50)) {
				player.x += 3.0;
			}
		}
	}

	/**
	 * An event which indicates that a key action occurred
	 * @param event
	 */
	public void keyTyped(KeyEvent event) {
		keyChar = event.getKeyChar();//For Debug

		if(('r' == event.getKeyChar() || 'R' == event.getKeyChar()) && debug) {//Reset Spawn Location
			player.fallSpd = 0;
			player.setLocation(180, 180);
		}
		if(KeyEvent.VK_0 == event.getKeyChar()) {//Enable or Disable Debug Info
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
	public void checkCollisions() {
		player.collidingTop = false;
		player.collidingBot = false;
		player.collidingLeft = false;
		player.collidingRight = false;
		for(int i = 0; i < platforms.size(); i++) {
			Platform platform = platforms.get(i);
			if(platform.getBounds().contains(player.getTopLeft()) || platform.getBounds().contains(player.getTopRight())) {
				player.collidingTop = true;
				player.jumping = false;
				player.falling = true;
				player.jumpSpd = player.jumpOriginalVal;
			}
			if(platform.getBounds().contains(player.getBotLeft()) || platform.getBounds().contains(player.getBotRight())) {
				player.collidingBot = true;
				player.y = platform.y - player.size;
				player.falling = false;
				player.fallSpd = 0;
			}
			if(platform.getBounds().contains(player.getLeftTop()) || platform.getBounds().contains(player.getLeftBot())) {
				player.collidingLeft = true;
			}
			if(platform.getBounds().contains(player.getRightTop()) || platform.getBounds().contains(player.getRightBot())) {
				player.collidingRight = true;
			}
		}
		if(!player.collidingBot) {
			player.falling = true;
		}
	}

}
