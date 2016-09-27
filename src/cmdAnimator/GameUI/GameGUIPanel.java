package cmdAnimator.GameUI;

import static org.junit.Assert.assertEquals;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GameGUIPanel extends JPanel {
	
	private JTextField commandLine;
	private JButton enterButton;
	private JPanel userInputPanel;
	private GameCanvas screen;
	private JTextArea outputScreen;
	private JScrollPane scroller;
	private JPanel screenBorder;
	
	private JScrollPane sp;
	
	public GameGUIPanel(){
		screen = new GameCanvas();
		userInputPanel = new JPanel();
		setUpUserInputPanel();		
		setupScreen();
		setupOutputScreen();
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//this.add(screenBorder);

		this.add(sp, BorderLayout.CENTER);
		this.add(scroller);
		this.add(userInputPanel);
	}

	private void setupOutputScreen() {
		outputScreen =  new JTextArea(2,2);
		outputScreen.setLineWrap(true);
		outputScreen.setFont(new Font("AR DESTINE", Font.ITALIC, 15));
		outputScreen.setForeground(Color.GREEN);
		outputScreen.setEditable(false);
		scroller = new JScrollPane(outputScreen);
		scroller.setBorder(new EmptyBorder(10,10,10,10));
		scroller.setPreferredSize(new Dimension(600,120));
	}
	

	private void setupScreen() {
		screen.setPreferredSize(new Dimension(600,250));
		screen.setBackground(Color.WHITE); //changing the color should not be done in this class
		
		screenBorder = new JPanel();
		screenBorder.setBackground(Color.BLACK);
		screenBorder.add(screen);
		screenBorder.setBorder(new EmptyBorder(0,10,0,10));
		sp = new JScrollPane(screenBorder);
	}
	
	private void changeNewScreenProperties(){
		if(screen.getPreferredSize().getHeight() != 600){
			screen.setPreferredSize(new Dimension(600,250));
			screen.setBackground(Color.WHITE); //changing the color should not be done in this class
		}
		screen.repaint();
		screenBorder.repaint();
		sp.repaint();
		screenBorder.add(screen);
		sp.setViewportView(screenBorder);	
		
	}

	private void setUpUserInputPanel() {
		setupCommandLine();
		setupEnterButton();
		userInputPanel.add(commandLine);
		userInputPanel.add(enterButton);
	}

	private void setupEnterButton() {
		enterButton =  new JButton();
		enterButton.setFont(new Font("AR DESTINE", Font.PLAIN, 20));
		enterButton.setText("Enter");
		enterButton.setPreferredSize(new Dimension(100,50));
	}

	private void setupCommandLine() {
		commandLine = new JTextField();
		commandLine.setFont(new Font("AR DESTINE", Font.PLAIN, 20));
		commandLine.setPreferredSize(new Dimension(500,50));
	}
	
	public JTextField getCommandLine(){
		return commandLine;
	}

	public JButton getEnterButton(){
		return enterButton;
	}
	
	public JPanel getUserInputPanel(){
		return userInputPanel;
	}
	
	public GameCanvas getScreen(){
		return screen;
	}
	
	public void setScreen(GameCanvas newScreen){
		//remove the previous canvas from the border and remove the border from the layout
		
		screenBorder.remove(screen);
		sp.getViewport().remove(screenBorder);
		
		screen = newScreen;
		changeNewScreenProperties();
	}
	
	public JTextArea getOutputScreen(){
		return outputScreen;
	}
	
	public JPanel getScreenBorder(){
		return screenBorder;
	}
	
	public JScrollPane getScroller(){
		return scroller;
	}

	public void setEnabledForEnterButton(boolean shouldEnable) {
		enterButton.setEnabled(shouldEnable);
		
	}
}
