package GUI.Controller;

import BE.Playlist;
import GUI.Model.SongModel;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SongViewController extends BaseController implements Initializable {

    @FXML
    private TextField searchBar;

    @FXML
    private TextField playingSong;

    //public ListView<Songs> lstSongs;
    //public ListView<SongsInPlaylist> lstSongsInPlaylist;
    //public ListView<Playlists> lstPlaylists;

    public Button newPlaylist;
    public Button editPlaylist;
    public Button deletePlaylist;

    public Button deleteSongInPlaylist;
    public Button newSong;
    public Button editSong;
    public Button playBtn;
    public Button deleteSong;
    public Button reverseBtn;
    public Button forwardBtn;
    public Button addSong;
    public Slider volumeSlider;

    private SongModel songModel;

    public SongViewController()  {

        try {
            songModel = new SongModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @Override
    public void setup() {

    }

    public void handleNewPlaylist(ActionEvent actionEvent) {
    }

    public void handleEditPlaylist(ActionEvent actionEvent) {
    }

    public void handleDeletePlaylist(ActionEvent actionEvent) {
    }

    public void handleDeleteSongsInPlaylist(ActionEvent actionEvent) {
    }

    public void handleNewSongs(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/NewSongView.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("New / Edit Song");
        stage.show();

    }

    public void handleEditSongs(ActionEvent actionEvent) {
    }

    public void handleDeleteSong(ActionEvent actionEvent) {
    }

    public void handleAddSongs(ActionEvent actionEvent) {
    }
}
