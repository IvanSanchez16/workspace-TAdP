package serpientesyescaleras;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.JOptionPane;

public class Controlador implements ActionListener,KeyListener{
	Modelo modelo;
	VTablero vista;
	int JEnTurno,TotalJugadores;
	
	public Controlador(Modelo m,VTablero v) {
		modelo=m;
		vista=v;
		IniciarJuego();
	}

	public void IniciarJuego() {
		TotalJugadores=modelo.IniciarJuego()-1;
		JEnTurno=0;
		vista.HazInterfaz(TotalJugadores+1,modelo.L,modelo.Jug);
		vista.setEscuchador(this);
		vista.update(vista.getGraphics());
	}
	
	public void SigTurno() {
		JEnTurno++;
		if(JEnTurno>TotalJugadores)
			JEnTurno=0;
	}
	
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()==vista.BtnTurno) 
			Turno();
	}
	
	private void Turno(){
		vista.ActualizarMensaje("Tira los dados                        ",Color.BLACK);
		int d1=modelo.TiroDado();
		int d2=modelo.TiroDado();
		vista.ActualizaDados( d1 , d2 );
		int totalavanzar=d1+d2;
		Casilla[] tem;
		try {
			tem=modelo.Avance( modelo.ObtenerNodo(modelo.Jug[JEnTurno].getPosicion() ) ,totalavanzar,false);
		}catch(NullPointerException e) {
			tem=modelo.Avance( modelo.L.getFrente() ,totalavanzar,true);
		}
		vista.MoverJugador(tem, modelo.Jug[JEnTurno]);
		Casilla cactual=modelo.Jug[JEnTurno].getPosicion();
		if(cactual.getTipoCasilla()=='S') 
			AccionSerpiente(cactual);
		if(cactual.getTipoCasilla()=='E') 
			AccionEscalera(cactual);
		if(modelo.Gano(JEnTurno)){
			vista.MensajeGano(JEnTurno);
			int jdn=JOptionPane.showConfirmDialog(null,"Desea jugar de nuevo?","Juego nuevo", JOptionPane.YES_NO_OPTION);
			if(jdn==JOptionPane.YES_OPTION) {
				IniciarJuego();
				return;
			}
		}
		if(d1==d2) {
			JEnTurno--;
			vista.ActualizarMensaje("Pares!! Vuelves a tirar",new Color(216, 213, 41));
		}
		SigTurno();
		vista.ActualizarLb(JEnTurno);
	}
	
	private void AccionSerpiente(Casilla cactual) {
		Casilla[] tem;
		vista.ActualizarMensaje("Serpiente!! Retrocede "+cactual.getPosiciones()+" casillas",new Color(194, 44, 39));
		//vista.update(vista.getGraphics());
		try {
			Thread.sleep(2000);
		}catch(Exception e) {}
		tem=modelo.Retroceso( modelo.ObtenerNodo(modelo.Jug[JEnTurno].getPosicion() ) , cactual.getPosiciones());
		vista.MoverJugador(tem, modelo.Jug[JEnTurno]);
	}
	
	private void AccionEscalera(Casilla cactual) {
		Casilla[] tem;
		vista.ActualizarMensaje("Escalera!! Sube "+cactual.getPosiciones()+" casillas",new Color(59, 185, 46));
		//vista.update(vista.getGraphics());
		try {
			Thread.sleep(2000);
		}catch(Exception e) {}
		tem=modelo.Avance( modelo.ObtenerNodo(modelo.Jug[JEnTurno].getPosicion() ) , cactual.getPosiciones(),false);
		vista.MoverJugador(tem, modelo.Jug[JEnTurno]);
	}

	@Override
	public void keyPressed(KeyEvent evt) {
		if(evt.getKeyChar()==KeyEvent.VK_ENTER)
			Turno();
	}

	@Override
	public void keyReleased(KeyEvent evt) {	
	}

	@Override
	public void keyTyped(KeyEvent evt) {	
	}
}
