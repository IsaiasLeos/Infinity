
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Isaias, Ivan
 */
public class Player {

	public int x;//coordinates of player
	public int y;
	public int size;

	public double maxJumpSpd = 0;
	public double jumpOriginalVal = 10;
	public double jumpSpd = jumpOriginalVal;
	public double jumpAcceler = 0.2;
	public double fallSpd = 0;
	public double fallAcceler = 0.15;
	public boolean jumping;
	public boolean falling;

	public boolean collidingTop;//Collision Checks
	public boolean collidingBot;
	public boolean collidingLeft;
	public boolean collidingRight;

	public BufferedImage playerIddle;
	public BufferedImage playerJump;
	public BufferedImage[] playerRun = new BufferedImage[5];

	private AnimationManager runAnimation;

	/**
	 * Creates the player at the given position.
	 * @param x
	 * @param y
	 * @param size
	 */
	public Player(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
		playerJump = ImageLoader.loadImage("/assets/playerjumping.png");
		playerIddle = ImageLoader.loadImage("/assets/playeriddle.png");
		playerRun[0] = ImageLoader.loadImage("/assets/player1.png");
		playerRun[1] = ImageLoader.loadImage("/assets/player2.png");
		playerRun[2] = ImageLoader.loadImage("/assets/player3.png");
		playerRun[3] = ImageLoader.loadImage("/assets/player4.png");
		playerRun[4] = ImageLoader.loadImage("/assets/player5.png");

		runAnimation = new AnimationManager(100, playerRun);
	}

	/**
	 * Draws the player.
	 * @param g2
	 */
	public void draw(Graphics2D g2) {
		if(jumping || falling){
			g2.drawImage(playerJump, x, y, null);
		}
		else if(collidingRight){
			g2.drawImage(playerIddle, x, y, null);
		}
		else{
			g2.drawImage(runAnimation.getFrame(), x, y, null);
		}
		//Rectangle2D rect = new Rectangle2D.Double(x, y, size, size);
		//g2.fill(rect);
	}

	/**
	 * Sets the players location given the coordinates
	 * @param x
	 * @param y
	 */
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Checks whether the player is "dead" or "alive."
	 * @return 
	 */
	public boolean isPlayerDead() {
		return y > 720;
	}

	/**
	 * Updates the falling and jumping coordinates.
	 */
	public void update() {
		runAnimation.update();
		if(jumping && !collidingTop) {
			if(jumpSpd > maxJumpSpd) {
				y -= jumpSpd;
				jumpSpd -= jumpAcceler;
			}
			else {
				jumpSpd = jumpOriginalVal;
				jumping = false;
				falling = true;
			}
		}
		if(falling) {
			y += fallSpd;
			fallSpd += fallAcceler;
		}
	}

	public Point2D getTopLeft(){
		return new Point2D.Double(x + 3, y - 2);
	}

	public Point2D getTopRight(){
		return new Point2D.Double(x + size - 3, y - 2);
	}

	public Point2D getBotLeft(){
		return new Point2D.Double(x + 3, y + size + 2);
	}

	public Point2D getBotRight(){
		return new Point2D.Double(x + size - 3, y + size + 2);
	}

	public Point2D getLeftTop(){
		return new Point2D.Double(x - 2, y + 3);
	}

	public Point2D getLeftBot(){
		return new Point2D.Double(x - 2, y + size - 3);
	}

	public Point2D getRightTop(){
		return new Point2D.Double(x + size + 2, y + 3);
	}

	public Point2D getRightBot(){
		return new Point2D.Double(x + size + 2, y + size - 3);
	}
}