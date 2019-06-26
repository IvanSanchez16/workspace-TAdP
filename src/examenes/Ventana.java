package examenes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import utilería.Rutinas.*;

public class Ventana extends JFrame{
	
	JButton BtnIniciar;
	JLabel[] AIndicadores;
	JLabel LbMsg;
	Controlador C;
	int GalonesActuales;
	JPanel P1;
	Timer T;
	
	public Ventana() {
		super("Tinaco de agua");
		HazInterfaz();
		GalonesActuales=32;
		ActualizaInterfaz();
	}
	
	private void HazInterfaz() {
		setSize(250,680);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel P2=new JPanel();
		P2.setLayout(new BoxLayout(P2,BoxLayout.Y_AXIS));
		BtnIniciar=new JButton("Iniciar Simulación");
		P2.add(BtnIniciar);
		LbMsg=new JLabel();
		P2.add(LbMsg);
		add(P2,BorderLayout.NORTH);
		
		P1=new JPanel();
		P1.setLayout(new GridLayout(0,2,10,3));
		AIndicadores=new JLabel[40];
		for(int i=40;i>0;i--) {
			JLabel lb=new JLabel(( ((i-1)*10)+1 )+"-"+(i*10),SwingConstants.RIGHT);
			P1.add(lb);
			AIndicadores[i-1]=new JLabel();
			AIndicadores[i-1].setBackground(Color.GREEN);
			AIndicadores[i-1].setOpaque(true);
			P1.add(AIndicadores[i-1]);
		}
		add(P1,BorderLayout.CENTER);
		setVisible(true);
	}
	
	public void setEscuchador(Controlador c) {
		C=c;
		BtnIniciar.addActionListener(C);
		T=new Timer(100,C);
	}
	
	public void agregaAgua(int ngalones) {
		GalonesActuales+=ngalones;
		ActualizaInterfaz();
	}
	
	public void validaLimites() {
		if(GalonesActuales>320) {
			GalonesActuales=320;
			ActualizaInterfaz();
		}

		if(GalonesActuales<32) {
			GalonesActuales=32;
			ActualizaInterfaz();
		}
	}
	
	public void extraeAgua(int ngalones) {
		GalonesActuales-=ngalones;
		ActualizaInterfaz();
		
	}
	
	private void ActualizaInterfaz() {
		for(int i=1;i<=40;i++) {
			if(GalonesActuales >= i*10) {
				AIndicadores[i-1].setBackground(Color.RED);
				continue;
			}
			if( ( GalonesActuales < (i*10) ) && ( GalonesActuales >= ((i-1)*10)+1) ) {
				AIndicadores[i-1].setBackground(Color.BLUE);
				continue;
			}
			AIndicadores[i-1].setBackground(Color.GREEN);
		}
	}
	
	public void actualizaMensaje(String msg) {
		LbMsg.setText(msg);
	}
}
