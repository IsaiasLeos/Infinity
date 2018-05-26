
import java.util.Random;

public class PlatformGenerator {

	public int x;

	public Platform generatePlatform(int x) {
		Random rng = new Random();
		int platformNum = rng.nextInt(5);
		switch(platformNum) {
			case 0:
				return new Platform(1, x, 495, 867, 225);
			case 1:
				return new Platform(2, x, 420, 549, 300);
			case 2:
				return new Platform(3, x, 495, 867, 225);
			default:
				return new Platform(4, x, 537, 390, 183);
		}
	}
}
