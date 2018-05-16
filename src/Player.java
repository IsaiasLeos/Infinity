
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import javax.imageio.ImageIO;

/**
 *
 * @author Isaias Leos Ayala, Ivan Mota
 */
public class Player {

	public int x;//coordinates of player
	public int y;
	public int jumpSpeed;
	public final int size = 32;//original size
	public int newSize = size;

	public double maxJumpSpd = 0;
	public double jumpOriginalVal = 10;
	public double jumpSpd = jumpOriginalVal;
	public double jumpAcceler = 0.2;
	public double fallSpd = 0;
	public double fallAcceler = 0.15;
	public boolean jumping;
	public boolean falling;

	public boolean collidingTop;
	public boolean collidingBot;
	public boolean collidingLeft;
	public boolean collidingRight;

	public BufferedImage playerImage;

	private Timer animate;
	public BufferedImage imageTest;
	public BufferedImage[] animationTest = new BufferedImage[3];

	/**
	 * Creates the player at the given position.
	 * @param x
	 * @param y
	 * @param jumpSpeed
	 * @param size
	 */
	public Player(int x, int y, int jumpSpeed, int newSize) {
		this.x = x;
		this.y = y;
		this.jumpSpeed = jumpSpeed;
		this.newSize = newSize;
	}

	/**
	 * Draws the player.
	 * @param g2
	 */
	public void draw(Graphics2D g2) {
		try {//sets the player image
			playerImage = ImageIO.read(getClass().getResourceAsStream("Assets/green.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		g2.drawImage(playerImage, x, y, size, size, null); //draw the image
	}

	/**
	 * Sets the players location given the coordinates
	 * @param x
	 * @param y
	 */
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Updates the falling and jumping motions.
	 */
	public void update() {
		if(jumping && !collidingTop) {
			if(jumpSpd > maxJumpSpd) {
				y -= jumpSpd;
				jumpSpd -= jumpAcceler;
			}
			else {
				jumpSpd = jumpOriginalVal;
				jumping = false;
				falling = true;
			}
		}
		if(falling) {
			y += fallSpd;
			fallSpd += fallAcceler;
		}
	}
}
