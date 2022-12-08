package DAL;

import BE.Playlist;
import BE.Song;

import java.util.List;

public interface IPlaylistSongDataAccess {
    void addSongToPlaylist(Playlist playlist, Song song);

    public List<Song> loadSongsFromPlaylist(Playlist playlist);
}
