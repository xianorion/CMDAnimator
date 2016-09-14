package cmdAnimator.GameCanvasActions;

import java.util.ArrayList;
import java.util.Iterator;

import cmdAnimator.GameUI.GameCanvas;
import cmdAnimator.GameUI.GameGUIPanel;

public class FrameAnimator {

	private static final int ONE_SECOND_IN_MILLI = 1000;
	private int fps;
	private ArrayList<GameCanvas> frames;
	int time= 0;
	
	public FrameAnimator(int fps){
		frames = new ArrayList<GameCanvas>();
		this.fps = fps;
	}
	
	public void playAnimation(GameGUIPanel GUI){
		//System.out.println("Start playing");
		int i = 0;
		Iterator<GameCanvas> iterator = frames.iterator();
		while(iterator.hasNext()){
			iterator.next();
			try {
				Thread.sleep(ONE_SECOND_IN_MILLI/fps);
				GUI.setScreen(frames.get(i));  
				GUI.getScreen().repaint(); //repaint the canvas
				GUI.repaint(); //repaint the frame
				//System.out.println("repainted "+i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("---Thread.sleep() in cmdAnimator.FrameAnimator.playAnimation()---");
				e.printStackTrace();
			}
			i++;
		}
	}

	public void addFrameToAnimation(GameGUIPanel GUI){
		frames.add(GUI.getScreen());
		GUI.setScreen(new GameCanvas());
	}
	
	public void moveToFrameNumber(GameGUIPanel GUI){
		//find the number in arraylist
		
		//set the GUI screen to this frame
		
	}
	
	public void deleteFrame(int frameNumber){
		
	}
	
	public int getNumberOfCurrentFrame(GameCanvas currentFrame){
		return 0;
	}
	
	public GameCanvas getFrameBasedOnFrameNumber(int frameNumber){
		if(frames.get(frameNumber)!= null){
			return frames.get(frameNumber);
		}
		else
			return null;
	}
	
	public int getFPS(){
		return fps;
	}
	
	public void setFPS(int newFPS){
		this.fps =  newFPS;
	}
	
	public ArrayList<GameCanvas> getFrames(){
		return frames;
	}
}
