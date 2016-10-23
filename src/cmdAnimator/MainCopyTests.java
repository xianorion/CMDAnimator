package cmdAnimator;

import static org.junit.Assert.*;
import java.awt.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cmdAnimator.GameCanvasActions.CanvasText;
import cmdAnimator.GameCanvasActions.FrameAnimator;
import cmdAnimator.GameCanvasActions.GameAnimator;
import cmdAnimator.GameGuiTests.dummyApp;
import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class MainCopyTests {

	FrameAnimator  Animation;
	GameGui gui;
	MainCopy robot;
	@Before
	public void setup(){
		Animation =  GameAnimator.getInstance();
	    gui = GUI.getInstance();
	    robot = new MainCopy();
	    GuiCommands.commands.clear();
	    GuiCommands.currentCommandView = 0;
	    gui.getCommandLine().setText("");
	    Animation.setCurrentFrameNumber(0);
	    Animation.getFrames().clear();
	    Animation.setTotalNumberOfFrames(0);
	}
	@Test
	public void ifNoCommandExecutedUserCannotMoveUpOrDown() {
		robot.CommandTestKeys("up");
		assertEquals("",gui.getCommandLine().getText());
		assertEquals(0, GuiCommands.currentCommandView);
		
		robot.CommandTestKeys("down");
		assertEquals("", gui.getCommandLine().getText());
		assertEquals(0, GuiCommands.currentCommandView);
	}
	
	@Test
	public void ifOneCommandExecutedUserCanMoveUpThenDownTwoTimes() {
		gui.getCommandLine().setText("command");
		
		robot.CommandTestKeys("enter");
		assertEquals(1, GuiCommands.commands.size());
		robot.CommandTestKeys("up");
		assertEquals("command", gui.getCommandLine().getText());
		assertEquals(0, GuiCommands.currentCommandView);
		
		robot.CommandTestKeys("down");
		assertEquals("", gui.getCommandLine().getText());
		
		robot.CommandTestKeys("down");
		assertEquals("", gui.getCommandLine().getText());
	}
	
	@Test
	public void ifOneCommandExecutedUserCannotMoveDownOnce() {
		gui.getCommandLine().setText("command");
		
		robot.CommandTestKeys("enter");
		assertEquals(1, GuiCommands.currentCommandView);
		
		robot.CommandTestKeys("down");
		assertEquals("", gui.getCommandLine().getText());
		assertEquals(1, GuiCommands.currentCommandView);
		
		
	}
	
	@Test
	public void whenUserEntersTwoCommandsGuiCanTraverseUPAndDownCorrectly() {
		gui.getCommandLine().setText("command1");
		robot.CommandTestKeys("enter");
		gui.getCommandLine().setText("command2");
		robot.CommandTestKeys("enter");
		gui.getCommandLine().setText("command3");
		robot.CommandTestKeys("enter");
		
		assertEquals(3, GuiCommands.currentCommandView);

		robot.CommandTestKeys("up");
		assertEquals("command3", gui.getCommandLine().getText());
		assertEquals(2, GuiCommands.currentCommandView);
		robot.CommandTestKeys("up");
		assertEquals("command2", gui.getCommandLine().getText());
		assertEquals(1, GuiCommands.currentCommandView);
		robot.CommandTestKeys("up");
		assertEquals("command1", gui.getCommandLine().getText());
		assertEquals(0, GuiCommands.currentCommandView);
		robot.CommandTestKeys("up");
		assertEquals("command1", gui.getCommandLine().getText());
		assertEquals(0, GuiCommands.currentCommandView);
		
		robot.CommandTestKeys("down");
		assertEquals("command2", gui.getCommandLine().getText());
		assertEquals(1, GuiCommands.currentCommandView);
		robot.CommandTestKeys("down");
		assertEquals("command3", gui.getCommandLine().getText());
		assertEquals(2, GuiCommands.currentCommandView);
		robot.CommandTestKeys("down");
		assertEquals("", gui.getCommandLine().getText());
	}
	
	@Test
	public void ifOneCommandExecutedUserCanMoveUpOnce() {
		gui.getCommandLine().setText("command");
		
		robot.CommandTestKeys("enter");
		assertEquals(1, GuiCommands.currentCommandView);
		
		robot.CommandTestKeys("up");
		assertEquals("command", gui.getCommandLine().getText());
		assertEquals(0, GuiCommands.currentCommandView);
		
		
	}
	
	@Test
	public void ifOneFrameUserCanGoLeftOnceThenCannotGoRight() {
		gui.getScreen().addText(new CanvasText("hello", new Point(45,45)));
		Animation.addFrameToAnimation();
		assertEquals(1, Animation.getTotalNumberOfFrames());
		
		robot.CommandTestKeys("left");
		assertEquals(1, Animation.getNumberOfCurrentFrame());
		assertEquals(gui.getScreen().getTextToWrite().get(0), Animation.getCurrentFrame().getTextToWrite().get(0));
		
		robot.CommandTestKeys("right");
		assertEquals(1, Animation.getNumberOfCurrentFrame());
		assertEquals(gui.getScreen().getTextToWrite().get(0), Animation.getCurrentFrame().getTextToWrite().get(0));
		
	}
	
	@Test
	public void ifTwoFramesUserCanGoLeftTwiceThenGoRightOnce() {
		gui.getScreen().addText(new CanvasText("hello1", new Point(45,45)));
		Animation.addFrameToAnimation();
		gui.getScreen().addText(new CanvasText("hello2", new Point(45,45)));
		Animation.addFrameToAnimation();
		assertEquals(2, Animation.getNumberOfCurrentFrame());
		
		robot.CommandTestKeys("left");
		assertEquals(1, Animation.getNumberOfCurrentFrame());
		assertEquals(gui.getScreen().getTextToWrite().get(0), Animation.getCurrentFrame().getTextToWrite().get(0));
		
		robot.CommandTestKeys("left");
		assertEquals(1, Animation.getNumberOfCurrentFrame());
		assertEquals(gui.getScreen().getTextToWrite().get(0), Animation.getCurrentFrame().getTextToWrite().get(0));
		
		
		robot.CommandTestKeys("right");
		assertEquals(2, Animation.getNumberOfCurrentFrame());
		assertEquals(gui.getScreen().getTextToWrite().get(0), Animation.getCurrentFrame().getTextToWrite().get(0));
		
		robot.CommandTestKeys("right");
		assertEquals(2, Animation.getNumberOfCurrentFrame());
		assertEquals(gui.getScreen().getTextToWrite().get(0), Animation.getCurrentFrame().getTextToWrite().get(0));
		
	}
	
	@Test
	public void ifUserEntersHiAndGoesUpTheTextInCommandLineIsHi() {
		gui.getCommandLine().setText("hi");
		robot.CommandTestKeys("enter");
		assertEquals(1, GuiCommands.currentCommandView);
		
		robot.CommandTestKeys("up");
		assertEquals("hi", gui.getCommandLine().getText());
	}
	
	@Test
	public void ifUserEntersHiThenNoAndGoesUpTheTextInCommandLineIsNo() {
		gui.getCommandLine().setText("hi");
		robot.CommandTestKeys("enter");
		assertEquals(1, GuiCommands.currentCommandView);
		
		gui.getCommandLine().setText("no");
		robot.CommandTestKeys("enter");
		assertEquals(2, GuiCommands.currentCommandView);
		
		robot.CommandTestKeys("up");
		assertEquals("no", gui.getCommandLine().getText());
	}

	@Test
	public void ifUserEntersHiThenNoAndGoesUpTwiceThenDownTwiceAndUpAgainCommandLineIsNo() {
		gui.getCommandLine().setText("hi");
		robot.CommandTestKeys("enter");
		assertEquals(1, GuiCommands.currentCommandView);
		
		gui.getCommandLine().setText("No");
		robot.CommandTestKeys("enter");
		assertEquals(2, GuiCommands.currentCommandView);
		
		robot.CommandTestKeys("up");
		robot.CommandTestKeys("up");
		robot.CommandTestKeys("down");
		robot.CommandTestKeys("down");
		robot.CommandTestKeys("up");
		assertEquals("No", gui.getCommandLine().getText());
	}

	@Test
	public void ifUserEntersHiThenNoAndGoesUpThenDownTwiceTextInCommandLineIsNothing() {
		gui.getCommandLine().setText("hi");
		robot.CommandTestKeys("enter");
		assertEquals(1, GuiCommands.currentCommandView);
		
		gui.getCommandLine().setText("no");
		robot.CommandTestKeys("enter");
		assertEquals(2, GuiCommands.currentCommandView);
		
		robot.CommandTestKeys("up");
		robot.CommandTestKeys("down");
		robot.CommandTestKeys("down");
		assertEquals("", gui.getCommandLine().getText());
	}
	
	@Test 
	public void ifOneFrameIsAddedToAnimationUserCanGoLeftOnce(){
		Point point = new Point(45,45);
		gui.getScreen().addText(new CanvasText("hello1", point));
		Animation.addFrameToAnimation();
		assertEquals(1, Animation.getNumberOfCurrentFrame());

		gui.getCommandLine().setText("");
		robot.CommandTestKeys("left");
		assertEquals(gui.getScreen().getTextToWrite().get(point).getTextToAdd(), Animation.getFrameBasedOnFrameNumber(1).getTextToWrite().get(point).getTextToAdd());
	}
	
	@Test
	public void ifOneFrameIsAddedToAnimationUserCanGoLeftOnceAndNotRight(){
		Point point = new Point(45,45);
		gui.getScreen().addText(new CanvasText("hello1", point));
		Animation.addFrameToAnimation();
		assertEquals(1, Animation.getNumberOfCurrentFrame());

		gui.getCommandLine().setText("");
		robot.CommandTestKeys("left");
		assertEquals(gui.getScreen().getTextToWrite().get(point).getTextToAdd(), Animation.getFrameBasedOnFrameNumber(1).getTextToWrite().get(point).getTextToAdd());

		gui.getCommandLine().setText("");
		robot.CommandTestKeys("right");
		assertEquals(gui.getScreen().getTextToWrite().get(point).getTextToAdd(), Animation.getFrameBasedOnFrameNumber(1).getTextToWrite().get(point).getTextToAdd());
		
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
