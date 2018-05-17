
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class InfinityTest {

	public LinkedList<PlatformGenerator> platforms = new LinkedList<>();
	public Player player = new Player(50, 50, 50, 50);//Spawn Location
	public ScoreSystem scoreboard = new ScoreSystem();
	public char keyCode;
	public int mouseCoordX;
	public int mouseCoordY;
	public boolean debug;

	public void onCreate() {
		scoreboard.createFile();
		scoreboard.readScoreFile();
		for(int i = 0; i < 50; i++) {
			platforms.add(new PlatformGenerator(i * 250, 400));
		}
	}

	public void update() {
		checkCollisions();
		scoreboard.systemScore();
		player.update();
		for(int i = 0; i < platforms.size(); i++) {
			for(int j = 0; j < platforms.get(i).plat.size(); j++) {
				if(!player.collidingRight) {
					platforms.get(i).plat.get(j).x -= 2;
				}
			}
		}
	}

	public void isPlayerDead() {

	}

	public void draw(Graphics2D g) {
		player.draw(g);
		if(debug) {
			isDebugEnabled(g);
		}
		for(int i = 0; i < platforms.size(); i++) {
			for(int j = 0; j < platforms.get(i).plat.size(); j++) {
				platforms.get(i).plat.get(j).draw(g);
			}
		}
	}

	/**
	 * Debug information
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
	}

	public void mousePressed(MouseEvent event) {//Player jumps
		if(!player.jumping && !player.falling) {
			player.jumping = true;
		}
		mouseCoordX = event.getX();
		mouseCoordY = event.getY();
		//Displays information of where the mouse was clicked.
//		System.out.println(event);
	}

	/**
	 * Checks what keys you pressed.
	 * @param event
	 */
	public void keyTyped(KeyEvent event) {
		char letterPressed = event.getKeyChar();
		keyCode = letterPressed;
		if('r' == letterPressed || 'R' == letterPressed) {//Reset Spawn Location
			player.fallSpd = 0;
			player.setLocation(640, 180);
		}
		if('0' == letterPressed) {//Enable Debug or Disable Debug
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
	 * Checks the power up collisions.
	 */
	public void checkCollisions() {
		player.collidingTop = false;
		player.collidingBot = false;
		player.collidingRight = false;
		player.collidingLeft = false;
		for(int i = 0; i < platforms.size(); i++) {
			for(int j = 0; j < platforms.get(i).plat.size(); j++) {
				int platformX = platforms.get(i).plat.get(j).x;
				int platformY = platforms.get(i).plat.get(j).y;
				int platformW = platforms.get(i).plat.get(j).width;
				int platformH = platforms.get(i).plat.get(j).height;
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
					if(player.x + player.size == platformX + 1) {
						player.collidingRight = true;
					}
					if(player.x == platformX + platformW + 1) {
						player.collidingLeft = true;
					}
				}
				if(!player.collidingBot) {
					player.falling = true;
				}
			}
		}
	}
}
