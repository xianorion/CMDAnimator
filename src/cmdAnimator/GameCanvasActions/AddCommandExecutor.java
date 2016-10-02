package cmdAnimator.GameCanvasActions;

public class AddCommandExecutor implements ICommandExecutor {

	private String error = "";
	
	@Override
	public void execute(String[] parameters) throws InvalidCommandException {
		
		switch (parameters[0].toLowerCase()) {
		case "text":
			executeTextAddition(parameters);
			break;
		default:
			error = "Not a valid command";
			throw new InvalidCommandException();
		}
		

	}

	@Override
	public String getErrorType() {
		return error;
	}
	
	
	private void executeTextAddition(String[] para){
		
	}

}
