package BE;

public class Song {

    private int id;
    private String title;
    private int lenght;
    private String artist;


    public Song(int id, String title, int length) {
        this.id = id;
        this.title = title;
        this.lenght = length;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public int getLenght() { return lenght; }

    public void setLenght(int lenght) { this.lenght = lenght; }

    public String getArtist() {
        return artist;
    }
}
