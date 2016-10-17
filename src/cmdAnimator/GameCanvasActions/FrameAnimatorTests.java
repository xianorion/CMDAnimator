package cmdAnimator.GameCanvasActions;

import org.junit.*;
import static org.junit.Assert.*;

import java.awt.Point;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.swing.Timer;

import cmdAnimator.GUI;
import cmdAnimator.GameGui;
import cmdAnimator.GameUI.GameCanvas;
import cmdAnimator.GameUI.GameCanvasTests.dummyApp;
import javafx.application.Application;
import javafx.stage.Stage;

public class FrameAnimatorTests {

	//divided a time in nano seconds just enougn so if one divides by 10 once more
	//and assigns it to a double value, they should get a decimal value for elapsed time
	private static final int DIVIDE_TO_GET_FIRST_DECIMAL_IN_ONES_PLACE_FOR_NANO_TIME = 100000000;
	private static final int DIVISION_TO_CONVERT_NANO_TO_SECONDS = 1000000000;
	private static final int DIVISION_TO_CONVERT_MILLI_TO_SECONDS = 1000;
	FrameAnimator Animation;
	float beginningTime, endingTime, deltaTime;
	GameGui gui;
	
	@Before
	public void setup(){
		gui= GUI.getInstance(); 
		Animation = new FrameAnimator();
	}

	@Test
	public void totalAnimationTimeIs1SecondWith2FPSAnd2Frames() {
		Animation.setFPS(2);
		Animation.addFrameToAnimation();
		Animation.addFrameToAnimation();

		Animation.playAnimation();
		double runTime = (double)Animation.getTimer().getDelay()/1000;
		double runAmounts = Animation.getFrames().size();

		assertEquals(1, runTime*runAmounts, 0.0);
	}
	
	@Test
	public void totalAnimationTimeIsHalfASecondWith2FPSAnd1Frame(){
		Animation.setFPS(2);
		Animation.addFrameToAnimation();
		
		Animation.playAnimation();
		double runTime = (double)Animation.getTimer().getDelay()/1000;
		double runAmounts = Animation.getFrames().size();

		assertEquals(.5, runTime*runAmounts, 0.0);
	}
	
	@Test
	public void totalAnimationTimeIsOneAndAHalfSecondsWith2FPSAnd3Frames(){
		Animation.setFPS(2);
		Animation.addFrameToAnimation();
		Animation.addFrameToAnimation();
		Animation.addFrameToAnimation();
		
		Animation.playAnimation();
		double runTime = (double)Animation.getTimer().getDelay()/1000;
		double runAmounts = Animation.getFrames().size();

		assertEquals(1.5, runTime*runAmounts, 0.0);
	}
	
	
	@Test
	public void whenNewFrameIsAddedAllComponentsOnFrameArePlacedInArrayListAndGUIScreenIsNowBlank(){
		Point point = new Point(50,50);
		Point point2 = new Point(55,55);
		String filename = "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png";

		gui.addTextToCanvas(new CanvasText("Test Text", point));
		gui.addImageToCanvas(new CanvasImage(filename, point2));
		GameCanvas oldFrame = GameCanvas.copy(gui.getScreen());
		Animation.addFrameToAnimation();
		
		assertEquals(Animation.getFrames().get(0).getTextToWrite(), oldFrame.getTextToWrite());
		assertEquals(Animation.getFrames().get(0).getImagesToAdd(), oldFrame.getImagesToAdd());
		assertTrue(gui.getScreen().getTextToWrite().isEmpty());
		assertTrue(gui.getScreen().getImagesToAdd().isEmpty());
	}
	
	
	@Test
	public void frameIsDeletedFromArrayListWhenDeleteIsCalled(){
		Animation.addFrameToAnimation();
		Animation.getFrameBasedOnFrameNumber(1).addText(new CanvasText("hi", new Point(1,1)));
		GameCanvas canvas = new GameCanvas(null);
		canvas = GameCanvas.copy(Animation.getFrameBasedOnFrameNumber(1));
		
		assertEquals(canvas.getTextToWrite(), Animation.getFrameBasedOnFrameNumber(1).getTextToWrite());
		Animation.deleteFrame(1);

		assertNull(Animation.getFrameBasedOnFrameNumber(1));
	}
	
	@Test 
	public void whenUserGetsFrameNumberOfZeroReturnNull(){
		assertNull(Animation.getFrameBasedOnFrameNumber(0));
	}
	
	@Test 
	public void whenUserGetsFrameNumberOfOneWithZeroFramesInanimatorReturnNull(){
		assertNull(Animation.getFrameBasedOnFrameNumber(1));
	}
	
