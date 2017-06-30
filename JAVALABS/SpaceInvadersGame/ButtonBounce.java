package program7;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.event.*;
import javax.swing.*;


 public class ButtonBounce extends JFrame implements ActionListener
{

    JFrame frame;
    DrawPanel drawPanel;
    private int circlewidth = 10;
    private int circleheight = 10;
    private int oneX = 52;
    private int oneY = 26;
    private int xspeed = 0;
    private int yspeed = 0;
    
    JButton startbutton;
    JButton stopbutton;
    JScrollBar xspeedsb;
    JScrollBar yspeedsb;
    boolean go = false;
    boolean up = false;
    boolean down = true;
    boolean left = false;
    boolean right = true;
    JPanel buffer;
    
    
    ButtonBounce()
    { 
        super("ButtonBounce");
        JPanel right = new JPanel(new GridLayout(8,1,3,3));
        System.out.println(this.go);
        xspeedsb = new JScrollBar(JScrollBar.HORIZONTAL);
        right.add(xspeedsb);
       
        JLabel xspeedvalue = new JLabel(" XSPEED:" + String.valueOf(xspeed));
        right.add(xspeedvalue);
        xspeedsb.addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
              xspeed = xspeedsb.getValue();
              xspeedvalue.setText(" XSPEED:" + String.valueOf(xspeed));
               }
         });
        yspeedsb = new JScrollBar(JScrollBar.HORIZONTAL);
        right.add(yspeedsb);
        
        JLabel yspeedvalue = new JLabel(" YSPEED:" + String.valueOf(yspeed));
        right.add(yspeedvalue);
        yspeedsb.addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
              yspeed = yspeedsb.getValue();
              yspeedvalue.setText(" YSPEED:" + String.valueOf(yspeed));
               }
         });
        
        JScrollBar heightsb = new JScrollBar(JScrollBar.HORIZONTAL);
        right.add(heightsb);
        
        JLabel circleheightvalue = new JLabel(" CIRCLE HEIGHT:" + String.valueOf(circleheight));
        right.add(circleheightvalue);
        
        heightsb.addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
              circleheight = heightsb.getValue();
              circleheightvalue.setText(" CIRCLE HEIGHT:" + String.valueOf(circleheight));
               }
         });
        
        JScrollBar widthsb = new JScrollBar(JScrollBar.HORIZONTAL);
        right.add(widthsb);
        
        JLabel circlewidthvalue = new JLabel(" CIRCLE WIDTH:" + String.valueOf(circlewidth));
        right.add(circlewidthvalue);
        
        widthsb.addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
              circlewidth = widthsb.getValue();
              circlewidthvalue.setText(" CIRCLE WIDTH:" + String.valueOf(circlewidth));
               }
         });
        
        JPanel south = new JPanel();
        this.startbutton = new JButton("Start");
        this.stopbutton = new JButton("Stop");
        
        
        south.add(startbutton);
        south.add(stopbutton);
        
        drawPanel = new DrawPanel();
       JPanel buffer = new JPanel(new BorderLayout());
       
       buffer.add(drawPanel, BorderLayout.CENTER);
       buffer.setPreferredSize(new Dimension(400,400));
      
        this.add(buffer, BorderLayout.CENTER);
        this.add(right, BorderLayout.EAST);
        this.add(south , BorderLayout.SOUTH);
        //frame.getContentPane().add(BorderLayout.EAST, right);
        this.setResizable(true);
       
        this.setLocationByPlatform(true);
        this.setVisible(true);
       this.pack();
        this.stopbutton.addActionListener(this);
        this.startbutton.addActionListener(this);
        moveIt();
        
        
    }
    
    class DrawPanel extends JPanel
    {
        private static final long serialVersionUID = 1L;

        public void paintComponent(Graphics g)
        {
           
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 400, 400);
            
            Graphics2D g2 = (Graphics2D) g;
            
            g2.setColor(Color.BLACK);
            
            		
            			
           Ellipse2D circle = new Ellipse2D.Double(oneX, oneY,
                    circlewidth,
                    circleheight);
            
            g2.draw(circle);
            g2.fill(circle);
            
            
        }
    }

    public void moveIt()
    {
        while (true)
        {
        	
        	if (oneX + circlewidth >= 400)
            {
                right = false;
                left = true;
            }
            if (oneX <= 7)
            {
                right = true;
                left = false;
            }
            if (oneY + circleheight >= 400)
            {
                up = true;
                down = false;
            }
            if (oneY <= 7)
            {
                up = false;
                down = true;
            }
            if (up) oneY = oneY - yspeed;
            if (down) oneY = oneY + yspeed;
            if (left) oneX = oneX - xspeed;
            if (right) oneX = oneX + xspeed;
            try
            {
                Thread.sleep(10);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            this.repaint();
        }
        }
    
    public void actionPerformed(ActionEvent e){
    	
    	

  	  if(e.getSource() == this.startbutton )
  	  {   System.out.println("this was called");
  		 xspeed = this.xspeedsb.getValue() ;
  		 yspeed = this.yspeedsb.getValue();
  		
  		  
  	  }
  	  else if(e.getSource() == this.stopbutton)
  	  {
  		 xspeed = 0;
  		 yspeed = 0;
  	  }
  }
    public static void main(String[] args)
    {
        ButtonBounce yes = new ButtonBounce();
    }

  
}