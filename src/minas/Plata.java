package minas;

import javax.swing.*;
import java.awt.event.*;

public class Plata implements ActionListener{
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private float avanX,avanY,actX,actY,avanW,avanH;
	private int xp,yp;
	boolean band;
	Timer t;
	
	public Plata() {
		x=195;
		y=495;
		alto=0;
		ancho=0;
		t=new Timer(20,this);
	}
	
	public void animacionPlata(Pais p) {
		x=195;
		y=495;
		alto=ancho=0;
		actX=x;
		actY=y;
		avanW=avanH=0;
		xp=p.getX()+12;
		yp=p.getY()+2;
		band=true;
		avanX=(float)(xp-x)/100f;
		avanY=(float)(yp-y)/100f;
		t.start();
		while(t.isRunning() || band);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if((x>=xp-1 && x<=xp+1) && (y>=yp-1 && y<=yp+1))
			t.stop();
		actX+=avanX;
		x=((int)actX);
		actY+=avanY;
		y=((int)actY);
		if(band && ancho<90 && alto<75) {
			avanW+=1.8;
			ancho=(int)avanW;
			avanH+=1.5;
			alto=(int)avanH;
			if(ancho==90 && alto==75) 
				band=false;
			return;
		}
		avanW-=1.8;
		ancho=(int)avanW;
		avanH-=1.5;
		alto=(int)avanH;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

}
