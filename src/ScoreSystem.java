
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Isaias Leos Ayala
 */
public class ScoreSystem
{

	public int score;
	public int highScore;
	public String gameFolderPath = System.getProperty("user.dir") + "\\src\\Data";
	public String gameFilePath = gameFolderPath + "\\scoreboard.txt";

	/**
	 * Creates the scoreboard file if non-existent.
	 */
	public void fileCreationIfNonExistent()
	{
		File gameFolder = new File(gameFolderPath);
		if(!gameFolder.exists())
		{//Folder doesn't exist. Create it
			gameFolder.mkdir();//Folder created
			File gameFile = new File(gameFilePath);
			if(!gameFile.exists())
			{// File doesn't exists, create it
				try
				{//scoreboard created in data folder
					gameFile.createNewFile();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
			else
			{//File exists
			}
		}
		else
		{//Error
		}
	}

	/**
	 * Updates the score of the game.
	 * @return returns whether there is a new higher score.
	 */
	public boolean systemScore()
	{
		score++;
		if(score > highScore)
		{
			highScore = score;
			writeHighScore();
			return true;
		}
		return false;
	}

	/**
	 * Reads the file scoreboard and sets the high score.
	 */
	public void readScoreFile()
	{
		try(FileOutputStream newFile = new FileOutputStream(new File(gameFilePath), true))
		{
			Scanner scnr = new Scanner(new File(gameFilePath));
			if(scnr.hasNextLine())
			{
				Scanner lineScnr = new Scanner(scnr.nextLine());
				highScore = lineScnr.nextInt();
			}
			scnr.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Writes the current score to the highscore.
	 */
	public void writeHighScore()
	{
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File(gameFilePath))))
		{
			String scr = Integer.toString(highScore);
			writer.write(scr);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
