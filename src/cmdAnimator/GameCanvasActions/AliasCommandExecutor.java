package cmdAnimator.GameCanvasActions;

import cmdAnimator.GUI;
import cmdAnimator.GameGui;

public class AliasCommandExecutor implements ICommandExecutor{

	private final String DEFAULT_ADD_ERROR = "Not a command\nType 'help remove' and refer to "+
			"the help sidebar for more information";
	private String error = "";
	private GameGui guiInUse = GUI.getInstance();
	private FrameAnimator animation = GameAnimator.getInstance();
	
	@Override
	public void execute(String[] parameters) throws InvalidCommandException {
		if (parameters.length == 2) {
			if(!Aliases.AddNewAlias(parameters[0], parameters[1])){
				error = "Cannot use a normal command as an alias.";
				throw new InvalidCommandException("Cannot use a normal command as an alias.");
			}

			guiInUse.appendTextToOutputScreen("Alias created");
		} else if (parameters.length == 1) {
			System.out.println("eeee "+parameters[0]);
			if (parameters[0].equalsIgnoreCase("display")){
				//display the aliases
				for (String key : Aliases.getAliasList().keySet()) {
					System.out.println("printing");
					guiInUse.appendTextToOutputScreen("Alias: \""+key+ "\" replaces: \"" + Aliases.getAliasList().get(key)+"\"");
				}
			}else
				throwErrorWithOutputMessage(DEFAULT_ADD_ERROR);
		} else {
			throwErrorWithOutputMessage(DEFAULT_ADD_ERROR);
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
