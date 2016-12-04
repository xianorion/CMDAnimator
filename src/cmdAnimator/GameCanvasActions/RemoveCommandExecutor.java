package cmdAnimator.GameCanvasActions;

import cmdAnimator.GUI;
import cmdAnimator.GameGui;

public class RemoveCommandExecutor implements ICommandExecutor {

	private final String DEFAULT_ADD_ERROR = "Not a command\nType 'help remove' and refer to "+
			"the help sidebar for more information";
	private String error = "Error using remove command.";
	private GameGui guiInUse = GUI.getInstance();
	private FrameAnimator animation = GameAnimator.getInstance();
	
	@Override
	public void execute(String[] parameters) throws InvalidCommandException {
		if (parameters.length == 2) {
			
			switch(parameters[0].toLowerCase()){
			case "frame":
				executeFrameRemoval(parameters);
				break;
				default:
					break;
			}

		
		}else{
			throwErrorWithOutputMessage(DEFAULT_ADD_ERROR);
		}
	}
	

	private void executeFrameRemoval(String[] parameters) throws InvalidCommandException{
		if(parameters.length == 2){
			double frameToRemove = Double.parseDouble(parameters[1]);
			if(frameToRemove % 1 == 0){
				System.out.println((int)frameToRemove);
				animation.deleteFrame((int)frameToRemove);
			}else{
				 throwErrorWithOutputMessage("Frame removal includes a non integer frame as a number");
			}
				
		}else{
			 throwErrorWithOutputMessage("Frame removal includes too many parameters");
		}
	}

	@Override
	public String getErrorType() {
		return error;
	}

	public void throwErrorWithOutputMessage(String errorMsg) throws InvalidCommandException{
		error = errorMsg;
		throw new InvalidCommandException(error);
	}
}
