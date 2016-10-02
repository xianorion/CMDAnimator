package cmdAnimator.GameCanvasActions;

public interface ICommandExecutor{
	public void execute (String[] parameters) throws InvalidCommandException;
	public String getErrorType();

}
