package BE;

public class Song {

    private int id;
    private String title;
    private int time;
    private String artist;
    private String category;
    private String fPath;

    public Song(int id, String title, String artist, String category, int time, String fPath) {
        this.id = id;
        this.artist = artist;
        this.title = title;
        this.category = category;
        this.time = time;
        this.fPath = fPath;
    }

    public Song(String updateTitle, String updateArtist, int updateTime, String updateCategory, String updateFPath) {
        this.title = updateTitle;
        this.artist = updateArtist;
        this.time = updateTime;
        this.category = updateCategory;
        this.fPath = updateFPath;
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

    public String getFPath() {return fPath;}

    public void setFPath(String fPath) {this.fPath = fPath;}

    @Override
    public String toString() {
        return title;
    }
}
