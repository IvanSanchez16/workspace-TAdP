package componentes;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

import utiler�a.Rutinas;

public class LlenarArchivosM {
	static RandomAccessFile archMunicipios,archCiudades;
	public static void main(String []a) {
		try {
			archMunicipios=new RandomAccessFile("Municipios.dat","rw");
			archCiudades=new RandomAccessFile("Ciudades.dat","rw");
			String[]munAg= {"Aguascalientes","Asientos","Calvillo","Cos�o"
					,"Jes�s Mar�a","Pabell�n de Arteaga","Rinc�n de Romos",
					"San Jos� de Garcia","Tepezal�","El Llano"};
			String[]munBj= {"Ensenada","Mexicali",
					"Rosarito","Tecate","Tijuana"};
			String[]munBjs= {"Comond�","Muleg�","La Paz"
					,"Los Cabos","Loreto"};
			String[]munCam= {"Calkin�","Campeche","Carmen"
					,"Champot�n","Hecelchak�n","Hopelch�n","Palizada"
					, "Tenabo","Esc�rcega","Calakmul"};
			String[]munChia= {"Acala","Chiapilla","Ixtapa","Ocosingo","Palenque"
					,"Las Rosas","Siltepec","Soyal�","Tuxtla Guti�rrez","El Parral"};
			String[]munChih= {"Allende","Balleza","Camargo","Cuauht�moc","Chihuahua",
					"Juarez","Madera","Meoqui","Nuevo Casas Grandes","Ojinaga"};
			String[]munCDMX= {"�lvaro Obreg�n","Azcapotzalco","Coyoac�n","Cuauht�moc","Gustavo A. Madero","Iztapalapa"
					,"Tl�huac","Tlalpan","Venustiano Carranza","Xochimilco"};
			String[]munCoa= {"Acu�a","Frontera","Matamoros","Monclova","M�zquiz"
					,"Piedras Negras","Ramos Arizpe","Saltillo","San Pedro","Torre�n"};
			String[]munCol= {"Armer�a","Colima","Comala","Coquimatl�n","Cuauht�moc"
					,"Ixtlahuac�n","Manzanillo","Minatitl�n","Tecom�n","Villa de �lvarez"};
			String[]munDur= {"Canelas","Durango","Ind�","Nazas","Ocampo"
					,"Poanas","San Bernardo","Santa Clara","Tamazula","Nuevo Ideal"};
			String[]munGua= {"Celaya","Dolores Hidalgo","Guanuajuato","Irapuato","Le�n",
					"P�njamo","Salamanca","San Miguel de Allende","Silao de la Victoria","Valle de Santiago"};
			String[]munGue= {"Acapulco de Ju�rez","Acatepec","Ahuacuotzingo","Ajuchitl�n del Progreso"
					,"Arcelia","Atoyac de �lvarez","Chilpancingo de los Bravos","Iguala de la Independencia","Zihuatanejo de Azueta"};
			String[]munHid= {"Actopan","Cuautepec de Hinojosa","Huejutla de Reyes","Mineral de la Reforma","Pachuca de Soto"
					,"Tepeapulco","Tepeji del R�o de Ocampo","Tizaycua","Tula de Allende","Tulancingo de Bravo"};
			String[]munJal= {"El Salto","Guadalajara","Lagos de Moreno","Puerto Vallarta","Tepatitl�n de Morelos"
					,"Tlajomulco de Z��iga","Tlaquepaque","Tonal�","Zapopan","Zapotl�n el Grande"};
			String[]munMex= {"Atizap�n de Zaragoza","Chimalhuac�n","Cuautitl�n Izcalli","Ecatepec de Morelos","Ixtapaluca",
					"Naucalpan de Ju�rez","Nezahualc�yotl","Tlalnepantla de Baz","Toluca","Tultitl�n"};
			String[]munMic= {"Apatzing�n","Hidalgo","La Piedad","L�zaro C�rdenas","Maravat�o"
					,"Morelia","P�tzcuaro","Uruapan","Zamora","Zit�cuaro"};
			String[]munMor= {"Ayala","Cuautla","Cuernavaca","Emiliano Zapata","Jiutepec",
					"Jojutla","Puente de Ixtla","Temixco","Xochitepec","Yautepec"};
			String[]munNay= {"Acaponeta","Amatl�n de Ca�as","Huajicori","Jala","El Nayar"
					,"Ruiz","San Pedro Lagunillas","Santiago Ixcuintla","Tepic","La Yesca"};
			String[]munNLe= {"Apodaca","Cadereyta Jim�nez","Garc�a","General Escobedo","Guadalupe"
					,"Ju�rez","Monterrey","San Nicol�s de los Garza","San Pedro Garza Garc�a","Santa Catarina"};
			String[]munOax= {"Abejones","Calihual�","Cosolapa","Cosoltepec","Villa Hidalgo"
					,"Ixtl�n de Ju�rez","Oaxaca de Ju�rez","La Reforma","Salina Cruz","Mechoac�n"};
			String[]munPue= {"Amozoc","Atlixco","Cuautlancingo","Huauchinango","Puebla"
					,"San Andr�s Cholula","San Mart�n Texmelucan","San Pedro Cholula","Tehuac�n","Teziutl�n"};
			String[]munQue= {"Amealco de Bonfil","Cadereyta de Montes","Col�n","Corregidora","El Marqu�s"
					,"Ezequiel Montes","Pedro Escobedo","Quer�taro","San Juan del R�o","Tequisquiapan"};
			String[]munQRo= {"Cozumel","Felipe Carrillo Puerto","Isla Mujeres","Oth�n P. Blanco"
					,"L�zaro C�rdenas","Solidaridad","Tulum","Bacalar","Puerto Morelos"};
			String[]munSLP= {"Matehuala","Rioverde","San Luis Potos�","Soledad de Graciano S�nchez","Ciudad Valles"
					,"Mexquitic de Carmona","Xilitla","Aquism�n","Villa de Reyes","�bano"};
			String[]munSin= {"Culiac�n","Ahome","Mazatl�n","Navolato","Guasave"
					,"Elota","Sinaloa","Salvador Alvarado","El Fuerte","Escuinapa"};
			String[]munSon= {"Hermosillo","Cajeme","Nogales","San Luis R�o Colorado","Navojoa"
					,"Guaymas","Caborca","Agua Prieta","Huatabampo","Puerto Pe�asco"};
			String[]munTab= {"Balanc�n","C�rdenas","Centro","Comalcalco","Huimanguillo"
					,"Jonuta","Para�so","Tacotalpa","Teapa","Tenosique"};
			String[]munTam= {"Reynosa","Matamoros","Nuevo Laredo","Victoria","Tampico"
					,"Altamira","Ciudad Madero","R�o Bravo","El Mante","Valle Hermoso"};
			String[]munTla= {"Tlaxcala","Huamantla","Apizaco","San Pablo del Monte","Chiautempan"
					,"Tlaxco","Zacatelco","Panotla","Nat�vitas","Xalostoc"};
			String[]munVer= {"Veracruz","Xalapa","Coatzacoalcos","C�rdoba","Poza Rica de Hidalgo"
					,"Papantla","Minatitl�n","San Andr�s Tuxtla","Tuxpan"};
			String[]munYuc= {"M�rida","Chemax","Halach�","Izamal","Maxcan�"
					,"Peto","Temoz�n","Ticul","Um�n","Yaxcab�"};
			String[]munZac= {"Fresnillo","Guadalupe","Zacatecas","Pinos","R�o Grande"
					,"Sombrerete","Jerez","Loreto","Calera","Ojocaliente"};
			String [][] mun= {munAg,munBj,munBjs,munCam,munChia,munChih,munCDMX,munCoa,munCol,munDur,munGua
					,munGue,munHid,munJal,munMex,munMic,munMor,munNay,munNLe,munOax,munPue,munQue,munQRo
					,munSLP,munSin,munSon,munTab,munTam,munTla,munVer,munYuc,munZac};
			//EscribirMunicipios(mun);
			EscribirCiudades(mun);
		}catch(Exception e) {}
	}

