package minas;

import javax.swing.*;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.*;
import utilería.*;

public class Vista extends JFrame implements ActionListener{
	
	Image backimage,imgPin,fondo,negado,confirmado,plata;
	Graphics g;
	Pais[] apEu,apAs;
	int[] at;
	int[] axE= {45,50,10,90,140,145,145,300,270,200,175,120,190,230,265,180,305,275,285,245,265,75,220,205,240,70,115,115,185,165};
	int[] ayE= {235,180,120,205,225,150,190,115,200,160,240,140,135,145,130,180,145,150,165,165,180,185,175,190,190,240,190,215,205,220};
	int[] axA= {550,545,665,415,570,360,450};
	int[] ayA= {280,140,250,310,390,230,185};
	Plata[] ap;
	int tonVenEu,tonVenAs;
	int tonDisEu,tonDisAs,tonTot;
	int contPed;
	int[] atonVenEu,atonVenAs;
	Timer t;
	Semaforo s;
	
	public Vista() {
		super("Minas");
		hazInterfaz();
		Dibuja();
		JOptionPane.showMessageDialog(this,"Oprima 'Aceptar' para empezar","Inicio",JOptionPane.INFORMATION_MESSAGE);
		
		for(int i=0 ; i<apEu.length ; i++) 
			apEu[i].start();
		for(int i=0 ; i<apAs.length ; i++)
			apAs[i].start();
		 
		t.start();
		while(hayVivos());
		JDialog jd=new JDialog(this,"Resultados",true);
		jd.setLayout(new BorderLayout());
		jd.setSize(800,300);
		jd.setResizable(false);
		jd.setLocationRelativeTo(null);
		String[] columnas= {"País","# Pedidos totales","# Pedidos Exitosos","# Pedidos Rechazados","Plata Excelente imp.","Plata Buena imp.","Plata Regular imp."};
		String[][] mdatos=new String[apEu.length+apAs.length][7];
		for(int i=0 ; i<apEu.length ; i++) {
			mdatos[i][0]=apEu[i].toString();
			mdatos[i][1]=apEu[i].getNPedidos()+"";
			mdatos[i][2]=apEu[i].getNPedidosA()+"";
			mdatos[i][3]=apEu[i].getNPedidosR()+"";
			mdatos[i][4]=apEu[i].getTonAdq(2)+"";
			mdatos[i][5]=apEu[i].getTonAdq(1)+"";
			mdatos[i][6]=apEu[i].getTonAdq(0)+"";
		}
		for(int i=apEu.length ; i<apAs.length+apEu.length ; i++) {
			mdatos[i][0]=apAs[i-apEu.length].toString();
			mdatos[i][1]=apAs[i-apEu.length].getNPedidos()+"";
			mdatos[i][2]=apAs[i-apEu.length].getNPedidosA()+"";
			mdatos[i][3]=apAs[i-apEu.length].getNPedidosR()+"";
			mdatos[i][4]=apAs[i-apEu.length].getTonAdq(2)+"";
			mdatos[i][5]=apAs[i-apEu.length].getTonAdq(1)+"";
			mdatos[i][6]=apAs[i-apEu.length].getTonAdq(0)+"";
		}
		JTable jt=new JTable(mdatos,columnas);
		jt.setEnabled(false);
		jt.setFont(new Font("Dubai",1,15));
		JScrollPane sp=new JScrollPane(jt);
		jd.add(sp);
		jd.setVisible(true);
	}
	
	public boolean hayVivos() {
		for(int i=0 ; i<apEu.length ; i++) {
			if(apEu[i].isAlive())
				return true;
		}
		for(int i=0 ; i<apAs.length ; i++) {
			if(apAs[i].isAlive())
				return true;
		}
		return false;
	}
	
	public void hazInterfaz() {
		setSize(900,700);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
		imgPin=Rutinas.AjustarImagen("imagenes/pin.png",25,30).getImage();
		fondo=Rutinas.AjustarImagen("imagenes/mundo.jpg",getWidth(),getHeight()).getImage();
		confirmado=Rutinas.AjustarImagen("imagenes/paloma bien.png",25,25).getImage();
		negado=Rutinas.AjustarImagen("imagenes/negado.png",25,25).getImage();
		plata=Rutinas.AjustarImagen("imagenes/plata.png",100,75).getImage();
		backimage=createImage(getWidth(),getHeight());
		g=backimage.getGraphics();
		t=new Timer(20,this);
		
		atonVenEu=new int[3];
		atonVenAs=new int[3];
		ap=new Plata[3];
		for(int i=0 ; i<ap.length ; i++)
			ap[i]=new Plata();
		tonVenEu=tonVenAs=contPed=0;
		tonTot=Rutinas.nextInt(10000,20000);
		tonDisEu=tonDisAs=tonTot/2;
		if(tonTot%2!=0)
			tonDisEu++;
		at=new int[3];
		at[0]=(int) ((float)tonTot*0.3f);
		at[1]=(int) ((float)tonTot*0.6f);
		at[2]=(int) ((float)tonTot*0.1f);
		for(int i=2 ; at[0]+at[1]+at[2]!=tonTot ; i--)
			at[i]++;
		System.out.println("Toneladas totales: "+tonTot);
		System.out.println("Toneladas regulares: "+at[0]);
		System.out.println("Toneladas buenas: "+at[1]);
		System.out.println("Toneladas excelentes: "+at[2]+"\n");
		
		Semaforo se=new Semaforo(1);
		Semaforo sb=new Semaforo(1);
		Semaforo sr=new Semaforo(1);
		s=new Semaforo(1);
		apEu=new Pais[Rutinas.nextInt(10,30)];
		apAs=new Pais[Rutinas.nextInt(5,7)];
		for(int i=0 ; i<apEu.length ; i++) 
			apEu[i]=new Pais(axE[i],ayE[i],se,sb,sr,this,"Europa",i+1);
		for(int i=0 ; i<apAs.length ; i++)
			apAs[i]=new Pais(axA[i],ayA[i],se,sb,sr,this,"Asia",i+1);
		
	}
	
