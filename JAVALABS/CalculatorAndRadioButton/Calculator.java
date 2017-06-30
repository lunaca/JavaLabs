//Zac Brooks
// Programming Assignment 5


// Basic Calculator Java Program


package program5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.ScriptEngine;

class Calculator extends JFrame implements ActionListener {
	
	// FIRST DECLARE THE REQUIRED DATA FIELDS
	
		final int MAX_INPUT_LENGTH = 20;
		final int INPUT_MODE = 0;
		final int RESULT_MODE = 1;
		final int ERROR_MODE = 2;
		int displayMode;

		boolean clearnextpress;
		boolean percentage;
		double finalNumber;
		String lastOperator;
		boolean pexist = false;
		private JMenu jmenuFile, jmenuHelp;
		private JMenuItem jmenuitemExit, jmenuitemAbout;
		String inputString;
		private JTextField jtfOutput;
		private JButton jbnButtons[];
		private JPanel jplMaster, jplBackSpace, jplControl;
		
		Font f12 = new Font("Impact", 0, 12);
		Font f121 = new Font("Helvetica", 1, 12);
		
		//Constructor
		
		public Calculator() 
		{
			//ADD MENU BAR JUST TO SHOW OFF
			//FILE
			jmenuFile = new JMenu("File");
			jmenuFile.setFont(f121);
			jmenuFile.setMnemonic(KeyEvent.VK_F);
			
			//EXIT
			jmenuitemExit = new JMenuItem("Exit");
			jmenuitemExit.setFont(f12);
			jmenuitemExit.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_X, 
														ActionEvent.CTRL_MASK));
			jmenuFile.add(jmenuitemExit);

			//HELP
			jmenuHelp = new JMenu("Help");
			jmenuHelp.setFont(f121);
			jmenuHelp.setMnemonic(KeyEvent.VK_H);
			
			//ABOUT
			jmenuitemAbout = new JMenuItem("About Calculator");
			jmenuitemAbout.setFont(f12);
			jmenuHelp.add(jmenuitemAbout);
			
			//CREATE MENU BAR
			JMenuBar myMenu = new JMenuBar();
			myMenu.add(jmenuFile);
			myMenu.add(jmenuHelp);
			setJMenuBar(myMenu);
			
			//SET UP FRAME

			this.setBackground(Color.black);

			jplMaster = new JPanel();
			jplMaster.setBackground(Color.black);
			
			jtfOutput = new JTextField("0");
			jtfOutput.setEditable(false);
			jtfOutput.setOpaque(true);
			
			// Add display to north of frame
			this.add(jtfOutput, BorderLayout.NORTH);

			jbnButtons = new JButton[23];
			
			
			//MAKE THE BUTTONS!!!!!!
			

			JPanel jplButtons = new JPanel();
			jplButtons.setBackground(Color.black);
			
			// CREATE NUMBER BUTTONS USING FOR LOOP
			for (int i=0; i<=9; i++)
			{
				// set each Jbutton label to the value of index
				jbnButtons[i] = new JButton(String.valueOf(i));
			}

			// Create operator Jbuttons
			jbnButtons[10] = new JButton("+/-");
			jbnButtons[11] = new JButton(".");
			jbnButtons[12] = new JButton("=");
			jbnButtons[13] = new JButton("/");
			jbnButtons[14] = new JButton("*");
			jbnButtons[15] = new JButton("-");
			jbnButtons[16] = new JButton("+");
			jbnButtons[17] = new JButton("sqrt");
			jbnButtons[18] = new JButton("(");
			jbnButtons[19] = new JButton(")");
			
			jplBackSpace = new JPanel();
			jplBackSpace.setBackground(Color.black);;
			jplBackSpace.setLayout(new GridLayout(1, 1, 2, 2));

			jbnButtons[20] = new JButton("BACKSPACE");
			jplBackSpace.add(jbnButtons[20]);

			jplControl = new JPanel();
			jplControl.setLayout(new GridLayout(1, 2, 2 ,2));
			
			jplControl.setBackground(Color.black);
			jbnButtons[21] = new JButton(" CE ");
			jbnButtons[22] = new JButton("C");

			jplControl.add(jbnButtons[21]);
			jplControl.add(jbnButtons[22]);

			
			
			
