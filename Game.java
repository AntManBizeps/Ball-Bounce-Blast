import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game {

    private Player player1, player2;
    private Pane pitch;
    private Text score1, score2;
    private Ball ball;
    protected Stage stage;


    public Game(Player player1, Player player2, Pane pitch, Text score1, Text score2, Stage stage){
        this.stage = stage;
        this.score1 = score1;
        this.score2 = score2;
        this.pitch = pitch;
        this.player1 = player1;
        this.player2 = player2;
        this.score1 = score1;
        this.score2 = score2;
        this.pitch.setFocusTraversable(true);
        this.pitch.setOnKeyPressed(keyPressed);
        timer.start();
    }

    public void setBall(Ball ball){
        this.ball = ball;
    }


    private AnimationTimer timer = new AnimationTimer() {

        @Override
        public void handle(long now) {
            // update paddle positions
        
                player1.rectangle.setLayoutY(player1.rectangle.getLayoutY()+player1.deltaY);
                player2.rectangle.setLayoutY(player2.rectangle.getLayoutY()+player2.deltaY);
    
                if (player1.rectangle.getLayoutY()<= 0) {
                    player1.rectangle.setLayoutY(0);
                }
                if (player2.rectangle.getLayoutY() <= 0) {
                    player2.rectangle.setLayoutY(0);
                }
                if (player1.rectangle.getLayoutY()>= 620) {
                    player1.rectangle.setLayoutY(620);
                }
                if (player2.rectangle.getLayoutY() >= 620) {
                    player2.rectangle.setLayoutY(620);
                }
            
            
        }

    };

    public void changeScore(Player player, int n){
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO, event -> {
                ball.loop.stop();
                timer.stop();
                displayGoal("GOAL!");
                player.increaseScore();
                if(n==1) score1.setText(String.valueOf(player.getScore()));
                else if(n==2) score2.setText(String.valueOf(player.getScore()));
                if(player.getScore()==5){
                    makeWin(n);
                }
            }),
            new KeyFrame(Duration.seconds(2), event -> {
                ball.circle.relocate(417, 327);
                player1.rectangle.relocate(50, 310);   
                player1.deltaY = 0;
                player2.deltaY = 0;          
                player2.rectangle.relocate(800, 310);
                timer.start();
                ball.loop.play();
            })
        );
        timeline.setCycleCount(1); // Uruchom tylko raz
        
        // Uruchom Timeline
        timeline.play();

    }

    public void makeWin(int n){
        try {
            
            Pane root = new Pane();
            Scene scene = new Scene(root, 1000, 800);

            Image icon = new Image("Pong.png");
            stage.getIcons().add(icon);

             // Ustawianie obrazu jako tło sceny
            BackgroundImage backgroundImage = new BackgroundImage(
                new Image("galaktyka.jpg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1000, 800, false, false, false, false)
            );

            // Ustawianie tła na scenie
            Background background = new Background(backgroundImage);
            root.setBackground(background);
            
            stage.setScene(scene);
            stage.setTitle("Ball Bounce Blast!");
            stage.setResizable(false);
            stage.show();

            Text wintext = new Text("Player " + n + " wins!");
            wintext.setFont(Font.font("Arial", FontWeight.BOLD, 50));
            wintext.setFill(Color.WHITE);
            wintext.setStroke(Color.BLACK);
            wintext.toFront();
            wintext.setLayoutX(285);
            wintext.setLayoutY(180);

            Button restartButton = new Button("RESTART");
            restartButton.setPrefSize(200, 100);
             // Ustawianie rozmiaru czcionki na przycisku
            restartButton.setStyle("-fx-font-size: 25px;");
            restartButton.setLayoutX(404);
            restartButton.setLayoutY(305);
            restartButton.toFront();

            root.getChildren().add(restartButton);
            root.getChildren().add(wintext);

            restartButton.setOnAction(event -> {
                GUI newgui = new GUI(stage);
            });


    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void displayGoal(String phrase){
        Text text = new Text(phrase);
        text.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        text.setFill(Color.WHITE);
        text.setLayoutX(345);
        text.setLayoutY(335);
        
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO, event -> {
                // Wyświetl napis "Goal"
                pitch.getChildren().add(text);
            }),
            new KeyFrame(Duration.seconds(2.5), event -> {
                // Ukryj napis "Goal" po 2 sekundach
                pitch.getChildren().remove(text);
            })
        );
        timeline.setCycleCount(1); // Uruchom tylko raz
        
        // Uruchom Timeline
        timeline.play();
    }





    




    //handler for moving a rectangle up and down
    protected EventHandler<KeyEvent> keyPressed = 
        new EventHandler<KeyEvent>() {

        @Override
        public void handle(KeyEvent event) {
            
          switch (event.getCode()) {
            case UP:
                
                player2.deltaY = -6;
                break;
            case DOWN: 
               
                player2.deltaY = 6;
                break;
            case W:
                
                player1.deltaY = -6;
                break;
            case S:
                
                player1.deltaY = 6;
                break;
          
            default:
                break;
          }
        }
            
        
    };


    // protected EventHandler<KeyEvent> keyReleased = new EventHandler<KeyEvent>() {

    //     @Override
    //     public void handle(KeyEvent event) {
    //         // set movement to 0, if the released key was responsible for the paddle
    //         switch (event.getCode()) {
    //             case W:
    //                 player1.deltaY = 0;
    //                 break;
    //             case S:
    //                 player1.deltaY = 0;
    //                 break;
    //             case UP:
    //                 player2.deltaY = 0;
    //                 break;
    //             case DOWN:
    //                 player2.deltaY = 0;
    //                 break;
    //         }
    //     }

    // };


    
}

    



