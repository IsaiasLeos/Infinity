
import java.awt.image.BufferedImage;

public class AnimationManager {

    public int speed;
    public int index;
    public BufferedImage[] frames;
    public long lastTime;
    public long timer;

    public AnimationManager(int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    public void update() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (timer > speed) {
            index++;
            timer = 0;
            if (index >= frames.length) {
                index = 0;
            }
        }
    }

    public BufferedImage getFrame() {
        return frames[index];
    }
}
