package serpientesyescaleras;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Jugador extends JLabel{
	private Casilla Posicion;
	
	public Jugador(int nj,ImageIcon a) {
		super("J"+nj,a,0);
		setForeground(Color.BLACK);
		Posicion=null;
	}

	public Casilla getPosicion() {
		return Posicion;
	}

	public void setPosicion(Casilla posicion) {
		Posicion = posicion;
	}
	
	
}
