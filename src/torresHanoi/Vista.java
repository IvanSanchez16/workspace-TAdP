package torresHanoi;

import javax.swing.*;

import utilería.Rutinas;

import java.awt.*;

public class Vista extends JFrame{
	
	JButton BtnIniciar;
	JLabel lb;
	Lienzo lienzo;
	Controlador C;
	Timer T;
	ButtonGroup GB;
	JRadioButton RBtn3,RBtn4,RBtn5,RBtn6,RBtn7,RBtn,RBtn8,RBtn9,RBtn10,RBtn11,RBtn12,RBtn13,RBtn14,RBtn15;
	
	public Vista() {
		super("Hanoi");
		HazInterfaz();
		setVisible(true);
		lienzo=new Lienzo(createImage(700,300));
		add(lienzo);
	}
	
	public void HazInterfaz() {
		setSize(800,450);
		PanelFondo fondo=new PanelFondo();
		setContentPane(fondo);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Rutinas.AjustarImagen("imagenes/icono.png",70,70).getImage());
		
		lb=new JLabel("Número de discos");
		lb.setFont(new Font("Berlin Sans FB",0,16));
		lb.setBounds(50,20,125,30);
		lb.setBackground(Color.WHITE);
		lb.setOpaque(true);
		add(lb);
		
		BtnIniciar=new JButton("Iniciar");
		BtnIniciar.setBounds(640,20,110,30);
		BtnIniciar.setFont(new Font("Tahoma",0,17));
		add(BtnIniciar);
		BtnIniciar.grabFocus();
		
		GB=new ButtonGroup();
		RBtn3=new JRadioButton("3",true);
		RBtn3.setBounds(50,60,45,30);
		AñadeRadioButton(RBtn3);
		
		RBtn4=new JRadioButton("4");
		RBtn4.setBounds(104,60,45,30);
		AñadeRadioButton(RBtn4);
		
		RBtn5=new JRadioButton("5");
		RBtn5.setBounds(159,60,45,30);
		AñadeRadioButton(RBtn5);
		
		RBtn6=new JRadioButton("6");
		RBtn6.setBounds(213,60,45,30);
		AñadeRadioButton(RBtn6);
		
		RBtn7=new JRadioButton("7");
		RBtn7.setBounds(268,60,45,30);
		AñadeRadioButton(RBtn7);
		
		RBtn8=new JRadioButton("8");
		RBtn8.setBounds(322,60,45,30);
		AñadeRadioButton(RBtn8);
		
		RBtn9=new JRadioButton("9");
		RBtn9.setBounds(377,60,45,30);
		AñadeRadioButton(RBtn9);
		
		RBtn10=new JRadioButton("10");
		RBtn10.setBounds(431,60,45,30);
		AñadeRadioButton(RBtn10);
		
		RBtn11=new JRadioButton("11");
		RBtn11.setBounds(486,60,45,30);
		AñadeRadioButton(RBtn11);
		
		RBtn12=new JRadioButton("12");
		RBtn12.setBounds(540,60,45,30);
		AñadeRadioButton(RBtn12);
		
		RBtn13=new JRadioButton("13");
		RBtn13.setBounds(595,60,45,30);
		AñadeRadioButton(RBtn13);
		
		RBtn14=new JRadioButton("14");
		RBtn14.setBounds(649,60,45,30);
		AñadeRadioButton(RBtn14);
		
		RBtn15=new JRadioButton("15");
		RBtn15.setBounds(704,60,45,30);
		AñadeRadioButton(RBtn15);
	}
	
	private void AñadeRadioButton(JRadioButton rb) {
		GB.add(rb);
		rb.setFont(new Font("Berlin Sans FB",0,16));
		rb.setBackground(Color.WHITE);
		rb.setOpaque(true);
		add(rb);
		rb.grabFocus();
	}
	
	public void setEnabledRButtons() {
		RBtn3.setEnabled(!RBtn3.isEnabled());
		RBtn4.setEnabled(!RBtn4.isEnabled());
		RBtn5.setEnabled(!RBtn5.isEnabled());
		RBtn6.setEnabled(!RBtn6.isEnabled());
		RBtn7.setEnabled(!RBtn7.isEnabled());
		RBtn8.setEnabled(!RBtn8.isEnabled());
		RBtn9.setEnabled(!RBtn9.isEnabled());
		RBtn10.setEnabled(!RBtn10.isEnabled());
		RBtn11.setEnabled(!RBtn11.isEnabled());
		RBtn12.setEnabled(!RBtn12.isEnabled());
		RBtn13.setEnabled(!RBtn13.isEnabled());
		RBtn14.setEnabled(!RBtn14.isEnabled());
		RBtn15.setEnabled(!RBtn15.isEnabled());

	}
	
	public void setEscuchador(Controlador c) {
		C=c;
		T=new Timer(16,C);
		BtnIniciar.addActionListener(C);
		RBtn3.addActionListener(C);
		RBtn4.addActionListener(C);
		RBtn5.addActionListener(C);
		RBtn6.addActionListener(C);
		RBtn7.addActionListener(C);
		RBtn8.addActionListener(C);
		RBtn9.addActionListener(C);
		RBtn10.addActionListener(C);
		RBtn11.addActionListener(C);
		RBtn12.addActionListener(C);
		RBtn13.addActionListener(C);
		RBtn14.addActionListener(C);
		RBtn15.addActionListener(C);
	}
	
	public void AsignaArreglo(Disco[] d) {
		lienzo.AsignaArreglo(d);
	}
	
}
class PanelFondo extends JPanel {
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawImage(Rutinas.AjustarImagen("imagenes/fondoF.jpg",getWidth(),getHeight()).getImage(),0,0,800,450,null);
		g.fillRect(48,98,704,304);
		g.fillRect(48,18,129,34);
		g.fillRect(48,58,49,34);
		g.fillRect(102,58,49,34);
		g.fillRect(157,58,49,34);
		g.fillRect(211,58,49,34);
		g.fillRect(266,58,49,34);
		g.fillRect(320,58,49,34);
		g.fillRect(375,58,49,34);
		g.fillRect(429,58,49,34);
		g.fillRect(484,58,49,34);
		g.fillRect(538,58,49,34);
		g.fillRect(593,58,49,34);
		g.fillRect(647,58,49,34);
		g.fillRect(702,58,49,34);
		setOpaque(false);
		super.paint(g);
	}
}

