package cmdAnimator;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;

import cmdAnimator.GameCanvasActions.CommandParser;
import cmdAnimator.GameCanvasActions.FrameAnimator;
import javafx.scene.control.Menu;

public class GuiCommands {
	
	public static ArrayList<String> commands =  new ArrayList<String>();
	public static int currentCommandView = 0;

	public static void executeAddBackgroundCommand(GameGui gui, File file) {
		gui.getCommandLine().setText("add background \"" + file.getAbsolutePath() + "\"");
		CommandParser.parseText("add background \"" + file.getAbsolutePath() + "\"");
	}

	public static void executeAddImageCommand(GameGui gui, File file, String point) {
		gui.getCommandLine().setText("add image \"" + file.getAbsolutePath() + "\" " + point);
		CommandParser.parseText("add image \"" + file.getAbsolutePath() + "\" " + point);
	}

	public static void addCommandToPreviousCommandsFeed(GameGui gui, ArrayList<String> commands,
			int currentCommandView) {
		if (!commands.contains(gui.getCommandLineText()))
			commands.add(gui.getCommandLineText());
		currentCommandView = commands.size();
	}

	public static void executeBackgroundImageAdditionCommand(GameGui gui, String ImagePath) {
		gui.getCommandLine().setText("add background \"" + ImagePath + "\"");
		GuiCommands.addCommandToPreviousCommandsFeed(gui, commands, currentCommandView);
		CommandParser.parseText("add background \"" + ImagePath + "\"");
	}

	public static void executeImageAdditionCommand(GameGui gui, String ImagePath, String point) {
		gui.getCommandLine().setText("add image \"" + ImagePath + "\" " + point);
		GuiCommands.addCommandToPreviousCommandsFeed(gui, commands, currentCommandView);
		CommandParser.parseText("add image \"" + ImagePath + "\" " + point);
	}

}
