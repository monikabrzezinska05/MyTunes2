package BE;

import java.util.List;

//constructer for Song class.
public class Playlist {
    private int id;
    private String title;


    public Playlist(int id, String title) {
        this.id = id;
        this.title = title;

    }

    public static List<Playlist> search(List<Playlist> allPlaylists, String query) {
        return allPlaylists;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString()
    {
        return id + ": " + title;

    }
}