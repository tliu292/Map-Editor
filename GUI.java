package application;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GUI {
    private static HBox bottom;
    private static HBox top;
    private static HBox center;
    private static VBox left;
    private static VBox right;

    private static ArrayList<Item> items;

    public static BorderPane setupGUI(BorderPane root) {
        items = new ArrayList<Item>();
        root.setLeft(setupLeft());
        root.setRight(setupRight());
        root.setBottom(setupBottom());
        root.setCenter(setupCenter());
        root.setTop(setupTop());

        return root;
    }

    private static HBox setupBottom() {
        bottom = new HBox();
        bottom.setId("hbox-bottom");
        bottom.getChildren().add(new Label("Bottom"));
        return bottom;
    }

    private static HBox setupTop() {
        top = new HBox();
        top.setId("hbox-top");
        //top.getChildren().add(new Label("Top"));
        top.getChildren().add(setupButton("Room"));
        top.getChildren().add(setupButton("Enemy"));
        top.getChildren().add(setupButton("Weapon"));
        return top;
    }

    private static HBox setupCenter() {
        center = new HBox();
        center.setId("hbox-center");
        center.getChildren().add(new Label("Center"));
        return center;
    }

    private static VBox setupLeft() {
        left = new VBox();
        left.setId("vbox-left");
        left.getChildren().add(new Label("Left"));
        return left;
    }

    private static VBox setupRight() {
        right = new VBox();
        right.setId("vbox-right");
        right.getChildren().add(new Label("Right"));
        return right;
    }
    
    private static Button setupButton(String type) {
        Button addButton = new Button();
        if (type.equals("Room")) {
            addButton.setText("Add Room");
            addButton.setOnAction(e -> {
                Room newRoom = new Room(50, 50, 0);
                items.add(newRoom);
                center.getChildren().add(newRoom.add());
            });
        } else if (type.equals("Enemy")) {
            addButton.setText("Add Enemy");
            addButton.setOnAction(e -> {
                Enemy newEnemy = new Enemy(50, 50, 0);
                items.add(newEnemy);
                center.getChildren().add(newEnemy.add());
            });
        } else if (type.equals("Weapon")) {
            addButton.setText("Add Weapon");
            addButton.setOnAction(e -> {
                Weapon newWeapon = new Weapon(50, 50, 0);
                items.add(newWeapon);
                center.getChildren().add(newWeapon.add());
            });
        }
        return addButton;
    }
}