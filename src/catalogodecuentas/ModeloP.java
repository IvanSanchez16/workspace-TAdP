package catalogodecuentas;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import utilería.Rutinas;

public class ModeloP {
	
	final String POLIZA="Poliza.dat";
	RandomAccessFile archPolizas;
	ArrayList<Poliza> AP;
	
	public ModeloP() {
		AP=new ArrayList<Poliza>();
	}
	
	public boolean AgregarPoliza(int np,String nc,int imp,boolean t) {
		AbrirCerrarPoliza(true);
		try {
		if(!ExistePoliza(np)) {
			AP.add(new Poliza(np,nc,imp,t));
			AbrirCerrarPoliza(false);
			return true;
		}
		AbrirCerrarPoliza(false);
		return false;
		}catch(IOException e) {}
		AbrirCerrarPoliza(false);
		return false;
	}
	
	public void RegistrarPolizas() {
		AbrirCerrarPoliza(true);
		try {
			long largo=archPolizas.length();
			for(int i=0 ; i<AP.size() ; i++) {
				archPolizas.seek( largo + (i*Poliza.LARGO));
				archPolizas.writeInt( AP.get(i).getNoPoliza() );
				archPolizas.writeUTF( AP.get(i).getSubSubCuenta() );
				archPolizas.writeInt( AP.get(i).getImporte() );
				archPolizas.writeBoolean( AP.get(i).isTipo() );
			}
		}catch(IOException e) {}
		AbrirCerrarPoliza(false);
	}
	
	private boolean ExistePoliza(int np) throws IOException{
		int npolizas=(int) archPolizas.length()/Poliza.LARGO;
		for(int i=0 ; i<npolizas ; i++) {
			archPolizas.seek(i*Poliza.LARGO);
			if(np==archPolizas.readInt())
				return true;
		}
		return false;
	}
	
	public String[][] ObtenerPolizasMatriz() {
		AbrirCerrarPoliza(true);
		String[][] ac=null;
		try {
			int npolizas=(int) archPolizas.length()/Poliza.LARGO;
			ac=new String[npolizas][4];
			boolean act;
			for(int i=0 ; i<npolizas ; i++) {
				ac[i][0]=archPolizas.readInt()+"";
				ac[i][1]=archPolizas.readUTF();
				ac[i][2]=archPolizas.readInt()+"";
				act=archPolizas.readBoolean();
				ac[i][3]=act?"C":"A";
			}
		}catch(IOException e) {}
		AbrirCerrarPoliza(false);
		return ac;
	}
	
	public ArrayList<Poliza> ObtenerPolizaCompleta(int np){
		AbrirCerrarPoliza(true);
		ArrayList<Poliza> ap = new ArrayList<Poliza>();
		try {
			String ssc;
			boolean t;
			int npaux,i=0,imp;
			int npolizas=(int) archPolizas.length()/Poliza.LARGO;
			do {
				archPolizas.seek(i*Poliza.LARGO);
				npaux=archPolizas.readInt();
			}while(npaux!=np && i++<npolizas);
			do {
				ssc=archPolizas.readUTF();
				imp=archPolizas.readInt();
				t=archPolizas.readBoolean();
				ap.add(new Poliza(npaux,ssc,imp,t));
				npaux=archPolizas.readInt();
			}while(npaux==np);
		}catch(Exception e) {}
		AbrirCerrarPoliza(false);
		return ap;
	}
	
	public ArrayList<Poliza> ObtenerPolizas(){
		ArrayList<Poliza> ap=new ArrayList<Poliza>();
		AbrirCerrarPoliza(true);
		try {
			int npolizas=(int) archPolizas.length()/Poliza.LARGO;
			int np,imp,npant=0;
			String ssc;
			boolean t;
			for(int i=0 ; i<npolizas ; i++) {
				archPolizas.seek(i*Poliza.LARGO);
				np=archPolizas.readInt();
				if(i!=0 && np==npant) 
					continue;
				ssc=archPolizas.readUTF();
				imp=archPolizas.readInt();
				t=archPolizas.readBoolean();
				ap.add(new Poliza(np,ssc,imp,t));
				npant=np;
			}
		}catch(IOException e) {}
		AbrirCerrarPoliza(false);
		return ap;
	}

	private void AbrirCerrarPoliza(boolean band) {
		if(band) {
			try {
				archPolizas=new RandomAccessFile(POLIZA,"rw");
			}catch(Exception e) {}
			return;
		}
		try {
			archPolizas.close();
		}catch(Exception e) {}
	}
}
