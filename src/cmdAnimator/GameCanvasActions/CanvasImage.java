package cmdAnimator.GameCanvasActions;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.scene.image.ImageView;

public class CanvasImage implements ImageHandler{
	
	private ImageView image;
	private final int DEFAULT_HEIGHT_AND_WIDTH = 50;
	private String imageFilename;
	private Point pointToAddImage;
	BufferedImage imageBuffer;
	private int height;
	private int width;

	public CanvasImage(String imageFilename, Point pointToAddImage ) {
		this.pointToAddImage = pointToAddImage;
		this.imageFilename = imageFilename;
		image = new ImageView();
		setImage(imageFilename);
		this.height = DEFAULT_HEIGHT_AND_WIDTH;
		this.width =  DEFAULT_HEIGHT_AND_WIDTH;
	}
	
	public CanvasImage(String imageFilename, Point pointToAddImage, int givenHeight, int givenWidth ) {
		this.pointToAddImage = pointToAddImage;
		this.imageFilename = imageFilename;
		image = new ImageView();
		setImage(imageFilename);
		this.height = givenHeight;
		this.width =  givenWidth;
	}

	public boolean doesImageExist(){
		return this.imageBuffer != null;
	}
	
	public void setImage(String filename) {
		File imagePath = new File(filename);
		//System.out.println(imagePath.toURI().toString());
		//System.out.println(filename);
		try {
			imageBuffer = ImageIO.read(imagePath);
		} catch (IOException e) { //if image DNE return after setting both image instances to null
			image = null;
			imageBuffer = null;
			return;
		}
		System.out.println(imagePath.toURI().toString());
		image = new ImageView(imagePath.toURI().toString());
		
	}
	
	public ImageView getImage(){
		return image;
	}
	
	public Point getPointToAddImage(){
		return pointToAddImage;
	}
	
	public String getImageFilename(){
		return imageFilename;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	public void resizeImage() {
		// TODO Auto-generated method stub
		
	}

}
