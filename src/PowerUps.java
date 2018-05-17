
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
	public final int size = 32;//original size

	public double lowerLimit = 50;
	public double upperLimit = 1150;
	public double spawnLocation;

	public BufferedImage playerImage;

	public PowerUps() {
		spawnLocation = (Math.random() * (upperLimit - lowerLimit)) + lowerLimit;
	}

	public void draw(Graphics2D g2) {
		try {
			playerImage = ImageIO.read(getClass().getResourceAsStream("assets/potion.png"));
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

	}
}
