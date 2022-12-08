package BLL;

import BE.Playlist;
import BE.Song;
import DAL.IPlaylistSongDataAccess;
import DAL.db.PlaylistSongDAO_DB;

import java.util.List;

public class PlaylistSongManager {
    private IPlaylistSongDataAccess playlistSongDAO;
    public PlaylistSongManager() {
        playlistSongDAO = new PlaylistSongDAO_DB();
    }

}
