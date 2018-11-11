package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Item {
    protected static ImageView selectedImage;
    protected static Item selectedItem;
    protected int height;
    protected int width;
    protected double positionX;
    protected double positionY;
    protected Image icon;
    protected ImageView imageView;
    
    protected double orgSceneX, orgSceneY;
    protected double orgTranslateX, orgTranslateY;
    
    
    public Item(int height, int width, String path) {
        this.height = height;
        this.width = width;
        this.icon = new Image(path, height, width, true, true);
        this.positionX = Main.SCENE_WIDTH / 2;
        this.positionY = Main.SCENE_HEIGHT / 2;
    }
    
    public ImageView getDraggableImageView() {
        ImageView iv = new ImageView(this.icon);
        
        iv.setOnMousePressed(e -> {
            orgSceneX = e.getSceneX();
            orgSceneY = e.getSceneY();
            orgTranslateX = ((ImageView)(e.getSource())).getTranslateX();
            orgTranslateY = ((ImageView)(e.getSource())).getTranslateY();
        });
        
        iv.setOnMouseDragged(e -> {
            double offsetX = e.getSceneX() - orgSceneX;
            double offsetY = e.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
             
            ((ImageView)(e.getSource())).setTranslateX(newTranslateX);
            ((ImageView)(e.getSource())).setTranslateY(newTranslateY);
            this.positionX = newTranslateX;
            this.positionY = newTranslateY;
        });
        
        iv.setOnMouseClicked(e -> {
            if (selectedImage != null) {
                selectedImage.setStyle("");
            }
            selectedImage = iv;
            selectedItem = this;
            selectedImage.setStyle("-fx-effect: innershadow(gaussian, red, 3, 1.0, 0, 0);");
        });
        imageView = iv;
        return iv;
    }

}
