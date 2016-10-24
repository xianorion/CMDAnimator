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
	final static int HEIGHT=300;
	final static int WIDTH=600;
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
		//set up drawing properties for text
		g.setFont(UserTextConstants.getInstance().getFont());
		g.setFill(UserTextConstants.getInstance().getTextColorFill());
		
		Iterator<Entry<Point, CanvasText>> it = textToWrite.entrySet().iterator();
		while (it.hasNext()) {
			CanvasText text = it.next().getValue();
			g.fillText(text.getTextToAdd(), text.getPointToAddTextTo().x, text.getPointToAddTextTo().y);

		}
		// adding images
		Iterator<Entry<Point, CanvasImage>> iterator2 = imagesToAdd.entrySet().iterator();
		while (iterator2.hasNext()) {
			CanvasImage image = iterator2.next().getValue();
			g.drawImage(image.getImage().getImage(), image.getPointToAddImage().x, image.getPointToAddImage().y, 50,50);
		}
		
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
