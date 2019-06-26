package memorama;
import java.awt.Color;

import javax.swing.*;

import utilería.Rutinas;
public class Cartas extends JButton{
	private String NombreImagen;
	
	public Cartas(String ni) {
		setIcon(Rutinas.AjustarImagen("imagenes/nba.jpg", 160,120));
		setBackground(new Color(45, 92, 231));
		setBorder(null);
		NombreImagen=ni;
	}
	
	public String getNombreImagen() {
		return NombreImagen;
	}
	
	public void Voltear() {
		setEnabled(false);
		setDisabledIcon(Rutinas.AjustarImagen(NombreImagen,160,120));
		update(getGraphics());
	}
}
