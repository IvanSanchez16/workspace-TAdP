package examenes;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import utilería.Rutinas;

public class Controlador implements ActionListener{

	Ventana vista;
	int cantAgua;
	boolean bandInOut,bandLimpieza,bandLitGal;
	int Cont;
	
	public Controlador(Ventana v) {
		vista=v;
		Cont=0;
		bandLimpieza=false;
	}
	
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()==vista.BtnIniciar) {
			vista.T.start();
			return;
		}
		if(evt.getSource()==vista.T) {
			//El número de litros que se añadiran o extraeran es entre 10 y 20
			cantAgua=Rutinas.nextInt(3,5);
			
			//Convertir y decidir si serán Litros o galones
			bandLitGal=Rutinas.nextInt(1,2)==1?true:false;
			//Si dio true, usaremos galones por lo cual haremos la conversión, si no, no es necesario hacer nada
			if(bandLitGal)
				cantAgua=cantAgua*4;
			
			//Generador de booleano para decidir si extraer o agregar
			if(vista.GalonesActuales>32) //Si tiene menos del 10% de su capacidad no permite extraer liquido
				bandInOut=Rutinas.nextInt(1,2)==1?true:false;
			else
				bandInOut=true;
			if(bandInOut)
				vista.agregaAgua(cantAgua);
			else
				vista.extraeAgua(cantAgua);
			
			//Control de limpieza de tinaco:
			if(vista.GalonesActuales<32) {
				Cont++;
				if(Cont>=5 && bandLimpieza) {
					vista.actualizaMensaje("Limpieza de tinaco");
					vista.extraeAgua(vista.GalonesActuales);
					Cont=0;
					bandLimpieza=false;
					vista.update(vista.getGraphics());
					try {
						Thread.sleep(2000);
					}catch(Exception e) {}
				}
			}
			//Comprobar si ya ha llegado al 60% de su capacidad
			if(vista.GalonesActuales>=216)
				bandLimpieza=true;
			
			vista.validaLimites();
		}
	}

}
