package terrenosHermanos;

import javax.swing.*;

public class AplHermanos {
	
	public static void main(String [] args) {
		Vista vista=new Vista();
		JOptionPane.showMessageDialog(vista,"Oprima 'Aceptar' para empezar","Inicio",JOptionPane.INFORMATION_MESSAGE);
		vista.iniciar();
	}
}
