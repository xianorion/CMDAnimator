package cmdAnimator;

import java.io.File;

import cmdAnimator.GameCanvasActions.AddCommandExecutor;
import cmdAnimator.GameCanvasActions.CommandFileRunner;
import cmdAnimator.GameCanvasActions.CommandParser;
import cmdAnimator.GameCanvasActions.FrameAnimator;
import cmdAnimator.GameCanvasActions.InvalidCommandException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class KeyAndButtonActionListenerSetUp {
	public static void setUpArrowKeyClickActions(FrameAnimator Animation, GameGui gui, TextField cmdLine) {
		cmdLine.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER)) {
					if (!GuiCommands.commands.contains(gui.getCommandLineText()))
						GuiCommands.commands.add(gui.getCommandLineText());
					GuiCommands.currentCommandView = GuiCommands.commands.size();
					CommandParser.parseText(gui.getCommandLineText());
				} else if (event.getCode().equals(KeyCode.RIGHT)) {
					if (gui.getCommandLineText().equals("")
							&& (Animation.getNumberOfCurrentFrame() + 1) <= Animation.getTotalNumberOfFrames())
						CommandParser.parseText("goto frame " + (Animation.getNumberOfCurrentFrame() + 1));
				} else if (event.getCode().equals(KeyCode.LEFT)) {
					if (gui.getCommandLineText().equals("") && (Animation.getNumberOfCurrentFrame() - 1) > 0)
						CommandParser.parseText("goto frame " + (Animation.getNumberOfCurrentFrame() - 1));
					else if (gui.getCommandLineText().equals("") && (Animation.getNumberOfCurrentFrame() - 1) == 0) {
						CommandParser.parseText("goto frame 1");
					}
				} else if (event.getCode().equals(KeyCode.UP)) {
					if (GuiCommands.currentCommandView > 0
							&& GuiCommands.currentCommandView <= GuiCommands.commands.size()) {
						gui.getCommandLine().setText(GuiCommands.commands.get(GuiCommands.currentCommandView - 1));
						GuiCommands.currentCommandView--;
					} else if (GuiCommands.commands.size() > 0 && GuiCommands.currentCommandView == 0) {
						gui.getCommandLine().setText(GuiCommands.commands.get(GuiCommands.currentCommandView));
					}
				} else if (event.getCode().equals(KeyCode.DOWN)) {
					if (GuiCommands.currentCommandView < GuiCommands.commands.size() - 1
							&& GuiCommands.currentCommandView >= 0) {
						gui.getCommandLine().setText(GuiCommands.commands.get(GuiCommands.currentCommandView + 1));
						GuiCommands.currentCommandView++;
					} else {
						GuiCommands.currentCommandView = GuiCommands.commands.size();
						gui.getCommandLine().setText("");
					}
				}

			}
		});
	}

	// uses the mouse position to get the position of the mouse on the canvas
	// and displays it on
	// the gui's right side.
	public static void setUpSceneMouseListener(Scene scene) {
		GameGui gui = GUI.getInstance();
		scene.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (mouseInsideGui(event))
					gui.setCurrentPointLabel("(" + (event.getX() - 200) + "," + event.getY() + ")");
			}

			private boolean mouseInsideGui(MouseEvent event) {
				if (event.getX() >= gui.getHelpPanelWidth()
						&& event.getX() <= (gui.getHelpPanelWidth() + gui.getScreen().getWidth()) && event.getY() >= 0
						&& event.getY() <= gui.getScreen().getHeight())
					return true;
				return false;
			}

		});
	}

	// sets up a listener for when the user clicks the enter key, the command in
	// the commandline should be executed
	public static void setEnterButtonFromGuiAsAListener(Stage primaryStage, GameGui gui, Button enterButton) {
		enterButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!GuiCommands.commands.contains(gui.getCommandLineText()))
					GuiCommands.commands.add(gui.getCommandLineText());
				GuiCommands.currentCommandView = GuiCommands.commands.size();
				CommandParser.parseText(gui.getCommandLineText());

				primaryStage.requestFocus();
			}
		});
	}

	// enables a file chooser for the add image button on the gui and the run
	// command file of the gui
	public static void enableFileChooserListener(Stage primaryStage, GameGui gui) {
		FileChooser files = new FileChooser();
		FileChooser files2 = new FileChooser();
		FileChooser.ExtensionFilter textFilter = new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt");
		FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg",
				"*.gif");
		files.getExtensionFilters().add(imageFilter);
		files2.getExtensionFilters().add(textFilter);

		gui.getAddImageButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				File file = files.showOpenDialog(primaryStage);
				if (file != null) {
					String point = Prompts.promptUserForPoint();
					if (point == null) {
						gui.appendTextToOutputScreen("Add image request canceled.");
					} else if (point.equalsIgnoreCase("background")) {
						GuiCommands.executeBackgroundImageAdditionCommand(gui, file.getAbsolutePath());

					} else if (AddCommandExecutor.convertStringToPoint(point) != null) {
						GuiCommands.executeImageAdditionCommand(gui, file.getAbsolutePath(), point,
								Prompts.imageHeightPrompt(), Prompts.imageWidthPrompt());
					} else
						gui.appendTextToOutputScreen("Invalid Image");
				}
			}
		});

		gui.getRunCommandFileButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				File file = files2.showOpenDialog(primaryStage);
				if (file != null) {
					try {
						CommandFileRunner.runBatchFile(file.getAbsolutePath());
					} catch (InvalidCommandException e1) {
						gui.appendTextToOutputScreen("File Error: must be a textfile!");
					}
				}
			}
		});
	}
}
