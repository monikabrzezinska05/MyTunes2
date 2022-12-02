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

    public List<Playlist> getPlaylists() throws SQLServerException {
        ArrayList<Playlist> allPlaylists = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM Playlist;";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {

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
    public List<Playlist> getPlaylist() throws Exception {
        return null;
    }

    @Override
    public Playlist createPlaylist(String title) throws Exception {
        String sql = "INSERT INTO Playlist (title)VALUES (?);";

        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, title);

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            int id = 0;

            if (rs.next())
            {
                id = rs.getInt(1);
            }
            Playlist playlist = new Playlist(id, title);
            return playlist;
        } catch (SQLException exc) {
            exc.printStackTrace();
            throw new Exception("Could not create playlist", exc);
        }
    }

    @Override
    public void updatePlaylist(Playlist playlist) throws Exception {
        try(Connection connection = databaseConnector.getConnection()){

            String sql ="UPDATE Playlist SET title = ? WHERE ID = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, playlist.getTitle());

            statement.executeUpdate();
        }
        catch(SQLException exc){
            exc.printStackTrace();
            throw new Exception("Could not update playlist", exc);
        }

    }

    @Override
    public void deletePlaylist(Playlist playlist) throws Exception {
        try(Connection connection = databaseConnector.getConnection()){
            String sql = "DELETE FROM Playlist WHERE ID = ?;";
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

