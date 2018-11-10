package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane root = new BorderPane();
		root = GUI.setupGUI(root);
		Scene mainScene = new Scene(root, 800, 600);
		mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("Map Editor");
		primaryStage.setScene(mainScene);
		primaryStage.show();
		
	}
}
