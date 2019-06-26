package listaPersonasInterfaz;
import  javax.swing.*;
import javax.swing.border.Border;

import utilería.NodoDBL;

import java.awt.*;
import java.awt.event.*;

public class Ventana extends JFrame implements ActionListener,KeyListener{
	
	JButton BtnGrabar,BtnConsulta,BtnLimpiar;
	JTextField TxtNombre,TxtEdad,TxtEstatura;
	JLabel LbNombre,LbEdad,LbEstatura;
	JRadioButton RBtnNombre,RBtnEdad,RBtnEstatura,RBtnEEN;
	ButtonGroup BGrpCriterio;
	JDialog DConsulta;
	
	public Ventana() {
		super("Registro personas");
		HazRadioButtons();
		HazCajasDeTexto();
		HazBotones();
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		HazEscuchas();
	}
	
	private void HazCajasDeTexto() {
		JPanel P3=new JPanel();
		P3.setLayout(new GridLayout(0,2));
		LbNombre=new JLabel("Nombre: ",SwingConstants.RIGHT);
		LbEdad=new JLabel("Edad: ",SwingConstants.RIGHT);
		LbEstatura=new JLabel("Estatura: ",SwingConstants.RIGHT);
		TxtNombre=new JTextField();
		TxtEdad=new JTextField();
		TxtEstatura=new JTextField();
		P3.add(LbNombre);
		P3.add(TxtNombre);
		P3.add(LbEdad);
		P3.add(TxtEdad);
		P3.add(LbEstatura);
		P3.add(TxtEstatura);
		add(P3);
	}

	private void HazRadioButtons() {
		JPanel P1=new JPanel();
		P1.setLayout(new BoxLayout(P1,BoxLayout.X_AXIS));
		P1.setBorder(BorderFactory.createTitledBorder("Criterios de ordenamiento"));
		BGrpCriterio=new ButtonGroup();
		RBtnNombre=new JRadioButton("Nombre",true);
		RBtnEdad=new JRadioButton("Edad");
		RBtnEstatura=new JRadioButton("Estatura");
		RBtnEEN=new JRadioButton("Edad - Estatura - Nombre");
		BGrpCriterio.add(RBtnNombre);
		BGrpCriterio.add(RBtnEdad);
		BGrpCriterio.add(RBtnEstatura);
		BGrpCriterio.add(RBtnEEN);
		P1.add(RBtnNombre);
		P1.add(RBtnEdad);
		P1.add(RBtnEstatura);
		P1.add(RBtnEEN);
		add(P1,BorderLayout.NORTH);
	}

	private void HazBotones() {
		JPanel P2=new JPanel();
		P2.setLayout(new GridLayout(0,3));
		BtnGrabar=new JButton("Grabar");
		BtnConsulta=new JButton("Consultar");
		BtnLimpiar=new JButton("Limpiar");
		P2.add(BtnGrabar);
		P2.add(BtnConsulta);
		P2.add(BtnLimpiar);
		add(P2,BorderLayout.SOUTH);
	}
	
