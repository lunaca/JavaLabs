package program3;


///Provided by Dr. Smith

import java.io.FileInputStream;

import javazoom.jl.player.Player;



import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class MP3Player
{
	protected String fileName;

	

	public MP3Player()
	{
		// TODO Auto-generated constructor stub
	}
	
	public void setFileName(String filename)
	{
		this.fileName = filename;
	}
	
	public String getFileName()
	{
		return fileName;
	}
	
	public void play()
	{
		try
		{
			FileInputStream fis = new FileInputStream(this.fileName);
			Player playMP3 = new Player(fis);
			playMP3.play();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		MP3Player mp3 = new MP3Player();
		mp3.setFileName(args[0]);
		mp3.play();

	}

}