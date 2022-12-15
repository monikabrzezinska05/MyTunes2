package BE;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private int id;
    private String plTitle;
    private int plSongs;

    private int plTime;

    private List<Song> songs;

    // A bunch of get and set methods for our playlist class.
    public int getId() {
        return id;
    }

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

    // get method for our list of songs.
    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    //constructor for our Playlist class. With variables.
    public Playlist(String plTitle) {
        this.id = id;
        this.plTitle = plTitle;
        this.plSongs = plSongs;
        songs = new ArrayList<>();
    }

    //search list of playlist.
    public static List<Playlist> search(List<Playlist> allPlaylists, String query) {
        return allPlaylists;
    }

    //used to return list of songs in a specific playlist.
    public List<Song> getSongsInPlaylist() {
        return songs;
    }

    //adding a song to a playlist
    public void addSongToPlaylist(Song song) {
        songs.add(song);
    }

    public void removeSongFromPlaylist(Song song){
        songs.remove(song);
    }



}