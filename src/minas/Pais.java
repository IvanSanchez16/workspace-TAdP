package minas;

import utilería.*;

public class Pais extends Thread{
	private String nombre,pedido;
	private int x;
	private int y;
	private int npedidos,npedidosa,npedidosr;
	private Semaforo se,sb,sr;
	private boolean vivo;
	private byte estado;
	private Vista ventana;
	private int[] tonAdq;
	static final byte PROCESANDO=0,ESPERANDO=1,ACEPTADO=2,RECHAZADO=3,PIDIENDO=4;
	
	public Pais(int x,int y,Semaforo se,Semaforo sb,Semaforo sr,Vista ventana,String continente,int n) {
		this.x=x;
		this.y=y;
		this.se=se;
		this.sb=sb;
		this.sr=sr;
		this.ventana=ventana;
		vivo=true;
		estado=PROCESANDO;
		tonAdq=new int[3];
		nombre=continente+" "+n;
		npedidos=npedidosa=npedidosr=0;
	}
	
	public void run() {
		boolean ped;
		while(vivo) {
			npedidos++;
			try {
				sleep(Rutinas.nextInt(1000,30000));
			}catch(Exception e) {}
			
			int ton=Rutinas.nextInt(100,500);
			int tipo=Rutinas.nextInt(0,2);
			pedido=ton+"-"+(tipo==0?"R":tipo==1?"B":"E");
			estado=ESPERANDO;
			switch(tipo) {
				case 0:
					sr.Espera();
					estado=PIDIENDO;
					try {
						sleep(1000);
					}catch(Exception e) {}
					ped=ventana.realizarPeticion(this,ton,tipo);
					sr.Libera();
					if(ped)
						npedidosa++;
					else
						npedidosr++;
					break;
				case 1:
					sb.Espera();
					estado=PIDIENDO;
					try {
						sleep(1000);
					}catch(Exception e) {}
					ped=ventana.realizarPeticion(this,ton,tipo);
					sb.Libera();
					if(ped)
						npedidosa++;
					else
						npedidosr++;
					break;
				case 2:
					se.Espera();
					estado=PIDIENDO;
					try {
						sleep(1000);
					}catch(Exception e) {}
					ped=ventana.realizarPeticion(this,ton,tipo);
					se.Libera();
					if(ped)
						npedidosa++;
					else
						npedidosr++;
					break;	
			}
			try {
				sleep(2000);
			}catch(Exception e) {}
			estado=PROCESANDO;
		}
		estado=PROCESANDO;
	}
	public int getNPedidos() {
		return npedidos;
	}
	
	public int getNPedidosA() {
		return npedidosa;
	}
	
	public int getNPedidosR() {
		return npedidosr;
	}
	
	public void acabaHilo() {
		vivo=false;
	}
	
	public byte getEstado() {
		return estado;
	}
	
	public void setEstado(byte edo) {
		estado=edo;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String getPedido() {
		return pedido;
	}
	
	public int getTonAdq(int tipo) {
		return tonAdq[tipo];
	}
	
	public void addTonAdq(int tipo,int ta) {
		tonAdq[tipo]+=ta;
	}
	
	public String toString() {
		return nombre;
	}
}
