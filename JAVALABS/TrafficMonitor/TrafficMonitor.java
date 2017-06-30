package program6;

import java.awt.*;

import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;



import java.io.*;
import java.nio.charset.*;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.ScriptEngine;

public class TrafficMonitor extends JFrame implements ActionListener {
	JLabel message = new JLabel("");
	private JEditorPane jta;
	String[] cities = new String[50];
	JButton[] buttons = new JButton[50];
	JButton remove = new JButton();
	JButton add = new JButton();
	JTextField jtf = new JTextField();
	int x = 0 ;
	int goodindex = 0;
	int j = 0;
TrafficMonitor() throws Exception
{
	
super("TrafficMonitor");

String reader = "";
this.j = 0;

try (
		InputStream fis = new FileInputStream("program6/cities.txt");
		InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		BufferedReader br = new BufferedReader(isr);
) {
	while ((reader = br.readLine()) != null) { 
		cities[j] = reader;
		
		this.j++;
	}
}
	
 JPanel all = new JPanel();
 all.setLayout(new BorderLayout());
 
 JPanel grid1 = new JPanel(new GridLayout(10,1,5,5));
 
 int i = 0;
 for (i = 0;  i< j; i++) 
 {  String counter = "" + i;
 	JButton button = new JButton(cities[i]);
 	
 	

   grid1.add(button);
   buttons[i] = button;
 }
 
 JScrollPane scrollPane = new JScrollPane(grid1);
 
 //Set the scrollbars
 JPanel buttonscroll = new JPanel();
 scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
 scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
 scrollPane.setPreferredSize(new Dimension(200,100));
 
 //Add to JPanel to keep preferred size
 buttonscroll.add(scrollPane);
 

 all.add(buttonscroll, BorderLayout.SOUTH);
 
 
 JPanel centerp = new JPanel(new BorderLayout());
 JPanel centerenter = new JPanel();
 JLabel entercity = new JLabel("Enter City");
 centerenter.add(entercity);
 centerp.add(centerenter, BorderLayout.NORTH);
 
 
 JPanel gridouter = new JPanel();
 JPanel gridder = new JPanel(new GridLayout(2,1,1,1));
 this.add.setText("ADD");
 this.remove.setText("REMOVE");
 gridder.add(add);
 gridder.add(remove);
 gridouter.add(gridder);
 centerp.add(gridouter, BorderLayout.SOUTH);
 
 
 JPanel centercenter = new JPanel(new FlowLayout());
 JLabel city = new JLabel("City");
 
 this.jtf.setPreferredSize(new Dimension(350,20));
 centercenter.add(city);
 centercenter.add(jtf);
 centercenter.add(message);
 centerp.add(centercenter, BorderLayout.CENTER);
 
 all.add(centerp, BorderLayout.CENTER);
 
 
 
 
 
 URL url = new URL("https://wego.here.com/traffic/usa/dallas");
//Create Return value
String turl = " https://wego.here.com/traffic/usa/dallas";
JPanel jp = new JPanel(new FlowLayout(FlowLayout.CENTER));

//Create Text area
jta = new JEditorPane(url);

//Disable editing to enable hyperlinks
jta.setEditable(false);

//Add handler for hyperlink events
jta.addHyperlinkListener(new HyperlinkHandler());

//Create a scrollable Pane
jta.setContentType("text/html");
jta.setText("<center><h1>Traffic Monitoring System</h1></center>");
JScrollPane jsp1 = new JScrollPane(jta);

//Set preferred size
jsp1.setPreferredSize(new Dimension(600,300));
jsp1.setMinimumSize(new Dimension(700,200));

//Add the Pane to the Panel
jp.add(jsp1);

all.add(jp,BorderLayout.NORTH);



 
 
 

 
 
 this.add(all);
 this.setVisible(true);
 this.pack();
 
 for (int k=0; k<j; k++){
		buttons[k].addActionListener(this);
	}
	
	add.addActionListener(this);
	remove.addActionListener(this);

 
 
 
 addWindowListener(new WindowAdapter() {

		public void windowClosed(WindowEvent e)
		{
			System.exit(0);
		}
	}
);
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
	
}
	
public void actionPerformed(ActionEvent e){
	
	

  if(e.getSource() == this.remove)
  {
	  
	  
	   		boolean linefound = false; 
	  		String file = "program6/cities.txt";
	  		String lineToRemove = this.jtf.getText();
		    try {

		      File inFile = new File(file);

		      if (!inFile.isFile()) {
		        System.out.println("Parameter is not an existing file");
		        return;
		      }

		      //Construct the new file that will later be renamed to the original filename.
		      File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

		      BufferedReader br = new BufferedReader(new FileReader(file));
		      PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

		      String line = null;

		      //Read from the original file and write to the new
		      //unless content matches data to be removed.
		      while ((line = br.readLine()) != null) {

		        if (!line.trim().equals(lineToRemove)) {
		        	
		        	
		          pw.println(line);
		          pw.flush();
		          
		         
		        }
		        if (line.trim().equals(lineToRemove))
		        {
		        	linefound = true;
		        	
		        }
		      }
		     
		      pw.close();
		      br.close();

		      //Delete the original file
		      if (!inFile.delete()) {
		        System.out.println("Could not delete file");
		        return;
		      }

		      //Rename the new file to the filename the original file had.
		      if (!tempFile.renameTo(inFile))
		        System.out.println("Could not rename file");

		    }
		    catch (FileNotFoundException ex) {
		      ex.printStackTrace();
		    }
		    catch (IOException ex) {
		      ex.printStackTrace();
		    }
		  
	  this.jtf.setText("");
	  
	 if (linefound == true) 
		 this.message.setText("Your City Was Removed");
	 else if (linefound == false) 
		 this.message.setText("City Not Found");
    
  }
	
	else if(e.getSource() == this.add)
	
   {
	 
	  String newcity = "";
	  newcity = this.jtf.getText();
	  
	  
	  cities[this.j ] = newcity;
	  
//	  for(int p = 0; p < (j+1) ; p++)
//	  {		System.out.println(this.j);
//		  System.out.println(p + ":");
//		  System.out.println(cities[p]);
//	  }
	  
	  this.jtf.setText("");
	  
	  
	  try {
		this.write("program6.cities.txt", cities);
		this.message.setText("Your City Was Added!");
	} catch (IOException e1) {
		this.message.setText("Your City Was Not Added Succesfully");
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	   
   }
	   
	// Search for the button pressed until end of array or key found
	for (int x=0; x<buttons.length; x++)
	{
		if(e.getSource() == buttons[x])
		{
			goodindex = x ; 
			System.out.println(goodindex);
			System.out.println(cities[goodindex]);
			jta.setText("<center><h1>Traffic Information for" + " "  + buttons[goodindex].getText()   + " can be found at </h1></center>" + "<center><h1>https://wego.here.com/traffic/usa/" + buttons[goodindex].getText() + "</h1></center>" );
		}
		}
		}
public static void main(String args[]) throws Exception
{
	new TrafficMonitor();
}






public class HyperlinkHandler implements HyperlinkListener
{
public void hyperlinkUpdate(HyperlinkEvent event)
{
  URL url = null;

  HyperlinkEvent.EventType ev = event.getEventType();
  if (ev == HyperlinkEvent.EventType.ACTIVATED)
  {
    try
    {
      url = event.getURL();
      jta.setPage(url);
    }
    catch(IOException io)
    {
      System.out.println(io.getMessage());
      io.printStackTrace();
    }
  }

}
}

public void write (String filename, String[]x) throws IOException{
	File fold=new File("program6/cities.txt");
	fold.delete();
	File fnew=new File("program6/cities.txt");
	
	BufferedWriter outputWriter = null;
	  outputWriter = new BufferedWriter(new FileWriter(fnew, false));
	  for (int i = 0; i <= this.j; i++) {
	   
	    // Or:
	    outputWriter.write(x[i]);
	    outputWriter.newLine();
	    
	  }
	  outputWriter.flush();  
	  outputWriter.close();  
	}
}


