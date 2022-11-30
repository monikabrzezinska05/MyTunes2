package BE;

public class Song {

    private int id;
    private String title;
    private int lenght;
    private String artist;

    private String category;


    public Song(int id, String artist, String title, String category, int length) {
        this.id = id;
        this.artist = artist;
        this.title = title;
        this.category = category;
        this.lenght = length;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public int getLenght() { return lenght; }

    public void setLenght(int lenght) { this.lenght = lenght; }

    public String getCategory(String category) {return category;}

    public void setCategory(){this.category = category;}

    public String getArtist(String artist) {
        return artist;
    }

    public void setArtist() {this.artist = artist;}
}
