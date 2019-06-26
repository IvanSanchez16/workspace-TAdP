package utilería;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JMensajeGira extends JLabel implements ActionListener {
	
	private String Texto;
	private Timer T;
	public JMensajeGira(String Texto){
	//	super(Texto);
		this.Texto=Texto;
		T=new Timer(100,this);
		T.start();
		Texto=Rutinas.PonBlancos(Texto, 100);
		this.Texto=Texto;
	}
	public void actionPerformed(ActionEvent Evt) {
		setText(Texto);
		Texto=Texto.substring(1)+Texto.charAt(0);
		
	}

}
