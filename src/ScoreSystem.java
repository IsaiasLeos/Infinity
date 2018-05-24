
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class ScoreSystem {

	public int score;
	public int highScore;

	/**
	 * Checks if score file exists, if it doesn't exist create it.
	 */
	public void createFile() {
		try {
			File tmpDir = new File("score.txt");
			boolean exists = tmpDir.exists();
			if(!exists) {
				PrintWriter writer = new PrintWriter("score.txt", "UTF-8");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Reads the file scoreboard and sets the high score.
	 */
	public void readScoreFile() {
		try {
			Scanner scnr = new Scanner(new File("score.txt"));
			if(scnr.hasNextLine()) {
				String lineScanned = scnr.nextLine();
				Scanner lineScnr = new Scanner(lineScanned);
				highScore = lineScnr.nextInt();
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
			highScore = score;
			BufferedWriter br = new BufferedWriter(new FileWriter("score.txt"));
			String scr = Integer.toString(highScore);
			br.write(scr);
			br.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
