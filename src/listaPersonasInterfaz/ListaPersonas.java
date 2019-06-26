package listaPersonasInterfaz;


import utilería.ListaDBL;
import utilería.NodoDBL;
import utilería.Rutinas;

class Persona {
	private String Nombre;
	private int Edad;
	private int Estatura;
	static int Criterio;

	public Persona() {
		this("",0,0);
	}

	public Persona(String n,int e,int es) {
		Nombre=n;
		Edad=e;
		Estatura=es;
	}

	public String getNombre() {
		return Nombre;
	}

	public int getEdad() {
		return Edad;
	}

	public int getEstatura() {
		return Estatura;
	}

	public String toString() {
		switch(Criterio) {
		case 0:
			return Rutinas.PonBlancos(Nombre,30);
		case 1:
			return Rutinas.PonCeros(Edad,5);
		case 2: 
			return Rutinas.PonCeros(Estatura,3);
		default:
			return Rutinas.PonCeros(Edad,5)+Rutinas.PonCeros(Estatura,3)+Rutinas.PonBlancos(Nombre,30);
		}
	}
}
public class ListaPersonas {

	public static ListaDBL<Persona> L=new ListaDBL<Persona>();
	public static Persona P=new Persona();
	
	public static void Quicksort(int izq,int der) {
		NodoDBL<Persona> pivote=L.getFrente();

		for(int z=0;z<izq;z++)
			pivote=pivote.getSig();

		int i=izq;
		int j=der; 
		NodoDBL<Persona> nodoI=L.getFrente(),nodoD=L.getFrente();

		for(int a=0;a<i;a++,nodoI=nodoI.getSig());
		for(int b=0;b<j;b++,nodoD=nodoD.getSig());

		while(i<j){  
			while(nodoI.Info.toString().compareToIgnoreCase(pivote.Info.toString())<=0 && i<j) {
				i++;
				nodoI=nodoI.getSig();
			}
			while(nodoD.Info.toString().compareToIgnoreCase(pivote.Info.toString())>0) {
				j--;
				nodoD=nodoD.getAnt();
			}
			if (i<j) { 
				P = nodoI.Info;
				nodoI.Info=nodoD.Info;
				nodoD.Info=P;
			}
		}
		P = pivote.Info;
		pivote.Info=nodoD.Info;
		nodoD.Info=P;
		if(izq<j-1)
			Quicksort(izq,j-1); 
		if(j+1<der)
			Quicksort(j+1,der);
	}
	
	public static void Guardar(String n,int e,int es) {
		L.InsertaOrdenado(new Persona(n,e,es));
	}
	public static void main(String[] args) {
		Ventana Ventana=new Ventana();
	}

}
