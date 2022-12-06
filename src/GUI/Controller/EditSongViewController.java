package GUI.Controller;

import BE.Song;
import GUI.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class EditSongViewController extends BaseController implements Initializable {

    public ListView<Song> lstSong;
    private SongModel songModel;
    public TextField txtTitle;
    public TextField txtArtist;
    public TextField txtTime;
    public TextField txtFile;
    public Button cancelSong;
    public Button chooseFile;
    public Button saveSong;
    public ComboBox<String> categoryDropdown;

    @Override
    public void setup() {
        SongModel songModel = getModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoryDropdown.getItems().addAll("Pop", "HipHop", "Jazz", "Rap", "Rock", "Blues", "EDM",
                "Hard Style", "Metal", "Alternative", "Classic", "Country", "R&B", "Soul", "Dødstramp");
        categoryDropdown.getSelectionModel().selectFirst();
    }

    public void handleChooseFile(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        Stage stage = (Stage) cancelSong.getScene().getWindow();
        File f = fc.showOpenDialog(stage);
        txtFile.setText(f.getPath());
    }

    public void handleCancelSong(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelSong.getScene().getWindow();
        stage.close();
    }

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

}
