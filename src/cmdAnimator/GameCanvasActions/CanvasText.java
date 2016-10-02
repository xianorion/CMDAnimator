package cmdAnimator.GameCanvasActions;


import java.awt.Point;

/*writes a word on the canvas in an animation like way*/
public class CanvasText {

	private Point pointToAddTextTo;
	private String textToAdd;
	

	
	public CanvasText(String textToAdd, Point point){
		this.textToAdd = textToAdd;
		this.pointToAddTextTo = point;
	}
	
	public Point getPointToAddTextTo(){
		return pointToAddTextTo;
	}
	
	public String getTextToAdd(){
		return textToAdd;
	}

}
