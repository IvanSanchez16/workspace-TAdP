package catalogodecuentas;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.TableColumn;

import utilería.JIntegerBox;

public class JTabbedPanePolizas extends JTabbedPane{
	
	JPanel PAgg,PMod,PConsulta;
	JTextArea TxtArea,TxtAreaA;
	ControladorP C;
	JButton BtnGrabar,BtnAggInfo,BtnLimpiar,BtnGuardar;
	JRadioButton RBtnC,RBtnA;
	ButtonGroup GB;
	JIntegerBox TxtImporte,TxtNPoliza;
	JComboBox<String> TxtSSCuenta;
	JComboBox<Integer> CBoxPolizas;
	int abono,cargo;
	JLabel LbCargo,LbAbono;
	JTable JTConsulta;
	JScrollPane sp;
	private Font F,F2,FN; 

	public JTabbedPanePolizas() {
		F=new Font("Candara",1,15);
		F2=new Font("Candara",0,15);
		FN=new Font("Dubai",0,15);
		setFont(F2);
		PAgg=new JPanel();
		generaPAgg();
		PMod=new JPanel();
		generaPMod();
		PConsulta=new JPanel();
		PConsulta.setLayout(null);
		add(PAgg,"Agregar póliza");
		add(PMod,"Afectación");
		add(PConsulta,"Consultar");
		setVisible(false);
		setPreferredSize(new Dimension(440,241));
	}
	
	public void setEscuchador(ControladorP c) {
		C=c;
		BtnGuardar.addActionListener(C);
		BtnGrabar.addActionListener(C);
		BtnAggInfo.addActionListener(C);
		BtnLimpiar.addActionListener(C);
		CBoxPolizas.addActionListener(C);
	}
	
	public void aggTextArea(Poliza p) {
		String aux=p.toString()+"\n";
		TxtArea.setText(TxtArea.getText()+aux);
		if(p.isTipo()) {
			cargo+=p.getImporte();
			LbCargo.setText("Cargo: "+cargo);
		}else {
			abono+=p.getImporte();
			LbAbono.setText("Abono: "+abono);
		}
	}
	
	public void LlenaComboBox(ArrayList<Cuenta> ac) {
		TxtSSCuenta.removeAllItems();
		for(int i=0 ; i<ac.size() ; i++) 
			TxtSSCuenta.addItem(ac.get(i).getNoCuenta());			
	}
	
	private void generaPAgg() {
		PAgg.setLayout(null);
		JLabel lb1=new JLabel("No de Póliza:",SwingConstants.RIGHT);
		lb1.setFont(F);
		JLabel lb2=new JLabel("SubSubCuenta:",SwingConstants.RIGHT);
		lb2.setFont(F);
		JLabel lb3=new JLabel("Importe:",SwingConstants.RIGHT);
		lb3.setFont(F);
		LbCargo=new JLabel("Cargo: 0");
		LbCargo.setFont(FN);
		LbAbono=new JLabel("Abono: 0");
		LbAbono.setFont(FN);
		RBtnC=new JRadioButton("Cargo",true);
		RBtnC.setFont(F);
		RBtnA=new JRadioButton("Abono");
		RBtnA.setFont(F);
		GB=new ButtonGroup();
		GB.add(RBtnC);
		GB.add(RBtnA);
		BtnGrabar=new JButton("Guardar Póliza");
		BtnGrabar.setFont(F);
		BtnAggInfo=new JButton("Grabar");
		BtnAggInfo.setFont(F);
		BtnLimpiar=new JButton("Limpiar");
		BtnLimpiar.setFont(F);
		TxtArea=new JTextArea();
		TxtArea.setFont(F2);
		TxtArea.setText("No Póliza\tSubSubCuenta\tImporte\tTipo\n");
		TxtArea.setEditable(false);
		TxtImporte=new JIntegerBox();
		TxtImporte.setFont(FN);
		TxtNPoliza=new JIntegerBox();
		TxtNPoliza.setFont(FN);
		TxtSSCuenta=new JComboBox<String>();
		TxtSSCuenta.setFont(FN);
		JScrollPane PS=new JScrollPane(TxtArea);
		
		lb1.setBounds(3,3,90,27);
		TxtNPoliza.setBounds(93,3,150,27);
		lb2.setBounds(3,31,100,27);
		TxtSSCuenta.setBounds(106,31,150,27);
		RBtnC.setBounds(3,57,60,27);
		RBtnA.setBounds(75,57,65,27);
		lb3.setBounds(3,85,60,27);
		TxtImporte.setBounds(66,85,150,27);
		BtnGrabar.setBounds(290,6,140,33);
		BtnLimpiar.setBounds(290,39,140,33);
		BtnAggInfo.setBounds(290,73,140,33);
		PS.setBounds(3,110,430,80);
		LbCargo.setBounds(220,182,80,30);
		LbAbono.setBounds(320,182,80,30);
		PAgg.add(lb1);
		PAgg.add(TxtNPoliza);
		PAgg.add(lb2);
		PAgg.add(TxtSSCuenta);
		PAgg.add(RBtnC);
		PAgg.add(RBtnA);
		PAgg.add(lb3);
		PAgg.add(TxtImporte);
		PAgg.add(BtnGrabar);
		PAgg.add(BtnAggInfo);
		PAgg.add(PS);
		PAgg.add(LbCargo);
		PAgg.add(LbAbono);
		PAgg.add(BtnLimpiar);
	}
	
