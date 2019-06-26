package utilería;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame implements ActionListener {
	JMenuItem Azul,Rojo,Amarillo;
	JMenuItem Verano,Otoño,Invierno;
	JMenu Primavera;
	JMenuItem Montaña,Desierto,Playa;
	
	JButton BtnAtras,BtnSiguiente;
	JPanel Panel;
	JDialog [] Pan2;
	int Sub=0;
	public Menu(){
		super("Centro de investigación");
		HazInterfaz();
		HazEscuchas();
	}
	public void HazInterfaz(){
		setSize(400,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setBackground(Color.GREEN);
		JMenuBar Barra=new JMenuBar();
		setJMenuBar(Barra);
		Barra.setBackground(Color.cyan);
		JMenu MenuColores=new JMenu("Colores");
		Barra.add(MenuColores);
		Azul=new JMenuItem("Azul");
		Rojo=new JMenuItem("Rojo");
		Amarillo=new JMenuItem("Amarillo");
		MenuColores.add(Azul);
		MenuColores.add(Rojo);
		MenuColores.add(Amarillo);
		JMenu MenuAutos=new JMenu("Auto");
		Barra.add(MenuAutos);
		
		JMenu MenuVacaciones=new JMenu("Vacaciones");
		
		Verano=new JMenuItem("Verano");
		
		Otoño=new JMenuItem("Otoño");
		Invierno=new JMenuItem("Invierno");
		
		Primavera=new JMenu("Primavera");
		Playa=new JMenuItem("Playa");
		Desierto=new JMenuItem("Desierto");
		Montaña=new JMenuItem("Montaña");
		Primavera.add(Playa);
		Primavera.add(Desierto);
		Primavera.add(Montaña);
		
		MenuVacaciones.add(Verano);
		MenuVacaciones.add(Primavera);
		MenuVacaciones.add(Otoño);
		MenuVacaciones.add(Invierno);
		Barra.add(MenuVacaciones);
		
		Panel=new JPanel();
		Panel.setLayout(new GridLayout(0,2));
		BtnSiguiente=new JButton("Siguiente");
		BtnAtras=new JButton("Anterior");
		Panel.add(BtnAtras);
		Panel.add(BtnSiguiente);
		
		
		setVisible(true);
		
	}
	public void HazEscuchas(){
		Azul.addActionListener(this);
		Rojo.addActionListener(this);
		Amarillo.addActionListener(this);
		
		Verano.addActionListener(this);
		BtnAtras.addActionListener(this);
		BtnSiguiente.addActionListener(this);
		
		
		Playa.addActionListener(this);
	}
	public static void main(String [] a){
		new Menu();
	}
	
	public void actionPerformed(ActionEvent Evt) {
		
		if(Evt.getSource()==Playa){
			JDialog PPlaya=new JDialog();
			PPlaya.setSize(300,400);
			PPlaya.setLocationRelativeTo(null);
			JTabbedPane P=new JTabbedPane();

			P.addTab("Datos Generales", null, null, "información del empleado");

			P.addTab("Finanzas", null, new JPanel(), "situación económica");

			P.addTab("Proyección", null, new JPanel(), "hacia donde vamos");

			P.addTab("Finanzas", null, new JPanel(), "situación económica");

			PPlaya.add(P);
			PPlaya.setModal(true);
			PPlaya.setVisible(true);;
			
			
		}
		System.out.println("llegó");
		if(Evt.getSource()==Azul){
			this.getContentPane().setBackground(Color.BLUE);
			return;
		}
		if(Evt.getSource()==Amarillo){
			this.getContentPane().setBackground(Color.YELLOW);
			return;
		}
		if(Evt.getSource()==Rojo){
			this.getContentPane().setBackground(Color.RED);
			return;
		}
		if(Evt.getSource()==Verano){
			Pan2=new JDialog[5];
			for(int i=0 ; i<Pan2.length ; i++){
				Pan2[i]=new JDialog();
				Pan2[i].setTitle("Pantalla# "+(i+1));
				Pan2[i].setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				Pan2[i].setSize(400, 300);
				Pan2[i].setLocationRelativeTo(null);
				Pan2[i].setModal(true);

			}
			Pan2[Sub].add(Panel,BorderLayout.SOUTH);
			BtnAtras.setEnabled(false);
			Pan2[Sub].setVisible(true);
			return;
		}
		if(Evt.getSource()==BtnSiguiente){
			if(Sub==Pan2.length-2){
				BtnSiguiente.setEnabled(false);
			}
			if(Sub==0)
				BtnAtras.setEnabled(true);
			Sub++;
			Pan2[Sub].add(Panel,BorderLayout.SOUTH);
			Pan2[Sub].setVisible(true);
			return;
		}
		if(Evt.getSource()==BtnAtras){
			if(Sub==1)
				BtnAtras.setEnabled(false);
			BtnSiguiente.setEnabled(true);
			Pan2[Sub].setVisible(false);
			Sub--;
			Pan2[Sub].add(Panel,BorderLayout.SOUTH);
			return;
		}
		
	}
}
