package componentes;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

import utilería.Rutinas;

public class LlenarArchivosM {
	static RandomAccessFile archMunicipios,archCiudades;
	public static void main(String []a) {
		try {
			archMunicipios=new RandomAccessFile("Municipios.dat","rw");
			archCiudades=new RandomAccessFile("Ciudades.dat","rw");
			String[]munAg= {"Aguascalientes","Asientos","Calvillo","Cosío"
					,"Jesús María","Pabellón de Arteaga","Rincón de Romos",
					"San José de Garcia","Tepezalá","El Llano"};
			String[]munBj= {"Ensenada","Mexicali",
					"Rosarito","Tecate","Tijuana"};
			String[]munBjs= {"Comondú","Mulegé","La Paz"
					,"Los Cabos","Loreto"};
			String[]munCam= {"Calkiní","Campeche","Carmen"
					,"Champotón","Hecelchakán","Hopelchén","Palizada"
					, "Tenabo","Escárcega","Calakmul"};
			String[]munChia= {"Acala","Chiapilla","Ixtapa","Ocosingo","Palenque"
					,"Las Rosas","Siltepec","Soyaló","Tuxtla Gutiérrez","El Parral"};
			String[]munChih= {"Allende","Balleza","Camargo","Cuauhtémoc","Chihuahua",
					"Juarez","Madera","Meoqui","Nuevo Casas Grandes","Ojinaga"};
			String[]munCDMX= {"Álvaro Obregón","Azcapotzalco","Coyoacán","Cuauhtémoc","Gustavo A. Madero","Iztapalapa"
					,"Tláhuac","Tlalpan","Venustiano Carranza","Xochimilco"};
			String[]munCoa= {"Acuña","Frontera","Matamoros","Monclova","Múzquiz"
					,"Piedras Negras","Ramos Arizpe","Saltillo","San Pedro","Torreón"};
			String[]munCol= {"Armería","Colima","Comala","Coquimatlán","Cuauhtémoc"
					,"Ixtlahuacán","Manzanillo","Minatitlán","Tecomán","Villa de Álvarez"};
			String[]munDur= {"Canelas","Durango","Indé","Nazas","Ocampo"
					,"Poanas","San Bernardo","Santa Clara","Tamazula","Nuevo Ideal"};
			String[]munGua= {"Celaya","Dolores Hidalgo","Guanuajuato","Irapuato","León",
					"Pénjamo","Salamanca","San Miguel de Allende","Silao de la Victoria","Valle de Santiago"};
			String[]munGue= {"Acapulco de Juárez","Acatepec","Ahuacuotzingo","Ajuchitlán del Progreso"
					,"Arcelia","Atoyac de Álvarez","Chilpancingo de los Bravos","Iguala de la Independencia","Zihuatanejo de Azueta"};
			String[]munHid= {"Actopan","Cuautepec de Hinojosa","Huejutla de Reyes","Mineral de la Reforma","Pachuca de Soto"
					,"Tepeapulco","Tepeji del Río de Ocampo","Tizaycua","Tula de Allende","Tulancingo de Bravo"};
			String[]munJal= {"El Salto","Guadalajara","Lagos de Moreno","Puerto Vallarta","Tepatitlán de Morelos"
					,"Tlajomulco de Zúñiga","Tlaquepaque","Tonalá","Zapopan","Zapotlán el Grande"};
			String[]munMex= {"Atizapán de Zaragoza","Chimalhuacán","Cuautitlán Izcalli","Ecatepec de Morelos","Ixtapaluca",
					"Naucalpan de Juárez","Nezahualcóyotl","Tlalnepantla de Baz","Toluca","Tultitlán"};
			String[]munMic= {"Apatzingán","Hidalgo","La Piedad","Lázaro Cárdenas","Maravatío"
					,"Morelia","Pátzcuaro","Uruapan","Zamora","Zitácuaro"};
			String[]munMor= {"Ayala","Cuautla","Cuernavaca","Emiliano Zapata","Jiutepec",
					"Jojutla","Puente de Ixtla","Temixco","Xochitepec","Yautepec"};
			String[]munNay= {"Acaponeta","Amatlán de Cañas","Huajicori","Jala","El Nayar"
					,"Ruiz","San Pedro Lagunillas","Santiago Ixcuintla","Tepic","La Yesca"};
			String[]munNLe= {"Apodaca","Cadereyta Jiménez","García","General Escobedo","Guadalupe"
					,"Juárez","Monterrey","San Nicolás de los Garza","San Pedro Garza García","Santa Catarina"};
			String[]munOax= {"Abejones","Calihualá","Cosolapa","Cosoltepec","Villa Hidalgo"
					,"Ixtlán de Juárez","Oaxaca de Juárez","La Reforma","Salina Cruz","Mechoacán"};
			String[]munPue= {"Amozoc","Atlixco","Cuautlancingo","Huauchinango","Puebla"
					,"San Andrés Cholula","San Martín Texmelucan","San Pedro Cholula","Tehuacán","Teziutlán"};
			String[]munQue= {"Amealco de Bonfil","Cadereyta de Montes","Colón","Corregidora","El Marqués"
					,"Ezequiel Montes","Pedro Escobedo","Querétaro","San Juan del Río","Tequisquiapan"};
			String[]munQRo= {"Cozumel","Felipe Carrillo Puerto","Isla Mujeres","Othón P. Blanco"
					,"Lázaro Cárdenas","Solidaridad","Tulum","Bacalar","Puerto Morelos"};
			String[]munSLP= {"Matehuala","Rioverde","San Luis Potosí","Soledad de Graciano Sánchez","Ciudad Valles"
					,"Mexquitic de Carmona","Xilitla","Aquismón","Villa de Reyes","Ébano"};
			String[]munSin= {"Culiacán","Ahome","Mazatlán","Navolato","Guasave"
					,"Elota","Sinaloa","Salvador Alvarado","El Fuerte","Escuinapa"};
			String[]munSon= {"Hermosillo","Cajeme","Nogales","San Luis Río Colorado","Navojoa"
					,"Guaymas","Caborca","Agua Prieta","Huatabampo","Puerto Peñasco"};
			String[]munTab= {"Balancán","Cárdenas","Centro","Comalcalco","Huimanguillo"
					,"Jonuta","Paraíso","Tacotalpa","Teapa","Tenosique"};
			String[]munTam= {"Reynosa","Matamoros","Nuevo Laredo","Victoria","Tampico"
					,"Altamira","Ciudad Madero","Río Bravo","El Mante","Valle Hermoso"};
			String[]munTla= {"Tlaxcala","Huamantla","Apizaco","San Pablo del Monte","Chiautempan"
					,"Tlaxco","Zacatelco","Panotla","Natívitas","Xalostoc"};
			String[]munVer= {"Veracruz","Xalapa","Coatzacoalcos","Córdoba","Poza Rica de Hidalgo"
					,"Papantla","Minatitlán","San Andrés Tuxtla","Tuxpan"};
			String[]munYuc= {"Mérida","Chemax","Halachó","Izamal","Maxcanú"
					,"Peto","Temozón","Ticul","Umán","Yaxcabá"};
			String[]munZac= {"Fresnillo","Guadalupe","Zacatecas","Pinos","Río Grande"
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
