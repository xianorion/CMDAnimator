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
			Button enterButton = gui.getEnterButton();
			VBox libraryMenu = gui.getImageLibrary();
			KeyAndButtonActionListenerSetUp.setEnterButtonFromGuiAsAListener(primaryStage, gui, enterButton);
			// listener for file choosing
			KeyAndButtonActionListenerSetUp.enableFileChooserListener(primaryStage, gui);

			TextField cmdLine = gui.getCommandLine();
			// sets up left, right, up, and down key press actions for the user
			// keyboard clicks
			KeyAndButtonActionListenerSetUp.setUpArrowKeyClickActions(Animation, gui, cmdLine);

			Scene scene = new Scene(gui, 1040, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			KeyAndButtonActionListenerSetUp.setUpSceneMouseListener(scene);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);

	}
}
