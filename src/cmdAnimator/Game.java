package cmdAnimator;
import java.awt.Canvas;
import java.awt.Point;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.*;

import cmdAnimator.GameCanvasActions.GameCanvasTextWriter;
import cmdAnimator.GameUI.GameCanvas;
import cmdAnimator.GameUI.GameGUIPanel;
import cmdAnimator.GameUI.GameWindow;

public class Game {

	
	public static void main(String args[]){
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		//open game window
		GameWindow bitformerWindow = new GameWindow("Bitformer"); //change the CMD Animator
		GameGUIPanel GUI = bitformerWindow.getGameGUIPanel();
		GameCanvas GameCanvas = GUI.getScreen(0);
		GameCanvas.addText(new GameCanvasTextWriter("Hello world", new Point(25,25)));
		GameCanvas.addText(new GameCanvasTextWriter("polly world", new Point(50,55))); 
		GameCanvas.addText(new GameCanvasTextWriter("Hello world", new Point(95,45)));
		//GameCanvas.deleteText("Hello world");
	}
	
	//if enter is clicked, get command
}
