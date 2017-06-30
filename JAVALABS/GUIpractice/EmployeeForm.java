package program4;


import javax.swing.*;
import java.awt.*;



public class EmployeeForm extends JFrame {
	
	
	
	public EmployeeForm(String title, String[] labels, int[] no_of_columns)
	{
		super(title);
		int i = 0;
		
		
		
	
		//And set closing operation on this class
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				
				JPanel border = new JPanel(new BorderLayout());
				
				 JPanel formlabel = new JPanel(new GridLayout(labels.length, 1));
				    for (i = 0; i<labels.length; i++)
				    {
				    	JLabel label = new JLabel(labels[i]);
				    	
				    	
				    	JPanel textborder = new JPanel(new FlowLayout(FlowLayout.CENTER));
				    	textborder.add(label);
				    	formlabel.add(textborder);
				    	
				    }
				    JPanel formfield = new JPanel(new GridLayout(labels.length, 1));
				    for (i = 0; i<labels.length; i++)
				    {
				    	JTextField jtf = new JTextField (no_of_columns[i]);
				    	JPanel textborder = new JPanel(new FlowLayout(FlowLayout.LEFT));
				    	
				    	textborder.add(jtf);
				    	
				    	formfield.add(textborder);
				    }
				    
				    
				    JPanel form = new JPanel(new BorderLayout());
				    form.add(formfield , BorderLayout.CENTER);
				    form.add(formlabel, BorderLayout.WEST);
				    
				    JPanel buffer = new JPanel(new FlowLayout(FlowLayout.CENTER));
				    buffer.add(form);
				    
				    
				    border.add(buffer, BorderLayout.NORTH);
				    
				  //Create JMenuBar
				    

				    JButton submit = new JButton("Submit");
				    JButton get = new JButton("Get");
				    
				    JPanel buttons = new JPanel();
				    buttons.add(submit);
				    buttons.add(get);
				    border.add(buttons, BorderLayout.CENTER);
				    
				    this.add(border);
				   
				    //Add a MenuBar
				    JMenuBar jmb = new JMenuBar();

				    //Add menus
				    JMenu jm1 = new JMenu("File");
				    JMenu jm2 = new JMenu("Edit");
				    JMenu jm3 = new JMenu("Search");

				    //Create Sub items
				    JMenuItem ji1 = new JMenuItem("New");
				    JMenuItem ji2 = new JMenuItem("Save");
				    JMenuItem ji3 = new JMenuItem("Exit");

				    //Add submenus
				    jm1.add(ji1);
				    jm1.add(ji2);
				    jm1.add(ji3);

				    jmb.add(jm1);
				    jmb.add(jm2);
				    jmb.add(jm3);
				    this.setJMenuBar(jmb);


				    
				    
				    this.setVisible(true);
				    this.pack();
	}

	public static void main(String[] args) {
		int[] no_of_columns = new int[]{7,20,20,10,20,20};
		String[] labels = {"ID","Last Name","First Name","Phone",
                "Department Name", "Job Title"};
		
		EmployeeForm employeeform = new EmployeeForm("Employee Form",
                labels,
                no_of_columns);


	}

}
