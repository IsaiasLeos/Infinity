
import java.util.LinkedList;
import java.util.Random;

public class PlatformGenerator{
	
	public LinkedList<Platform> platforms = new LinkedList<>();
	public int x;
	public int y;

	public PlatformGenerator(int x, int y){
		Random rng = new Random();
		int platformNum = rng.nextInt(5);
		switch(platformNum){
			case 0:
				Platform p1 = new Platform(x, y, 300, 100);
				platforms.add(p1);
				break;
			case 1:
				Platform p1 = new Platform(x, y, 50, 100);
				platforms.add(p1);
				break;
			case 2:
				Platform p1 = new Platform(x, y, 300, 20);
				platforms.add(p1);
				break;
			case 3:
				Platform p1 = new Platform(x, y, 300, 10);
				platforms.add(p1);
				break;
			default:
				Platform p1 = new Platform(x, y, 10, 10);
				platforms.add(p1);
				break;
		}
	}
}