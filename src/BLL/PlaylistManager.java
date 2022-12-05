package BLL;

import BE.Playlist;
import BE.Song;
import DAL.IPlaylistDataAccess;
import DAL.db.PlaylistDAO_DB;

import java.util.List;

public class PlaylistManager {
    private IPlaylistDataAccess playlistDAO;

    public PlaylistManager() {
        playlistDAO = new PlaylistDAO_DB();
    }

    public void deletePlaylist(Playlist deletedPlaylist) throws Exception {
        playlistDAO.deletePlaylist(deletedPlaylist);
    }
    public void updatePlaylist(Playlist updatedPlaylist) throws Exception {
        playlistDAO.updatePlaylist(updatedPlaylist);
    }
    public Playlist createPlaylist(String plTitle) throws Exception {
        return playlistDAO.createPlaylist(plTitle);
    }

    public List<Playlist> getPlaylist() throws Exception {
        return playlistDAO.getPlaylist();
    }
    public List<Playlist> search(String query) throws Exception{
        List<Playlist> allSongs = getPlaylist();
        List<Playlist> searchResult = Playlist.search(allSongs, query);
        return searchResult;
    }

}