package componentes;

import javax.swing.*;
import java.awt.*;
import java.util.regex.*;

public class SubComponente extends JPanel{
	private JButton btnBorrar;
	private JTextField txtInfo;
	private static String expresion;
	
	public SubComponente(CajasMultiples cm) {
		creaInterfaz();
		btnBorrar.addActionListener(cm);
	}
	
	private void creaInterfaz() {
		setLayout(new BorderLayout());
		btnBorrar=new JButton("X");
		btnBorrar.setFont(new Font("Console",1,8));
		txtInfo=new JTextField();
		add(txtInfo,BorderLayout.CENTER);
		add(btnBorrar,BorderLayout.EAST);
	}
	
	public boolean validarExpresion() {
		Pattern pat = Pattern.compile(expresion);
		Matcher mat = pat.matcher(txtInfo.getText());
		if(mat.find())
			return true;
		return false;
	}
	
	public String getText() {
		return txtInfo.getText();
	}
	
	public static void setExpresion(String exp) {
		expresion=exp;
	}
	
	public JButton getBtnBorrar() {
		return btnBorrar;
	}
	
}
