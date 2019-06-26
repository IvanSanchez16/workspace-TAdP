package serpientesyescaleras;

import java.awt.Color;
import javax.swing.*;
import java.awt.*;

public class Casilla extends JPanel{
	private int NoCasilla;
	private char TipoCasilla;
	private int Posiciones;
	private JLabel LbN;
	private ImageIcon imagen;

	public Casilla(int nc) {
		TipoCasilla='N';
		Posiciones=0;
		setEnabled(false);
		LbN=new JLabel(nc+"",SwingConstants.RIGHT);
		LbN.setFont(new Font("Tahoma", 1, 14));
		LbN.setForeground(new Color(0,0,0));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		add(LbN);
		if(nc%2==0) 
			setBackground(new Color(210, 111, 12));
		else 
			setBackground(new Color(254, 158, 62));
	}
		
	public char getTipoCasilla() {
		return TipoCasilla;
	}
	
	public void CambiaLabel(String a){
		LbN.setText(LbN.getText()+"             "+a);
	}
	
	public void setTipoCasilla(char tipoCasilla) {
		TipoCasilla = tipoCasilla;
	}
	
	public int getPosiciones() {
		return Posiciones;
	}
	
	public void setPosiciones(int posiciones) {
		Posiciones = posiciones;
	}
	
	public int getNoCasilla() {
		return NoCasilla;
	}
	
	public void CambiarImagen(String nom){
		imagen=new ImageIcon(nom);
		repaint();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		if(imagen!=null)
			g.drawImage(imagen.getImage(),20,10,45,45,null);
		
	}
}
