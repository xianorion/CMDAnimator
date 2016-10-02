package cmdAnimator.GameCanvasActions;

import java.awt.Point;


import cmdAnimator.GUI;
import cmdAnimator.GameUI.GameCanvas;
import javafx.scene.control.TextArea;
public class CommandParser {

//	static int x = 56;
//	static int y =  56;
//	static int z = 0;
//	static StringBuffer buffer = new StringBuffer();
//	static String[] image = {
//			"C:\\Users\\Orion\\workspace\\TextBasedGame\\src\\resource\\images\\kirbywalk1.png",
//			"C:\\Users\\Orion\\workspace\\TextBasedGame\\src\\resource\\images\\kirbywalk2.png",
//			"C:\\Users\\Orion\\workspace\\TextBasedGame\\src\\resource\\images\\kirbywalk3.png",
//			"C:\\Users\\Orion\\workspace\\TextBasedGame\\src\\resource\\images\\kirbywalk4.png",
//			"C:\\Users\\Orion\\workspace\\TextBasedGame\\src\\resource\\images\\kirbywalk5.png",
//			"C:\\Users\\Orion\\workspace\\TextBasedGame\\src\\resource\\images\\kirbywalk6.png",
//			"C:\\Users\\Orion\\workspace\\TextBasedGame\\src\\resource\\images\\kirbywalk7.png",
//			"C:\\Users\\Orion\\workspace\\TextBasedGame\\src\\resource\\images\\kirbywalk8.png",
//			"C:\\Users\\Orion\\workspace\\TextBasedGame\\src\\resource\\images\\kirbywalk9.png",
//			"C:\\Users\\Orion\\workspace\\TextBasedGame\\src\\resource\\images\\kirbywalk10.png"
//	};
//	
//	public static void parseText(GUI GUI, String text, FrameAnimator animation) {
//		GameCanvas GameCanvas =  GUI.getScreen();
//		if(text.equals("clear")){
//			TextArea GUIFeed = GUI.getOutputScreen();
//			String previousText = GUIFeed.getText();
//			GUIFeed.setText(previousText+"\n"+text);
//			
//			GUI.getScreen().clearCanvas();
//			System.out.println("cleared");
//			
//		}else if(text.equals("play")){
//	                	animation.playAnimation(GUI);	
//		}else if(text.equals("add")){
//			animation.addFrameToAnimation(GUI);
//			GameCanvas = GUI.getScreen();
//		}else if(text.equals("text")){
//			GameCanvas.addText(new CanvasText(buffer.toString(), new Point(x,y)));
//			x=x+5; y=y+5;
//			String text1 = "1";
//			buffer.append(text1);
//		}else if(text.equals("image")){
//			GameCanvas.addImage(new CanvasImage(image[z] ,  new Point(x,y)));
//			x=x+5; 
//			z++;
//		}else if(text.equals("delImg")){
//			GameCanvas.deleteImage(new Point(3,4)); 
//		}else if(text.equals("delTxt")){
//			GameCanvas.deleteText(new Point(x-5,y-5));
//		}
//		
//	}
	
	private StringBuffer command;
	private static ICommandExecutor typeOfCommand;
	private static String[] splittingCmds;
	private static GUI guiInUse;
	
	//return true if parse worked, else false
	public static boolean parseText(GUI gui, String text, FrameAnimator animator) {
		guiInUse = gui;
		splittingCmds = text.split("\\s+");

		if (stringArrayIsNotEmpty(splittingCmds)) {
			try {
				executeCommandBasedOnType();
			} catch (InvalidCommandException e) {
				System.out.println("error");
				gui.appendTextToOutputScreen(typeOfCommand.getErrorType());
				return false;
			}			
		}else{
			return false;
		}
		return true;
	}

	private static boolean stringArrayIsNotEmpty(String[] splittingCmds2) {
		if(splittingCmds2.length < 1 || splittingCmds2[0].equals("")){
			guiInUse.appendTextToOutputScreen("Nothing was typed");
			return false;
		}
		return true;
	}

	private static void executeCommandBasedOnType() throws InvalidCommandException {
		String[] splittingCmdsParameters = new String[splittingCmds.length - 1];

		for (int i = 0; i < splittingCmdsParameters.length; i++) {
			splittingCmdsParameters[i] = splittingCmds[i + 1];
		}

		switch (splittingCmds[0].toLowerCase()) {
		case "add":
			typeOfCommand = new AddCommandExecutor();
			typeOfCommand.execute(splittingCmdsParameters, guiInUse);
			break;
		case "play":

			break;
		case "clear":

			break;
		}

	}

	private void updateGUIAfterCommandEntered(){
		//call the gui to do this updating of output
		guiInUse.addUserInputToOutPutFieldAndClearUserInput();
	}
	
	private void invalidCommandPrinter(){
		
	}
	
	
	private void execute(){

	}

}
