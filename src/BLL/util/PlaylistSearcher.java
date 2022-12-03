package BLL.util;

import BE.Playlist;
import BE.Song;

import java.util.ArrayList;
import java.util.List;

public class PlaylistSearcher {
    public List<Playlist> search(List<Playlist> searchBase, String query){
    List<Playlist> searchResult = new ArrayList<>();

        for(Playlist playlist : searchBase){
        if(comparePlaylistTitles(query, playlist))
        {
            searchResult.add(playlist);
        }
    }
        return searchResult;
}

    private boolean comparePlaylistTitles(String query, Playlist playlist){
        return playlist.getTitle().toLowerCase().contains(query.toLowerCase());
    }
}
