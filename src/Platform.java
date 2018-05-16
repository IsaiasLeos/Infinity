
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Platform {

	public int x;
	public int y;
	public int width;
	public int height;

	public Platform(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void draw(Graphics2D g) {
		Rectangle2D box = new Rectangle2D.Double(x, y, width, height);
		g.fill(box);
	}
}
