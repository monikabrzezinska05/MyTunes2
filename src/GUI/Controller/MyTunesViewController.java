package GUI.Controller;

import BE.Playlist;
import BE.Song;
import GUI.Model.PlaylistModel;
import GUI.Model.SongModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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

    @FXML
    private TableView<Playlist> plTable;

    @FXML
    private TableColumn<Playlist, String> plTitle;

    @FXML
    private TableColumn<Playlist, Integer> plSongs;

    @FXML
    private TableColumn<Playlist, Integer> plTime;


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
            playlistModel = new PlaylistModel();
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


        plTitle.setCellValueFactory(new PropertyValueFactory<Playlist, String>("plTitle"));
        plTime.setCellValueFactory(new PropertyValueFactory<Playlist, Integer>("plTime"));
        plSongs.setCellValueFactory(new PropertyValueFactory<Playlist, Integer>("plSongs"));

        plTable.setItems(playlistModel.getObservablePlaylist());

    }


    @Override
    public void setup() {
        //songModel = getModel().getSongModel();

        editSong.setDisable(true);

        table.setItems(songModel.getObservableSongs());

        searchBar.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                songModel.searchSong(newValue);
            } catch (Exception e) {
                displayError(e);
            }
        });

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>() {
            @Override
            public void changed(ObservableValue<? extends Song> observable, Song oldValue, Song newValue) {
                if (newValue != null) {
                    editSong.setDisable(false);

                }
                else
                    editSong.setDisable(false);
            }
        });
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

        NewPlaylistViewController controller = loader.getController();
        controller.setPlaylistModelModel(playlistModel);
        controller.setup();

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

        NewSongViewController controller = loader.getController();
        controller.setModel(songModel);
        controller.setup();

        stage.setScene(new Scene(root));
        stage.setTitle("New / Edit Song");
        stage.show();

    }

    public void handleEditSongs(ActionEvent actionEvent) throws IOException{

        Song selectedSong = table.getSelectionModel().getSelectedItem();
        songModel.setSelectedSong(selectedSong);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GUI/View/NewSongView.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        NewSongViewController controller = loader.getController();
        controller.setModel(super.getModel());
        controller.setup();

        Stage dialogWindow = new Stage();
        dialogWindow.setTitle("New / Edit song");
        dialogWindow.initModality(Modality.WINDOW_MODAL);
        dialogWindow.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        Scene scene = new Scene(pane);
        dialogWindow.setScene(scene);

        dialogWindow.show();
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
