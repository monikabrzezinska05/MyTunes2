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

    /*public List<Playlist> getAllPlaylists throws Exception {
        return playlistDAO.getAllPlaylists();
    }*/

}