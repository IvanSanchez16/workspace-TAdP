package componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
 * Proyecto componentes - Evaluación para 2do parcial
 * 
 * Hecho por:
 * Iván Humberto Sánchez Aispuro y Ramón Jean Galeazzo Angulo Castro
 * Materia:
 * Tópicos Avanzados de Programación
 * Profesor:
 * Clemente Garcia Gerardo
 * 
 */


public class JTriComboBox extends JPanel implements ItemListener{
	private JLabel lbEdo,lbMun,lbCiu;
	private JComboBox<String> cboxEdo,cboxMun,cboxCiu;
	private RandomAccessFile archEdos,archMun,archCiu;
	
	public JTriComboBox() {
		this("","");
	}
	
	public JTriComboBox(String edo) {
		this(edo,"");
	}
	
	public JTriComboBox(String edo,String mun) {
		inicializaComponente();
		llenaCBEdo(edo);
		if(!mun.equals("")) {
			cboxMun.setSelectedItem(mun);
			cboxMun.setEnabled(false);
			return;
		}
		if(edo.equals("")) 
			cboxMun.setEnabled(false);
		cboxCiu.setEnabled(false);
	}

	public void itemStateChanged(ItemEvent evt) {
		if(evt.getStateChange()!=ItemEvent.SELECTED)
			return;
		if(evt.getSource()==cboxEdo) {
			cboxCiu.removeAllItems();
			if(evt.getItem().equals("Seleccione")) {
				cboxMun.removeAllItems();
				return;
			}
			llenaCBMun( cboxEdo.getSelectedIndex() );
			cboxMun.setEnabled(true);
			cboxCiu.setEnabled(false);
			return;
		}
		if(evt.getSource()==cboxMun) {
			if(evt.getItem().equals("Seleccione")) {
				cboxCiu.removeAllItems();
				return;
			}
			llenaCBCiu( cboxEdo.getSelectedIndex() , cboxMun.getSelectedIndex() );
			cboxCiu.setEnabled(true);
			return;
		}
	}
	
	public void setSelectedItemEstado(String edo) {
		cboxEdo.setSelectedItem(edo);
	}
	
	public void setSelectedItemMunicipio(String mun) {
		cboxMun.setSelectedItem(mun);
	}
	
	public void setSelectedItemCiudad(String ciu) {
		cboxCiu.setSelectedItem(ciu);
	}
	
	public void setSelectedIndexEstado(int edo) {
		cboxEdo.setSelectedIndex(edo);
	}
	
	public void setSelectedIndexMunicipio(int mun) {
		cboxMun.setSelectedIndex(mun);
	}
	
	public void setSelectedIndexCiudad(int ciu) {
		cboxCiu.setSelectedIndex(ciu);
	}
	
	public String getSelectedItemEstado() {
		String aux=(String) cboxEdo.getSelectedItem();
		if(aux!=null && aux.equals("Seleccione"))
			return "";
		return aux;
	}

	public String getSelectedItemMunicipio() {
		String aux=(String) cboxMun.getSelectedItem();
		if(aux!=null && aux.equals("Seleccione"))
			return "";
		return aux;
	}

	public String getSelectedItemCiudad() {
		String aux=(String) cboxCiu.getSelectedItem();
		if(aux!=null && aux.equals("Seleccione"))
			return "";
		return aux;
	}

	public int getSelectedIndexEstado() {
		return cboxEdo.getSelectedIndex();
	}
	
	public int getSelectedIndexMunicipio() {
		return cboxMun.getSelectedIndex();
	}
	
	public int getSelectedIndexCiudad() {
		return cboxCiu.getSelectedIndex();
	}
	
	public void setFont(Font font) {
		if(cboxEdo==null)
			return;
		lbEdo.setFont(font);
		lbMun.setFont(font);
		lbCiu.setFont(font);
		cboxEdo.setFont(font);
		cboxMun.setFont(font);
		cboxCiu.setFont(font);
	}
	
	public Font getFont() {
		if(cboxEdo==null)
			return null;
		return cboxEdo.getFont();
	}

	private void inicializaComponente() {
		setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		lbEdo=new JLabel("Estados: ");
		lbMun=new JLabel(" Municipios: ");
		lbCiu=new JLabel(" Ciudades: ");
		cboxEdo=new JComboBox<String>();
		cboxMun=new JComboBox<String>();
		cboxCiu=new JComboBox<String>();
		cboxEdo.addItemListener(this);
		cboxMun.addItemListener(this);
		cboxCiu.addItemListener(this);
		add(lbEdo);
		add(cboxEdo);
		add(lbMun);
		add(cboxMun);
		add(lbCiu);
		add(cboxCiu);
	}
	
