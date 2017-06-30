package program7;


import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;


 public class SpaceInvaders extends JFrame
{	Timer gametimer;
	 Timer firetimer;
	 double gametime = 0.0;
	 int score = 0;
	 int shots = 0;
	 int cannonx = 15;
	 int cannony = 435;
	 private Image cannon = Toolkit.getDefaultToolkit().getImage("program7/cannon1.png");
	 private Image background = Toolkit.getDefaultToolkit().getImage("program7/bg2.png");
	 private Image target = Toolkit.getDefaultToolkit().getImage("program7/target.png");
	 private Image cannonball = Toolkit.getDefaultToolkit().getImage("program7/cannonBall.png");
	 int[] targetx = new int[15];
	 int[] targety = new int[15];
	
	 boolean gameended = false;
	 Point omousePt;
	 int cannonballx = 30; 
	 int cannonbally = 420;
	 boolean firing = false;
	 boolean gamestarted = false;
	 boolean hittarget = false;
	 String gametimedisplay = "Time: 0.0";
	 String gamescoredisplay = "Score: 0";
	 String gameshotdisplay = "Shots: 0 ";
    SpaceInvaders()
    { 
        super("SpaceInvaders");
       
        setupTargets();
        DrawPanel drawPanel = new DrawPanel();
        drawPanel.setPreferredSize(new Dimension(640,480));
     
        
       
        
        JPanel buffer = new JPanel(new BorderLayout());
       
       buffer.add(drawPanel, BorderLayout.CENTER);
       buffer.setPreferredSize(new Dimension(640,480));
      
        this.add(buffer);
      
        this.setResizable(true);
       
        this.setLocationByPlatform(true);
        this.setVisible(true);
     this.addKeyListener(new keyb());
        this.pack();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                omousePt = e.getPoint();
                repaint();
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                cannonx = e.getX() ;
                 cannonballx = cannonx + 15;
               
                repaint();
            }
        });
        
    }
    
    class keyb implements KeyListener{

        public void keyPressed (KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_UP && !firing && gamestarted){
          System.out.println("up pressed");
          firing = true;
          firetimer = new Timer(10, new FireCannonListener());

          firetimer.start();
        
        }
        
        else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            if(!gamestarted){
        	System.out.println("game starting");
        	gamestarted = true;
        	gametimer = new Timer(100, new GameTimerListener());

            gametimer.start();
            }
            if(gameended)
            {
            	resetitAll();
            	
            	gameended = false;
            	gametimer = new Timer(100, new GameTimerListener());

                gametimer.start();
            }
        	
        }else if(e.getKeyCode()==KeyEvent.VK_RIGHT && gamestarted){            
           
        	cannonx+=15;
        	if(!firing)
        	cannonballx +=15;
        	
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT & gamestarted){
            cannonx-=15;
            if(!firing)
            cannonballx -= 15;
        }

        repaint();

        }
        public void keyReleased (KeyEvent e){}
        public void keyTyped (KeyEvent e){}
        }
   
   

    class GameTimerListener implements ActionListener {
    	  public void actionPerformed(ActionEvent a) {
    		  int i = 0;
    		  gametime += 0.1;
    		  
    		  
    		

    		  Double fixgametime = BigDecimal.valueOf(gametime)
    		      .setScale(6, RoundingMode.HALF_UP)
    		      .doubleValue();
    		  
    		  
    		  String appendtime = Double.toString(fixgametime);
    		  
    		  gametimedisplay = "Time :" + appendtime ;
    		  for(i = 0 ; i < 10 ; i++)
    	         {
    	      	   targety[i] += 1;
    	      	   repaint();
    	      	   
    	      	   
    	      		if(targety[i] > 460){
    	        	  	
    	        	  	gametimer.stop();
    	        	 
    	        	  	gameended = true;
    	         }
    			
    			
    			
    			
    	  
    	  	}
    	  	}
    	  }

    	    

