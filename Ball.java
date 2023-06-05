

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


public class Ball {
    protected Circle circle;
    private Pane pitch;
    private double deltaY = 3, deltaX = -3;
    private Player player1, player2;
    private Game game;
    final Timeline loop;


    public Ball(Pane pitch, Player player1, Player player2, Game game){
        this.game = game;
        this.pitch = pitch;
        this.player1 = player1;
        this.player2 = player2;
        circle = new Circle(8, Color.YELLOWGREEN);
        circle.relocate(417, 327);
        pitch.getChildren().addAll(circle);

        loop = new Timeline(new KeyFrame(Duration.millis(10), bouncingBallHandler));
        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();

    }

    //handler for bouncing ball
    EventHandler<ActionEvent> bouncingBallHandler = 
        new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent t) {
            circle.setLayoutX(circle.getLayoutX() + deltaX);
            circle.setLayoutY(circle.getLayoutY() + deltaY);
            
                final Bounds bounds = pitch.getBoundsInLocal();
                final boolean atRightBorder = circle.getLayoutX() >= (bounds.getMaxX() - circle.getRadius());
                final boolean atLeftBorder = circle.getLayoutX() <= (bounds.getMinX() + circle.getRadius());
                final boolean atBottomBorder = circle.getLayoutY() >= (bounds.getMaxY() - circle.getRadius());
                final boolean atTopBorder = circle.getLayoutY() <= (bounds.getMinY() + circle.getRadius());

                if (atBottomBorder || atTopBorder) {
                    deltaY *= -1;
                }
                if (atRightBorder || atLeftBorder) {
                    deltaX *= -1;
                }

                 


                boolean top1 = circle.getLayoutY() >= (player1.rectangle.getLayoutY()-7.9);
                boolean down1 = circle.getLayoutY() <= (player1.rectangle.getLayoutY()+57.9);
                boolean right1 = circle.getLayoutX() <= (player1.rectangle.getLayoutX()+17.9);
                boolean left1 = circle.getLayoutX() >= (player1.rectangle.getLayoutX()-7.9);

                if (top1 && down1 && right1 && left1){
                    deltaX *= -1;
                    // if((circle.getLayoutY() <= (player1.rectangle.getLayoutY()-8.1)) || (circle.getLayoutY() >= (player1.rectangle.getLayoutY()+58.1)))
                    // {
                    //     deltaY *= -1;
                        
                    // } else 
                    
                }

                boolean top2 = circle.getLayoutY() >= (player2.rectangle.getLayoutY()-7.9);
                boolean down2 = circle.getLayoutY() <= (player2.rectangle.getLayoutY()+57.9);
                boolean right2 = circle.getLayoutX() <= (player2.rectangle.getLayoutX()+17.9);
                boolean left2 = circle.getLayoutX() >= (player2.rectangle.getLayoutX()-7.9);

                if (top2 && down2 && right2 && left2){
                    deltaX *= -1;
                    // if((circle.getLayoutY() >= (player2.rectangle.getLayoutY()-8.0)) || (circle.getLayoutY() <= (player2.rectangle.getLayoutY()+58.0)))
                    // {
                    //     deltaY *= -1;
                        
                    //} 
                    //else 
                   
                }
            
                boolean goalFor1 = atLeftBorder && (circle.getLayoutY() <= 435.0) && (circle.getLayoutY() >= 235.0);
                boolean goalFor2 = atRightBorder && (circle.getLayoutY() <= 435.0) && (circle.getLayoutY() >= 235.0);
                
                if(goalFor1) game.changeScore(player1, 1);
                if(goalFor2) game.changeScore(player2, 2);
                
          
        }
    };

}
