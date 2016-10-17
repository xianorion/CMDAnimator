package cmdAnimator.GameCanvasActions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import cmdAnimator.GUI;
import cmdAnimator.GameGui;
import cmdAnimator.GameUI.GameCanvas;

public class FrameAnimator {

	private final int DEFAULT_FPS = 5;
	private static final int ONE_SECOND_IN_MILLI = 1000;
	private int totalNumberOfFrames;
	private int fps;
	private ArrayList<GameCanvas> frames;
	private Timer timer;
	private int currentFrameBeingDisplayed;
	private GameGui gui;
	private static int currentFrameNumber =0;

	public FrameAnimator() {
		frames = new ArrayList<GameCanvas>();
		this.fps = DEFAULT_FPS;
		gui =  GUI.getInstance();
	}

	// add tests for me!!
	public void playAnimation() {
		currentFrameBeingDisplayed = 0;
		gui.setDisabledForEnterButton(true);
		// timer that runs until we have shown all the frames in the arraylist once
		timer = new Timer(ONE_SECOND_IN_MILLI / fps, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentFrameBeingDisplayed < frames.size()) {
					gui.setScreen(frames.get(currentFrameBeingDisplayed));
					System.out.println("repainted ");
					currentFrameBeingDisplayed++;
					
				} else {
					timer.stop();
					gui.setDisabledForEnterButton(false);	
				}
			
			}
		});
		timer.setRepeats(true);
		timer.start();
		System.out.println("--------------------------");
	}

	public void addFrameToAnimation() {
		frames.add(GameCanvas.copy(gui.getScreen()));
		gui.setScreen(null);
		totalNumberOfFrames++;
		currentFrameNumber++;
		System.out.println("current frame num "+ currentFrameNumber+" total frame"+ totalNumberOfFrames);
		updateCurrentFrameLabel(false);
	}

	public void moveToFrameNumber(int frameNumber) throws InvalidCommandException {
		if(frameNumber -  1 < totalNumberOfFrames && frameNumber -1 >= 0 ){
			currentFrameNumber = frameNumber;
			gui.setScreen(getFrameBasedOnFrameNumber(frameNumber));
		}else{
			throw new InvalidCommandException();
		}
		updateCurrentFrameLabel(true);
	}

	public void deleteFrame(int frameNumber) {
		if(frameNumber -1 < totalNumberOfFrames){
			System.out.println("removed");
			frames.remove(frameNumber-1);
			totalNumberOfFrames--;
			currentFrameNumber = totalNumberOfFrames;
			updateCurrentFrameLabel(true);
		}
		

	}

	public int getNumberOfCurrentFrame() {
		return currentFrameNumber;
	}

	public GameCanvas getFrameBasedOnFrameNumber(int frameNumber) {
		if (frameNumber-1 < totalNumberOfFrames && frameNumber-1 >=0 && frames.get(frameNumber-1) != null) {
			System.out.println("got um!");
			return frames.get(frameNumber-1);
		} else
			return null;
	}

	public int getFPS() {
		return fps;
	}
	
	public Timer getTimer(){
		return timer;
	}

	public void setFPS(int newFPS) {
		this.fps = newFPS;
	}
	public void setCurrentFrameNumber(int number){
		currentFrameNumber = number;
	}

	public ArrayList<GameCanvas> getFrames() {
		return frames;
	}
	
	public int getTotalNumberOfFrames(){
		return totalNumberOfFrames;
	}

	public GameCanvas getCurrentFrame() {
		System.out.println("getting current frame "+ (currentFrameNumber-1));
		return getFrameBasedOnFrameNumber(currentFrameNumber);
	}

	public void moveToClearFrame() {
		gui.setScreen(new GameCanvas(null));
		 updateCurrentFrameLabel(false);
	}
	
	//input: false if we are on a clear screen, true if we are on some frame we had before
	public void updateCurrentFrameLabel(boolean num) {
		if (!num)
			gui.setCurrentFrameLabel("Current Frame: n/a");
		else
			gui.setCurrentFrameLabel("Current Frame: " + getNumberOfCurrentFrame());
	}
}
