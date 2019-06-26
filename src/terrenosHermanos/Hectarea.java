package terrenosHermanos;

import javax.swing.*;
import java.awt.*;

public class Hectarea extends JLabel{
	
	public Hectarea(int numero,char estado) {
		super(numero+"-"+estado);
		setFont(new Font("Dubai",1,17));
		setForeground(Color.BLACK);
		setBackground(Color.WHITE);
		setOpaque(true);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
}
