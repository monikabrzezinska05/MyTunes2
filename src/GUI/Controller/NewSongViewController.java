package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewSongViewController extends BaseController implements Initializable {

    @FXML
    private Button chooseFIle;
    @FXML
    private Button cancelSong;
    @FXML
    private Button saveSong;
    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtArtist;
    @FXML
    private TextField txtTime;
    @FXML
    private TextField txtFile;
    @FXML
    private ComboBox<String> categoryDropdown;
    

    @Override
    public void setup() {
        
    }

    public void handleCancelSong(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelSong.getScene().getWindow();
        stage.close();
    }

    public void handleSaveSong(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoryDropdown.getItems().addAll("Pop", "HipHop", "Jazz", "Rap", "Rock", "Blues", "EDM",
                "Hard Style", "Metal", "Alternative", "Classic", "Country", "R&B", "Soul");
        categoryDropdown.getSelectionModel().selectFirst();

    }
}
