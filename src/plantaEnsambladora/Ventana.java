package plantaEnsambladora;

import javax.swing.*;
import utilería.*;
import java.awt.*;
import java.util.InputMismatchException;
import java.awt.event.*;

public class Ventana extends JFrame implements ActionListener{
	
	HiloLineaProduccion[] hp;
	Auto[] aA;
	int nEstaciones,anchoEstacion,altoEstacion;
	Image backImage;
	Graphics g;
	Image[] est,carros;
	Timer t;

	
	public Ventana() {
		super("Planta esambladora NISSON");
		while(true) {
			try {
				nEstaciones=Integer.parseInt(JOptionPane.showInputDialog(this,"Proporcione el número de líneas de producción","Inserción de datos",JOptionPane.QUESTION_MESSAGE));
				if(nEstaciones<8 || nEstaciones>15) 
					throw new Exception();
				break;
			}catch(Exception e) {
				JOptionPane.showMessageDialog(this,"Ingrese un número entre el 8 y el 15","Error de captura",JOptionPane.ERROR_MESSAGE);
			}
		}
		hazInterfaz();
	}
	
	private void hazInterfaz() {
		Semaforo se1=new Semaforo(5);
		Semaforo se2=new Semaforo(4);
		Semaforo se22=new Semaforo(2);
		Semaforo se3=new Semaforo(3);
		Semaforo se4=new Semaforo(3);
		Semaforo sh=new Semaforo(1);
		hp=new HiloLineaProduccion[nEstaciones];
		aA=new Auto[nEstaciones];
		HiloLineaProduccion.nautos=0;
		
		setSize(1000,720);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		altoEstacion=(getHeight()-29)-( (nEstaciones-1)*5 );
		altoEstacion=(altoEstacion/nEstaciones);
		anchoEstacion=(getWidth()-100)/8;
		
		for(int i=0 ; i<nEstaciones ; i++) {
			aA[i]=new Auto(110,((i+1)*altoEstacion)+(i*5));
			hp[i]=new HiloLineaProduccion(aA[i],se1,se2,se22,se3,se4,sh);
		}

		est=new Image[8];
		est[0]=Rutinas.AjustarImagen("imagenes/estacion1.jpg",anchoEstacion,altoEstacion).getImage();
		est[1]=Rutinas.AjustarImagen("imagenes/estacion2.jpg",anchoEstacion,altoEstacion).getImage();
		est[2]=Rutinas.AjustarImagen("imagenes/estacion2.jpg",anchoEstacion,altoEstacion).getImage();
		est[3]=Rutinas.AjustarImagen("imagenes/estacion3.jpg",anchoEstacion,altoEstacion).getImage();
		est[4]=Rutinas.AjustarImagen("imagenes/estacion4.jpg",anchoEstacion,altoEstacion).getImage();
		est[5]=Rutinas.AjustarImagen("imagenes/estacion5.jpg",anchoEstacion,altoEstacion).getImage();
		est[6]=Rutinas.AjustarImagen("imagenes/estacion6.jpg",anchoEstacion,altoEstacion).getImage();
		est[7]=Rutinas.AjustarImagen("imagenes/estacion7.jpg",anchoEstacion,altoEstacion).getImage();

		carros=new Image[7];
		for(int i=0 ; i<carros.length ; i++)
			carros[i]=Rutinas.AjustarImagen("imagenes/c"+(i+1)+".png",85,30).getImage();
		
		backImage=createImage(getWidth(),getHeight());
		g=backImage.getGraphics();
		t=new Timer(20,this);
		Dibuja();
		
		JOptionPane.showMessageDialog(this,"Oprima 'Aceptar' para empezar","Inicio",JOptionPane.INFORMATION_MESSAGE);
		for(int i=0 ; i<nEstaciones ; i++) 
			hp[i].start();
		t.start();
	}
	
	public void Dibuja() {
		super.paint(g);
		for(int i=0 ; i<nEstaciones ; i++) {
			g.drawString("Linea #"+(i+1),28,(i*(altoEstacion+5))+40);
			g.drawString("Auto #",35,(i*(altoEstacion+5))+55);
			g.drawString((aA[i].getNumero()+""),50,(i*(altoEstacion+5))+70);
			for(int j=0 ; j<8  ; j++) { //4-28
				g.drawImage(est[j],100+(j*anchoEstacion),(i*(altoEstacion+5))+28,anchoEstacion,altoEstacion,null);
				if( (aA[i].getX()==(j*112)+110) && aA[i].getX()!=894 ) {
					g.setColor(Color.BLACK);
					g.fillRect(100+(j*anchoEstacion)+10,(i*(altoEstacion+5))+30,anchoEstacion-20,8);
					g.setColor(Color.GREEN);
					g.fillRect(100+(j*anchoEstacion)+11,(i*(altoEstacion+5))+31,(int)aA[i].getProgreso(),6);
				}
			}
			g.setColor(Color.BLACK);
			g.fillRect(0,(i*(altoEstacion+5))+23,getWidth(),5);
			g.drawImage(carros[aA[i].getEstado()],aA[i].getX(),aA[i].getY(),85,30,null);
		}
		repaint();
	}
	
	public void paint(Graphics g) {
		g.drawImage(backImage,0,0, null);
	}
	
	public static void main(String []args) {
		Ventana v=new Ventana();
	}

	public void actionPerformed(ActionEvent arg0) {
		Dibuja();
	}

}