	private void HazEscuchas() {
		BtnGrabar.addActionListener(this);
		BtnConsulta.addActionListener(this);
		BtnLimpiar.addActionListener(this);
		RBtnNombre.addActionListener(this);
		RBtnEdad.addActionListener(this);
		RBtnEstatura.addActionListener(this);
		RBtnEEN.addActionListener(this);
		TxtNombre.addActionListener(this);
		TxtEdad.addActionListener(this);
		TxtEstatura.addActionListener(this);
		TxtNombre.addKeyListener(this);
		TxtEdad.addKeyListener(this);
		TxtEstatura.addKeyListener(this);
	}

	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() instanceof JButton){
			EventosBoton((JButton) evt.getSource());
			return;
		}
		if(evt.getSource() instanceof JRadioButton){
			EventosRadioB((JRadioButton)evt.getSource());
			return;
		}
		if(evt.getSource() instanceof JTextField){
			EventosCajasTxt((JTextField)evt.getSource());
			return;
		}
	}

	private void EventosCajasTxt(JTextField txt) {
		if(txt == TxtNombre) 
			TxtEdad.requestFocus();
		if(txt == TxtEdad) 
			TxtEstatura.requestFocus();
		if(txt == TxtEstatura) 
			BtnGrabar.requestFocus();
	}

	private void EventosRadioB(JRadioButton rbtn) {
		if(rbtn==RBtnNombre)
			Persona.Criterio=0;
		if(rbtn==RBtnEdad)
			Persona.Criterio=1;
		if(rbtn==RBtnEstatura)
			Persona.Criterio=2;
		if(rbtn==RBtnEEN)
			Persona.Criterio=3;
		if(ListaPersonas.L.Length()!=0)
			ListaPersonas.Quicksort(0,ListaPersonas.L.Length()-1);
	}

	private void EventosBoton(JButton btn) {
		JDialog Aviso;
		if(btn==BtnGrabar) {
			try {
				String nombre=TxtNombre.getText();
				int edad=Integer.parseInt(TxtEdad.getText());
				int estatura=Integer.parseInt(TxtEstatura.getText());
				ListaPersonas.Guardar(nombre,edad,estatura);
				TxtNombre.setText("");
				TxtEdad.setText("");
				TxtEstatura.setText("");
				return;
			}catch(NumberFormatException e) {
				Aviso=new JDialog(this,"Aviso",true);
				Aviso.add(new JLabel("Hay campos vacios"));
				Aviso.pack();
				Aviso.setResizable(false);
				Aviso.setLocationRelativeTo(null);
				Aviso.setVisible(true);
			}
		}
		if(btn==BtnConsulta) {
			if(ListaPersonas.L.Length()!=0) {
				GeneraJDialog();
				return;
			}
			Aviso=new JDialog(this,"Aviso",true);
			Aviso.add(new JLabel("La lista está vacia"));
			Aviso.pack();
			Aviso.setResizable(false);
			Aviso.setLocationRelativeTo(null);
			Aviso.setVisible(true);
			return;
		}
		if(btn==BtnLimpiar) {
			TxtNombre.setText("");
			TxtEdad.setText("");
			TxtEstatura.setText("");
			return;
		}
	}
	
	private void GeneraJDialog() {
		DConsulta=new JDialog(this,"Consulta",true);
		DConsulta.setLayout(new GridLayout(0,3,10,10));
		NodoDBL<Persona> aux1=ListaPersonas.L.getFrente();
		Persona aux;
		DConsulta.add(new JLabel("Nombre"));
		DConsulta.add(new JLabel("Edad"));
		DConsulta.add(new JLabel("Estatura"));
		for(int i=0;i<ListaPersonas.L.Length();i++) {
			aux=aux1.Info;
			DConsulta.add(new JLabel(aux.getNombre()));
			DConsulta.add(new JLabel(""+aux.getEdad()));
			DConsulta.add(new JLabel(""+aux.getEstatura()));
			aux1=aux1.getSig();
		}
		DConsulta.setResizable(false);
		DConsulta.setLocationRelativeTo(null);
		DConsulta.pack();
		DConsulta.setVisible(true);
	}

	
	public void keyPressed(KeyEvent evt) {
		
	}


	public void keyReleased(KeyEvent evt) {

	}


	public void keyTyped(KeyEvent evt) {
		JTextField aux=(JTextField)evt.getSource();
		if(aux==TxtNombre) {
			if(!(evt.getKeyChar()>='A' && evt.getKeyChar()<='Z' ||
					evt.getKeyChar()>='a' && evt.getKeyChar()<='z' || 
					evt.getKeyChar()==' '))
			evt.consume();
			return;
		}
		if(aux==TxtEdad || aux==TxtEstatura) {
			if(evt.getKeyChar()<'0' || evt.getKeyChar()>'9')
				evt.consume();
			if(aux.getText().length()==3)
				evt.consume();
		}
	}
}
