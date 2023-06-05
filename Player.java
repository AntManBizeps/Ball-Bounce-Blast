
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Player {
    protected Rectangle rectangle;
    protected Pane pitch;
    protected double deltaY = 0;
    private int score = 0;

    public Player(Color color, double X, double Y){
        this.rectangle = new Rectangle();
        rectangle.setWidth(10);
        rectangle.setHeight(50);
        rectangle.setFill(color);
        rectangle.setLayoutX(X);
        rectangle.setLayoutY(Y);
        rectangle.toFront();
    }

    public void setScore(int n){
        score = n;
    }

    public void increaseScore(){
        score++;  
    }

    public int getScore(){
        return score;
    }

}
