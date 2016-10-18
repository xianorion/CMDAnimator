package cmdAnimator.GameUI;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cmdAnimator.GUI;
import cmdAnimator.GameGui;
import cmdAnimator.GameCanvasActions.CanvasImage;
import cmdAnimator.GameCanvasActions.CanvasText;
import cmdAnimator.GameCanvasActions.FrameAnimator;
import cmdAnimator.GameCanvasActions.GameAnimator;
import cmdAnimator.GameCanvasActions.InvalidCommandException;
import javafx.application.Application;
import javafx.stage.Stage;

public class GameCanvasTests {
	
	GameCanvas canvas;
	FrameAnimator anime;
	Point point;
	
	@Before
	public void setup(){
		canvas  = new GameCanvas(null);
		point = new Point(1,1);
		canvas.addText(new CanvasText("Test Text", point));
	}

	@Test
	public void TextIsAddedToCanvasAtParticularPoint() {
		assertTrue(canvas.getTextToWrite().containsKey(point));
		assertEquals(canvas.getTextToWrite().get(point).getTextToAdd(), "Test Text");
	}
	
	@Test
	public void TextThatIsAddedCanBeRemoved(){
		canvas.deleteText(point);
		
		assertFalse(canvas.getTextToWrite().containsKey("Test Text"));
	}
	
	@Test
	public void TwoTextsArePresentWhenAdded(){
		Point point2 = new Point(5,5);
		canvas.addText(new CanvasText("Hi", point2));
		
		assertTrue(canvas.getTextToWrite().containsKey(point));
		assertEquals(canvas.getTextToWrite().get(point).getTextToAdd(), "Test Text" );
		
		assertTrue(canvas.getTextToWrite().containsKey(point2));
		assertEquals(canvas.getTextToWrite().get(point2).getTextToAdd(), "Hi" );
	
	}
	
	@Test
	public void addNullImageToCanvasAndItIsntAdded(){
		canvas.addImage(new CanvasImage("null.png",point));
		
		assertFalse(canvas.getImagesToAdd().containsKey(point));
	}
	
	@Test
	public void addImageToCanvasAtSomePointIsPlacedAtCorrectPoint() {
		String filename = "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png";
		canvas.addImage(new CanvasImage( filename ,point));
		
		assertTrue(canvas.getImagesToAdd().containsKey(point));
		assertEquals(canvas.getImagesToAdd().get(point).getImageFilename(), filename);
	}

	@Test
	public void ImageThatIsAddedCanBeRemoved() {
		String filename = "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png";
		canvas.addImage(new CanvasImage( filename ,point));
		canvas.deleteImage(point);
		
		assertFalse(canvas.getImagesToAdd().containsKey(point));
	}
	
	@Test
	public void whenCanvasClearArrayListsAreEmptyAndBackgroundIsNull(){
		String filename = "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png";
		canvas.addImage(new CanvasImage( filename ,point));
		
		canvas.addText(new CanvasText("Hi", new Point(56,56)));
		
		canvas.clearCanvas();
		assertNull(canvas.getBackgroundImage());
		assertEquals(0, canvas.getImagesToAdd().size());
		assertEquals(0, canvas.getTextToWrite().size());
	}
	
	@Test
	public void afterCopyAndClearOfCanvasWithBackgroundNewCopiesBackgroundImageShouldStillBeSet(){
		canvas.setBackgroundImage(new CanvasImage( "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png", new Point(0,0)));
		GameCanvas newCanvas = GameCanvas.copy(canvas);
		
		canvas.clearCanvas();
		assertEquals("..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png", newCanvas.getBackgroundImage().getImageFilename());
	}
	
	@Test
	public void afterCopyAndClearOfCanvasWithoutBackgroundNewCopiesBackgroundImageShouldBeNull(){
		canvas.setBackgroundImage(null);
		GameCanvas newCanvas = GameCanvas.copy(canvas);
		
		canvas.clearCanvas();
		assertNull(newCanvas.getBackgroundImage());
	
	}
	
	@Test
	public void afterClearTheFrameBackgroundShouldBeClear(){
		canvas.setBackgroundImage(new CanvasImage( "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png", new Point(0,0)));
		canvas.clearCanvas();
		
		assertNull(canvas.getBackgroundImage());
	}
	

		
	@Test
	public void copyCopiesDataIntoAnotherCanvasCorrectly(){
		
	}
	
	public static class dummyApp extends Application {
	    @Override
	    public void start(Stage primaryStage) throws Exception {
	    }
	}

	@BeforeClass
	public static void initJFX() throws InterruptedException {
	    Thread t = new Thread("JavaFX Init Thread") {
	        public void run() {
	            Application.launch(dummyApp.class, new String[0]);
	        }
	    };
	    t.setDaemon(true);
	    t.start();
	    Thread.sleep(500);
	}

}
