package catalogodecuentas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JCuentaField extends JTextField implements KeyListener{
	public JCuentaField() {
		addKeyListener(this);
	}
	
	public void keyPressed(KeyEvent evt) {}
	public void keyReleased(KeyEvent evt) {setText(PonGuiones());}	
	
	
	public void keyTyped(KeyEvent evt) {
		if(!Character.isDigit(evt.getKeyChar())) {
			evt.consume();
			return;
		}
		if(QuitaGuiones().length()>=6) {
			evt.consume();
			return;
		}
	}
	
	public String ObtenerNumero() {
		return QuitaGuiones();
	}

	private String PonGuiones() {
		String texto=QuitaGuiones(),textonuevo="";
		if(texto.length()<=2)
			return texto;
		for(int i=0 ; i<texto.length() ; i++) {
			if(i==2 || i==4) 
				textonuevo=textonuevo+"-";
			textonuevo=textonuevo+texto.charAt(i);
		}
		return textonuevo;
	}
	
	private String QuitaGuiones() {
		String texto=getText(),textonuevo="";
		if(texto.length()<=2)
			return texto;
		for(int i=0 ; i<texto.length() ; i++) {
			if(texto.charAt(i)=='-') 
				continue;
			textonuevo=textonuevo+texto.charAt(i);
		}
		return textonuevo;
	}
}
