package GUI.Controller;

import BE.Song;
import GUI.Model.SongModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MyTunesViewController extends BaseController implements Initializable {

    @FXML
    private TextField searchBar;

    @FXML
    private TextField playingSong;

    @FXML
    private TableView<Song> table;

    @FXML
    private TableColumn<Song, Integer> time;

    @FXML
    private TableColumn<Song, String> title;

    @FXML
    private TableColumn<Song, String> artist;

    @FXML
    private TableColumn<Song, String> category;

    //public ListView<Songs> lstSongs;
    //public ListView<SongsInPlaylist> lstSongsInPlaylist;
    //public ListView<Playlists> lstPlaylists;

    public Button newPlaylist;
    public Button editPlaylist;
    public Button deletePlaylist;

    public Button deleteSongInPlaylist;
    public Button newSong;
    public Button editSong;
    public Button playBtn;
    public Button deleteSong;
    public Button reverseBtn;
    public Button forwardBtn;
    public Button addSong;
    public Slider volumeSlider;

    private SongModel songModel;

    public MyTunesViewController()  {

        try {
            songModel = new SongModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //editSong.setDisable(true);
        title.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
        time.setCellValueFactory(new PropertyValueFactory<Song, Integer>("time"));
        category.setCellValueFactory(new PropertyValueFactory<Song, String>("category"));
        artist.setCellValueFactory(new PropertyValueFactory<Song, String>("artist"));

        table.setItems(songModel.getObservableSongs());
    }


    @Override
    public void setup() {

    }

    private void displayError(Throwable t) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }

    public void handleNewPlaylist(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/NewPlaylistView.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("New Playlist");
        stage.show();
    }

    public void handleEditPlaylist(ActionEvent actionEvent) {
    }

    public void handleDeletePlaylist(ActionEvent actionEvent) {
    }

    public void handleDeleteSongsInPlaylist(ActionEvent actionEvent) {
    }

    public void handleNewSongs(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/NewSongView.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("New / Edit Song");
        stage.show();

    }

    public void handleEditSongs(ActionEvent actionEvent) {
        try {
            Song editedSong = table.getSelectionModel().getSelectedItem();

            //editedSong.setTitle(txtTitle);
        } catch (Exception e) {
            displayError(e);
        }
    }

    public void handleDeleteSong(ActionEvent actionEvent) throws Exception {
      try {
        Song deletedSong = table.getSelectionModel().getSelectedItem();
        songModel.deleteSong(deletedSong);
    }
      catch (Exception exc){
          exc.printStackTrace();
          throw new Exception("Could not delete song", exc);
      }
    }

    public void handleAddSongs(ActionEvent actionEvent) {
    }
}
