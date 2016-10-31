package cmdAnimator.GameCanvasActionsTests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import cmdAnimator.GameCanvasActions.CommandFileRunner;
import cmdAnimator.GameCanvasActions.InvalidCommandException;
import cmdAnimator.GameCanvasActionsTests.GoToCommandExecutorTests.dummyApp;
import javafx.application.Application;
import javafx.stage.Stage;

public class CommandFileRunnerTests {

	@Test
	public void noErrorIfValidFileIsPassed() {
		try {
			CommandFileRunner.runBatchFile("..\\TextBasedGame\\src\\resource\\textCommands\\batchfile.txt");
		} catch (InvalidCommandException e) {
			fail("Test Failed: invalid command exception is thrown");
		}

	}
	
	@Test(expected = InvalidCommandException.class)
	public void returnExceptionIfInvalidFileIsPassed() throws InvalidCommandException{
		CommandFileRunner.runBatchFile("..\\TextBasedGame\\src\\resource\\textCommands\\batchfile2.txt");
	}
	
	@Test(expected = InvalidCommandException.class)
	public void returnExceptionIfImageIsPassed() throws InvalidCommandException{
		CommandFileRunner.runBatchFile("..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png");
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
