package GUI.Controller;

import BE.MusicPlayer;
import BE.Playlist;
import BE.Song;
import GUI.Model.PlaylistModel;
import GUI.Model.SongModel;
import javafx.beans.binding.StringBinding;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.util.Optional;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class MyTunesViewController<songPath> extends BaseController implements Initializable {

    @FXML
    public Label currentlyPlayingSong;
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
    //public ListView<SongsInPlaylist> lstSongsInPlaylist;
    //public ListView<Playlists> lstPlaylists;
    @FXML
    public Slider volumeSlider;
    public MediaPlayer mediaPlayer;
    public ListView<Song> listview;
    @FXML
    private TextField searchBar;
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
    private SongModel songModel;
    private MediaView mediaView;
    private static final MusicPlayer musicPlayer = new MusicPlayer();
    public Playlist currentPlaylist;

    public MyTunesViewController() {

        try {
            songModel = new SongModel();
            playlistModel = new PlaylistModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setup();
        title.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
        category.setCellValueFactory(new PropertyValueFactory<Song, String>("category"));
        artist.setCellValueFactory(new PropertyValueFactory<Song, String>("artist"));
        time.setCellValueFactory(new PropertyValueFactory<Song, Integer>("time"));

        table.setItems(songModel.getObservableSongs());

        plTitle.setCellValueFactory(new PropertyValueFactory<Playlist, String>("plTitle"));
        plTime.setCellValueFactory(new PropertyValueFactory<Playlist, Integer>("plTime"));
        plSongs.setCellValueFactory(new PropertyValueFactory<Playlist, Integer>("plSongs"));

        plTable.setItems(playlistModel.getObservablePlaylist());
        listview.setItems(playlistModel.getOSPS());

    }

    // Disables the Edit button until a song or playlist has been selected. Volume slider to change the volume
    @Override
    public void setup() {
        editSong.setDisable(true);
        editPlaylist.setDisable(true);
        deleteSongInPlaylist.setDisable(true);

        volumeSlider.setValue(musicPlayer.getVolume() * 100);
        volumeSlider.valueProperty().addListener(observable -> mediaPlayer.setVolume(volumeSlider.getValue() / 100));

        table.setItems(songModel.getObservableSongs());
        plTable.setItems(playlistModel.getObservablePlaylist());
        listview.setItems(playlistModel.getOSPS());

        searchBar.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                songModel.searchSong(newValue);
            } catch (Exception e) {
                displayError(e);
            }
        });

        listview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>() {
            @Override
            public void changed(ObservableValue<? extends Song> observable, Song oldValue, Song newValue) {
                deleteSongInPlaylist.setDisable(newValue == null);
            }
        });

        plTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Playlist>() {
            @Override
            public void changed(ObservableValue<? extends Playlist> observable, Playlist oldValue, Playlist newValue) {
                editPlaylist.setDisable(newValue == null);
            }
        });

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>() {
            @Override
            public void changed(ObservableValue<? extends Song> observable, Song oldValue, Song newValue) {
                editSong.setDisable(newValue == null);
            }

        });
    }
    private void playSong(String songPath) throws Exception {
        File file = new File(songPath);
        Media mSong = new Media(file.getAbsoluteFile().toURI().toString());
        if(mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
        {
            mediaPlayer.stop();
        }
        try{
            mediaPlayer = new MediaPlayer(mSong);
            playingTimer();
            mediaPlayer.play();
        }catch (Exception exc) {
            exc.printStackTrace();
            throw new Exception("Could not play song", exc);
        }
    }

    private void displayError(Throwable t) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }

    // Pressing the New button open the New Playlist window where you chose the name for your playlist
    public void handleNewPlaylist(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/NewPlaylistView.fxml"));
        Parent root = loader.load();

        NewPlaylistViewController controller = loader.getController();
        controller.setPlaylistModel(playlistModel);
        controller.setup();

        stage.setScene(new Scene(root));
        stage.setTitle("New Playlist");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.show();
    }

    public void handleEditPlaylist(ActionEvent actionEvent) throws IOException {
        Playlist selectedPlaylist = plTable.getSelectionModel().getSelectedItem();
        if (selectedPlaylist != null) {

            playlistModel.setSelectedPlaylist(selectedPlaylist);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GUI/View/EditPlaylistView.fxml"));
            Parent root = loader.load();

            EditPlaylistViewController controller = loader.getController();
            controller.setPlaylistModel(playlistModel);
            controller.setup();

            Stage dialogWindow = new Stage();
            dialogWindow.setTitle("Edit Playlist");
            dialogWindow.initModality(Modality.WINDOW_MODAL);
            dialogWindow.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            dialogWindow.setScene(scene);
            dialogWindow.showAndWait();
        }
    }

    // Select a playlist to be deleted which pops up a confirmation window
    public void handleDeletePlaylist(ActionEvent actionEvent) throws Exception {
        try {
            confirmationAlertPlaylist();
        } catch (Exception exc) {
            exc.printStackTrace();
            throw new Exception("Could not delete playlist", exc);
        }
    }
    public void handleNewSongs(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/NewSongView.fxml"));
        Parent root = loader.load();

        NewSongViewController controller = loader.getController();
        controller.setModel(songModel);
        controller.setup();

        stage.setScene(new Scene(root));
        stage.setTitle("New Song");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.show();
    }

    // Select a song to be edited which pops up the edit window
    public void handleEditSongs(ActionEvent actionEvent) throws Exception {
        Song selectedSong = table.getSelectionModel().getSelectedItem();
        if (selectedSong != null) {

            songModel.setSelectedSong(selectedSong);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GUI/View/EditSongView.fxml"));
            Parent root = loader.load();

            EditSongViewController controller = loader.getController();
            controller.setModel(songModel);
            controller.setup();

            Stage dialogWindow = new Stage();
            dialogWindow.setTitle("Edit Song");
            dialogWindow.initModality(Modality.WINDOW_MODAL);
            dialogWindow.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            dialogWindow.setScene(scene);
            dialogWindow.showAndWait();
        }
    }

    // Select a song to be deleted which pops up a confirmation window
    public void handleDeleteSong(ActionEvent actionEvent) throws Exception {
        try {
            confirmationAlertSong();
        } catch (Exception exc) {
            exc.printStackTrace();
            throw new Exception("Could not delete song", exc);
        }
    }

   public void handleAddSongs(ActionEvent actionEvent) throws Exception {
        if(plTable.getSelectionModel().getSelectedIndex() != -1 && table.getSelectionModel().getSelectedIndex() != -1){

            Playlist currentPlaylist = plTable.getSelectionModel().getSelectedItem();
            Song songToBeAdded = table.getSelectionModel().getSelectedItem();

            playlistModel.addSongToPlaylist(currentPlaylist, songToBeAdded);
            listview.getItems().add(songToBeAdded);
        }
    }

    public void handlePlayBtn(ActionEvent actionEvent) throws Exception {
        Song songToPlay = table.getSelectionModel().getSelectedItem();
        playSong(songToPlay.getFPath());
    }

    // Actively shows how long the song has been playing in seconds and minutes
    private void playingTimer()
    {
        currentlyPlayingSong.textProperty().bind(
                new StringBinding()
                {
                    {
                        super.bind(mediaPlayer.currentTimeProperty());
                    }

                    @Override
                    protected String computeValue() {
                        String times = String.format("%d min, %d sec",
                                TimeUnit.MILLISECONDS.toMinutes((long)mediaPlayer.getCurrentTime().toMillis()),
                                TimeUnit.MILLISECONDS.toSeconds((long)mediaPlayer.getCurrentTime().toMillis()) -
                                        TimeUnit.MINUTES.toSeconds(
                                                TimeUnit.MILLISECONDS.toMinutes(
                                                        (long)mediaPlayer.getCurrentTime().toMillis()
                                                )
                                        )
                        );
                        return times;
                    }
                });
    }

    // Confirmation popup when trying to delete song and deletes when pressing OK
    public void confirmationAlertSong() throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("You are about to delete a Song");
        alert.setContentText("Are you sure you want to delete?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Song deletedSong = table.getSelectionModel().getSelectedItem();
            songModel.deleteSong(deletedSong);
        } else {

        }
    }

    // Confirmation popup when trying to delete playlist and deletes when pressing OK
    public void confirmationAlertPlaylist() throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("You are about to delete a Playlist");
        alert.setContentText("Are you sure you want to delete?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Playlist deletedPlaylist = plTable.getSelectionModel().getSelectedItem();
            playlistModel.deletePlaylist(deletedPlaylist);
        } else {

        }
    }

   public void confirmationAlertSongInPlaylist() throws Exception {
       Playlist playlistChoice = plTable.getSelectionModel().getSelectedItem();
       if (playlistChoice != null) {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Confirmation Dialog");
           alert.setHeaderText("You are about to delete a Song in a Playlist");
           alert.setContentText("Are you sure you want to delete?");
           Optional<ButtonType> result = alert.showAndWait();
           if (result.get() == ButtonType.OK) {
               Playlist selectedPlaylist = plTable.getSelectionModel().getSelectedItem();
               Song selectedSong = listview.getSelectionModel().getSelectedItem();
               playlistModel.removeSongFromPlaylist(selectedPlaylist, selectedSong);
               //plTable.getSelectionModel().select(selectedPlaylist);
               listview.getItems().remove(selectedSong);


           } else {

           }
       }
   }

    public void plClicker(MouseEvent mouseEvent) {
        if(plTable.getSelectionModel().getSelectedIndex() != -1){
            playlistModel.loadSongsFromPlaylist(plTable.getSelectionModel().getSelectedItem());

        }
    }

    public void buttonSongUp(ActionEvent actionEvent) {
       int index = listview.getSelectionModel().getSelectedIndex();
        if (index != 0) {
           listview.getItems().add(index - 1, listview.getItems().remove(index));
           listview.getSelectionModel().clearAndSelect(index - 1);
        }
    }

    public void buttonSongDown(ActionEvent actionEvent) {
        int index = listview.getSelectionModel().getSelectedIndex();
            if (index != 0) {
                listview.getItems().add(index + 1, listview.getItems().remove(index));
                listview.getSelectionModel().clearAndSelect(index + 1);
            }
    }

    public void handleDeleteSongInPlaylist(ActionEvent actionEvent) throws Exception {
            try {
                confirmationAlertSongInPlaylist();
            } catch (Exception exc) {
                exc.printStackTrace();
                throw new Exception("Could not delete song", exc);
            }
    }
}