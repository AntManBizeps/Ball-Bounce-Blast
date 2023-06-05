import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Controller {

    @FXML
    private Pane pitch,  BackgroundBoard;

    @FXML
    private Text score1, score2; 

    protected Rectangle goal1, goal2, player1, player2;
    protected Ball ball;
    protected Stage stage;
    
    public void initialize(Stage stage) {
        //goal on the left side
        this.stage = stage;
        goal1 = new Rectangle();
        goal1.setWidth(5);
        goal1.setHeight(200);
        goal1.setFill(Color.WHITE);
        goal1.setLayoutX(-2);
        goal1.setLayoutY(235);
        goal1.toFront();
        pitch.getChildren().addAll(goal1);
        //goal on the right side
        goal2 = new Rectangle();
        goal2.setWidth(5);
        goal2.setHeight(200);
        goal2.setFill(Color.WHITE);
        goal2.setLayoutX(847);
        goal2.setLayoutY(235);
        goal2.toFront();
        pitch.getChildren().addAll(goal2);
        //player1 on the left
        Player player1 = new Player(Color.RED, 50.0, 310.0);
        pitch.getChildren().addAll(player1.rectangle);
        //player2 on the right
        Player player2 = new Player(Color.YELLOW, 800.0, 310.0);
        pitch.getChildren().addAll(player2.rectangle);

        Game game = new Game(player1, player2, pitch, score2, score1, stage);
        Ball ball = new Ball(pitch, player1,  player2, game);
        game.setBall(ball);
        
    }





    

}
