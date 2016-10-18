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
	
	private void executeAddImageCommand(GameGui gui){
		String point = Prompts.promptUserForPoint();
		
		gui.getCommandLine().setText("add image \""+associatedPath+"\" "+
            	point);
	}

	public String getImagePath(){
		return associatedPath;
	}
}
