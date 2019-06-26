package catalogodecuentas;

public class Poliza {
	private String SubSubCuenta;
	private int NoPoliza,Importe;
	private boolean Tipo;
	static final int LARGO=17;
	
	public Poliza(int np,String ssc,int i,boolean coa) {
		SubSubCuenta=ssc;
		NoPoliza=np;
		Importe=i;
		Tipo=coa;
	}

	public String getSubSubCuenta() {
		return SubSubCuenta;
	}

	public int getNoPoliza() {
		return NoPoliza;
	}

	public void setImporte(int importe) {
		Importe=importe;
	}
	
	public int getImporte() {
		return Importe;
	}

	public boolean isTipo() {
		return Tipo;
	}

	public String toString() {
		return NoPoliza + "\t" + SubSubCuenta + "\t" + Importe + "\t"+ (Tipo?'C':'A');
	}

	
	
	
}
