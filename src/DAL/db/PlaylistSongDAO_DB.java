package DAL.db;

import BE.Playlist;
import BE.Song;
import DAL.IPlaylistSongDataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlaylistSongDAO_DB implements IPlaylistSongDataAccess {
    private DatabaseConnector databaseConnector;

    public PlaylistSongDAO_DB() {
        databaseConnector = new DatabaseConnector();
    }

    public Song addSongToPlaylist(Playlist playlist, Song song) {
        try(Connection connection = databaseConnector.getConnection()){
            String sql = "INSERT INTO PlaylistSongs(PlaylistId, SongId) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, playlist.getId());
            statement.setInt(2, song.getId());

            statement.executeUpdate();
        }
        catch (SQLException exc){
            exc.printStackTrace();

        }
        return song;
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
