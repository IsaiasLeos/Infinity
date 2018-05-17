
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Isaias, Ivan
 */
public class Potion {

	public int x;//coordinates of powerup
	public int y;
	public int movement;
	public final int size = 32;//original size

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
	public boolean itemCollided;

	public double lowerLimit = 500;
	public double upperLimit = 1030;
	public double itemLocation = (Math.random() * (upperLimit - lowerLimit)) + lowerLimit;
	public BufferedImage itemImage;

	public Potion() {
	}

	/**
	 * Draws the potion item.
	 * @param g2 
	 */
	public void draw(Graphics2D g2) {
		try {
			itemImage = ImageIO.read(getClass().getResourceAsStream("assets/potion.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		if(!itemCollided) {
			g2.drawImage(itemImage, x, y, size, size, null);//draw the image
		}
	}

	public void resetLocation() {
	}

	/**
	 * Updates the coordinates of the potion.
	 */
	public void update() {
		if(!itemCollided) {
			x = (int) itemLocation;
			if(collidingBot) {
				itemLocation -= movement;
			}
			else {
				y += fallSpd;
				fallSpd += fallAcceler;
			}
		}
	}
}