class FireCannonListener implements ActionListener {
  public void actionPerformed(ActionEvent a) {


		cannonbally -= 5;
		repaint();
		
		int i = 0;
	  	for(i = 0; i < 10; i++)
	  	{
	  		if(cannonballx >= targetx[i] && cannonballx < (targetx[i] + 30) && cannonbally >= targety[i] && cannonbally < targety[i] + 15)
	  		{
	  			if(hittarget == false)
	  			{targetx[i] = 1000;
	  			hittarget = true;
	  			score += 1;
	  			gamescoredisplay = "Score: " + score;
	  			}
	  		}
	  	}
	 
  	if(cannonbally < 0){
  	cannonballx = cannonx + 15;
  	cannonbally = 420;
  	firetimer.stop();
  	firing = false;
  	hittarget = false;
  	
	shots += 1;
	gameshotdisplay = "Shots: " + shots;
	repaint();
  	}
  if(score == 10)
  {
		gametimer.stop();
   	 
	  	gameended = true;
  }
  	}
  }

    
 
    class DrawPanel extends JPanel
    {
        
       
		private static final long serialVersionUID = 1L;
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
           
         
           g.drawImage(background, 0, 0, this);
           g.drawImage(cannon, cannonx, cannony, this); 
           g.drawImage(cannonball,  cannonballx,  cannonbally,  this);
          int i = 0;
          
           for(i = 0 ; i < 10 ; i++)
           {
        	   g.drawImage(target , targetx[i], targety[i], this);
        	   
           }
         
           
          g.drawString(gametimedisplay, 200, 30);
          g.drawString(gamescoredisplay, 300, 30);
          g.drawString(gameshotdisplay, 400, 30);
          if(!gamestarted)
          {
        	  String beginmessage = "Press Down Arrow To Start!";
        	  g.drawString(beginmessage, 250, 400);
          }
          
          if(gameended)
          {
        	  String endmessage = "Press Down Arrow to play again!";
        	  g.drawString(endmessage, 250, 400);
          }
            
          
            
            
        }
    }
    public void resetitAll()
    {	  gametime = 0.0;
	 score = 0;
	 shots = 0;
	  cannonx = 15;
	  cannony = 435;
	   cannonballx = 30; 
		  cannonbally = 420;
		  firing = false;
		  
		  hittarget = false;
		 gametimedisplay = "Time: 0.0";
		 gamescoredisplay = "Score: 0";
		 gameshotdisplay = "Shots: 0 ";
    	setupTargets();
    	repaint();
    	
    	
    }
    public void setupTargets()
    {	int i = 0;
    	int randomNumx = 0 ;
    	int randomNumy = 0;
        int xminimum = 40 ; 
        int xmaximum = 600; 
        int yminimum = 50;
        int ymaximum = 150;
    	
    	 for(i = 0 ; i < 10 ; i++)
         {
      	   Random randx = new Random();
      	   targetx[i] = xminimum + randx.nextInt((xmaximum - xminimum) + 1);
      	   
      	   Random randy = new Random();
      	   targety[i] = yminimum + randy.nextInt((ymaximum - yminimum) + 1);
      	   
      	   
      	   
         }
         
    	
    }
    
//    }
//
//    public void moveIt()
//    {
//        while (true)
//        {
//        	
//        	if (oneX + circlewidth >= 400)
//            {
//                right = false;
//                left = true;
//            }
//            if (oneX <= 7)
//            {
//                right = true;
//                left = false;
//            }
//            if (oneY + circleheight >= 400)
//            {
//                up = true;
//                down = false;
//            }
//            if (oneY <= 7)
//            {
//                up = false;
//                down = true;
//            }
//            if (up) oneY = oneY - yspeed;
//            if (down) oneY = oneY + yspeed;
//            if (left) oneX = oneX - xspeed;
//            if (right) oneX = oneX + xspeed;
//            try
//            {
//                Thread.sleep(10);
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//            this.repaint();
//        }
//        }
//    
//    public void actionPerformed(ActionEvent e){
//    	
//    	
//
//  	  if(e.getSource() == this.startbutton )
//  	  {   System.out.println("this was called");
//  		 xspeed = this.xspeedsb.getValue() ;
//  		 yspeed = this.yspeedsb.getValue();
//  		
//  		  
//  	  }
//  	  else if(e.getSource() == this.stopbutton)
//  	  {
//  		 xspeed = 0;
//  		 yspeed = 0;
//  	  }
//  }
    public static void main (String[] args)
    {
        SpaceInvaders go = new SpaceInvaders();
    }

  
}
