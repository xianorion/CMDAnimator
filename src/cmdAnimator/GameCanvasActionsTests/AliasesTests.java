package cmdAnimator.GameCanvasActionsTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cmdAnimator.GUI;
import cmdAnimator.GameGui;
import cmdAnimator.GameCanvasActions.Aliases;
import cmdAnimator.GameCanvasActions.FrameAnimator;
import cmdAnimator.GameCanvasActions.GameAnimator;
import javafx.application.Application;
import javafx.stage.Stage;

public class AliasesTests {
	
	GameGui gui;
	FrameAnimator Animation;

	@Before
	public void setup(){
		gui= GUI.getInstance(); 
		Animation = GameAnimator.getInstance();
		Animation.getFrames().clear();
		Animation.setTotalNumberOfFrames(0);
	}

	@Test
	public void ifUserCreatesAddAsAnAliasForMoveAliasIsntCreated() {
		assertFalse(Aliases.AddNewAlias("Add", "Move"));
	}
	
	@Test
	public void userCreatesMsgAsAliasForHelloStringAndAliasParserConvertsMsgWorldTohelloWorld() {
		Aliases.AddNewAlias("msg", "hello");
		String[] parameters = {"msg", "world"};
		String[] text = Aliases.convertCommandWithAliasesIntoNormalCommand(parameters);
		assertEquals("hello", text[0]);
		assertEquals("world", text[1]);

	}
	
	@Test
	public void userCreatesMsgAsAliasForHelloStringAndAForAddTextAndAliasParserReturnsHelloWorldAddText() {
		Aliases.AddNewAlias("msg", "hello");
		Aliases.AddNewAlias("a", "add text");
		String[] parameters = {"msg","world", "a"};
		String[] text = Aliases.convertCommandWithAliasesIntoNormalCommand(parameters);
		assertEquals("hello", text[0]);
		assertEquals("world", text[1]);
		assertEquals("add", text[2]);
		assertEquals("text", text[3]);

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
