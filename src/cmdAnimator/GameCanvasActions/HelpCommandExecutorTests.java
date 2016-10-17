package cmdAnimator.GameCanvasActions;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cmdAnimator.GUI;
import cmdAnimator.GameGui;
import cmdAnimator.GameCanvasActions.GoToCommandExecutorTests.dummyApp;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelpCommandExecutorTests {

	FrameAnimator Animation;
	GameGui gui;
	HelpCommandExecutor hce;
	String[] parameters;
	@Before
	public void setup(){
		Animation = GameAnimator.getInstance();
		gui = GUI.getInstance();
		hce = new HelpCommandExecutor();
		
	}
	
	@Test(expected = InvalidCommandException.class)
	public void ifParametersAreAddMeThenThrowExeception() throws InvalidCommandException{
		parameters= new String[]{"Add", "me"};
	    hce.execute(parameters);
	}

	@Test
	public void ifNoParmetersThenGuiCommandTextFieldContainsSameTextAsCommandTextFile() throws InvalidCommandException {
		parameters= null;
	    hce.execute(parameters);
	      
	    assertEquals(hce.getFileInfoInHelpTextArea("..\\TextBasedGame\\src\\resource\\textCommands\\commands.txt"),gui.getHelpCommands().getText());
	}
	
	@Test
	public void ifAddParmetersThenGuiCommandTextFieldContainsSameTextAsAddCommandTextFile() throws InvalidCommandException {
		parameters= new String[]{"Add"};
	    hce.execute(parameters);
	    assertEquals(hce.getFileInfoInHelpTextArea("..\\TextBasedGame\\src\\resource\\textCommands\\addCommands.txt"),gui.getHelpCommands().getText());
	}
	
	@Test
	public void ifRemoveParmetersThenGuiCommandTextFieldContainsSameTextAsAddCommandTextFile() throws InvalidCommandException {
		parameters= new String[]{"remove"};
	    hce.execute(parameters);
	    assertEquals(hce.getFileInfoInHelpTextArea("..\\TextBasedGame\\src\\resource\\textCommands\\removeCommands.txt"),gui.getHelpCommands().getText());
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
