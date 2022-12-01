package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewPlaylistViewController extends BaseController implements Initializable {

    public Button cancelPlaylist;
    public Button savePlaylist;

    @FXML
    private TextField txtField;

    @Override
    public void setup() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleCancelPlaylist(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelPlaylist.getScene().getWindow();
        stage.close();
    }

    public void handleSavePlaylist(ActionEvent actionEvent) {
    }
}
