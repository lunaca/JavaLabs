//Zac Brooks
//Radio Button


package program5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class RadioButton extends JFrame
{
	private String[] names = {"Bird","Pig","Cat","Dog","Rabbit"};
	private JRadioButton [] radioButtons = new JRadioButton[5];
	private int [] counts = {0, 0, 0, 0, 0};
	private ImageIcon ic;
	JLabel picture;
	JLabel clicks = new JLabel();
	String s;
	
	public RadioButton()
	{
		//set frame title and close on exit
		super("RadioButton");
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		//PICTURE PANEL
		
		//buffer for pic
		JPanel picBuffer = new JPanel(new FlowLayout());
		
		//add jLabel
		ic = new ImageIcon("program5/Bird.gif");
		ic.setDescription("Bird");
		Image bird = ic.getImage();
		picture = new JLabel(ic);
		picBuffer.add(picture);
		this.add(picBuffer, BorderLayout.WEST);
		
		//RADIOBUTTONS PANEL
		JPanel rButtons = new JPanel(new GridLayout(5, 1));
		ButtonGroup bg = new ButtonGroup();
		
		//create radioButtons
		myActionHandler actionHandler = new myActionHandler();
		for (int i = 0; i < names.length; i++)
		{
			JRadioButton jrb = new JRadioButton(names[i]);
			jrb.addActionListener(actionHandler);
			radioButtons[i] = jrb;
			rButtons.add(jrb);
			bg.add(jrb);
		}
		this.add(rButtons, BorderLayout.CENTER);
		
		//BUTTON CLICKED DISPLAY
		JPanel right = new JPanel(new BorderLayout());
		clicks.setText("                                                           ");
		right.add(clicks, BorderLayout.CENTER);
		this.add(right, BorderLayout.EAST);
		
		//display GUI
		this.pack();
		this.setVisible(true);
	}

	class myActionHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Object source = e.getSource();
			
			if(source instanceof JRadioButton)
			{
				JRadioButton rb = (JRadioButton)source;
				s = rb.getText();
				String fileName = "program5/" + s + ".gif";
				ImageIcon ic = new ImageIcon(fileName);
				picture.setIcon(ic);
				for(int i = 0; i < 5; i++)
				{
					if(s.equals(names[i]))
					{
						counts[i]++;
						clicks.setText(s + " button  clicked " + counts[i]);
					}
				}
			}
		}
		
	}
	
	public JLabel getLabel()
	{
		return clicks;
	}
	
	public JRadioButton getRadioButtonAt(int index)
	{
		return radioButtons[index];
	}
	
	public JLabel getImage()
	{
		return picture;
	}
	
	public static void main(String[] args)
	{
		RadioButton s = new RadioButton();
	}

}
