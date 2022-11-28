package DAL.db;

import BE.Playlist;
import DAL.IPlaylistDataAccess;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO_DB implements IPlaylistDataAccess {

    private DatabaseConnector databaseConnector;
    public PlaylistDAO_DB(){
        databaseConnector = new DatabaseConnector();
    }

    public List<Playlist> getAllPlaylists() throws SQLServerException {
        ArrayList<Playlist> allPlaylists = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection())
        {
            String sql = "SELECT * FROM Playlist;";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {

                int id = rs.getInt("ID");
                String title = rs.getString("Title");

                Playlist playlist = new Playlist(id, title);
                allPlaylists.add(playlist);
            }
            return allPlaylists;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Playlist createPlaylist(String title, int id) {
        return null;
    }

    @Override
    public void updatePlaylist(Playlist playlist) {

    }

    @Override
    public void deletePlaylist(Playlist playlist) {

    }

}

