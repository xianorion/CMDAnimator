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
	private int width;
	private int height;

	public CanvasImage(String imageFilename, Point pointToAddImage ) {
		this.pointToAddImage = pointToAddImage;
		this.imageFilename = imageFilename;
		this.width = 21;
		this.height = 20;
		setImage(imageFilename);
	}
	
	public CanvasImage(Point pointToAddImage, String imageFilename, int width, int height) {
		this.pointToAddImage = pointToAddImage;
		this.imageFilename = imageFilename;
		this.width = width;
		this.height = height;
	}

	public boolean doesImageExist(String filename) {
		return false;
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
