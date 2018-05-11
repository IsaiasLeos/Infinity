import java.util.LinkedList;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class InfinityTest
{

	public LinkedList<Platform> platforms = new LinkedList<Platform>();
	Player person = new Player(50, 50, 50, 50);

	public void onCreate()
	{
		for(int i = 0; i < 6; i++){
			platforms.add(new Platform(i * 200, 400, 150, 100));
		}
	}

	public void update()
	{
		person.update();
		for(int i = 0; i < platforms.size(); i++){
			platforms.get(i).x -= 1;
		}
	}

	public void draw(Graphics2D g)
	{
		person.draw(g);
		for(int i = 0; i < platforms.size(); i++){
			platforms.get(i).draw(g);
		}
	}

	/**
	 * 
	 * @param event
	 */
	public void mousePressed(MouseEvent event)
	{
		System.out.println("player.Jump()");
		person.jumping = true;
	}

}
