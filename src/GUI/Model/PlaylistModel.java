package GUI.Model;

import BE.Playlist;
import BE.Song;
import BLL.PlaylistManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class PlaylistModel {

    private ObservableList<Playlist> playlist;
    private PlaylistManager playlistManager;
    private Playlist selectedPlaylist;
    private ObservableList<Song> selectedPlaylistSongs;

    public PlaylistModel() throws Exception {
        playlistManager = new PlaylistManager();
        playlist = FXCollections.observableArrayList();
        playlist.addAll(playlistManager.getPlaylist());
        selectedPlaylistSongs = FXCollections.observableArrayList();

    }

    public ObservableList<Playlist> getObservablePlaylist() {
        return playlist;
    }

    public void searchPlaylist(String query) throws Exception {
        List<Playlist> searchResults = playlistManager.search(query);
        playlist.clear();
        playlist.addAll(searchResults);
    }

    public void createPlaylist(String plTitle) throws Exception {
        Playlist p = playlistManager.createPlaylist(plTitle);

        playlist.add(p);
    }

    public void updatePlaylist(Playlist updatedPlaylist) throws Exception {
        playlistManager.updatePlaylist(updatedPlaylist);
        playlist.clear();
        playlist.addAll(playlistManager.getPlaylist());
    }

    public void deletePlaylist(Playlist deletedPlaylist) throws Exception {
        playlistManager.deletePlaylist(deletedPlaylist);
        playlist.clear();
        playlist.addAll(playlistManager.getPlaylist());
    }

    public void setSelectedPlaylist(Playlist selectedPlaylist) {
        this.selectedPlaylist = selectedPlaylist;
    }

    public Playlist getSelectedPlaylist() {
        this.selectedPlaylist = selectedPlaylist;
        return selectedPlaylist;
    }

    public void addSongToPlaylist(Playlist sTPlaylist, Song song) throws Exception {
        // Add Song to Playlist in DB
        playlistManager.addSongToPlaylist(sTPlaylist, song);

        //
        playlist.clear();
        playlist.addAll(playlistManager.getPlaylist());
    }

    public void loadSongsFromPlaylist(Playlist lSPlaylist) {
        playlistManager.loadSongsFromPlaylist(lSPlaylist);
        selectedPlaylistSongs.clear();
        selectedPlaylistSongs.addAll(playlistManager.loadSongsFromPlaylist(lSPlaylist));
    }

    public void removeSongFromPlaylist(Playlist selectedPlaylist, Song selectedSong) throws Exception {
        playlistManager.removeSongFromPlaylist(selectedPlaylist, selectedSong);
    }
    //OSPS = Observable playlist song
    public ObservableList<Song> getOSPS() {
        return selectedPlaylistSongs;
    }

}

