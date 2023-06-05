import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class preController {
    
    @FXML
    private Button playButton, instructionButton;

    protected Stage stage;

    public void initialize(Stage stage){
        this.stage = stage;
    }

    public void playClicked(ActionEvent event){
        GUI gui = new GUI(stage);
    }

    public void instructionClicked(ActionEvent event){                   

        Alert a = new Alert(AlertType.NONE,                 
        "Name: Pong\nAuthor: Antoni Adamczyk\nHint: W & S for player on the left, UP & DOWN for player on the right to move up and down.\nGame is played until 5 goals scored.\n",ButtonType.OK);
        
        a.setTitle("About");
        a.show();
    }
}
