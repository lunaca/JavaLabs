package program5;

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class CalculatorTester
{
  private int score = 120;

  public CalculatorTester()
  {
  }

  public  double genOperand(long value,double value1,int[] exponent,
                            int[] digits, int[] neg_exponent,int count)
  {
    int i = 0;
    double operand = 0.0;

    //Get # of digits
    int no_digits = (int)(value+value1+count)%3;

    if (no_digits == 0)
      no_digits = 1;

    for (i = 0; i < no_digits; i++)
    {
      operand += (int)Math.pow( ((value%5)+value1+count+no_digits)%3,(double)exponent[(int)((value%9)+value1+13+count)%3]);
      operand *= 1.333;
    }

    for (i = 0; i < no_digits; i++)
    {
      operand += Math.pow( ((value%7)+value1+count)%3,neg_exponent[(int)((value%7)+value1+count)%3]);
    }
    return operand;
  }

  /*String[] paren = { "((((a+b)/c)-d)+e)","(((a+b)+c)-d)",
    "((a/b)*c)", "(a+b)/c", "((a+b)*c)-d)",
    "((a/c)*d)", "(a-b)*d" };*/

  private double getExpectedResults(double a,double b,
                double c,double d,double e,int index)
  {
    double expected_results = -1;

    switch(index)
    {
      case 0:
          expected_results = ((((a+b)/c)-d)+e);
          break;

      case 1:
          expected_results = (((a+b)+c)-d);
          break;
      case 2:
          expected_results = ((a/b)*c);
          break;
      case 3:
          expected_results = (a+b)/c;
          break;
      case 4:
          expected_results = (((a+b)*c)-d);
          break;
      case 5:
          expected_results = ((a/c)*d);
          break;
      case 6:
          expected_results = ((a-b)*d);
          break;

      default:
          System.out.println("ERROR");
          System.out.println("Tester has been changed");
          System.out.println("Download tester again");
          break;
      }

      return(expected_results);

  }

  private void pressButtons(Calculator calculator, String str,int index,double a,double b,
                double c,double d,double e)
  {
    int i = 0;
    JButton jb;
    String label;
    //Press buttons for operand1
    for (i = 0; i < str.length(); i++)
    {
      //Get the JButton
      label = str.substring(i,i+1);
      jb = calculator.getButton(label);
      jb.doClick(100);
    }

    //Get the textfield
    JTextField jtf = calculator.getJTextField();
    String jtf_value = jtf.getText();

    //Compare values
    if (!str.equals(jtf_value))
    {
      System.out.println("You are not displaying parenthetical expressions properly!");
      System.out.println("Expected JTextField: " + str);
      System.out.println("Actual JTextField: " +jtf_value);
      System.out.println("-10");
      score -= 10;
      System.out.println("\n\n\n\n");
    }

    //Click =
    jb = calculator.getButton("=");
    jb.doClick(1000);

    //Get the textfield

    String res_str = jtf.getText();
    double value3 = Double.parseDouble(res_str);

    //Get the expected results
    double expected_results = getExpectedResults(a,b,c,d,e,index);


    //Display Error if incorrect results
    double diff = Math.abs(expected_results - value3);

    if (diff > 0.75)
    {
      //System.out.println("a: " + a + " b: " + b + " c: " + c + " d: " + d + " e: " + e);
      System.out.println("Incorrect Answer for Expression: " + str);
      System.out.println("Your Answer: " + value3);
      System.out.println("Expected Answer: " + expected_results);
      System.out.println("Check basic +/-* operators and all digits");
      System.out.println("-7");
      score -= 7;
      System.out.println("\n\n\n\n");

    }
  }



  private void pressButtons(Calculator calculator,double operand1)
  {
    //Convert operands to String
    int i = 0;
    String label;
    JButton jb;
    String str1 = String.valueOf(operand1);

    //Truncate both strings to 3 decimal places
    int dec_place = str1.indexOf('.');

    //Get the str
    String str_op1 = str1.substring(0,dec_place+3);

    //Press buttons for operand1
    for (i = 0; i < str_op1.length(); i++)
    {
      //Get the JButton
      label = str_op1.substring(i,i+1);
      jb = calculator.getButton(label);
      jb.doClick(100);
    }

  }


  private void pressButtons(Calculator calculator,double operand1,double operand2,
            boolean neg_value,int op_pos,String[] operators,int[] digits, boolean eval)
  {
    //Convert operands to String
    int i = 0;
    String label;
    JButton jb;
    String str1 = String.valueOf(operand1);
    String str2 = String.valueOf(operand2);

    //Truncate both strings to 3 decimal places
    int dec_place = str1.indexOf('.');

    //Get the str
    String str_op1 = str1.substring(0,dec_place+3);

    dec_place = str2.indexOf('.');
    String str_op2 = str2.substring(0,dec_place+3);

    //Press buttons for operand1
    for (i = 0; i < str_op1.length(); i++)
    {
      //Get the JButton
      label = str_op1.substring(i,i+1);
      jb = calculator.getButton(label);
      jb.doClick(100);
    }

    //Press the operand
    label = operators[op_pos];
    jb = calculator.getButton(label);
    jb.doClick(500);

    //Press buttons for operand2
    for (i = 0; i < str_op2.length(); i++)
    {
      //Get the JButton
      label = str_op2.substring(i,i+1);
      jb = calculator.getButton(label);
      jb.doClick(100);
    }

    //Press the equals
    if (eval)
    {
      jb = calculator.getButton("=");
      jb.doClick(500);

      //Get the JTextField
      JTextField jtf = calculator.getJTextField();

      //Output the jtf
      //System.out.println(jtf.getText());

      //Check the results
      String res = jtf.getText();
      double value3 = Double.parseDouble(res);

      //Compute expected results
      double expected_results = 0;
      double operand1a = Double.parseDouble(str_op1);
      double operand1b = Double.parseDouble(str_op2);

      if (operators[op_pos].equals("+"))
      {
        expected_results = operand1a + operand1b;
      }
      else if (operators[op_pos].equals("-"))
      {
        expected_results = operand1a - operand1b;
      }
      else if (operators[op_pos].equals("/"))
      {
        expected_results = operand1a / operand1b;
      }
      else if (operators[op_pos].equals("*"))
      {
        expected_results = operand1a * operand1b;
      }

      //Display Error if incorrect results
      double diff = Math.abs(expected_results - value3);

      if (diff > 0.75)
      {
        System.out.println("Incorrect Answer for Expression: " + str_op1 +
                           operators[op_pos] + str_op2);
        System.out.println("Your Answer: " + value3);
        System.out.println("Expected Answer: " + expected_results);
        System.out.println("Check basic +/-* operators and all digits");
        System.out.println("-5");
        score -= 5;
        System.out.println("\n\n\n\n");

      }
    }




    try
    {
      Thread.sleep(1000);
    }
    catch(Exception e)
    {

    }


  }

  private String setParen(double a,double b,double c,double dd,double e,String paren)
  {
    //Locate and replace all variable
    int i = 0;
    int pos = 0;
    String[] values = {"a","b","c","d","e"};
    String[] pat = {"[a]","[b]","[c]","[d]","[e]"};
    int loc = -1;
    String[] rep = new String[5];
    boolean next = true;

    //Convert all values to strings
    rep[0] = String.valueOf(a);
    pos = rep[0].indexOf(".");
    rep[0] = rep[0].substring(0,pos+3);

    rep[1] = String.valueOf(b);
    pos = rep[1].indexOf(".");
    rep[1] = rep[1].substring(0,pos+3);

    rep[2] = String.valueOf(c);
    pos = rep[2].indexOf(".");
    rep[2] = rep[2].substring(0,pos+3);

    rep[3] = String.valueOf(dd);
    pos = rep[3].indexOf(".");
    rep[3] = rep[3].substring(0,pos+3);

    rep[4] = String.valueOf(e);
    pos = rep[4].indexOf(".");
    rep[4] = rep[4].substring(0,pos+3);

    String result = new String();


    for (i = 0; i < 5; i++)
    {
      loc = paren.indexOf(values[i]);

      if (loc != -1)
      {
        result = paren.replaceAll(pat[i],rep[i]);
        paren = new String(result);
      }
    }

    result = new String(paren);
    return(result);
  }


  public static void main(String[] args)throws Exception
  {
    Calculator calculator = new Calculator();
    CalculatorTester tl5 = new CalculatorTester();

    //Operator Values
    String[] operators = {"+","-","*","/"};
    int[] digits = {0,1,2,3,4,5,6,7,8,9};
    int[] exponent = {0,1,2,3,4};
    int[] neg_exponent = {-1,-2,-3};
    double operand1;
    double operand2;
    int i = 0;
    int length_of_expression = 0;

    Date d = new Date();

    long value = d.getTime();
    double value1 = 10*Math.random();

    int no_of_times = ((int)((long)value%9 + (int)value1 + 27)%8) + 9;

    //Generate different values
    for (i = 0; i < no_of_times; i++)
    {


      //Generate length of expression
      length_of_expression = (int)(((long)value+(int)value1+i)%4);
      value += i*7;
      value1 += i*9;

      //Generate operand 1
       operand1 = tl5.genOperand(value,value1,exponent,digits, neg_exponent,i);

       //Get the operator
       int op_pos = (int)(value+value1+i)%4;

       //Get negative/ positive
       boolean neg_value = false;
       if ( (value+value1+i)%2 == 0)
       {
         neg_value = true;
       }
       else
       {
         neg_value = false;
       }

      //Get the next operand
      operand2 = tl5.genOperand(value+7+i,value1,exponent,digits, neg_exponent,i);

      //Press the buttons
      tl5.pressButtons(calculator,operand1,operand2,neg_value,op_pos,operators,digits,true);

      //Press the clear button
      JButton jb = calculator.getButton("C");
      jb.doClick(500);
      Thread.sleep(1000);

      }

      //Perform chained values
      for (i = 0; i < 5; i++)
      {
        value = d.getTime();
        value1 = 10*Math.random();

        //Generate length of expression
        length_of_expression = (int)(((long)value+(int)value1+i)%4);
        value += i*7;
        value1 += i*9;

        //Generate operand 1
        operand1 = tl5.genOperand(value,value1,exponent,digits, neg_exponent,i);

        //Get the operator
        int op_pos = (int)(value+value1+i)%4;

        //Get negative/ positive
        boolean neg_value = false;
        if ( (value+value1+i)%2 == 0)
         {
           neg_value = true;
         }
         else
         {
           neg_value = false;
         }

        //Get the next operand
        operand2 = tl5.genOperand(value+7+i,value1,exponent,digits, neg_exponent,i);

        //Press the buttons
        tl5.pressButtons(calculator,operand1,operand2,neg_value,op_pos,operators,digits,false);

        //Get the operator
        int op_pos1 = (int)(value+value1+i+9)%2;
        String label = operators[op_pos1];

        //Press the operand button
        JButton jb = calculator.getButton(label);
        jb.doClick(500);

        //Get the JTextField
        JTextField jtf = calculator.getJTextField();

        //Output the jtf
        //System.out.println(jtf.getText());

        //Check the results
        String res = jtf.getText();
        //System.out.println(res);
        double value3 = Double.parseDouble(res);

        //Compute expected results
        double expected_results = 0;
        double operand1a = operand1;
        double operand1b = operand2;

        if (operators[op_pos].equals("+"))
        {
          expected_results = operand1a + operand1b;
        }
        else if (operators[op_pos].equals("-"))
        {
          expected_results = operand1a - operand1b;
        }
        else if (operators[op_pos].equals("/"))
        {
          expected_results = operand1a / operand1b;
        }
        else if (operators[op_pos].equals("*"))
        {
          expected_results = operand1a * operand1b;
        }

        //Display Error if incorrect results
        double diff = Math.abs(expected_results - value3);

        if ( diff > 0.75)
        {
          System.out.println("Incorrect Answer for Chained Expression: " );
          System.out.println("Your Answer: " + value3);
          System.out.println("Expected Answer: " + expected_results);
          System.out.println("Check basic +/-* operators and all digits");
          System.out.println("-5");
          tl5.score-= 5;
          System.out.println("\n\n\n\n");

        }

        //Save expected results
        double expected_results1 = expected_results;

        //Enter next operand
        tl5.pressButtons(calculator,operand2);

        //Press equal
        //Press the operand button
        jb = calculator.getButton("=");
        jb.doClick(500);

        //Get the JTextField
        jtf = calculator.getJTextField();

        //Output the jtf
        //System.out.println(jtf.getText());

        //Check the results
        res = jtf.getText();
        value3 = Double.parseDouble(res);

        //Compute expected results
        expected_results = 0;
        operand1a = operand1;
        operand1b = operand2;

        if (operators[op_pos1].equals("+"))
        {
          expected_results = expected_results1 + operand1b;
        }
        else if (operators[op_pos1].equals("-"))
        {
          expected_results = expected_results1 - operand1b;
        }
        else if (operators[op_pos1].equals("/"))
        {
          expected_results = expected_results1 / operand1b;
        }
        else if (operators[op_pos1].equals("*"))
        {
          expected_results = expected_results1 * operand1b;
        }


        //Display Error if incorrect results
        diff = Math.abs(expected_results - value3);

        if ( diff > 0.75)
        {
          System.out.println("Incorrect Answer for Chained Expression2: " );
          System.out.println("Your Answer: " + value3);
          System.out.println("Expected Answer: " + expected_results);
          System.out.println("Check basic +/-* operators and all digits");
          System.out.println("-5");
          tl5.score-= 5;
          System.out.println("\n\n\n\n");
        }

        jb = calculator.getButton("C");
        jb.doClick(500);

      }

      //Handle parenthesis
      String[] paren = { "((((a+b)/c)-d)+e)","(((a+b)+c)-d)",
        "((a/b)*c)", "((a+b)/c)", "(((a+b)*c)-d)",
        "((a/c)*d)", "((a-b)*d)" };

      long timea = d.getTime();
      int counter = (int)(timea%7);

      for (i = 0; i < 7; i++)
      {
        //Generate numbers

        double a = tl5.genOperand(value, value1, exponent, digits, neg_exponent, i+1)+1;
        double b = tl5.genOperand(value+(7+i),value1,exponent,digits, neg_exponent,i+3)+1;
        double c = tl5.genOperand(value+(i+9),value1,exponent,digits, neg_exponent,i+5)+1;
        double dd = tl5.genOperand(value+(11+i),value1,exponent,digits, neg_exponent,i+7)+1;
        double e = tl5.genOperand(value+(13+i),value1,exponent,digits, neg_exponent,i+9)+1;

        //Relaplace letters with values inside of parenthesis
        String parena = tl5.setParen(a,b,c,dd,e,paren[counter]);
        tl5.pressButtons(calculator, parena,counter,a,b,
                c,dd,e);

        counter++;
        counter %= 7;
      }

        if (tl5.score ==  120)
        System.out.println("Success");

    }


}
