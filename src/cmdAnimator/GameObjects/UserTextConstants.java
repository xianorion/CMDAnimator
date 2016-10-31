package cmdAnimator.GameObjects;


import java.util.Arrays;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class UserTextConstants {
	
	private static String textFont = "Arial";
	private static int fontSize =  12;
	private static Color fontColor = Color.BLACK;
	private static UserTextConstants utc = new UserTextConstants();
	private static String[] colorNameChoices= {"Black", "Blue","Cyan", "Dark Gray", "Gray", "Green",
			"Light Gray","Magenta", "Orange", "Pink","Red", "White", "Yellow"
	};
	private static final Color colors[] = {Color.BLACK, Color.BLUE, Color.CYAN, Color.DARKGRAY,
			Color.GRAY, Color.GREEN, Color.LIGHTGRAY, Color.MAGENTA, Color.ORANGE, Color.PINK,
			Color.RED, Color.WHITE, Color.YELLOW};
	private static String[] fontNameChoices = {"Arial", "Courier New", "AR DARLING", "AR DELANEY","AR HERMANN",
			"Chiller", "Calibri","Impact" ,"Jokerman", "MV Boli","Old English Text MT" };
	private static String[] fontSizes = {"8","9","10","11","12","13","14","16","18","20","22","24","26","28",
			"36"};
	
	private UserTextConstants(){}
	
	
	public static UserTextConstants getInstance(){
		return utc;
	}
	
	public void setTextFont(String font){
		textFont = font;
	}
	
	public void setFontSize(int size){
			fontSize=size;
	}
	
	public void setFontSize(String size){
		int index =  Arrays.asList(fontSizes).indexOf(size);
		if(index < fontSizes.length)
			fontSize = Integer.parseInt(fontSizes[index]);
	}
	
	public void setTextColor(String color){
		int index =  Arrays.asList(colorNameChoices).indexOf(color);
		if(index < colors.length && index >= 0)
			fontColor = colors[index];
	}
	
	public String getTextFont(){
		return textFont;
	}
	
	public int getFontSize(){
		return fontSize;
	}
	
	//for text to call all of the below
	public Color getTextColorFill(){
		return fontColor;
	}
	
	public Font getFont(){
		return new Font(textFont, fontSize);
	}
	
	public Color[] getColorChoices(){
		return colors;
	}

	public String[] getColorNameChoices() {
		return colorNameChoices;
	}
	
	public String[] getFontNameChoices(){
		return fontNameChoices;
	}
	
	public String[] getFontSizes(){
		return fontSizes;
	}

}
