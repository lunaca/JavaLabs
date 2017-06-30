package program3;

//Austin Rice
//PROGRAM 3



import java.io.FileInputStream;
import javazoom.jl.player.Player;



public class MP3 extends MP3Player implements Sounds {
	
	
	
		//data fields (describer)
		protected String title;
		
		//required methods from Sound (same ones from mp3player
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
		
		public void print()
		{
			String file = getFileName();
			System.out.println("Title: " + title);
			System.out.println("File Name: " + file);
		
		}
		
		public void setTitle(String title)
		{
			this.title = title;
		}
		
		public String getTitle()
		{
			return title;
		}
		
		//// BUT NOW HERE IS MAIN (WITH GUIDELINES STEP BY STEP)
		
		public static void main (String args []){
		
			
		/// before beginning, will initialize the tools to conquer my task
	
			
		//The following array contains the names for each of the mp3 files to be played by the application:
		String[] mp3_sounds = {"baboon","badger","bear","bees","bison","blackbird","bluejay","bobcat",
				"camel","dog","donkey","eagle","elephant","goat","pig","robin","sheep","bark","drip","glass","hammer","phone","toy"};

		//A parallel array containing a title (i.e., descriptive phrase) for each mp3 file is shown below:
		String[] mp3_titles = {"Baboon Making Noise","Honey Badger","A Black Bear","A Hive of Bees", "Bison Grunting","Blackbirds at night","A Bluejay squaking", "A bobcat", "A camel making noise", "A dog", "A donkey Hee-Haw","An eagle soaring","An elephant doing things", "A goat", "A pig","A singing Robin","A Wooly Sheep", "A dog barking","A dripping faucet","A glass half full","A hammer and nail","A ringing phone","A toy"};

		//An array containing the names for each wav file to be played by the application is:		
		String[] wave_sounds = {"airplane","alarm","applause","baby","bomb","chicken","chimes","cow","coyote","crickets","ding","electric","notify","recycle","ringout","saw","drill" };
			
		//A parallel array containing a title (i.e., descriptive phrase) for each mp3 file is shown below:
		String[] wave_titles = {"A flying airplane", "An alarm ringing","Applause! Applause!", "A baby - A baby","Dropping Bomb", "A chicken crowing","Wind chimes","A hungry cow","A howling coyote","Hear the crickets","Ding Dong","Electrical Current","Notify Me Now","Recycle Now","Phone Ringing","Saw Sounds","An elecric drill"};

		//Declaration of other variables used
				
		String previousword;
		String currentword;
		String nextword;
		String argzzz;
		int count = 0;

		
		//1.	Split command line argument into separate words:
		
		
		//reels in the arguments
		argzzz = args[0];
				
		//splits input into a words array using the String split function
		String[] words = argzzz.split(" ");		
			
//		2.	Allocate a large array of Sounds (say 200).. This is to hold the playlist of Sounds:
		
		Sounds[] genericSounds= new Sounds[200];
		int soundcounter = 0;
		
//		3.	Match the words extracted from the command line with the wav/mp3 filenames.
		
		//have to do this with a three layer for loop. i counter iterates through the argzzz
		//j counter sets it as an mp3
		//k counter sets it as a wav
			
		
		for(int i = 0; i < words.length; i++)
		{
			//FIRST, KEEP TRACK OF WORDS TO PRINT OUT CONTEXT (for printing context)
			
			
			if(i == 0)
			{	
				previousword = words[i];
			}
			else
			{
				previousword = words[i-1];	
			}
			
			
			currentword = words[i];
			
			
			
			if( i == words.length-1)
			{
				nextword = words[i];
			}
			
			else
			{
				nextword = words[i+1];
			}
			
			
			
			// J Checks to see if there is a match with the current word
			for(int j = 0; j < mp3_sounds.length; j++ )
			{  
				if( words[i].contains(mp3_sounds[j]))
				{
					
			//	4.	If a match is found, allocate the appropriate MP3 object or Wave object .
					MP3Player foundsound = new MP3();
					
			//	5.	Set the title  and filename on  the appropriate object
					((MP3) foundsound).setTitle(mp3_titles[j]) ;
					
					
					String element = mp3_sounds[j];
					String f = "./program3/" + element + ".mp3";

					foundsound.setFileName(f);
					
			//	6.	Assign the appropriate object to an element of the Sounds array	
					genericSounds[soundcounter] = ((Sounds)foundsound);
					soundcounter++;
					
			//	7.	Display the following information on the console
			//	a.	The context of the matched word within in the command line paragraph – this consists of the following
			//	i.	the previous word
			//	ii.	the word itself
			//	iii.	the following (next) word
			//	b.	Invoke the print method of the element in the Generic Sound. This displays the appropriate title and filename		
					
					System.out.println("-----------------------------------------");
					System.out.println("Match Found: " + " " + previousword + " " + currentword + " " + nextword );
					
					((MP3) foundsound).print();
					
					System.out.println("-----------------------------------------");
					
			//	8.	Play the corresponding sound file. This is accomplished by invoking the play method on the element of the playlist.	
					
					foundsound.play();				
					
					
				}
			
			}
			
			for(int k = 0; k < wave_sounds.length; k++ )
			{
				if( words[i].contains(wave_sounds[k]))
				{
					
			//	4.	If a match is found, allocate the appropriate MP3 object or Wave object .
					
					WavePlayer foundsoundw = new Wave();
			
			//	5.	Set the title  and filename on  the appropriate object
					
					((Wave)foundsoundw).setTitle(wave_titles[k]);
			
					String element = wave_sounds[k];
					String f = "./program3/" + element + ".wav";
			
					foundsoundw.setFileName(f);
					
			//	6.	Assign the appropriate object to an element of the Sounds array
					genericSounds[soundcounter] = ((Sounds)foundsoundw);
					soundcounter++;
					
			//	7.	Display the following information on the console
			//	a.	The context of the matched word within in the command line paragraph – this consists of the following
			//	i.	the previous word
			//	ii.	the word itself
			//	iii.	the following (next) word
			//	b.	Invoke the print method of the element in the Generic Sound. This displays the appropriate title and filename
	
					System.out.println("_________________________________________");
					
					System.out.println("Match Found: " + " " + previousword + " " + currentword + " " + nextword);
					((Wave) foundsoundw).print();
					
					
					System.out.println("-----------------------------------------");
					
			//	8.	Play the corresponding sound file. This is accomplished by invoking the play method on the element of the playlist.
					foundsoundw.play();
					

				}
			}
		}
		}


		}


