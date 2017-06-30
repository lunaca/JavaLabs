package program4;

import javax.swing.*;
import java.awt.*;

public class RadioButtonTest extends JFrame {
	
		
		private String[] names = {"Bird", "Pig", "Cat", "Dog", "Rabbit"};
		private String[] labels = {"Name", "Address", "Phone", "Zip"};
		private int[] lengths = {10,20,7,5};
		private JTextField[] tf  = new JTextField[7];
		private JCheckBox [] cb = new JCheckBox[7];
		private JButton [] jb = new JButton[7];
		private JLabel [] jl = new JLabel[7];
		private JLabel jimage ;
		private JRadioButton [] jrb = new JRadioButton[7];
		
	public RadioButtonTest(String title)
		{
			super(title);
			int i = 0;
			int j = 0;
			int counter = 0;

			//And set closing operation on this class
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
			//THIS IS THE CONSTRUCTOR FOR GRIDLAYOUT
			JPanel p1 = new JPanel(new GridLayout(5, 1));
			
			//2. Add JRadioButtons to Panel p1

			 ///	BY DEFAULT, JRADIOBUTTONS CN BE MULTIPLY SELECTED. :O
		    /// YOU HAVE TO HELP JAVA OUT BY GROUPING JRB IN A BUTTONG GROUP
		    /// BUTTON GROUPS ARE NOT A GUI COMPONENT, ITS A LOGICAL COMPONENT. ASSURES MUTUALL EXCLUSIVENESS FOR JRB
		    
			//Make the JRadioButtons mutually exclusive
			//1. Create ButtonGroup 
			ButtonGroup bg = new ButtonGroup();
			
		    //Create a JRadio Button  for each picture - store in JPanel
		    for (i = 0; i < names.length; i++)
		    {
		      //Create JRadiouButton
		     jrb[i] = new JRadioButton(names[i]);
		      p1.add(jrb[i]);
		      bg.add(jrb[i]);;
		    }

		  //Create yet another JPanel and add the Image and JLabel
		  JPanel graphics = new JPanel();
		  jimage= new JLabel(names[0]);
		  String Bird = "program4/" + names[0] + ".gif";
		  ImageIcon Bird1 = new ImageIcon(Bird);
		  Bird1.setDescription("Bird");
		  jimage.setIcon(Bird1);
		  graphics.add(jimage);
		 


		    //WITH A BUFFER, THE NORMAL CONSTRUCTOR OF JPANEL (FLOW LAYOUT) WILL KEEP YOUR PREFFERED SIZE
		  //3. Buffer p1 with another JPanel to keep preferred size

		    JPanel outer = new JPanel();
		    outer.add(graphics);
		    outer.add(p1);
		    
		  //6. Add Another JPanel for CheckBoxes (5x1) (above loop)
		    JPanel p2 = new JPanel(new GridLayout(5,1));
		    
		    for( i = 0; i < names.length; i++)
		    	
		    {
		    	//7. Create JCheckBox within current Loop and add to p2
		    	cb[i] = new JCheckBox(names[i]);
		    	p2.add(cb[i]);
		    }

		    outer.add(p2);
		    
		   
		     JPanel another = new JPanel(new BorderLayout());
		     

		 
		    //Add to JPanel for Buffering
		    JPanel randomfields = new JPanel(new GridLayout(3, 3));
		    for(i = 0; i < 3; i++)
		    {
		    	jl[i] = new JLabel("Label" + i);
		    	jb[i] = new JButton("Button" + " " + i);
		    	tf[i] = new JTextField ("Button" + i, 20 );
		    	
		    	JPanel rpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		    	
		    	rpanel.add(jl[i]);
		    	rpanel.add(jb[i]);
		    	rpanel.add(tf[i]);
		    	
		    	randomfields.add(rpanel);
		    	
		    }
		    
		    JPanel bufferer = new JPanel();
		    bufferer.add(randomfields);
	
		   //SO NOW YOU HAVE TWO PANELS, ONE CALLED LOWER AND THAN ONE CALLED BUTTONS
		    another.add(bufferer, BorderLayout.SOUTH);
		   
		    
		  //4. Add JPanel to Center of JFrame
		    this.add(outer,BorderLayout.NORTH);
		    this.add(bufferer,BorderLayout.CENTER);
		    //this.add(outer1,BorderLayout.NORTH);
		    
		    //Add pack the GUI
		    this.setVisible(true);
		    this.pack();

		  
		 	    


		}
		

public JCheckBox getCheckBoxAt(int index)
//Returns the corresponding JCheckBox object that corresponds to the input parameter index. The JCheckBoxes are numbered from 0 to 4 as shown on page 4.
{
	JCheckBox selectedone = new JCheckBox();
	selectedone = cb[index];
	return selectedone;
}
public JRadioButton getRadioButtonAt(int index)
//Returns the corresponding JRadioButton object that corresponds to the input parameter index. The JRadioButtons are numbered from 0 to 4 as shown on page 4.
{
	JRadioButton selectedone = new JRadioButton();
	selectedone = jrb[index];
	return selectedone;
}
public JButton getButtonAt(int index)
//Returns the corresponding JButton object that corresponds to the input parameter index. The JButtons are numbered from 0 to 3 as shown on page 4.
{
	JButton selectedone = new JButton();
	selectedone = jb[index];
	return selectedone;
}
public JLabel getLabelAt(int index)
//Returns the corresponding JLabel object that corresponds to the input parameter index. The JLabels are numbered from 0 to 3 as shown on page 4
{
	JLabel selectedone = new JLabel();
	selectedone = jl[index];
	return selectedone;
}
public JTextField getTextAt(int index)
//Returns the corresponding JTextField object that corresponds to the input parameter index. The JTextFields are numbered from 0 to 3 as shown on page 4
{
	JTextField selectedone = new JTextField();
	selectedone = tf[index];
	return selectedone;
}
public JLabel getImage()
//Returns the JLabel at the top of the image containing the image.
{
	JLabel selectedone = new JLabel();
	selectedone = jimage;
	return selectedone;
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RadioButtonTest("Test 1");
	}
}
