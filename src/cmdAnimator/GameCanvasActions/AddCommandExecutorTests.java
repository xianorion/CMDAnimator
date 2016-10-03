package cmdAnimator.GameCanvasActions;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
	
	@Test
	public void returnTrueWhenUserEntersStringHello(){
		
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
