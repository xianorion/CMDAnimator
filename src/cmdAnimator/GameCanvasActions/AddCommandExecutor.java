package cmdAnimator.GameCanvasActions;

import java.awt.Point;

import cmdAnimator.GUI;
import cmdAnimator.GameGui;

//text with quotations doesn't work: "hello\"this\" does 

public class AddCommandExecutor implements ICommandExecutor {

	private final String DEFAULT_ADD_ERROR = "Not a command\nType 'help add' and refer to "+
			"the help sidebar for more information";
	private String error = "";
	private GameGui guiInUse = GUI.getInstance();
	private FrameAnimator animation = GameAnimator.getInstance();
	
	@Override
	public void execute(String[] parameters) throws InvalidCommandException {
		
		if (parameters.length >= 1 && parameters.length <= 3) {

			for (int i = 0; i < parameters.length; i++)
				System.out.println("para " + parameters[i]);
			switch (parameters[0].toLowerCase()) {
			case "image":
				executeImageAddition(parameters, false);
				break;
			case "text":
				executeTextAddition(parameters);
				break;
			case "frame":
				executeFrameAddition(parameters);
				break;
			case "background":
				executeBackgroundImageAddition(parameters);
				break;
			default:
				throwErrorWithOutputMessage("Not a valid command");
			}
		} else {
			throwErrorWithOutputMessage(DEFAULT_ADD_ERROR);
		}

	}

	private void executeBackgroundImageAddition(String[] parameters) throws InvalidCommandException {
		executeImageAddition(parameters, true);
	}

	protected void executeFrameAddition(String[] parameters) throws InvalidCommandException{
		if(parameters.length ==1){
		animation.addFrameToAnimation();
		}else{
			throwErrorWithOutputMessage("Invalid Command.\nTo add a new Frame use command: \"Add Frame\"");
		}
	}



	@Override
	public String getErrorType() {
		return error;
	}

	private void executeTextAddition(String[] para) throws InvalidCommandException{
		if(para.length == 3){
		Point point = convertStringToPoint(para[2]);
		if(point == null){
			throwErrorWithOutputMessage("Point representation is incorrect.\nPoints are in format (X-Coordinate, Y-Coordinate)"
					+ "\nExample: (4,5)");
		}else{
			guiInUse.addTextToCanvas(new CanvasText(para[1], point));
		}
		}else{
			error = "Not enough parameters to add text";
			throw new InvalidCommandException();
		}
		

	}

	private void executeImageAddition(String[] para, boolean isBackgroundImage) throws InvalidCommandException {
		Point point =  null;
		boolean isAdded = false;
		if (para.length == 3 || (para.length == 2 && isBackgroundImage)) {
			if (!isBackgroundImage)
				point = convertStringToPoint(para[2]);
			else
				point = new Point(0, 0);

			if (point == null) {
				throwErrorWithOutputMessage(
						"Point representation is incorrect.\nPoints are in format (X-Coordinate, Y-Coordinate)"
								+ "\nExample: (4,5)");
			} else {
				if (!isBackgroundImage)
					isAdded = guiInUse.addImageToCanvas(new CanvasImage(para[1], point));
				else
					isAdded = guiInUse.addBackgroundToCanvas(new CanvasImage(para[1], point));
				// System.out.println("image " + para[1] + " is " + isAdded);
				if (!isAdded) {
					throwErrorWithOutputMessage("Invalid Image");
				}
			}
		} else {
			throwErrorWithOutputMessage("not enough parameters to add an image");
		}
	}

	public static Point convertStringToPoint(String string) {
		Point point = null;
		int x = 0, y = 0;
		String[] coordinates = string.split("\\(|\\,|\\)|\\s+");

		if (coordinates.length == 3 && string.contains("(") && string.contains(")"))
			try {
				x = Integer.parseInt(coordinates[1]);
				y = Integer.parseInt(coordinates[2]);
				point = new Point(x, y);

			} catch (NumberFormatException e) {
				point = null;
			}

		return point;
	}

	public void throwErrorWithOutputMessage(String errorMsg) throws InvalidCommandException{
		error = errorMsg;
		throw new InvalidCommandException(error);
	}
	
}
