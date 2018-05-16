
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class InfinityTest {

	public LinkedList<PlatformGenerator> platforms = new LinkedList<>();
	public Player player = new Player(50, 50, 50, 50);//Spawn Location
	public ScoreSystem scoreboard = new ScoreSystem();
	public char keyCode;

	public void onCreate() {
		scoreboard.fileCreationIfNonExistent();
		scoreboard.readScoreFile();
		for(int i = 0; i < 50; i++) {
			platforms.add(new PlatformGenerator(i * 300, 400));
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

	public void draw(Graphics2D g) {
		player.draw(g);
		do {//visual representation of the score
			g.drawString(Integer.toString(scoreboard.highScore), 1230, 50);
			g.drawString(Integer.toString(scoreboard.score), 1230, 75);
		}
		while(scoreboard.systemScore());

		g.drawString("Key: " + Character.toString(keyCode), 1230, 25);
		for(int i = 0; i < platforms.size(); i++) {
			for(int j = 0; j < platforms.get(i).plat.size(); j++) {
				platforms.get(i).plat.get(j).draw(g);
			}
		}
	}

	public void mousePressed(MouseEvent event) {//Player jumps
		if(!player.jumping && !player.falling) {
			player.jumping = true;
		}
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
		//Displays the about the key being pressed.
//		System.out.println(event);
	}

	/**
	 * Checks the players collisions.
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