	public boolean realizarPeticion(Pais p,int ton,int tipo) {
		s.Espera();
		System.out.println("Número de pedido: "+ ++contPed);
		System.out.println("Toneladas pedidas: "+ton+" toneladas");
		System.out.println("Tipo: "+ (tipo==0?"Regular":tipo==1?"Buena":"Excelente") );
		System.out.println("Pais solicitando: "+p);
		System.out.println("Toneladas vendidas al continente: "+(p.toString().startsWith("Asia")?tonVenAs:tonVenEu));
		System.out.print("Estatus: ");
		if(p.getX()>=360) {	//Asia
			if(tonVenAs >= tonDisAs) {
				System.out.println("Rechazado. Toneladas agotadas para el continente\n");
				s.Libera();
				p.setEstado(Pais.RECHAZADO);
				acabaAsia();
				return false;
			}
			if(at[tipo]<=0) {
				System.out.println("Rechazado. No hay material del tipo solicitado\n");
				s.Libera();
				p.setEstado(Pais.RECHAZADO);
				return false;
			}
			if(ton>at[tipo]) 
				ton=at[tipo];	
			if(tonVenAs+ton > tonDisAs) {
				int dif=(tonTot/2)-tonVenAs;
				if(dif<at[tipo]) 
					ton=dif;
				acabaAsia();
			}
			tonVenAs+=ton;
			p.addTonAdq(tipo,ton);
			atonVenAs[tipo]+=ton;
			at[tipo]-=ton;
			System.out.println("Realizado\n");
			s.Libera();
			p.setEstado(Pais.ACEPTADO);
			ap[tipo].animacionPlata(p);
			return true;
		}	//Europa
		if(tonVenEu >= tonDisEu) {
			System.out.println("Rechazado. Toneladas agotadas para el continente\n");
			s.Libera();
			p.setEstado(Pais.RECHAZADO);
			acabaEuropa();
			return false;
		}
		if(at[tipo]<=0) {
			System.out.println("Rechazado. No hay material del tipo solicitado\n");
			s.Libera();
			p.setEstado(Pais.RECHAZADO);
			return false;
		}
		if(ton>at[tipo]) 
			ton=at[tipo];
		if(tonVenEu+ton > tonDisEu) {
			int dif=tonDisEu-tonVenEu;
			if(dif<at[tipo])
				ton=dif;
			acabaEuropa();
		}
		tonVenEu+=ton;
		p.addTonAdq(tipo,ton);
		atonVenEu[tipo]+=ton;
		at[tipo]-=ton;
		System.out.println("Realizado\n");
		s.Libera();
		p.setEstado(Pais.ACEPTADO);
		ap[tipo].animacionPlata(p);
		return true;
	}
	
	private void acabaAsia() {
		for(int i=0 ; i<apAs.length ; i++) 
			apAs[i].acabaHilo();
	}
	
	private void acabaEuropa() {
		for(int i=0 ; i<apEu.length ; i++) 
			apEu[i].acabaHilo();
	}
	
	public void Dibuja() {
		super.paint(g);
		g.drawImage(fondo,0,0,getWidth(),getHeight(),null);
		for(int i=0 ; i<apEu.length ; i++) 
			DibujaPais(apEu[i]);
		for(int i=0 ; i<apAs.length ; i++) 
			DibujaPais(apAs[i]);
		for(int i=0 ; i<ap.length ; i++)
			g.drawImage(plata,ap[i].getX(),ap[i].getY(),ap[i].getAncho(),ap[i].getAlto(),null);
		repaint();
	}
	
	public void DibujaPais(Pais p) {
		g.setColor(Color.BLACK);
		g.drawOval(p.getX(),p.getY(),25,5);
		switch(p.getEstado()) {
		case Pais.ESPERANDO:
			g.drawImage(imgPin,p.getX(),p.getY()-28,25,30,null);
			return;
		case Pais.ACEPTADO:
			g.drawImage(confirmado,p.getX(),p.getY()-25,25,25,null);
			return;
		case Pais.RECHAZADO:
			g.drawImage(negado,p.getX(),p.getY()-25,25,25,null);
			return;
		case Pais.PIDIENDO:
			g.setColor(Color.white);
			int[] ax={p.getX()+7,p.getX()+17,p.getX()+12},ay={p.getY()-3,p.getY()-3,p.getY()+2};
			g.fillPolygon(ax,ay,3);
			g.fillRect(p.getX()-5,p.getY()-23,34,20);
			g.setColor(Color.BLACK);
			g.drawString(p.getPedido(),p.getX()-5,p.getY()-8);
			return;
		}
	}

	public void paint(Graphics g) {
		g.drawImage(backimage,0,0,getWidth(),getHeight(),null);
	}

	public static void main(String[] args) {
		Vista ventana=new Vista();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Dibuja();
	}
}