	private void generaPMod() {
		PMod.setLayout(null);
		JLabel lb1=new JLabel("Número de póliza:",SwingConstants.RIGHT);
		lb1.setFont(F);
		CBoxPolizas=new JComboBox<Integer>();
		CBoxPolizas.setFont(FN);
		TxtAreaA=new JTextArea();
		TxtAreaA.setFont(F2);
		JScrollPane sp=new JScrollPane(TxtAreaA);
		BtnGuardar=new JButton("Afectar Cuentas");
		BtnGuardar.setFont(F);
		
		lb1.setBounds(70,5,120,30);
		CBoxPolizas.setBounds(195,5,150,30);
		sp.setBounds(5,35,425,140);
		BtnGuardar.setBounds(5,175,425,30);
		
		PMod.add(lb1);
		PMod.add(CBoxPolizas);
		PMod.add(sp);
		PMod.add(BtnGuardar);
	}
	
	public void ActualizaPConsulta(String[][] filas) {
		if(sp!=null)
			PConsulta.remove(sp);
		String[] columnas= {"Número","SubSubCuenta","Importe","Tipo"};
		JTConsulta=new JTable(filas,columnas);
		TableColumn colnumero= JTConsulta.getColumn("Número");
		TableColumn colsscuenta= JTConsulta.getColumn("SubSubCuenta");
		TableColumn colimporte= JTConsulta.getColumn("Importe");
		TableColumn coltipo= JTConsulta.getColumn("Tipo");
		JTConsulta.setEnabled(false);
		colnumero.setResizable(false);
		colsscuenta.setResizable(false);
		colimporte.setResizable(false);
		coltipo.setResizable(false);
		JTConsulta.getTableHeader().setReorderingAllowed(false);
		JTConsulta.setFont(FN);
		sp=new JScrollPane(JTConsulta);
		sp.setBounds(0,0,440,200);
		PConsulta.add(sp);
	}

	public void LlenaComboBoxPolizas(ArrayList<Poliza> ap) {
		CBoxPolizas.removeAllItems();
		for(int i=0 ; i<ap.size() ; i++) 
			CBoxPolizas.addItem(ap.get(i).getNoPoliza());
	}

	public void DefineTextArea(ArrayList<Poliza> ap) {
		TxtAreaA.setText("# de póliza\tSubSubCuenta\tImporte\tTipo");
		for(int i=0 ; i<ap.size() ; i++) 
			TxtAreaA.setText(TxtAreaA.getText()+"\n"+ap.get(i));
	}
}
