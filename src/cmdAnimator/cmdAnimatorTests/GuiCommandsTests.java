package cmdAnimator.cmdAnimatorTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cmdAnimator.GUI;
import cmdAnimator.GameGui;
import cmdAnimator.GuiCommands;
import cmdAnimator.cmdAnimatorTests.GameGuiTests.dummyApp;
import javafx.application.Application;
import javafx.stage.Stage;

public class GuiCommandsTests {
	
	GuiCommands commander;
	GameGui gui;
	
	@Before
	public void setUp(){
		commander =  new GuiCommands();
		//clear commands array before each test
		commander.commands.clear();
		gui = GUI.getInstance();
	}

	@Test
	public void ifCommandHelpAndAddFrameExecutedThenHelpExecutedHelpIsMostRecentInArrayList() {
		gui.setCommandLineText("help");
		commander.addCommandToPreviousCommandsFeed(gui);
		gui.setCommandLineText("Add frame");
		commander.addCommandToPreviousCommandsFeed(gui);
		gui.setCommandLineText("help");
		commander.addCommandToPreviousCommandsFeed(gui);
		
		assertEquals("help", commander.commands.get(1));
	}
	
	
	@Test
	public void ifCommandHelpAndAddFrameExecutedThenAddFrameIsMostRecentInArrayList() {
		gui.setCommandLineText("help");
		commander.addCommandToPreviousCommandsFeed(gui);
		gui.setCommandLineText("Add frame");
		commander.addCommandToPreviousCommandsFeed(gui);
		
		assertEquals("Add frame", commander.commands.get(1));
	}

	@Test
	public void ifCommandHelpAndAddFrameThenHelpAddExecutedThenAddFrameExecutedAddFrameIsMostRecentInArrayList() {
		gui.setCommandLineText("help");
		GuiCommands.addCommandToPreviousCommandsFeed(gui);
		gui.setCommandLineText("Add frame");
		GuiCommands.addCommandToPreviousCommandsFeed(gui);
		gui.setCommandLineText("help add");
		GuiCommands.addCommandToPreviousCommandsFeed(gui);
		gui.setCommandLineText("Add frame");
		GuiCommands.addCommandToPreviousCommandsFeed(gui);
		
		assertEquals("Add frame", commander.commands.get(2));
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
