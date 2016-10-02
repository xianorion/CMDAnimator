package cmdAnimator.GameCanvasActions;

import cmdAnimator.GUI;

public interface ICommandExecutor{
	public void execute (String[] parameters, GUI gui, FrameAnimator animator) throws InvalidCommandException;
	public String getErrorType();

}
