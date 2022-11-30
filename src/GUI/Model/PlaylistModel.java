package GUI.Model;

import BE.Playlist;
import BLL.PlaylistManager;
import BLL.SongManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class PlaylistModel {

    private ObservableList<Playlist> playlist;
    private PlaylistManager playlistManager;

    /*public PlaylistModel() throws Exception {
        playlistManager = new PlaylistManager();
        playlist = FXCollections.observableArrayList();
        playlist.addAll(playlistManager.getPlaylist());
    }*/

    public ObservableList<Playlist> getObservablePlaylist() {
        return playlist;
    }

   /* public void searchPlaylist(String query) throws Exception {
        List<Playlist> searchResults = songManager.search(query);
        playlist.clear();
        playlist.clear;
        playlist.addAll(searchResults);
    }*/

}
