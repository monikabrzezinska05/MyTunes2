package GUI.Model;

// Java imports
import BE.Playlist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// Project imports
import BLL.SongManager;
import BE.Song;

import java.util.List;

public class SongModel {

    private ObservableList<Song> songs;
    private ObservableList<Playlist> playlist;
    private SongManager songManager;

    public SongModel() throws Exception {
        songManager = new SongManager();
        songs = FXCollections.observableArrayList();
        //songs.addAll(songManager.);
        playlist = FXCollections.observableArrayList();
        //songs.addAll(songManager.);
    }

    public ObservableList<Song> getSongs() {
        return songs;
    }

    public ObservableList<Playlist> getPlaylist() {
        return playlist;
    }

    public void searchSong(String query) throws Exception {
        List<Song> searchResults = songManager.search(query);
        songs.clear();
        songs.addAll(searchResults);
    }

    /*public void searchPlaylist(String query) throws Exception {
        List<Playlist> searchResults = songManager.search(query);
        playlist.clear();
        playlist.addAll(searchResults);
    }*/

    public void updateSong(Song updatedSong) throws Exception {
        songManager.updateSong(updatedSong);
        songs.clear();
        songs.addAll(songManager.getSongs());
    }
}
