package cmdAnimator.GameCanvasActions;

import java.awt.Point;

import cmdAnimator.GUI;

public class AddCommandExecutor implements ICommandExecutor {

	private final String DEFAULT_ADD_ERROR = "Not a command\nType 'help add' and refer to "+
			"the help sidebar for more information";
	private String error = "";
	private GUI guiInUse;
	private FrameAnimator animation;
	
	@Override
	public void execute(String[] parameters, GUI gui, FrameAnimator animator) throws InvalidCommandException {
		guiInUse = gui;
		animation = animator;
		
		if (parameters.length == 1 || parameters.length == 3) {

			for (int i = 0; i < parameters.length; i++)
				System.out.println("para " + parameters[i]);
			switch (parameters[0].toLowerCase()) {
			case "image":
				executeImageAddition(parameters);
				break;
			case "text":
				executeTextAddition(parameters);
				break;
			case "frame":
				executeFrameAddition(parameters);
				break;
			default:
				throwErrorWithOutputMessage("Not a valid command");
			}
		} else {
			throwErrorWithOutputMessage(DEFAULT_ADD_ERROR);
		}

	}



	protected void executeFrameAddition(String[] parameters) throws InvalidCommandException{
		if(parameters.length ==1){
		animation.addFrameToAnimation(guiInUse);
		}else{
			throwErrorWithOutputMessage("Invalid Command.\nTo add a new Frame use command: \"Add Frame\"");
		}
	}



	@Override
	public String getErrorType() {
		return error;
	}

	private void executeTextAddition(String[] para) throws InvalidCommandException{
		Point point = convertStringToPoint(para[2]);
		String textToAdd = convertUserStringToExcludeSurroundingQuotationMarks(para[1]);
		System.out.println("String ___________");
		if(point == null){
			throwErrorWithOutputMessage("Point representation is incorrect.\nPoints are in format (X-Coordinate, Y-Coordinate)"
					+ "\nExample: (4,5)");
		}else if(textToAdd == null){
			throwErrorWithOutputMessage("Text representation is incorrect."
					+ "\nExample: \"add text \"Hello World\" (6,6)\"");
		}else{
			guiInUse.addTextToCanvas(new CanvasText(textToAdd, point));
		}

	}

	private void executeImageAddition(String[] para) throws InvalidCommandException{
		Point point = convertStringToPoint(para[2]);
		String imageFile = convertUserStringToFileName(para[1]);
		boolean isAdded =  false;
		if(point == null){
			throwErrorWithOutputMessage("Point representation is incorrect.\nPoints are in format (X-Coordinate, Y-Coordinate)"
					+ "\nExample: (4,5)");
		}else if(imageFile == null){
			throwErrorWithOutputMessage("Image file representation is incorrect."
					+ "\nExample: \"add image \"mypath\\image.gif\" (6,6)\"");
		}else{
			isAdded = guiInUse.addImageToCanvas(new CanvasImage(imageFile, point));
			System.out.println("image "+ imageFile+" is "+isAdded);
			if(!isAdded){
				throwErrorWithOutputMessage("Invalid Image");
			}
		}
	}

	private String convertUserStringToFileName(String string) {
		String filename;
		String[] imagefileArray = string.split("\\\"");
		for(int i = 0; i< imagefileArray.length; i++)
		System.out.println("image file is "+ imagefileArray[i]);
		if(imagefileArray.length != 2){
			filename = null;
		}else
			filename = imagefileArray[1];
		return filename;
	}
	
	protected String convertUserStringToExcludeSurroundingQuotationMarks(String string) {
		System.out.println("string " + string);
		char[] stringArray = string.toCharArray();
		if (string != null && !string.equals("") && stringArray[0] == '"'
				&& stringArray[stringArray.length - 1] == '"') {
			return string.substring(1, stringArray.length - 1);
		}
		return null;
	}

	protected Point convertStringToPoint(String string) {
		Point point =null;
		int x=0,y=0;
		String[] coordinates = string.split("\\(|\\,|\\)|\\s+");

		if (coordinates.length == 3)
			try {
				x = Integer.parseInt(coordinates[1]);
				y = Integer.parseInt(coordinates[2]);
				point = new Point(x, y);

			} catch (NumberFormatException e) {
				point = null;
			}

		return point;
	}

	private void throwErrorWithOutputMessage(String errorMsg) throws InvalidCommandException{
		error = errorMsg;
		throw new InvalidCommandException();
	}
	
}
