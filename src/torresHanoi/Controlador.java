package torresHanoi;

import java.awt.event.*;
import javax.swing.*;

public class Controlador implements ActionListener{

	Vista vista;
	Modelo modelo;
	
	public Controlador(Vista v,Modelo m) {
		vista=v;
		modelo=m;
	}
	
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()==vista.T) {
			modelo.RealizaMov();
			vista.lienzo.Dibuja();
			if(modelo.MActual==null) {
				vista.setEnabledRButtons();
				vista.T.stop();
				vista.BtnIniciar.setEnabled(false);
				vista.BtnIniciar.setText("Iniciar");
			}
			return;
		}
		if(evt.getSource()==vista.BtnIniciar) {
			String Btntxt =vista.BtnIniciar.getText();
			if(Btntxt=="Iniciar" || Btntxt=="Reanudar") {
				vista.setEnabledRButtons();
				vista.T.start();
				vista.BtnIniciar.setText("Pausar");
				return;
			}
			vista.setEnabledRButtons();
			vista.T.stop();
			vista.BtnIniciar.setText("Reanudar");
			return;
		}
		
		if(!vista.BtnIniciar.isEnabled())
			vista.BtnIniciar.setEnabled(true);
		JRadioButton RBtn=(JRadioButton)evt.getSource();
		if(RBtn==vista.RBtn3) {
			ReiniciarJuego(3);
			return;
		}
		if(RBtn==vista.RBtn4) {
			ReiniciarJuego(4);
			return;
		}
		if(RBtn==vista.RBtn5) {
			ReiniciarJuego(5);
			return;
		}
		if(RBtn==vista.RBtn6) {
			ReiniciarJuego(6);
			return;
		}
		if(RBtn==vista.RBtn7) {
			ReiniciarJuego(7);
			return;
		}
		if(RBtn==vista.RBtn8) {
			ReiniciarJuego(8);
			return;
		}
		if(RBtn==vista.RBtn9) {
			ReiniciarJuego(9);
			return;
		}
		if(RBtn==vista.RBtn10) {
			ReiniciarJuego(10);
			return;
		}
		if(RBtn==vista.RBtn11) {
			ReiniciarJuego(11);
			return;
		}
		if(RBtn==vista.RBtn12) {
			ReiniciarJuego(12);
			return;
		}
		if(RBtn==vista.RBtn13) {
			ReiniciarJuego(13);
			return;
		}
		if(RBtn==vista.RBtn14) {
			ReiniciarJuego(14);
			return;
		}
		if(RBtn==vista.RBtn15) {
			ReiniciarJuego(15);
			return;
		}
	}
	private void ReiniciarJuego(int ndiscos) {
		modelo.CrearJuego(ndiscos);
		vista.AsignaArreglo(modelo.ADiscos);
		vista.BtnIniciar.setText("Iniciar");
	}

}
