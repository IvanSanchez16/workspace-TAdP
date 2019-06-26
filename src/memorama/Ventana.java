package memorama;
import javax.swing.*;
import utilería.Rutinas;
import java.awt.*;
import java.util.Vector;

public class Ventana extends JFrame{

	Escuchador e;
	Cartas[] AC;
	String[] Imagenes= {"imagenes/boston.png","imagenes/pelicans.png","imagenes/bucks.png","imagenes/portland.png","imagenes/lakers.png","imagenes/76ers.png","imagenes/denver.png","imagenes/spurs.png","imagenes/toronto.png","imagenes/heat.png"};
	ButtonGroup Grp;
	JRadioButton R1,R2,R3,R4,R5;
	JPanel P1,P2;

	public Ventana(int npares) {
		super("Memorama");
		HazInterfaz(npares);
		setSize(800,700);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void HazInterfaz(int npares) {
		ImageIcon Iaux=Rutinas.AjustarImagen("imagenes/nba.jpg",30,40);
		Image icono=Iaux.getImage();
		setIconImage(icono);
		AgregaJRadioButtons();
		AgregaBotones(npares*2);
	}

	public void setEscuchador(Escuchador e) {
		for(int i=0 ; i<AC.length ; i++)
			AC[i].addActionListener(e);
		R1.addActionListener(e);
		R2.addActionListener(e);
		R3.addActionListener(e);
		R4.addActionListener(e);
		R5.addActionListener(e);
		this.e=e;
	}
	
	public void VuelveEscuchadores() {
		for(int i=0 ; i<AC.length ; i++)
			AC[i].addActionListener(e);
	}
	
	public void AgregaBotones(int ncartas) {
		if(P1!=null)
			remove(P1);
		P1 = new JPanel(new GridLayout(0, (ncartas/2%2==0?ncartas/4:ncartas/3) ,5,5));
		AC=new Cartas[ncartas];
		for(int i=0,j=0; i<AC.length ; i+=2,j++) {
			AC[i]=new Cartas(Imagenes[j]);
			AC[i+1]=new Cartas(Imagenes[j]);
		}
		Vector<Integer> VAux=new Vector<Integer>();
		int aux;
		for(int i=0 ; i<ncartas ; i++) 
			VAux.add(i);
		for(int j=0; j<AC.length ; j++) {
			aux= Rutinas.nextInt( VAux.size() );
			P1.add( AC[VAux.get(aux)] );
			VAux.remove(aux);
		}
		P1.setBackground(new Color(31, 49, 176));
		add(P1,BorderLayout.CENTER);
		System.out.println(P1.getHeight());
	}

	private void AgregaJRadioButtons() {
		P2=new JPanel();
		P2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE),"Número de pares para jugar",0,0,new Font("Tahoma",1,13),Color.WHITE));
		Grp=new ButtonGroup();
		R1=new JRadioButton("6 Pares");
		R1.setBackground(Color.BLACK);
		R1.setForeground(Color.WHITE);
		R2=new JRadioButton("7 Pares");
		R2.setBackground(Color.BLACK);
		R2.setForeground(Color.WHITE);
		R3=new JRadioButton("8 Pares");
		R3.setBackground(Color.BLACK);
		R3.setForeground(Color.WHITE);
		R4=new JRadioButton("9 Pares");
		R4.setBackground(Color.BLACK);
		R4.setForeground(Color.WHITE);
		R5=new JRadioButton("10 Pares",true);
		R5.setBackground(Color.BLACK);
		R5.setForeground(Color.WHITE);
		Grp.add(R1);
		Grp.add(R2);
		Grp.add(R3);
		Grp.add(R4);
		Grp.add(R5);
		P2.add(R1);
		P2.add(R2);
		P2.add(R3);
		P2.add(R4);
		P2.add(R5);
		P2.setBackground(new Color(0,0,0));
		add(P2,BorderLayout.NORTH);
	}
	
	public void RehacerJuego(int ncartas,int y) {
		AgregaBotones(ncartas);
		VuelveEscuchadores();
		setSize(700,y);
		setLocationRelativeTo(null);
	}
}
