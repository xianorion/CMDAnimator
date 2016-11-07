package cmdAnimator.GameCanvasActionsTests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cmdAnimator.GUI;
import cmdAnimator.GameGui;
import cmdAnimator.GameCanvasActions.CanvasText;
import cmdAnimator.GameCanvasActions.FrameAnimator;
import cmdAnimator.GameCanvasActions.GameAnimator;
import cmdAnimator.GameCanvasActions.InvalidCommandException;
import cmdAnimator.GameCanvasActions.RemoveCommandExecutor;
import cmdAnimator.GameCanvasActionsTests.FrameAnimatorTests.dummyApp;
import cmdAnimator.GameUI.GameCanvas;
import javafx.application.Application;
import javafx.stage.Stage;

public class RemoveCommandExecutorTests {
	
	RemoveCommandExecutor rce;
	GameGui gui;
	FrameAnimator Animation;
	
	@Before
	public void setup(){
		rce = new RemoveCommandExecutor();
		gui= GUI.getInstance(); 
		Animation = GameAnimator.getInstance();
		Animation.getFrames().clear();
		Animation.setTotalNumberOfFrames(0);
	}
	
	@Test(expected = InvalidCommandException.class)
	public void ifParametersAreFrameExceptionIsThrown() throws InvalidCommandException {
		String[] parameters = {"frame"};
		rce.execute(parameters);
	}
	
	@Test(expected = InvalidCommandException.class)
	public void ifParametersAreFrameNumberPointExceptionIsThrown() throws InvalidCommandException {
		String[] parameters = {"frame","1","(2,2)"};
		rce.execute(parameters);
	}
	
	@Test(expected = InvalidCommandException.class)
	public void ifParametersAreFrameOneDotFiveExecptionIsThrown() throws InvalidCommandException{
		String[] parameters = {"frame","1.5"};
		rce.execute(parameters);
	}
	
	@Test
	public void anAddedFrameCanBeRemoved() throws InvalidCommandException{
		String[] parameters = {"frame","1"};
		Animation.addFrameToAnimation();
		Animation.getFrameBasedOnFrameNumber(1).addText(new CanvasText("hi", new Point(1,1)));
		GameCanvas canvas = new GameCanvas(null);
		canvas = GameCanvas.copy(Animation.getFrameBasedOnFrameNumber(1));
		
		assertEquals(canvas.getTextToWrite(), Animation.getFrameBasedOnFrameNumber(1).getTextToWrite());
		
		rce.execute(parameters);
		assertNull(Animation.getFrameBasedOnFrameNumber(1));
	
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
