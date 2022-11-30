package GUI;

import GUI.Controller.SongViewController;
import GUI.Model.SongModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("View/SongView.fxml"));
        Parent root = loader.load();

        SongViewController controller = loader.getController();
        controller.setModel(new SongModel());
        controller.setup();

        primaryStage.setTitle("MyTunes");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void main(String[] args) { launch(args); }
}