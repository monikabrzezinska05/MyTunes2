package GUI.Controller;

import BE.MusicPlayer;
import BE.Playlist;
import BE.Song;
import GUI.Model.PlaylistModel;
import BLL.PlaylistManager;
import GUI.Model.SongModel;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.StringBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
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
        time.setCellValueFactory(new PropertyValueFactory<Song, Integer>("time"));
        category.setCellValueFactory(new PropertyValueFactory<Song, String>("category"));
        artist.setCellValueFactory(new PropertyValueFactory<Song, String>("artist"));

        table.setItems(songModel.getObservableSongs());
        

        plTime.setCellValueFactory(new PropertyValueFactory<Playlist, Integer>("plTime"));
        plSongs.setCellValueFactory(new PropertyValueFactory<Playlist, Integer>("plSongs"));
        plTitle.setCellValueFactory(new PropertyValueFactory<Playlist, String>("plTitle"));

        plTable.setItems(playlistModel.getObservablePlaylist());
    }


    @Override
    public void setup() {
        editSong.setDisable(true);
        volumeSlider.setValue(musicPlayer.getVolume() * 100);
        volumeSlider.valueProperty().addListener(observable -> mediaPlayer.setVolume(volumeSlider.getValue() / 100));

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

                } else
                    editSong.setDisable(true);
            }
        });
    }
    private void playSong(String songPath) throws Exception {
        File file = new File(songPath);
        Media mSong = new Media(file.getAbsoluteFile().toURI().toString());
        //Label lTime = new Label();
        if(mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
        {
            mediaPlayer.stop();
        }
        try{
            mediaPlayer = new MediaPlayer(mSong);
            //double total = mediaPlayer.getTotalDuration().toMillis();
            //double current = mediaPlayer.getCurrentTime().toMillis();
            //lTime.setText(getTimers(current) + "/" + getTimers(total));
            mediaPlayer.play();
        }catch (Exception exc) {
            exc.printStackTrace();
            throw new Exception("Could not play song", exc);
        }

    }

        //    mediaPlayer.getTotalDuration().toMinutes();

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
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.show();
    }

    public void handleEditPlaylist(ActionEvent actionEvent) {
    }

    public void handleDeletePlaylist(ActionEvent actionEvent) throws Exception {
        try {
            Playlist deletedPlaylist = plTable.getSelectionModel().getSelectedItem();
            playlistModel.deletePlaylist(deletedPlaylist);
        } catch (Exception exc) {
            exc.printStackTrace();
            throw new Exception("Could not delete playlist", exc);
        }
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
        stage.setTitle("New Song");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.show();
    }

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
            dialogWindow.setTitle("Edit Movie");
            dialogWindow.initModality(Modality.WINDOW_MODAL);
            dialogWindow.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            dialogWindow.setScene(scene);
            dialogWindow.showAndWait();
        }
    }

    public void handleDeleteSong(ActionEvent actionEvent) throws Exception {
        try {
            Song deletedSong = table.getSelectionModel().getSelectedItem();
            songModel.deleteSong(deletedSong);
        } catch (Exception exc) {
            exc.printStackTrace();
            throw new Exception("Could not delete song", exc);
        }
    }

    public void handleAddSongs(ActionEvent actionEvent) {
        currentPlaylist = plTable.getSelectionModel().getSelectedItem();
        Song addSong = table.getSelectionModel().getSelectedItem();
        currentPlaylist.addSongToPlaylist(addSong);
        plTable.refresh();
    }

    public void handlePlayBtn(ActionEvent actionEvent) throws Exception {
        Song songToPlay = table.getSelectionModel().getSelectedItem();
        playSong(songToPlay.getFPath());
    }

    private void insertnamehere()
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

    public static String getTimers(double millis){
        millis /=1000;
        String sec = formatTime(millis %60);
        millis /= 60;
        String min = formatTime(millis %60);
        millis /= 60;
        String hour = formatTime(millis %24);
        return hour + ":" + min + ":" + sec;
    }

    public static String formatTime(double time){
        int t = (int)time;
        if (t > 9) {
            return String.valueOf(t);
        }
        return "0" +t;
    }
}