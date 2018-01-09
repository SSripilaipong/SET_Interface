package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RunningPage extends BorderPane {
	Text text = new Text("program is running...");
	TextArea textArea = new TextArea();
	
	public RunningPage() {
		VBox box = new VBox();
		
		textArea.setEditable(false);
		
		box.setPadding(new Insets(20));
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(text, textArea);
		
		setCenter(box);
	}
	
	public void execute() {
		try {
			Process process = new ProcessBuilder("python", "test_exec.py").start();
			new Thread(() -> {
				InputStream is = process.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String line;

				try {
					while ((line = br.readLine()) != null) {
					  textArea.appendText(line+"\n");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("finished");
				
				
				
				Platform.runLater(PageManager::showFinishPage);
			}).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
