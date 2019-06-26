package memorama;

import java.awt.event.*;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import utilería.Rutinas;


public class Escuchador implements ActionListener{
	int cont=0,contasiertos=0;
	Cartas Caux;
	Ventana v;
	
	public Escuchador(Ventana V) {
		v=V;
	}
	
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() instanceof JRadioButton) {
			JRadioButton aux=(JRadioButton) evt.getSource();
			if(aux==v.R1) {
				v.RehacerJuego(12,697);
				Principal.Pares=6;
				contasiertos=cont=0;
				return;
			}
			if(aux==v.R2) {
				v.RehacerJuego(14,698);
				Principal.Pares=7;
				contasiertos=cont=0;
				return;
			}
			if(aux==v.R3) {
				v.RehacerJuego(16,699);
				Principal.Pares=8;
				contasiertos=cont=0;
				return;
			}
			if(aux==v.R4) {
				v.RehacerJuego(18,701);
				Principal.Pares=9;
				contasiertos=cont=0;
				return;
			}
			if(aux==v.R5) {
				v.RehacerJuego(20,702);
				Principal.Pares=10;
				contasiertos=cont=0;
				return;
			}
			return;
		}
		Cartas aux=(Cartas) evt.getSource();
		aux.Voltear();
		cont++;
		if(cont%2==0) {
			try {
				Thread.sleep(450);
			}catch(InterruptedException e) {}
			if(Caux.getNombreImagen().compareTo(aux.getNombreImagen())==0) {
				contasiertos++;
				if(Principal.Pares==contasiertos) {
					JOptionPane.showMessageDialog(null,"Terminaste :)");
				}
			}else {
				Caux.setIcon(Rutinas.AjustarImagen("imagenes/nba.jpg", 160,120));
				aux.setIcon(Rutinas.AjustarImagen("imagenes/nba.jpg", 160,120));
				Caux.setEnabled(true);
				aux.setEnabled(true);
				cont=0;
			}
		}
		Caux=aux;
	}

}
