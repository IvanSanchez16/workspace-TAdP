package serpientesyescaleras;

import javax.swing.*;

import utilería.ListaDBL;
import utilería.NodoDBL;
import utilería.Rutinas;
import java.awt.*;

@SuppressWarnings("serial")
public class VTablero extends JFrame{
	
	Controlador C;
	JButton BtnTurno;
	JLabel LbJEnTurno,Dado1,Dado2,LbMsg;
	JPanel PSur,PTablero,PTemporal,PMensajes;
	
	public VTablero() {
		super("Serpientes y Escaleras");
		setSize(860,720);
		ImageIcon Iaux=Rutinas.AjustarImagen("iconosye.jpg",60,60);
		Image icono=Iaux.getImage();
		setIconImage(icono);
		setLocationRelativeTo(null);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void HazInterfaz(int njug,ListaDBL<Casilla> L,Jugador[] Jug) {
		if(PSur!=null)
			remove(PSur);
		if(PTablero!=null)
			remove(PTablero);
		PSur=new JPanel();
		PSur.setLayout(new GridLayout(0,3));
		PTablero=new JPanel();
		PTablero.setLayout(new GridLayout(0,10));
		NodoDBL<Casilla> aux=L.getFin();
		while(aux!=null) {
			for(int i=0;i<10;aux=aux.getAnt(),i++) 
				PTablero.add(aux.Info);
			for(int i=1;i<10;aux=aux.getAnt(),i++);
			for(int i=10;i>0;aux=aux.getSig(),i--) 
				PTablero.add(aux.Info);
			for(int i=0;i<=10;aux=aux.getAnt(),i++);
		}
		add(PTablero,BorderLayout.CENTER);
		LbJEnTurno=new JLabel("Turno : 1");
		LbJEnTurno.setFont(new Font("Tahoma", 1, 15));
		PTemporal=new JPanel();
		PTemporal.setLayout(new GridLayout(0,3));
		for(int i=0;i<njug;i++)
			PTemporal.add(Jug[i]);
		PTemporal.setBorder(BorderFactory.createLineBorder(new Color(130, 130, 128),4));
		PSur.add(PTemporal);
		
		PMensajes=new JPanel();
		PMensajes.setLayout(new BoxLayout(PMensajes,BoxLayout.Y_AXIS));
		JLabel lb1=new JLabel(njug+" Jugadores",SwingConstants.RIGHT);
		lb1.setFont(new Font("Tahoma", 1, 15));
		PMensajes.add(lb1);
		PMensajes.add(LbJEnTurno);
		LbMsg=new JLabel("Tira los dados                      ");
		LbMsg.setFont(new Font("Tahoma", 1, 16));
		PMensajes.add(LbMsg);
		PMensajes.setBorder(BorderFactory.createLineBorder(new Color(130, 130, 128),4));
		PSur.add(PMensajes);
		
		JPanel P3=new JPanel();
		BtnTurno=new JButton("Tirar Dados");
		P3.add(BtnTurno,BorderLayout.NORTH);
		Dado1=new JLabel(Rutinas.AjustarImagen("dados/1.jpg",50,50));
		Dado2=new JLabel(Rutinas.AjustarImagen("dados/1.jpg",50,50));
		
		P3.add(Dado1,BorderLayout.EAST);
		P3.add(Dado2,BorderLayout.WEST);
		P3.setBorder(BorderFactory.createLineBorder(new Color(130, 130, 128),4));
		PSur.add(P3);
		
		PSur.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK,2),"HUD"));
		add(PSur,BorderLayout.SOUTH);
		setVisible(true);
	}
	
	public void ActualizaDados(int d1,int d2) {
		for(int i=1;i<=6;i++) {
			try {
				Thread.sleep(100);
				Dado1.setIcon(Rutinas.AjustarImagen("dados/"+i+".jpg",50,50));
				Dado1.update(Dado1.getGraphics());
				Dado2.setIcon(Rutinas.AjustarImagen("dados/"+i+".jpg",50,50));
				Dado2.update(Dado2.getGraphics());
			}catch(Exception e) {}
		}
		Dado1.setIcon(Rutinas.AjustarImagen("dados/"+d1+".jpg",50,50));
		Dado1.update(Dado1.getGraphics());
		Dado2.setIcon(Rutinas.AjustarImagen("dados/"+d2+".jpg",50,50));
		Dado2.update(Dado2.getGraphics());
	}

	public void setEscuchador(Controlador c) {
		C=c;
		BtnTurno.addActionListener(C);
		addKeyListener(C);
	}

	public void MoverJugador(Casilla[] ac,Jugador jug) {
		for(int i=0;i<ac.length;i++) {
			ac[i].add(jug);
			if(i!=0)
				ac[i-1].update(ac[i-1].getGraphics());
			else if(jug.getPosicion()!=null)
				jug.getPosicion().update(jug.getPosicion().getGraphics());
			else if(jug.getPosicion()==null) 
				PTemporal.update(PTemporal.getGraphics());
			ac[i].update(ac[i].getGraphics());
			try {
				Thread.sleep(350);
			}catch(Exception e) {}
		}
		jug.setPosicion(ac[ac.length-1]);
	}

	public void ActualizarLb(int jug) {
		LbJEnTurno.setText("Turno : "+(jug+1));
	}

	public void MensajeGano(int jug) {
		BtnTurno.setEnabled(false);
		removeKeyListener(C);
		JOptionPane.showMessageDialog(this, "Ha ganado el jugador "+(jug+1),"Felicidades!",JOptionPane.INFORMATION_MESSAGE);
	}

	public void ActualizarMensaje(String msg,Color c) {
		if(msg.compareTo(LbMsg.getText())!=0){
			LbMsg.setText(msg);
			LbMsg.setForeground(c);
			LbMsg.update(LbMsg.getGraphics());
			PMensajes.update(PMensajes.getGraphics());
			PSur.update(PSur.getGraphics());
			ActualizaContenedor();
		}
	}
	
	private void ActualizaContenedor(){
		if(getWidth()==720)
			setSize(860,721);
		else
			setSize(860,720);
	}

}
