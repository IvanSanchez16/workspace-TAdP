package catalogodecuentas;

import javax.swing.*;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

import utilería.JIntegerBox;

public class JTabbedPaneCatalogo extends JTabbedPane {
	
	JCuentaField TxtCuenta,TxtCuentaCon;
	JIntegerBox TxtSaldo;
	JComboBox<String> TxtCuentaM,TxtCuentaB;
	JTextArea TxtDatosB;
	JTextField TxtNombre,TxtNombreM,TxtNewNombre;
	JPanel PAgg,PMod,PBaja,PConsulta;
	JButton BtnGrabar,BtnLimpiar,BtnGuardar,BtnBorrar,BtnBuscar;
	ControladorC C;	
	JTable JTConsulta;
	JScrollPane sp;
	private Font F,F2,FN;
	
	public JTabbedPaneCatalogo() {
		F=new Font("Candara",1,15);
		F2=new Font("Candara",0,15);
		FN=new Font("Dubai",0,15);
		setFont(F2);
		
		PAgg=new JPanel();
		generaPAgg();
		
		PMod=new JPanel();
		generaPMod();
		
		PBaja=new JPanel();
		generaPBaja();
		
		PConsulta=new JPanel();
		generaPConsulta();
		
		add(PAgg,"Agregar");
		add(PMod,"Modificar");
		add(PBaja,"Dar de Baja");
		add(PConsulta,"Consultar");
		setPreferredSize(new Dimension(440,241));
	}
	
	public void setEscuchador(ControladorC c,ControlDeEntradas ce) {
		C=c;
		TxtCuentaM.addActionListener(C);
		TxtCuentaB.addActionListener(C);
		BtnBorrar.addActionListener(C);
		BtnGuardar.addActionListener(C);
		BtnGrabar.addActionListener(C);
		BtnLimpiar.addActionListener(C);
		TxtCuenta.addActionListener(C);
		TxtNombre.addActionListener(C);
		TxtSaldo.addActionListener(C);
		BtnBuscar.addActionListener(C);
		TxtNewNombre.addActionListener(C);
		
		TxtNombre.addKeyListener(ce);
		TxtNewNombre.addKeyListener(ce);
		
	}
	
	public void LlenaComboBox(ArrayList<Cuenta> ac) {
		TxtCuentaM.removeAllItems();
		TxtCuentaB.removeAllItems();
		for(int i=0 ; i<ac.size() ; i++) {
			TxtCuentaM.addItem(ac.get(i).getNoCuenta());
			TxtCuentaB.addItem(ac.get(i).getNoCuenta());
			if(i==0) {
				TxtNombreM.setText(ac.get(i).getNombre());
				TxtDatosB.setText( txtArea( ac.get(i) ) );
			}
		}
	}
	
	public String txtArea(Cuenta c) {
		return "No de Cuenta: "+c.getNoCuenta()+"\n"+
				"Nombre: "+c.getNombre()+"\n"+
				"Saldo: "+c.getSaldo()+"\n"+
				"Cargo: "+c.getCargo()+"\n"+
				"Abono: "+c.getAbono();
	}
	
	private void generaPAgg() {
		PAgg.setLayout(new GridLayout(0,2,10,10));
		TxtCuenta=new JCuentaField();
		TxtCuenta.setFont(FN);
		TxtNombre=new JTextField();
		TxtNombre.setFont(FN);
		TxtSaldo=new JIntegerBox();
		TxtSaldo.setFont(FN);
		JLabel lb1=new JLabel("Número de cuenta",SwingConstants.RIGHT);
		lb1.setFont(F);
		JLabel lb2=new JLabel("Nombre",SwingConstants.RIGHT);
		lb2.setFont(F);
		JLabel lb3=new JLabel("Saldo",SwingConstants.RIGHT);
		lb3.setFont(F);
		BtnGrabar=new JButton("Grabar");
		BtnGrabar.setFont(F);
		BtnLimpiar=new JButton("Limpiar");
		BtnLimpiar.setFont(F);
		PAgg.add(lb1);
		PAgg.add(TxtCuenta);
		PAgg.add(lb2);
		PAgg.add(TxtNombre);
		PAgg.add(lb3);
		PAgg.add(TxtSaldo);
		PAgg.add(BtnGrabar);
		PAgg.add(BtnLimpiar);
	}

