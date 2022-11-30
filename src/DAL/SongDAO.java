package DAL;

import BE.Song;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class SongDAO {

    private static final String SONG_FILE = "data/song_titles.txt";
    private Path filePath = Path.of(SONG_FILE);

    public List<Song> getSongs() throws Exception {

        try {
            List<String> lines = Files.readAllLines(filePath);
            List<Song> songs = new ArrayList<>();

            for (String line : lines) {
                String[] separatedLine = line.split(",");

                int id = Integer.parseInt(separatedLine[0]);
                String title = separatedLine[1];
                String category = separatedLine[2];
                int length = Integer.parseInt(separatedLine[3]);


                //Create a new Song.be
                Song newSong = new Song(id, title, category, length);
                songs.add(newSong);
            }
                songs.sort(Comparator.comparingInt(Song::getId));

                return songs;
            }
        catch (IOException ex) {
                throw ex;
            }
}


}

