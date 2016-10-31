package cmdAnimator.GameCanvasActions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import cmdAnimator.GUI;
import cmdAnimator.GameGui;

public class HelpCommandExecutor implements ICommandExecutor {

	private final String DEFAULT_HELP_ERROR = "Not a command\nType 'help' and refer to "+
			"the help sidebar for more information";
	private String error = "";
	private GameGui guiInUse = GUI.getInstance();
	
	@Override
	public void execute(String[] parameters) throws InvalidCommandException {
		if( parameters == null || parameters.length == 0){
			placeTextIntoGuiHelpTextArea(getFileInfoInHelpTextArea("..\\TextBasedGame\\src\\resource\\textCommands\\commands.txt"));
		} else if (parameters.length == 1) {
			switch (parameters[0].toLowerCase()) {
			case "add":
				placeTextIntoGuiHelpTextArea(getFileInfoInHelpTextArea("..\\TextBasedGame\\src\\resource\\textCommands\\addCommands.txt"));
				break;
			case "remove":
				placeTextIntoGuiHelpTextArea(getFileInfoInHelpTextArea("..\\TextBasedGame\\src\\resource\\textCommands\\removeCommands.txt"));
				break;
			default:
				throwErrorWithOutputMessage(DEFAULT_HELP_ERROR);
				break;
			}

		} else
			throwErrorWithOutputMessage(DEFAULT_HELP_ERROR);

	}
	
	public String getFileInfoInHelpTextArea(String file) throws InvalidCommandException{	    
	    BufferedReader reader = null;     
	    try {
	       reader = new BufferedReader(new FileReader(file)); 
	       StringBuilder builder = new StringBuilder();
	        String lineOfText = reader.readLine();
	        while (lineOfText != null) {
	            builder.append(lineOfText);
	            builder.append("\n");
	            lineOfText = reader.readLine();
	        }
	        return builder.toString();
	    } catch (IOException e) {
	    	throwErrorWithOutputMessage("file reading error");
		}finally {
	        try {
				reader.close();
			} catch (IOException e) {
				throwErrorWithOutputMessage("file closing error");
			}
	    }
	    return null;
	}
	
	private void placeTextIntoGuiHelpTextArea(String text){
		if(text != null)
		guiInUse.getHelpCommands().setText(text);
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
