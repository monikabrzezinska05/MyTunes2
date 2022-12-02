package GUI.Model;

import BE.Playlist;
import BLL.PlaylistManager;
import BLL.SongManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class PlaylistModel {

    private ObservableList<Playlist> playlist;
    private PlaylistManager plm;

    public PlaylistModel() throws Exception {
        plm = new PlaylistManager();
        playlist = FXCollections.observableArrayList();
        playlist.addAll(plm.getPlaylist());
    }

    public ObservableList<Playlist> getObservablePlaylist() {
        return playlist;
    }

    public void searchPlaylist(String query) throws Exception {
        List<Playlist> searchResults = plm.search(query);
        playlist.clear();  
        playlist.addAll(searchResults);
    }

}
