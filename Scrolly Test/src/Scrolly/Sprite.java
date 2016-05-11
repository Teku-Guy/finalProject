package Scrolly;

import java.awt.*;

public class Sprite extends Squob 
{
	public Sprite(double xs, double ys, double ws, double hs)
	{
		super(xs,ys,ws,hs);
	}
	public void draw(Graphics g)
	{
		g.drawOval((int)x, (int)y, (int)w, (int)h);
	}
	public void draw(Graphics g,double x0, double y0)
	{
		g.drawOval((int)(x-x0), (int)(y-y0), (int)w, (int)h);
	}

}
