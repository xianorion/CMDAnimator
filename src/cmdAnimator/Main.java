package cmdAnimator;
	
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;

import cmdAnimator.GameCanvasActions.AddCommandExecutor;
import cmdAnimator.GameCanvasActions.CommandFileRunner;
import cmdAnimator.GameCanvasActions.CommandParser;
import cmdAnimator.GameCanvasActions.FrameAnimator;
import cmdAnimator.GameCanvasActions.GameAnimator;
import cmdAnimator.GameCanvasActions.InvalidCommandException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;



public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("CMDAnimator");
			FrameAnimator Animation = GameAnimator.getInstance();
			GameGui gui = GUI.getInstance();						
			Button enterButton =gui.getEnterButton();
			VBox libraryMenu = gui.getImageLibrary();
			setEnterButtonFromGuiAsAListener(primaryStage, gui, enterButton);
			//listener for file choosing 
			enableFileChooserListener(primaryStage, gui);

			TextField cmdLine = gui.getCommandLine();
			//sets up left, right, up, and down key press actions for the user keyboard clicks
			cmdLine.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					if (event.getCode().equals(KeyCode.ENTER)) {
						if (!GuiCommands.commands.contains(gui.getCommandLineText()))
							GuiCommands.commands.add(gui.getCommandLineText());
						GuiCommands.currentCommandView = GuiCommands.commands.size();
						CommandParser.parseText(gui.getCommandLineText());
					} else if (event.getCode().equals(KeyCode.RIGHT)) {
						if (gui.getCommandLineText().equals("") && (Animation.getNumberOfCurrentFrame() +1) <= Animation.getTotalNumberOfFrames())
							CommandParser.parseText("goto frame " + (Animation.getNumberOfCurrentFrame() + 1));
					} else if (event.getCode().equals(KeyCode.LEFT)) {
						if (gui.getCommandLineText().equals("") && (Animation.getNumberOfCurrentFrame() - 1) >0)
							CommandParser.parseText("goto frame " + (Animation.getNumberOfCurrentFrame() - 1));
						else if(gui.getCommandLineText().equals("") && (Animation.getNumberOfCurrentFrame() -1) == 0){
							CommandParser.parseText("goto frame 1");
						}
					} else if (event.getCode().equals(KeyCode.UP)) {
						if (GuiCommands.currentCommandView > 0 && GuiCommands.currentCommandView <= GuiCommands.commands.size()) {
							gui.getCommandLine().setText(GuiCommands.commands.get(GuiCommands.currentCommandView - 1));
							GuiCommands.currentCommandView--;
						}else if(GuiCommands.commands.size() > 0 && GuiCommands.currentCommandView == 0){
							gui.getCommandLine().setText(GuiCommands.commands.get(GuiCommands.currentCommandView));
						}
					} else if (event.getCode().equals(KeyCode.DOWN)) {
						if (GuiCommands.currentCommandView < GuiCommands.commands.size() - 1 && GuiCommands.currentCommandView >= 0) {
							gui.getCommandLine().setText(GuiCommands.commands.get(GuiCommands.currentCommandView + 1));
							GuiCommands.currentCommandView++;
						}else{
							GuiCommands.currentCommandView = GuiCommands.commands.size();
							gui.getCommandLine().setText("");
						}
					}

				}
			});

			Scene scene = new Scene(gui, 1040, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			setUpSceneMouseListener(scene);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	
		
	}
	//uses the mouse position to get the position of the mouse on the canvas and displays it on
	//the gui's right side.
	private void setUpSceneMouseListener(Scene scene) {
		GameGui gui =  GUI.getInstance();
		scene.setOnMouseMoved(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				if(mouseInsideGui(event))
					gui.setCurrentPointLabel("("+(event.getX()-200)+","+event.getY()+")");
			}

			private boolean mouseInsideGui(MouseEvent event) {
				if(event.getX() >=gui.getHelpPanelWidth() && 
						event.getX() <= (gui.getHelpPanelWidth()+gui.getScreen().getWidth())
						&& event.getY() >=0 
						&& event.getY() <= gui.getScreen().getHeight())
					return true;
				return false;
			}
			
		});
	}
//sets up a listener for when the user clicks the enter key, the command in the commandline should be executed
	private void setEnterButtonFromGuiAsAListener(Stage primaryStage, GameGui gui, Button enterButton) {
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

	//enables a file chooser for the add image button on the gui and the run command file of the gui
	private void enableFileChooserListener(Stage primaryStage, GameGui gui) {
		FileChooser files = new FileChooser();
		FileChooser files2 = new FileChooser();
		FileChooser.ExtensionFilter textFilter = new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt");
		FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
		files.getExtensionFilters().add(imageFilter);
		files2.getExtensionFilters().add(textFilter);

		gui.getAddImageButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				File file = files.showOpenDialog(primaryStage);
				if (file != null) {
					String point = Prompts.promptUserForPoint();
					if(point == null){
						gui.appendTextToOutputScreen("Add image request canceled.");
					}
					else if (point.equalsIgnoreCase("background")) {
						GuiCommands.executeBackgroundImageAdditionCommand(gui, file.getAbsolutePath());
						
					} else if(AddCommandExecutor.convertStringToPoint(point) != null){
						GuiCommands.executeImageAdditionCommand(gui, file.getAbsolutePath(), point, Prompts.imageHeightPrompt(), Prompts.imageWidthPrompt());
					}else
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

	public static void main(String[] args) {
		launch(args);

	}
}
