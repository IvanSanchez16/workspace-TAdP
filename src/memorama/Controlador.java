package memorama;

import java.awt.event.*;

import javax.swing.JOptionPane;

import utilería.Rutinas;


public class Controlador implements ActionListener{
	int cont=0,contasiertos=0;
	Cartas Caux;
	public void actionPerformed(ActionEvent evt) {
		Cartas aux=(Cartas) evt.getSource();
		aux.Voltear();
		cont++;
		if(cont%2==0) {
			try {
				Thread.sleep(350);
			}catch(InterruptedException e) {}
			if(Caux.getNombreImagen().compareTo(aux.getNombreImagen())==0) {
				Caux.setDisabledIcon(Rutinas.AjustarImagen(Caux.getNombreImagen(), 100,120));
				aux.setDisabledIcon(Rutinas.AjustarImagen(aux.getNombreImagen(), 100,120));
				Caux.setEnabled(false);
				aux.setEnabled(false);
				contasiertos++;
				if(Principal.Pares==contasiertos) {
					JOptionPane.showMessageDialog(null,"Haz terminado");
				}
			}else {
				Caux.setIcon(Rutinas.AjustarImagen("carta.jpg", 100,120));
				aux.setIcon(Rutinas.AjustarImagen("carta.jpg", 100,120));
				Caux.setEnabled(true);
				aux.setEnabled(true);
				cont=0;
			}
		}
		Caux=aux;
	}

}
