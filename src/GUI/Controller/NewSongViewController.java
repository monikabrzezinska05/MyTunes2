package GUI.Controller;

import BE.Song;
import GUI.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class NewSongViewController extends BaseController implements Initializable {

    public ListView<Song> lstSong;
    private SongModel songModel;

    @FXML
    private Button chooseFile;
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
    public void initialize(URL location, ResourceBundle resources) {

        categoryDropdown.getItems().addAll("Pop", "HipHop", "Jazz", "Rap", "Rock", "Blues", "EDM",
                "Hard Style", "Metal", "Alternative", "Classic", "Country", "R&B", "Soul", "Dødstramp");
        categoryDropdown.getSelectionModel().selectFirst();
    }

        @Override
    public void setup() {
        songModel = getModel();
    }
    //a cancel song that closes down the new song view controller.
    public void handleCancelSong(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelSong.getScene().getWindow();
        stage.close();
    }

    //a button that saves the new song added to the song table.
    public void handleSaveSong(ActionEvent actionEvent) {

        String title = txtTitle.getText();
        String artist = txtArtist.getText();
        String category = categoryDropdown.getSelectionModel().getSelectedItem();
        int time = Integer.parseInt(txtTime.getText());
        String fPath = txtFile.getText();

        Stage stage = (Stage) saveSong.getScene().getWindow();
        stage.close();

        try {
            songModel.createSong(title, artist, category, time, fPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //a choose button, to choose a local filepath.
    public void handleChooseFile(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        Stage stage = (Stage) cancelSong.getScene().getWindow();
        File f = fc.showOpenDialog(stage);
        txtFile.setText(f.getPath());
    }
}