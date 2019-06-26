package examenes;

public class Apl {

	public static void main(String[] args) {
		Ventana vista=new Ventana();
		Controlador c=new Controlador(vista);
		vista.setEscuchador(c);
	}

}
