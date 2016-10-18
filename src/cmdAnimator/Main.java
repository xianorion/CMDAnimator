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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;



public class Main extends Application {
	
	public static ArrayList<String> commands =  new ArrayList<String>();
	private static int currentCommandView = 0;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("CMDAnimator");
			FrameAnimator Animation = GameAnimator.getInstance();
			GameGui gui = GUI.getInstance();						
			Button enterButton =gui.getEnterButton();
			Menu libraryMenu = gui.getImageLibrary();
		    setListenerForImageLibraryInGui(gui, libraryMenu);
			setEnterButtonFromGuiAsAListener(primaryStage, gui, enterButton);
			//listener for file choosing 
			enableFileChooserListener(primaryStage, gui);

			TextField cmdLine = gui.getCommandLine();

			cmdLine.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					if (event.getCode().equals(KeyCode.ENTER)) {
						if (!commands.contains(gui.getCommandLineText()))
							commands.add(gui.getCommandLineText());
						currentCommandView = commands.size();
						CommandParser.parseText(gui.getCommandLineText());
					} else if (event.getCode().equals(KeyCode.RIGHT)) {
						if (gui.getCommandLineText().equals("") && (Animation.getNumberOfCurrentFrame() +1) <= Animation.getTotalNumberOfFrames())
							CommandParser.parseText("goto frame " + (Animation.getNumberOfCurrentFrame() + 1));
					} else if (event.getCode().equals(KeyCode.LEFT)) {
						if (gui.getCommandLineText().equals("") && (Animation.getNumberOfCurrentFrame() - 1) >0)
							CommandParser.parseText("goto frame " + (Animation.getNumberOfCurrentFrame() - 1));
					} else if (event.getCode().equals(KeyCode.UP)) {
						if (currentCommandView > 0) {
							gui.getCommandLine().setText(commands.get(currentCommandView - 1));
							currentCommandView--;
						}
					} else if (event.getCode().equals(KeyCode.DOWN)) {
						if (currentCommandView < commands.size() - 1) {
							gui.getCommandLine().setText(commands.get(currentCommandView + 1));
							currentCommandView++;
							System.out.println("current command view " + currentCommandView);
						}else
							gui.getCommandLine().setText("");

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

	private void setListenerForImageLibraryInGui(GameGui gui, Menu libraryMenu) {
		libraryMenu.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				MenuItem item = ((MenuItem) event.getSource());
				Menu m = gui.getImageLibrary();
				int i = 0;
				// find the item clicked in the library menu
				while (m.getItems().get(i) != null && m.getItems().get(i).equals(item)) {
					i++;
				}
				// alert gui that this image has been added to library so we
				// dont add again and execute
				// its addition to canvas
				gui.buttonExecuteCalled = true;
				String point = Prompts.promptUserForPoint();
				if (point.equalsIgnoreCase("background")) {
					GuiCommands.executeBackgroundImageAdditionCommand(gui,
							((AddImageButton) m.getItems().get(i).getGraphic()).getImagePath(), commands,
							currentCommandView);
				} else {
					GuiCommands.executeImageAdditionCommand(gui,
							((AddImageButton) m.getItems().get(i).getGraphic()).getImagePath(), point, commands,
							currentCommandView);
				}
			}
		});
	}

	private void setEnterButtonFromGuiAsAListener(Stage primaryStage, GameGui gui, Button enterButton) {
		enterButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!commands.contains(gui.getCommandLineText()))
					commands.add(gui.getCommandLineText());
				currentCommandView++;
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
						GuiCommands.executeBackgroundImageAdditionCommand(gui, file.getAbsolutePath(), commands,
								currentCommandView);
					} else {
						GuiCommands.executeImageAdditionCommand(gui, file.getAbsolutePath(), point, commands,
								currentCommandView);
					}

				}
			}
		});
	}

	public static void main(String[] args) {
		launch(args);

	}
}
