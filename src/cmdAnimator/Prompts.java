package cmdAnimator;

import java.awt.Color;
import java.awt.Point;
import java.util.Optional;

import cmdAnimator.GameObjects.UserTextConstants;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.TextInputDialog;

public class Prompts extends Group{
	
	private static UserTextConstants utc = UserTextConstants.getInstance();
	//if the user cancels a prompt, it wont continue asking for prompts
	private static boolean shouldContinuePrompts = true;
	

	public static String promptUserForPoint() {
		TextInputDialog dialog = new TextInputDialog("(x,y)");
		dialog.setTitle("Command Prompt");
		dialog.setHeaderText("Input a point value or type \"background\" to set image as background");
		dialog.setContentText("Where would you like to place the image on the canvas? eg. (33,23), background");

		
		Optional<String> point = dialog.showAndWait();

		if (point.isPresent()){
		    return point.get();
		}
		
		return null;
	}

	public static void promptUserToChangeTextStyle(String prompt) {
		
		switch (prompt) {
		case "color":
			textColorPrompt();
			if (shouldContinuePrompts)
				promptUserToChangeTextStyle("size");
			else
				shouldContinuePrompts = true;
			break;
		case "size":
			textSizePrompt();
			if (shouldContinuePrompts)
				promptUserToChangeTextStyle("style");
			else
				shouldContinuePrompts = true;
			break;
		case "style":
			textFontPrompt();
			break;
		}
		
		
	}
	
	

	private static void textFontPrompt() {
		ChoiceDialog<String> dialog = new ChoiceDialog<>("Arial", utc.getFontNameChoices());
		dialog.setTitle("Change Text Font");
		dialog.setHeaderText("Choose a Font from the dropdown menu. \n(Note: will change every text font in your animation)");
		dialog.setContentText("Choose your font:");

		// set the new color for text
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			utc.setTextFont(result.get());
		}
	}

	private static void textSizePrompt() {
		ChoiceDialog<String> dialog = new ChoiceDialog<>("12", utc.getFontSizes());
		dialog.setTitle("Change Text Size");
		dialog.setHeaderText("Choose a size from the dropdown menu. \n(Note: will change every text size in your animation)");
		dialog.setContentText("Choose your Size:");

		// set the new color for text
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			utc.setFontSize(result.get());
		}else
			shouldContinuePrompts = false;
	}

	private static void textColorPrompt() {
		ChoiceDialog<String> dialog = new ChoiceDialog<>("Black", utc.getColorNameChoices());
		dialog.setTitle("Change Text Color");
		dialog.setHeaderText("Choose a color from the dropdown menu. \n(Note: will change every text color in your animation)");
		dialog.setContentText("Choose your Color:");

		// set the new color for text
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			utc.setTextColor(result.get());
		}else
			shouldContinuePrompts = false;
	}

}
 