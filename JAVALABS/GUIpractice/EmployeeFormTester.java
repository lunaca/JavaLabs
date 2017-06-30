package program4;
import javax.swing.*;
import java.awt.*;

public class EmployeeFormTester
{
  private int[] no_of_columns = new int[]{15,30,30,13,30,30};

  private String[] fields = {"ID","Last Name","First Name","Phone",
                             "Department Name", "Job Title"};

  private int label_count = 0;
  private int tf_count = 0;
  private int field_count = 0;
  private int menu_count = 0;
  private int menu_item_count = 0;
  private int score = 30;


public void processComponents(Component jc) throws InterruptedException
{
  int i = 0;
  Component[] all_components;

  //Check for number of components
  if (((Container)jc).getComponentCount() > 0)
  {
      //Get the corresponding component
      all_components = ((Container)jc).getComponents();

      //Process next group
      for (i = 0; i < all_components.length; i++)
      {

        processComponents(all_components[i]);
      }
  }
  else
  {
    //Process current component
    //Check for type



    if (jc instanceof JButton)
    {
      //check for submit/get
      JButton jb1 = (JButton)jc;

      //Get the text
      String str = jb1.getText();
      Thread.sleep(1000);
      jb1.doClick(1000);

      if ( (!str.equals("Submit")) && (!str.equals("Get")) )
      {
        System.out.println("\n\nError in JButton Text " + i);
        System.out.println("Expected Answer: " +  "Submit/Get");
        System.out.println("Your Answer: " + str);
        System.out.println("-2");
        score -= 2;
      }

      return;
    }

    if ( jc instanceof JLabel)
    {
      if (label_count == 6)
        return;

      //Get the text
      JLabel jl = (JLabel)jc;
      String text = jl.getText();

      //Check for label
      if ( !fields[label_count].equals(text) )
      {
        System.out.println("\n\nError in JLabel Text Field # " + i);
        System.out.println("Expected Answer: " +  fields[label_count]);
        System.out.println("Your Answer: " + text);
        System.out.println("-2");
        score -= 2;
      }
      label_count++;
    }
    else if (jc instanceof JTextField)
    {
      //Get the textfield
      JTextField jtf = (JTextField)jc;
      String insert = "This is a field " + tf_count;
      jtf.setText(insert);

      //Get the text just inserted
      String str = jtf.getText();

      //Compare text
      if (!str.equals(insert) )
      {
        System.out.println("\n\nError in JTextField Text Field " );
        System.out.println("Expected Answer: " +  insert);
        System.out.println("Your Answer: " + str);
        System.out.println("-2");
        score -= 2;
      }
      field_count++;

    }
    if (jc instanceof JTextField)
      tf_count++;
  }


}

public void processMenuBar(JFrame jf) throws InterruptedException
{
  int i = 0;
  int j = 0;
  JMenuBar jb = jf.getJMenuBar();
  String[] ans = {"File","Edit","Search"};
  String[] ans1 = {"New","Save","Exit"};

  //Get the menus
  for (i = 0; i < jb.getMenuCount(); i++)
  {
    JMenu jm = jb.getMenu(i);

    //Check the string
    String str = jm.getText();
    this.menu_count++;
    jm.doClick(1000);

    //Compare the string
    if (!str.equals(ans[i]))
    {
        System.out.println("\n\nError in JMenu #" + i);
        System.out.println("Expected Answer: " +  ans[i]);
        System.out.println("Your Answer: " + str);
        System.out.println("-2");
        score -= 2;
    }

    //Get each menu item
    for (j = 0; j < jm.getItemCount(); j++)
    {
       JMenuItem ji = jm.getItem(j);
       this.menu_item_count++;
       Thread.sleep(1000);
       ji.doClick(1000);

       str = ji.getText();

       if (!str.equals(ans1[j]))
       {
          System.out.println("\n\nError in JMenuItem #" + j);
          System.out.println("Expected Answer: " +  ans1[j]);
          System.out.println("Your Answer: " + str);
          System.out.println("-2");
          score -= 2;
      }
    }
  }

  //Check all results
  if (this.label_count != 6)
  {
    System.out.println("\n\nIncorrect # of Labels" );
    System.out.println("Expected Answer: " + 6);
    System.out.println("Your Answer: " + this.label_count);
    System.out.println("-2");
    score -= 2;
  }

  if (this.field_count != 6)
  {
    System.out.println("\n\nIncorrect # of TextFields" );
    System.out.println("Expected Answer: " + 6);
    System.out.println("Your Answer: " + this.field_count);
    System.out.println("-2");
    score -= 2;
  }

  if (this.menu_count != 3)
  {
    System.out.println("\n\nIncorrect # of Menus" );
    System.out.println("Expected Answer: " + 3);
    System.out.println("Your Answer: " + this.menu_count);
    System.out.println("-2");
    score -= 2;
  }

  if (this.menu_item_count != 3)
  {
    System.out.println("\n\nIncorrect # of MenusItems" );
    System.out.println("Expected Answer: " + 3);
    System.out.println("Your Answer: " + this.menu_item_count);
    System.out.println("-2");
    score -= 2;
  }

}






public static void main(String[] args) throws Exception
{
  //Get each component
  EmployeeFormTester tl5b = new EmployeeFormTester();

  String[] fields1 = tl5b.fields;
  int[] no_of_columns1 = tl5b.no_of_columns;
  EmployeeForm emp = new EmployeeForm("Employee Form",fields1,
                                       no_of_columns1);

  //Get all the components
  tl5b.processComponents(emp);
  tl5b.processMenuBar(emp);

  //Check the counts
  if (tl5b.tf_count != 6)
  {
    System.out.println("\n\nIncorrect # of  Text Fields " );
    System.out.println("Expected # of Text Fields: 6" );
    System.out.println("Your # of Text Field: " + tl5b.tf_count);
    System.out.println("-2");
    tl5b.score -= 2;
  }

  if (tl5b.label_count != 6)
  {
    System.out.println("\n\nIncorrect # of  Text Fields " );
    System.out.println("Expected # of Text Fields: 6" );
    System.out.println("Your # of Text Field: " + tl5b.tf_count);
    System.out.println("-2");
    tl5b.score -= 2;

  }


  //Display answer
  if (tl5b.score == 30)
  	System.out.println("Success" );
  else
  	System.out.println("Failure - Please Correct as Previous Messages Instruct");


}




}
