package componentes;

public class Municipio {
	private int idEdo;
	private int id;
	private String nombre;
	static int LARGO=60;
	
	public Municipio(int ie,int i,String n) {
		idEdo=ie;
		id=i;
		nombre=n;
	}

	public int getIdEdo() {
		return idEdo;
	}

	public void setIdEdo(int idEdo) {
		this.idEdo = idEdo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
