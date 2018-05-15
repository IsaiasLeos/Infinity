
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
	public int[] scoreboard = new int[3];
	public String gameFolderPath, gameFilePath;

	public ScoreSystem()
	{
		gameFolderPath = System.getProperty("user.dir") + "\\src\\data";
		gameFilePath = gameFolderPath + "\\scoreboard.txt";

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

	public void readScoreFile()
	{
		try
		{
			FileOutputStream newFile = new FileOutputStream(new File(gameFilePath), true);
			Scanner scnr = new Scanner(new File(gameFilePath));
			if(scnr.hasNextLine())
			{
				Scanner lineScnr = new Scanner(scnr.nextLine());
				highScore = lineScnr.nextInt();
			}
			newFile.close();
			scnr.close();
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
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(gameFilePath)));
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
