package GUI.Controller;

import GUI.Model.PlaylistModel;
import GUI.Model.SongModel;

public abstract class BaseController {
    protected SongModel songModel;

    public void setModel(SongModel model){this.songModel = model;}

    public SongModel getModel(){
        return songModel;
    }


    PlaylistModel playlistModel;

    public void setPlaylistModelModel(PlaylistModel model){this.playlistModel = model;}

    public PlaylistModel getPlaylistModel(){ return playlistModel; }

    public abstract void setup();
}
