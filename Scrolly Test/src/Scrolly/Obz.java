package Scrolly;

public class Obz {
	private Squob[] list;
	private int size;
	private int maxSize;
	
	public Obz()
	{
		size = 0;
		maxSize = 50;
		list = new Squob[maxSize];
		for(int i = 0; i < maxSize;i++)
		{
			list[i]= new Squob(0,0,0,0);
		}
	}
	
	public int size()
	{
		return size;
	}
	
	public void add(Squob s)
	{
		list[size].copy(s);
		size++;
	}
	
	public Squob get(int i)
	{
		if(i<size)
		{
			return list[i];
		}
		else return null;
	}

}