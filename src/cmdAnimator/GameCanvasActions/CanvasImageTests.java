package cmdAnimator.GameCanvasActions;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

public class CanvasImageTests {

	@Test
	public void isImageDoesntExistDoesImageExistReturnsFalse() {
		Point pointToAddImage =  new Point(5,5);
		CanvasImage image = new CanvasImage("image.jpeg", pointToAddImage);

		assertFalse(image.doesImageExist());
	}
	
	@Test
	public void isImageDoesExistDoesImageExistReturnsTrue() {
		Point pointToAddImage =  new Point(5,5);
		CanvasImage image = new CanvasImage("C:\\Users\\Orion\\workspace\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png", pointToAddImage );

		assertTrue(image.doesImageExist());
	}

}
