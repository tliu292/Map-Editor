package application;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GUI {

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
		bottom.setId("hbox-bottom");
		bottom.getChildren().add(new Label("Bottom"));
		return bottom;
	}

	private static HBox setupTop() {
		HBox top = new HBox();
		top.setId("hbox-top");
		top.getChildren().add(new Label("Top"));
		return top;
	}

	private static HBox setupCenter() {
		HBox center = new HBox();
		center.setId("hbox-center");
		center.getChildren().add(new Label("Center"));
		return center;
	}

	private static VBox setupLeft() {
		VBox left = new VBox();
		left.setId("vbox-left");
		left.getChildren().add(new Label("Left"));
		return left;
	}

	private static VBox setupRight() {
		VBox right = new VBox();
		right.setId("vbox-right");
		right.getChildren().add(new Label("Right"));
		return right;
	}
}
