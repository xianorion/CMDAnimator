package bitformer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameCanvas extends Canvas {
	
	//defaults for the canvas
	@Override
	public void paint(Graphics g){
		g.setColor(Color.BLUE);
		g.setFont(new Font("AR DESTINE", Font.ITALIC, 15));
		g.drawString("my first canvas", 20, 20);
	}

}
