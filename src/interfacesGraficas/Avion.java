package interfacesGraficas;

import javax.swing.ImageIcon;
import utilería.*;

public class Avion extends Thread{
	static int TA=0;
	int turno=0;
	int contador=0;
	private String Image;
	String Image1,Image2;
	int x,y,Ancho,Alto; 
	boolean Dir=true;
	boolean disp;
	Semaforo S;
	boolean Band;

	public Avion(int turno,int x,int y,int Ancho,int Alto,Semaforo S) {
		Image1="Avion1.png";
		Image2="Avion2.png";
		Image=Image1;
		this.turno=turno;
		this.x=x;
		this.y=y;
		this.Ancho=Ancho;
		this.Alto=Alto;
		this.disp=true;
		this.S=S;
		this.Band=true;
	}
	public void run(){
		while(Band) {
			S.Espera();
			disp=false;
			lateral();
			Image=Dir?Image1:Image2;
			int aux=y;
			if(turno!=TA) {
				abajo(300);
				contador++;
				arriba(aux);
				disp=true;
				S.Libera();
				try {
					sleep(Rutinas.nextInt(8000,15000));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				continue;
			}
			abajo(700);
			Aterrizar(turno);
			try {
				Band=false;
				TA++;
				sleep(5000);
			}catch(Exception e) {}
			S.Libera();
		}
	}

	public void Aterrizar(int turno) {
		while(x<=1200) {
			x+=5;
			try {
				sleep(10);
			} catch (InterruptedException e) {
			}
		}

	}
	private void arriba(int aux) {
		while(y!=aux) {
			y-=2;
			try {
				sleep(10);
			} catch (InterruptedException e) {}
		}
	}
	public void lateral() {
		while(!(x>180 && x<210)){
			if (Dir) {
				this.x+=2;
				try {
					sleep(5);
				} catch (InterruptedException e) {
				}
			}else {
				this.x-=2;
				try {
					sleep(5);
				} catch (InterruptedException e) {
				}
			}
			if(x<=0) {
				Dir=true;
				Image=Image1;
			}
			if(x>=1200) {
				Dir=false;
				Image=Image2;

			}
		}
	}
	public void abajo(int cant) {
		while(y<=cant) {
			this.y+=2;
			try {
				sleep(10);
			} catch (InterruptedException e) {}
		}
	}
	public void setImage(String image) {
		Image = image;
	}
	public ImageIcon getImage() {
		return Rutinas.AjustarImagen(Image, Ancho, Alto);
	}
}