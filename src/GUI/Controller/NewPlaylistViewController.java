package GUI.Controller;

<<<<<<< Updated upstream
=======
import BE.Playlist;
>>>>>>> Stashed changes
import GUI.Model.PlaylistModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewPlaylistViewController extends BaseController implements Initializable {

    public Label alertLabel;

    private PlaylistModel playlistModel;

    public Button cancelPlaylist;
    public Button savePlaylist;

    private PlaylistModel playlistModel;

    @FXML
    private TextField txtPlaylistTitle;

    @Override
    public void setup() {
        playlistModel = getPlaylistModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleCancelPlaylist(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelPlaylist.getScene().getWindow();
        stage.close();
    }

    public void handleSavePlaylist(ActionEvent actionEvent) throws Exception {
        try {
<<<<<<< Updated upstream
            playlistModel.createPlaylist(txtPlaylistTitle.getText());
=======
            PlaylistModel playlistModel = new PlaylistModel();
            playlistModel.createPlaylist(txtField.getText());
>>>>>>> Stashed changes

            Stage stage = (Stage) savePlaylist.getScene().getWindow();
            stage.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
