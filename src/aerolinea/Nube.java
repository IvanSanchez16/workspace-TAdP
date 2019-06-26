package aerolinea;

import java.awt.*;

public class Nube 
{
	Image img;
	int x;
	int y;
	
	public Nube(Image img, int x, int y)
	{
		this.img=img;
		this.x=x;
		this.y=y;
	}
	
	public void dibujarNube(Graphics g)
	{
		g.drawImage(img, x, y, null);
	}
	
}
