package memorama;

public class Principal {
	
	static int Pares;
	public static void main(String[] args) {
		Pares=10;
		Ventana v1=new Ventana(Pares);
		Escuchador e=new Escuchador(v1);
		v1.setEscuchador(e);
	}

}
