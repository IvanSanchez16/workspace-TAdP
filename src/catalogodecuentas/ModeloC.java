package catalogodecuentas;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import utilería.Rutinas;

public class ModeloC {
	
	final String CUENTA="Cuenta.dat",INDEXC="IndexCuenta.dat";
	RandomAccessFile archIndCuentas,archCuentas;
	
	public void AñadirCuenta(String nc,String n,int s) {
		AbrirCerrarCuenta(true);
		try {
			int ncuentas=(int) archCuentas.length()/Cuenta.LARGO;
			
			archIndCuentas.seek( ColocarApuntadorC(nc,ncuentas)*Cuenta.LARGOI );
			archIndCuentas.writeUTF(nc);
			archIndCuentas.writeInt(ncuentas);

			archCuentas.seek( archCuentas.length() );
			int na=CuentaAcentos(n);
			archCuentas.writeUTF( Rutinas.PonBlancos(n,50-na) );
			archCuentas.writeInt(s);
			archCuentas.writeInt(0);
			archCuentas.writeInt(0);
			archCuentas.writeBoolean(true);
		} catch (IOException e) {}
		AbrirCerrarCuenta(false);
	}

	public boolean validarCuenta(String nc) {
		AbrirCerrarCuenta(true);
		int ncuentas=0;
		try {
			ncuentas=(int) archCuentas.length()/Cuenta.LARGO;
			for(int i=0; i<ncuentas ; i++) {
				archIndCuentas.seek(i*Cuenta.LARGOI);
				String ncactual=archIndCuentas.readUTF();
				if(nc.compareTo(ncactual)==0) { 
					AbrirCerrarCuenta(false);
					return false;
				}
			}
		}catch(IOException e) {}

		if(nc.substring(2).equals("0000")) {
			AbrirCerrarCuenta(false);
			return true;
		}
		if(nc.substring(4).equals("00")) {
			try {
				for(int i=0; i<ncuentas ; i++) {
					archIndCuentas.seek(i*Cuenta.LARGOI);
					String ncactual=archIndCuentas.readUTF();
					if(nc.substring(0,2).compareTo(ncactual.substring(0,2))==0) {
						AbrirCerrarCuenta(false);
						return true;
					}
				}
			}catch(IOException e) {}
			AbrirCerrarCuenta(false);
			return false;
		}
		try {
			for(int i=0; i<ncuentas ; i++) {
				archIndCuentas.seek(i*Cuenta.LARGOI);
				String ncactual=archIndCuentas.readUTF();
				if(nc.substring(0,4).compareTo(ncactual.substring(0,4))==0) {
					AbrirCerrarCuenta(false);
					return true;
				}
			}
		}catch(IOException e) {}
		AbrirCerrarCuenta(false);
		return false;
	}
	
	public void DarBajaCuenta(String nc) {
		try {
			int ncuentas=(int) archCuentas.length()/Cuenta.LARGO;
			DarBajaCuenta(nc,0,ncuentas);
		}catch(IOException e) {}
	}
	
	private void DarBajaCuenta(String nc,int izq,int der) throws IOException{
		int medio=(der+izq)/2;
		int index=medio*Cuenta.LARGOI;
		archIndCuentas.seek(index);
		String numc=archIndCuentas.readUTF();
		if(nc.equals(numc)) {
			int pos=archIndCuentas.readInt();
			archCuentas.seek((pos*Cuenta.LARGO));
			archCuentas.readUTF();
			archCuentas.readInt();
			archCuentas.readInt();
			archCuentas.readInt();
			archCuentas.writeBoolean(false);
			return;
		}
		if(nc.compareTo(numc)<0) {
			DarBajaCuenta(nc,izq,medio);
			return;
		}
		DarBajaCuenta(nc,medio,der);
	}
	
	public void DarBaja(String nc) {
		AbrirCerrarCuenta(true);
		try {
			int ncuentas=(int) archCuentas.length()/Cuenta.LARGO;
			for(int i=0 ; i<ncuentas ; i++) {
				archIndCuentas.seek(i*Cuenta.LARGOI);
				String ncp=archIndCuentas.readUTF();
				if(esHijo(nc,ncp))
					DarBajaCuenta(ncp);
			}
			DarBajaCuenta(nc);
		}catch(IOException e) {}
		AbrirCerrarCuenta(false);
	}
	
