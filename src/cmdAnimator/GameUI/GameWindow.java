package cmdAnimator.GameUI;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class GameWindow extends JFrame{

	private GameGUIPanel GUI = new GameGUIPanel();
	
	public GameWindow(String name){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(640,480);
		this.setTitle(name);
		this.getContentPane().add(GUI);
		this.pack();
        this.setVisible(true);
	}
	
	public GameGUIPanel getGameGUIPanel(){
		return GUI;
	}

	
}
