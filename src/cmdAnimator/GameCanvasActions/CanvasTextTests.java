package cmdAnimator.GameCanvasActions;


import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import cmdAnimator.GameObjects.UserTextConstants;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CanvasTextTests {
	
	UserTextConstants utc;
	
	@Before
	public void setup(){
		utc =UserTextConstants.getInstance();
		utc.setFontSize(12);
		utc.setTextColor("Black");
		utc.setTextFont("Arial");
	}
	
	@Test
	public void textAddedHasArialTwelveAndFontChangedToChillerNewTextAddedHasFontChiller() {
		CanvasText text = new CanvasText("text", new Point(4, 4));
		utc.setTextFont("chiller");
		CanvasText text2 = new CanvasText("text", new Point(4, 15));

		assertEquals(new Font("Arial", 12), text.getFont());
		assertEquals(new Font("chiller", 12), text2.getFont());
	}
	
	@Test
	public void textAddedHasArialTwelveBlackAndFontChangedToSizeFifteenNewTextAddedIsArialFifteenBlack(){
		CanvasText text = new CanvasText("text", new Point(4, 4));
		utc.setFontSize(15);
		CanvasText text2 = new CanvasText("text", new Point(4, 15));

		assertEquals(new Font("Arial", 12), text.getFont());
		assertEquals(new Font("Arial", 15), text2.getFont());
		assertEquals(Color.BLACK, text.getColor());
		assertEquals(Color.BLACK, text2.getColor());
	}
	
	
	@Test
	public void textAddedHasArialTwelveAndFontChangedToChillerAndColorRedNewTextAddedHasFontChillerAndColorRed() {
		CanvasText text = new CanvasText("text", new Point(4, 4));
		utc.setTextFont("chiller");
		utc.setTextColor("Red");
		CanvasText text2 = new CanvasText("text", new Point(4, 15));

		assertEquals(new Font("Arial", 12), text.getFont());
		assertEquals(Color.BLACK, text.getColor());
		assertEquals(new Font("chiller", 12), text2.getFont());
		assertEquals(Color.RED, text2.getColor());
	}
	
	@Test
	public void textAddedHasArialTwelveBlackAndFontChangedToChillerTwentyGreenNewTextAddedHasTheseProperties(){
		CanvasText text = new CanvasText("text", new Point(4, 4));
		utc.setTextFont("chiller");
		utc.setTextColor("Green");
		utc.setFontSize(15);
		CanvasText text2 = new CanvasText("text", new Point(4, 15));

		assertEquals(new Font("Arial", 12), text.getFont());
		assertEquals(Color.BLACK, text.getColor());
		assertEquals(new Font("chiller", 15), text2.getFont());
		assertEquals(Color.GREEN, text2.getColor());
	}

}
