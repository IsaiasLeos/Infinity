
import java.util.LinkedList;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class InfinityTest
{

	public LinkedList<Platform> platforms = new LinkedList<>();
	public Player player = new Player(50, 50, 50, 50);

	public void onCreate()
	{
		for(int i = 0; i < 6; i++)
		{
			platforms.add(new Platform(i * 250, 400, 150, 100));
		}
	}

	public void update()
	{
		checkCollitions();
		player.update();
		for(int i = 0; i < platforms.size(); i++)
		{
			platforms.get(i).x -= 2;
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
		if(!player.jumping && !player.falling)
		{
		player.jumping = true;
		}
	}

	public void checkCollitions()
	{	
		player.collidingTop = false;
		player.collidingBot = false;
		player.collidingRight = false;
		player.collidingLeft = false;
		for(int i = 0; i < platforms.size(); i++)
		{
			int platformX = platforms.get(i).x;
			int platformY = platforms.get(i).y;
			int platformW = platforms.get(i).width;
			int platformH = platforms.get(i).height;
			if(player.y <= platformY + platformH && 
				player.y + player.size >= platformY && 
				player.x <= platformX + platformW && 
				player.x + player.size >= platformX)
			{
				if(player.x <= platformX + platformW - 2 &&
					player.x + player.size - 2 >= platformX &&
					player.y > platformY)
				{
					player.collidingTop = false;
					player.jumping = false;
					player.falling = true;
					player.jumpSpd = player.jumpOriginalVal;
				}
				if(player.x <= platformX + platformW - 2 &&
					player.x + player.size - 2 >= platformX &&
					player.y + player.size < platformY + platformH)
				{
					player.y = platformY - player.size;
					player.collidingBot = true;
					player.falling = false;
					player.fallSpd = 0;
				}
				if(player.x + player.size == platformX)
				{
					player.collidingRight = true;
				}
				if(player.x == platformX + platformW)
				{
					player.collidingLeft = true;
				}
			}
			if(!player.collidingBot)
			{
				player.falling = true;
			}
		}
	}
}
