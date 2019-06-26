package componentes;

import javax.swing.*;

import utilería.Rutinas;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class AplComponentes extends JFrame implements ActionListener{
	
	JTriComboBox JTB;
	JFiltro JF;
	CajasMultiples CM;
	
	JTextField TxtEdoM,TxtMunM,TxtCiuM,TxtEdoMI,TxtMunMI,TxtCiuMI,TxtAgg;
	JButton BtnActualizar,BtnCrecer,BtnAchicar,BtnFuenteAleatoria,BtnAgg,BtnLimpiar,BtnAgg2;
	JComboBox<String> CBox;
	
	public AplComponentes() {
		super("Presentación componentes");
		setSize(600,400);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch(Exception e) {}
		
		JTabbedPane tp = new JTabbedPane();
		JPanel p1=creaPanelCombosDependientes();
		JPanel p2=creaPanelComboFiltrado();
		JPanel p3=creaPanelCajasMultiples();
		tp.add(p1,"Combos dependientes");
		tp.add(p2,"Combo muestra filtrado");
		tp.add(p3,"Cajas múltiples");
		add(tp);
		
		setVisible(true);
	}
	
	private JPanel creaPanelCajasMultiples() {
		JPanel p=new JPanel();
		p.setLayout(null);
		CM=new CajasMultiples();
		CM.setBounds(100,0,400,250);
		CBox=new JComboBox<String>();
		CBox.setBounds(100,260,200,40);
		BtnAgg2=new JButton("Actualizar Combo");
		BtnAgg2.setBounds(305,260,150,40);
		BtnAgg2.addActionListener(this);
		
		p.add(BtnAgg2);
		p.add(CBox);
		p.add(CM);
		
		return p;
	}

	private JPanel creaPanelComboFiltrado() {
		JPanel p=new JPanel();
		p.setLayout(null);
		
		String []VN ={"Alicia","Maria","Sofia","Antonio","Nereida","Carolina",
		        "Rebaca","Javier","Luis","Aguascalientes","Baja California","Baja California Sur"
				,"Campeche","Chiapas","Chihuahua", "CDMX","Coahuila",
				"Colima","Durango","Guanajuato","Guerrero","Hidalgo",
				"Jalisco","México","Michoacan","Morelos","Nayarit",
				"Nuevo León","Oaxaca","Puebla","Querétaro","Quintana Roo",
				"San Luis Potosí","Sinaloa","Sonora","Tabasco","Tamaulipas",
				"Tlaxcala","Veracruz","Yucatán","Zacatecas"};
		JF=new JFiltro(VN);
		JF.setBounds(200,30,200,50);
		
		BtnAgg=new JButton("Agregar Elemento");
		TxtAgg=new JTextField("");
		BtnLimpiar=new JButton("Limpiar combo");
		TxtAgg.setBounds(125,250,200,30);
		BtnAgg.setBounds(325,250,150,29);
		BtnLimpiar.setBounds(125,282,350,30);
		BtnAgg.addActionListener(this);
		BtnLimpiar.addActionListener(this);
		
		p.add(TxtAgg);
		p.add(BtnLimpiar);
		p.add(BtnAgg);
		p.add(JF);
		return p;
	}

	private JPanel creaPanelCombosDependientes() {
		JPanel p=new JPanel();
		p.setLayout(null);
		JTB=new JTriComboBox();
		JTB.setBounds(25,0,530,30);
		
		JPanel p2=new JPanel();
		p2.setLayout(new GridLayout(0,2,5,0));
		JLabel lb=new JLabel("Nombre");
		JLabel lb2=new JLabel("Index");
		TxtEdoM=new JTextField("");
		TxtEdoMI=new JTextField("");
		TxtMunM=new JTextField("");
		TxtMunMI=new JTextField("");
		TxtCiuM=new JTextField("");
		TxtCiuMI=new JTextField("");
		TxtEdoM.setEditable(false);
		TxtEdoMI.setEditable(false);
		TxtMunM.setEditable(false);
		TxtMunMI.setEditable(false);
		TxtCiuM.setEditable(false);
		TxtCiuMI.setEditable(false);
		p2.add(lb);
		p2.add(lb2);
		p2.add(TxtEdoM);
		p2.add(TxtEdoMI);
		p2.add(TxtMunM);
		p2.add(TxtMunMI);
		p2.add(TxtCiuM);
		p2.add(TxtCiuMI);
		BtnActualizar=new JButton("Actualizar Información");
		BtnActualizar.setBounds(25,155,250,30);
		BtnActualizar.addActionListener(this);
		
		BtnCrecer=new JButton("Alargar");
		BtnAchicar=new JButton("Recortar");
		BtnFuenteAleatoria=new JButton("Generar Fuente aleatoria");
		BtnCrecer.setBounds(350,70,200,30);
		BtnAchicar.setBounds(350,110,200,30);
		BtnFuenteAleatoria.setBounds(350,150,200,30);
		BtnCrecer.addActionListener(this);
		BtnAchicar.addActionListener(this);
		BtnFuenteAleatoria.addActionListener(this);
		
		p2.setBounds(25,50,250,100);
		
		p.add(BtnFuenteAleatoria);
		p.add(BtnCrecer);
		p.add(BtnAchicar);
		p.add(BtnActualizar);
		p.add(p2);
		p.add(JTB);
		return p;
	}

	public static void main(String []args) {
		new AplComponentes();
	}

	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()==BtnActualizar) {
			TxtEdoM.setText(JTB.getSelectedItemEstado());
			TxtEdoMI.setText(JTB.getSelectedIndexEstado()+"");
			TxtMunM.setText(JTB.getSelectedItemMunicipio());
			TxtMunMI.setText(JTB.getSelectedIndexMunicipio()+"");
			TxtCiuM.setText(JTB.getSelectedItemCiudad());
			TxtCiuMI.setText(JTB.getSelectedIndexCiudad()+"");
			return;
		}
		if(evt.getSource()==BtnCrecer) {
			Font aux=JTB.getFont();
			JTB.setBounds(JTB.getX(),JTB.getY(),JTB.getWidth()+5,JTB.getHeight());
			JTB.updateUI();
			JTB.setFont(aux);
			return;
		}
		if(evt.getSource()==BtnAchicar) {
			Font aux=JTB.getFont();
			JTB.setBounds(JTB.getX(),JTB.getY(),JTB.getWidth()-5,JTB.getHeight());
			JTB.updateUI();
			JTB.setFont(aux);
			return;
		}
		if(evt.getSource()==BtnFuenteAleatoria) {
			String[] Nombrefonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
			JTB.setFont(new Font(Nombrefonts[Rutinas.nextInt(0,Nombrefonts.length-1)],Rutinas.nextInt(0,2),Rutinas.nextInt(8,15)));
			return;
		}
		if(evt.getSource()==BtnAgg) {
			JF.addElement(TxtAgg.getText());
			TxtAgg.setText("");
			return;
		}
		if(evt.getSource()==BtnLimpiar) {
			JF.clearComboBox();
			return;
		}
		if(evt.getSource()==BtnAgg2) {
			CBox.removeAllItems();
			for(int i=0 ; i<CM.arraysSize() ; i++) 
				CBox.addItem(CM.getElementAt(i));
			return;
		}
	}
}
