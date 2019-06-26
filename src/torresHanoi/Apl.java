package torresHanoi;

public class Apl {
	
	public static void main(String[] args) {
		Vista v=new Vista();
		Modelo m=new Modelo();
		Controlador c=new Controlador(v,m);
		v.setEscuchador(c);
		m.CrearJuego(3);
		v.AsignaArreglo(m.ADiscos);
	}
	
}
