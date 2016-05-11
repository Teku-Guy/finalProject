package Scrolly;

import java.awt.*;

public class Squob 
{
	public double x,y,w,h;
	private Color c;
	public Squob(double xs, double ys, double ws, double hs)
	{
		x = xs;
		y = ys;
		w = ws;
		h = hs;
		c = Color.BLACK;
	}
	
	public void draw(Graphics g)
	{
		g.fillRect((int)x,(int)y,(int)w,(int)h);
	}
	public void draw(Graphics g,double x0, double y0)
	{
		g.fillRect((int)(x-x0),(int)(y-y0),(int)w,(int)h);
	}	
	
	public void copy(Squob s)
	{
		x = s.x;
		y = s.y;
		w = s.w;
		h = s.h;
	}
	public boolean isIn(double cx, double cy)
	{
		//top
		if((cx>x)&&(cx<x+w)&&(cy>y)&&(cy<y+h))return true;
		else return false;
	}
	public boolean isIn(Squob s)
	{
		if((s.x+s.w>x)&&(s.x<x+w)&&(s.y+s.h>y)&&(s.y<y+h)) return true;
		else return false;

	}
	public boolean isIn(Squob s,double dx, double dy)
	{
		if((s.x+dx+s.w>x)&&(s.x+dx<x+w)&&(s.y+dy+s.h>y)&&(s.y+dy<y+h)) return true;
		else return false;

	}
	
}