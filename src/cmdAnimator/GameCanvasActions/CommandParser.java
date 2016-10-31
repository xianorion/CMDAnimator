package cmdAnimator.GameCanvasActions;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cmdAnimator.GUI;
import cmdAnimator.GameGui;
import cmdAnimator.GuiCommands;
import cmdAnimator.GameUI.GameCanvas;
import javafx.scene.control.TextArea;
public class CommandParser {	
	private StringBuffer command;
	private static boolean printOutputCommand =true;
	private static ICommandExecutor typeOfCommand;
	private static final ICommandExecutor defaultTypeOfCommand = new ICommandExecutor(){
	
		@Override
		public void execute(String[] parameters)
				throws InvalidCommandException {
		}

		@Override
		public String getErrorType() {
			return "Not a valid command.";
		}

		@Override
		public void throwErrorWithOutputMessage(String errorMsg) throws InvalidCommandException {
			// TODO Auto-generated method stub
			
		}
		
	};
	private static String[] splittingCmds;
	private static GameGui guiInUse = GUI.getInstance();
	private static FrameAnimator animation = GameAnimator.getInstance();
	
	//return true if parse worked, else false
	public static boolean parseText(String text) {
		typeOfCommand = defaultTypeOfCommand;
		splittingCmds = splitTextBasedOnDelimiters(text);
				//text.split("\\s+");

		if (stringArrayIsNotEmpty(splittingCmds)) {
			try {
				executeCommandBasedOnType();
			} catch (InvalidCommandException e) {
				System.out.println("error");
				guiInUse.appendTextToOutputScreen(typeOfCommand.getErrorType());
				typeOfCommand = defaultTypeOfCommand;
				return false;
			}			
		}else{
			typeOfCommand = defaultTypeOfCommand;
			return false;
		}
		return true;
	}

	private static String[] splitTextBasedOnDelimiters(String text) {
		List<String> matchList = new ArrayList<String>();
		Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"");
		Matcher regexMatcher = regex.matcher(text);
		while (regexMatcher.find()) {
		    if (regexMatcher.group(1) != null) {
		        // Add double-quoted string without the quotes
		        matchList.add(regexMatcher.group(1));
		    } else {
		        // Add unquoted word
		        matchList.add(regexMatcher.group());
		    }
		} 
		return matchList.toArray(new String[0]);
	}

	private static boolean stringArrayIsNotEmpty(String[] splittingCmds2) {
		if(splittingCmds2.length < 1 || splittingCmds2[0].equals("")){
			guiInUse.appendTextToOutputScreen("Nothing was typed");
			return false;
		}
		return true;
	}

	private static void executeCommandBasedOnType() throws InvalidCommandException {
		String[] splittingCmdsParameters = new String[splittingCmds.length - 1];

		//get all the parameters, i.e. text after first word
		for (int i = 0; i < splittingCmdsParameters.length; i++) {
			splittingCmdsParameters[i] = splittingCmds[i + 1];
		}

		switch (splittingCmds[0].toLowerCase()) {
		case "add":
			typeOfCommand = new AddCommandExecutor();
			typeOfCommand.execute(splittingCmdsParameters);
			break;
		case "remove":
			typeOfCommand = new RemoveCommandExecutor();
			typeOfCommand.execute(splittingCmdsParameters);
			break;
		case "goto":
			typeOfCommand = new GoToCommandExecutor();
			typeOfCommand.execute(splittingCmdsParameters);
			printOutputCommand =false;
			break;
		case "play":
			animation.playAnimation();
			break;
		case "clear": // check if works
			guiInUse.clearStage();
			break;
		case "done":
			animation.moveToClearFrame();
			break;
		case "fps":
			typeOfCommand = new FpsCommandExecutor();
			typeOfCommand.execute(splittingCmdsParameters);
			break;
		case "help":
			typeOfCommand = new HelpCommandExecutor();
			typeOfCommand.execute(splittingCmdsParameters);
			break;
		case "run":
			CommandFileRunner.parseRunCommand(splittingCmdsParameters);
			break;
		default:
			guiInUse.addUserInputToOutPutFieldAndClearUserInput();
			throw new InvalidCommandException();

		}
		if(printOutputCommand){
			guiInUse.addUserInputToOutPutFieldAndClearUserInput();	
		}
		printOutputCommand = true;
		typeOfCommand = defaultTypeOfCommand;
	}

	private void updateGUIAfterCommandEntered() {
		// call the gui to do this updating of output
		guiInUse.addUserInputToOutPutFieldAndClearUserInput();
	}
	
	private void invalidCommandPrinter(){
		
	}
	
	
	private void execute(){

	}

}
