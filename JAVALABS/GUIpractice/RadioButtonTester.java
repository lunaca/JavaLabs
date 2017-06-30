package program4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RadioButtonTester
{
 private static JTextField jtf;
  private int counter = 0;

  class ButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      //System.out.println("Button was selected");
      try
      {
        Thread.sleep(500);
      }
      catch(InterruptedException ie)
      {
        System.out.println(ie.getMessage());
        ie.printStackTrace();
      }
      jtf.setText("Button #" + counter++ + " was pressed!");
    }
  }

 public static void main(String[] args)  throws Exception
 {
   //Variable Declaration/Initialization
   int i = 0;
   int j = 0;
   int k = 0;
   int score = 30;
   String[] answer_text = {"Button #0 was pressed!",
                           "Button #1 was pressed!",
                           "Button #2 was pressed!"};

   String[] answer_button = {"Button 0", "Button 1", "Button 2"};

   String[] answer_label = {"Im label #0", "Im label #1", "Im label #2"};

   String[] array = {"Bird","Pig","Cat","Dog","Rabbit"};

   RadioButtonTester tl6 = new RadioButtonTester();
   RadioButtonTest rbt = new RadioButtonTest("Test 1");

   for (i = 0; i < 3; i++)
   {

    //Get the text field
    jtf = rbt.getTextAt(i);

    //Set the text color
    jtf.setForeground(Color.green);

    //Set the background color
    jtf.setBackground(Color.blue);

    //Set the text
    jtf.setText("Hello World Everyone!");

    //Get the button
    JButton button = rbt.getButtonAt(i);

    //Get info about the button
    String button_text = button.getText();

    //Add a listener
    button.addActionListener( new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
       try
       {
         Thread.sleep(500);
       }
       catch(InterruptedException ie)
       {
         System.out.println(ie.getMessage());
         ie.printStackTrace();
       }
       //System.out.println("Button was selected");

      }
    } );

    button.addActionListener(tl6.new ButtonListener());

    Thread.sleep(500);

    button.doClick(500);

    //Get the textfield
    jtf = rbt.getTextAt(i);

    //Compare to result
    String result_text = jtf.getText();

    if (!result_text.equals(answer_text[i]))
    {
      System.out.println("\n\nError in Text Field # " + i);
      System.out.println("Expected Answer: " +  answer_text[i]);
      System.out.println("Your Answer: " + result_text);
      System.out.println("-2");
      score -= 2;
    }

    //Get button
    String button_text1 = button.getText();

    if (!button_text1.equals(answer_button[i]))
    {
      System.out.println("Error in Text Field # " + i);
      System.out.println("Expected Answer: " +  answer_button[i]);
      System.out.println("Your Answer: " + button_text1);
      System.out.println("-2");
      score -= 2;
    }

    //Get Label
    JLabel label = rbt.getLabelAt(i);

    label.setText("Im label #" + i);

    String label_text = label.getText();

    if (!label_text.equals(answer_label[i]))
    {
      System.out.println("Error in Label #" + i);
      System.out.println("Expected Answer: " +  answer_label[i]);
      System.out.println("Your Answer: " + label_text);
      System.out.println("-2");
      score -= 2;
    }

   }

    //Get the radio buttons
    for (i = 0; i < 5; i++)
    {
      JRadioButton rb = rbt.getRadioButtonAt(i);

      //Get info about the button
      String rb_text = rb.getText();

      if (!rb_text.equals(array[i]))
      {
        System.out.println("Error in RadioButton #" + i);
        System.out.println("Expected Answer: " +  array[i]);
        System.out.println("Your Answer: " + rb_text);
        System.out.println("-2");
        score -= 2;
      }

      rb.doClick(500);

      //Check the button
      boolean result = rb.isSelected();

      for (j = 0; j < 5; j++)
      {
        rb = rbt.getRadioButtonAt(j);
        result = rb.isSelected();
        if ((i == j) && (result != true))
        {
          System.out.println("Error on Radio Button #" + i );
          System.out.println("Make sure this one is selected!");
          System.out.println("Check your ButtonGroup!");
          System.out.println(-5);
          score -= 2;
        }
        else if ((i != j) && (result == true))
        {
          System.out.println("Error on Radio Button #" + i);
          System.out.println("Make sure this one is not selected!");
          System.out.println("Check your ButtonGroup");
          System.out.println(-5);
          score -= 2;
        }
      }
    }

    //Get the check boxes
    for (i = 0; i < 5; i++)
    {
      JCheckBox cb = rbt.getCheckBoxAt(i);

      //Get info about the button
      String cb_text = cb.getText();

      if (!cb_text.equals(array[i]))
      {
        System.out.println("Error in CheckBox #" + i);
        System.out.println("Expected Answer: " +  array[i]);
        System.out.println("Your Answer: " + cb_text);
        System.out.println("-2");
        score -= 2;
      }

      cb.doClick(500);

      //Check the button
      boolean result = cb.isSelected();

      for (j = 0; j < 5; j++)
      {
        cb = rbt.getCheckBoxAt(j);
        result = cb.isSelected();
        if ((i == j) && (result != true))
        {
          System.out.println("Error on Check Box #" + i );
          System.out.println("Make sure this one is selected!");
          System.out.println(-2);
          score -= 2;
        }

      }
    }

    //Process the JLabel
    JLabel jlabel = rbt.getImage();
    //Get the icon
    ImageIcon ic = (ImageIcon)jlabel.getIcon();

    //Get the string
    String str = ic.getDescription();

    //Verify bird is found
    if (!str.equals("Bird"))
    {
      System.out.println("Error in initial ImageIcon " );
      System.out.println("Expected Answer: " +  "Bird");
      System.out.println("Your Answer: " + str);
      System.out.println("-2");
      score -= 2;
    }

    //Process all image icons
    for (i = 0; i < 5; i++)
    {
      JRadioButton rb2 = rbt.getRadioButtonAt(i);
      rb2.doClick(1000);
      ImageIcon ic1 = new ImageIcon("program4/" + array[i] + ".gif",array[i]);

      //Set the labels icon
      jlabel.setIcon(ic1);

      //Get the string
      JLabel jl = rbt.getImage();

      //Get the icon
      ImageIcon ic2 = (ImageIcon)jl.getIcon();

      //Get the string
      String str1 = ic2.getDescription();

      //Verify correct picture found
      if (!str1.equals(array[i]))
      {
        System.out.println("Error in ImageIcon #" + i );
        System.out.println("Expected Answer: " +  array[i]);
        System.out.println("Your Answer: " + str1);
        System.out.println("-2");
        score -= 2;
      }
    }



    if (score == 30)
    	System.out.println("Success");
    
 }
}

