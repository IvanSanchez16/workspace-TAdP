package componentes;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

public class CajasMultiples extends JPanel implements ActionListener{

	private JRadioButton rbtnCorreo,rbtnRFC,rbtnTelefono;
	private JButton btnNCaja;
	private ArrayList<SubComponente> ac;
	private JPanel PCajas,p2;
	private JScrollPane sp;
	private final String expCorreo="^[a-zA-Z0-9_-]+@+[a-zA-Z]+\\.[a-z||A-Z]{2,3}$",
						 expRFC="^[A-Z]{4}[0-9]{6}[A-Z0-9]{3}$",
						 expTel="^[0-9]{1,3}[ ]{1}[0-9]{10}$";
	
	public CajasMultiples() {
		ac=new ArrayList<SubComponente>();
		creaInterfaz();
		hazEscuchas();
	}

	public String getElementAt(int index) {
		return ac.get(index).getText();
	}
	
	public String[] getAllTexts() {
		String []aux=new String[ac.size()];
		for(int i=0 ; i<aux.length ; i++) 
			aux[i]=ac.get(i).getText();
		return aux;
	}
	
	public int arraysSize() {
		return ac.size();
	}
	
	public boolean isSelectedCorreo() {
		return rbtnCorreo.isSelected();
	}
	
	public boolean isSelectedRFC() {
		return rbtnRFC.isSelected();
	}
	
	public boolean isSelectedTelefono() {
		return rbtnTelefono.isSelected();
	}
	
	public void setSize(int x,int y) {
		super.setSize(x,y);
		sp.setPreferredSize(new Dimension(getWidth(),getHeight()-(p2.getHeight()+5)));
	}
	
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() instanceof JRadioButton) {
			JRadioButton rbtn = (JRadioButton)evt.getSource();
			if(rbtn==rbtnCorreo) {
				SubComponente.setExpresion(expCorreo);
				return;
			}
			if(rbtn==rbtnRFC) {
				SubComponente.setExpresion(expRFC);
				return;	
			}
			if(rbtn==rbtnTelefono) {
				SubComponente.setExpresion(expTel);
				return;
			}
			return;
		}
		JButton aux=(JButton)evt.getSource();
		if(aux==btnNCaja) {
			if(!validarExpresion()) {
				JOptionPane.showMessageDialog(null,"Uno o más campos no cumplen la estructura seleccionada","Error", JOptionPane.WARNING_MESSAGE);
				return;
			}
			SubComponente sb = new SubComponente(this); 
			ac.add(sb);
			insertaCajas();
			updateUI();
			rbtnCorreo.setEnabled(false);
			rbtnRFC.setEnabled(false);
			rbtnTelefono.setEnabled(false);
			return;
		}
		for(int i=0 ; i<ac.size() ; i++) {
			if(aux==ac.get(i).getBtnBorrar()) {
				ac.remove(i);
				insertaCajas();
				updateUI();
				if(ac.size()==0) {
					rbtnCorreo.setEnabled(true);
					rbtnRFC.setEnabled(true);
					rbtnTelefono.setEnabled(true);
				}
				return;
			}
		}
	}
	
	private void insertaCajas() {
		PCajas.removeAll();
		for(int i=0 ; i<ac.size() ; i++){
			ac.get(i).setBounds(5,(25*i)+(5*(i+1)),PCajas.getWidth()-30,25);
			PCajas.add(ac.get(i));
		}
		PCajas.setPreferredSize(new Dimension(PCajas.getWidth()-20,ac.size()*30));
	}
	
	private void creaInterfaz() {
		setLayout(new BorderLayout());
		PCajas = new JPanel();
		PCajas.setLayout(null);
		PCajas.setBorder(BorderFactory.createLineBorder(Color.black));
		p2 = new JPanel();
		p2.setLayout(new GridLayout(0,3,5,5));
		
		ButtonGroup gb=new ButtonGroup();
		rbtnCorreo=new JRadioButton("Correo",true);
		SubComponente.setExpresion(expCorreo);
		rbtnRFC=new JRadioButton("RFC");
		rbtnTelefono=new JRadioButton("Teléfono");
		gb.add(rbtnCorreo);
		gb.add(rbtnRFC);
		gb.add(rbtnTelefono);
		btnNCaja=new JButton("Nueva Caja");
		btnNCaja.setFont(new Font("Console",1,11));
		p2.add(rbtnCorreo);
		p2.add(rbtnRFC);
		p2.add(rbtnTelefono);
		p2.add(btnNCaja);
		
		sp=new JScrollPane(PCajas);
		add(p2,BorderLayout.NORTH);
		add(sp,BorderLayout.CENTER);
	}
	
	private void hazEscuchas() {
		rbtnCorreo.addActionListener(this);
		rbtnRFC.addActionListener(this);
		rbtnTelefono.addActionListener(this);
		btnNCaja.addActionListener(this);
	}
	
	private boolean validarExpresion() {
		for(int i=0 ; i<ac.size() ; i++)
			if(!ac.get(i).validarExpresion())
				return false;
		return true;
	}
}
