package program3;

//AUSTIN RICE
//PROGRAM 3






public class Wave extends WavePlayer implements Sounds
	{
	
	
		//Data fields
		protected String title;
		
		
		
		//methods to match sounds:
		
		
		public void play()
		{
			
			//call run from inherited wave player
			
			this.run();
		}
		
		public void print()
		{
			
			//get file name
			String file = getFileName();
			
			//print out the title and path
			
			
			System.out.println("Title: " + title);
			System.out.println("File Name: " + file);
		}
		
		public void setTitle(String title)
		{
			//sets the title of the wave
			
			this.title = title;
			
		}
		
		public String getTitle()
		{
			
			//gets the title of the wave file
			return title;
		}
		
		
	}
		




