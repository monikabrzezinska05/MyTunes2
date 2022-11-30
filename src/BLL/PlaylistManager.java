package BLL;

import BE.Playlist;
import DAL.IPlaylistDataAccess;
import DAL.db.PlaylistDAO_DB;

import java.util.List;

public class PlaylistManager {
    private IPlaylistDataAccess playlistDAO;

    public PlaylistManager() {
        playlistDAO = new PlaylistDAO_DB();
    }

    public Playlist createPlaylist(String name) throws Exception {
        return playlistDAO.createPlaylist(name);
    }

}