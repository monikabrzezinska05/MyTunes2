package DAL;

import BE.Playlist;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.util.List;

public interface IPlaylistDataAccess {

    public List<Playlist> getPlaylist() throws Exception;

    public Playlist createPlaylist (String plTitle) throws Exception;

    public void updatePlaylist(Playlist playlist) throws Exception;

    public void deletePlaylist(Playlist playlist)throws Exception;
}
