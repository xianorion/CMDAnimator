package cmdAnimator.GameCanvasActionsTests;

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
import cmdAnimator.GameCanvasActions.GoToCommandExecutor;
import cmdAnimator.GameCanvasActions.InvalidCommandException;
import cmdAnimator.GameUI.GameCanvas;
import javafx.application.Application;
import javafx.stage.Stage;

public class GoToCommandExecutorTests {
	
	FrameAnimator Animation;
	GameGui gui;
	GoToCommandExecutor gtce;
	String[] parameters;
	@Before
	public void setup(){
		Animation = GameAnimator.getInstance();
		gui = GUI.getInstance();
		gtce = new GoToCommandExecutor();
		
	}

	@Test(expected = InvalidCommandException.class)
	public void ifExecuteParametersAreFrameExceptionIsThrown() throws InvalidCommandException {
		parameters= new String[]{"frame"};
	    gtce.execute(parameters);
	}
	
	@Test(expected = InvalidCommandException.class)
	public void ifExecuteParametersAreFrameOneDotFiveExceptionIsThrown() throws InvalidCommandException {
		String[] parameters= {"frame","1.5"};
		
	    GoToCommandExecutor gtce =  new GoToCommandExecutor();
	    gtce.execute(parameters);
	}
	
	@Test(expected = InvalidCommandException.class)
	public void ifExecuteParametersAreFrameNegativeOneExceptionIsThrown() throws InvalidCommandException {
		String[] parameters= {"frame","-1"};
		
	    GoToCommandExecutor gtce =  new GoToCommandExecutor();
	    gtce.execute(parameters);
	}
	
	@Test(expected = InvalidCommandException.class)
	public void ifExecuteParametersAreFrameOneWithNoFramesExceptionIsThrown() throws InvalidCommandException {
		String[] parameters= {"frame","1"};
		
	    GoToCommandExecutor gtce =  new GoToCommandExecutor();
	    gtce.execute(parameters);
	}
	
	
	@Test
	public void ifTwoFramesAreAddedAndWePassFrameOneNextGuiFrameIsThatOfFrameOne() throws InvalidCommandException{
		parameters = new String[]{"frame", "1"};
		Animation.addFrameToAnimation();
		Animation.addFrameToAnimation();
		
		//copy second frame into game canvas
		Animation.getFrameBasedOnFrameNumber(1).addImage(new CanvasImage( "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png" ,new Point(3,4)));
		Animation.getFrameBasedOnFrameNumber(1).addText(new CanvasText("hi", new Point(1,1)));
		GameCanvas canvas = new GameCanvas(null);
		canvas = GameCanvas.copy(Animation.getFrameBasedOnFrameNumber(1));

		//second frame doesnt equal current frame displayed 
		assertNotEquals(canvas.getImagesToAdd(), gui.getScreen().getImagesToAdd());
		assertNotEquals(canvas.getImagesToAdd(), gui.getScreen().getTextToWrite());
		
		gtce.execute(parameters);
		//after moving to second frame, second frame equal current frame displayed on gui
		assertEquals(canvas.getImagesToAdd(), gui.getScreen().getImagesToAdd());
		assertEquals(canvas.getTextToWrite(), gui.getScreen().getTextToWrite());
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
