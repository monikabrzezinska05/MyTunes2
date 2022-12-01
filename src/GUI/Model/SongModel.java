package GUI.Model;

// Java imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// Project imports
import BLL.SongManager;
import BE.Song;

import java.util.List;

public class SongModel {

    private ObservableList<Song> songs;
    private SongManager songManager;

    public SongModel() throws Exception {
        songManager = new SongManager();
        songs = FXCollections.observableArrayList();
        songs.addAll(songManager.getSongs());
    }

    public ObservableList<Song> getObservableSongs() {
        return songs;
    }

    public void createSong(String title, String artist, String category, int time) throws Exception {
        Song s = songManager.createSong(title, artist, category, time);

        songs.add(s);
    }

    public void searchSong(String query) throws Exception {
        List<Song> searchResults = songManager.search(query);
        songs.clear();
        songs.addAll(searchResults);
    }

    public void updateSong(Song updatedSong) throws Exception {
        songManager.updateSong(updatedSong);
        songs.clear();
        songs.addAll(songManager.getSongs());
    }

    public void deleteSong(Song deletedSong) throws Exception {
        songManager.deleteSong(deletedSong);
        songs.clear();
        songs.addAll(songManager.getSongs());
    }
}
