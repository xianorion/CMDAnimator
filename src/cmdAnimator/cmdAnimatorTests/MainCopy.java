package cmdAnimator.cmdAnimatorTests;

import cmdAnimator.GUI;
import cmdAnimator.GameGui;
import cmdAnimator.GuiCommands;
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
				if (gui.getCommandLineText().equals("") &&(Animation.getNumberOfCurrentFrame() +1) <= Animation.getTotalNumberOfFrames())
					CommandParser.parseText("goto frame " + (Animation.getNumberOfCurrentFrame() + 1));
			} else if (run.equalsIgnoreCase("left")) {
				if (gui.getCommandLineText().equals("") && (Animation.getNumberOfCurrentFrame() - 1) >0)
					CommandParser.parseText("goto frame " + (Animation.getNumberOfCurrentFrame() - 1));
				else if(gui.getCommandLineText().equals("") && (Animation.getNumberOfCurrentFrame() -1) == 0){
					CommandParser.parseText("goto frame 1");
				}
			} else if (run.equalsIgnoreCase("up")) {
				if (GuiCommands.currentCommandView > 0 && GuiCommands.currentCommandView <= GuiCommands.commands.size()) {
					gui.getCommandLine().setText(GuiCommands.commands.get(GuiCommands.currentCommandView - 1));
					GuiCommands.currentCommandView--;
				}else if(GuiCommands.commands.size() > 0 && GuiCommands.currentCommandView == 0){
					gui.getCommandLine().setText(GuiCommands.commands.get(GuiCommands.currentCommandView));
				}
			} else if (run.equalsIgnoreCase("down")) {
				if (GuiCommands.currentCommandView < GuiCommands.commands.size() - 1 && GuiCommands.currentCommandView >= 0) {
					gui.getCommandLine().setText(GuiCommands.commands.get(GuiCommands.currentCommandView + 1));
					GuiCommands.currentCommandView++;
					System.out.println("current command view " + GuiCommands.currentCommandView);
				}else{
					GuiCommands.currentCommandView = GuiCommands.commands.size();
					gui.getCommandLine().setText("");
				}
		}
	}

}
