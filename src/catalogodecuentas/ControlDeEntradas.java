package catalogodecuentas;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class ControlDeEntradas implements KeyListener{
	
	JTabbedPaneCatalogo JTPCatalogo;
	JTabbedPanePolizas JTPPolizas;
	public ControlDeEntradas(JTabbedPaneCatalogo jtpc,JTabbedPanePolizas jtpp) {
		JTPCatalogo=jtpc;
		JTPPolizas=jtpp;
	}

	public void keyPressed(KeyEvent evt) {}
	public void keyReleased(KeyEvent evt) {}

	public void keyTyped(KeyEvent evt){
		if(evt.getSource()==JTPCatalogo.TxtNombre) {
			if(JTPCatalogo.TxtNombre.getText().length()>=25 || (!Character.isAlphabetic(evt.getKeyChar()) && !Character.isDigit(evt.getKeyChar()) && (evt.getKeyChar()!=KeyEvent.VK_SPACE))){
				evt.consume();
				return;
			}
		}
		if(evt.getSource()==JTPCatalogo.TxtNewNombre) {
			if(JTPCatalogo.TxtNewNombre.getText().length()>=25 || (!Character.isAlphabetic(evt.getKeyChar()) && !Character.isDigit(evt.getKeyChar()) && (evt.getKeyChar()!=KeyEvent.VK_SPACE))){
				evt.consume();
				return;
			}
		}
	}
	
}
