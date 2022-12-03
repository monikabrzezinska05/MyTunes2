package BLL;

import BE.Song;
import BLL.util.SongSearcher;
import DAL.ISongDataAccess;
import DAL.db.SongDAO_DB;

import java.util.List;

public class SongManager {

    private SongSearcher ss = new SongSearcher();

    private ISongDataAccess songDAO;

    public SongManager(){
        songDAO = new SongDAO_DB();
    }

    public List<Song> getSongs() throws Exception{
        return songDAO.getSongs();
    }

    public List<Song> search(String query) throws Exception{
        List<Song> allSongs = getSongs();
        List<Song> searchResult = ss.search(allSongs, query);
        return searchResult;
    }

    public void updateSong(Song updatedSong) throws Exception {
        songDAO.updateSong(updatedSong);

    }

    public void deleteSong(Song deletedSong) throws Exception{
        songDAO.deleteSong(deletedSong);
        }

    public Song createSong(String title, String artist, String category, int time, String fPath) throws Exception {
        return songDAO.createSong(title, artist, category, time, fPath);
    }
}
