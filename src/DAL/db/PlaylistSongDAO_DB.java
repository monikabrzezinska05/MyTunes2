package DAL.db;

import BE.Playlist;
import BE.Song;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlaylistSongDAO_DB {
    private DatabaseConnector databaseConnector;

    public void addSongToPlaylist(Playlist playlist, Song song) throws Exception {
        try(Connection connection = databaseConnector.getConnection()){
            String sql = "INSERT INTO PlaylistSongs(PlaylistId, SongId) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, playlist.getId());
            statement.setInt(2, song.getId());

            statement.executeUpdate();
        }
        catch (SQLException exc){
            exc.printStackTrace();
            throw new Exception("Could not add song to playlist", exc);
        }
    }
    public void removeSongFromPlaylist(Playlist selectedPlist, Song selectedSong) throws Exception {
        try (Connection connection = databaseConnector.getConnection()){
            String sql = "DELETE FROM PlaylistSong WHERE PlaylistID = ? and SongId = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, selectedPlist.getId());
            statement.setInt(2, selectedSong.getId());

            statement.executeUpdate();
        }
        catch (SQLException exc){
            exc.printStackTrace();
            throw new Exception("could not delete song from playlist");
        }
    }
}
