
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Platform {

	public int x;
	public int y;
	public int width;
	public int height;
	private BufferedImage image;

	public Platform(int platNumber, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		switch(platNumber) {
			case 1:
				image = ImageLoader.loadImage("/platform1.png");
				break;
			case 2:
				image = ImageLoader.loadImage("/platform2.png");
				break;
			case 3:
				image = ImageLoader.loadImage("/platform3.png");
				break;
			default:
				image = ImageLoader.loadImage("/platform4.png");
				break;
		}
	}

	public void draw(Graphics2D g) {
		//g.fill(new Rectangle2D.Double(x, y, width, height));
		g.drawImage(image, x, y, null);
	}

	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x, y, width, height);
	}
}
