package cmdAnimator.GameCanvasActions;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import cmdAnimator.GUI;
import cmdAnimator.GameGui;
import cmdAnimator.GameCanvasActions.FrameAnimatorTests.dummyApp;
import javafx.application.Application;
import javafx.stage.Stage;

public class AddCommandExecutorTests {
	String text;
	Point point;
	int x,y;
	AddCommandExecutor ACE;
	@Before
	public void setup(){
		ACE= new AddCommandExecutor();
	}
	
	@Test
	public void returnPointWithX5andy3WhenStringOfFiveandThreeAreEnteredInCorrectFormat() {
		text = "(5,3)";
		point = ACE.convertStringToPoint(text);
		
		assertEquals(new Point(5,3), point);
	}
	
	@Test
	public void returnPointWithX40andy30WhenStringOfFortyandThirtyAreEnteredInCorrectFormat() {
		text = "(40,30)";
		point = ACE.convertStringToPoint(text);
		
		assertEquals(new Point(40,30), point);
	}
	
	@Test
	public void returnNullWhenStringOfFortyAndAHalfandThirtyAreEnteredInCorrectFormat() {
		text = "(40.5,30)";
		point = ACE.convertStringToPoint(text);
		
		assertEquals(null, point);
	}
	
	@Test
	public void returnNullWhenStringOf40and30And50AreEnteredInCorrectFormat() {
		text = "(40,30,50)";
		point = ACE.convertStringToPoint(text);
		
		assertEquals(null, point);
	}
	
	@Test (expected = InvalidCommandException.class)
	public void shouldThrowExecptionIfTextIsJustEntered() throws InvalidCommandException{
		String[] textToEnter = {"text"};
		ACE.execute(textToEnter);
	}
	
	@Test (expected = InvalidCommandException.class)
	public void shouldThrowExecptionIfImageIsJustEntered() throws InvalidCommandException{
		String[] textToEnter = {"image"};
		ACE.execute(textToEnter);
	}
	
	@Test(expected = InvalidCommandException.class)
	public void passingBackgroundAsParametersDoesThrowError() throws InvalidCommandException{
		String[] textToEnter = {"background"};
		ACE.execute(textToEnter);	
	}
	
	@Test(expected = InvalidCommandException.class)
	public void passingBackgroundPointAsParametersDoesThrowError() throws InvalidCommandException{
		String[] textToEnter = {"background", "(45,45)"};
		ACE.execute(textToEnter);
	}
	
	@Test(expected = InvalidCommandException.class)
	public void passingBackgroundWithTwoOtherStringsAsParametersDoesThrowError() throws InvalidCommandException{
		String[] textToEnter = {"background", "(45,45)", "tester"};
		ACE.execute(textToEnter);
	}
	
	@Test
	public void passingBackgroundPlusAValidImageAsParametersDoesNotThrowError(){
		String[] textToEnter = {"background","..\\TextBasedGame\\src\\resource\\images\\kirbyBackground.png"};
		try {
			ACE.execute(textToEnter);
		} catch (InvalidCommandException e) {
			fail("unexpected error occurred: "+ e.getMessage());
		}
	}
	
	@Test(expected = InvalidCommandException.class)
	public void passingRegularImagePlusAValidImageAsParametersDoesThrowError() throws InvalidCommandException{
		String[] textToEnter = {"image","..\\TextBasedGame\\src\\resource\\images\\kirbyBackground.png"};
		ACE.execute(textToEnter);

	}
	
	@Test(expected = InvalidCommandException.class)
	public void passingBackgroundPlusAnInvalidImageAsParametersDoesThrowError() throws InvalidCommandException{
		String[] textToEnter = {"background","invalidImage.jpg"};
		ACE.execute(textToEnter);
	}
	
	@Test
	public void returnCanvasBackgroundImageIsSetAndCanvasImageArrayInGuiDoesntContainAddedImagePoint(){
		String[] textToEnter = {"background","..\\TextBasedGame\\src\\resource\\images\\kirbyBackground.png"};
		try {
			ACE.execute(textToEnter);
		} catch (InvalidCommandException e) {
			fail("unexpected error occurred: "+ e.getMessage());
		}
		
		GameGui gui = GUI.getInstance();
		assertFalse(gui.getScreen().getImagesToAdd().containsKey(new Point(0,0)));
		assertEquals("..\\TextBasedGame\\src\\resource\\images\\kirbyBackground.png",gui.getScreen().getBackgroundImage().getImageFilename());
	}
	
	@Test
	public void returnCanvasBackgroundImageIsNotSetAndCanvasImageArrayInGuiDoesContainAddedImagePoint(){
		String[] textToEnter = {"image","..\\TextBasedGame\\src\\resource\\images\\kirbyBackground.png", "(45,45)"};
		GameGui gui = GUI.getInstance();
		//clear background from last test instance
		gui.getScreen().setBackgroundImage(null);
		try {
			ACE.execute(textToEnter);
		} catch (InvalidCommandException e) {
			fail("unexpected error occurred: "+ e.getMessage());
		}

		assertTrue(gui.getScreen().getImagesToAdd().containsKey(new Point(45,45)));
		assertNull(gui.getScreen().getBackgroundImage());
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
