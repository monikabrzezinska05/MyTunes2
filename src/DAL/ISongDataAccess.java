package DAL;

import BE.Playlist;
import BE.Song;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.SQLException;
import java.util.List;

public interface ISongDataAccess {
    public List<Song> getSongs() throws SQLServerException;

    public Song createSong(String title, String artist, String category, int time, String fPath) throws Exception;

    public void updateSong(Song song) throws Exception;

    public void deleteSong(Song song) throws Exception;

}
