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
	static int z = 0;
	static StringBuffer buffer = new StringBuffer();
	static String[] image = {
			"C:\\Users\\Orion\\workspace\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png",
			"C:\\Users\\Orion\\workspace\\TextBasedGame\\src\\resource\\images\\kirbywalk2.png",
			"C:\\Users\\Orion\\workspace\\TextBasedGame\\src\\resource\\images\\kirbywalk3.png",
			"C:\\Users\\Orion\\workspace\\TextBasedGame\\src\\resource\\images\\kirbywalk4.png",
			"C:\\Users\\Orion\\workspace\\TextBasedGame\\src\\resource\\images\\kirbywalk5.png",
			"C:\\Users\\Orion\\workspace\\TextBasedGame\\src\\resource\\images\\kirbywalk6.png",
			"C:\\Users\\Orion\\workspace\\TextBasedGame\\src\\resource\\images\\kirbywalk7.png",
			"C:\\Users\\Orion\\workspace\\TextBasedGame\\src\\resource\\images\\kirbywalk8.png",
			"C:\\Users\\Orion\\workspace\\TextBasedGame\\src\\resource\\images\\kirbywalk9.png",
			"C:\\Users\\Orion\\workspace\\TextBasedGame\\src\\resource\\images\\kirbywalk10.png"
	};
	
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
		}else if(text.equals("image")){
			GameCanvas.addImage(new CanvasImage(image[z] ,  new Point(3,4)));
			x=x+5; y=y+5;
			z++;
		}
		
	}

}
