package terrenosHermanos;

import javax.swing.*;
import java.awt.event.*;
import utilería.*;
import java.awt.*;

public class Vista extends JFrame implements ActionListener{
	
	JPanel PCentro,PDatos,PHermanos;
	JLabel LbH1,LbH2,LbH3;
	Hectarea[] AH;
	HiloHermano[] AHh;
	Elemento[] AS;
	Image backimage;
	Graphics g;
	Timer t;
	
	public Vista() {
		super("Tres hermanos");
		hazInterfaz();
	}
	
	public void iniciar() {
		t.start();
		for(int i=0 ; i<AHh.length ; i++)
			AHh[i].start();
	}
	
	public void transformarHec(Color c,int nh) { 
		AH[nh-1].setBackground(c);
		if(c.getRed()==255) {
			int n=Integer.parseInt(LbH1.getText());
			n++;
			LbH1.setText(n+"");
			return;
		}
		if(c.getBlue()==255) {
			int n=Integer.parseInt(LbH2.getText());
			n++;
			LbH2.setText(n+"");
			return;
		}
		int n=Integer.parseInt(LbH3.getText());
		n++;
		LbH3.setText(n+"");
	}
	
	private void hazInterfaz() {
		setSize(750,700);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PCentro=new JPanel();
		AH=new Hectarea[100];
		AS=new Elemento[100];
		PCentro.setLayout(new GridLayout(0,10));
		for(int i=0 ; i<100 ; i++) {
			int num=Rutinas.nextInt(1,3);			
			char estado=(num==1?'E':num==2?'B':'M');
			AH[i]=new Hectarea(i+1,estado);			
			AS[i]=new Elemento();										
			PCentro.add(AH[i]); 					
		}
		add(PCentro);
		
		PHermanos=new JPanel();					
		PHermanos.setLayout(new GridLayout(0,3));
		JLabel lb1=new JLabel("Hermano 1: Rojo",SwingConstants.CENTER);
		JLabel lb2=new JLabel("Hermano 2: Azul",SwingConstants.CENTER);
		JLabel lb3=new JLabel("Hermano 3: Verde",SwingConstants.CENTER);
		lb1.setFont(new Font("Dubai",1,16));
		lb2.setFont(new Font("Dubai",1,16));
		lb3.setFont(new Font("Dubai",1,16));
		lb1.setForeground(Color.RED);
		lb2.setForeground(Color.BLUE);
		lb3.setForeground(new Color(30,140,30));
		PHermanos.add(lb1);
		PHermanos.add(lb2);
		PHermanos.add(lb3);
		add(PHermanos,BorderLayout.NORTH);
		
		PDatos=new JPanel();						
		PDatos.setLayout(new BoxLayout(PDatos,BoxLayout.X_AXIS));
		JLabel lb4=new JLabel("  No de h. del 1er hermano: ");
		JLabel lb5=new JLabel("    No de h. del 2do hermano: ");
		JLabel lb6=new JLabel("    No de h. del 3er hermano: ");
		lb4.setFont(new Font("Dubai",1,17));
		lb5.setFont(new Font("Dubai",1,17));
		lb6.setFont(new Font("Dubai",1,17));
		LbH1=new JLabel("0");
		LbH2=new JLabel("0");
		LbH3=new JLabel("0");
		LbH1.setFont(new Font("Dubai",1,17));
		LbH2.setFont(new Font("Dubai",1,17));
		LbH3.setFont(new Font("Dubai",1,17));
		PDatos.add(lb4);
		PDatos.add(LbH1);
		PDatos.add(lb5);
		PDatos.add(LbH2);
		PDatos.add(lb6);
		PDatos.add(LbH3);
		add(PDatos,BorderLayout.SOUTH);
		
		AHh=new HiloHermano[3];
		AHh[0]=new HiloHermano(AS,this,40,28,Color.RED);
		AHh[1]=new HiloHermano(AS,this,290,28,Color.BLUE);
		AHh[2]=new HiloHermano(AS,this,530,28,new Color(30,140,30));
		
		setVisible(true);
		backimage=createImage(getWidth(),getHeight());
		g=backimage.getGraphics();
		t=new Timer(10,this);
		Dibuja();
	}
	
	public void paint(Graphics g) {
		g.drawImage(backimage,0,0,getWidth(),getHeight(),null);		
	}
	
	public void Dibuja() {  
		super.paint(g);
		dibujaH(AHh[0]);
		dibujaH(AHh[1]);
		dibujaH(AHh[2]);
		repaint();
	}
	
	private void dibujaH(HiloHermano h) { 
		g.setColor(Color.BLACK);
		g.fillOval(h.getX(),h.getY(),20,20);
		g.setColor(h.getColor());
		g.fillOval(h.getX()+4,h.getY()+4,12,12);
	}

	public void actionPerformed(ActionEvent evt) {
		Dibuja();
	}
	
	
}
	