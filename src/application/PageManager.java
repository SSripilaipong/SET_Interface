package application;

import javafx.scene.Scene;

public class PageManager {

	public static FileSelectingPage fileSelectingPage = new FileSelectingPage();
	public static RunningPage runningPage = new RunningPage();
	public static FinishPage finishPage = new FinishPage();
	
	public static void showFileSelectingPage() {
		Scene scene = new Scene(fileSelectingPage, Main.screenBounds.getWidth()/2, Main.screenBounds.getHeight()/2);
		
		Main.primaryStage.setScene(scene);
	}
	
	public static void showRunningPage() {
		Scene scene = new Scene(runningPage, Main.screenBounds.getWidth()/2, Main.screenBounds.getHeight()/2);
		
		Main.primaryStage.setScene(scene);
		
		runningPage.execute();
	}
	
	public static void showFinishPage() {
		Scene scene = new Scene(finishPage, Main.screenBounds.getWidth()/2, Main.screenBounds.getHeight()/2);
		
		Main.primaryStage.setScene(scene);
	}
}
