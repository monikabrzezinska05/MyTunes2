package DAL;

import BE.Playlist;
import BE.Song;

public interface IPlaylistSongDataAccess {
    Song addSongToPlaylist(Playlist playlist, Song song);
}
