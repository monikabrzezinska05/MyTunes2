package BLL;

import BE.Playlist;
import BE.PlaylistSong;
import BE.Song;
import DAL.IPlaylistSongDataAccess;
import DAL.db.PlaylistSongDAO_DB;

import java.util.List;

public class PlaylistSongManager {
    private IPlaylistSongDataAccess playlistSongDAO;
    public PlaylistSongManager() {
        playlistSongDAO = new PlaylistSongDAO_DB();
    }

    public List<PlaylistSong> search(String query){
        List<PlaylistSong> allSongs = getPlaylistSongs();
        List<PlaylistSong> searchResult = PlaylistSong.search(allSongs, query);
        return searchResult;
    }
    public Song addSongToPlaylist(Playlist playlist, Song song){
        return playlistSongDAO.addSongToPlaylist(playlist, song);
    }
}
