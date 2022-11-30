package BE;

public class Song {

    private int id;
    private String title;
    private int time;
    private String artist;

    private String category;


    public Song(int id, String title, String artist, String category, int time) {
        this.id = id;
        this.artist = artist;
        this.title = title;
        this.category = category;
        this.time = time;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public int getTime() { return time; }

    public void setTime(int time) { this.time = time; }

    public String getCategory() {return category;}

    public void setCategory(String category){this.category = category;}

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {this.artist = artist;}
}
