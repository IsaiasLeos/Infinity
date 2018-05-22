
import java.util.LinkedList;
import java.util.Random;

public class PlatformGenerator {

	public int x;

	public Platform generatePlatform(int x){
		Random rng = new Random();
		int platformNum = rng.nextInt(5);
		switch(platformNum) {
			case 0:
				return new Platform(x, 400, 300, 400);
			case 1:
				return new Platform(x, 450, 400, 400);
			case 2:
				return new Platform(x, 500, 450, 400);
			case 3:
				return new Platform(x, 400, 500, 400);
			default:
				return new Platform(x, 450, 300, 400);
		}
	}
}
