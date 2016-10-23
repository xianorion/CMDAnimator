package cmdAnimator;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cmdAnimator.GameCanvasActions.CanvasImage;
import cmdAnimator.GameCanvasActions.CanvasText;
import cmdAnimator.GameCanvasActions.FrameAnimator;
import cmdAnimator.GameCanvasActions.GameAnimator;
import cmdAnimator.GameCanvasActions.InvalidCommandException;
import cmdAnimator.GameUI.GameCanvas;
import cmdAnimator.GameUI.GameCanvasTests.dummyApp;
import javafx.application.Application;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GameGuiTests {
	
	String outputText;
	GameGui gui;
	TextField cmdLine;
	FrameAnimator anime;

	
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
		assertEquals(filename, ((AddImageButton)(gui.getImageLibrary().getChildren().get(0))).getImagePath());
	}
	
	@Test
	public void whenInvalidImageIsAddedToStageItIsNotAddedToImageLibrary(){
		String filename = "..\\TextBasedGame\\src\\resource\\images\\invalidity1.png";
		Point point = new Point(40,45);
		
		gui.addImageToCanvas(new CanvasImage(filename, point));
		assertEquals(0, gui.getImageLibrary().getChildren().size());
	}
	
	@Test
	public void whenButtonExecuteIsCalledImageNotAddedToImageLibrary(){
		String filename = "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png";
		Point point = new Point(40,45);
		
		gui.addImageToCanvas(new CanvasImage(filename, point));
		gui.buttonExecuteCalled = true;
		gui.addImageToCanvas(new CanvasImage(filename, point));
		assertEquals(filename, ((AddImageButton)(gui.getImageLibrary().getChildren().get(0))).getImagePath());
		assertEquals(1, gui.getImageLibrary().getChildren().size());
	}
	
	@Test
	public void whenIAddTwoDifferentImagesImageLibraryContainsBoth(){
		String filename = "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png";
		String filename2 = "..\\TextBasedGame\\src\\resource\\images\\kirbywalk2.png";
		Point point = new Point(40,45);
		Point point2 = new Point(56,78);
		
		gui.addImageToCanvas(new CanvasImage(filename, point));
		gui.addImageToCanvas(new CanvasImage(filename2, point2));
		assertEquals(filename, ((AddImageButton)(gui.getImageLibrary().getChildren().get(0))).getImagePath());
		assertEquals(filename2, ((AddImageButton)(gui.getImageLibrary().getChildren().get(1))).getImagePath());
		assertEquals(2, gui.getImageLibrary().getChildren().size());
	}
	
	@Test
	public void whenIAddTwoOfTheSameImagesAreAddedImageLibraryContainsOne(){
		String filename = "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png";
		String filename2 = "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png";
		Point point = new Point(40,45);
		Point point2 = new Point(56,78);
		
		gui.addImageToCanvas(new CanvasImage(filename, point));
		gui.addImageToCanvas(new CanvasImage(filename2, point2));
		assertEquals(filename, ((AddImageButton)(gui.getImageLibrary().getChildren().get(0))).getImagePath());
		assertEquals(1, gui.getImageLibrary().getChildren().size());
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
		assertEquals(filename, ((AddImageButton)(gui.getImageLibrary().getChildren().get(0))).getImagePath());
		assertEquals(filename2, ((AddImageButton)(gui.getImageLibrary().getChildren().get(1))).getImagePath());
		assertEquals(2, gui.getImageLibrary().getChildren().size());
	}
	
	@Test
	public void afterClearTheFrameBackgroundShouldBeClearEvenAfterTraversingWIthAnimator() throws InvalidCommandException{
		
		GameGui gui = GUI.getInstance();
		anime = GameAnimator.getInstance();
		gui.addBackgroundToCanvas(new CanvasImage( "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png", new Point(0,0)));
		anime.addFrameToAnimation();
		anime.addFrameToAnimation();
		anime.moveToFrameNumber(1);
		
		assertEquals(anime.getFrameBasedOnFrameNumber(1).getBackgroundImage().getImageFilename(), "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png");
		assertEquals(gui.getScreen().getBackgroundImage().getImageFilename(), "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png");
		
		gui.clearStage();
		anime.moveToFrameNumber(2);
		anime.moveToFrameNumber(1);
		assertNull(anime.getFrameBasedOnFrameNumber(1).getBackgroundImage());
		assertNull(gui.getScreen().getBackgroundImage());
	}
	
	
	@Test
	public void afterClearTheFrameBackgroundShouldBeClearEvenAfterTraversingWIthAnimatorThenAddFrameShouldHaveBackground() throws InvalidCommandException{
		
		GameGui gui = GUI.getInstance();
		anime = GameAnimator.getInstance();
		gui.addBackgroundToCanvas(new CanvasImage( "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png", new Point(0,0)));
		anime.addFrameToAnimation();
		anime.addFrameToAnimation();
		anime.moveToFrameNumber(1);
		
		assertEquals(anime.getFrameBasedOnFrameNumber(1).getBackgroundImage().getImageFilename(), "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png");
		assertEquals(gui.getScreen().getBackgroundImage().getImageFilename(), "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png");
		
		gui.clearStage();
		anime.moveToFrameNumber(2);
		anime.moveToFrameNumber(1);
		assertNull(anime.getFrameBasedOnFrameNumber(1).getBackgroundImage());
		assertNull(gui.getScreen().getBackgroundImage());
		
		gui.addBackgroundToCanvas(new CanvasImage( "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png", new Point(0,0)));
		assertEquals(anime.getFrameBasedOnFrameNumber(1).getBackgroundImage().getImageFilename(), "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png");
		assertEquals(gui.getScreen().getBackgroundImage().getImageFilename(), "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png");
		
	}
	
	@Test
	public void whenIAddTwoDifferentBackgroundsTheyAreBothContainedInTheLibrary(){
		GameGui gui = GUI.getInstance();
		anime = GameAnimator.getInstance();
		String filename = "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png";
		String filename2 = "..\\TextBasedGame\\src\\resource\\images\\kirbywalk2.png";
		gui.addBackgroundToCanvas(new CanvasImage( filename, new Point(0,0)));
		gui.addBackgroundToCanvas(new CanvasImage( filename2, new Point(0,0)));
		
		assertEquals(filename, ((AddImageButton)(gui.getImageLibrary().getChildren().get(0))).getImagePath());
		assertEquals(filename2, ((AddImageButton)(gui.getImageLibrary().getChildren().get(1))).getImagePath());
	}
	
	@Test
	public void whenIAddTwoDifferentOfTheSameBackgroundsOneIsContainedInTheLibrary(){
		GameGui gui = GUI.getInstance();
		anime = GameAnimator.getInstance();
		gui.getImageLibrary().getChildren().clear();

		String filename = "..\\TextBasedGame\\src\\resource\\images\\kirbywalk6.png";
		String filename2 = "..\\TextBasedGame\\src\\resource\\images\\kirbywalk6.png";
		gui.addBackgroundToCanvas(new CanvasImage( filename, new Point(0,0)));
		gui.addBackgroundToCanvas(new CanvasImage( filename2, new Point(0,0)));

		assertEquals(1, gui.getImageLibrary().getChildren().size());
		assertEquals(filename, ((AddImageButton)(gui.getImageLibrary().getChildren().get(0))).getImagePath());
	}
	
	@Test
	public void whenIAddTwoDifferentOfTheSamefileTypesOneIsContainedInTheLibrary(){
		GameGui gui = GUI.getInstance();
		anime = GameAnimator.getInstance();
		anime.setCurrentFrameNumber(0);
		gui.getImageLibrary().getChildren().clear();

		String filename = "..\\TextBasedGame\\src\\resource\\images\\kirbywalk7.png";
		gui.addBackgroundToCanvas(new CanvasImage( filename, new Point(0,0)));
		gui.addImageToCanvas(new CanvasImage( filename, new Point(0,0)));

		assertEquals(1, gui.getImageLibrary().getChildren().size());
		assertEquals(filename, ((AddImageButton)(gui.getImageLibrary().getChildren().get(0))).getImagePath());
	}

	@Test
	public void stageCanClearIfThereAreNoFramesInAnimation(){
		anime = GameAnimator.getInstance();
		anime.setCurrentFrameNumber(0);
		anime.getFrames().clear();
		
		gui.clearStage();
	}
	
	@Test
	public void stageCanClearFrameAndGUIisUserIsViewingAFrame() throws InvalidCommandException{
		anime = GameAnimator.getInstance();
		anime.setCurrentFrameNumber(0);
		anime.getFrames().clear();
		gui = GUI.getInstance();
		Point point = new Point(45,45);
		gui.addTextToCanvas(new CanvasText("hello1", point ));
		assertEquals("hello1", gui.getScreen().getTextToWrite().get(point).getTextToAdd());

		anime.addFrameToAnimation();

		assertEquals("hello1", anime.getFrames().get(0).getTextToWrite().get(point).getTextToAdd());

		
		anime.moveToFrameNumber(1);
		gui.clearStage();
		assertEquals(0, anime.getFrameBasedOnFrameNumber(1).getTextToWrite().size());
	}
	
	@Test
	public void stageCanOnlyClearGUIisUserIsNotViewingAFrame(){
		anime = GameAnimator.getInstance();
		anime.setCurrentFrameNumber(0);
		anime.getFrames().clear();
		gui = GUI.getInstance();
		Point point = new Point(45,45);
		gui.addTextToCanvas(new CanvasText("hello1", point ));
		assertEquals("hello1", gui.getScreen().getTextToWrite().get(point).getTextToAdd());

		anime.addFrameToAnimation();

		assertEquals("hello1", anime.getFrames().get(0).getTextToWrite().get(point).getTextToAdd());

		gui.clearStage();
		assertEquals(1, anime.getFrameBasedOnFrameNumber(1).getTextToWrite().size());
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
