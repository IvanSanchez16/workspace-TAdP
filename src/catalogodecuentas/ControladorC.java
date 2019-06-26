package catalogodecuentas;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class ControladorC implements ActionListener,MouseListener{
	
	Vista vista;
	ModeloC modelo;
	public ControladorC(Vista v,ModeloC m) {
		vista=v;
		modelo=m;
		vista.JTPCatalogo.LlenaComboBox( modelo.ObtenerCuentas() );
		vista.JTPPolizas.LlenaComboBox( modelo.ObtenerSubSubCuentas() );
		vista.JTPCatalogo.ActualizaPConsulta(modelo.ObtenerCuentasMatriz());
	}
	
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() instanceof JButton) {
			eventosBtn((JButton) evt.getSource());
			return;
		}
		if(evt.getSource() instanceof JComboBox) {
			eventosCombo((JComboBox)evt.getSource());
			return;
		}
		if(evt.getSource() instanceof JTextField) {
			eventosTxt((JTextField)evt.getSource());
			return;
		}
	}

	public void mouseClicked(MouseEvent evt) {
			vista.setJTabbedPane(true);
	}

	private void eventosCombo(JComboBox Cbox) {
		if(Cbox==vista.JTPCatalogo.TxtCuentaM) {
			String nc=(String) vista.JTPCatalogo.TxtCuentaM.getSelectedItem();
			Cuenta caux=modelo.ObtenerCuenta(nc);
			if(caux!=null)
				vista.JTPCatalogo.TxtNombreM.setText(caux.getNombre());
			return;
		}
		if(Cbox==vista.JTPCatalogo.TxtCuentaB) {
			String nc=(String) vista.JTPCatalogo.TxtCuentaB.getSelectedItem();
			Cuenta caux=modelo.ObtenerCuenta(nc);
			if(caux!=null)
				vista.JTPCatalogo.TxtDatosB.setText(vista.JTPCatalogo.txtArea(caux));
		}
	}
	
	private void eventosBtn(JButton btn) {
		if(btn==vista.JTPCatalogo.BtnGrabar) {
			String nc=vista.JTPCatalogo.TxtCuenta.ObtenerNumero();
			String nom=vista.JTPCatalogo.TxtNombre.getText();
			String ss=vista.JTPCatalogo.TxtSaldo.getText();
			if(nc.equals("") || nom.equals("") || ss.equals("")) {
				vista.MostrarJOError("Error de captura","Llene todo los campos");
				return;
			}
			if(!modelo.validarCuenta(nc)) {
				vista.MostrarJOError("Error de captura","Número de cuenta ya existente ó sin cuenta mayor");
				return;
			}
			int s=(int) vista.JTPCatalogo.TxtSaldo.ObtenerCantidad();
			modelo.AñadirCuenta(nc,nom,s);
			vista.JTPCatalogo.TxtCuenta.setText("");
			vista.JTPCatalogo.TxtNombre.setText("");
			vista.JTPCatalogo.TxtSaldo.setText("");
			vista.JTPCatalogo.LlenaComboBox( modelo.ObtenerCuentas() );
			vista.JTPPolizas.LlenaComboBox( modelo.ObtenerSubSubCuentas() );
			vista.JTPCatalogo.ActualizaPConsulta(modelo.ObtenerCuentasMatriz());
			return;
		}
		if(btn==vista.JTPCatalogo.BtnLimpiar) {
			vista.JTPCatalogo.TxtCuenta.setText("");
			vista.JTPCatalogo.TxtNombre.setText("");
			vista.JTPCatalogo.TxtSaldo.setText("");
			return;
		}
		if(btn==vista.JTPCatalogo.BtnGuardar) {
			String newnombre=vista.JTPCatalogo.TxtNewNombre.getText();
			if(newnombre.equals("") || newnombre.equals(vista.JTPCatalogo.TxtNombreM.getText())) {
				vista.MostrarJOError("Error de captura","Ingrese un nuevo nombre válido");
				return;
			}
			modelo.ModificarNombre((String)vista.JTPCatalogo.TxtCuentaM.getSelectedItem(),newnombre);
			vista.JTPCatalogo.ActualizaPConsulta(modelo.ObtenerCuentasMatriz());
			vista.JTPCatalogo.LlenaComboBox( modelo.ObtenerCuentas() );
			vista.JTPPolizas.LlenaComboBox( modelo.ObtenerSubSubCuentas() );
			vista.JTPCatalogo.TxtNewNombre.setText("");
			return;
		}
		if(btn==vista.JTPCatalogo.BtnBorrar) {
			if(vista.MostrarJOYoN("Borrar registro","¿Seguro que quiere eliminarla?")==JOptionPane.YES_OPTION) {
				String nc=(String)vista.JTPCatalogo.TxtCuentaB.getSelectedItem();
				modelo.DarBaja(nc);
				vista.JTPCatalogo.ActualizaPConsulta(modelo.ObtenerCuentasMatriz());
				vista.JTPCatalogo.LlenaComboBox( modelo.ObtenerCuentas() );
				vista.JTPPolizas.LlenaComboBox( modelo.ObtenerSubSubCuentas() );
				return;
			}
		}
		if(btn==vista.JTPCatalogo.BtnBuscar) {
			String nc=vista.JTPCatalogo.TxtCuentaCon.ObtenerNumero();
			Cuenta c=modelo.ObtenerCuenta(nc);
			if(c==null) {
				vista.MostrarJOError("Error de consulta","La cuenta no existe ó no se encuentra activa");
				return;
			}
			vista.MostrarCuenta(c);
			return;
		}
	}
	
	private void eventosTxt(JTextField txt) {
		if(txt==vista.JTPCatalogo.TxtCuenta) {
			if(vista.JTPCatalogo.TxtCuenta.ObtenerNumero().length()!=6) {
				vista.MostrarJOError("Error de captura","El número de cuenta debe cumplir el formato (##-##-##)");
				return;
			}
			vista.JTPCatalogo.TxtNombre.grabFocus();
			return;
		}
		if(txt==vista.JTPCatalogo.TxtNombre) {
			vista.JTPCatalogo.TxtSaldo.grabFocus();
			return;
		}
		if(txt==vista.JTPCatalogo.TxtSaldo) {
			eventosBtn(vista.JTPCatalogo.BtnGrabar);
			vista.JTPCatalogo.TxtCuenta.grabFocus();
			return;
		}
		if(txt==vista.JTPCatalogo.TxtNewNombre) {
			eventosBtn(vista.JTPCatalogo.BtnGuardar);
			return;
		}
	}
	
	public void AfectarCuentas(ArrayList<Poliza> ap) {
		Poliza p;
		int imp;
		for(int i=0 ; i<ap.size() ; i++) {
			p=ap.get(i);
			imp=p.getImporte();
			if(p.isTipo())
				modelo.AfectarCuentas(p.getSubSubCuenta(),imp,0); 
			else
				modelo.AfectarCuentas(p.getSubSubCuenta(),0,imp);
		}
		vista.JTPCatalogo.ActualizaPConsulta(modelo.ObtenerCuentasMatriz());
	}
	
	public void mouseEntered(MouseEvent evt) {}
	public void mouseExited(MouseEvent evt) {}
	public void mousePressed(MouseEvent evt) {}
	public void mouseReleased(MouseEvent evt) {}

	
}
