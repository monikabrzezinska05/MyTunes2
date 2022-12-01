package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        MenuButton menuButton = new MenuButton("Category");
        menuButton.getItems().addAll(new MenuItem("Pop"), new MenuItem("HipHop"));
    }

    public void handleCancelSong(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelSong.getScene().getWindow();
        stage.close();
    }

    public void handleSaveSong(ActionEvent actionEvent) {
    }

    public void handleSaveSong(ActionEvent actionEvent) {
    }

    /*public void handleSaveSong(ActionEvent actionEvent) {
        try {
        } catch
    }*/
}
