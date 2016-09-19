package cmdAnimator.GameUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JPanel;

import cmdAnimator.GameCanvasActions.CanvasImage;
import cmdAnimator.GameCanvasActions.CanvasText;

public class GameCanvas extends JPanel {
	
	HashMap<Point, CanvasText> textToWrite;
	HashMap<Point, CanvasImage> imagesToAdd;
	//GameCanvasImageWriter images;
	
	public GameCanvas(){
		textToWrite = new HashMap<Point, CanvasText>();
		imagesToAdd = new HashMap<Point, CanvasImage>();
		
	}
	
	
	public void addText(CanvasText newText){
		textToWrite.put(newText.getPointToAddTextTo(), newText);
		this.repaint();
	}
	
	public void deleteText(Point key){
		textToWrite.remove(key);
		this.repaint();
	}
	
	//defaults for the canvas
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//g.setColor(Color.BLACK);
		//g.setFont(new Font("ARIAL", Font.ITALIC, 15));
		
		//adds all the text every added from the addText function onto the canvas
		Iterator<Entry<Point, CanvasText>> it = textToWrite.entrySet().iterator();
		while(it.hasNext()) {
			CanvasText text = it.next().getValue();
			  g.drawString(text.getTextToAdd(), text.getPointToAddTextTo().x, text.getPointToAddTextTo().y);
			}
		
		//adding images
		Iterator<Entry<Point, CanvasImage>> iterator2 = imagesToAdd.entrySet().iterator();
		while(iterator2.hasNext()) {
			CanvasImage image = iterator2.next().getValue();
			g.drawImage(image.getImage(), image.getPointToAddImage().x, image.getPointToAddImage().y, this);
			}
	}
	
	public HashMap<Point, CanvasText> getTextToWrite(){
		return textToWrite;
	}
	
	//clears the canvas
	//We need to remove elements from all of the hashmaps
	public void clearCanvas(){
		textToWrite.clear();
		repaint();
	}

   
	public void addImage(CanvasImage canvasImage) {
		// TODO Auto-generated method stub
		if(canvasImage.getImage() != null)
			imagesToAdd.put(canvasImage.getPointToAddImage(), canvasImage);
		else
			System.out.println("is null");
		this.repaint();
	}


	public HashMap<Point, CanvasImage> getImagesToAdd() {
		// TODO Auto-generated method stub
		return imagesToAdd;
	}


	public void deleteImage(Point point) {
		// TODO Auto-generated method stub
		imagesToAdd.remove(point);
		this.repaint();
	}

}
