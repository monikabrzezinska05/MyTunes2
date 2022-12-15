package DAL.db;

import BE.Playlist;
import DAL.IPlaylistDataAccess;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO_DB implements IPlaylistDataAccess {

    private DatabaseConnector databaseConnector;

    public PlaylistDAO_DB() {
        databaseConnector = new DatabaseConnector();
    }

    public List<Playlist> getPlaylist() throws SQLServerException {
        ArrayList<Playlist> allPlaylists = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM Playlist;";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("Id");
                String title = rs.getString("Title");

                Playlist playlist = new Playlist(title);
                playlist.setId(id);
                allPlaylists.add(playlist);
            }
            return allPlaylists;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //a method that connects to the database, and uses a SQL string to insert a title, to the playlist table
    @Override
    public Playlist createPlaylist(String plTitle) throws Exception {
        String sql = "INSERT INTO Playlist (Title)VALUES (?);";

        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, plTitle);

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            int id = 0;

            if (rs.next())
            {
                id = rs.getInt(1);
            }
            Playlist playlist = new Playlist(plTitle);
            return playlist;
        } catch (SQLException exc) {
            exc.printStackTrace();
            throw new Exception("Could not create playlist", exc);
        }
    }
    //a method that connects to the database, and uses a SQL string to update a title in the playlist table, with a specified Id.
    @Override
    public void updatePlaylist(Playlist playlist) throws Exception {
        try(Connection connection = databaseConnector.getConnection()){

            String sql ="UPDATE Playlist SET title = ? WHERE ID = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, playlist.getPlTitle());
            statement.setInt(2, playlist.getId());

            statement.executeUpdate();
        }
        catch(SQLException exc){
            exc.printStackTrace();
            throw new Exception("Could not update playlist", exc);
        }

    }
    //a method that connects to the database, and uses a SQL string to delete a specified Id from the playlist table.
    @Override
    public void deletePlaylist(Playlist playlist) throws Exception {
        int id = playlist.getId();
        try(Connection connection = databaseConnector.getConnection()){

            String sql = "DELETE FROM Playlist WHERE Id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, playlist.getId());

            statement.executeUpdate();
        }
        catch (SQLException exc){
            exc.printStackTrace();
            throw new Exception("Could not delete playlist", exc);
        }
    }
}

