package DAL;

import BE.Playlist;
import BE.Song;

import java.util.List;

public interface IPlaylistSongDataAccess {//The interface used for PlaylistSongDAO_DB
    void addSongToPlaylist(Playlist playlist, Song song);

    public List<Song> loadSongsFromPlaylist(Playlist playlist);

    void removeSongFromPlaylist(Playlist rSPlaylist, Song Song) throws Exception;
}
