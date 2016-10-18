package cmdAnimator;

import cmdAnimator.GameCanvasActions.CommandParser;
import cmdAnimator.GameCanvasActions.FrameAnimator;
import cmdAnimator.GameCanvasActions.GameAnimator;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainCopy {
	private FrameAnimator  Animation =  GameAnimator.getInstance();
	private GameGui gui = GUI.getInstance();

	public void CommandTestKeys(String run){
			if (run.equalsIgnoreCase("enter")) {
				if (!GuiCommands.commands.contains(gui.getCommandLineText()))
					GuiCommands.commands.add(gui.getCommandLineText());
				GuiCommands.currentCommandView = GuiCommands.commands.size();
				CommandParser.parseText(gui.getCommandLineText());
			} else if (run.equalsIgnoreCase("right")) {
				if ((Animation.getNumberOfCurrentFrame() +1) <= Animation.getTotalNumberOfFrames())
					CommandParser.parseText("goto frame " + (Animation.getNumberOfCurrentFrame() + 1));
			} else if (run.equalsIgnoreCase("left")) {
				if ( (Animation.getNumberOfCurrentFrame() - 1) >0)
					CommandParser.parseText("goto frame " + (Animation.getNumberOfCurrentFrame() - 1));
			} else if (run.equalsIgnoreCase("up")) {
				if (GuiCommands.currentCommandView > 0 && GuiCommands.currentCommandView <= GuiCommands.commands.size()) {
					gui.getCommandLine().setText(GuiCommands.commands.get(GuiCommands.currentCommandView - 1));
					GuiCommands.currentCommandView--;
				}
			} else if (run.equalsIgnoreCase("down")) {
				if (GuiCommands.currentCommandView < GuiCommands.commands.size() - 1 && GuiCommands.currentCommandView >= 0) {
					gui.getCommandLine().setText(GuiCommands.commands.get(GuiCommands.currentCommandView + 1));
					GuiCommands.currentCommandView++;
					System.out.println("current command view " + GuiCommands.currentCommandView);
				}else if(GuiCommands.currentCommandView == 0 && GuiCommands.commands.size()  == 1 ){
					gui.getCommandLine().setText(GuiCommands.commands.get(GuiCommands.currentCommandView));
					GuiCommands.currentCommandView++;
				}else
					gui.getCommandLine().setText("");
			
		}
	}

}
