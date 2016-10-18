package cmdAnimator;

import static org.junit.Assert.*;
import java.awt.*;

import org.junit.Before;
import org.junit.Test;

import cmdAnimator.GameCanvasActions.FrameAnimator;
import cmdAnimator.GameCanvasActions.GameAnimator;
import javafx.scene.input.KeyEvent;

public class MainCopyTests {

	FrameAnimator  Animation;
	GameGui gui;
	@Before
	public void setup(){
		Animation =  GameAnimator.getInstance();
	    gui = GUI.getInstance();
	}
	@Test
	public void ifNoCommandExecutedUserCannotMoveUpOrDown() {
		
	}
	
	@Test
	public void ifOneCommandExecutedUserCanMoveUpThenDownTwoTimes() {
		
	}
	
	@Test
	public void ifOneCommandExecutedUserCanMoveUpThenDownOnce() {
		
	}
	
	@Test
	public void ifOneCommandExecutedUserCannotMoveDownOnce() {
		
	}
	
	@Test
	public void ifOneFrameUserCanGoLeftOnceThenGoRightOnce() {
		
	}
	
	@Test
	public void ifTwoFramesUserCanGoLeftTwiceThenGoRightOnce() {
		
	}
	
	@Test
	public void ifUserEntersHiAndGoesUpTheTextInCommandLineIsHi() {
		
	}
	
	@Test
	public void ifUserEntersHiThenNoAndGoesUpTheTextInCommandLineIsNo() {
		
	}
	
	@Test
	public void ifUserEntersHiThenNoAndGoesUpThenDownTwiceTextInCommandLineIsNsothing() {
		
	}

}
