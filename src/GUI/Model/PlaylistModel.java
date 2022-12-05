package GUI.Model;

import BE.Playlist;
import BE.Song;
import BLL.PlaylistManager;
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

    public void createPlaylist(String plTitle) throws Exception {
        Playlist p = plm.createPlaylist(plTitle);

        playlist.add(p);
    }

    public void updatePlaylist(Playlist updatedPlaylist) throws Exception {
        plm.updatePlaylist(updatedPlaylist);
        playlist.clear();
        playlist.addAll(plm.getPlaylist());
    }
    public void deletePlaylist(Playlist deletedPlaylist) throws Exception {
        plm.deletePlaylist(deletedPlaylist);
        playlist.clear();
        playlist.addAll(plm.getPlaylist());
    }

}
