package torresHanoi;

import java.awt.*;

import utilería.Rutinas;

public class Disco {
	
	private Color color;
	private int x;
	private int y;
	private int ancho;
	
	public Disco(int x,int y,int ancho) {
		color=new Color(Rutinas.nextInt(0,200),Rutinas.nextInt(0,200),Rutinas.nextInt(0,200));
		this.x=x;
		this.y=y;
		this.ancho=ancho;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}
	
	public int getAncho() {
		return ancho;
	}
}
