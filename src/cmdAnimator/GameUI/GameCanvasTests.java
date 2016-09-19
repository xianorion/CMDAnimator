package cmdAnimator.GameUI;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import cmdAnimator.GameCanvasActions.CanvasImage;
import cmdAnimator.GameCanvasActions.CanvasText;

public class GameCanvasTests {
	
	GameCanvas canvas;
	Point point;
	
	@Before
	public void setup(){
		canvas  = new GameCanvas();
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
	public void addImageToCanvasAtSomePointIsPlacedAtCorrectPoint(){
		String filename = "C:\\Users\\Orion\\workspace\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png";
		canvas.addImage(new CanvasImage( filename ,point));
		
		assertTrue(canvas.getImagesToAdd().containsKey(point));
		assertEquals(canvas.getImagesToAdd().get(point).getImageFilename(), filename);
	}

	@Test
	public void ImageThatIsAddedCanBeRemoved(){
		String filename = "C:\\Users\\Orion\\workspace\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png";
		canvas.addImage(new CanvasImage( filename ,point));
		canvas.deleteImage(point);
		
		assertFalse(canvas.getImagesToAdd().containsKey(point));
	}

}