	private boolean esHijo(String ncp,String nch) {
		if(ncp.equals(nch))
			return false;
		if(ncp.substring(0,2).equals(nch.substring(0,2))) {
			if(ncp.substring(2,4).equals("00")) 
				return true;
			if(ncp.substring(0,4).equals(nch.substring(0,4)))
				if(ncp.substring(4).equals("00")) 
					return true;	
			return false;
		}
		return false;
	}
	
	public void AfectarCuentas(String ssb,int cargo,int abono) {
		AbrirCerrarCuenta(true);
		try {
			int ncuentas=(int) archCuentas.length()/Cuenta.LARGO;
			String ncaux;
			int pos,c,a;
			for(int i=0 ; i<ncuentas ; i++) {
				archIndCuentas.seek(i*Cuenta.LARGOI);
				ncaux=archIndCuentas.readUTF();
				pos=archIndCuentas.readInt();
				archCuentas.seek(pos*Cuenta.LARGO);
				if(esHijo(ncaux,ssb) || ssb.equals(ncaux)) {
					archCuentas.readUTF();
					archCuentas.readInt();
					c=archCuentas.readInt();
					a=archCuentas.readInt();
					archCuentas.seek((pos*Cuenta.LARGO)+56);
					archCuentas.writeInt(cargo+c);
					archCuentas.writeInt(abono+a);
				}
			}
		}catch(IOException e) {}
	}
	
	public ArrayList<Cuenta> ObtenerSubSubCuentas(){
		AbrirCerrarCuenta(true);
		ArrayList<Cuenta> aaux=new ArrayList<Cuenta>();
		try {
			int ncuentas=(int) archCuentas.length()/Cuenta.LARGO;
			String ncaux,n;
			int pos,s,c,a;
			for(int i=0 ; i<ncuentas ; i++) {
				archIndCuentas.seek(i*Cuenta.LARGOI);
				ncaux=archIndCuentas.readUTF();
				pos=archIndCuentas.readInt();
				archCuentas.seek(pos*Cuenta.LARGO);
				n=archCuentas.readUTF().trim();
				s=archCuentas.readInt();
				c=archCuentas.readInt();
				a=archCuentas.readInt();
				boolean act=archCuentas.readBoolean();
				if(esSubSubCuenta(ncaux) && act) 
					aaux.add(new Cuenta(ncaux,n,s,c,a));
			}
		}catch(IOException e) {}
		AbrirCerrarCuenta(false);
		return aaux;
	}
	
	private boolean esSubSubCuenta(String nc) {
		return !(nc.substring(4).equals("00"));	
	}
	
	public void ModificarNombre(String nc,String nn) {
		AbrirCerrarCuenta(true);
		try {
			int ncuentas=(int) archCuentas.length()/Cuenta.LARGO;
			ModificarNombre(nc,nn,0,ncuentas);
		}catch(IOException e) {}
		AbrirCerrarCuenta(false);
	}
	
	private void ModificarNombre(String nc,String nn,int izq,int der) throws IOException{
		int medio=(der+izq)/2;
		int index=medio*Cuenta.LARGOI;
		archIndCuentas.seek(index);
		String numc=archIndCuentas.readUTF();
		if(nc.equals(numc)) {
			int pos=archIndCuentas.readInt();
			archCuentas.seek(pos*Cuenta.LARGO);
			int na=CuentaAcentos(nn);
			archCuentas.writeUTF( Rutinas.PonBlancos(nn,50-na) );
			return;
		}
		if(nc.compareTo(numc)<0) {
			ModificarNombre(nc,nn,izq,medio);
			return;
		}
		ModificarNombre(nc,nn,medio,der);
	}
	
	public ArrayList<Cuenta> ObtenerCuentas() {
		AbrirCerrarCuenta(true);
		ArrayList<Cuenta> ac=null;
		try {
			int ncuentas=(int) archCuentas.length()/Cuenta.LARGO;
			ac=new ArrayList<Cuenta>();
			String nc,nom;
			int s,a,c;
			boolean act;
			for(int i=0 ; i<ncuentas ; i++) {
				archIndCuentas.seek(i*Cuenta.LARGOI);
				nc=archIndCuentas.readUTF();
				int pos=archIndCuentas.readInt();
				archCuentas.seek(pos*Cuenta.LARGO);
				nom=archCuentas.readUTF().trim();
				s=archCuentas.readInt();
				c=archCuentas.readInt();
				a=archCuentas.readInt();
				act=archCuentas.readBoolean();
				if(act) {
					ac.add(new Cuenta(nc,nom,s,c,a));
					continue;
				}
			}
		}catch(IOException e) {}
		AbrirCerrarCuenta(false);
		return ac;
	}
	
