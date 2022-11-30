package GUI.Model;

// Java imports
import BE.Playlist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// Project imports
import BLL.SongManager;
import BE.Song;
import BE.Playlist;

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

    public ObservableList<Song> getObservableSongs() {
        return songs;
    }

    public ObservableList<Playlist> getObservablePlaylist() {
        return playlist;
    }

    public void searchSong(String query) throws Exception {
        List<Song> searchResults = songManager.search(query);
        songs.clear();
        songs.addAll(searchResults);
    }

    /*public void searchPlaylist(String query) throws Exception {
        List<Playlist> searchResults = songManager.search(query);
<<<<<<< Updated upstream
        playlist.clear();
=======
        playlist.clear;
>>>>>>> Stashed changes
        playlist.addAll(searchResults);
    }*/

    public void updateSong(Song updatedSong) throws Exception {
        songManager.updateSong(updatedSong);
        songs.clear();
        songs.addAll(songManager.getSongs());
    }
<<<<<<< Updated upstream
}
=======

    public void deleteSong() throws  Exception {

    }
}
>>>>>>> Stashed changes
