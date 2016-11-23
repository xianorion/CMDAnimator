package cmdAnimator.GameUI;

import java.awt.Point;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import cmdAnimator.GUI;
import cmdAnimator.GameCanvasActions.CanvasImage;
import cmdAnimator.GameCanvasActions.CanvasText;
import cmdAnimator.GameObjects.UserTextConstants;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class GameCanvas extends Canvas {
	public final static int HEIGHT=300;
	public final static int WIDTH=600;
	HashMap<Point, CanvasText> textToWrite;
	HashMap<Point, CanvasImage> imagesToAdd;
	private CanvasImage background;
	
	public GameCanvas(CanvasImage backgroundImg){
		super(WIDTH, HEIGHT);
		textToWrite = new HashMap<Point, CanvasText>();
		imagesToAdd = new HashMap<Point, CanvasImage>();
		GraphicsContext g = this.getGraphicsContext2D();
		background = backgroundImg;
	}
	
	
	public void addText(CanvasText newText){
		textToWrite.put(newText.getPointToAddTextTo(), newText);
		this.updatePane();
	}
	
	public void deleteText(Point key){
		textToWrite.remove(key);
		this.updatePane();
	}
	
	//defaults for the canvas
	public void updatePane(){
		GraphicsContext g = this.getGraphicsContext2D();
		g.clearRect(0, 0, this.getWidth(), this.getHeight());		
		//set background image if has-----
		if(background != null)
			g.drawImage(background.getImage().getImage(), 0, 0, WIDTH, HEIGHT);
		//----------------------------------------------------

		Iterator<Entry<Point, CanvasText>> it = textToWrite.entrySet().iterator();
		while (it.hasNext()) {
			CanvasText text = it.next().getValue();
			//set up drawing text properties
			g.setFont(text.getFont());
			g.setFill(text.getColor());
			//add text to screen based on drawing properties
			paintTextToScreenBasedOnTextWrappingProperties(g, text);
			//g.fillText(text.getTextToAdd(), text.getPointToAddTextTo().x, text.getPointToAddTextTo().y);

		}
		// adding images
		Iterator<Entry<Point, CanvasImage>> iterator2 = imagesToAdd.entrySet().iterator();
		while (iterator2.hasNext()) {
			CanvasImage image = iterator2.next().getValue(); 
			g.drawImage(image.getImage().getImage(), image.getPointToAddImage().x, image.getPointToAddImage().y, image.getWidth(),image.getHeight());
		}
		
	}
	
	protected int paintTextToScreenBasedOnTextWrappingProperties(GraphicsContext g, CanvasText text) {
		//calculate the length of letter and set up variables
		char[] textArray = text.getTextToAdd().toCharArray();
		double lengthOfLetter = g.getFont().getSize();
		double x = text.getPointToAddTextTo().x;
		double y = text.getPointToAddTextTo().y;
		int timesWrapped = 0;
				
		//calculate the length size of the text
		double sizeOfText = lengthOfLetter*text.getTextToAdd().length();
		//if length + x point on screen is > length of screen, cut text based on avalible length
		if(sizeOfText+ text.getPointToAddTextTo().getX() > WIDTH){
			for(int i =0; i < textArray.length; i++){
				//paint each letter of the string and wrap when necessary
				if(x +lengthOfLetter > WIDTH){
					x = 1;
					y = y + lengthOfLetter;
					timesWrapped++;
				}
				g.fillText(String.valueOf(textArray[i]), x,y);
				x=x+lengthOfLetter;
				
			}
		}else
			g.fillText(text.getTextToAdd(), text.getPointToAddTextTo().x, text.getPointToAddTextTo().y);
	return timesWrapped;
	}


	public HashMap<Point, CanvasText> getTextToWrite(){
		return textToWrite;
	}
	
	//clears the canvas
	//We need to remove elements from all of the hashmaps
	public void clearCanvas(){
		textToWrite.clear();
		imagesToAdd.clear();
		background = null;
		this.updatePane();
		}

   
	public boolean addImage(CanvasImage canvasImage) {
		if (canvasImage.doesImageExist()) {
			imagesToAdd.put(canvasImage.getPointToAddImage(), canvasImage);
			this.updatePane();
			return true;
		} 
		return false;
	}


	public HashMap<Point, CanvasImage> getImagesToAdd() {
		return imagesToAdd;
	}


	public void deleteImage(Point point) {
		imagesToAdd.remove(point);
		this.updatePane();
	}
	
	public void setTextToWrite(HashMap<Point, CanvasText> ttw){
		textToWrite = ttw ;
	}
	
	
	public void setImagesToAdd(HashMap<Point, CanvasImage> ita){
		imagesToAdd=ita;
	}
	
	public CanvasImage getBackgroundImage(){
		return background;
	}

	public boolean setBackgroundImage(CanvasImage image){
		if (image != null && image.doesImageExist()) {
			
			System.out.println("Background image set");
			background = image;
			this.updatePane();
			return true;
		} 
		System.out.println("background image is set to null in setbackground");
		background = null;
		return false;	
	}

	public static GameCanvas copy(GameCanvas screen) {
		GameCanvas newCanvas;
		if(screen.background != null){
			newCanvas = new GameCanvas(new CanvasImage(screen.background.getImageFilename(), new Point(0,0)));
			System.out.println("background is set to "+ newCanvas.getBackgroundImage());
		}else{
			newCanvas = new GameCanvas(null);
		}
		if (screen != null) {
			Iterator<Entry<Point, CanvasText>> it = screen.textToWrite.entrySet().iterator();
			while (it.hasNext()) {
				CanvasText text = it.next().getValue();
				newCanvas.addText(text);
			}

			// adding images
			Iterator<Entry<Point, CanvasImage>> iterator2 = screen.imagesToAdd.entrySet().iterator();
			while (iterator2.hasNext()) {
				CanvasImage image = iterator2.next().getValue();
				newCanvas.addImage(image);
			}
		}
		return newCanvas;
	}
	

}
