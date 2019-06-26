package torresHanoi;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JOptionPane;

import utilería.ListaDBL;
import utilería.NodoDBL;
import utilería.Rutinas;

public class Modelo {
	
	ListaDBL<Movimiento> L;
	Disco[] ADiscos;
	Torre[] ATorres;
	NodoDBL<Movimiento> MActual;
	
	public void CrearJuego(int ndiscos) {
		ADiscos=new Disco[ndiscos];
		for(int i=ndiscos;i>0;i--) {
			int ancho=(i*15)+5;
			int x=100-((ancho-30)/2);
			int y=255-((ndiscos-i)*15);
			ADiscos[i-1]=new Disco(x,y,ancho);
		}
		ATorres=new Torre[3];
		for(int j=0;j<3;j++) 
			ATorres[j]=new Torre(j);
		ATorres[0].setAlturaAct((15*ndiscos)+30);
		
		L=new ListaDBL<Movimiento>();
		Hanoi(ATorres[0],ATorres[1],ATorres[2],ndiscos);
		MActual=L.getFrente();
	}
	
	public void RealizaMov() {
		Disco disco=MActual.Info.getDisco();
		Torre tinicial=MActual.Info.getTInicial();
		Torre tfinal=MActual.Info.getTFinal();
		int dy=disco.getY(),dx=disco.getX(),da=disco.getAncho(),tfx=tfinal.getX(),tfa=tfinal.getAlturaAct(),tia=tinicial.getAlturaAct();
		if(dy!=5) {
			if(dx+(da/2) != tfx) {
				disco.setY(dy-5);
				return;
			}
			disco.setY(dy+5);
			if(dy+5==300-(tfa+15)) {
				tfinal.setAlturaAct(tfa+15);
				tinicial.setAlturaAct(tia-15);
				MActual=(MActual.getSig()!=null)?MActual.getSig():null;
				return;
			}
			return;
		}
		if(dx+(da/2) < tfx) {
			disco.setX(disco.getX()+5);
			return;
		}
		if(dx+(da/2) > tfx) {
			disco.setX(dx-5);
			return;
		}
		disco.setY(dy+5);
		return;
	}
	
	private void Hanoi(Torre Ini,Torre Cen,Torre Fin,int N){
		if(N==1){
			L.InsertaFin(new Movimiento(ADiscos[N-1],Ini,Fin));
			return;
		}
		Hanoi(Ini,Fin,Cen,N-1);
		L.InsertaFin(new Movimiento(ADiscos[N-1],Ini,Fin));
		Hanoi(Cen,Ini,Fin,N-1);
	}
	
}
