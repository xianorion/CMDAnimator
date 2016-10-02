package cmdAnimator;


import cmdAnimator.GameCanvasActions.CanvasImage;
import cmdAnimator.GameCanvasActions.CanvasText;
import cmdAnimator.GameUI.GameCanvas;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GUI extends Group{
	private TextArea outputField;
	private Label libraryTitle;
	private MenuBar libraryMenu;
	private FlowPane canvasPane;
	private GameCanvas stage;
	private TextField userInputField;
	private Button enterButton;
	private Label helpTitle;
	private TextArea helpCommands;
	private ScrollPane helpScroller;
	private final String outputFieldText ="---output---";
	
	public GUI(){
		outputField = new TextArea(outputFieldText);
		libraryTitle = new Label();
		helpTitle = new Label();
		stage  = new GameCanvas();
		userInputField = new TextField("");
		enterButton = new Button("Enter");
		helpCommands = new TextArea();
		helpScroller = new ScrollPane();
		canvasPane = new FlowPane();
		libraryMenu = new MenuBar();
		
		setupIDs();
		setupGUITextFields();
		setupStage();
		setupLibrary();
		setupHelp();
		
		
		BorderPane pane = new BorderPane();
		pane.setId("mainPane");
		VBox outputAreas = new VBox();
		HBox userinputArea = new HBox();
		VBox LibraryArea = new VBox();
		VBox helpArea = new VBox();
		
		canvasPane.getChildren().add(stage);
		userinputArea.getChildren().addAll(userInputField, enterButton);
		outputAreas.getChildren().addAll(canvasPane, outputField, userinputArea);
		outputAreas.setId("outputArea");

		LibraryArea.getChildren().addAll(libraryTitle, libraryMenu);
		LibraryArea.setId("libraryArea");
		helpArea.getChildren().addAll(helpTitle,helpScroller);
		
		pane.setCenter(outputAreas);
		pane.setLeft(helpArea);
		pane.setRight(LibraryArea); 
		
		this.getChildren().add(pane);
		
	}

	private void setupIDs() {
		canvasPane.setId("canvasContainer");
		outputField.setId("outputField");;
		libraryTitle.setId("libraryTitle");
		helpTitle.setId("helpTitle");
		libraryMenu.setId("libraryMenu");
		stage.setId("stage");
		userInputField.setId("commandLine");
		enterButton.setId("enterButton");
		helpCommands.setId("helpCommandArea");
		helpScroller.setId("helpScoller");
		helpCommands.setEditable(false);
		
	}

	private void setupStage() {
		canvasPane.setPrefSize(300, 300);
		enterButton.setPrefSize(55, 35);
		
		userInputField.setPrefSize(555, 35);
		userInputField.setMinHeight(35);
		userInputField.setMinWidth(555);
		userInputField.setMaxHeight(35);
		userInputField.setMaxWidth(555);
		
		outputField.setPrefSize(610, 130);
		outputField.setMinHeight(130);
		outputField.setMinWidth(610);
		outputField.setMaxHeight(130);
		outputField.setMaxWidth(610);	
		outputField.setEditable(false);
	}



	private void setupHelp() {
		helpTitle.setText("Commands");
		helpTitle.setPrefSize(200, 50);
		helpCommands.setWrapText(true);
		helpCommands.setMinHeight(425);
		helpCommands.setMaxHeight(425);
		helpCommands.setMinWidth(200);
		helpCommands.setMaxWidth(200);
		
		helpScroller.setMinHeight(425);
		helpScroller.setMaxHeight(425);
		helpScroller.setMinWidth(200);
		helpScroller.setMaxWidth(200);
		
		helpCommands.setText("This is where help file will be placed");
		helpScroller.setContent(helpCommands);
		helpScroller.setHbarPolicy(ScrollBarPolicy.NEVER);
	}



	private void setupLibrary() {
		
		libraryTitle.setText("Frames");
		libraryTitle.setPrefSize(180, 50);
		libraryMenu.setPrefSize(180, 425);
		libraryMenu.setMinWidth(180);
		libraryMenu.setMaxWidth(180);
		libraryMenu.setMaxHeight(425);
		libraryMenu.setMinHeight(425);
	}


	private void setupGUITextFields() {
		// TODO Auto-generated method stub
		
	}

	public void setDisabledForEnterButton(boolean b) {
		enterButton.setDisable(b);
		
	}

	public void setScreen(GameCanvas gameCanvas) {
		if (gameCanvas != null) {
			stage.setImagesToAdd(gameCanvas.getImagesToAdd());
			stage.setTextToWrite(gameCanvas.getTextToWrite());

		} else {
			stage.clearCanvas();
		}
		stage.updatePane();

	}

	public GameCanvas getScreen() {
		return stage;
	}

	public TextArea getOutputScreen() {
		return outputField;
	}
	
	public Button getEnterButton(){
		return enterButton;
	}

	public TextField getCommandLine() {
		return userInputField;
	}

	public void addUserInputToOutPutFieldAndClearUserInput() {
		appendTextToOutputScreen(userInputField.getText());
		userInputField.setText("");
	}
	
	public String getOutputFieldDefaultText(){
		return outputFieldText;
	}

	public void appendTextToOutputScreen(String string) {
		outputField.setText(outputField.getText()+"\n"+string);
	}
	
	public void addTextToCanvas(CanvasText newText){
		stage.addText(newText);
	}
	
	public void addImageToCanvas(CanvasImage canvasImage){
		stage.addImage(canvasImage);
	}
}
