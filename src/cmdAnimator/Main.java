package cmdAnimator;
	
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;

import cmdAnimator.GameCanvasActions.CommandParser;
import cmdAnimator.GameCanvasActions.FrameAnimator;
import cmdAnimator.GameCanvasActions.GameAnimator;
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
import javafx.scene.layout.VBox;



public class Main extends Application {
	
	protected String RobotTester = null;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("CMDAnimator");
			FrameAnimator Animation = GameAnimator.getInstance();
			GameGui gui = GUI.getInstance();						
			Button enterButton =gui.getEnterButton();
			VBox libraryMenu = gui.getImageLibrary();
		    //setListenerForImageLibraryInGui(gui, libraryMenu);
			setEnterButtonFromGuiAsAListener(primaryStage, gui, enterButton);
			//listener for file choosing 
			enableFileChooserListener(primaryStage, gui);

			TextField cmdLine = gui.getCommandLine();

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
							System.out.println("current command view " + GuiCommands.currentCommandView);
						}else{
							GuiCommands.currentCommandView = GuiCommands.commands.size();
							gui.getCommandLine().setText("");
						}
					}

				}
			});

			Scene scene = new Scene(gui, 1040, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	
		
	}

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

	private void enableFileChooserListener(Stage primaryStage, GameGui gui) {
		FileChooser files = new FileChooser();
		gui.getAddImageButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				File file = files.showOpenDialog(primaryStage);
				if (file != null) {
					String point = Prompts.promptUserForPoint();
					if (point.equalsIgnoreCase("background")) {
						GuiCommands.executeBackgroundImageAdditionCommand(gui, file.getAbsolutePath());
						
					} else {
						GuiCommands.executeImageAdditionCommand(gui, file.getAbsolutePath(), point);
					}

				}
			}
		});
	}

	public static void main(String[] args) {
		launch(args);

	}
}
