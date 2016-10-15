package cmdAnimator.GameCanvasActions;

import cmdAnimator.GUI;
import cmdAnimator.GameGui;

public class GoToCommandExecutor implements ICommandExecutor {
	
	private final String DEFAULT_ADD_ERROR = "Not a command\nType 'help goto' and refer to "+
			"the help sidebar for more information";
	private String error = "";
	private GameGui guiInUse = GUI.getInstance();
	private FrameAnimator animation = GameAnimator.getInstance();

	@Override
	public void execute(String[] parameters) throws InvalidCommandException {
		if(parameters.length == 2){
			switch(parameters[0].toLowerCase()){
			case "frame":
				executeFrameSwitch(parameters);
				break;
				default:
					
					break;
			}
			
		}else{
			throwErrorWithOutputMessage(DEFAULT_ADD_ERROR);
		}
	}

	private void executeFrameSwitch(String[] parameters) throws InvalidCommandException{
		double frameToMoveTo = Double.parseDouble(parameters[1]);
		if (frameToMoveTo % 1 == 0 && frameToMoveTo > 0) {
			if (frameToMoveTo <= animation.getTotalNumberOfFrames())
				animation.moveToFrameNumber((int) frameToMoveTo);
			else {
				error = "Frame number does not exist";
				throw new InvalidCommandException();
			}

		} else {
			error = "Frame removal includes a non integer frame as a number";
			throw new InvalidCommandException();
		}
	}

	@Override
	public String getErrorType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void throwErrorWithOutputMessage(String errorMsg) throws InvalidCommandException{
		error = errorMsg;
		throw new InvalidCommandException();
	}

}
