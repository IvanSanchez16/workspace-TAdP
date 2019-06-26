package componentes;

public class Ciudad {
	private int idEdo;
	private int idMun;
	private int idCiu;
	private String nombre;
	static final int LARGO=64;
	
	public Ciudad(int e,int m,int c,String n) {
		idEdo=e;
		idMun=m;
		idCiu=c;
		nombre=n;
	}

	public int getIdEdo() {
		return idEdo;
	}

	public int getIdMun() {
		return idMun;
	}

	public int getIdCiu() {
		return idCiu;
	}

	public String getNombre() {
		return nombre;
	}
	
	
}
