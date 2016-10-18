package cmdAnimator;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cmdAnimator.GameCanvasActions.CanvasImage;
import cmdAnimator.GameUI.GameCanvasTests.dummyApp;
import javafx.application.Application;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GameGuiTests {
	
	String outputText;
	GameGui gui;
	TextField cmdLine;
	
	@Before
	public void setUp(){
		gui = new GameGui();
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
	
	@Test
	public void whenImageIsAddedToStageItIsAddedToImageLibrary(){
		String filename = "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png";
		Point point = new Point(40,45);
		
		gui.addImageToCanvas(new CanvasImage(filename, point));
		assertEquals(filename, ((AddImageButton)(gui.getImageLibrary().getItems().get(0).getGraphic())).getImagePath());
	}
	
	@Test
	public void whenInvalidImageIsAddedToStageItIsNotAddedToImageLibrary(){
		String filename = "..\\TextBasedGame\\src\\resource\\images\\invalidity1.png";
		Point point = new Point(40,45);
		
		gui.addImageToCanvas(new CanvasImage(filename, point));
		assertEquals(0, gui.getImageLibrary().getItems().size());
	}
	
	@Test
	public void whenButtonExecuteIsCalledImageNotAddedToImageLibrary(){
		String filename = "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png";
		Point point = new Point(40,45);
		
		gui.addImageToCanvas(new CanvasImage(filename, point));
		gui.buttonExecuteCalled = true;
		gui.addImageToCanvas(new CanvasImage(filename, point));
		assertEquals(filename, ((AddImageButton)(gui.getImageLibrary().getItems().get(0).getGraphic())).getImagePath());
		assertEquals(1, gui.getImageLibrary().getItems().size());
	}
	
	@Test
	public void whenIAddTwoDifferentImagesImageLibraryContainsBoth(){
		String filename = "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png";
		String filename2 = "..\\TextBasedGame\\src\\resource\\images\\kirbywalk2.png";
		Point point = new Point(40,45);
		Point point2 = new Point(56,78);
		
		gui.addImageToCanvas(new CanvasImage(filename, point));
		gui.addImageToCanvas(new CanvasImage(filename2, point2));
		assertEquals(filename, ((AddImageButton)(gui.getImageLibrary().getItems().get(0).getGraphic())).getImagePath());
		assertEquals(filename2, ((AddImageButton)(gui.getImageLibrary().getItems().get(1).getGraphic())).getImagePath());
		assertEquals(2, gui.getImageLibrary().getItems().size());
	}
	
	@Test
	public void whenIAddTwoDifferentImagesImageAndButtonExecutionHappendsInbetweenLibraryContainsBoth(){
		String filename = "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png";
		String filename2 = "..\\TextBasedGame\\src\\resource\\images\\kirbywalk2.png";
		Point point = new Point(40,45);
		Point point2 = new Point(56,78);
		
		gui.addImageToCanvas(new CanvasImage(filename, point));
		//button from gui execution
		gui.buttonExecuteCalled = true;
		gui.addImageToCanvas(new CanvasImage(filename, point));
		//-------
		gui.addImageToCanvas(new CanvasImage(filename2, point2));
		assertEquals(filename, ((AddImageButton)(gui.getImageLibrary().getItems().get(0).getGraphic())).getImagePath());
		assertEquals(filename2, ((AddImageButton)(gui.getImageLibrary().getItems().get(1).getGraphic())).getImagePath());
		assertEquals(2, gui.getImageLibrary().getItems().size());
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