//			SET ALL NUMBER BUTTONS TO RED AND OPS TO GREEN :)
			for (int i=0; i<jbnButtons.length; i++)	{
				jbnButtons[i].setFont(f12);

				if (i<10)
					jbnButtons[i].setForeground(Color.RED);
					
				else
					jbnButtons[i].setForeground(Color.GREEN);
			}
		
			
			
			
			//MAKE PANEL FOR 4 BY FIVE GRID
			jplButtons.setLayout(new GridLayout(4, 5, 2, 2));
			
			//STARTING AT TOP LEFT, ADD BUTTONS
			// First row
			for(int i=7; i<=9; i++)		{
				jplButtons.add(jbnButtons[i]);
			}
			
			// ADD BUTTON AND SQRROOT
			jplButtons.add(jbnButtons[13]);
			jplButtons.add(jbnButtons[17]);
			
			// SECOND ROW
			for(int i=4; i<=6; i++)
			{
				jplButtons.add(jbnButtons[i]);
			}
			
			// MULTIPLY AND CLOSE (
			jplButtons.add(jbnButtons[14]);
			jplButtons.add(jbnButtons[18]);

			// Third row
			for( int i=1; i<=3; i++)
			{
				jplButtons.add(jbnButtons[i]);
			}
			
			//adds button - and )
			jplButtons.add(jbnButtons[15]);
			jplButtons.add(jbnButtons[19]);
			
			//Fourth Row
			// add 0, +/-, ., +, and =
			jplButtons.add(jbnButtons[0]);
			jplButtons.add(jbnButtons[10]);
			jplButtons.add(jbnButtons[11]);
			jplButtons.add(jbnButtons[16]);
			jplButtons.add(jbnButtons[12]);
			
			jplMaster.setLayout(new BorderLayout());
			jplMaster.add(jplBackSpace, BorderLayout.WEST);
			jplMaster.add(jplControl, BorderLayout.EAST);
			jplMaster.add(jplButtons, BorderLayout.SOUTH);

			// ADD THE MASTER OF BUTTONS UNDER THE DISPLAY
			this.add(jplMaster, BorderLayout.SOUTH);
			this.requestFocus();
			
			//ADD ACTION LISTENER FOR THE BUTTONS
			for (int i=0; i<jbnButtons.length; i++){
				jbnButtons[i].addActionListener(this);
			}
			
			jmenuitemAbout.addActionListener(this);
			jmenuitemExit.addActionListener(this);

			clearAll();

			//add WindowListener for closing frame and ending program
			addWindowListener(new WindowAdapter() {

					public void windowClosed(WindowEvent e)
					{
						System.exit(0);
					}
				}
			);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}	//End of Contructor Calculator

		
		
		
		
		public void actionPerformed(ActionEvent e){
			
			double result = 0;
		   inputString = getDisplayString();
		   
			if(e.getSource() == jmenuitemAbout){
				JDialog dlgAbout = new CustomABOUTDialog(this, "About Java Swing Calculator", true);
				dlgAbout.setVisible(true);
			}else if(e.getSource() == jmenuitemExit){
				System.exit(0);
			}	
			
			// Search for the button pressed until end of array or key found
			for (int i=0; i<jbnButtons.length; i++)
			{
				if(e.getSource() == jbnButtons[i])
				{
					if(inputString.contains("("))
					{ pexist = true;
					
					if(i == 12)
					{
					
					processPEquals();
					return;
					}
					else if(i == 17)
					{
						return;
					}
					
					else
					{
						if (clearnextpress)
							
						{setDisplayString("");}
						
						else if (!clearnextpress)
						{
						
					setDisplayString(inputString + jbnButtons[i].getText());
					return;}
					
					}
					}
					else if (!inputString.contains("("))
					{pexist = false;
					
					switch(i)
					{
						case 0:
							addDigitToDisplay(i);
							break;

						case 1:
							addDigitToDisplay(i);
							break;

						case 2:
							addDigitToDisplay(i);
							break;

						case 3:
							addDigitToDisplay(i);
							break;

						case 4:
							addDigitToDisplay(i);
							break;

						case 5:
							addDigitToDisplay(i);
							break;

						case 6:
							addDigitToDisplay(i);
							break;

						case 7:
							addDigitToDisplay(i);
							break;

						case 8:
							addDigitToDisplay(i);
							break;

						case 9:
							addDigitToDisplay(i);
							break;

						case 10:	// +/-
							processSignChange();
							break;

						case 11:	// decimal point
							addDecimalPoint();
							break;

						case 12:	// =
							processEquals();
							break;

						case 13:	// divide
							processOperator("/");
							break;

						case 14:	// *
							processOperator("*");
							break;

						case 15:	// -
							processOperator("-");
							break;

						case 16:	// +
							processOperator("+");
							break;

						case 17:	// sqrt
							if (displayMode != ERROR_MODE)
							{
								try
								{
									if (getDisplayString().indexOf("-") == 0)
										displayError("Invalid input for function!");

									result = Math.sqrt(getNumberInDisplay());
									displayResult(result);
								}

								catch(Exception ex)
								{
									displayError("Invalid input for function!");
									displayMode = ERROR_MODE;
								}
							}
							break;

						case 18:	//open(
							
							if (clearnextpress)
							{
								setDisplayString("(");
								clearnextpress = false;
							}
							else if (inputString.indexOf("0") == 0)
							{setDisplayString("(");
							}
							else 
							{
							
							setDisplayString(inputString + "(");
							return;}
							break;
							
						case 19:	
							
							setDisplayString(inputString + ")");
							break;

						case 20:	// backspace
							if (displayMode != ERROR_MODE){
								setDisplayString(getDisplayString().substring(0,
											getDisplayString().length() - 1));
								
								if (getDisplayString().length() < 1)
									setDisplayString("0");
							}
							break;

						case 21:	// CE
							clearExisting();
							break;

						case 22:	// C
							clearAll();
							break;
					}
					}
				}
			}
		}
		
		///Returns the corresponding calculator’s JButton based on the input parameter label. For example, if the label parameter is assigned to “+/-“, this method will return the JButton on the calculator corresponding to “+/-“ button. If the label is assigned “9”, the JButton corresponding the number 9 digit is returned.
				
		public JButton getButton(String label)
		{ 	JButton thechosenone = new JButton();
			for (int i=0; i<jbnButtons.length; i++)
			{
				if(label.equals(jbnButtons[i].getText()))
						{
						thechosenone =  jbnButtons[i];
						}
			}
			return thechosenone;
		}
		
		//Returns the JTextField used for displaying all intermediate and final results

		public JTextField getJTextField()
		{
		JTextField heckyes = new JTextField();
		heckyes = this.jtfOutput;
		return heckyes;
		}
		
		
		
		void setDisplayString(String s){
			jtfOutput.setText(s);
		}

		String getDisplayString (){
			return jtfOutput.getText();
		}

		void addDigitToDisplay(int digit){
			if (clearnextpress)
				setDisplayString("");

			inputString = getDisplayString();
			
			if (inputString.indexOf("0") == 0){
				inputString = inputString.substring(1);
			}

			if ((!inputString.equals("0") || digit > 0)  && inputString.length() < MAX_INPUT_LENGTH){
				setDisplayString(inputString + digit);
			}
			

			displayMode = INPUT_MODE;
			clearnextpress = false;
		}

		void addDecimalPoint(){
			displayMode = INPUT_MODE;

			if (clearnextpress)
				setDisplayString("");

			 inputString = getDisplayString();
		
			//IF DISPLAY ALREADY HAS DECIMAL, DON'T DO ANYTHING TO IT
			if (inputString.indexOf(".") < 0)
				setDisplayString(new String(inputString + "."));
		}

		void processSignChange(){
			if (displayMode == INPUT_MODE)
			{
			 inputString = getDisplayString();

				if (inputString.length() > 0 && !inputString.equals("0"))
				{
					if (inputString.indexOf("-") == 0)
						setDisplayString(inputString.substring(1));

					else
						setDisplayString("-" + inputString);
				}
				
			}

			else if (displayMode == RESULT_MODE)
			{
				double numberInDisplay = getNumberInDisplay();
			
				if (numberInDisplay != 0)
					displayResult(-numberInDisplay);
			}
		}

		void clearAll()	{
			setDisplayString("0");
			lastOperator = "0";
			finalNumber = 0;
			displayMode = INPUT_MODE;
			clearnextpress = true;
			pexist = false;
		}
		void clearExisting(){
			setDisplayString("0");
			clearnextpress = true;
			displayMode = INPUT_MODE;
			pexist = false;
		}

		double getNumberInDisplay()	{
			String input = jtfOutput.getText();
			return Double.parseDouble(input);
		}

		void processOperator(String op) {
			if (displayMode != ERROR_MODE)
			{
				double numberInDisplay = getNumberInDisplay();

				if (!lastOperator.equals("0"))	
				{
					try
					{
						double result = processLastOperator();
						displayResult(result);
						finalNumber = result;
					}

					catch (DivideByZeroException e)
					{
					}
				}
			
				else
				{
					finalNumber = numberInDisplay;
				}
				
				clearnextpress = true;
				lastOperator = op;
			}
		}

		void processEquals(){
			double result = 0;

			if (displayMode != ERROR_MODE){
				try			
				{
					result = processLastOperator();
					displayResult(result);
				}
				
				catch (DivideByZeroException e)	{
					displayError("Cannot divide by zero!");
				}

				lastOperator = "0";
			}
		}
		void processPEquals()
		{
			double result = 0;
			ScriptEngineManager mgr = new ScriptEngineManager();
			ScriptEngine engine = mgr.getEngineByName("JavaScript");
			
			
			try {
				result =  (double) engine.eval(inputString);
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			displayResult(result);
			
			
			
		}
		double processLastOperator() throws DivideByZeroException {
			double result = 0;
			double numberInDisplay = getNumberInDisplay();

			if (lastOperator.equals("/"))
			{
				if (numberInDisplay == 0)
					throw (new DivideByZeroException());

				result = finalNumber / numberInDisplay;
			}
				
			if (lastOperator.equals("*"))
				result = finalNumber * numberInDisplay;

			if (lastOperator.equals("-"))
				result = finalNumber - numberInDisplay;

			if (lastOperator.equals("+"))
				result = finalNumber + numberInDisplay;

			return result;
		}

		void displayResult(double result){
			setDisplayString(Double.toString(result));
			finalNumber = result;
			displayMode = RESULT_MODE;
			clearnextpress = true;
			pexist = false;
		}

		void displayError(String errorMessage){
			setDisplayString(errorMessage);
			finalNumber = 0;
			displayMode = ERROR_MODE;
			clearnextpress = true;
		}

	
		public static void main(String args[]) {
			Calculator calci = new Calculator();
			Container contentPane = calci.getContentPane();
			calci.setTitle("Java Swing Calculator");
			calci.setSize(241, 217);
			calci.pack();
			calci.setLocation(400, 250);
			calci.setVisible(true);
		
		}
	}
	

