package GUI.Model;

import BE.Playlist;
import javafx.collections.ObservableList;

import java.util.List;

public class PlaylistModel {

    private ObservableList<Playlist> playlist;
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
