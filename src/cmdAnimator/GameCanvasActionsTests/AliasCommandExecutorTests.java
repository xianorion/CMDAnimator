package cmdAnimator.GameCanvasActionsTests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cmdAnimator.GUI;
import cmdAnimator.GameGui;
import cmdAnimator.GameCanvasActions.AliasCommandExecutor;
import cmdAnimator.GameCanvasActions.Aliases;
import cmdAnimator.GameCanvasActions.FrameAnimator;
import cmdAnimator.GameCanvasActions.GameAnimator;
import cmdAnimator.GameCanvasActions.InvalidCommandException;
import cmdAnimator.GameUI.GameCanvas;
import javafx.application.Application;
import javafx.stage.Stage;

public class AliasCommandExecutorTests {

	AliasCommandExecutor ace;
	GameGui gui;
	FrameAnimator Animation;

	@Before
	public void setup(){
		ace = new AliasCommandExecutor();
		gui= GUI.getInstance(); 
		Animation = GameAnimator.getInstance();
		Animation.getFrames().clear();
		Animation.setTotalNumberOfFrames(0);
	}
	
	@Test(expected = InvalidCommandException.class)
	public void ifParametersAreAddExceptionIsThrown() throws InvalidCommandException {
		String[] parameters = {"Add"};
		ace.execute(parameters);
	}
	
	@Test(expected = InvalidCommandException.class)
	public void ifParametersAreAliasSomeOtherExceptionIsThrown() throws InvalidCommandException {
		String[] parameters = {"Alias", "Some", "Other"};
		ace.execute(parameters);
	}
	
	@Test(expected = InvalidCommandException.class)
	public void ifParametersAreDispExceptionIsThrown() throws InvalidCommandException {
		String[] parameters = {"disp"};
		ace.execute(parameters);
	}
	
	@Test
	public void ifParametersAreDisplayExceptionIsNotThrown(){
		String[] parameters = {"display"};
		try {
			ace.execute(parameters);
		} catch (InvalidCommandException e) {
			fail();
		}
	}
	
	@Test(expected = InvalidCommandException.class)
	public void ifParametersAreNullExceptionIsThrown() throws InvalidCommandException {
		String[] parameters = {};
		ace.execute(parameters);
	}
	
	@Test
	public void ifParametersAreAAndAddAliasClassHasAddedThisNewAlias() throws InvalidCommandException {
		String[] parameters = {"a","Add"};
		ace.execute(parameters);
		assertEquals("Add", Aliases.getReplacedTextFromAlias("a") );
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
