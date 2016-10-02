package cmdAnimator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cmdAnimator.GameUI.GameCanvasTests.AsNonApp;
import javafx.application.Application;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GUItests {
	
	String outputText;
	GUI gui;
	TextField cmdLine;
	
	@Before
	public void setUp(){
		gui = new GUI();
		cmdLine = gui.getCommandLine();
		outputText= gui.getOutputFieldDefaultText()+"\n";
	}

	@Test
	public void whenAddUserInputHasadd_imageTextItIsAddedOnceItIsAddedToOutput() {
		cmdLine.setText("add image");
		gui.addUserInputToOutPutFieldAndClearUserInput();
		
		assertEquals(outputText+"add image", gui.getOutputScreen().getText());
	}
	
	@Test
	public void whenUserInputHasDogTextAndAddsOutputAndUserTextHasCatTextAndAddsToOutputOutputHasDogCatText(){
		cmdLine.setText("Dog");
		gui.addUserInputToOutPutFieldAndClearUserInput();
		cmdLine.setText("Cat");
		gui.addUserInputToOutPutFieldAndClearUserInput();
		
		assertEquals(outputText+"Dog\nCat", gui.getOutputScreen().getText());
		
	}
	
	@Test
	public void whenUserInputAddsDogTextToOutputUserInputScreenIsCleared(){
		cmdLine.setText("Dog");
		gui.addUserInputToOutPutFieldAndClearUserInput();
		
		assertEquals("", cmdLine.getText());
	}

	
	public static class AsNonApp extends Application {
	    @Override
	    public void start(Stage primaryStage) throws Exception {
	        // noop
	    }
	}

	@BeforeClass
	public static void initJFX() {
	    Thread t = new Thread("JavaFX Init Thread") {
	        public void run() {
	            Application.launch(AsNonApp.class, new String[0]);
	        }
	    };
	    t.setDaemon(true);
	    t.start();
	}
}
