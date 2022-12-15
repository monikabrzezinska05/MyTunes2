package BE;

import DAL.db.SongDAO_DB;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MusicPlayer {
    protected MediaPlayer mediaPlayer;
    protected Media media;
    protected Song song;
    //Making some get and set methods for our mediaplayer class.
    public Media getMedia() {
        return media;
    }

    public Song getSong() {
        return song;
    }

    //sets the song that should be played
    public void setSong(Song song) {
        if (song != null && this.song != song) {
            this.song =song;

            //gets filepath to play song
            if (!song.getFPath().isBlank()) {
                media = new Media(new File(SongDAO_DB.getFpath()).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
            }
        }
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    //mute funciton for volume
    public void mute() {
        setVolume(0);
    }

    //starts playing file
    public void play() {
        if (mediaPlayer != null)
            mediaPlayer.play();
    }

    //pauses song
    public void pause() {
        if (mediaPlayer != null)
            return; mediaPlayer.pause();
    }

    //stops playing file
    public void stop() {
        if (mediaPlayer != null)
            mediaPlayer.stop();
    }

    // used for volume slider
    public double getVolume() {
        if (mediaPlayer != null)
            return mediaPlayer.getVolume();
        return 0;
    }

    //for our volume slider
    public void setVolume(double volume) {
        if (mediaPlayer != null);
        mediaPlayer.setVolume(volume);
    }
}