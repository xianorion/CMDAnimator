package cmdAnimator;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.*;

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
		GameWindow bitformerWindow = new GameWindow("Bitformer");
		
		
		
	}
}
