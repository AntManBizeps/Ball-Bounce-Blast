

import javafx.application.Application;

import javafx.scene.layout.Pane;

import javafx.scene.shape.Circle;

import javafx.stage.Stage;



public class App extends Application {
    protected Stage stage;
    public static void main(String[] args) throws Exception {
        Application.launch(args);
    }

    public static Circle circle;
    public static Pane canvas;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        final preGUI pregui = new preGUI(stage);
       
        

    }

    public static void initiate(Stage stage){
        final preGUI pregui = new preGUI(stage);
    }

   

    
}
