package plantaEnsambladora;

import utilería.*;

public class HiloLineaProduccion extends Thread{
	static int nautos;
	private Semaforo se1,se21,se22,se3,se4,sh;
	private Auto carro;
	
	public HiloLineaProduccion(Auto c,Semaforo se1, Semaforo se21, Semaforo se22, Semaforo se3, Semaforo se4,Semaforo sh) {
		this.se1 = se1;
		this.se21 = se21;
		this.se22 = se22;
		this.se3 = se3;
		this.se4 = se4;
		this.sh=sh;
		carro=c;
	}

	public void run() {
		int nautostem;
		while(true) {
			sh.Espera();
			
			nautostem=++nautos;
			sh.Libera();
			if(nautostem>1000)
				return;
			elaboraAuto(nautostem);
			
		}
	}

	private void elaboraAuto(int nauto) {
		carro.setNumero(nauto);
		carro.setEstado(0);
		carro.setX(110);
		se1.Espera();
		while(carro.getProgreso()<90) {
			try {
				sleep(20);
			}catch(Exception e) {}
			carro.avanzaProgreso((90f/(12f*50f)));
		}
		se1.Libera();
		carro.setProgreso(0f);
		carro.setEstado(1);
		avanzaEstacion();
		
		se21.Espera();
		while(carro.getProgreso()<90) {
			try {
				sleep(20);
			}catch(Exception e) {}
			carro.avanzaProgreso((90f/(4f*50f)));
		}
		carro.setProgreso(0f);
		avanzaEstacion();
		se22.Espera();
		se21.Libera();
		while(carro.getProgreso()<90) {
			try {
				sleep(20);
			}catch(Exception e) {}
			carro.avanzaProgreso((90f/(3f*50f)));
		}
		se22.Libera();
		carro.setProgreso(0f);
		carro.setEstado(2);
		avanzaEstacion();
		
		se3.Espera();
		while(carro.getProgreso()<90) {
			try {
				sleep(20);
			}catch(Exception e) {}
			carro.avanzaProgreso((90f/(6f*50f)));
		}
		se3.Libera();
		carro.setProgreso(0f);
		carro.setEstado(3);
		avanzaEstacion();
		
		se4.Espera();
		while(carro.getProgreso()<90) {
			try {
				sleep(20);
			}catch(Exception e) {}
			carro.avanzaProgreso((90f/(3f*50f)));
		}
		se4.Libera();
		carro.setProgreso(0f);
		carro.setEstado(4);
		avanzaEstacion();
		
		while(carro.getProgreso()<90) {
			try {
				sleep(20);
			}catch(Exception e) {}
			carro.avanzaProgreso((90f/(5f*50f)));
		}
		carro.setProgreso(0f);
		carro.setEstado(5);
		avanzaEstacion();
		
		while(carro.getProgreso()<90) {
			try {
				sleep(20);
			}catch(Exception e) {}
			carro.avanzaProgreso((90f/(10f*50f)));
		}
		carro.setProgreso(0f);
		carro.setEstado(6);
		avanzaEstacion();
		
		try {
			sleep(1000);
		}catch(Exception e) {}
		avanzaEstacion();
	}
	
	private void avanzaEstacion() {
		for(int i=0 ; i<112 ; i+=2) {
			carro.avanzar();
			try {
				sleep(20);
			}catch(Exception e) {}
		}
	}
}