	public static void EscribirMunicipios(String [][] mun) throws IOException{
		archMunicipios.seek(0);
		for(int i=1 ; i<=mun.length ; i++) {
			Arrays.sort(mun[i-1]);
			for(int j=1 ; j<=mun[i-1].length ; j++) {
				archMunicipios.writeInt(i);
				System.out.print(i+" ");
				archMunicipios.writeInt(j);
				System.out.print(j+" ");
				int na=ContarAcentos(mun[i-1][j-1]);
				archMunicipios.writeUTF(Rutinas.PonBlancos(mun[i-1][j-1], 50-na));
				System.out.println(mun[i-1][j-1]);
			}
		}
	}
	
	public static void EscribirCiudades(String [][] mun) throws IOException{
		archCiudades.seek(0);
		for(int i=1 ; i<=mun.length ; i++) {
			Arrays.sort(mun[i-1]);
			for(int j=1 ; j<=mun[i-1].length ; j++) {
				for(int k=1 ; k<=10 ; k++) {
					archCiudades.writeInt(i);
					System.out.print(i+" ");
					archCiudades.writeInt(j);
					System.out.print(j+" ");
					archCiudades.writeInt(k);
					System.out.print(k+" ");
					int na=ContarAcentos(mun[i-1][j-1]);
					String munaux=mun[i-1][j-1]+"Ciu"+k;
					archCiudades.writeUTF(Rutinas.PonBlancos(munaux, 50-na));
					System.out.println(munaux);
				}
			}
		}
	}
	
	public static int ContarAcentos(String n) {
		int na=0;
		for(int i=0 ; i<n.length() ; i++) {
			if(n.charAt(i)<32 || n.charAt(i)>122)
				na++;
		}
		return na;
	}
}
