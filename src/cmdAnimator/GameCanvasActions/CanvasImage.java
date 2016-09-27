package cmdAnimator.GameCanvasActions;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class CanvasImage implements ImageHandler{
	
	private BufferedImage image;
	private String imageFilename;
	private Point pointToAddImage;

	public CanvasImage(String imageFilename, Point pointToAddImage ) {
		this.pointToAddImage = pointToAddImage;
		this.imageFilename = imageFilename;
		setImage(imageFilename);
	}

	public boolean doesImageExist() {
		return this.image != null;
	}
	
	public void setImage(String filename) {

		try {
			File imageFile = new File(filename); // image file path
			image = ImageIO.read(imageFile);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			image = null;
		}
	}
	
	public BufferedImage getImage(){
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
