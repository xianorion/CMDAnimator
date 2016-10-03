package cmdAnimator;

//singleton class for GameGui
public class GUI {
	private static GameGui gui = null;
	
	private GUI(){};
	
	public static synchronized GameGui getInstance(){
		if(gui == null){
			gui = new GameGui();
		}
		
		return gui;
	}

}
