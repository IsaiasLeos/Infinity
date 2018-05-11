
import java.util.LinkedList;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class InfinityTest
{

	public LinkedList<Platform> platforms = new LinkedList<>();
	Player player = new Player(50, 50, 50, 50);

	public void onCreate()
	{
		for(int i = 0; i < 6; i++)
		{
			platforms.add(new Platform(i * 220, 400, 150, 100));
		}
	}

	public void update()
	{
		
		player.update();
		for(int i = 0; i < platforms.size(); i++)
		{
			checkCollitions();
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
		System.out.println(event);
		player.jumping = true;
	}

	public void checkCollitions()
	{
		for(int i = 0; i < platforms.size(); i++)
		{
			boolean isFalling = platforms.get(i).getBounds().contains(new Point2D.Double(player.x, player.y + player.size + 1)) || platforms.get(i).getBounds().contains(new Point2D.Double(player.x + player.size, player.y + player.size + 1));
			if(isFalling)
			{
				player.falling = false;
			}
		}
	}
}
