package BE;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private int id;
    private String plTitle;
    private int plSongs;

    private int plTime;

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

    public void setPlTime(int plTime) {
        this.plTime = plTime;
    }

    public int getPlSongs() {
        return plSongs;
    }

    public void setPlSongs(int plSongs) {
        this.plSongs = plSongs;
    }


    //constructor for our Playlist class. With variables.
    public Playlist(String plTitle) {
        this.id = id;
        this.plTitle = plTitle;
        this.plSongs = plSongs;
    }

    //search list of playlist.
    public static List<Playlist> search(List<Playlist> allPlaylists, String query) {
        return allPlaylists;
    }

    //used to return list of songs in a specific playlist.



}