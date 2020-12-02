package AlgoritmoDePorter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**** @author Lukas*/
public class AlgoritmoDePorter extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Image icon = new Image(getClass().getResourceAsStream("/images/Porter.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Algoritmo de Porter");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }    
    public static void main(String[] args) {
        launch(args);
    }   
}