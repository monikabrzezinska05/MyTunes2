package DAL.db;

import BE.Playlist;
import BE.Song;
import DAL.ISongDataAccess;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.beans.property.StringProperty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO_DB implements ISongDataAccess{

    private DatabaseConnector databaseConnector;
    private static StringProperty fPath;

    public SongDAO_DB(){
        databaseConnector = new DatabaseConnector();
    }

    public static String getFpath() {
        return fPath.get();
    }
    //a list of all the songs in our songs table.
    public List<Song> getSongs() throws SQLServerException {
        ArrayList<Song> allSongs = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection())
        {
            String sql = "SELECT * FROM Song;";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {

                int id = rs.getInt("ID");
                String title = rs.getString("Title");
                String artist = rs.getString("Artist");
                String category = rs.getString("Category");
                int time = rs.getInt("Time");
                String fPath = rs.getString("songPath");

                Song song = new Song(id, title, artist, category, time, fPath);
                allSongs.add(song);
            }
            return allSongs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //a method that connects to the database, and uses a SQL string to add a song to the song table,
    //with the given values title, artist, category, time and songPath
    @Override
    public Song createSong(String title, String artist, String category, int time, String fPath) throws Exception {
        String sql = "INSERT INTO Song (title, artist, category, time, songPath)VALUES (?,?,?,?,?);";

        try(Connection connection = databaseConnector.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, title);
            statement.setString(2, artist);
            statement.setString(3, category);
            statement.setInt(4, time);
            statement.setString(5, fPath);

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            int id = 0;

            if(rs.next());{
                id = rs.getInt(1);
            }

            Song song = new Song(id, title, artist, category, time, fPath);
            return song;
        }
        catch (SQLException exc){
            exc.printStackTrace();
            throw new Exception("Could not create song", exc);

        }
    }
    //a method that connects to the database, and uses a SQL string to update the title, artist, category of a song,
    //with a specified id on the song table.
    @Override
    public void updateSong(Song song) throws Exception {
        try(Connection connection = databaseConnector.getConnection()){

            String sql = "UPDATE Song SET Title = ?, Artist = ?, Category = ? WHERE Id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, song.getTitle());
            statement.setString(2, song.getArtist());
            statement.setString(3, song.getCategory());
            statement.setInt(4, song.getId());

            statement.executeUpdate();
        }
        catch(SQLException exc){
            exc.printStackTrace();
            throw new Exception("Could not update song", exc);
        }

    }
    //a method that connects to the database, and uses a SQL string to delete a song with a specified Id.
    @Override
    public void deleteSong(Song song) throws Exception {
        try(Connection connection = databaseConnector.getConnection()){

            String sql = "DELETE FROM Song WHERE id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, song.getId());

            statement.executeUpdate();
        }
        catch (SQLException exc){
            exc.printStackTrace();
            throw new Exception("Could not delete song", exc);
        }

    }


}