	@Test
	public void whenWeAddThreeFramesAndMoveToSecondFrameAddedAnimatorsCurrentCanvasIsSecondFrame() throws InvalidCommandException{
		Animation.setCurrentFrameNumber(0);
		Animation.addFrameToAnimation();
		Animation.addFrameToAnimation();
		Animation.addFrameToAnimation();
		
		//copy second frame into game canvas
		Animation.getFrameBasedOnFrameNumber(2).addImage(new CanvasImage( "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png" ,new Point(3,4)));
		Animation.getFrameBasedOnFrameNumber(2).addText(new CanvasText("hi", new Point(1,1)));
		GameCanvas canvas = new GameCanvas(null);
		canvas = GameCanvas.copy(Animation.getFrameBasedOnFrameNumber(2));

		//second frame doesnt equal current frame displayed 
		assertNotEquals(canvas.getImagesToAdd(), gui.getScreen().getImagesToAdd());
		assertNotEquals(canvas.getImagesToAdd(), gui.getScreen().getTextToWrite());
		
		Animation.moveToFrameNumber(2);
		//after moving to second frame, second frame equal current frame displayed on gui
		assertEquals(canvas.getImagesToAdd(), gui.getScreen().getImagesToAdd());
		assertEquals(canvas.getTextToWrite(), gui.getScreen().getTextToWrite());
	}
	
	@Test
	public void whenWeAddTwoFramesAndMoveToFirstFrameAddedAnimatorsCurrentCanvasIsFirstFrame() throws InvalidCommandException{
		Animation.setCurrentFrameNumber(0);
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
		
		Animation.moveToFrameNumber(1);
		//after moving to second frame, second frame equal current frame displayed on gui
		assertEquals(canvas.getImagesToAdd(), gui.getScreen().getImagesToAdd());
		assertEquals(canvas.getTextToWrite(), gui.getScreen().getTextToWrite());
	}
	
	@Test(expected = InvalidCommandException.class)
	public void whenWeAddTwoFramesAndMoveToThirdFrameAddedExceptionIsThrown() throws InvalidCommandException{
		Animation.setCurrentFrameNumber(0);
		Animation.addFrameToAnimation();
		Animation.addFrameToAnimation();
		
		//copy second frame into game canvas
		Animation.getFrameBasedOnFrameNumber(2).addImage(new CanvasImage( "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png" ,new Point(3,4)));
		Animation.getFrameBasedOnFrameNumber(2).addText(new CanvasText("hi", new Point(1,1)));
		GameCanvas canvas = new GameCanvas(null);
		canvas = GameCanvas.copy(Animation.getFrameBasedOnFrameNumber(2));

		//second frame doesnt equal current frame displayed 
		assertNotEquals(canvas.getImagesToAdd(), gui.getScreen().getImagesToAdd());
		assertNotEquals(canvas.getImagesToAdd(), gui.getScreen().getTextToWrite());
		
		Animation.moveToFrameNumber(3);
		//after moving to second frame, second frame equal current frame displayed on gui
		assertNotEquals(canvas.getImagesToAdd(), gui.getScreen().getImagesToAdd());
		assertNotEquals(canvas.getTextToWrite(), gui.getScreen().getTextToWrite());
	}
	
	@Test(expected = InvalidCommandException.class)
	public void whenWeAddTwoFramesAndMoveToZeroFrameAddedExceptionIsThrown() throws InvalidCommandException{
		Animation.setCurrentFrameNumber(0);
		Animation.addFrameToAnimation();
		Animation.addFrameToAnimation();
		
		//copy second frame into game canvas
		Animation.getFrameBasedOnFrameNumber(2).addImage(new CanvasImage( "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png" ,new Point(3,4)));
		Animation.getFrameBasedOnFrameNumber(2).addText(new CanvasText("hi", new Point(1,1)));
		GameCanvas canvas = new GameCanvas(null);
		canvas = GameCanvas.copy(Animation.getFrameBasedOnFrameNumber(2));

		//second frame doesnt equal current frame displayed 
		assertNotEquals(canvas.getImagesToAdd(), gui.getScreen().getImagesToAdd());
		assertNotEquals(canvas.getImagesToAdd(), gui.getScreen().getTextToWrite());
		
		Animation.moveToFrameNumber(0);
	}
	
	@Test
	public void WhenMoveToClearFrameIsCalledGuiIsNull(){
		gui.addImageToCanvas(new CanvasImage( "..\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png" ,new Point(3,4)));
		gui.addTextToCanvas(new CanvasText("hi", new Point(1,1)));
		gui.addBackgroundToCanvas(new CanvasImage( "..\\TextBasedGame\\src\\resource\\images\\galaxy.jpg", new Point(0,0) ));
		
		
		assertEquals(1,gui.getScreen().getTextToWrite().size());
		assertEquals(1,gui.getScreen().getImagesToAdd().size());
		assertEquals("..\\TextBasedGame\\src\\resource\\images\\galaxy.jpg", gui.getScreen().getBackgroundImage().getImageFilename());
		
		Animation.moveToClearFrame();
		assertEquals(0,gui.getScreen().getTextToWrite().size());
		assertEquals(0,gui.getScreen().getImagesToAdd().size());
		assertNull(gui.getScreen().getBackgroundImage());
	}
	
	@Test 
	public void ifDeletedFrameCurrentFrameNumberCurrentFrameNumberGoesToSizeOfFramesList(){
		Animation.setCurrentFrameNumber(0);
		Animation.addFrameToAnimation();
		Animation.addFrameToAnimation();
		
		assertEquals(2, Animation.getNumberOfCurrentFrame());
		
		Animation.deleteFrame(2);
		assertEquals(Animation.getTotalNumberOfFrames(), Animation.getNumberOfCurrentFrame());
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
