
import java.util.LinkedList;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class InfinityTest
{

	public LinkedList<Platform> platforms = new LinkedList<>();
	public Player player = new Player(50, 50, 50, 50);

	public boolean topCollition;
	public boolean botCollition;
	public boolean rightCollition;
	public boolean leftCollition;

	public void onCreate()
	{
		for(int i = 0; i < 6; i++)
		{
			platforms.add(new Platform(i * 220, 400, 150, 100));
		}
	}

	public void update()
	{
		checkCollitions();
		player.update();
		for(int i = 0; i < platforms.size(); i++)
		{
			platforms.get(i).x -= 1;
		}
	}

	public void draw(Graphics2D g)
	{
		player.draw(g);
		for(int i = 0; i < platforms.size(); i++)
		{
			platforms.get(i).draw(g);
		}
	}

	public void mousePressed(MouseEvent event)
	{
		//System.out.println(event);
		player.jumping = true;
	}

	public void checkCollitions()
	{
		for(int i = 0; i < platforms.size(); i++)
		{
			if(platforms.get(i).getBounds().intersects(player.getTopBounds()))
			{
				topCollition = true;
			}
			if(platforms.get(i).getBounds().intersects(player.getBotBounds()))
			{
				botCollition = true;
				player.falling = false;
			}
			if(platforms.get(i).getBounds().intersects(player.getLeftBounds()))
			{
				leftCollition = true;
			}
			if(platforms.get(i).getBounds().intersects(player.getRightBounds()))
			{
				rightCollition = true;
			}
		}
	}
}
