
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author Isaias Leos Ayala
 */
public class ScoreSystem
{

	public int score;
	public int highScore;
	public int[] scoreboard = new int[3];

	public ScoreSystem()
	{
		readScoreFile();
		writeHighScore();
	}

	public void readScoreFile()
	{
		try
		{
			FileOutputStream newFile = new FileOutputStream(getClass().getResource("scoreboard.txt"), true);
			Scanner scnr = new Scanner(new File(ScoreSystem.class.getResource("scoreboard.txt")));
			if(scnr.hasNextLine())
			{
				Scanner lineScnr = new Scanner(scnr.nextLine());
				highScore = lineScnr.nextInt();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void writeHighScore()
	{
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(ScoreSystem.class.getResource("scoreboard.txt")));
			String scr = Integer.toString(highScore);
			writer.write(scr);
			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
