
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ScoreSystem {

	public int score;
	public int highScore;

	/**
	 * Updates the score of the game.
	 * @return returns whether there is a new higher score.
	 */
	public boolean systemScore() {
		score++;
		if(score > highScore) {
			highScore = score;
			writeHighScore();
			return true;
		}
		return false;
	}

	/**
	 * Reads the file scoreboard and sets the high score.
	 */
	public void readScoreFile() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("scoreboard.txt")));
			String st;
			if((st = br.readLine()) != null) {
				highScore = Integer.parseInt(st);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Writes the current score to the high score.
	 */
	public void writeHighScore() {
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter("scoreboard.txt"));
			String scr = Integer.toString(highScore);
			br.write(scr);
			br.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