class DivideByZeroException extends Exception{
	public DivideByZeroException()
	{
		super();
	}
	
	public DivideByZeroException(String s)
	{
		super(s);
	}
}

class CustomABOUTDialog extends JDialog implements ActionListener {
	JButton jbnOk;

	CustomABOUTDialog(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		setBackground(Color.black);
		
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));

		StringBuffer text = new StringBuffer();
		text.append("About this Fucking Badass Calculator\n\n");
		text.append("Developer:	Zachary Alexander Brooks\n");
		text.append("Version:	1.0");
		
		JTextArea jtAreaAbout = new JTextArea(5, 21);
		jtAreaAbout.setText(text.toString());
		jtAreaAbout.setFont(new Font("Times New Roman", 1, 13));
		jtAreaAbout.setEditable(false);

		p1.add(jtAreaAbout);
		p1.setBackground(Color.red);
		getContentPane().add(p1, BorderLayout.CENTER);

		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jbnOk = new JButton(" OK ");
		jbnOk.addActionListener(this);

		p2.add(jbnOk);
		getContentPane().add(p2, BorderLayout.SOUTH);

		setLocation(408, 270);
		setResizable(false);

		addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e)
				{
					Window aboutDialog = e.getWindow();
					aboutDialog.dispose();
				}
			}
		);

		pack();
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == jbnOk)	{
			this.dispose();
		}
	}
}
	
	
