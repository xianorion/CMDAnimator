package cmdAnimator.GameCanvasActions;

import cmdAnimator.GameGui;

public class GameAnimator {
	
	private static FrameAnimator animator = null;
	
	private GameAnimator(){
		
	};
	
	public static synchronized FrameAnimator getInstance(){
		if(animator == null){
			animator = new FrameAnimator();
		}
		
		return animator;
	}

}
