package GUI.Controller;

import BE.Song;
import GUI.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;

public class EditSongViewController extends BaseController implements Initializable {

    public Button editFile;
    public Button cancelEdit;
    public Button editSave;
    private SongModel songModel;
    public TextField txtTitle;
    public TextField txtArtist;
    public TextField txtTime;
    public TextField txtFile;
    public ComboBox<String> categoryDropdown;

    @Override
    public void setup() {
        songModel = getModel();

        txtTitle.setText(songModel.getSelectedSong().getTitle());
        txtArtist.setText(songModel.getSelectedSong().getArtist());
        txtTime.setText(String.valueOf(songModel.getSelectedSong().getTime()));
        txtFile.setText(songModel.getSelectedSong().getFPath());
        categoryDropdown.getSelectionModel().getSelectedItem();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoryDropdown.getItems().addAll("Pop", "HipHop", "Jazz", "Rap", "Rock", "Blues", "EDM",
                "Hard Style", "Metal", "Alternative", "Classic", "Country", "R&B", "Soul", "Dødstramp");
        categoryDropdown.getSelectionModel().selectFirst();
    }

    public void updateSong(ActionEvent actionEvent) throws Exception {
        String updateTitle = txtTitle.getText();
        String updateArtist = txtArtist.getText();
        int updateTime = Integer.parseInt(txtTime.getText());
        String updateCategory = categoryDropdown.getSelectionModel().getSelectedItem().toString();
        String updateUrl = txtFile.getText();
    }

    public void handleChooseFile(ActionEvent actionEvent) {
    }

    public void handleEditFile(ActionEvent actionEvent) {

        FileChooser fc = new FileChooser();
        Stage stage = (Stage) cancelEdit.getScene().getWindow();
        File f = fc.showOpenDialog(stage);
        txtFile.setText(f.getPath());
    }

    public void handleCancelEdit(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelEdit.getScene().getWindow();
        stage.close();
    }

    public void handleEditSong(ActionEvent actionEvent) throws Exception {
        String updatedTitle = txtTitle.getText();
        String updatedArtist = txtArtist.getText();
        String updatedCategory = categoryDropdown.getSelectionModel().getSelectedItem().toString();

        Song songToBeUpdated = songModel.getSelectedSong();

        songToBeUpdated.setTitle(updatedTitle);
        songToBeUpdated.setArtist(updatedArtist);
        songToBeUpdated.setCategory(updatedCategory);

        songModel.updateSong(songToBeUpdated);

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

}
