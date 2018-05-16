
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Isaias, Ivan
 */
public class PowerUps {

	public int x;//coordinates of powerup
	public int y;
	public int jumpSpeed;
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

	public double lowerLimit = 50;
	public double upperLimit = 1150;
	public double spawnLocation;

	public BufferedImage playerImage;

	/**
	 * Creates the power up at the given position.
	 */
	public PowerUps() {
		spawnLocation = (Math.random() * (upperLimit - lowerLimit)) + lowerLimit;
		System.out.println(spawnLocation);
	}

	/**
	 * Draws the power up.
	 * @param g2
	 */
	public void draw(Graphics2D g2) {
		try {//sets the player image
			playerImage = ImageIO.read(getClass().getResourceAsStream("Assets/potion.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		g2.drawImage(playerImage, (int) spawnLocation, y, size, size, null);//draw the image
	}

	public void resetLocation() {
		spawnLocation = (Math.random() * (upperLimit - lowerLimit)) + lowerLimit;
	}

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
