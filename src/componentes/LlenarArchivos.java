package componentes;

import java.io.RandomAccessFile;
import java.util.Scanner;

import utilería.Rutinas;

public class LlenarArchivos {
	static RandomAccessFile archEstado;
	public static void main(String []a) {
		try {
			archEstado=new RandomAccessFile("Estados.dat","rw");
			String[]nombreEstados= {
					"Aguascalientes","Baja California","Baja California Sur"
					,"Campeche","Chiapas","Chihuahua", "CDMX","Coahuila",
					"Colima","Durango","Guanajuato","Guerrero","Hidalgo",
					"Jalisco","México","Michoacan","Morelos","Nayarit",
					"Nuevo León","Oaxaca","Puebla","Querétaro","Quintana Roo",
					"San Luis Potosí","Sinaloa","Sonora","Tabasco","Tamaulipas",
					"Tlaxcala","Veracruz","Yucatán","Zacatecas"
			};
			archEstado.seek(0);
			for(int i=1 ; i<33 ; i++) {
				archEstado.writeInt(i);
				System.out.print(i+" ");
				archEstado.writeUTF(Rutinas.PonBlancos(nombreEstados[i-1],50));
				System.out.println(nombreEstados[i-1]);
			}
		}catch(Exception e) {}
	}
}
