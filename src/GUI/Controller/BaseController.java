package GUI.Controller;

import GUI.Model.SongModel;

public abstract class BaseController {
    private SongModel model;

    public void setModel(SongModel model){this.model = model;}

    public SongModel getModel(){
        return model;
    }

    public abstract void setup();

}
