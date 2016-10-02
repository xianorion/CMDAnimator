package cmdAnimator.GameCanvasActions;

import java.awt.Point;

import cmdAnimator.GUI;

public class AddCommandExecutor implements ICommandExecutor {

	private final String DEFAULT_ADD_ERROR = "'Add' is not a command\nType 'help add' and refer to "+
			"the help sidebar for more information";
	private String error = "";
	private GUI guiInUse;
	
	@Override
	public void execute(String[] parameters, GUI gui) throws InvalidCommandException {
		guiInUse = gui;
		if (parameters.length == 1 || parameters.length == 3) {

			for (int i = 0; i < parameters.length; i++)
				System.out.println("para " + parameters[i]);
			switch (parameters[0].toLowerCase()) {
			case "image":
				executeImageAddition(parameters);
				break;
			default:
				throwErrorWithOutputMessage("Not a valid command");
			}
		} else {
			throwErrorWithOutputMessage(DEFAULT_ADD_ERROR);
		}

	}

	@Override
	public String getErrorType() {
		return error;
	}
	
	
	private boolean executeImageAddition(String[] para) throws InvalidCommandException{
		Point point = convertStringToPoint(para[2]);
		boolean isAdded =  false;
		if(point == null){
			throwErrorWithOutputMessage("Point representation is incorrect.\nPoints are in format (X-Coordinate, Y-Coordinate)"
					+ "\nExample: (4,5)");
		}else{
			isAdded = guiInUse.addImageToCanvas(new CanvasImage(para[1], point));
			System.out.println("image "+ para[1]+" is "+isAdded);
			if(!isAdded){
				throwErrorWithOutputMessage("Invalid Image");
			}
		}
		
		return isAdded;
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
