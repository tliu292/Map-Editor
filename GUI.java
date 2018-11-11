package application;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GUI {
    private static HBox bottom;
    private static HBox top;
    private static GridPane center;
    private static VBox left;
    private static VBox right;

    private static ArrayList<Item> items;
    private static String[] roomPath;
    private static String[] enemyPath;
    private static String[] weaponPath;

    public static BorderPane setupGUI(BorderPane root) {
        items = new ArrayList<Item>();
        roomPath = setPath("src/application/ROOMS_IMAGE_PATH.txt");
        enemyPath = setPath("src/application/ENEMIES_IMAGE_PATH.txt");
        weaponPath = setPath("src/application/WEAPONS_IMAGE_PATH.txt");
        root.setLeft(setupLeft("D"));
        root.setRight(setupRight());
        root.setBottom(setupBottom());
        root.setCenter(setupCenter(root));
        root.setTop(setupTop(root));

        return root;
    }

    private static HBox setupBottom() {
        bottom = new HBox();
        bottom.setId("hbox-bottom");
        bottom.setMinHeight(100);
        bottom.setMaxHeight(100);
        Button zoomIn = new Button();
        zoomIn.setText("Zoom In");
        zoomIn.setOnAction(e -> {
            if (Item.selectedImage != null) {
                double initHeight = Item.selectedImage.getImage().getHeight();
                double initWidth = Item.selectedImage.getImage().getWidth();
                if (Item.selectedImage.getFitHeight() == 0 
                                && Item.selectedImage.getFitHeight() <= 400
                                && Item.selectedImage.getFitWidth() <= 600) {
                    Item.selectedImage.setFitHeight(initHeight * 1.5);
                    Item.selectedImage.setFitWidth(initWidth * 1.5);
                } else if (Item.selectedImage.getFitHeight() <= 400
                                && Item.selectedImage.getFitWidth() <= 600) {
                    Item.selectedImage.setFitHeight(Item.selectedImage.getFitHeight() * 1.5);
                    Item.selectedImage.setFitWidth(Item.selectedImage.getFitWidth() * 1.5);
                }
            }
        });
        bottom.getChildren().add(zoomIn);
        
        Button zoomOut = new Button();
        zoomOut.setText("Zoom Out");
        zoomOut.setOnAction(e -> {
            if (Item.selectedImage != null) {
                double initHeight = Item.selectedImage.getImage().getHeight();
                double initWidth = Item.selectedImage.getImage().getWidth();
                if (Item.selectedImage.getFitHeight() == 0) {
                    Item.selectedImage.setFitHeight(initHeight / 1.5);
                    Item.selectedImage.setFitWidth(initWidth / 1.5);
                } else {
                    Item.selectedImage.setFitHeight(Item.selectedImage.getFitHeight() / 1.5);
                    Item.selectedImage.setFitWidth(Item.selectedImage.getFitWidth() / 1.5);
                }
            }
        });
        bottom.getChildren().add(zoomOut);
        
        Button rotate = new Button();
        rotate.setText("Rotate");
        rotate.setOnAction(e -> {
            if (Item.selectedImage != null) {
                Item.selectedImage.setRotate(Item.selectedImage.getRotate() + 90);
                if (Item.selectedImage.getRotate() == 360) {
                    Item.selectedImage.setRotate(0);
                }
            }
        });
        bottom.getChildren().add(rotate);
        
        return bottom;
    }

    private static HBox setupTop(BorderPane root) {
        top = new HBox();
        top.setId("hbox-top");
        top.getChildren().add(setupButton(root, "Room"));
        top.getChildren().add(setupButton(root, "Enemy"));
        top.getChildren().add(setupButton(root, "Weapon"));
        return top;
    }

    private static GridPane setupCenter(BorderPane root) {
        center = new GridPane();
        center.setId("gridpane-center");
        Image image = new Image("application/images/background.png",2400,3200,true,true);
        BackgroundImage myBI = new BackgroundImage(image,BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        center.setBackground(new Background(myBI));
        center.setOnMouseClicked(e -> {
            if (Item.selectedItem instanceof Enemy) {
                double x = e.getSceneX();
                double y = e.getSceneY();
                Circle point = new Circle(x, y, 3);
                
                //point.setTranslateX(x);
                //point.setTranslateY(y);
            }
        });
        return center;
    }

    private static VBox setupLeft(String mode) {
        left = new VBox();
        left.setId("vbox-left");
        if (mode.equals("D")) {
        left.getChildren().add(new Label("Left"));
        return left;
        } else if (mode.equals("R")) {
            for (int i = 0; i < roomPath.length; i++) {
                Image curRoom = new Image(roomPath[i]);
                Button button = new Button();
                Room newRoom = new Room(100, 100, roomPath[i]);
                ImageView roomView = new ImageView(curRoom);
                roomView.setFitHeight(80);
                roomView.setFitWidth(80);
                button.setGraphic(roomView);
                button.setOnAction( e -> {
                    items.add(newRoom);                    
                    center.add(newRoom.getDraggableImageView(), 2, 2, 1, 1);
                });
                left.getChildren().add(button);
            }
            return left;
        } else if (mode.equals("E")) {
            for (int i = 0; i < enemyPath.length; i++) {
                Image curEnemy = new Image(enemyPath[i]);
                Button button = new Button();
                Enemy newEnemy = new Enemy(50, 50, enemyPath[i]);
                ImageView enemyView = new ImageView(curEnemy);
                enemyView.setFitHeight(80);
                enemyView.setFitWidth(80);
                button.setGraphic(enemyView);
                button.setOnAction( e -> {
                    items.add(newEnemy);
                    center.add(newEnemy.getDraggableImageView(), 2, 2, 1, 1);
                });
                left.getChildren().add(button);
            }
            return left;
        } else if (mode.equals("W")) {
            for (int i = 0; i < weaponPath.length; i++) {
                Image curWeapon = new Image(weaponPath[i]);
                Button button = new Button();
                Weapon newWeapon = new Weapon(30, 30, weaponPath[i]);
                ImageView weaponView = new ImageView(curWeapon);
                weaponView.setFitHeight(80);
                weaponView.setFitWidth(80);
                button.setGraphic(weaponView);
                button.setOnAction( e -> {
                    items.add(newWeapon);
                    center.add(newWeapon.getDraggableImageView(), 2, 2, 1, 1);
                });
                left.getChildren().add(button);
            }
            return left;
        } else {
            return left;
        }
    }

    private static VBox setupRight() {
        right = new VBox();
        right.setId("vbox-right");
        right.getChildren().add(new Label("Right"));
        return right;
    }
    
    private static Button setupButton(BorderPane root, String type) {
        Button addButton = new Button();
        if (type.equals("Room")) {
            addButton.setText("Add Room");
            addButton.setOnAction(e -> {
                root.setLeft(setupLeft("R"));
            });
        } else if (type.equals("Enemy")) {
            addButton.setText("Add Enemy");
            addButton.setOnAction(e -> {
                root.setLeft(setupLeft("E"));
            });
        } else if (type.equals("Weapon")) {
            addButton.setText("Add Weapon");
            addButton.setOnAction(e -> {
                root.setLeft(setupLeft("W"));
            });
        }
        return addButton;
    }
    
    private static String[] setPath(String filePath) {
        ArrayList<String> imagePath = new ArrayList<String>();
        File file = new File(filePath);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                imagePath.add(line.trim());
                line = br.readLine();
            }
            br.close();
        } catch(IOException e) {
            e.printStackTrace();
        } 
        return Arrays.copyOf(imagePath.toArray(), imagePath.size(), String[].class);
    }
}