	private void generaPMod() {
		PMod.setLayout(new BorderLayout());
		JPanel P1=new JPanel(new GridLayout(0,2,10,10));
		TxtCuentaM=new JComboBox<String>();
		TxtCuentaM.setFont(FN);
		TxtNombreM=new JTextField();
		TxtNombreM.setFont(F);
		TxtNombreM.setEditable(false);
		TxtNewNombre=new JTextField();
		TxtNewNombre.setFont(FN);
		JLabel lb1=new JLabel("No de Cuenta",SwingConstants.RIGHT);
		lb1.setFont(F);
		JLabel lb2=new JLabel("Nombre actual",SwingConstants.RIGHT);
		lb2.setFont(F);
		JLabel lb3=new JLabel("Nombre nuevo",SwingConstants.RIGHT);
		lb3.setFont(F);
		BtnGuardar=new JButton("Guardar cambios");
		BtnGuardar.setFont(F);
		P1.add(lb1);
		P1.add(TxtCuentaM);
		P1.add(lb2);
		P1.add(TxtNombreM);
		P1.add(lb3);
		P1.add(TxtNewNombre);
		PMod.add(P1,BorderLayout.CENTER);
		PMod.add(BtnGuardar,BorderLayout.SOUTH);
	}
	
	private void generaPBaja() {
		PBaja.setLayout(null);
		TxtCuentaB=new JComboBox<String>();
		TxtCuentaB.setFont(FN);
		TxtCuentaB.setBounds(192,5,190,30);
		TxtDatosB=new JTextArea();
		TxtDatosB.setFont(F2);
		TxtDatosB.setBounds(15,50,410,120);
		TxtDatosB.setEditable(false);
		BtnBorrar=new JButton("Borrar cuenta");
		BtnBorrar.setFont(F);
		BtnBorrar.setBounds(15,170,410,30);
		JLabel lb1=new JLabel("No de Cuenta",SwingConstants.RIGHT);
		lb1.setFont(F);
		lb1.setBounds(0,5,182,30);
		PBaja.add(lb1);
		PBaja.add(TxtCuentaB);
		PBaja.add(TxtDatosB);
		PBaja.add(BtnBorrar);
	}
	
	private void generaPConsulta() {
		PConsulta.setLayout(null);
		BtnBuscar=new JButton("Buscar");
		BtnBuscar.setFont(F);
		TxtCuentaCon=new JCuentaField();
		TxtCuentaCon.setFont(FN);
		JLabel lb1=new JLabel("Buscar cuenta:",SwingConstants.RIGHT);
		lb1.setFont(F);
		
		lb1.setBounds(40,3,120,35);
		TxtCuentaCon.setBounds(165,3,90,35);
		BtnBuscar.setBounds(260,3,120,35);
		
		PConsulta.add(lb1);
		PConsulta.add(TxtCuentaCon);
		PConsulta.add(BtnBuscar);
	}
	
	public void ActualizaPConsulta(String[][] filas) {
		if(sp!=null)
			PConsulta.remove(sp);
		String[] columnas= {"Número","Nombre","Saldo","Cargo","Abono","Activa"};
		JTConsulta=new JTable(filas,columnas);
		JTConsulta.setFont(FN);
		TableColumn colnombre= JTConsulta.getColumn("Nombre");
		TableColumn colnumero= JTConsulta.getColumn("Número");
		TableColumn colsaldo= JTConsulta.getColumn("Saldo");
		TableColumn colcargo= JTConsulta.getColumn("Cargo");
		TableColumn colabono= JTConsulta.getColumn("Abono");
		TableColumn colactivo= JTConsulta.getColumn("Activa");
		JTConsulta.setEnabled(false);
		colnombre.setPreferredWidth(180);
		colnombre.setResizable(false);
		colnumero.setResizable(false);
		colsaldo.setResizable(false);
		colcargo.setResizable(false);
		colabono.setResizable(false);
		colactivo.setResizable(false);
		JTConsulta.getTableHeader().setReorderingAllowed(false);
		sp=new JScrollPane(JTConsulta);
		sp.setBounds(0,40,440,170);
		PConsulta.add(sp);
	}
	
}
