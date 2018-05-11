
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class InfinityTest
{

	Player person = new Player(50, 50, 50, 50);

	public void onCreate()
	{

	}

	public void update()
	{
		person.update();
	}

	public void draw(Graphics2D g)
	{
		person.draw(g);
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
