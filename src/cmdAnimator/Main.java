package cmdAnimator;
	
import cmdAnimator.GameCanvasActions.CommandParser;
import cmdAnimator.GameCanvasActions.FrameAnimator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("CMDAnimator");
			FrameAnimator Animation = new FrameAnimator(8);
			GUI gui = new GUI();			
			
			
			
			Button enterButton =gui.getEnterButton();
			
			enterButton.setOnAction( e -> new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	CommandParser.parseText(gui, gui.getCommandLine().getText(), Animation);
	            	System.out.println("called");
	    			primaryStage.requestFocus();
	            }
	        });
			
			TextField cmdLine= gui.getCommandLine();
			
			cmdLine.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					if (event.getCode().equals(KeyCode.ENTER)) {
						CommandParser.parseText(gui, gui.getCommandLine().getText(), Animation);
					}

				}
			});
			
			Scene scene = new Scene(gui,1040,500);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
		
	}
}
