package com.karoliskursevicius.myfavartist.itunes;

import com.karoliskursevicius.myfavartist.core.artist.Album;
import com.karoliskursevicius.myfavartist.core.artist.Artist;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Karolis Kursevičius
 */
@Component
@RequiredArgsConstructor
public class ITunesClient {
    private static final String ITUNES_ROOT_URI = "https://itunes.apple.com";

    private final RestTemplate iTunesRestTemplate;

    public List<Artist> searchArtists(String query) {
        Objects.requireNonNull(query);
        String url = ITUNES_ROOT_URI + "/search?entity=allArtist&term={term}";
        ResponseEntity<Response> response = iTunesRestTemplate.exchange(url, HttpMethod.GET, null, Response.class, query);
        return response.getBody().getResults().stream()
                .filter(Result::isArtist)
                .map(this::toArtist)
                .collect(Collectors.toUnmodifiableList());
    }

    public Set<Album> getTopAlbums(String artistId) {
        Objects.requireNonNull(artistId);
        String url = ITUNES_ROOT_URI + "/lookup?entity=album&amgArtistId={amgArtistId}&limit=5";
        ResponseEntity<Response> response = iTunesRestTemplate.exchange(url, HttpMethod.GET, null, Response.class, artistId);
        List<Album> albums = response.getBody().getResults().stream()
                .filter(Result::isAlbum)
                .map(this::toAlbum)
                .collect(Collectors.toUnmodifiableList());
        for (int i = 0; i < albums.size(); i++) {
            albums.get(i).setRank(i + 1);
        }

        return albums.stream()
                .collect(Collectors.toUnmodifiableSet());
    }

    private Artist toArtist(Result source) {
        Artist target = new Artist();
        target.setExternalId(source.getAmgArtistId());
        target.setName(source.getArtistName());
        target.setGenre(source.getPrimaryGenreName());
        return target;
    }

    private Album toAlbum(Result source) {
        Album target = new Album();
        target.setExternalId(source.getCollectionId());
        target.setName(source.getCollectionName());
        target.setGenre(source.getPrimaryGenreName());
        return target;
    }
}
