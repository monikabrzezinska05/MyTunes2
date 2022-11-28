package DAL;

import BE.Song;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.util.List;

public interface ISongDataAccess {
    public List<Song> getAllSongs() throws SQLServerException;

    public Song createSong(String title, String artist, int length);

    public void updateSong(Song song);

    public void deleteSong(Song song);
}
