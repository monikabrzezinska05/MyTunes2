package GUI.Controller;

import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SongViewController implements Initializable {

    public TextField txtSongSearch;
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

    public TextField searchBar;
    public TextField



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
