package cmdAnimator;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;

import cmdAnimator.GameCanvasActions.CommandParser;
import cmdAnimator.GameCanvasActions.FrameAnimator;
import javafx.scene.control.Menu;

//class that saves previously exeucted commands and executes some commands that were called by the gui
public class GuiCommands {
	
	public static ArrayList<String> commands =  new ArrayList<String>();
	public static int currentCommandView = 0;


	private static void updateCurrentCommandView() {
		GuiCommands.currentCommandView = GuiCommands.commands.size();
	}	

	public static void addCommandToPreviousCommandsFeed(GameGui gui) {
		System.out.println("-----adding to feed------");
		if (!commands.contains(gui.getCommandLineText()))
			commands.add(gui.getCommandLineText());
		else
			makeCommandMostRecentlyAddedToQueue(gui.getCommandLineText());
		currentCommandView = commands.size();
	}

	private static void makeCommandMostRecentlyAddedToQueue(String commandLineText) {
		System.out.println("-----command has been executed before------");
		commands.remove(commandLineText);
		commands.add(commandLineText);
	}

	public static void executeBackgroundImageAdditionCommand(GameGui gui, String ImagePath) {
		gui.getCommandLine().setText("add background \"" + ImagePath + "\"");
		GuiCommands.addCommandToPreviousCommandsFeed(gui);
		CommandParser.parseText("add background \"" + ImagePath + "\"");
		gui.getCommandLine().setText("");
		
		updateCurrentCommandView();

	}

	public static void executeImageAdditionCommand(GameGui gui, String ImagePath, String point, String height, String width) {
		gui.getCommandLine().setText("add image \"" + ImagePath + "\" " + point +" "+height+" "+ width);
		GuiCommands.addCommandToPreviousCommandsFeed(gui);
		CommandParser.parseText("add image \"" + ImagePath + "\" " + point+" "+height+" "+ width);
		gui.getCommandLine().setText("");
		
		updateCurrentCommandView();
	}

}
