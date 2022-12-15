package DAL.db;

import BE.Playlist;
import BE.Song;
import DAL.IPlaylistSongDataAccess;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistSongDAO_DB implements IPlaylistSongDataAccess {
    private DatabaseConnector databaseConnector;

    public PlaylistSongDAO_DB() {
        databaseConnector = new DatabaseConnector();
    }

    //a method that connects to the database, and uses a SQL string to add a playlistId and a songId to the playlistSongs table
    public void addSongToPlaylist(Playlist playlist, Song song) {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "INSERT INTO PlaylistSongs(PlaylistId, SongId) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, playlist.getId());
            statement.setInt(2, song.getId());

            statement.executeUpdate();
        } catch (SQLException exc) {
            exc.printStackTrace();

        }
    }
    //a method that connects to the database, and uses a SQL string to delete a song from a specified playlist through the playlistSongs table
    public void removeSongFromPlaylist(Playlist rSPlaylist, Song Song) throws Exception {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "DELETE FROM PlaylistSongs WHERE PlaylistId = ? and SongId = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, rSPlaylist.getId());
            statement.setInt(2, Song.getId());

            statement.executeUpdate();
        } catch (SQLException exc) {
            exc.printStackTrace();
            throw new Exception("could not delete song from playlist");
        }
    }
    //a method that connects to the database, and uses a SQL string where it selects all from the song table.
    //then joins the two tables, giving us the title in our MyTunes controller, rather than just having Ids.
    public List<Song> loadSongsFromPlaylist(Playlist playlist) {
        ArrayList<Song> allSongs = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT song.* FROM PlaylistSongs JOIN Song ON SongId = Song.Id WHERE playlistId = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,playlist.getId());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

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
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