	public Cuenta ObtenerCuenta(String nc) {
		AbrirCerrarCuenta(true);
		try {
			int ncuentas=(int) archCuentas.length()/Cuenta.LARGO;
			if(nc==null || nc.equals("")) {
				AbrirCerrarCuenta(false);
				return null;
			}
			return ObtenerCuenta(nc,0,ncuentas);
		}catch(IOException e) {}
		return null;
	}
	
	private Cuenta ObtenerCuenta(String nc,int izq,int der) throws IOException{
		int medio=(der+izq)/2;
		int index=medio*Cuenta.LARGOI;
		archIndCuentas.seek(index);
		String numc=archIndCuentas.readUTF();
		if(nc.equals(numc)) {
			int pos=archIndCuentas.readInt();
			archCuentas.seek(pos*Cuenta.LARGO);
			String nom=archCuentas.readUTF().trim();
			int s=archCuentas.readInt();
			int c=archCuentas.readInt();
			int a=archCuentas.readInt();
			boolean act=archCuentas.readBoolean();
			if(!act) {
				AbrirCerrarCuenta(false);
				return null;
			}
			AbrirCerrarCuenta(false);
			return new Cuenta(numc,nom,s,c,a);
		}
		if(der==izq || der==izq+1) {
			AbrirCerrarCuenta(false);
			return null;
		}
		if(nc.compareTo(numc)<0)
			return ObtenerCuenta(nc,izq,medio);
		return ObtenerCuenta(nc,medio,der);
	}

	private long ColocarApuntadorC(String nc,int ncuentas) throws IOException{
		int i;
		for(i=0 ; i<ncuentas ; i++) {
			archIndCuentas.seek(i*Cuenta.LARGOI);
			String ncactual=archIndCuentas.readUTF();
			if(nc.compareTo(ncactual)<0) 
				break;
		}
		RecorrerArchivoC(i,ncuentas);
		return i;
	}
	
	private void RecorrerArchivoC(int index,int ncuentas) throws IOException{
		for(int i=ncuentas-1 ; i>=index ; i--) {
			archIndCuentas.seek(i*Cuenta.LARGOI);
			String nc=archIndCuentas.readUTF();
			int pos=archIndCuentas.readInt();
			archIndCuentas.writeUTF(nc);
			archIndCuentas.writeInt(pos);
		}
	}

	private void AbrirCerrarCuenta(boolean band) {
		if(band) {
			try {
				archIndCuentas=new RandomAccessFile(INDEXC,"rw");
				archCuentas=new RandomAccessFile(CUENTA,"rw");
			}catch(Exception e) {}
			return;
		}
		try {
			archIndCuentas.close();
			archCuentas.close();
		}catch(Exception e) {}
	}

	private int CuentaAcentos(String n) {
		int na=0;
		for(int i=0 ; i<n.length() ; i++) {
			if(n.charAt(i)<32 || n.charAt(i)>122)
				na++;
		}
		return na;
	}
	
	public String[][] ObtenerCuentasMatriz() {
		AbrirCerrarCuenta(true);
		String[][] ac=null;
		try {
			int ncuentas=(int) archCuentas.length()/Cuenta.LARGO;
			ac=new String[ncuentas][6];
			boolean act;
			for(int i=0 ; i<ncuentas ; i++) {
				archIndCuentas.seek(i*Cuenta.LARGOI);
				ac[i][0]=archIndCuentas.readUTF();
				int pos=archIndCuentas.readInt();
				archCuentas.seek(pos*Cuenta.LARGO);
				ac[i][1]=archCuentas.readUTF().trim();
				ac[i][2]=archCuentas.readInt()+"";
				ac[i][3]=archCuentas.readInt()+"";
				ac[i][4]=archCuentas.readInt()+"";
				act=archCuentas.readBoolean();
				ac[i][5]=act?"Sí":"No";
			}
		}catch(IOException e) {}
		AbrirCerrarCuenta(false);
		return ac;
	}

}
