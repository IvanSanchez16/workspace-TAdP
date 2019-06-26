package catalogodecuentas;

import javax.swing.*;

import utilería.JIntegerBox;

import java.awt.*;

public class Vista extends JFrame{
	JMenu MCatalogo,MPolizas;
	JTabbedPaneCatalogo JTPCatalogo;
	JTabbedPanePolizas JTPPolizas;
	JPanel PanelCentral;
	ControladorC C;
	ControladorP C2;
	private final Font F,F2;
	
	public Vista() {
		super("Catálogo de cuentas");
		F=new Font("Candara",0,15);
		F2=new Font("Dubai",0,14);
		try {
		setDefaultLookAndFeelDecorated(true);
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch(Exception e) {}
		HazInterfaz();
	}
	
	public void setEscuchador(ControladorC c,ControladorP c2,ControlDeEntradas ce) {
		C=c;
		C2=c2;
		MCatalogo.addMouseListener(C);
		MPolizas.addMouseListener(C2);
		JTPCatalogo.setEscuchador(C,ce);
		JTPPolizas.setEscuchador(C2);
	}
	
	private void HazInterfaz() {
		setSize(450,300);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar Menu=new JMenuBar();
		MCatalogo=new JMenu("Catálogo");
		MCatalogo.setFont(F);
		MPolizas=new JMenu("Pólizas");
		MPolizas.setFont(F);
		Menu.add(MCatalogo);
		Menu.add(MPolizas);
		
		JTPCatalogo=new JTabbedPaneCatalogo();
		JTPPolizas=new JTabbedPanePolizas();

		PanelCentral=new JPanel();
		PanelCentral.add(JTPCatalogo);
		PanelCentral.add(JTPPolizas);
		add(PanelCentral);
		
		setJMenuBar(Menu);
		setVisible(true);
	}
	
	public void MostrarJOError(String title,String msg) {
		JOptionPane.showMessageDialog(null,msg,title, JOptionPane.WARNING_MESSAGE);
	}
	
	public int MostrarJOYoN(String title,String msg) {
		return JOptionPane.showConfirmDialog(this,msg,title,JOptionPane.YES_NO_OPTION);
	}
	
	public void MostrarCuenta(Cuenta c) {
		JDialog jd=new JDialog(this,"Consulta de cuenta",true);
		jd.setLayout(new GridLayout(0,2,5,5));
		jd.setSize(250,150);
		jd.setLocationRelativeTo(null);
		
		JLabel lb1=new JLabel("Número de cuenta:",SwingConstants.RIGHT);
		JLabel lb2=new JLabel(c.getNoCuenta());
		JLabel lb3=new JLabel("Nombre:",SwingConstants.RIGHT);
		JLabel lb4=new JLabel(c.getNombre());
		JLabel lb5=new JLabel("Saldo:",SwingConstants.RIGHT);
		JLabel lb6=new JLabel(c.getSaldo()+"");
		JLabel lb7=new JLabel("Cargo:",SwingConstants.RIGHT);
		JLabel lb8=new JLabel(c.getCargo()+"");
		JLabel lb9=new JLabel("Abono:",SwingConstants.RIGHT);
		JLabel lb10=new JLabel(c.getAbono()+"");
		
		lb1.setFont(F2);
		lb2.setFont(F2);
		lb3.setFont(F2);
		lb4.setFont(F2);
		lb5.setFont(F2);
		lb6.setFont(F2);
		lb7.setFont(F2);
		lb8.setFont(F2);
		lb9.setFont(F2);
		lb10.setFont(F2);
		
		jd.add(lb1);
		jd.add(lb2);
		jd.add(lb3);
		jd.add(lb4);
		jd.add(lb5);
		jd.add(lb6);
		jd.add(lb7);
		jd.add(lb8);
		jd.add(lb9);
		jd.add(lb10);
		
		jd.setVisible(true);
	}
	
	public void setJTabbedPane(boolean band) {
		if(band) {
			JTPCatalogo.setVisible(true);
			JTPPolizas.setVisible(false);
			PanelCentral.update(PanelCentral.getGraphics());
			return;
		}
		JTPCatalogo.setVisible(false);
		JTPPolizas.setVisible(true);
		PanelCentral.update(PanelCentral.getGraphics());
	}
}
