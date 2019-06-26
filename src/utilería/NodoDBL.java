package utilería;

public class NodoDBL<T> {
	private NodoDBL<T> Ant;
	public T Info;
	private NodoDBL<T> Sig;
	
	public NodoDBL(T dato){
		Info=dato;
		Ant=Sig=null;
	}

	public NodoDBL<T> getAnt() {
		return Ant;
	}

	public void setAnt(NodoDBL<T> ant) {
		Ant = ant;
	}

	public NodoDBL<T> getSig() {
		return Sig;
	}

	public void setSig(NodoDBL<T> sig) {
		Sig = sig;
	}
	
	
}
