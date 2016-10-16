package cmdAnimator.GameCanvasActions;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.scene.image.ImageView;

public class CanvasImage implements ImageHandler{
	
	private ImageView image;
	private String imageFilename;
	private Point pointToAddImage;
	BufferedImage imageBuffer;

	public CanvasImage(String imageFilename, Point pointToAddImage ) {
		this.pointToAddImage = pointToAddImage;
		this.imageFilename = imageFilename;
		image = new ImageView();
		setImage(imageFilename);
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

	public void resizeImage() {
		// TODO Auto-generated method stub
		
	}

}
