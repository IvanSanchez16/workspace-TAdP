package utilería;

public class Pila <T> {
	private int Tope,Max;
	private T P[];
	public T Dr;
	
	public Pila(){
		this(10);
	}
	public Pila(int NE){
		Max=NE;
		Tope=-1;
		P=(T[])new Object[Max];
	}
	
	public boolean Inserta(T Dato){
		if(Llena())
			return false;
		P[++Tope]=Dato;
		return true;
	}
	
	public boolean Retira(){
		if(Vacia())
			return false;
		Dr=P[Tope];
		P[Tope]=null;
		Tope--;
		return true;
	}
	
	public boolean Vacia(){
		return Tope==-1;
	}
	
	public boolean Llena(){
		return Tope==Max-1;
	}
}
