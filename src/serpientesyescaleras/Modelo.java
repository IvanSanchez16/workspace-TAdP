package serpientesyescaleras;

import java.awt.Color;

import javax.swing.JOptionPane;

import utilería.ListaDBL;
import utilería.NodoDBL;
import utilería.Rutinas;

public class Modelo {
	ListaDBL<Casilla> L = new ListaDBL<Casilla>();	
	Jugador[] Jug;
	
	public int TiroDado() {
		return Rutinas.nextInt(1,6);
	}
	
	public int IniciarJuego() {
		int njug=2;
		while(true) {
			try {
				njug=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el número de jugadores"));
				if(njug<=1 || njug>10) {
					JOptionPane.showMessageDialog(null, "Ingrese un número entre 2 y 10","Error de captura", JOptionPane.WARNING_MESSAGE);
					continue;
				}
				break;
			}catch (NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Ingresé un número","Error de captura", JOptionPane.WARNING_MESSAGE);
			}
		}
		CrearTablero();
		DefinirJugadores(njug);
		return njug;
	}
	
	public Casilla[] Retroceso(NodoDBL<Casilla> cinicial,int npasos) {
		Casilla[] tem=new Casilla[npasos];
		int i=0;
		for(; i<npasos ; i++) {
			cinicial=cinicial.getAnt();
			tem[i]=cinicial.Info;
		}
		return tem;
	}
	
	public Casilla[] Avance(NodoDBL<Casilla> cinicial,int npasos,boolean band) {
		boolean band2=false;
		Casilla[] tem=new Casilla[npasos];
		int i=0;
		if(band) {
			tem[0]=L.getFrente().Info;
			i++;
		}
		for(; i<npasos ; i++) {
			cinicial=(!band2?cinicial.getSig():cinicial.getAnt());
			tem[i]=cinicial.Info;
			if(cinicial.getSig()==null)
				band2=true;
		}
		return tem;
	}
	
	public boolean Gano(int pos) {
		return ObtenerNodo(Jug[pos].getPosicion()).getSig()==null;
	}
	
	public NodoDBL<Casilla> ObtenerNodo(Casilla c){
		NodoDBL<Casilla> aux=L.getFrente();
		while(aux.Info!=c)
			aux=aux.getSig();
		return aux;
	}
	
	public void DefinirJugadores(int njug) {
		Jug=new Jugador[njug];
		for(int i=0;i<njug;i++) 
			Jug[i]=new Jugador(i+1,Rutinas.AjustarImagen("dados/J.png",15,15));
	}
	
	public void CrearTablero() {
		L=new ListaDBL<Casilla>();
		for(byte i=1;i<=100;i++) 
			L.InsertaFin(new Casilla(i));
		for(byte i=0;i<5;i++)
			GenerarEscalera(i);
		for(byte i=0;i<5;i++)
			GenerarSerpiente(i);
	}
	
	private void GenerarEscalera(int n) {
		int Pos,Rango;
		NodoDBL<Casilla> Aux,Aux2;
		do {
			Pos=Rutinas.nextInt(15,70);
			Aux=L.getFrente();
			for(byte i=1;i<Pos; Aux=Aux.getSig() , i++);
		}while(Aux.Info.getTipoCasilla()!='N'); 
		
		Aux.Info.CambiarImagen("dados/escalera.png");
		Aux.Info.setTipoCasilla('E');
		Aux.Info.setBackground(Color.GREEN);
		Aux.Info.CambiaLabel(n+"");
		
		do {
			Aux2=Aux;
			Rango=Rutinas.nextInt(5,20);
			for(byte j=0;j<Rango; Aux2=Aux2.getSig() , j++);
		}while(Aux2.Info.getTipoCasilla()!='N');
		Aux.Info.setPosiciones(Rango);
		Aux2.Info.setTipoCasilla('T');
		Aux2.Info.setBackground(new Color(69, 132, 188));
		Aux2.Info.CambiaLabel(n+"");
		Aux2.Info.CambiarImagen("dados/escaleraT.png");
		
	}
	
	private void GenerarSerpiente(int n) {
		int Pos,Rango;
		NodoDBL<Casilla> Aux,Aux2;
		do {
		Pos=Rutinas.nextInt(30,95);
		Aux=L.getFrente();
		for(byte i=1;i<Pos; Aux=Aux.getSig() , i++);
		}while(Aux.Info.getTipoCasilla()!='N') ;
		
		Aux.Info.setTipoCasilla('S');
		Aux.Info.setBackground(new Color(245,72,57));
		Aux.Info.CambiaLabel(n+"");
		Aux.Info.CambiarImagen("dados/serpiente.png");
		
		do{
			Aux2=Aux;
			Rango=Rutinas.nextInt(5,20);
			for(byte j=0;j<Rango; Aux2=Aux2.getAnt() , j++);
		}while(Aux2.Info.getTipoCasilla()!='N');
		Aux.Info.setPosiciones(Rango);
		Aux2.Info.setTipoCasilla('T');
		Aux2.Info.setBackground(Color.ORANGE);
		Aux2.Info.CambiaLabel(n+"");
		Aux2.Info.CambiarImagen("dados/serpienteT.png");

	}
}
