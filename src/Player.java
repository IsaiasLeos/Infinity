
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;

/**
 *
 * @author Isaias Leos Ayala, Ivan Mota
 */
public class Player
{

	public int x;
	public int y;
	public int jumpSpeed;
	public int size;

	public double maxJumpSpd = 0;
	public double jumpOriginalVal = 8;
	public double jumpSpd = jumpOriginalVal;
	public double jumpAcceler = 0.2;
	public double fallSpd = 0;
	public double maxFallSpd = 5;
	public double fallAcceler = 0.15;
	public boolean jumping;
	public boolean falling;

	/**
	 * Creates the player at the given position.
	 * @param x
	 * @param y
	 * @param jumpSpeed
	 * @param size
	 */
	public Player(int x, int y, int jumpSpeed, int size)
	{
		this.x = x;
		this.y = y;
		this.jumpSpeed = jumpSpeed;
		this.size = size;
	}

	/**
	 * Draws the player.
	 * @param g
	 */
	public void draw(Graphics2D g2)
	{
		Rectangle2D rect = new Rectangle2D.Double(x, y, jumpSpeed, size);
		g2.fill(rect);
	}

	/**
	 * Updates the players motions.
	 */
	public void update()
	{
		if(jumping)
		{
			if(jumpSpd > maxJumpSpd)
			{
				y -= jumpSpd;
				jumpSpd -= jumpAcceler;
			}
			else
			{
				jumpSpd = jumpOriginalVal;
				jumping = false;
				falling = true;
			}
		}
		if(falling)
		{
			if(fallSpd < maxFallSpd)
			{
				y += fallSpd;
				fallSpd += fallAcceler;
			}
			y += fallSpd;
		}
	}

	/**
	 * Returns top collition bounds.
	 */
	public Rectangle2D getTopBounds()
	{
		return new Rectangle2D.Double(x + 2, y - 2, size - size/8, size/2);
	}

	/**
	 * Returns bot collition bounds.
	 */
	public Rectangle2D getBotBounds()
	{
		return new Rectangle2D.Double(x + 2, y + size/2 + 2, size - size/8, size/2);
	}

	/**
	 * Returns left collition bounds.
	 */
	public Rectangle2D getLeftBounds()
	{
		return new Rectangle2D.Double(x - 2, y + 2, size/3, size - 4);
	}

	/**
	 * Returns right collition bounds.
	 */
	public Rectangle2D getRightBounds()
	{
		return new Rectangle2D.Double(x + size - size/3 + 2, y + 2, size/3, size - 4);
	}
}
