package serpientesyescaleras;

public class App {

	public static void main(String[] args) {
		Modelo modelo=new Modelo();
		VTablero vista=new VTablero();
		Controlador controlador=new Controlador(modelo,vista);
	}

}
