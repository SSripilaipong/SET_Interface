package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class FinishPage extends BorderPane {

	Text text = new Text("Finished!!!");
	
	public FinishPage() {
		setPadding(new Insets(20));
		setCenter(text);
	}
}
