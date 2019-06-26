package aerolinea;

import javax.swing.*;

import utilería.*;

import java.awt.*;
import java.awt.event.*;

public class Vista extends JFrame
{
	Image backImage,fondo;
	Graphics g;
	Avion[] aviones;
	
	int nAviones;
	Nube nube1,nube2,nube3;
	
	public Vista()
	{
		generarDatos();
		hazInterfaz();		
		for(int i=0; i<aviones.length ; i++)
		{
			aviones[i].start();
		}
		while(aviones[aviones.length-1].isAlive())
		{
			try {Thread.sleep(8);}catch(Exception e) {}
			dibuja();
		}
		System.out.println("Han aterrizado todos los aviones");
	}
	private void generarDatos()
	{
		
		nAviones=Rutinas.nextInt(3,6);
		aviones = new Avion[nAviones];
		for(int i=0;i<nAviones;i++)
		{	
			aviones[i]=new Avion("aerolineaImgs/avion"+(i+1)+"D.png"
								,"aerolineaImgs/avion"+(i+1)+"Iz.png"
								,"aerolineaImgs/avion"+(i+1)+"AT.png"
								,i*50+20,i*50+20,i+1);
			aviones[i].actual=new Elemento();
			if(i==0)
				continue;
			aviones[i].siguiente=aviones[i-1].actual;
		}
		nube1 = new Nube(Rutinas.AjustarImagen("aerolineaImgs/nube1.png", 300, 200).getImage(),100,50);
		nube2 =	new Nube(Rutinas.AjustarImagen("aerolineaImgs/nube2.png", 300, 200).getImage(),800,100);
		nube3 =	new Nube(Rutinas.AjustarImagen("aerolineaImgs/nube3.png", 300, 200).getImage(),500,150);
	}
	private void hazInterfaz()
	{
		Container c=getContentPane();
		setSize(1100,700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setVisible(true);
		c.setBackground(new Color(76,193,255));

		backImage = createImage(getWidth(),getHeight());
		g = backImage.getGraphics();
	}
	public static void main(String[] a)
	{
		new Vista();
	}
	public void dibuja()
	{
		super.paint(g);
		
		g.setColor(new Color(47,43,44));
		g.fillRect(0, 600, getWidth(), 100);
		g.setColor(Color.WHITE);
		for(int i=0; i<9; i++)
		{
			g.fillRect(i*130+50, 640, 100, 10);
		}
		
		for(int i=0; i<aviones.length; i++)
		{
			if(aviones[i].accion==0 && !aviones[i].sentido)
				aviones[i].dibujarAvion(g);
		}
		nube3.dibujarNube(g);
		nube2.dibujarNube(g);
		for(int i=0; i<aviones.length; i++)
		{
			if(aviones[i].sentido || aviones[i].accion!=0)
				aviones[i].dibujarAvion(g);
		}
		
		nube1.dibujarNube(g);
		repaint();
	}
	public void paint(Graphics g)
	{
		g.drawImage(backImage,0,0,getWidth(),getHeight(),null);
	}
}
