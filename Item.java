package application;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Item {
    protected int height;
    protected int width;
    protected int positionX;
    protected int positionY;
    protected Image icon;
    protected String[] IMAGE_PATHS = new String[] {};
    
    public Item(int height, int width, int iconNum) {
        this.height = height;
        this.width = width;
        this.icon = new Image(IMAGE_PATHS[iconNum], height, width, true, true);
        this.positionX = Main.SCENE_WIDTH / 2;
        this.positionY = Main.SCENE_HEIGHT / 2;
    }
    protected void setPaths() {
        
    }
    
    public ImageView add() {
        return new ImageView(this.icon);
    }

}
