package BE;

public class Song {

    private int id;
    private String title;
    private int time;
    private String artist;
    private String category;
    private String fPath;


    // Making a constructer for our Song class, with the variables that we need.
    public Song(int id, String title, String artist, String category, int time, String fPath) {
        this.id = id;
        this.artist = artist;
        this.title = title;
        this.category = category;
        this.time = time;
        this.fPath = fPath;
    }

    // Making a new constructer for Song, that can be used to edit songs in our mucisplayer.
    public Song(String updateTitle, String updateArtist, int updateTime, String updateCategory, String updateFPath) {
        this.title = updateTitle;
        this.artist = updateArtist;
        this.time = updateTime;
        this.category = updateCategory;
        this.fPath = updateFPath;
    }


    //Making a bunch of get and set methods for our variables.
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

    //Filepath is stored in our database, then we use that filepath to play songs that are on your computer locally.
    public String getFPath() {return fPath;}

    public void setFPath(String fPath) {this.fPath = fPath;}

    @Override
    public String toString() {
        return title;
    }
    //Converts int time to a string, with calculated minutes and seconds.
    public String getTimeStamp(){
        int minutes = time /60;
        int seconds = time %60;
        String textSeconds;
        if(seconds <= 9){
            textSeconds = "0" + seconds;
        }else{
            textSeconds = ""+ seconds;
        }
        return minutes + ":" + textSeconds;
    }
}
