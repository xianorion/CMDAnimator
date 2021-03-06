package cmdAnimator.GameCanvasActionsTests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import cmdAnimator.GUI;
import cmdAnimator.GameGui;
import cmdAnimator.GameCanvasActions.CommandParser;
import cmdAnimator.GameCanvasActions.FrameAnimator;
import cmdAnimator.GameCanvasActions.GameAnimator;
import cmdAnimator.GameUI.GameCanvasTests.dummyApp;
import javafx.application.Application;
import javafx.stage.Stage;

public class CommandParserTests {

	GameGui gui;
	String text;
	FrameAnimator animator;
	
	@Before
	public void setup(){
		gui = GUI.getInstance();
		animator = GameAnimator.getInstance();
		animator.setFPS(8);
	}
	@Test
	public void ifUserInputIsEmptyReturnFalseForParseText() {
		text = "";
		assertFalse(CommandParser.parseText(text));
	}
	
	@Test
	public void ifUserEntersTextAddParserReturnsFalse(){
		text = "add";
		assertFalse(CommandParser.parseText( text));
	}
	
	@Test
	public void ifUserEntersAddtextWithMoreThanFourWordsParseReturnsFalse(){
		text = "add image image.gif (x,y) me";
		assertFalse(CommandParser.parseText(text));
	}
	
	@Test
	public void ifUserEntersAddImageValidImageToPointParseReturnsTrue(){
		text = "add image \"..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png\" (40,40)";
		assertTrue(CommandParser.parseText(text));
	}
	
	@Test
	public void ifUserEntersAddImageInValidImageToPointParseReturnsFalse(){
		text = "add image \"image.gif\" (40,40)";
		assertFalse(CommandParser.parseText(text));
	}
	
	@Test
	public void ifUserEntersImageFileWithMultipleQuotiationsReturnFalse(){
		text = "add image \"imag\"e.\"gif\" (40,40)";
		assertFalse(CommandParser.parseText(text));
	}
	
	@Test
	public void ifUserEntersAddImageValidImageToBadPointSyntaxParseReturnsFalse(){
		text = "add image \"image.gif\" (40.6,40)";
		assertFalse(CommandParser.parseText(text));
	}
	
	@Test
	public void ifUserEntersAddTextToPointParseReturnsTrue(){
		text = "add text \"hello\" (40,40)";
		assertTrue(CommandParser.parseText(text));
	}
	
	@Test
	public void returnTrueWhenHelloQuotationWorldSurroundedByQuotationsIsPassed(){
		text = "add text \"Hello World\" (45,40)";
		
		assertTrue(CommandParser.parseText(text));
	}
	
	@Test
	public void returnFalseWhenQuotationHelloSpaceWorldSurroundedByQuotationsIsPassed(){
		text = "add text \"Hello World (45,40)";
		
		assertFalse(CommandParser.parseText(text));
	}
	
	@Test
	public void returnFalseWhenHelloQuotationWorldSurroundedByQuotationsIsPassed(){
		text = "add text \"Hello\"World\" (45,40)";
		
		assertFalse(CommandParser.parseText(text));
	}
	
	@Test
	public void returnFalseWhenUnknownCommandIsPassed() {
		text = "app text \"Hello World\" (45,40)";

		assertFalse(CommandParser.parseText(text));
	}

	@Test
	public void ifUserEntersAddTextWithoutQuotationsToPointParseReturnsFalse(){
		text = "add text hello (40.6,40)";
		assertFalse(CommandParser.parseText(text));
	}
	
	@Test
	public void ifUserEntersAddTextToBadPointSyntaxParseReturnsFalse(){
		text = "add text \"hello\" (4.0,40)";
		assertFalse(CommandParser.parseText(text));
	}
	
	@Test
	public void ifUserEntersAddTextWithMultipleWordsToPointParseReturnsTrue(){
		text = "add text \"hello world\" (40,40)";
		assertTrue(CommandParser.parseText(text));
	}
	
	@Test 
	public void ifUserInputIsMultipleSpacesReturnsFalseForParseText(){
		text = "     ";
		assertFalse(CommandParser.parseText(text));
	}
	
	@Test
	public void ifUserTextIsAddFrameParseTextReturnsTrue(){
		text = "add Frame";
		assertTrue(CommandParser.parseText(text));
	}
	
	@Test
	public void totalNumberOfFramesIncreasesByOneWhenAddFrameIsCalled(){
		int totalFrames = animator.getTotalNumberOfFrames();
		text = "add frame";
		
		CommandParser.parseText(text);
		assertEquals(totalFrames + 1, animator.getTotalNumberOfFrames());
	}
	
	@Test
	public void totalNumberOfFramesDoesNotIncreaseWhenAddFrameOneTwoIsCalled(){
		int totalFrames = animator.getTotalNumberOfFrames();
		text = "add frame one two";
		
		CommandParser.parseText(text);
		assertEquals(totalFrames, animator.getTotalNumberOfFrames());
		
	}
	
	@Test
	public void ifUserEntersTextRemoveFrameOneParserReturnsTrue(){
		text = "remove frame 1";
		assertTrue(CommandParser.parseText( text));
	}
	
	@Test
	public void ifUserEntersTextRemoveParserReturnsFalse(){
		text = "remove";
		assertFalse(CommandParser.parseText( text));
	}
	
	@Test
	public void ifUserEntersTextGotoParserReturnsFalse(){
		text = "goto";
		assertFalse(CommandParser.parseText( text));
	}
	
	@Test
	public void ifUserEntersTextGotoFrameOneParserReturnsTrue(){
		text ="goto frame 1";
		assertTrue(CommandParser.parseText(text));
	}
	
	@Test
	public void isUserEntersFPS25ParserReturnsTrueAndFPSIs25(){
		text = "fps 25";
		
		assertTrue(CommandParser.parseText(text));
		assertEquals(25, animator.getFPS());
	}
	
	@Test 
	public void ifUserEntersDoneParserIsTrue(){
		text = "Done";
		
		assertTrue(CommandParser.parseText(text));
	}
	
	@Test 
	public void ifUserEntersHelpParserIsTrue(){
		text = "help";		
		assertTrue(CommandParser.parseText(text));
	}
	
	@Test 
	public void ifUserEntersHelpAddParserIsTrue(){
		text = "help add";		
		assertTrue(CommandParser.parseText(text));
	}
	
	@Test 
	public void ifUserEntersHelpRemoveParserIsTrue(){
		text = "help remove";		
		assertTrue(CommandParser.parseText(text));
	}
	
	@Test 
	public void ifUserEntersHelpMeParserIsFalse(){
		text = "help me";		
		assertFalse(CommandParser.parseText(text));
	}
	
	@Test 
	public void ifUserEntersRunFileNameParserIsTrue(){
		text = "run \"..\\TextBasedGame\\src\\resource\\textCommands\\batchfile.txt\"";		
		assertTrue(CommandParser.parseText(text));
	}
	
	@Test
	public void ifUserEntersRunParserIsFalse(){
		text = "run";		
		assertFalse(CommandParser.parseText(text));
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
