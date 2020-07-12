package com.karoliskursevicius.myfavartist.itunes;

import com.karoliskursevicius.myfavartist.core.artist.Album;
import com.karoliskursevicius.myfavartist.core.artist.Artist;
import com.karoliskursevicius.myfavartist.core.artist.ExternalAlbumProvider;
import com.karoliskursevicius.myfavartist.core.artist.ExternalArtistSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Karolis Kursevičius
 */
@Component
@RequiredArgsConstructor
public class ITunesSearch implements ExternalArtistSearch, ExternalAlbumProvider {
    private final ITunesClient iTunesClient;

    @Override
    public List<Artist> search(String query) {
        Objects.requireNonNull(query);
        return iTunesClient.searchArtists(query);
    }

    @Override
    public Set<Album> getTopAlbums(String artistId) {
        Objects.requireNonNull(artistId);
        return iTunesClient.getTopAlbums(artistId);
    }
}
