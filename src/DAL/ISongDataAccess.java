package DAL;

import BE.Song;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.SQLException;
import java.util.List;

public interface ISongDataAccess {
    public List<Song> getAllSongs() throws SQLServerException;

    public Song createSong(String title, int length) throws Exception;

    public void updateSong(Song song) throws Exception;

    public void deleteSong(Song song) throws Exception;


}
