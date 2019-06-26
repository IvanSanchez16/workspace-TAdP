package torresHanoi;

import java.awt.*;

import utilería.Rutinas;

public class Lienzo extends Canvas{
	
	Image backImage;
	Graphics g;
	Disco[] AD;
	Image tubo=Rutinas.AjustarImagen("imagenes/tubomadera.png",30,250).getImage();
	Image madera=Rutinas.AjustarImagen("imagenes/madera.jpg",700,30).getImage();
	//Image fondo=Rutinas.AjustarImagen("imagenes/fondoF.jpg",700,300).getImage();
	
	public Lienzo(Image img) {
		setBounds(50,100,700,300);
		backImage=img;
		g=backImage.getGraphics();
		Dibuja();
	}
	
	public void Dibuja() {
		super.paint(g);
		//g.drawImage(fondo,0,0,700,300,null);
		g.setColor(Color.WHITE);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(tubo,100,20,30,280,null);
		g.drawImage(tubo,330,20,30,280,null);
		g.drawImage(tubo,560,20,30,280,null);
		g.drawImage(madera,0,270,700,30,null);
		if(AD!=null) {
			for(int i=0 ; i<AD.length ; i++) {
				g.setColor(AD[i].getColor());
				g.fillRect(AD[i].getX(),AD[i].getY(),AD[i].getAncho(),15);
			}
		}
		repaint();
	}
	
	public void AsignaArreglo(Disco[] d) {
		AD=d;
		Dibuja();
	}
	
	public void paint(Graphics g) {
		g.drawImage(backImage,0,0,getWidth(),getHeight(),this);
	}
	
}
