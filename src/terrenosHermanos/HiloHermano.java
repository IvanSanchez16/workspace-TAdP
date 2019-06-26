package terrenosHermanos;

import utilería.*;
import java.awt.*;

public class HiloHermano extends Thread{
	private Elemento[] AS;
	private Vista ventana;
	private int x,y;
	private Color color;

	public HiloHermano(Elemento[] as,Vista v,int x,int y,Color c) {
		AS=as;
		ventana=v;
		this.x=x;
		this.y=y;
		color=c;
	}
	
	public void run() {
		int nhectarea;
		while(hayTerrenos()) {	
			nhectarea=Rutinas.nextInt(1,100);
			moverCirculo(nhectarea);
			
			AS[nhectarea-1].S.Espera();
			try {
				sleep(1000);
			}catch(Exception e) {}
			if(AS[nhectarea-1].Band) {	
				AS[nhectarea-1].S.Libera();
				continue;
			}
			AS[nhectarea-1].Band=true;
			AS[nhectarea-1].S.Libera();
			
			ventana.transformarHec(color,nhectarea); 
		}
	}
	
	private boolean hayTerrenos() {
		for(int i=0 ; i<AS.length ; i++) {
			if(!AS[i].Band)
				return true;
		}
		return false;
	}
	
	private void moverCirculo(int nhectarea) {	
		int xdes,ydes;	
		String nh=Rutinas.PonCeros(nhectarea-1,2);
		xdes=(Integer.parseInt(nh.charAt(1)+"")*74)+30;
		ydes=(Integer.parseInt(nh.charAt(0)+"")*62)+70;
		while(xdes!=x || ydes!=y) { 
			if(xdes<x)
				x-=2;
			if(xdes>x)
				x+=2;
			if(ydes<y)
				y-=2;
			if(ydes>y)
				y+=2;
			try {
				sleep(10);
			}catch(Exception e) {}
		}
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
