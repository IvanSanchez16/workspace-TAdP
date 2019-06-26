package catalogodecuentas;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class ControladorP implements ActionListener,MouseListener{
	
	Vista vista;
	ModeloP modelo;
	ControladorC C;
	
	public ControladorP(Vista v,ModeloP m,ControladorC c) {
		vista=v;
		modelo=m;
		C=c;
		vista.JTPPolizas.ActualizaPConsulta(modelo.ObtenerPolizasMatriz());
		vista.JTPPolizas.LlenaComboBoxPolizas( modelo.ObtenerPolizas() );
		int np=(int) vista.JTPPolizas.CBoxPolizas.getSelectedItem();
		vista.JTPPolizas.DefineTextArea( modelo.ObtenerPolizaCompleta(np) );
	}
	
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() instanceof JButton) {
			eventosBtn((JButton)evt.getSource());
			return;
		}
		if(evt.getSource()==vista.JTPPolizas.CBoxPolizas && vista.JTPPolizas.CBoxPolizas.getItemCount()!=0) {
			int np=(int) vista.JTPPolizas.CBoxPolizas.getSelectedItem();
			vista.JTPPolizas.DefineTextArea( modelo.ObtenerPolizaCompleta(np) );
			return;
		}
	}

	private void eventosBtn(JButton btn) {
		if(btn==vista.JTPPolizas.BtnAggInfo) {
			String nopoliza=vista.JTPPolizas.TxtNPoliza.getText();
			String ssc=(String)vista.JTPPolizas.TxtSSCuenta.getSelectedItem();
			String importe=vista.JTPPolizas.TxtImporte.getText();
			if(nopoliza.equals("") || ssc==null || importe.equals("")) {
				vista.MostrarJOError("Error en captura","Llene todos los campos");
				return;
			}
			int np=Integer.parseInt(nopoliza);
			int imp=Integer.parseInt(importe);
			boolean tipo=vista.JTPPolizas.RBtnC.isSelected();
			if(!modelo.AgregarPoliza(np, ssc, imp, tipo)) {
				vista.MostrarJOError("Error en captura","El número de póliza ya está en uso");
				return;
			}
			vista.JTPPolizas.aggTextArea(new Poliza(np,ssc,imp,tipo));
			vista.JTPPolizas.TxtImporte.setText("");
			vista.JTPPolizas.TxtNPoliza.setEditable(false);
			return;
		}
		if(btn==vista.JTPPolizas.BtnGrabar) {
			if((vista.JTPPolizas.cargo != vista.JTPPolizas.abono) || vista.JTPPolizas.cargo==0) {
				JOptionPane.showMessageDialog(null, "Cargo y Abono deben ser iguales y diferentes a 0","Error de captura", JOptionPane.WARNING_MESSAGE);
				return;
			}
			modelo.RegistrarPolizas();
			modelo.AP.clear();
			vista.JTPPolizas.abono=0;
			vista.JTPPolizas.cargo=0;
			vista.JTPPolizas.LbCargo.setText("Cargo: 0");
			vista.JTPPolizas.LbAbono.setText("Abono: 0");
			vista.JTPPolizas.TxtArea.setText("No Póliza\tSubSubCuenta\tTipo\tImporte\n");
			vista.JTPPolizas.TxtNPoliza.setText("");
			vista.JTPPolizas.TxtNPoliza.setEditable(true);
			vista.JTPPolizas.TxtImporte.setText("");
			vista.JTPPolizas.ActualizaPConsulta(modelo.ObtenerPolizasMatriz());
			vista.JTPPolizas.LlenaComboBoxPolizas( modelo.ObtenerPolizas() );
			return;
		}
		if(btn==vista.JTPPolizas.BtnLimpiar) {
			modelo.AP=new ArrayList<Poliza>();
			vista.JTPPolizas.abono=0;
			vista.JTPPolizas.cargo=0;
			vista.JTPPolizas.LbCargo.setText("Cargo: 0");
			vista.JTPPolizas.LbAbono.setText("Abono: 0");
			vista.JTPPolizas.TxtArea.setText("No Poliza\tSubSubCuenta\tImporte\tTipo\n");
			vista.JTPPolizas.TxtNPoliza.setText("");
			vista.JTPPolizas.TxtImporte.setText("");
			vista.JTPPolizas.TxtNPoliza.setEditable(true);
			return;
		}
		if(btn==vista.JTPPolizas.BtnGuardar) {
			int np=(int) vista.JTPPolizas.CBoxPolizas.getSelectedItem();
			C.AfectarCuentas(modelo.ObtenerPolizaCompleta(np));
			return;
		}
	}
	
	public void mouseClicked(MouseEvent arg0) {
		vista.setJTabbedPane(false);
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
