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
    public Song createSong(String title, int length) throws Exception {
        String sql = "INSERT INTO Song (title, length)VALUES (?,?);";

        try(Connection connection = databaseConnector.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, title);
            statement.setInt(2, length);

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            int id = 0;

            if(rs.next());{
                id = rs.getInt(1);
            }

            Song song = new Song(id, title, length);
            return song;
        }
        catch (SQLException exc){
            exc.printStackTrace();
            throw new Exception("Could not create song", exc);

        }
    }


    @Override
    public void updateSong(Song song) throws Exception {
        try(Connection connection = databaseConnector.getConnection()){

            String sql = "UPDATE Song SET Title = ?, Artist = ?, Length = ? WHERE Id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, song.getTitle());
            statement.setString(2, song.getArtist());
            statement.setInt(3, song.getLenght());

            statement.executeUpdate();
        }
        catch(SQLException exc){
            exc.printStackTrace();
            throw new Exception("Could not update song", exc);
        }

    }

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
