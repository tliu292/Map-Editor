package application;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Enemy extends Item{
	
	protected List<Circle> path; // update the path in sequential order
	
	protected boolean specifyPath;
	
    public Enemy(int height, int width, String path) {
        super(height, width, path);
        this.specifyPath = false;
    }
    
    public void addPathNode(Circle circle) {
    	if (path == null) path = new ArrayList<Circle>();
    	path.add(circle);
    }
    
    public void animatePath() {
    	Path animatedPath = new Path();
		if (path.size() != 0) {	
    		animatedPath.getElements().add(new MoveTo(path.get(0).getCenterX()-375, path.get(0).getCenterY()-325));
			for (int i = 1; i < path.size(); i++) {
				animatedPath.getElements().add(new LineTo(path.get(i).getCenterX()-375, path.get(i).getCenterY()-325));
			}
			PathTransition pathTransition = new PathTransition();
			pathTransition.setDuration(Duration.millis(2000));
			pathTransition.setPath(animatedPath);
			pathTransition.setNode(Item.selectedImage);
			pathTransition.setCycleCount(Timeline.INDEFINITE);
			pathTransition.setAutoReverse(true);
			pathTransition.play();
		}
    }
    
    public void clearPath() {
    	path.clear();
    }
}
