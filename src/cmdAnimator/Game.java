package cmdAnimator;

import java.awt.Canvas;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.*;

import cmdAnimator.GameCanvasActions.CanvasText;
import cmdAnimator.GameCanvasActions.CommandParser;
import cmdAnimator.GameCanvasActions.FrameAnimator;
import cmdAnimator.GameUI.GameCanvas;
import cmdAnimator.GameUI.GameGUIPanel;
import cmdAnimator.GameUI.GameWindow;

public class Game {

	static GameWindow bitformerWindow; // change the CMD Animator
	static FrameAnimator Animation;

	public static void main(String args[]) {

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Error, NImbus not available");
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
		}
		// open game window
		// to reduce issues, the interface must be initialized in another thread
		try {
			SwingUtilities.invokeAndWait(new Runnable() {

				@Override
				public void run() {
					bitformerWindow = new GameWindow("Bitformer"); // change the
																	// CMD
																	// Animator

					Animation = new FrameAnimator(8);
					GameGUIPanel GUI = bitformerWindow.getGameGUIPanel();

					GUI.getEnterButton().addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							CommandParser.parseText(GUI, GUI.getCommandLine().getText(), Animation);

						}

					});
				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		/*------------------------------------MANUAL TESTS-----------------------------------*/

	}

	// if enter is clicked, get command
}
