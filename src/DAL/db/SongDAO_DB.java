package DAL.db;

import BE.Song;
import DAL.ISongDataAccess;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO_DB implements ISongDataAccess{

    private DatabaseConnector databaseConnector;
    public SongDAO_DB(){
        databaseConnector = new DatabaseConnector();
    }

    public List<Song> getAllSongs() throws SQLServerException {
        ArrayList<Song> allSongs = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection())
        {
            String sql = "SELECT * FROM Song;";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {

                int id = rs.getInt("ID");
                String title = rs.getString("Title");
                int length = rs.getInt("Length");

                Song song = new Song(id, title, length);
                allSongs.add(song);
            }
            return allSongs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Song createSong(String title, String artist, int length) {
        return null;
    }

    @Override
    public void updateSong(Song song) {

    }

    @Override
    public void deleteSong(Song song) {

    }
}
