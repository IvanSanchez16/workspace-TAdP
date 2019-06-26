package aerolinea;

import java.awt.*;
import javax.swing.*;
import utilería.*;

public class Avion extends Thread
{
	Image img,img2,img3,img4;
	Elemento actual, siguiente;
	
	double x,y , xI,yI;
	int nAvion,nIntentos,limIzq,limDer;
	boolean sentido;
	
	byte accion;
	final static byte MOVER=0,INTENTAR_ATERRIZAR=1,ATERRIZAR=2,CANCELAR_ATERRIZAJE=3,SACAR_AVION=4,TERMINAR=5,MOVER2=6;
	final static double DISTANCIA=5, AX=1400,AY=480,PX=100,PY=510;
	public double distX , distY;
	int contador=0;
	
	
	public Avion(String img, String img2,String img3, int x, int y,int nAvion)
	{
		this.nAvion=nAvion;
		this.img=Rutinas.AjustarImagen(img, 300, 200).getImage();
		this.img2=Rutinas.AjustarImagen(img2, 100, 70).getImage();
		this.img3=Rutinas.AjustarImagen(img3, 300, 200).getImage();
		this.img4=Rutinas.AjustarImagen(img2, 300, 200).getImage();
		this.x=xI=x;
		this.y=yI=y;
		nIntentos=0;
		sentido=true;
		accion=0;
		limIzq=-200;
		limDer=1300;
	}
	public void run()
	{
		while(true)
		{
			switch(accion)
			{
				case MOVER:
					moverAvion();
					if(Rutinas.nextInt(300)==1 && sentido && x<700)
					{
						accion=1;
						distX=Math.abs(x-AX);
						distY=Math.abs(y-AY);
					}
					break;
				case INTENTAR_ATERRIZAR:
					moverAterrizaje();
					break;
				case ATERRIZAR:
					moverAPista();	
					break;
				case CANCELAR_ATERRIZAJE:
					cancelarAterrizaje();
					break;
				case SACAR_AVION:
					sacarAvion();
					break;
				case TERMINAR:
					System.out.println("El avión "+nAvion+" aterrizó con éxito y ha abandonado la pista.");
					return;
				case MOVER2:
					moverAvion();
			}
			try{this.sleep(16);}catch(Exception e){}
		}
	}
	public void dibujarAvion(Graphics g)
	{
		if(sentido)
		{
			g.drawImage(img, (int)x, (int)y, null);
			return;
		}
		if(accion==MOVER)
		{
			g.drawImage(img2, (int)x, (int)y, null);
			return;
		}
		if(accion==ATERRIZAR || accion==SACAR_AVION)
		{
			g.drawImage(img3, (int)x, (int)y, null);
			return;
		}
		g.drawImage(img4, (int)x, (int)y, null);


	}
	public void moverIzq(double desplazamiento)
	{
		x-=desplazamiento;
	}
	public void moverDer(double desplazamiento)
	{
		x+=desplazamiento;
	}
	public void subir(double desplazamiento)
	{
		y-=desplazamiento;
	}
	public void bajar(double desplazamiento)
	{
		y+=desplazamiento;
	}
	public void moverAvion()
	{
		
		if(x<limIzq)
		{
			sentido=true;
			accion=MOVER;
			limIzq=-200;
		}
		if(x>limDer)
			sentido=false;
		
		if(sentido)
			moverDer(DISTANCIA);
		else
			moverIzq(DISTANCIA);

	}
	public void moverAterrizaje()
	{

		if(Math.abs(x-AX)<5 && Math.abs(y-AY)<5)
		{			
			if(siguiente == null)
			{
				distX=Math.abs(x-PX);
				distY=Math.abs(y-PY);
				sentido=false;
				accion=ATERRIZAR;
				return;
			}
			siguiente.S.Espera();
				sentido=false;
				if(siguiente.Band)
				{
					distX=Math.abs(x-PX);
					distY=Math.abs(y-PY);
					accion=ATERRIZAR;
				}else{
					distX=Math.abs(x-xI);
					distY=Math.abs(y-yI);
					accion=CANCELAR_ATERRIZAJE;
					System.out.println("El avión "+nAvion+" intentó aterrizar por "+(++nIntentos)+" vez.");
				}
			siguiente.S.Libera();
			return;
		}
		moverDer(distX/180);
		bajar(distY/180);
		sentido=true;
	}	
	public void moverAPista()
	{
		if(Math.abs(x-PX)<5 && Math.abs(y-PY)<5)
		{
			try
			{
				sleep(5000);
			}catch(Exception e){}
			accion=SACAR_AVION;
		}
		moverIzq(distX/250);
		bajar(distY/250);	
	}
	public void cancelarAterrizaje()
	{
		if(Math.abs(x-xI)<5 && Math.abs(y-yI)<5)
		{
			accion=MOVER2;
			limIzq=-500;
		}
		moverIzq(distX/200);
		subir(distY/200);
	}
	public void sacarAvion()
	{
		if(x<-300)
		{
			actual.S.Espera();
				actual.Band=true;
			actual.S.Libera();
			accion=TERMINAR;
		}
		moverIzq(10);
	}
}
