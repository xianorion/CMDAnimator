package cmdAnimator.GameUI;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import cmdAnimator.GameCanvasActions.GameCanvasTextWriter;

public class GameCanvasTests {
	
	GameCanvas canvas;
	Point point;
	
	@Before
	public void setup(){
		canvas  = new GameCanvas();
		point = new Point(1,1);
		canvas.addText(new GameCanvasTextWriter("Test Text", point));
	}

	@Test
	public void TextIsAddedToCanvas() {
		assertTrue(canvas.getTextToWrite().containsKey("Test Text"));
		assertEquals(canvas.getTextToWrite().get("Test Text").getPointToAddTextTo(), point );
	}
	
	@Test
	public void TextThatIsAddedCanBeRemoved(){
		canvas.deleteText("Test Text");
		
		assertFalse(canvas.getTextToWrite().containsKey("Test Text"));
	}
	
	@Test
	public void TwoTextsArePresentWhenAdded(){
		Point point2 = new Point(5,5);
		canvas.addText(new GameCanvasTextWriter("Hi", point2));
		
		assertTrue(canvas.getTextToWrite().containsKey("Test Text"));
		assertEquals(canvas.getTextToWrite().get("Test Text").getPointToAddTextTo(), point );
		
		assertTrue(canvas.getTextToWrite().containsKey("Hi"));
		assertEquals(canvas.getTextToWrite().get("Hi").getPointToAddTextTo(), point2 );
	
	}

	

}
