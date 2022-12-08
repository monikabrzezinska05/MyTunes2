package GUI.Model;

import BE.Playlist;
import BE.Song;
import BLL.PlaylistSongManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlaylistSongModel {
    private ObservableList<PlaylistSong> playlistSong;

    private PlaylistSongManager playlistSongManager;

    public PlaylistSongModel(){
        playlistSongManager = new PlaylistSongManager();
        playlistSong = FXCollections.observableArrayList();
        playlistSong.addAll(playlistSongManager.getPlaylist());
    }

    public void addSongToPlaylist(Playlist selectedItem, Song selectedItem1) {
    }
}
