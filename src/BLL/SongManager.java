package BLL;

import BE.Song;
import BLL.util.SongSearcher;
import DAL.ISongDataAccess;
import DAL.SongDAO;
import DAL.db.SongDAO_DB;
public class SongManager {
    private SongSearcher ss = new SongSearcher();

    private ISongDataAccess songDAO;
}
