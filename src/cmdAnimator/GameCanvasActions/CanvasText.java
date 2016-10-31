package cmdAnimator.GameCanvasActions;

import java.awt.Point;

import cmdAnimator.GameObjects.UserTextConstants;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/*writes a word on the canvas in an animation like way*/
public class CanvasText {

	private Point pointToAddTextTo;
	private String textToAdd;
	private Font font;
	private Color color;
	

	
	public CanvasText(String textToAdd, Point point){
		this.textToAdd = textToAdd;
		this.pointToAddTextTo = point;
		this.font = UserTextConstants.getInstance().getFont();
		this.color =  UserTextConstants.getInstance().getTextColorFill();
	}
	
	public Point getPointToAddTextTo(){
		return pointToAddTextTo;
	}
	
	public String getTextToAdd(){
		return textToAdd;
	}
	public Font getFont(){
		return font;
	}
	
	public Color getColor(){
		return color;
	}

}
