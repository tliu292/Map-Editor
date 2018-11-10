import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GUI {
	
	private static final int VBOX_WIDTH = 150;
	private static final int HBOX_HEIGHT = 100;

	public static BorderPane setupGUI(BorderPane root) {
		root.setLeft(setupLeft());
		root.setRight(setupRight());
		root.setBottom(setupBottom());
		root.setTop(setupTop());
		root.setCenter(setupCenter());
		return root;
	}

	private static HBox setupBottom() {
		HBox bottom = new HBox();
		bottom.getChildren().add(new Label("Bottom"));
		return bottom;
	}

	private static HBox setupTop() {
		HBox top = new HBox();
		top.getChildren().add(new Label("Top"));
		return top;
	}

	private static HBox setupCenter() {
		HBox center = new HBox();
		center.getChildren().add(new Label("Center"));
		return center;
	}

	private static VBox setupLeft() {
		VBox left = new VBox();
		left.getChildren().add(new Label("Left"));
		return left;
	}

	private static VBox setupRight() {
		VBox right = new VBox();
		right.getChildren().add(new Label("Right"));
		return right;
	}
}
