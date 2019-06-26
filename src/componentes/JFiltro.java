package componentes;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class JFiltro extends JPanel implements KeyListener,ActionListener,FocusListener{

	private JComboBox<String> cbFiltro;
	private JButton btnOrd, btnNormal;
	private String[] AString;
	private String prefijo;
	private boolean orden;
	private JTextComponent txtCB;
	private Color CS,CNS,CF;
	
	public JFiltro(ArrayList<String> as) {
		this( (String[]) as.toArray() );
	}
	
	public JFiltro(Vector<String> as) {
		this( (String[]) as.toArray() );
	}
	
	public JFiltro(String[] as){
		hazInterfaz();
		AString=as;
		prefijo="";
		orden=true;
		CS=Color.GRAY;
		CNS=btnNormal.getBackground();
		btnNormal.setBackground(CS);
		btnNormal.setForeground(Color.WHITE);
		llenaCBOriginal();
	}

	public void addElement(String elem) {
		String[] aux=new String[AString.length+1];
		for(int i=0 ; i<AString.length ; i++)
			aux[i]=AString[i];
		aux[AString.length]=elem;
		AString=aux;
		if(orden) {
			llenaCBOriginal();
			return;
		}
		llenaCBOrdenado();
	}
	
	public void clearComboBox() {
		cbFiltro.removeAllItems();
		AString=new String[0];
	}
	
	public void setArrayElement(String[] as) {
		AString=as;
		if(orden) {
			llenaCBOriginal();
			return;
		}
		llenaCBOrdenado();
	}
	
	public String getSelectedItem() {
		return (String) cbFiltro.getSelectedItem();
	}

	public int getSelectedIndex() {
		return cbFiltro.getSelectedIndex();
	}
	
	public void setPressedColor(Color colorBtn) {
		CS=colorBtn;
		int promCol=(colorBtn.getBlue()+colorBtn.getGreen()+colorBtn.getRed())/3;
		if(promCol>120) 
			CF=Color.BLACK;
		else
			CF=Color.WHITE;
		if(orden) {
			btnNormal.setBackground(CS);
			btnNormal.setForeground(CF);
		}else {
			btnOrd.setBackground(CS);
			btnOrd.setForeground(CF);
		}
	}
	
	public void setPressedColor(Color colorBtn,Color colorFont) {
		CS=colorBtn;
		CF=colorFont;
		if(orden) {
			btnNormal.setBackground(CS);
			btnNormal.setForeground(CF);
		}else {
			btnOrd.setBackground(CS);
			btnOrd.setForeground(CF);
		}
	}
	
	public void keyPressed(KeyEvent evt) {
		if(evt.isActionKey()) 
			return;
		if(evt.getKeyChar()==KeyEvent.VK_BACK_SPACE) {
			if(prefijo.length()>0)
				prefijo=prefijo.substring(0,prefijo.length()-1);
			return;
		}
		prefijo+=evt.getKeyChar();
	}
	
	public void keyReleased(KeyEvent evt) {
		if(orden) 
			llenaCBOriginal();
		else
			llenaCBOrdenado();
		cbFiltro.setPopupVisible(false);
		cbFiltro.setPopupVisible(true);
		txtCB.setText(prefijo);
	}

	public void actionPerformed(ActionEvent evt) {
		if(!orden && evt.getSource()==btnNormal) {
			orden=true;
			btnNormal.setBackground(CS);
			btnNormal.setForeground(CF);
			btnOrd.setBackground(CNS);
			btnOrd.setForeground(Color.BLACK);
			llenaCBOriginal();
			return;
		}
		if(orden && evt.getSource()==btnOrd) {
			orden=false;
			btnNormal.setBackground(CNS);
			btnNormal.setForeground(Color.BLACK);
			btnOrd.setBackground(CS);
			btnOrd.setForeground(CF);
			llenaCBOrdenado();
			return;
		}
	}
	
	private void llenaCBOriginal() {
		cbFiltro.removeAllItems();
		String aux;
		for(int i=0 ; i<AString.length ; i++) {
			aux=AString[i];
			if(aux.toLowerCase().startsWith(prefijo.toLowerCase()))
				cbFiltro.addItem(aux);
		}
		txtCB.setText(prefijo);
	}
	
	private void llenaCBOrdenado() {
		String[] as=AString.clone();
		String aux;
		Arrays.sort(as);
		cbFiltro.removeAllItems();
		for(int i=0 ; i<as.length ; i++) {
			aux=as[i];
			if(aux.toLowerCase().startsWith(prefijo.toLowerCase()))
				cbFiltro.addItem(aux);
		}
		txtCB.setText(prefijo);
 	}

	private void hazInterfaz(){
		setLayout(new GridBagLayout());

		cbFiltro = new JComboBox<String>();
		cbFiltro.setEditable(true);
		txtCB=(JTextComponent) cbFiltro.getEditor().getEditorComponent();
		txtCB.addKeyListener(this);
		txtCB.addFocusListener(this);
		GridBagConstraints c1 = new GridBagConstraints();
		
		btnNormal = new JButton("Ori");
		btnNormal.setFont(new Font("Console",1,8));
		btnNormal.addActionListener(this);
		GridBagConstraints c2 = new GridBagConstraints();

		btnOrd = new JButton("Ord");
		btnOrd.setFont(new Font("Console",1,8));
		btnOrd.addActionListener(this);
		GridBagConstraints c3 = new GridBagConstraints();

		c1.gridheight=2;
		c1.gridwidth=5;
		c1.gridx = 0;
		c1.gridy = 0;
		c1.ipadx = 0;
		c1.ipady = 0;
		c1.fill=GridBagConstraints.VERTICAL;
		add(cbFiltro,c1);
		
		c2.gridheight=1;
		c2.gridwidth=1;
		c2.gridx = 5;
		c2.gridy = 0;
		c2.ipady = 0;
		c2.ipadx=3;
		add(btnNormal,c2);
		
		c3.gridheight=1;
		c3.gridwidth=1;
		c3.gridx = 5;
		c3.gridy = 1;
		c3.ipady = 0;
		add(btnOrd,c3);
	}

	public void keyTyped(KeyEvent evt) {}

	public void focusGained(FocusEvent evt) {
		cbFiltro.showPopup();
	}

	public void focusLost(FocusEvent arg0) {}

}
