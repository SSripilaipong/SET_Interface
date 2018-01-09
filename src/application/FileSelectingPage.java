package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class FileSelectingPage extends BorderPane {
	
	FileChooser fileChooser = new FileChooser();
	DirectoryChooser dirChooser = new DirectoryChooser();
	
	// center
	GridPane center = new GridPane();
	
	Text description = new Text();

	Label settingLabel = new Label();
	TextField settingText = new TextField();
	Button settingButton = new Button();

	Label inputLabel = new Label();
	TextField inputText = new TextField();
	Button inputFileButton = new Button();
	Button inputDirButton = new Button();
	
	// bottom
	HBox bottom = new HBox();
	
	Button startButton = new Button();
	
	public FileSelectingPage() {
		super();
		
		configureCenter();		
		configureBottom();
	
		setPadding(new Insets(20));
		
		setCenter(center);
		setBottom(bottom);
		
		addListener();
	}
	
	private void configureCenter() {
		description.setText(
			"choose a folder containing settings and input file or folder"
		);
		
		Path settingPath = Paths.get(System.getProperty("user.dir"));
		settingPath = Paths.get(settingPath.toString(), "settings");
		
		settingLabel.setText("setting folder : ");
		settingLabel.setContentDisplay(ContentDisplay.RIGHT);
		settingText.setPrefWidth(Main.screenBounds.getWidth()/4);
		settingText.setText(settingPath.toString());
		settingText.setEditable(false);
		settingButton.setText("choose folder");

		inputLabel.setText("input file or folder : ");
		inputLabel.setContentDisplay(ContentDisplay.RIGHT);
		inputText.setPrefWidth(Main.screenBounds.getWidth()/4);
		inputText.setText("");
		inputText.setEditable(false);
		inputFileButton.setText("choose files");
		inputDirButton.setText("choose folder");
		
		center.setAlignment(Pos.CENTER);
		center.setHgap(5);
		center.setVgap(20);

		center.add(description, 0, 0, 2, 1);

		center.add(settingLabel, 0, 1);
		center.add(settingText, 1, 1);
		center.add(settingButton, 2, 1);
		
		center.add(inputLabel, 0, 2);
		center.add(inputText, 1, 2);
		center.add(inputDirButton, 2, 2);
		center.add(inputFileButton, 3, 2);
	}
	
	private void configureBottom() {
		startButton.setText("start");
		startButton.setPrefWidth(Main.screenBounds.getWidth()/17);
		
		bottom.setAlignment(Pos.CENTER_RIGHT);
		
		bottom.getChildren().add(startButton);
	}
	
	private void addListener() {
		settingButton.setOnMouseClicked(e -> {
			configureDirChooser("choose setting folder", new File(settingText.getText()));
			
			File file = dirChooser.showDialog(Main.primaryStage);
			if(file != null) {
				settingText.setText(file.toString());
			}
		});

		inputDirButton.setOnMouseClicked(e -> {
			File initialDirectory = null;
			
			if(inputText.getText().trim().length() > 0) {
				initialDirectory = new File(inputText.getText());
				if(!initialDirectory.exists())
					initialDirectory = null;
			}
			
			if(initialDirectory == null){
				Path path = Paths.get(System.getProperty("user.dir")).getParent();
				initialDirectory = new File(path.toString());
			}
			
			if(!initialDirectory.isDirectory())
				initialDirectory = initialDirectory.getParentFile();
			
			configureDirChooser("choose input folder", initialDirectory);
			
			File file = dirChooser.showDialog(Main.primaryStage);
			if(file != null) {
				inputText.setText(file.toString());
			}
		});
		
		inputFileButton.setOnMouseClicked(e -> {
			File initialDirectory = null;
			
			if(inputText.getText().trim().length() > 0) {
				initialDirectory = new File(inputText.getText());
				if(!initialDirectory.exists())
					initialDirectory = null;
			}
			
			if(initialDirectory == null){
				Path path = Paths.get(System.getProperty("user.dir")).getParent();
				initialDirectory = new File(path.toString());
			}
			
			if(!initialDirectory.isDirectory())
				initialDirectory = initialDirectory.getParentFile();
			
			configureFileChooser("choose input files", initialDirectory);
			
			List<File> files = fileChooser.showOpenMultipleDialog(Main.primaryStage);
			if(files != null) {
				inputText.setText(
					files.stream()
					.map(f -> f.toString())
					.collect(Collectors.joining(";"))
				);
			}
		});
		
		startButton.setOnMouseClicked(e -> {
			PageManager.showRunningPage();
		});
	}

    private void configureFileChooser(String title, File initialDiretory) {                           
	    fileChooser.setTitle(title);
	    fileChooser.setInitialDirectory(initialDiretory);
    }
    
    private void configureDirChooser(String title, File initialDiretory) {                           
	    dirChooser.setTitle(title);
	    dirChooser.setInitialDirectory(initialDiretory);
    }
}
