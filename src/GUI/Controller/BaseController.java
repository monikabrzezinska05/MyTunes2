package GUI.Controller;

import BE.Song;
import GUI.Model.PlaylistModel;
import GUI.Model.SongModel;

public abstract class BaseController {
    private SongModel model;

    public void setModel(SongModel model){this.model = model;}

    public SongModel getModel(){
        return model;
    }


    PlaylistModel playlistModel;

    public void setPlaylistModel(PlaylistModel model){this.playlistModel = model;}

    public PlaylistModel getPlaylistModel(){ return playlistModel; }

    public abstract void setup();

    protected void setSong(Song selectedItem) {
    }

}
