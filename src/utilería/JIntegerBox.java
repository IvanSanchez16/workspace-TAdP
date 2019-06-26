package utiler�a;

import javax.swing.*;
import java.awt.event.*;
public class JIntegerBox extends JTextField implements KeyListener{

	public JIntegerBox() {
		addKeyListener(this);
	}
	
	public void keyPressed(KeyEvent evt) {
	}

	
	public void keyReleased(KeyEvent evt) {
	}
	
	public void keyTyped(KeyEvent evt) {
		if(!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar()!='-')
			evt.consume();
		if(evt.getKeyChar()=='-') {
			evt.consume();
			if(getText().length()==0) {
				setText("-");
				return;
			}
			if(getText().charAt(0)!='-') {
				setText("-"+getText());
				return;
			}
		}
	}
	
	public long ObtenerCantidad() {
		return Long.parseLong(getText());
	}
}
