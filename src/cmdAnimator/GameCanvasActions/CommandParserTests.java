package cmdAnimator.GameCanvasActions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
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
	public void ifUserEntersAddImageInValidImageToPointParseReturnsFalse(){
		text = "add image \"image.gif\" (40,40)";
		assertFalse(CommandParser.parseText(gui, text, animator));
	}
	
	@Test
	public void ifUserEntersAddImageValidImageToBadPointSyntaxParseReturnsFalse(){
		text = "add image \"image.gif\" (40.6,40)";
		assertFalse(CommandParser.parseText(gui, text, animator));
	}
	
	@Test
	public void ifUserEntersAddTextToPointParseReturnsTrue(){
		
	}
	
	@Test
	public void ifUserEntersAddTextToBadPointSyntaxParseReturnsFalse(){
		
	}
	@Test 
	public void ifUserInputIsMultipleSpacesReturnsFalseForParseText(){
		text = "     ";
		assertFalse(CommandParser.parseText(gui, text, animator));
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
