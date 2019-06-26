package catalogodecuentas;

public class Apl {

	public static void main(String[] args) {
		Vista vista=new Vista();
		ModeloC modelo=new ModeloC();
		ControladorC controlador=new ControladorC(vista,modelo);
		ModeloP modelo2=new ModeloP();
		ControladorP controlador2=new ControladorP(vista,modelo2,controlador);
		ControlDeEntradas controlador3=new ControlDeEntradas(vista.JTPCatalogo,vista.JTPPolizas);
		vista.setEscuchador(controlador,controlador2,controlador3);
	}

}
