package cmdAnimator.GameCanvasActions;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import cmdAnimator.GameUI.GameCanvas;
import cmdAnimator.GameUI.GameGUIPanel;
import cmdAnimator.GameUI.GameWindow;

public class FrameAnimatorTests {

	//divided a time in nano seconds just enougn so if one divides by 10 once more
	//and assigns it to a double value, they should get a decimal value for elapsed time
	private static final int DIVIDE_TO_GET_FIRST_DECIMAL_IN_ONES_PLACE_FOR_NANO_TIME = 100000000;
	private static final int DIVISION_TO_CONVERT_NANO_TO_SECONDS = 1000000000;
	FrameAnimator Animation;
	float beginningTime, endingTime, deltaTime;
	GameWindow window;
	GameGUIPanel GUI;
	
	@Before
	public void setup(){
		window = new GameWindow("TestWindow");
		GUI = window.getGameGUIPanel();
	}

	@Test
	public void totalAnimationTimeIs1SecondWith2FPSAnd2Frames() {
		Animation = new FrameAnimator(2);
		Animation.addFrameToAnimation(GUI);
		Animation.addFrameToAnimation(GUI);
		
		beginningTime = System.nanoTime()/DIVISION_TO_CONVERT_NANO_TO_SECONDS;
		Animation.playAnimation(GUI);
		endingTime = System.nanoTime()/DIVISION_TO_CONVERT_NANO_TO_SECONDS;
		
		deltaTime = endingTime - beginningTime;
		assertEquals(1, deltaTime, 0.0);
	}
	
	@Test
	public void totalAnimationTimeIsHalfASecondWith2FPSAnd1Frame(){
		Animation = new FrameAnimator(2);
		Animation.addFrameToAnimation(GUI);
		
		beginningTime = System.nanoTime()/DIVIDE_TO_GET_FIRST_DECIMAL_IN_ONES_PLACE_FOR_NANO_TIME;
		Animation.playAnimation(GUI);
		endingTime = System.nanoTime()/DIVIDE_TO_GET_FIRST_DECIMAL_IN_ONES_PLACE_FOR_NANO_TIME;
		
		deltaTime = (endingTime - beginningTime)/10; //moves 1's place to 1st decimal place
		assertEquals(.5, deltaTime, .0);
	}
	
	@Test
	public void totalAnimationTimeIsOneAndAHalfSecondsWith2FPSAnd3Frames(){
		Animation = new FrameAnimator(2);
		Animation.addFrameToAnimation(GUI);
		Animation.addFrameToAnimation(GUI);
		Animation.addFrameToAnimation(GUI);
		
		beginningTime = System.nanoTime()/DIVIDE_TO_GET_FIRST_DECIMAL_IN_ONES_PLACE_FOR_NANO_TIME;
		Animation.playAnimation(GUI);
		endingTime = System.nanoTime()/DIVIDE_TO_GET_FIRST_DECIMAL_IN_ONES_PLACE_FOR_NANO_TIME;
		
		deltaTime = (endingTime - beginningTime)/10; //moves 1's place to 1st decimal place
		assertEquals(1.5, deltaTime, .0);
	}
	
	@Test
	public void whenNewFrameIsAddedItIsPlacedInArrayListAndGUIScreenIsNowBlank(){
		Animation = new FrameAnimator(2);
		GameCanvas frameAdded = GUI.getScreen();
		Animation.addFrameToAnimation(GUI);
		
		assertTrue(Animation.getFrames().contains(frameAdded));
		assertEquals(0,GUI.getScreen().getComponentCount());
	}
	
	
	@Test
	public void frameIsDeletedFromArrayListWhenDeleteIsCalled(){
		
	}

}
