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

import cmdAnimator.GameCanvasActions.CanvasText;

public class GameCanvas extends JPanel {
	
	HashMap<String, CanvasText> textToWrite;
	//GameCanvasImageWriter images;
	
	public GameCanvas(){
		textToWrite = new HashMap<String, CanvasText>();
		
	}
	
	
	public void addText(CanvasText newText){
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
		
		//adds all the text every added from the addText function onto the canvas
		Iterator<Entry<String, CanvasText>> it = textToWrite.entrySet().iterator();
		while(it.hasNext()) {
			CanvasText text = it.next().getValue();
			  g.drawString(text.getTextToAdd(), text.getPointToAddTextTo().x, text.getPointToAddTextTo().y);
			}
		
		//adding images
	}
	
	public HashMap<String, CanvasText> getTextToWrite(){
		return textToWrite;
	}
	
	//clears the canvas
	//We need to remove elements from all of the hashmaps
	public void clearCanvas(){
		textToWrite.clear();
		repaint();
	}

}
