package program5;




import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class RadioButtonTester
{

private int counter = 0;
private static RadioButton rbb;

public static void main(String[] args) throws Exception
{
 //Variable Declaration/Initialization
 int i = 0;
 int j = 0;
 int k = 0;
 int score = 20;

 int[][] clicks = { {7,3,2,4,4}};

 Date date = new Date();
 long value = date.getTime();
 long index = 0;

 //Loop thru all values



 String[] array = {"Bird","Pig","Cat","Dog","Rabbit"};

 RadioButtonTester tl6 = new RadioButtonTester();
 rbb = new RadioButton();


  //Get the radio buttons
  for (i = 0; i < 5; i++)
  {

    JRadioButton rb = rbb.getRadioButtonAt(i);

    //Get info about the button
    String rb_text = rb.getText();

    for (j = 0; j < clicks[(int)index][i]; j++)
    {
      rb.doClick(100);
      Thread.sleep(1600);

      String answer = array[i] + " button " + " clicked " + (j+1);

      //Get the label
      JLabel jl = rbb.getLabel();

      String text = jl.getText();

      if (!text.equals(answer))
      {
        System.out.println("Error in RadioButton #" + i);
        System.out.println("Expected Answer: " +  answer);
        System.out.println("Your Answer: " + text);
        System.out.println("-2");
        score -= 2;
      }

      //Get the Image Icon
      JLabel img = rbb.getImage();
      ImageIcon pic = (ImageIcon)img.getIcon();
      String res = pic.toString();

      //Make sure image matches
      answer = "program5/" + rb_text + ".gif";
      if (res.equals(answer) == false)
      {
        System.out.println("Error in Image" + i);
        System.out.println("Expected Answer: " +  answer);
        System.out.println("Your Answer: " + res);
        System.out.println("-2");
        score -= 2;
      }





    }
  }
  
  if (score == 20)
  	System.out.println("Success");
}



}
