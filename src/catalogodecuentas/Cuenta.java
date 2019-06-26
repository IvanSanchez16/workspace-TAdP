package catalogodecuentas;

public class Cuenta {
	private String NoCuenta,Nombre;
	private int Saldo,Cargo,Abono;
	private boolean Activo;
	public static int LARGO=65,LARGOI=12;
	
	
	public Cuenta(String nc,String n,int s){
		this(nc,n,s,0,0);
	}
	
	public Cuenta(String nc,String n,int s,int c,int a) {
		NoCuenta=nc;
		Nombre=n;
		Saldo=s;
		Cargo=c;
		Abono=a;
		Activo=true;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public int getCargo() {
		return Cargo;
	}

	public void setCargo(int cargo) {
		Cargo = cargo;
	}

	public int getAbono() {
		return Abono;
	}

	public void setAbono(int abono) {
		Abono = abono;
	}

	public String getNoCuenta() {
		return NoCuenta;
	}

	public int getSaldo() {
		return Saldo;
	}
	
	public boolean isActivo() {
		return Activo;
	}

	public void setActivo(boolean activo) {
		Activo = activo;
	}

	public String toString() {
		return NoCuenta+"\t"+Nombre.trim()+"\t"+Saldo+"\t"+Cargo+"\t"+Abono;
	}
}
