package cmdAnimator;
import java.awt.Point;

import cmdAnimator.GameCanvasActions.CanvasImage;
import cmdAnimator.GameCanvasActions.CommandParser;
import javafx.scene.control.Button;

public class AddImageButton extends Button {
	
	private String associatedPath;
	
	public AddImageButton(String path){
		this.associatedPath = path;
	}
	
	/* Input: gui object
	 * Output: void
	 * Function: prompts the user for a point to place an image and add command to commandline for execution
	 */
	private void executeAddImageCommand(GameGui gui){
		String point = Prompts.promptUserForPoint();
		
		gui.getCommandLine().setText("add image \""+associatedPath+"\" "+
            	point);
	}

	public String getImagePath(){
		return associatedPath;
	}
}
