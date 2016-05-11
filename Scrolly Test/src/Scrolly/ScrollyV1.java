package Scrolly;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class ScrollyV1 extends Applet implements KeyListener, Runnable
{

	private static final long serialVersionUID = -1556851756306284087L;
	public Thread t;
	public boolean up,down,left,right,jump,ground;
	public double x,y;
	public double vx,vy;
	public double grav = 0.2;
	public double CX,CY;
	public double gamma = 0.1;

	Graphics bufferGraphics;
	Image offScreen;
	Dimension dim;
	
	Squob s1,s2,s3,w1,w2,w3,w4,w5;
	Sprite sp;
	Obz obz;
	
	public void init()
	{
		dim = getSize();
		offScreen = createImage(dim.width,dim.height);
		bufferGraphics = offScreen.getGraphics();
		
		CX = 200;
		CY = 150;
		x = y = 20;
		vx=vy = 0;
		up = down = false;
		left = right = false;
		jump = false;
		ground = true;
		t = new Thread();
		t.start();
		addKeyListener(this);
		
	//	
		sp = new Sprite(x,y,20,20);
		s1 = new Squob(2,150,100,10);
		s2 = new Squob(150,140,250,10);
		s3 = new Squob(450,110,60,10);
		
		w1 = new Squob(-50,50,10,150);
		w2 = new Squob(550,50,10,150);
		w3 = new Squob(-50,200,610,10);
		w4 = new Squob(-50,175,15,10);
		w5 = new Squob(380,80,30,10);
		
		obz = new Obz();
		obz.add(s1);
		obz.add(s2);
		obz.add(s3);
		obz.add(w1);
		obz.add(w2);
		obz.add(w3);
		obz.add(w4);
		obz.add(w5);
	}
	
	public void paint(Graphics g)
	{
		bufferGraphics.clearRect(0, 0, dim.width, dim.height);

		sp.draw(bufferGraphics,x-CX,y-CY);
		
		for (int i = 0; i<obz.size();i++)
		{
			obz.get(i).draw(bufferGraphics,x-CX,y-CY);
		}
		g.drawImage(offScreen,0,0,this);
	}
	public void update(Graphics g)
	{
		paint(g);
	}

	public void start()
	{
		t = new Thread(this);
		t.start();
	}

// Main loop goes here	
	public void run()
	{	
		while(true)
		{
			if(up) vy-=.2;
			if(down)vy+=.2;
			vx*=(1-gamma);// friction
			if(Math.abs(vx)<.1) vx = 0;
			if(right)vx+=.2;
			if(left)vx-=.2;

			if(jump&&ground)
			{
				vy-=4;
				jump = false;
				ground = false;
			}
			vy+=grav;
			if(willCollide(0,vy))
			{
				vy=0;
				ground = true;
			}
			if(willCollide(vx,0))
			{
				vx=0;
			}
			x+=vx;
			y+=vy;
			
			sp.x = x;
			sp.y = y;
			repaint();
			try
			{
				t.sleep(20);
			}
			catch(InterruptedException e)
			{
			}
		}
	}
	
// Collision detection
	public boolean willCollide(double dx, double dy)
	{
		for (int i = 0; i<obz.size();i++)
		{
			if(obz.get(i).isIn(sp,dx,dy))
			{
				return true;
			}
		}
		return false;
	}

// Keyboard Handlers
	
	public void keyTyped ( KeyEvent e )
	{

	}  
	public void keyPressed ( KeyEvent e)
	{

		if (e.getKeyCode()==e.VK_UP)
		{
			up = true;
		}
		if (e.getKeyCode()==e.VK_DOWN)
		{
			down = true;
		}
		if (e.getKeyCode()==e.VK_RIGHT)
		{
			right = true;
		}
		if (e.getKeyCode()==e.VK_LEFT)
		{
			left = true;
		}
		if(e.getKeyCode()==e.VK_SPACE)
		{
			if(ground) jump = true;
		}
	}  
	public void keyReleased ( KeyEvent e ){  
		if (e.getKeyCode()==e.VK_UP)
		{
			up = false;
		}
		if (e.getKeyCode()==e.VK_DOWN)
		{
			down = false;
		}
		if (e.getKeyCode()==e.VK_RIGHT)
		{
			right = false;
		}
		if (e.getKeyCode()==e.VK_LEFT)
		{
			left = false;
		}
	}
}