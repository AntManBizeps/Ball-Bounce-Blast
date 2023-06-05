

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GUI {
    public GUI(Stage stage){
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Grafics.fxml"));
            Parent root = loader.load();


            Image icon = new Image("Pong.png");
            stage.getIcons().add(icon);
            
            Scene scene = new Scene(root, 1000, 800);
            stage.setScene(scene);
            stage.setTitle("Ball Bounce Blast!");
            stage.setResizable(false);
            stage.show();

            // Przekazanie referencji do obiektu Stage do kontrolera
            Controller controller = loader.getController();
            controller.initialize(stage);
            
            
    
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
