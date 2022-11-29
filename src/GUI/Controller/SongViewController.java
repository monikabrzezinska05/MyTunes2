package GUI.Controller;

import BE.Playlist;
import GUI.Model.SongModel;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SongViewController implements Initializable {

    @FXML
    private TextField searchBar;

    @FXML
    private TextField playingSong;

    public ListView<Songs> lstSongs;
    public ListView<SongsInPlaylist> lstSongsInPlaylist;
    public ListView<Playlists> lstPlaylists;

    public Button newPlaylist;
    public Button editPLaylist;
    public Button deletePlaylist;

    public Button deleteSongInPlaylist;
    public Button newSong;
    public Button editSong;
    public Button playBtn;
    public Button deleteSong;
    public Button reverseBtn;
    public Button forwardBtn;
    public Button addSong;

    private SongModel songModel;

    public SongViewController()  {

        try {
            songModel = new SongModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    


}
