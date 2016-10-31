package cmdAnimator.GameCanvasActionsTests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import cmdAnimator.GameCanvasActions.CanvasImage;
import cmdAnimator.GameUI.GameCanvasTests.dummyApp;
import javafx.application.Application;
import javafx.stage.Stage;

public class CanvasImageTests {

	@Test
	public void isImageDoesntExistDoesImageExistReturnsFalse(){
		Point pointToAddImage =  new Point(5,5);
		CanvasImage image = new CanvasImage("image.jpeg", pointToAddImage);

		assertFalse(image.doesImageExist());
	}
	
	@Test
	public void isImageDoesExistDoesImageExistReturnsTrue(){
		Point pointToAddImage =  new Point(5,5);
		CanvasImage image = new CanvasImage("..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png", pointToAddImage );

		assertTrue(image.doesImageExist());
	}

	public static class dummyApp extends Application {
	    @Override
	    public void start(Stage primaryStage) throws Exception {
	    }
	}

	@BeforeClass
	public static void initJFX() {
	    Thread t = new Thread("JavaFX Init Thread") {
	        public void run() {
	            Application.launch(dummyApp.class, new String[0]);
	        }
	    };
	    t.setDaemon(true);
	    t.start();
	}
}
