package bitformer.GameUI;

import static org.junit.Assert.*;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class GameGUIPanelTests {

	private static final int COMMAND_LINE_HEIGHT = 50;
	private static final int ENTER_BUTTON_LENGTH = 100;
	private static final int ENTER_BUTTON_HEIGHT = 50;
	private static final int COMMAND_LINE_LENGTH = 500;
	private static final int SCREEN_HEIGHT = 120;
	private static final int MAX_COMPONENT_SCREEN_LENGTH = 600;
	private static final int CANVAS_HEIGHT = 250;

	GameGUIPanel GUI;
	@Before
	public void setUp(){
		GUI = new GameGUIPanel();
	}
	
	@Test
	public void GUIHasBoxLayout(){
		assertEquals(new BoxLayout(null, BoxLayout.Y_AXIS).getClass(), GUI.getLayout().getClass());
	}
	
	@Test
	public void GUIHasPanelInVerticalOrderConsistingOfCanvasTextAreaJFieldButton(){
		assertEquals(new GameCanvas().getClass(), GUI.getComponent(0).getClass());
		assertEquals(new JScrollPane().getClass(), GUI.getComponent(1).getClass());
		assertEquals(new JPanel().getClass(), GUI.getComponent(2).getClass());
	}
	
	@Test 
	public void GUIButtonIsNextToGUITextField(){
		assertSame(new JTextField().getClass(), GUI.getUserInputPanel().getComponent(0).getClass());
		assertSame(new JButton().getClass(), GUI.getUserInputPanel().getComponent(1).getClass());
	}
	

	@Test
	public void GUICanvasIsCorrectSize() {
		assertEquals(MAX_COMPONENT_SCREEN_LENGTH, (int)GUI.getScreen().getPreferredSize().getWidth());
		assertEquals(CANVAS_HEIGHT, (int)GUI.getScreen().getPreferredSize().getHeight());
	}
	
	@Test
	public void GUITextAreaScreenIsCorrectSize(){
		assertEquals(MAX_COMPONENT_SCREEN_LENGTH, (int)GUI.getScroller().getPreferredSize().getWidth());
		assertEquals(SCREEN_HEIGHT, (int)GUI.getScroller().getPreferredSize().getHeight());
	}
	
	@Test
	public void GUICommandLineIsCorrectSize(){
		assertEquals(COMMAND_LINE_LENGTH, (int)GUI.getCommandLine().getPreferredSize().getWidth());
		assertEquals(COMMAND_LINE_HEIGHT, (int)GUI.getCommandLine().getPreferredSize().getHeight());
	}
	
	@Test
	public void GUIEnterButtonIsCorrectSize(){
		assertEquals(ENTER_BUTTON_LENGTH, (int)GUI.getEnterButton().getPreferredSize().getWidth());
		assertEquals(ENTER_BUTTON_HEIGHT, (int)GUI.getEnterButton().getPreferredSize().getHeight());
	}
	
	@Test
	public void GUIOutputScreenHasCorrectProperties(){
		assertEquals(true, GUI.getOutputScreen().getLineWrap());
		assertEquals(new Font("AR DESTINE", Font.ITALIC, 15), GUI.getOutputScreen().getFont());
		assertEquals(Color.GREEN, GUI.getOutputScreen().getForeground());
		assertEquals(false, GUI.getOutputScreen().isEditable());
		assertEquals(new EmptyBorder(10,10,10,10).getBorderInsets(null), GUI.getScroller().getBorder().getBorderInsets(null));
	}
	
	@Test
	public void GUICanvasHasCorrectProperties(){
		assertEquals(Color.BLACK, GUI.getScreen().getBackground());

	}
	
	@Test 
	public void GUIEnterButtonHasCorrectProperties(){
		assertEquals("Enter", GUI.getEnterButton().getText());
		assertEquals(new Font("AR DESTINE", Font.PLAIN, 20), GUI.getEnterButton().getFont());
	}
	
	@Test
	public void GUICommandLineHasCorrectProperties(){
		assertEquals(new Font("AR DESTINE", Font.PLAIN, 20), GUI.getCommandLine().getFont());
	}
	

}
