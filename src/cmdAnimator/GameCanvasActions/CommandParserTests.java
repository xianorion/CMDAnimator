package cmdAnimator.GameCanvasActions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import cmdAnimator.GUI;
import cmdAnimator.GameUI.GameCanvasTests.dummyApp;
import javafx.application.Application;
import javafx.stage.Stage;

public class CommandParserTests {

	GUI gui;
	String text;
	FrameAnimator animator;
	
	@Before
	public void setup(){
		gui = new GUI();
		animator = new FrameAnimator(8);
	}
	@Test
	public void ifUserInputIsEmptyReturnFalseForParseText() {
		text = "";
		assertFalse(CommandParser.parseText(gui, text, animator));
	}
	
	@Test
	public void ifUserEntersTextAddParserReturnsFalse(){
		text = "add";
		assertFalse(CommandParser.parseText(gui, text, animator));
	}
	
	@Test
	public void ifUserEntersAddtextWithMoreThanFourWordsParseReturnsFalse(){
		text = "add image image.gif (x,y) me";
		assertFalse(CommandParser.parseText(gui, text, animator));
	}
	
	@Test
	public void ifUserEntersAddImageValidImageToPointParseReturnsTrue(){
		text = "add image \"..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png\" (40,40)";
		assertTrue(CommandParser.parseText(gui, text, animator));
	}
	
	@Test
	public void ifUserEntersAddImageValidWithOutQuotationImageToPointParseReturnsFalse(){
		text = "add image ..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png (40,40)";
		assertFalse(CommandParser.parseText(gui, text, animator));
	}
	
	@Test
	public void ifUserEntersAddImageInValidImageToPointParseReturnsFalse(){
		text = "add image \"image.gif\" (40,40)";
		assertFalse(CommandParser.parseText(gui, text, animator));
	}
	
	@Test
	public void ifUserEntersImageFileWithMultipleQuotiationsReturnFalse(){
		text = "add image \"imag\"e.\"gif\" (40,40)";
		assertFalse(CommandParser.parseText(gui, text, animator));
	}
	
	@Test
	public void ifUserEntersAddImageValidImageToBadPointSyntaxParseReturnsFalse(){
		text = "add image \"image.gif\" (40.6,40)";
		assertFalse(CommandParser.parseText(gui, text, animator));
	}
	
	@Test
	public void ifUserEntersAddTextToPointParseReturnsTrue(){
		text = "add text \"hello\" (40,40)";
		assertTrue(CommandParser.parseText(gui, text, animator));
	}
	
	@Test
	public void ifUserEntersAddTextWithoutQuotationsToPointParseReturnsFalse(){
		text = "add text hello (40.6,40)";
		assertFalse(CommandParser.parseText(gui, text, animator));
	}
	
	@Test
	public void ifUserEntersAddTextToBadPointSyntaxParseReturnsFalse(){
		text = "add text \"hello\" (4.0,40)";
		assertFalse(CommandParser.parseText(gui, text, animator));
	}
	@Test 
	public void ifUserInputIsMultipleSpacesReturnsFalseForParseText(){
		text = "     ";
		assertFalse(CommandParser.parseText(gui, text, animator));
	}
	
	@Test
	public void ifUserTextIsAddFrameParseTextReturnsTrue(){
		text = "add Frame";
		assertTrue(CommandParser.parseText(gui, text, animator));
	}
	
	@Test
	public void totalNumberOfFramesIncreasesByOneWhenAddFrameIsCalled(){
		int totalFrames = animator.getTotalNumberOfFrames();
		text = "add frame";
		
		CommandParser.parseText(gui, text, animator);
		assertEquals(totalFrames + 1, animator.getTotalNumberOfFrames());
	}
	
	@Test
	public void totalNumberOfFramesDoesNotIncreaseWhenAddFrameOneTwoIsCalled(){
		int totalFrames = animator.getTotalNumberOfFrames();
		text = "add frame one two";
		
		CommandParser.parseText(gui, text, animator);
		assertEquals(totalFrames, animator.getTotalNumberOfFrames());
		
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
