
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Isaias, Ivan
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

	/**
	 * Creates the player at the given position.
	 * @param x
	 * @param y
	 * @param jumpSpeed
	 * @param newSize
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
//		try {//sets the player image
//			playerImage = ImageIO.read(getClass().getResourceAsStream("assets/green.png"));
//		}
//		catch(IOException e) {
//			e.printStackTrace();
//		}
//		g2.drawImage(playerImage, x, y, size, size, null);//draw the image
		Rectangle2D rect = new Rectangle2D.Double(x, y, size, size);
		g2.fill(rect);
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
