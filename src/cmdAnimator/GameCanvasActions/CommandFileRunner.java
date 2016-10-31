package cmdAnimator.GameCanvasActions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import cmdAnimator.GUI;
import cmdAnimator.GameGui;
import cmdAnimator.GuiCommands;

public class CommandFileRunner {
	
	
	
	public static void runBatchFile(String file) throws InvalidCommandException{
		GameGui gui = GUI.getInstance();	
		BufferedReader reader = null;     
	    try {
	       reader = new BufferedReader(new FileReader(file)); 
	        String lineOfText = reader.readLine();
	        while (lineOfText != null) {
	            //put text in command line 
	        	gui.getCommandLine().setText(lineOfText);
	        	if (!GuiCommands.commands.contains(gui.getCommandLineText()))
					GuiCommands.commands.add(gui.getCommandLineText());
				GuiCommands.currentCommandView = GuiCommands.commands.size();
				//execute text
				CommandParser.parseText(gui.getCommandLineText());
	            lineOfText = reader.readLine();
	        }
	    } catch (IOException e) {
	    	System.out.println(e.getMessage());
	    	throw new InvalidCommandException();
		}finally {
	        try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
	    }
	}

	public static void parseRunCommand(String[] parameters) throws InvalidCommandException{
		if(parameters.length == 1){
			runBatchFile(parameters[0]);
		}else
			throw new InvalidCommandException();
	}

}
