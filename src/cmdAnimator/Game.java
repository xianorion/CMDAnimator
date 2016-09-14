package cmdAnimator;
import java.awt.Canvas;
import java.awt.Point;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.*;

import cmdAnimator.GameCanvasActions.CanvasText;
import cmdAnimator.GameCanvasActions.FrameAnimator;
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
			System.out.println("Error, NImbus not available");
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		//open game window
		GameWindow bitformerWindow = new GameWindow("Bitformer"); //change the CMD Animator
		GameGUIPanel GUI = bitformerWindow.getGameGUIPanel();
		GameCanvas GameCanvas = GUI.getScreen();
		FrameAnimator Animation =  new FrameAnimator(3);
		
		//manual test code
		GameCanvas.addText(new CanvasText("Hello world", new Point(25,25)));
		GameCanvas.addText(new CanvasText("polly world", new Point(50,55))); 
		Animation.addFrameToAnimation(GUI);
		GameCanvas = GUI.getScreen();
		GameCanvas.addText(new CanvasText("Hello world2", new Point(30,30)));
		Animation.addFrameToAnimation(GUI);
		GameCanvas = GUI.getScreen();
		GameCanvas.addText(new CanvasText("Hello world2", new Point(35,30)));
		Animation.addFrameToAnimation(GUI);
		GameCanvas = GUI.getScreen();
		GameCanvas.addText(new CanvasText("Hello world2", new Point(40,30)));
		Animation.addFrameToAnimation(GUI);
		GameCanvas = GUI.getScreen();
		GameCanvas.addText(new CanvasText("--Hello world2--", new Point(45,30)));
		GameCanvas.clearCanvas();
		GameCanvas.addText(new CanvasText("NOT Hello world2", new Point(45,30)));
		Animation.addFrameToAnimation(GUI);
		GameCanvas = GUI.getScreen();
		GameCanvas.addText(new CanvasText("Hello world2", new Point(50,30)));
		Animation.addFrameToAnimation(GUI);
		GameCanvas = GUI.getScreen();
		GameCanvas.addText(new CanvasText("Hello world2", new Point(55,30)));
		Animation.addFrameToAnimation(GUI);
		Animation.playAnimation(GUI);
		
		//GameCanvas.deleteText("Hello world");
	}
	
	//if enter is clicked, get command
}