	private void llenaCBEdo(String seleccionado) {
		cboxEdo.removeAllItems();
		ACarchE(true);
		try {
			archEdos.seek(0);
			int nedos=(int) (archEdos.length()/Estado.LARGO);
			cboxEdo.addItem("Seleccione");
			for(int i=0 ; i<nedos ; i++) {
				archEdos.readInt();
				cboxEdo.addItem( archEdos.readUTF().trim() );
			}
			if(!seleccionado.equals("")) {
				cboxEdo.setSelectedItem(seleccionado);
				cboxEdo.setEnabled(false);
			}
		}catch(Exception e) {}
		ACarchE(false);
	}
	
	private void llenaCBMun(int idedo) {
		cboxMun.removeAllItems();
		ACarchM(true);
		try {
			int nmun= (int) archMun.length()/Municipio.LARGO;
			archMun.seek( colocaApuntador(idedo,0,nmun) );
			int nedo=archMun.readInt();
			cboxMun.addItem("Seleccione");
			do {
				archMun.readInt();
				cboxMun.addItem( archMun.readUTF().trim() );
				nedo=archMun.readInt();
			}while(nedo==idedo);
		}catch(Exception e) {} 
		ACarchM(false);		
	}
	
	private void llenaCBCiu(int idedo,int idmun) {
		cboxCiu.removeAllItems();
		ACarchC(true);
		try {
			int nciu= (int) archCiu.length()/Ciudad.LARGO;
			archCiu.seek( colocaApuntador(idedo,idmun,0,nciu) );
			archCiu.readInt();
			int nmun=archCiu.readInt();
			cboxCiu.addItem("Seleccione");
			do {
				archCiu.readInt();
				cboxCiu.addItem( archCiu.readUTF().trim() );
				archCiu.readInt();
				nmun=archCiu.readInt();
			}while(nmun==idmun);
		}catch(Exception e) {};
	}
	
	private int colocaApuntador(int idedo,int izq,int der) throws IOException{
		int medio=(der+izq)/2;
		archMun.seek( medio*Municipio.LARGO );
		int nedo=archMun.readInt();
		if(idedo==nedo)
			return primerElemento(medio,idedo);
		if(nedo > idedo)
			return colocaApuntador(idedo,izq,medio);
		return colocaApuntador(idedo,medio,der);
	}

	private int primerElemento(int index,int idedo) throws IOException{
		if(index==0)
			return 0;
		archMun.seek( (index-1)*Municipio.LARGO );
		int nedo=archMun.readInt();
		if(nedo==idedo)
			return primerElemento(index-1,idedo);
		return index*Municipio.LARGO;
	}

	private int colocaApuntador(int idedo,int idmun,int izq,int der) throws IOException{
		int medio=(der+izq)/2;
		archCiu.seek( medio*Ciudad.LARGO );
		int nedo=archCiu.readInt();
		int nmun=archCiu.readInt();
		if(nedo==idedo && nmun==idmun)
			return primerElemento(medio,idedo,idmun);
		if(nedo > idedo)
			return colocaApuntador(idedo,idmun,izq,medio);
		if(nedo < idedo)
			return colocaApuntador(idedo,idmun,medio,der);
		if(nmun > idmun)
			return colocaApuntador(idedo,idmun,izq,medio);
		return colocaApuntador(idedo,idmun,medio,der);
	}

	private int primerElemento(int index,int idedo,int idmun) throws IOException{
		if(index==0)
			return 0;
		archCiu.seek( (index-1)*Ciudad.LARGO );
		archCiu.readInt();
		int nmun=archCiu.readInt();
		if(nmun==idmun)
			return primerElemento(index-1,idedo,idmun);
		return index*Ciudad.LARGO;
	}
	
	private void ACarchE(boolean aoc){
		try {
			if(aoc) {
				archEdos=new RandomAccessFile("Estados.dat","r");
				return;
			}
			archEdos.close();
		}catch(Exception e) {}
	}

	private void ACarchM(boolean aoc){
		try {
			if(aoc) {
				archMun=new RandomAccessFile("Municipios.dat","r");
				return;
			}
			archMun.close();
		}catch(Exception e) {}
	}
	
	private void ACarchC(boolean aoc){
		try {
			if(aoc) {
				archCiu=new RandomAccessFile("Ciudades.dat","r");
				return;
			}
			archCiu.close();
		}catch(Exception e) {}
	}
}
