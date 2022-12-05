package BE;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private int id;
    private String plTitle;
    private int plSongs;

    private int plTime;

    private List<Song> songs;


    public void setId(int id) {
        this.id = id;
    }

    public String getPlTitle() {
        return plTitle;
    }

    public void setPlTitle(String plTitle) {
        this.plTitle = plTitle;
    }

    public int getPlTime() {
        return plTime;
    }

    public int getPlSongs() {
        return plSongs;
    }

    public void setPlSongs(int plSongs) {
        this.plSongs = plSongs;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public Playlist(String plTitle) {
        this.id = id;
        this.plTitle = plTitle;
        this.plSongs = plSongs;
        songs = new ArrayList<>();
    }

    public static List<Playlist> search(List<Playlist> allPlaylists, String query) {
        return allPlaylists;
    }

    public List<Song> getSongsInPlaylist() {
        return songs;
    }

    public void addSongToPlaylist(Song song) {
        songs.add(song);
    }

    public int getId() {
        return id;
    }

}