package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    public final static int SCENE_WIDTH = 800;
    public final static int SCENE_HEIGHT = 700;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        BorderPane root = new BorderPane();
        root = GUI.setupGUI(root);
        Scene mainScene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setTitle("Map Editor");
        primaryStage.setMaxHeight(SCENE_HEIGHT);
        primaryStage.setMaxWidth(SCENE_WIDTH);
        primaryStage.setMinHeight(SCENE_HEIGHT);
        primaryStage.setMinWidth(SCENE_WIDTH);
        primaryStage.setScene(mainScene);
        primaryStage.show();
        
    }
}
