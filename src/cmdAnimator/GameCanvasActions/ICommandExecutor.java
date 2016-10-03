package cmdAnimator.GameCanvasActions;

import cmdAnimator.GameGui;

public interface ICommandExecutor{
	public void execute (String[] parameters) throws InvalidCommandException;
	public String getErrorType();

}
