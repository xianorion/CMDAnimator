package cmdAnimator;

import java.io.File;
import java.util.ArrayList;

import cmdAnimator.GameCanvasActions.CommandParser;

public class GuiCommands {

	public static void executeAddBackgroundCommand(GameGui gui, File file) {
		gui.getCommandLine().setText("add background \"" + file.getAbsolutePath() + "\"");
		CommandParser.parseText("add background \"" + file.getAbsolutePath() + "\"");
	}

	public static void executeAddImageCommand(GameGui gui, File file, String point) {
		gui.getCommandLine().setText("add image \"" + file.getAbsolutePath() + "\" " + point);
		CommandParser.parseText("add image \"" + file.getAbsolutePath() + "\" " + point);
	}

}
