package plantaEnsambladora;

public class Auto {
	private int x;
	private int y;
	private float progreso;
	private int estado;
	private int numero;
	
	public Auto(int x, int y) {
		this.x = x;
		this.y = y;
		this.progreso = 0;
		estado=0;
		numero=0;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void avanzar() {
		x+=2;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public float getProgreso() {
		return progreso;
	}
	public void setProgreso(float progreso) {
		this.progreso = progreso;
	}
	public void avanzaProgreso(float p) {
		progreso+=p;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado=estado;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
}
