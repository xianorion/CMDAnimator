package cmdAnimator.GameUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JPanel;

import cmdAnimator.GameCanvasActions.GameCanvasTextWriter;

public class GameCanvas extends JPanel {
	
	HashMap<String, GameCanvasTextWriter> textToWrite;
	//GameCanvasImageWriter images;
	
	public GameCanvas(){
		textToWrite = new HashMap<String, GameCanvasTextWriter>();
		
	}
	
	
	public void addText(GameCanvasTextWriter newText){
		textToWrite.put(newText.getTextToAdd(), newText);
		this.repaint();
	}
	
	public void deleteText(String key){
		textToWrite.remove(key);
	}
	
	//defaults for the canvas
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//g.setColor(Color.BLACK);
		//g.setFont(new Font("ARIAL", Font.ITALIC, 15));
		
		//adding text
		Iterator<Entry<String, GameCanvasTextWriter>> it = textToWrite.entrySet().iterator();
		while(it.hasNext()) {
			GameCanvasTextWriter text = (GameCanvasTextWriter)it.next().getValue();
			  g.drawString(text.getTextToAdd(), text.getPointToAddTextTo().x, text.getPointToAddTextTo().y);
			}
		
		//adding images
	}
	
	public HashMap<String, GameCanvasTextWriter> getTextToWrite(){
		return textToWrite;
	}
	

}
