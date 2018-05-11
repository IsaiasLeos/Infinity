
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Isaias Leos Ayala
 */
public class Player
{

	private int x;
	private int y;
	private int jumpSpeed;
	private int size;

	public double maxJumpSpd = 0;
    public double jumpOriginalVal = 8;
    public double jumpSpd = jumpOriginalVal;
    public double jumpAcceler = 0.2;
    public double fallSpd = 0;
    public double maxFallSpd = 5;
    public double fallAcceler = 0.15;
	public boolean jumping;
	public boolean falling;
	
	public Player(int x, int y, int jumpSpeed, int size)
	{
		this.x = x;
		this.y = y;
		this.jumpSpeed = jumpSpeed;
		this.size = size;
	}

	public void draw(Graphics2D g)
	{
		Graphics2D g2 = g;
		Rectangle2D rect = new Rectangle2D.Double(getX(),getY(),getJumpSpeed(),getSize());
		g2.fill(rect);
	}
	
	public void update()
	{
		  if(jumping){
            if(jumpSpd > maxJumpSpd){
                y -= jumpSpd;
                jumpSpd -= jumpAcceler;
            }
            else{
                jumpSpd = jumpOriginalVal;
                jumping = false;
                falling = true;
            }
        }
        if(falling){
            if(fallSpd < maxFallSpd){
                y += fallSpd;
                fallSpd += fallAcceler;
            }
            y += fallSpd;
        }
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getJumpSpeed()
	{
		return jumpSpeed;
	}

	public void setJumpSpeed(int jumpSpeed)
	{
		this.jumpSpeed = jumpSpeed;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}
}
