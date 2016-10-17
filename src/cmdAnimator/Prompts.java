package cmdAnimator;

import java.awt.Point;
import java.util.Optional;

import javafx.scene.Group;
import javafx.scene.control.TextInputDialog;

public class Prompts extends Group{
	
	

	public static String promptUserForPoint() {
		TextInputDialog dialog = new TextInputDialog("(x,y)");
		dialog.setTitle("Command Prompt");
		dialog.setHeaderText("Input a point value or type \"background\" to set image as background");
		dialog.setContentText("Where would you like to place the image on the canvas? eg. (33,23), background");

		
		Optional<String> point = dialog.showAndWait();
		if (point.isPresent()){
		    return point.get();
		}
		
		return "";
	}

}
