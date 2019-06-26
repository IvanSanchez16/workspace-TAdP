package torresHanoi;

public class Movimiento {
	
	private Disco disco;
	private Torre TInicial;
	private Torre TFinal;
	
	public Movimiento(Disco d,Torre ti,Torre tf) {
		disco=d;
		TInicial=ti;
		TFinal=tf;
	}

	public Disco getDisco() {
		return disco;
	}

	public Torre getTInicial() {
		return TInicial;
	}

	public Torre getTFinal() {
		return TFinal;
	}
	
	
}
