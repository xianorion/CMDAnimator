package cmdAnimator.GameCanvasActions;

import cmdAnimator.GUI;
import cmdAnimator.GameGui;

public class FpsCommandExecutor implements ICommandExecutor {
	
	private String error = "Error manipulating fps";
	private FrameAnimator animation = GameAnimator.getInstance();

	@Override
	public void execute(String[] newFPS) throws InvalidCommandException {
		if (newFPS != null && newFPS.length == 1 && newFPS[0] != "") {
			double cFPS = Double.parseDouble(newFPS[0]);
			if (cFPS % 1 == 0 && cFPS > 0){
				animation.setFPS((int) cFPS);
			}else
				throwErrorWithOutputMessage("Fps must be positive and non zero");
		}else{
			throwErrorWithOutputMessage("To change fps say \"fps newFpsInteger\"");
		}
	}
	
	@Override
	public String getErrorType() {
		return error;
	}

	@Override
	public void throwErrorWithOutputMessage(String errorMsg) throws InvalidCommandException {
		error = errorMsg;
		throw new InvalidCommandException(error);
	}

}
