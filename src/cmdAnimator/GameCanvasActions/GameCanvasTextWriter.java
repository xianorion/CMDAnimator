package cmdAnimator.GameCanvasActions;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import cmdAnimator.GameUI.GameCanvas;

/*writes a word on the canvas in an animation like way*/
public class GameCanvasTextWriter {

	private Point pointToAddTextTo;
	private String textToAdd;
	

	
	public GameCanvasTextWriter(String textToAdd, Point point){
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
