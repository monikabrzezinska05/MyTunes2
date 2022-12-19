package GUI.Controller;

import BE.MusicPlayer;
import BE.Playlist;
import BE.Song;
import GUI.Model.PlaylistModel;
import GUI.Model.SongModel;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
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
    public Button searchButton;
    @FXML
    private TextField searchBar;
    @FXML
    private TableView<Song> table;
    @FXML
    private TableColumn<Song, String> time;
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
    private TableColumn<Playlist, String> plTime;
    private SongModel songModel;
    private static final MusicPlayer musicPlayer = new MusicPlayer();


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
        //Sets up the playlist table column and the song table column. Giving the columns the value property we want to present.
        //Making them interactable.
        setup();
        title.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
        category.setCellValueFactory(new PropertyValueFactory<Song, String>("category"));
        artist.setCellValueFactory(new PropertyValueFactory<Song, String>("artist"));
        time.setCellValueFactory(t -> new SimpleStringProperty(t.getValue().getTimeStamp()));


        table.setItems(songModel.getObservableSongs());

        plTitle.setCellValueFactory(new PropertyValueFactory<Playlist, String>("plTitle"));
        plTime.setCellValueFactory(t -> new SimpleStringProperty(t.getValue().getTimeStamp()));
        plSongs.setCellValueFactory(new PropertyValueFactory<Playlist, Integer>("plSongs"));

        plTable.setItems(playlistModel.getObservablePlaylist());
        listview.setItems(playlistModel.getOSPS());

    }

    // Disables the Edit/Delete button until a song or playlist has been selected. Volume slider to change the volume
    @Override
    public void setup() {
        editSong.setDisable(true);
        editPlaylist.setDisable(true);
        deleteSongInPlaylist.setDisable(true);
        deletePlaylist.setDisable(true);
        deleteSong.setDisable(true);

        volumeSlider.setValue(musicPlayer.getVolume() * 100);
        volumeSlider.valueProperty().addListener(observable -> mediaPlayer.setVolume(volumeSlider.getValue() / 100));

        table.setItems(songModel.getObservableSongs());
        plTable.setItems(playlistModel.getObservablePlaylist());
        listview.setItems(playlistModel.getOSPS());

        listview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>() {
            @Override
            public void changed(ObservableValue<? extends Song> observable, Song oldValue, Song newValue) {
                deleteSongInPlaylist.setDisable(newValue == null);
            }
        });

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>() {
            @Override
            public void changed(ObservableValue<? extends Song> observable, Song oldValue, Song newValue) {
                deleteSong.setDisable(newValue == null);
            }
        });
        plTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Playlist>() {
            @Override
            public void changed(ObservableValue<? extends Playlist> observable, Playlist oldValue, Playlist newValue) {
                deletePlaylist.setDisable(newValue == null);
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
        File file = new File(songPath);//Creates a new file with the parameter songPath
        Media mSong = new Media(file.getAbsoluteFile().toURI().toString());//creates a new media with the name mSong,
        //takes the previous file, gets an abstract pathname, changes it to URI and then to string, so the program can read it.
        if(mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
        {//if the media player isn't null, and the media player is playing, it'll stop.
            mediaPlayer.stop();
        }
        try{
            mediaPlayer = new MediaPlayer(mSong);//sets mediaPlayer = to MediaPlayer mSong
            playingTimer();//Changes to a hour:minutes:seconds time format
            mediaPlayer.play();//starts the song
            mediaPlayer.setOnEndOfMedia(()->{
                Song song;
                if(table.getSelectionModel().getSelectedIndex() != -1){
                    int nextSongIndex = table.getSelectionModel().getSelectedIndex() +1;
                    table.getSelectionModel().select(nextSongIndex);
                    song = table.getSelectionModel().getSelectedItem();

                }else if(listview.getSelectionModel().getSelectedIndex() != -1){
                    int nextSongIndex = listview.getSelectionModel().getSelectedIndex() +1;
                    listview.getSelectionModel().select(nextSongIndex);
                    song = listview.getSelectionModel().getSelectedItem();
                }else{
                    return;
                }
                try {
                    playSong(song.getFPath());

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            });
        }catch (Exception exc) {
            exc.printStackTrace();
            throw new Exception("Could not play song", exc);
        }
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
    //Pressing the edit button opens the edit playlist window where you edit the name of your playlist.
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
    //Pressing the new song button, opens the window for adding a new song to the Songs table in the database.
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
    //Adds a song to a playlist, through the MyTunes view controller.
   public void handleAddSongs(ActionEvent actionEvent) throws Exception {
        if(plTable.getSelectionModel().getSelectedIndex() != -1 && table.getSelectionModel().getSelectedIndex() != -1){

            Playlist currentPlaylist = plTable.getSelectionModel().getSelectedItem();
            Song songToBeAdded = table.getSelectionModel().getSelectedItem();

            playlistModel.addSongToPlaylist(currentPlaylist, songToBeAdded);
            listview.getItems().add(songToBeAdded);
            table.getSelectionModel().clearSelection();
        }
    }
    //Button to start playing a song, calls the method playSong.
    public void handlePlayBtn(ActionEvent actionEvent) throws Exception {
        Song songToPlay = table.getSelectionModel().getSelectedItem();
        if (songToPlay != null) {
            playSong(songToPlay.getFPath());
        } else {
            Song songsToPlay = listview.getSelectionModel().getSelectedItem();
            playSong(songsToPlay.getFPath());
        }
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
                        int time = (int) (mediaPlayer.getCurrentTime().toMillis()/1000);
                        int minutes = time /60;
                        int seconds = time %60;
                        String textSeconds;
                        if(seconds <= 9){
                            textSeconds = "0" + seconds;
                        }else{
                            textSeconds = ""+ seconds;
                        }
                        return minutes + ":" + textSeconds;
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
    // Confirmation popup when trying to delete a song from a playlist, and deletes when pressing OK.
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
           }
       }
   }
    //a mouseEvent that makes your clicks interact with the playlist table.
    //Updating the listview, with the songs of the playlist.
    public void plClicker(MouseEvent mouseEvent) {
        if(plTable.getSelectionModel().getSelectedIndex() != -1){
            playlistModel.loadSongsFromPlaylist(plTable.getSelectionModel().getSelectedItem());
        }
    }
    //a button to make a song move a spot up on the playlist
    public void buttonSongUp(ActionEvent actionEvent) {
       int index = listview.getSelectionModel().getSelectedIndex();
        if (index != 0) {
           listview.getItems().add(index - 1, listview.getItems().remove(index));
           listview.getSelectionModel().clearAndSelect(index - 1);
        }
    }
    //a button to make a song move down a spot on the playlist
    public void buttonSongDown(ActionEvent actionEvent) {
        int index = listview.getSelectionModel().getSelectedIndex();
            if (index != 0) {
                listview.getItems().add(index + 1, listview.getItems().remove(index));
                listview.getSelectionModel().clearAndSelect(index + 1);
            }
    }
    //a button to delete a song from a playlist.
    public void handleDeleteSongInPlaylist(ActionEvent actionEvent) throws Exception {
            try {
                confirmationAlertSongInPlaylist();
            } catch (Exception exc) {
                exc.printStackTrace();
                throw new Exception("Could not delete song", exc);
            }
    }
    //a button to search through the songs table, finding either a song name or a playlist.
    //also changes the "Search" button to a "Clear" button, and clears your previous search.
    public void handleSearchButton(ActionEvent actionEvent) {
        if (searchButton.getText().equals("Search")) {
            if (searchBar.getText() != null) {
                String search = searchBar.getText().toLowerCase();
                table.setItems(songModel.searchedSongs(search));
            }
            searchButton.setText("Clear");
        } else if (searchButton.getText().equals("Clear")) {
            searchBar.clear();
            table.setItems(songModel.getObservableSongs());
            searchButton.setText("Search");
        }
    }

    public void handleNextSong(ActionEvent actionEvent) throws Exception {
        int index = listview.getSelectionModel().getSelectedIndex();
        if (index != 0) {
            listview.getItems().add(index + 1, listview.getItems().remove(index + 1));
            listview.getSelectionModel().clearAndSelect(index + 1);
            Song songToPlay = listview.getSelectionModel().getSelectedItem();
            if (songToPlay != null) {
                playSong(songToPlay.getFPath());
            }
        }
    }

    public void handleLastSong(ActionEvent actionEvent) throws Exception {
        int index = listview.getSelectionModel().getSelectedIndex();
        if (index != 0) {
            listview.getItems().add(index - 1, listview.getItems().remove(index - 1));
            listview.getSelectionModel().clearAndSelect(index - 1);
            Song songToPlay = listview.getSelectionModel().getSelectedItem();
            if (songToPlay != null) {
                playSong(songToPlay.getFPath());
            }
        }
    }

    public void deselectSong(MouseEvent mouseEvent) {
        table.getSelectionModel().clearSelection();
    }

    public void deselectLV(MouseEvent mouseEvent) {
        listview.getSelectionModel().clearSelection();
    }
}