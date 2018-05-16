
import java.util.LinkedList;
import java.util.Random;

public class PlatformGenerator {

	public LinkedList<Platform> plat = new LinkedList<>();
	public int x;
	public int y;

	public PlatformGenerator(int x, int y) {
		Random rng = new Random();
		int platformNum = rng.nextInt(5);
		switch(platformNum) {
			case 0:
				Platform p1 = new Platform(x, y, 150, 100);
				plat.add(p1);
				break;
			case 1:
				Platform p2 = new Platform(x, y, 100, 100);
				plat.add(p2);
				break;
			case 2:
				Platform p3 = new Platform(x, y, 300, 100);
				plat.add(p3);
				break;
			case 3:
				Platform p4 = new Platform(x, y, 200, 100);
				plat.add(p4);
				break;
			default:
				Platform p5 = new Platform(x, y, 250, 100);
				plat.add(p5);
				break;
		}
	}
}
