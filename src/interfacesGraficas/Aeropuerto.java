package interfacesGraficas;

import javax.swing.*;
import utilería.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Aeropuerto extends JFrame implements ActionListener{
	private Image backbuffer = null;
	private Graphics g;
	Timer T1=new Timer(20,null);
	Avion A[];
	Semaforo Pista=new Semaforo(1);
	JDialog d=new JDialog();
	int turno=0;
	private boolean band=false;

	public Aeropuerto() {
		super("Aeropuerto");
		HazInterfaz();
		CreaAviones(4);
		Dibuja();
		HazEscuchas();
		T1.start();
		while(Vivos(A));
		JOptionPane.showMessageDialog(this, "Aterrizaje Completado");
		Mostrar();
	}

	private void HazInterfaz() {
		setLayout(null);
		setSize(1200,800);
		setVisible(true);
		d.setLayout(new GridLayout(0,1,15,15));
		d.setSize(300, 200);
		backbuffer = createImage(getWidth(), getHeight());
		g =backbuffer.getGraphics();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void HazEscuchas() {
		T1.addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {
		MoverLateral(A);
	}
	public void Mostrar() {
		for (int j = 0; j < A.length; j++) {
			d.add(new JLabel("Avion:  "+j+" intento descender: "+A[j].contador));
		}
		d.setVisible(true);
	}
	public void CreaAviones(int cant) {
		A=new Avion[cant];
		boolean bv[]=new boolean [A.length];
		for (int i = 0; i < A.length; i++) {
			bv[i]=true;
			A[i]=new Avion(i,Rutinas.nextInt(0, 750),40+(i*30),50,30,Pista);
		}
		int aux;
		for (int i=0; i < A.length;) {
			aux=Rutinas.nextInt(A.length);
			if(bv[aux]) {
				A[aux].start();
				bv[aux]=false;
				i++;
			}
		}
	}

	public void paint(Graphics g) 	{
		g.drawImage(backbuffer, 0, 0, getWidth(), getHeight(), this);
	}

	public void Dibuja() {
		super.paint(g);
		g.drawImage(Rutinas.AjustarImagen("nube.jpg",this.getWidth() ,600).getImage(), 0, 0,null);
		g.drawImage(Rutinas.AjustarImagen("Atrrizaje.jpg",this.getWidth(),200).getImage(),0, 600,null);

		for (int i = 0; i < A.length; i++) {
			if((!A[i].disp && !A[i].Band)) {
				continue;
			}
			g.drawImage(A[i].getImage().getImage(),A[i].x,A[i].y,A[i].Ancho, A[i].Alto,null);
		}
		repaint();
	}

	public void MoverLateral(Avion Av[]) {
		for (int i = 0; i < Av.length; i++) {
			if(!Av[i].disp) {
				continue;	
			}
			if(!Av[i].Dir) {
				Av[i].x-=5;
			}else {
				Av[i].x+=5;
			}
			if(Av[i].x>=(this.getWidth()-Av[i].Ancho)||Av[i].x<=0) {
				Av[i].Dir=!Av[i].Dir;
				if(Av[i].Dir) {
					Av[i].setImage(Av[i].Image1);
				}else {
					Av[i].setImage(Av[i].Image2);
				}
			}
		}
		Dibuja();
		this.update(this.getGraphics());
	}
	public boolean Termina() {
		for (int i = 0; i < A.length; i++) {
			if(A[i].Band) {
				return true;
			}
		}

		return true;
	}
	public  boolean Vivos(Thread [] H){
		for(int i=0 ; i<H.length ; i++){
			if(H[i].isAlive())
				return true;
		}
		return false;
	}

	public static void main(String []a) {
		new Aeropuerto();
	}
}