package componentes;

public class Estado {
	private int id;
	private String nombre;
	static int LARGO=56;
	
	public Estado(int i,String n) {
		id=i;
		nombre=n;
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
