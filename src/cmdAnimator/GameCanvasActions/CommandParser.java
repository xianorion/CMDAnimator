package cmdAnimator.GameCanvasActions;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cmdAnimator.GUI;
import cmdAnimator.GameGui;
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
	private static final ICommandExecutor defaultTypeOfCommand = new ICommandExecutor(){

		@Override
		public void execute(String[] parameters)
				throws InvalidCommandException {
		}

		@Override
		public String getErrorType() {
			return "Not a valid command.";
		}
		
	};
	private static String[] splittingCmds;
	private static GameGui guiInUse = GUI.getInstance();
	private static FrameAnimator animation = GameAnimator.getInstance();
	
	//return true if parse worked, else false
	public static boolean parseText(String text) {
		typeOfCommand = defaultTypeOfCommand;
		splittingCmds = splitTextBasedOnDelimiters(text);
				//text.split("\\s+");

		if (stringArrayIsNotEmpty(splittingCmds)) {
			try {
				executeCommandBasedOnType();
			} catch (InvalidCommandException e) {
				System.out.println("error");
				guiInUse.appendTextToOutputScreen(typeOfCommand.getErrorType());
				typeOfCommand = defaultTypeOfCommand;
				return false;
			}			
		}else{
			typeOfCommand = defaultTypeOfCommand;
			return false;
		}
		return true;
	}

	private static String[] splitTextBasedOnDelimiters(String text) {
		List<String> matchList = new ArrayList<String>();
		Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"");
		Matcher regexMatcher = regex.matcher(text);
		while (regexMatcher.find()) {
		    if (regexMatcher.group(1) != null) {
		        // Add double-quoted string without the quotes
		        matchList.add(regexMatcher.group(1));
		    } else {
		        // Add unquoted word
		        matchList.add(regexMatcher.group());
		    }
		} 
		return matchList.toArray(new String[0]);
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

		//get all the parameters, i.e. text after first word
		for (int i = 0; i < splittingCmdsParameters.length; i++) {
			splittingCmdsParameters[i] = splittingCmds[i + 1];
		}

		switch (splittingCmds[0].toLowerCase()) {
		case "add":
			typeOfCommand = new AddCommandExecutor();
			typeOfCommand.execute(splittingCmdsParameters);
			break;
		case "remove":
			typeOfCommand = new RemoveCommandExecutor();
			typeOfCommand.execute(splittingCmdsParameters);
			break;
		case "play":
			animation.playAnimation();
			break;
		case "clear": // check if works
			guiInUse.clearStage();
			break;
		default:
			guiInUse.addUserInputToOutPutFieldAndClearUserInput();
			throw new InvalidCommandException();

		}
		guiInUse.addUserInputToOutPutFieldAndClearUserInput();
		typeOfCommand = defaultTypeOfCommand;
	}

	private void updateGUIAfterCommandEntered() {
		// call the gui to do this updating of output
		guiInUse.addUserInputToOutPutFieldAndClearUserInput();
	}
	
	private void invalidCommandPrinter(){
		
	}
	
	
	private void execute(){

	}

}
