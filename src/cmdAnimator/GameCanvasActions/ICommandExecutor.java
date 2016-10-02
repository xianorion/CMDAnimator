package cmdAnimator.GameCanvasActions;

import cmdAnimator.GUI;

public interface ICommandExecutor{
	public void execute (String[] parameters, GUI gui) throws InvalidCommandException;
	public String getErrorType();

}
