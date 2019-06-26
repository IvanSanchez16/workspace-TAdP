package torresHanoi;

public class Torre {
	private int x;
	private int AlturaAct;
	
	public Torre(int n) {
		switch(n) {
			case 0:
				x=115;
				break;
			case 1:
				x=345;
				break;
			case 2:
				x=575;
				break;
		}
		AlturaAct=30;
	}

	public int getX() {
		return x;
	}

	public int getAlturaAct() {
		return AlturaAct;
	}

	public void setAlturaAct(int alturaAct) {
		this.AlturaAct = alturaAct;
	}
}
