package cmdAnimator.GameCanvasActions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import cmdAnimator.GUI;
import cmdAnimator.GameUI.GameCanvas;

public class FrameAnimator {

	private static final int ONE_SECOND_IN_MILLI = 1000;
	private int fps;
	private ArrayList<GameCanvas> frames;
	private Timer timer;
	private int currentFrameBeingDisplayed;

	public FrameAnimator(int fps) {
		frames = new ArrayList<GameCanvas>();
		this.fps = fps;
	}

	// add tests for me!!
	public void playAnimation(GUI GUI) {
		// System.out.println("Start playing");
		currentFrameBeingDisplayed = 0;
		GUI.setDisabledForEnterButton(true);
		//GUI.repaint();
		// timer that runs until we have shown all the frames in the arraylist once
		timer = new Timer(ONE_SECOND_IN_MILLI / fps, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (currentFrameBeingDisplayed < frames.size()) {
					GUI.setScreen(frames.get(currentFrameBeingDisplayed));
					System.out.println("repainted ");
					currentFrameBeingDisplayed++;

				} else {
					timer.stop();
					GUI.setDisabledForEnterButton(false);	
				}
			
			}
		});
		timer.setRepeats(true);
		timer.start();
		System.out.println("--------------------------");
	}

	public void addFrameToAnimation(GUI GUI) {
		frames.add(GameCanvas.copy(GUI.getScreen()));
		GUI.setScreen(null);
	}

	public void moveToFrameNumber(GUI GUI) {
		// find the number in arraylist

		// set the GUI screen to this frame

	}

	public void deleteFrame(int frameNumber) {

	}

	public int getNumberOfCurrentFrame(GameCanvas currentFrame) {
		return 0;
	}

	public GameCanvas getFrameBasedOnFrameNumber(int frameNumber) {
		if (frames.get(frameNumber) != null) {
			return frames.get(frameNumber);
		} else
			return null;
	}

	public int getFPS() {
		return fps;
	}

	public void setFPS(int newFPS) {
		this.fps = newFPS;
	}

	public ArrayList<GameCanvas> getFrames() {
		return frames;
	}
}
