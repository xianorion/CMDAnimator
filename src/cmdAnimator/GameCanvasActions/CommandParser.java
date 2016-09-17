package cmdAnimator.GameCanvasActions;

import java.awt.Point;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import cmdAnimator.GameUI.GameCanvas;
import cmdAnimator.GameUI.GameGUIPanel;
import cmdAnimator.GameUI.GameWindow;

public class CommandParser {

	static int x = 56;
	static int y =  56;
	static StringBuffer buffer = new StringBuffer();
	
	public static void parseText(GameGUIPanel GUI, String text, FrameAnimator animation) {
		GameCanvas GameCanvas =  GUI.getScreen();
		if(text.equals("clear")){
			JTextArea GUIFeed = GUI.getOutputScreen();
			String previousText = GUIFeed.getText();
			GUIFeed.setText(previousText+"\n"+text);
			
			GUI.getScreen().clearCanvas();
			System.out.println("cleared");
			
		}else if(text.equals("play")){
	                	animation.playAnimation(GUI);	
		}else if(text.equals("add")){
			animation.addFrameToAnimation(GUI);
			GameCanvas = GUI.getScreen();
		}else if(text.equals("text")){
			GameCanvas.addText(new CanvasText(buffer.toString(), new Point(x,y)));
			x=x+5; y=y+5;
			String text1 = "1";
			buffer.append(text1);
		}
		
	}

}
