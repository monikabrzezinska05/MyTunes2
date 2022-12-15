package GUI.Controller;

import BE.Playlist;
import BE.Song;
import GUI.Model.PlaylistModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditPlaylistViewController extends BaseController implements Initializable {
    public TextField txtPlaylistTitle;
    public Button cancelEditedPlaylist;
    public Button saveEditedPlaylist;

    public void handleCancelEditPlaylist(ActionEvent actionEvent) {//Cancel button to close the edit playlist window.
        Stage stage = (Stage) cancelEditedPlaylist.getScene().getWindow();
        stage.close();
    }

    public void handleSaveEditedPlaylist(ActionEvent actionEvent) throws Exception {//Button to save the edited playlist in edit playlist window.
        String updatedPlTitle = txtPlaylistTitle.getText();

        Playlist playlistToBeUpdated = playlistModel.getSelectedPlaylist();
        playlistToBeUpdated.setPlTitle(updatedPlTitle);
        playlistModel.updatePlaylist(playlistToBeUpdated);

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void setup() {
        playlistModel = getPlaylistModel();

        txtPlaylistTitle.setText(playlistModel.getSelectedPlaylist().getPlTitle());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }



}
