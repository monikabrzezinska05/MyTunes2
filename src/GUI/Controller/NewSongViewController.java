package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class NewSongViewController extends BaseController{

    @FXML
    private Button chooseFIle;
    @FXML
    private Button cancelSong;
    @FXML
    private Button saveSong;
    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtArtist;
    @FXML
    private TextField txtTime;
    @FXML
    private TextField txtFile;
    @FXML
    private MenuButton categoryDropdown;
    

    @Override
    public void setup() {
        
    }

    public void handleCategoryDropdown(ActionEvent actionEvent) {
    }

    public void handleCancelSong(ActionEvent actionEvent) {
    }

    public void handleSaveSong(ActionEvent actionEvent) {
    }
}
