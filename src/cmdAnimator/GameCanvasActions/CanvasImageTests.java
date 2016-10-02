package cmdAnimator.GameCanvasActions;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import cmdAnimator.GameUI.GameCanvasTests.AsNonApp;
import javafx.application.Application;
import javafx.stage.Stage;

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
		CanvasImage image = new CanvasImage("..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png", pointToAddImage );

		assertTrue(image.doesImageExist());
	}

	@BeforeClass
	public static void setUpClass() throws InterruptedException {
	    // Initialise Java FX
	    Thread t = new Thread("JavaFX Init Thread") {
	        public void run() {
	            Application.launch(AsNonApp.class, new String[0]);
	        }
	    };
	    t.setDaemon(true);
	    t.start();
	    Thread.sleep(500);
	}
}
